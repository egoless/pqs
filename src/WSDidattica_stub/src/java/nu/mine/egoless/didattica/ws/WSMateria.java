/*
 * Nome file: WSMateria.java
 * Data creazione: March 13, 2007, 3:06 PM
 * Info svn: $Id: WSMateria.java 825 2007-03-26 19:33:46Z eric $
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
import nu.mine.egoless.ws.materiaclient.MateriaInsegnamento;
import ws.didattica.stub.Configuration;
import ws.didattica.stub.Storage;

/**
 * Web Service che gestisce le materie.
 */
@Stateless()
@WebService()
public class WSMateria {
   
   @PersistenceContext
   private EntityManager em;
   
   
   /**
    * Aggiunge una nuova materia.
    * @param nuovaMateria Materia da aggiungere.
    * @return Id assegnato alla materia creata.
    * @throws WSDidatticaException Lanciata quando non e' stato passato
    *                              alcun tipo.
    */
   @WebMethod
   public int aggiungiMateria(@WebParam(name = "nuovaMateria") MateriaInsegnamento nuovaMateria) throws WSDidatticaException{
      if (nuovaMateria==null) {
         throw new WSDidatticaException("Non e' stato passato alcuna materia.");
      } else {
         return Storage.persist(em, MateriaInsegnamento.class, nuovaMateria, Storage.MATERIA);
      }
   }
   
   /**
    * Cancella una materia.
    * @param idMateria Identificativo della materia da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaMateria(@WebParam(name = "idMateria") int idMateria) throws WSDidatticaException{
      Storage.delete(em, idMateria);
   }
   
   /**
    * Ottiene tutte le materie.
    * @return Insieme di tutte le materie.
    * @throws WSDidatticaException Lanciata quando si verificano
    * errori nel Web Service.
    */
   @WebMethod
   public MateriaInsegnamento[] recuperaMaterie() throws WSDidatticaException{
      Configuration conf=em.find(Configuration.class, 1L);
      
      if (conf==null) {
         conf=new Configuration();
         conf.setRecuperaMaterieBehavior(2);
      } else {
          em.refresh(conf);
      }
      
      if (conf==null || conf.getRecuperaMaterieBehavior()==0) {
         return null;
      } else {
         List<MateriaInsegnamento> result=new ArrayList<MateriaInsegnamento>();
         JAXBContext jc;
         JAXBElement<MateriaInsegnamento> je;
         
         if (conf.getRecuperaMaterieBehavior()==1) {
            List<Storage> l=Storage.retrieve(em, Storage.MATERIA);
            try {
               jc = JAXBContext.newInstance(MateriaInsegnamento.class);
               
               Unmarshaller u = jc.createUnmarshaller();
               for(Storage st : l) {
                  je=u.unmarshal(new StreamSource(new StringReader(st.getContent())), MateriaInsegnamento.class);
                  MateriaInsegnamento tv =  je.getValue();
                  tv.setId(st.getId());
                  result.add(tv);
               }
            } catch (JAXBException ex) {
               ex.printStackTrace();
            }
         } else {
            //Restituiamo dei dati al volo
            MateriaInsegnamento obj=new MateriaInsegnamento();
            obj.setId(1);
            obj.setNome("Matematica");
            result.add(obj);
            
            obj=new MateriaInsegnamento();
            obj.setId(2);
            obj.setNome("Italiano");
            result.add(obj);
            
            obj=new MateriaInsegnamento();
            obj.setId(3);
            obj.setNome("Elettronica");
            result.add(obj);
         }
         
         return result.toArray(new MateriaInsegnamento[] {});
      }
   }
}
