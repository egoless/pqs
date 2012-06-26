/*
 * Nome file: WSTipoProva.java
 * Data creazione: 24 marzo 2007, 14.49
 * Info svn: $Id: WSTipoProva.java 719 2007-03-25 18:00:52Z eric $
 */

package nu.mine.egoless.didattica.ws;

import java.io.StringReader;
import java.util.List;
import java.util.ArrayList;

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

import nu.mine.egoless.ws.tipoprovaclient.TipoProvaWSService;
import nu.mine.egoless.ws.tipoprovaclient.TipoProvaWS;
import nu.mine.egoless.ws.tipoprovaclient.TipoProva;
import nu.mine.egoless.ws.tipoprovaclient.OperazioneNonValida_Exception;
import ws.didattica.stub.Configuration;
import ws.didattica.stub.Storage;

/*
 * Web service per gestire i tipi di prova (orale, scritto, fine anno, ...).
 */
@Stateless()
@WebService()
public class WSTipoProva {
   
   @PersistenceContext
   private EntityManager em;
   
   /**
    * Aggiunge un nuovo tipo di prova.
    * @param nuovoTipoAssenza Tipo di assenza da aggiungere.
    * @return Id assegnato al tipo di assenza creato.
    * @throws WSDidatticaException Lanciata quando non e' stato passato
    *                              alcun tipo.
    */
   @WebMethod
   public int aggiungiTipoProva(@WebParam(name = "nuovoTipoProva") TipoProva nuovoTipoProva) throws WSDidatticaException{
      if (nuovoTipoProva==null) {
         throw new WSDidatticaException("Non e' stato passato alcun tipo di prova.");
      } else {
         return Storage.persist(em, TipoProva.class, nuovoTipoProva, Storage.TIPO_PROVA);
      }
   }
   
   /**
    * Cancella un tipo di prova.
    * @param idTipoProva Identificativo del tipo di prova da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaTipoProva(@WebParam(name = "idTipoProva") int idTipoProva) throws WSDidatticaException{
      Storage.delete(em, idTipoProva);
   }
   
   /**
    * Ottiene tutti i tipi di prova.
    * @return Insieme di tutti i tipi di prova.
    * @throws WSDidatticaException Lanciata quando si verificano
    * errori nel Web Service.
    */
   @WebMethod
   public TipoProva[] recuperaTipiProva() throws WSDidatticaException{
      //em.flush();
      Configuration conf=em.find(Configuration.class, 1L);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setRecuperaTipiProvaBehavior(2);
      } else {
         em.refresh(conf);
      }
      
      if (conf==null || conf.getRecuperaTipiProvaBehavior()==0) {
         return null;
      } else {
         List<TipoProva> result=new ArrayList<TipoProva>();
         JAXBContext jc;
         JAXBElement<TipoProva> je;
         
         if (conf.getRecuperaTipiProvaBehavior()==1) {
            List<Storage> l=Storage.retrieve(em, Storage.TIPO_PROVA);
            try {
               jc = JAXBContext.newInstance(TipoProva.class);
               
               Unmarshaller u = jc.createUnmarshaller();
               for(Storage st : l) {
                  je=u.unmarshal(new StreamSource(new StringReader(st.getContent())), TipoProva.class);
                  TipoProva tv =  je.getValue();
                  tv.setId(st.getId());
                  result.add(tv);
               }
            } catch (JAXBException ex) {
               ex.printStackTrace();
            }
         } else {
            //Restituiamo dei dati al volo
            TipoProva c=new TipoProva();
            c.setId(1);
            c.setNome("Scritto");
            result.add(c);
            
            c=new TipoProva();
            c.setId(2);
            c.setNome("Orale");
            result.add(c);
            
            c=new TipoProva();
            c.setId(3);
            c.setNome("Scrutinio finale");
            result.add(c);
         }
         
         return result.toArray(new TipoProva[] {});
      }
   }
}
