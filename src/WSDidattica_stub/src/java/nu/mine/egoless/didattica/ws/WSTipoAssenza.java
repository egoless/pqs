/*
 * Nome file: WSTipoAssenza.java
 * Data creazione: March 13, 2007, 1:48 PM
 * Info svn: $Id: WSTipoAssenza.java 825 2007-03-26 19:33:46Z eric $
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
import nu.mine.egoless.ws.tipoassenzaclient.TipoAssenza;
import ws.didattica.stub.Configuration;
import ws.didattica.stub.Storage;

/**
 * Web Service per gestire i tipi di assenza.
 */
@Stateless()
@WebService()
public class WSTipoAssenza {
   
   @PersistenceContext
   private EntityManager em;
   
   /**
    * Aggiunge un nuovo tipo di assenza.
    * @param nuovoTipoAssenza Tipo di assenza da aggiungere.
    * @return Id assegnato al tipo di assenza creato.
    * @throws WSDidatticaException Lanciata quando non e' stato passato
    *                              alcun tipo.
    */
   @WebMethod
   public int aggiungiTipoAssenza(@WebParam(name = "nuovoTipoAssenza") TipoAssenza nuovoTipoAssenza) throws WSDidatticaException{
      if (nuovoTipoAssenza==null) {
         throw new WSDidatticaException("Non e' stato passato alcun tipo di assenza.");
      } else {
         return Storage.persist(em, TipoAssenza.class, nuovoTipoAssenza, Storage.TIPO_ASSENZA);
      }
   }
   
   /**
    * Cancella un tipo di assenza.
    * @param idTipoAssenza Identificativo del tipo di assenza da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaTipoAssenza(@WebParam(name = "idTipoAssenza") int idTipoAssenza) throws WSDidatticaException{
      Storage.delete(em, idTipoAssenza);
   }
   
   /**
    * Ottiene tutti i tipi di assenza.
    * @return Insieme di tutti i tipi di assenza.
    * @throws WSDidatticaException Lanciata quando si verificano
    * errori nel Web Service.
    */
   @WebMethod
   public TipoAssenza[] recuperaTipiAssenza() throws WSDidatticaException{
      Configuration conf=em.find(Configuration.class, 1L);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setRecuperaTipiAssenzaBehavior(2);
      } else {
         em.refresh(conf);
      }
      
      if (conf==null || conf.getRecuperaTipiAssenzaBehavior()==0) {
         return null;
      } else {
         List<TipoAssenza> result=new ArrayList<TipoAssenza>();
         JAXBContext jc;
         JAXBElement<TipoAssenza> je;
         
         if (conf.getRecuperaTipiAssenzaBehavior()==1) {
            List<Storage> l=Storage.retrieve(em, Storage.TIPO_ASSENZA);
            try {
               jc = JAXBContext.newInstance(TipoAssenza.class);
               
               Unmarshaller u = jc.createUnmarshaller();
               for(Storage st : l) {
                  je=u.unmarshal(new StreamSource(new StringReader(st.getContent())), TipoAssenza.class);
                  TipoAssenza tv =  je.getValue();
                  tv.setId(st.getId());
                  result.add(tv);
               }
            } catch (JAXBException ex) {
               ex.printStackTrace();
            }
         } else {
            //Restituiamo dei dati al volo
            TipoAssenza obj=new TipoAssenza();
            obj.setId(1);
            obj.setDescrizione("Motivi familiari");
            result.add(obj);
            
            obj=new TipoAssenza();
            obj.setId(2);
            obj.setDescrizione("Motivi sportivi");
            result.add(obj);
            
            obj=new TipoAssenza();
            obj.setId(3);
            obj.setDescrizione("Malattia");
            result.add(obj);
         }
         
         return result.toArray(new TipoAssenza[] {});
      }
   }
   
}
