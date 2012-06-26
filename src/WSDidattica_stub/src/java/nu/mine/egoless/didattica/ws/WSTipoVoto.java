/*
 * Nome file: WSTipoVoto.java
 * Data creazione: March 13, 2007, 2:26 PM
 * Info svn: $Id: WSTipoVoto.java 825 2007-03-26 19:33:46Z eric $
 */
package nu.mine.egoless.didattica.ws;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import nu.mine.egoless.ws.tipovotoclient.TipoVoto;
import ws.didattica.stub.Configuration;
import ws.didattica.stub.Storage;

/**
 * Web Service per la gestione dei tipi di voto
 */
@Stateless()
@WebService()
public class WSTipoVoto {
   
   @PersistenceContext
   private EntityManager em;
   
   /**
    * Aggiunge un nuovo tipo di voto.
    * @param nuovoTipoVoto Tipo di voto da aggiungere.
    * @return Id assegnato al tipo di voto creato.
    * @throws WSDidatticaException Lanciata quando non e' stato passato
    *                              alcun tipo.
    */
   @WebMethod
   public int aggiungiTipoVoto(@WebParam(name = "nuovoTipoVoto") TipoVoto nuovoTipoVoto) throws WSDidatticaException{
      if (nuovoTipoVoto==null) {
         throw new WSDidatticaException("Non e' stato passato alcun tipo di voto.");
      } else {
         return Storage.persist(em, TipoVoto.class, nuovoTipoVoto, Storage.TIPO_VOTO);
      }
   }
   
   /**
    * Cancella un tipo di voto.
    * @param idTipoVoto Identificativo del tipo di voto da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaTipoVoto(@WebParam(name = "idTipoVoto") int idTipoVoto) throws WSDidatticaException{
      Storage.delete(em, idTipoVoto);
   }
   
   /**
    * Ottiene tutte i tipi di voto.
    * @return Insieme di tutti i tipi di voto.
    * @throws WSDidatticaException Lanciata quando si verificano
    * errori nel Web Service.
    */
   @WebMethod
   public TipoVoto[] recuperaTipiVoto() throws WSDidatticaException{
      Configuration conf=em.find(Configuration.class, 1L);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setRecuperaTipiVotoBehavior(2);
      } else {
         em.refresh(conf);
      }
      
      if (conf==null || conf.getRecuperaTipiVotoBehavior()==0) {
         return null;
      } else {
         List<TipoVoto> result=new ArrayList<TipoVoto>();
         JAXBContext jc;
         JAXBElement<TipoVoto> je;
          
         if (conf.getRecuperaTipiVotoBehavior()==1) {
            //Restituiamo cio' che c'e' nel DB
            List<Storage> l=Storage.retrieve(em, Storage.TIPO_VOTO);
            
            try {
               jc = JAXBContext.newInstance(TipoVoto.class);
               
               Unmarshaller u = jc.createUnmarshaller();
               for(Storage st : l) {
                  je=u.unmarshal(new StreamSource(new StringReader(st.getContent())), TipoVoto.class);
                  TipoVoto tv =  je.getValue();
                  tv.setId(st.getId());
                  result.add(tv);
               }
            } catch (JAXBException ex) {
               ex.printStackTrace();
            }
         } else {
            //Restituiamo dei dati al volo
            TipoVoto obj=new TipoVoto();
            obj.setId(1);
            obj.setNome("1");
            obj.setValore("1");
            result.add(obj);
            
            obj=new TipoVoto();
            obj.setId(2);
            obj.setNome("2");
            obj.setValore("2");
            result.add(obj);
            
            obj=new TipoVoto();
            obj.setId(3);
            obj.setNome("3");
            obj.setValore("3");
            result.add(obj);
         }
         
         return result.toArray(new TipoVoto[] {});
      }
   }
   
}
