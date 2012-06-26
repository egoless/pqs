/*
 * Nome file: WSAssenza.java
 * Data creazione: March 13, 2007, 9:07 AM
 * Info svn: $Id: WSAssenza.java 820 2007-03-26 18:42:33Z eric $
 */

package nu.mine.egoless.didattica.ws;

import java.util.List;
import java.util.ArrayList;

import nu.mine.egoless.supporto.DateTimeFacade;
import java.util.Calendar;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;

import nu.mine.egoless.ws.assenzaclient.AssenzaWSService;
import nu.mine.egoless.ws.assenzaclient.AssenzaWS;
import nu.mine.egoless.ws.assenzaclient.Assenza;
import nu.mine.egoless.ws.assenzaclient.OperazioneNonValida_Exception;
import nu.mine.egoless.ws.assenzaclient.ParametriRicercaAssenza;

/**
 * Web Service per gestire le assenze.
 */
@Stateless()
@WebService()
public class WSAssenza {
   
   @WebServiceRef(wsdlLocation = "conf/xml-resources/web-service-references/AssenzaWSService/wsdl/AssenzaWSService.wsdl")
   private AssenzaWSService service;
   
   /**
    * Determina se i campi dell'assenza hanno valori
    * validi.
    * @param assenzaAttuale Oggetto {@link Assenza} da validare.
    * @throws WSDidatticaException quando si verificano errori di validazione.
    */
   private void validaAssenza(Assenza assenzaAttuale) throws WSDidatticaException {
      if (assenzaAttuale==null) {
         throw new WSDidatticaException("Non e' stata passata alcuna assenza");
      }
      
      //Controlliamo la validità delle date
      if (assenzaAttuale.getDataOraInizio()!=null &&
              assenzaAttuale.getDataOraFine()!=null) {
         
         //Prima le estrapoliamo
         String stringaDataInizio=assenzaAttuale.getDataOraInizio().getDate();
         String stringaDataFine=assenzaAttuale.getDataOraFine().getDate();
         
         if(stringaDataInizio!=null && stringaDataFine!=null) {
            //Se sono valide le confrontiamo
            Calendar dataInizio=DateTimeFacade.String2GregorianCalendar(stringaDataInizio);
            Calendar dataFine=DateTimeFacade.String2GregorianCalendar(stringaDataFine);
            
            if (dataInizio==null) {
               throw new WSDidatticaException("Data di inizio assenza non valida.");
            }
            
            if (dataFine==null) {
               throw new WSDidatticaException("Data di inizio assenza non valida.");
            }
            
            if (dataFine.compareTo(dataInizio) < 0) {
               throw new WSDidatticaException("La data di fine assenza precede quella di inizio assenza.");
            }
         } else if(stringaDataInizio==null && stringaDataFine!=null) {
            //Se è presente solo quella finale, segnaliamo un errore.
            throw new WSDidatticaException(
                    "E' presente solo la data di fine assenza (dovrebbe essere presente anche quella di inizio");
         }
      }
   }
   
   /**
    * Aggiunge una nuova assenza..
    * @param nuovoAssenza Assenza da aggiungere.
    * @return Id assegnato all'assenza creata.
    * @throws WSDidatticaException Lanciata quando:
    * <ul>
    *   <li>non e' stato passata nessuna assenza;
    *   <li>ci sono errori di validazione sui campi.
    * </ul>
    */
   @WebMethod
   public int aggiungiAssenza(@WebParam(name = "nuovaAssenza") Assenza nuovaAssenza) throws WSDidatticaException{
      try {
         AssenzaWS port;
         
         validaAssenza(nuovaAssenza);
         port = service.getAssenzaWSPort();
         return port.aggiungiAssenza(nuovaAssenza);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /**
    * Modifica un'assenza esistente.
    * @param assenza Assenza con i dati modificati.
    * @return Id assegnato all'assenza creata.:
    * <ul>
    *   <li>non e' stato passato nessuna assenza;
    *   <li>ci sono errori di validazione sui campi.
    * </ul>
    */
   @WebMethod
   public void modificaAssenza(@WebParam(name = "assenza") Assenza assenza) throws WSDidatticaException{
      try {
         AssenzaWS port;
         
         validaAssenza(assenza);
         port = service.getAssenzaWSPort();
         port.modificaAssenza(assenza);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /**
    * Cancella un'assenza.
    * @param idAssenza Identificativo dell'assenza da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaAssenza(@WebParam(name = "idAssenza") int idAssenza) throws WSDidatticaException{
      try {
         AssenzaWS port= service.getAssenzaWSPort();
         port.cancellaAssenza(idAssenza);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /**
    * Permette di ottenere una serie di assenze che soddisfano a dei criteri.
    * @param insiemeParametri Parametri di ricerca.
    * @return Insieme di assenze che soddisfano i criteri di ricerca.
    * @throws WSDidatticaException Segnala un generico errore del WS.
    */
   @WebMethod
   public Assenza[] cercaAssenza(@WebParam(name = "insiemeParametri") ParametriRicercaAssenza insiemeParametri) throws WSDidatticaException{
      if (insiemeParametri==null) {
         throw new WSDidatticaException("Non sono stati passati i parametri di ricerca.");
      } else {
         try {
            AssenzaWS port = service.getAssenzaWSPort();
            List<Integer> result = port.cercaAssenza(insiemeParametri);
            List<Assenza> voti=new ArrayList<Assenza>();
            
            if (result==null) {
               return null;
            } else {
               //Cicliamo sui risultati e recuperiamo i tipi di assenza
               for(Integer i: result) {
                  Assenza obj=port.caricaAssenza(i);
                  voti.add(obj);
               }
               
               return voti.toArray(new Assenza[] {});
            }
         } catch (OperazioneNonValida_Exception ex) {
            //Mascheriamo l'eccezione
            throw new WSDidatticaException(ex.getMessage());
         }
      }
   }
}
