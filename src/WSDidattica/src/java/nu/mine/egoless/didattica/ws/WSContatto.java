/*
 * Nome file: WSContatto.java
 * Data creazione: March 13, 2007, 8:54 AM
 * Info svn: $Id: WSContatto.java 727 2007-03-25 18:33:13Z eric $
 */

package nu.mine.egoless.didattica.ws;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;

import nu.mine.egoless.ws.contattoclient.ContattoWSService;
import nu.mine.egoless.ws.contattoclient.ContattoWS;
import nu.mine.egoless.ws.contattoclient.Contatto;
import nu.mine.egoless.ws.contattoclient.ParametriRicercaContatto;
import nu.mine.egoless.ws.contattoclient.OperazioneNonValida_Exception;

/**
 * Web Service per gestire i contatti.
 */
@Stateless()
@WebService()
public class WSContatto {
   
   @WebServiceRef(wsdlLocation = "conf/xml-resources/web-service-references/ContattoWSService/wsdl/ContattoWSService.wsdl")
   private ContattoWSService service;
   
   /**
    * Determina se i campi del contatto hanno valori
    * validi.
    * @param contattoAttuale Oggetto {@link Contatto} da validare.
    * @throws WSDidatticaException quando si verificano errori di validazione.
    */
   private void validaContatto(Contatto contattoAttuale) throws WSDidatticaException {
      if (contattoAttuale==null) {
         throw new WSDidatticaException("Non e' stato passatto alcun contatto.");
      }
   }
   
   /**
    * Aggiunge un nuovo contatto.
    * @param nuovoConatto Contatto da aggiungere.
    * @return Id assegnato al contatto creato.
    * @throws WSDidatticaException Lanciata quando:
    * <ul>
    *   <li>non e' stato passato nessun contatto;
    *   <li>ci sono errori di validazione sui campi.
    * </ul>
    */
   @WebMethod
   public int aggiungiContatto(@WebParam(name = "nuovoContatto") Contatto nuovoContatto) throws WSDidatticaException{
      try {
         ContattoWS port;
         
         validaContatto(nuovoContatto);
         port = service.getContattoWSPort();
         return port.aggiungiContatto(nuovoContatto);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /**
    * Modifica i dati di un contatto esistente.
    * @param contatto Contatto con i dati modificati.
    * @throws WSDidatticaException Lanciata quando:
    * <ul>
    *   <li>non e' stato passato nessun contatto;
    *   <li>ci sono errori di validazione sui campi.
    * </ul>
    */
   @WebMethod
   public void modificaContatto(@WebParam(name = "contatto") Contatto contatto) throws WSDidatticaException{
      try {
         ContattoWS port;
         
         validaContatto(contatto);
         port = service.getContattoWSPort();
         port.modificaContatto(contatto);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /**
    * Cancella un contatto.
    * @param idContatto Identificativo del contatto da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaContatto(@WebParam(name = "idContatto") int idContatto) throws WSDidatticaException {
      try {
         ContattoWS port=service.getContattoWSPort();
         port.cancellaContatto(idContatto);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /**
    * Permette di ottenere una serie di contatti che soddisfano a dei criteri.
    * @param insiemeParametri Parametri di ricerca.
    * @return Insieme di contatti che soddisfano i criteri di ricerca.
    * @throws WSDidatticaException Segnala un generico errore del WS.
    */
   @WebMethod
   public Contatto[] cercaContatto(@WebParam(name = "insiemeParametri") ParametriRicercaContatto insiemeParametri) throws WSDidatticaException {
      if (insiemeParametri==null) {
         throw new WSDidatticaException("Non sono stati passati i parametri di ricerca.");
      }  else {
         try {
            ContattoWS port = service.getContattoWSPort();
            List<Integer> result = port.cercaContatto(insiemeParametri);
            List<Contatto> contatti=new ArrayList<Contatto>();
            
            if (result==null) {
               return null;
            } else {
               //Cicliamo sui risultati e recuperiamo i tipi di Prova
               for(Integer i: result) {
                  Contatto obj=port.caricaContatto(i);
                  contatti.add(obj);
               }
               
               return contatti.toArray(new Contatto[] {});
            }
         } catch (OperazioneNonValida_Exception ex) {
            //Mascheriamo l'eccezione
            throw new WSDidatticaException(ex.getMessage());
         }
      }
   }
   
}
