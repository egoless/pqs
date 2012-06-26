/*
 * Nome file: WSPersona.java
 * Data creazione: March 12, 2007, 8:49 PM
 * Info svn: $Id: WSPersona.java 797 2007-03-26 13:42:39Z eric $
 */

package nu.mine.egoless.didattica.ws;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
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
import nu.mine.egoless.ws.personaclient.Docente;
import nu.mine.egoless.ws.personaclient.ParametriRicercaDocente;
import nu.mine.egoless.ws.personaclient.ParametriRicercaStudente;
import nu.mine.egoless.ws.personaclient.Studente;
import nu.mine.egoless.supporto.DateTimeFacade;
import ws.didattica.stub.Configuration;
import ws.didattica.stub.Storage;

/**
 * Web Service per gestire le persone in generale
 */
@Stateless()
@WebService()
public class WSPersona {
   
   @PersistenceContext
   private EntityManager em;
   
   /**
    * Determina se i campi dello studente hanno valori
    * validi.
    * @param studenteAttuale Oggetto {@link Studente} da validare.
    * @throws WSDidatticaException quando si verificano errori di validazione.
    */
   private void validaStudente(Studente studenteAttuale) throws WSDidatticaException {
      if (studenteAttuale==null) {
         throw new WSDidatticaException("Non e' stato passato nessun studente");
      }
   }
   
   /**
    * Determina se i dati dell'insegnante sono validi.
    * @param insegnanteAttuale Insegnante da validare.
    * @throws WSDidatticaException quando si verificano errori di validazione.
    */
   private void validaInsegnante(Docente insegnanteAttuale) throws WSDidatticaException {
      if (insegnanteAttuale==null) {
         throw new WSDidatticaException("Non e' stato passato alcun insegnante.");
      }
   }
   
   /**
    * Aggiunge un nuovo studente.
    * @param nuovoStudente Studente da aggiungere.
    * @return Id assegnato allo studente creato.
    * @throws WSDidatticaException Lanciata quando:
    * <ul>
    *   <li>non e' stato passato nessun studente;
    *   <li>ci sono errori di validazione sui campi.
    * </ul>
    */
   @WebMethod
   public int aggiungiStudente(@WebParam(name = "nuovoStudente") Studente nuovoStudente) throws WSDidatticaException{
      validaStudente(nuovoStudente);
      return Storage.persist(em, Studente.class, nuovoStudente, Storage.STUDENTE);
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
   public void modificaStudente(@WebParam(name = "studente") Studente studente) throws WSDidatticaException{
      validaStudente(studente);
      Storage.persistExisting(em, Studente.class, studente, Storage.STUDENTE, studente.getId());
   }
   
   /**
    * Cancella uno studente.
    * @param idStudente Identificativo dello studente da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaStudente(@WebParam(name = "idStudente") int idStudente) throws WSDidatticaException{
      Storage.delete(em, idStudente);
   }
   
   /**
    * Permette di ottenere una serie di studenti che soddisfano a dei criteri.
    * @param insiemeParametri Parametri di ricerca.
    * @return Insieme di studenti che soddisfano i criteri di ricerca.
    * @throws WSDidatticaException Segnala un generico errore del WS.
    */
   @WebMethod
   public Studente[] cercaStudente(@WebParam(name = "insiemeParametri") ParametriRicercaStudente insiemeParametri) throws WSDidatticaException{
      if (insiemeParametri==null) {
         throw new WSDidatticaException("Non sono stati passati i parametri di ricerca.");
      }      else {
         Configuration conf=em.find(Configuration.class, 1L);
         
         if (conf==null) {
            conf=new Configuration();
            conf.setCercaStudenteBehavior(1);
         } else {
             em.refresh(conf);
         }
         
         if (conf==null || conf.getCercaStudenteBehavior()==0) {
            return null;
         } else {
            List<Studente> result=new ArrayList<Studente>();
            JAXBContext jc;
            JAXBElement<Studente> je;
            
            if (conf.getCercaStudenteBehavior()==1) {
               List<Storage> l=Storage.retrieve(em, Storage.STUDENTE);
               try {
                  jc = JAXBContext.newInstance(Studente.class);
                  
                  Unmarshaller u = jc.createUnmarshaller();
                  for(Storage st : l) {
                     if (insiemeParametri.getId()==0 || st.getId()==insiemeParametri.getId()) {
                        je=u.unmarshal(new StreamSource(new StringReader(st.getContent())), Studente.class);
                        Studente tv =  je.getValue();
                        tv.setId(st.getId());
                        result.add(tv);
                     }
                  }
               } catch (JAXBException ex) {
                  ex.printStackTrace();
               }
            } else {
               //Restituiamo dei dati al volo
               Studente obj=new Studente();
               obj.setId(1);
//               obj.setNome("Eric");
//               obj.setCognome("Miotto");
               obj.setMatricola("521896/A");
               //obj.setDataIscrizione(DateTimeFacade.Date2String(Calendar.getInstance().getTime()));
               result.add(obj);
               
               obj=new Studente();
               obj.setId(2);
//               obj.setNome("Carlo");
//               obj.setCognome("Miotto");
               obj.setMatricola("545896/A");
               //obj.setDataIscrizione(DateTimeFacade.Date2String(Calendar.getInstance().getTime()));
               result.add(obj);
               
               obj=new Studente();
               obj.setId(3);
//               obj.setNome("Antonello");
//               obj.setCognome("Miotto");
               obj.setMatricola("345896/A");
               //obj.setDataIscrizione(DateTimeFacade.Date2String(Calendar.getInstance().getTime()));
               result.add(obj);
            }
            
            return result.toArray(new Studente[] {});
         }
      }
   }
   
   
   
   
   /**
    * Aggiunge un nuovo insegnante.
    * @param nuovoInsegnante Insegnante da aggiungere.
    * @return Id assegnato all'insegnante creato.
    * @throws WSDidatticaException Lanciata quando:
    * <ul>
    *   <li>non e' stato passato nessun studente;
    *   <li>ci sono errori di validazione sui campi.
    * </ul>
    */
   @WebMethod
   public int aggiungiDocente(@WebParam(name = "nuovoDocente") Docente nuovoDocente) throws WSDidatticaException {
      validaInsegnante(nuovoDocente);
      return Storage.persist(em, Docente.class, nuovoDocente, Storage.INSEGNANTE);
      
   }
   
   /**
    * Modifica i dati di un insegnante esistente.
    * @param insegnante Insegnante con i dati modificati.
    * @throws WSDidatticaException Lanciata quando:
    * <ul>
    *   <li>non e' stato passato nessun insegnante;
    *   <li>ci sono errori di validazione sui campi.
    * </ul>
    */
   @WebMethod
   public void modificaDocente(@WebParam(name = "docente") Docente docente) throws WSDidatticaException{
      validaInsegnante(docente);
      Storage.persistExisting(em, Docente.class, docente, Storage.INSEGNANTE, docente.getId());
   }
   
   /**
    * Cancella un insegnante.
    * @param idInsegnante Identificativo dell'insegnante da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaDocente(@WebParam(name = "idDocente") int idDocente) throws WSDidatticaException{
      Storage.delete(em, idDocente);
   }
   
   /**
    * Permette di ottenere una serie di insegnanti che soddisfano a dei criteri.
    * @param insiemeParametri Parametri di ricerca.
    * @return Insieme di insegnanti che soddisfano i criteri di ricerca.
    * @throws WSDidatticaException Segnala un generico errore del WS.
    */
   @WebMethod
   public Docente[] cercaDocente(@WebParam(name = "insiemeParametri") ParametriRicercaDocente insiemeParametri) throws WSDidatticaException{
      if (insiemeParametri==null) {
         throw new WSDidatticaException("Non sono stati passati i parametri di ricerca.");
      }      else {
         Configuration conf=em.find(Configuration.class, 1L);
         
         if (conf==null) {
            conf=new Configuration();
            conf.setCercaInsegnanteBehavior(1);
         } else {
             em.refresh(conf);
         }
         
         if (conf==null || conf.getCercaInsegnanteBehavior()==0) {
            return null;
         } else {
            List<Docente> result=new ArrayList<Docente>();
            JAXBContext jc;
            JAXBElement<Docente> je;
            
            if (conf.getCercaInsegnanteBehavior()==1) {
               List<Storage> l=Storage.retrieve(em, Storage.INSEGNANTE);
               try {
                  jc = JAXBContext.newInstance(Docente.class);
                  
                  Unmarshaller u = jc.createUnmarshaller();
                  for(Storage st : l) {
                     if (insiemeParametri.getId()==0 || st.getId()==insiemeParametri.getId()) {
                        je=u.unmarshal(new StreamSource(new StringReader(st.getContent())), Docente.class);
                        Docente tv =  je.getValue();
                        tv.setId(st.getId());
                        result.add(tv);
                     }
                  }
               } catch (JAXBException ex) {
                  ex.printStackTrace();
               }
            } else {
               //Restituiamo dei dati al volo
               Docente obj=new Docente();
               obj.setId(1);
               obj.setNome("Eric");
               //obj.setCognome("Cena");
               //obj.setIdMateria(7);
               //obj.setDataAssunzione(DateTimeFacade.Date2String(Calendar.getInstance().getTime()));
               result.add(obj);
               
               obj=new Docente();
               obj.setId(2);
               //obj.setNome("Carlo");
               //obj.setCognome("Cena");
               //obj.getIdMaterie().add(5L);
               //obj.setDataAssunzione(DateTimeFacade.Date2String(Calendar.getInstance().getTime()));
               result.add(obj);
               
               obj=new Docente();
               obj.setId(3);
               //obj.setNome("Antonello");
               //obj.setCognome("Cena");
               //obj.setIdMateria(1);
               //obj.setDataAssunzione(DateTimeFacade.Date2String(Calendar.getInstance().getTime()));
               result.add(obj);
            }
            
            return result.toArray(new Docente[] {});
         }
      }
   }
   
   /*
    * Restituisce gli id delle materie insegnate da un dato docente.
    * @param idDocente id del docente di cui vogliamo sapere le materie che insegna.
    * @return Insieme di id delle materie insegnate dal docente.
    * @throws WSDidatticaException Segnala un generico errore del WS.
    */
   @WebMethod
   public List<Integer> getMaterie(@WebParam(name = "idDocente") int idDocente) throws WSDidatticaException {
      //TODO come implementarlo?
      return null;
   }
   
    /*
     * Imposta gli id delle materie insegnate da un dato docente.
     * @param idDocente id del docente di cui vogliamo sapere le materie che insegna.
     * @param idMaterie Insieme di id delle materie insegnate dal docente.
     * @throws WSDidatticaException Segnala un generico errore del WS.
     */
   @WebMethod
   public void setMaterie(@WebParam(name = "idDocente") int idDocente, @WebParam(name = "idMaterie") int[] idMaterie) throws WSDidatticaException {
      //TODO come implementarlo?
   }
   
   /*
    * Recupera l'id della classe che lo studente sta frequentando attualmente.
    * @param idStudente Id dello studente.
    * @return Id della classe che frequenta.
    * @throws WSDidatticaException Segnala un generico errore del WS.
    */
   @WebMethod
   public int getClasse(@WebParam(name = "idStudente") int idStudente) throws WSDidatticaException {
      //TODO come implementarlo?
      return 0;
   }
   
    /*
     * Imposta per uno dato studente la classe che sta frequentando attualmente.
     * @param idStudente Id dello studente.
     * @param idClasse Id della classe che frequenta.
     * @throws WSDidatticaException Segnala un generico errore del WS.
     */
   @WebMethod
   public void setClasse(@WebParam(name = "idStudente") int idStudente, @WebParam(name = "idClasse") int idClasse) throws WSDidatticaException {
      //TODO come implementarlo?
   }
}
