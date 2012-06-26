/*
 * Nome file: WSPersona.java
 * Data creazione: March 12, 2007, 8:49 PM
 * Info svn: $Id: WSPersona.java 912 2007-04-13 14:19:19Z stefano $
 */

package nu.mine.egoless.didattica.ws;

import java.util.List;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;

import nu.mine.egoless.ws.personaclient.Docente;
import nu.mine.egoless.ws.personaclient.ParametriRicercaDocente;
import nu.mine.egoless.ws.personaclient.PersonaWS;
import nu.mine.egoless.ws.personaclient.PersonaWSService;
import nu.mine.egoless.ws.personaclient.ParametriRicercaStudente;
import nu.mine.egoless.ws.personaclient.Studente;
import nu.mine.egoless.ws.personaclient.Persona;
import nu.mine.egoless.ws.personaclient.OperazioneNonValida_Exception;

import nu.mine.egoless.supporto.DateTimeFacade;

/**
 * Web Service per gestire le persone in generale
 */
@Stateless()
@WebService()
public class WSPersona {

   private static final int ANNO_SCOLASTICO_DUMMY=4;
   
   @WebServiceRef(wsdlLocation = "conf/xml-resources/web-service-references/PersonaWSService/wsdl/PersonaWSService.wsdl")
   private nu.mine.egoless.ws.personaclient.PersonaWSService service;
   
   
   /** Stringa con il pattern per il codice fiscale **/
   private static final String PATTERN_CODICE_FISCALE ="[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z0-9]{5}";
   
   /**
    * Determina se i dati di una persona sono validi.
    * @param personaAttuale Persona da validare.
    * @throws WSDidatticaException se ci sono errori di validazione.
    */
   public static void validaPersona(Persona personaAttuale) throws WSDidatticaException {
      if (personaAttuale==null) {
         throw new WSDidatticaException("Non e' stata passata alcuna persona");
      }
      
      if (personaAttuale.getCodiceFiscale()!=null) {
         //Controllo codice fiscale
         Pattern patternCodiceFiscale=Pattern.compile(PATTERN_CODICE_FISCALE);
         Matcher motore=patternCodiceFiscale.matcher(personaAttuale.getCodiceFiscale());
         
         if (!motore.matches()) {
            throw new WSDidatticaException("Il codice fiscale indicato non e' valido.");
         }
      }
      
      if (personaAttuale.getDataNascita()!=null) {
         String stringaDataNascita=personaAttuale.getDataNascita().getDate();
         
         if (stringaDataNascita !=null) {
            //controllo data di nascita
            Calendar adesso=Calendar.getInstance();
            
            Calendar dataNascita=DateTimeFacade.String2GregorianCalendar(stringaDataNascita);
            
            if (dataNascita==null) {
               throw new WSDidatticaException("La data di nascita specificato non e' valida.");
            }
            
            if (adesso.compareTo(dataNascita)<0) {
               throw new WSDidatticaException("La data di nascita e' successiva al giorno attuale.");
            }
         }
      }
   }
   
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
      
      //Eseguiamo i controlli per la persona
      validaPersona(studenteAttuale);
      
      if (studenteAttuale.getDataIscrizione()!=null) {
         String stringaDataIscrizione=studenteAttuale.getDataIscrizione().getDate();
         
         if (stringaDataIscrizione!= null) {
            //controllo data iscrizione
            Calendar adesso=Calendar.getInstance();
            
            Calendar dataIscrizione=DateTimeFacade.String2GregorianCalendar(stringaDataIscrizione);
            
            if (dataIscrizione==null) {
               throw new WSDidatticaException("La data di iscrizione non e' valida.");
            }
            
            if (adesso.compareTo(dataIscrizione)<0) {
               throw new WSDidatticaException("La data di iscrizione e' successiva al giorno attuale.");
            }
         }
      }
      
   }
   
   /**
    * Determina se i dati dell'insegnante sono validi.
    * @param docenteAttuale Insegnante da validare.
    * @throws WSDidatticaException quando si verificano errori di validazione.
    */
   private void validaDocente(Docente docenteAttuale) throws WSDidatticaException {
      if (docenteAttuale==null) {
         throw new WSDidatticaException("Non e' stato passato alcun insegnante.");
      }
      
      //Eseguiamo i controlli per la persona
      validaPersona(docenteAttuale);
      
      if (docenteAttuale.getDataAssunzione()!=null) {
         String stringaDataAssunzione=docenteAttuale.getDataAssunzione().getDate();
         
         if (stringaDataAssunzione!=null) {
            //controllo data iscrizione
            Calendar adesso=Calendar.getInstance();
            
            Calendar dataAssunzione=DateTimeFacade.String2GregorianCalendar(stringaDataAssunzione);
            
            if (dataAssunzione==null) {
               throw new WSDidatticaException("La data di assunzione non e' valida.");
            }
            
            if (adesso.compareTo(dataAssunzione)<0) {
               throw new WSDidatticaException("La data di assunzione e' successiva al giorno attuale.");
            }
         }
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
      try {
         PersonaWS port;
         
         validaStudente(nuovoStudente);
         port = service.getPersonaWSPort();
         return port.aggiungiStudente(nuovoStudente);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
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
      try {
         PersonaWS port;
         
         validaStudente(studente);
         port = service.getPersonaWSPort();
         port.modificaStudente(studente);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /**
    * Cancella uno studente.
    * @param idStudente Identificativo dello studente da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaStudente(@WebParam(name = "idStudente") int idStudente) throws WSDidatticaException{
      try {
         PersonaWS port = service.getPersonaWSPort();
         port.cancellaStudente(idStudente);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
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
      } else {
         try {
            PersonaWS port = service.getPersonaWSPort();
            List<Integer> result = port.cercaStudente(insiemeParametri);
            List<Studente> studenti=new ArrayList<Studente>();
            
            if (result==null) {
               return null;
            } else {
               //Cicliamo sui risultati e recuperiamo i tipi di Prova
               for(Integer i: result) {
                  Studente obj=port.caricaStudente(i);
                  studenti.add(obj);
               }
               
               return studenti.toArray(new Studente[] {});
            }
         } catch (OperazioneNonValida_Exception ex) {
            //Mascheriamo l'eccezione
            throw new WSDidatticaException(ex.getMessage());
         }
      }
   }
   
   /**
    * Aggiunge un nuovo insegnante.
    *
    * @param nuovoDocente Insegnante da aggiungere.
    * @return Id assegnato all'insegnante creato.
    * @throws WSDidatticaException Lanciata quando:
    * <ul>
    *   <li>non e' stato passato nessun studente;
    *   <li>ci sono errori di validazione sui campi.
    * </ul>
    */
   @WebMethod
   public int aggiungiDocente(@WebParam(name = "nuovoDocente") Docente nuovoDocente) throws WSDidatticaException {
      try {
         PersonaWS port;
         
         validaDocente(nuovoDocente);
         port=service.getPersonaWSPort();
         return port.aggiungiDocente(nuovoDocente);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /**
    * Modifica i dati di un docente esistente.
    *
    * @param docente Insegnante con i dati modificati.
    * @throws WSDidatticaException Lanciata quando:
    * <ul>
    *   <li>non e' stato passato nessun docente;
    *   <li>ci sono errori di validazione sui campi.
    * </ul>
    */
   @WebMethod
   public void modificaDocente(@WebParam(name = "docente") Docente docente) throws WSDidatticaException{
      try {
         PersonaWS port;
         
         validaDocente(docente);
         port=service.getPersonaWSPort();
         port.modificaDocente(docente);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /**
    * Cancella un insegnante.
    *
    * @param idDocente Identificativo dell'insegnante da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaDocente(@WebParam(name = "idDocente") int idDocente) throws WSDidatticaException{
      try {
         PersonaWS port = service.getPersonaWSPort();
         
         port.cancellaDocente(idDocente);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
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
      }  else {
         try {
            PersonaWS port = service.getPersonaWSPort();
            List<Integer> result = port.cercaDocente(insiemeParametri);
            List<Docente> docenti=new ArrayList<Docente>();
            
            if (result==null) {
               return null;
            } else {
               //Cicliamo sui risultati e recuperiamo i tipi di Prova
               for(Integer i: result) {
                  Docente obj=port.caricaDocente(i);
                  docenti.add(obj);
               }
               
               return docenti.toArray(new Docente[] {});
            }
         } catch (OperazioneNonValida_Exception ex) {
            //Mascheriamo l'eccezione
            throw new WSDidatticaException(ex.getMessage());
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
      try {
         PersonaWS port = service.getPersonaWSPort();
         return port.getMaterieDocente(idDocente);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
    /*
     * Imposta gli id delle materie insegnate da un dato docente.
     * @param idDocente id del docente di cui vogliamo sapere le materie che insegna.
     * @param idMaterie Insieme di id delle materie insegnate dal docente.
     * @throws WSDidatticaException Segnala un generico errore del WS.
     */
   @WebMethod
   public void setMaterie(@WebParam(name = "idDocente") int idDocente, @WebParam(name = "idMaterie") int[] idMaterie) throws WSDidatticaException {
      try {
         List<Integer> listMaterie=new ArrayList<Integer>();
         
         for(int id: idMaterie) {
            listMaterie.add(id);
         }
         
         PersonaWS port = service.getPersonaWSPort();
         port.setMaterie(idDocente, listMaterie);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /*
    * Recupera l'id della classe che lo studente sta frequentando attualmente.
    * @param idStudente Id dello studente.
    * @return Id della classe che frequenta.
    * @throws WSDidatticaException Segnala un generico errore del WS.
    */
   @WebMethod
   public int getClasse(@WebParam(name = "idStudente") int idStudente) throws WSDidatticaException {
      try {
         PersonaWS port = service.getPersonaWSPort();
         return port.getClasse(idStudente, ANNO_SCOLASTICO_DUMMY);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
    /*
     * Imposta per uno dato studente la classe che sta frequentando attualmente.
     * @param idStudente Id dello studente.
     * @param idClasse Id della classe che frequenta.
     * @throws WSDidatticaException Segnala un generico errore del WS.
     */
   @WebMethod
   public void setClasse(@WebParam(name = "idStudente") int idStudente, @WebParam(name = "idClasse") int idClasse) throws WSDidatticaException {
      try {
         PersonaWS port = service.getPersonaWSPort();
         port.addclasse(idStudente, idClasse);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
}
