/*
 * Nome file: WSVoto.java
 * Data creazione: March 13, 2007, 9:32 AM
 * Info svn: $Id: WSVoto.java 778 2007-03-26 08:36:12Z roberto $
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
import nu.mine.egoless.ws.votoclient.ParametriRicercaVoto;
import nu.mine.egoless.ws.votoclient.Voto;
import ws.didattica.stub.Configuration;
import ws.didattica.stub.Storage;

/**
 * Web Service per gestire i voti.
 */
@Stateless()
@WebService()
public class WSVoto {
   
   @PersistenceContext
   private EntityManager em;
   
   /**
    * Determina se i campi del voto hanno valori
    * validi.
    * @param votoAttuale Oggetto {@link Voto} da validare.
    * @throws WSDidatticaException quando si verificano errori di validazione.
    */
   private void validaVoto(Voto votoAttuale) throws WSDidatticaException {
      if (votoAttuale==null) {
         throw new WSDidatticaException("Non e' stato passato alcun voto");
      }
   }
   
   /**
    * Aggiunge un nuovo voto.
    * @param nuovoVoto Voto da aggiungere.
    * @return Id assegnato al voto creato.
    * @throws WSDidatticaException Lanciata quando:
    * <ul>
    *   <li>non e' stato passato nessun voto;
    *   <li>ci sono errori di validazione sui campi.
    * </ul>
    */
   @WebMethod
   public int aggiungiVoto(@WebParam(name = "nuovoVoto") Voto nuovoVoto) throws WSDidatticaException{
      validaVoto(nuovoVoto);
      return Storage.persist(em, Voto.class, nuovoVoto, Storage.VOTO);
   }
   
   /**
    * Modifica i dati di uno studente esistente.
    * @param studente Studente con i dati modificati.
    * @throws WSDidatticaException Lanciata quando:
    * <ul>
    *   <li>non e' stato passato nessun studente;
    *   <li>ci sono errori di validazione sui campi.
    * </ul>
    */
   @WebMethod
   public void modificaVoto(@WebParam(name = "voto") Voto voto) throws WSDidatticaException{
      validaVoto(voto);
      Storage.persistExisting(em, Voto.class, voto, Storage.VOTO, voto.getId());
   }
   
   /**
    * Cancella un voto.
    * @param idVoto Identificativo del voto da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaVoto(@WebParam(name = "idVoto") int idVoto) throws WSDidatticaException{
      Storage.delete(em, idVoto);
   }
   
   /**
    * Permette di ottenere una serie di voti che soddisfano a dei criteri.
    * @param insiemeParametri Parametri di ricerca.
    * @return Insieme di voti che soddisfano i criteri di ricerca.
    * @throws WSDidatticaException Segnala un generico errore del WS.
    */
   @WebMethod
   public Voto[] cercaVoto(@WebParam(name = "insiemeParametri") ParametriRicercaVoto insiemeParametri) throws WSDidatticaException{
      if (insiemeParametri==null) {
         throw new WSDidatticaException("Non sono stati passati i parametri di ricerca.");
      }      else {
         Configuration conf=em.find(Configuration.class, 1L);
         
         if (conf==null) {
            conf=new Configuration();
            conf.setCercaVotoBehavior(1);
         } else {
            em.refresh(conf);
         }
         
         if (conf==null || conf.getCercaVotoBehavior()==0) {
            return null;
         } else {
            List<Voto> result=new ArrayList<Voto>();
            JAXBContext jc;
            JAXBElement<Voto> je;
            
            if (conf.getCercaVotoBehavior()==1) {
               List<Storage> l=Storage.retrieve(em, Storage.VOTO);
               try {
                  jc = JAXBContext.newInstance(Voto.class);
                  
                  Unmarshaller u = jc.createUnmarshaller();
                  for(Storage st : l) {
                     if (insiemeParametri.getId()==0 || st.getId()==insiemeParametri.getId()) {
                        je=u.unmarshal(new StreamSource(new StringReader(st.getContent())), Voto.class);
                        Voto tv =  je.getValue();
                        tv.setId(st.getId());
                        result.add(tv);
                     }
                  }
               } catch (JAXBException ex) {
                  ex.printStackTrace();
               }
            } else {
               //Restituiamo dei dati al volo
               Voto obj=new Voto();
               obj.setId(1);
               obj.setTipoVotoId(7);
               obj.setDocenteId(7);
               obj.setTipoProvaId(9);
               obj.setMateriaId(9);
               result.add(obj);
               
               obj=new Voto();
               obj.setId(2);
               obj.setTipoVotoId(10);
               obj.setDocenteId(6);
               obj.setTipoProvaId(4);
               obj.setTipoProvaId(3);
               result.add(obj);
               
               obj=new Voto();
               obj.setId(3);
               obj.setTipoVotoId(5);
               obj.setDocenteId(2);
               obj.setTipoProvaId(9);
               obj.setTipoProvaId(9);
               result.add(obj);
            }
            
            return result.toArray(new Voto[] {});
         }
      }
   }
}
