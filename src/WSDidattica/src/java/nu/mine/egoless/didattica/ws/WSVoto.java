/*
 * Nome file: WSVoto.java
 * Data creazione: March 13, 2007, 9:32 AM
 * Info svn: $Id: WSVoto.java 820 2007-03-26 18:42:33Z eric $
 */

package nu.mine.egoless.didattica.ws;

import java.util.List;
import java.util.ArrayList;

import java.util.Calendar;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;

import nu.mine.egoless.ws.votoclient.VotoWSService;
import nu.mine.egoless.ws.votoclient.VotoWS;
import nu.mine.egoless.ws.votoclient.Voto;
import nu.mine.egoless.ws.votoclient.OperazioneNonValida_Exception;
import nu.mine.egoless.ws.votoclient.ParametriRicercaVoto;

import nu.mine.egoless.supporto.DateTimeFacade;

/**
 * Web Service per gestire i voti.
 */
@Stateless()
@WebService()
public class WSVoto {
   
   @WebServiceRef(wsdlLocation = "conf/xml-resources/web-service-references/VotoWSService/wsdl/VotoWSService.wsdl")
   private VotoWSService service;
   
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
      
      if (votoAttuale.getData()!=null) {
         String stringaData=votoAttuale.getData().getDate();
         
         if (stringaData!=null) {
            Calendar adesso=Calendar.getInstance();
            
            Calendar dataOra=DateTimeFacade.String2GregorianCalendar(stringaData);
            
            if (dataOra==null) {
              throw new WSDidatticaException("Il momento in cui il voto e' stato assegnato non e' valido."); 
            }
            
            if (adesso.compareTo(dataOra) <0) {
               throw new WSDidatticaException("Il momento in cui il voto e' stato assegnato e' successivo all'istante attuale.");
            }
         }
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
      try {
         VotoWS port;
         
         validaVoto(nuovoVoto);
         port = service.getVotoWSPort();
         return port.aggiungiVoto(nuovoVoto);
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
   public void modificaVoto(@WebParam(name = "voto") Voto voto) throws WSDidatticaException{
      try {
         VotoWS port;
         
         validaVoto(voto);
         port = service.getVotoWSPort();
         port.modificaVoto(voto);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /**
    * Cancella un voto.
    * @param idVoto Identificativo del voto da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaVoto(@WebParam(name = "idVoto") int idVoto) throws WSDidatticaException{
      try {
         VotoWS port = service.getVotoWSPort();
         port.cancellaVoto(idVoto);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
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
      } else {
         try {
            VotoWS port = service.getVotoWSPort();
            List<Integer> result = port.cercaVoto(insiemeParametri);
            List<Voto> voti=new ArrayList<Voto>();
            
            if (result==null) {
               return null;
            } else {
               //Cicliamo sui risultati e recuperiamo i tipi di Prova
               for(Integer i: result) {
                  Voto obj=port.caricaVoto(i);
                  voti.add(obj);
               }
               
               return voti.toArray(new Voto[] {});
            }
         } catch (OperazioneNonValida_Exception ex) {
            //Mascheriamo l'eccezione
            throw new WSDidatticaException(ex.getMessage());
         }
      }
      
      
   }
   
}
