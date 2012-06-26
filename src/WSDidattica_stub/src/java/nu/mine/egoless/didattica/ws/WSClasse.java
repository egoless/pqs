/*
 * Nome file: WSClasse.java
 * Data creazione: March 13, 2007, 1:48 PM
 * Info svn: $Id: WSClasse.java 825 2007-03-26 19:33:46Z eric $
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
import nu.mine.egoless.ws.classeclient.Classe;
import ws.didattica.stub.Configuration;
import ws.didattica.stub.Storage;

/**
 * Web Service per gestire le classi.
 */
@Stateless()
@WebService()
public class WSClasse {
   
   @PersistenceContext
   private EntityManager em;
   
   /**
    * Determina se i dati di una classe sono validi.
    * @param classeAttuale Classe da validare.
    * @throws WSDidatticaException se ci sono errori di validazione.
    */
   private void validaClasse(Classe classeAttuale) throws WSDidatticaException {
      if (classeAttuale==null) {
         throw new WSDidatticaException("Non e' stato passato alcuna classe.");
      }
   }
   
   /**
    * Aggiunge una nuova classe.
    * @param nuovaClasse Classe da aggiungere.
    * @return Id assegnato alla classe creata.
    * @throws WSDidatticaException Lanciata quando non e' stato passato
    *                              alcun tipo.
    */
   @WebMethod
   public int aggiungiClasse(@WebParam(name = "nuovaClasse") Classe nuovaClasse) throws WSDidatticaException {
      validaClasse(nuovaClasse);
      return Storage.persist(em, Classe.class, nuovaClasse, Storage.CLASSE);
   }
   
   /**
    * Cancella una classe.
    * @param idClasse Identificativo della classe da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaClasse(@WebParam(name = "idClasse") int idClasse) throws WSDidatticaException {
      Storage.delete(em, idClasse);
   }
   
   /**
    * Ottiene tutte le classi.
    * @return Insieme di tutte le classi.
    * @throws WSDidatticaException Lanciata quando si verificano
    * errori nel Web Service.
    */
   @WebMethod
   public Classe[] recuperaClassi() throws WSDidatticaException {
      //em.flush();
      Configuration conf=em.find(Configuration.class, 1L);
      
      if (conf==null) {
         conf=new Configuration ();
         conf.setRecuperaClassiBehavior (2);
      } else {
         em.refresh(conf);
      }
      
      if (conf==null || conf.getRecuperaClassiBehavior()==0) {
         return null;
      } else {
         List<Classe> result=new ArrayList<Classe>();
         JAXBContext jc;
         JAXBElement<Classe> je;
         
         if (conf.getRecuperaClassiBehavior()==1) {
            List<Storage> l=Storage.retrieve(em, Storage.CLASSE);
            try {
               jc = JAXBContext.newInstance(Classe.class);
               
               Unmarshaller u = jc.createUnmarshaller();
               for(Storage st : l) {
                  je=u.unmarshal(new StreamSource(new StringReader(st.getContent())), Classe.class);
                  Classe tv =  je.getValue();
                  tv.setId(st.getId());
                  result.add(tv);
               }
            } catch (JAXBException ex) {
               ex.printStackTrace();
            }
         } else {
            //Restituiamo dei dati al volo
            Classe c=new Classe();
            c.setId(1);
            c.setAnnoCorso(1);
            c.setSezione('A');
            result.add(c);
            
            c=new Classe();
            c.setId(2);
            c.setAnnoCorso(2);
            c.setSezione('A');
            result.add(c);
            
            c=new Classe();
            c.setId(3);
            c.setAnnoCorso(3);
            c.setSezione('A');
            result.add(c);
            
            c=new Classe();
            c.setId(3);
            c.setAnnoCorso(4);
            c.setSezione('B');
            result.add(c);
            
            c=new Classe();
            c.setId(3);
            c.setAnnoCorso(5);
            c.setSezione('B');
            result.add(c);
         }
         
         return result.toArray(new Classe[] {});
      }
   }
}
