/*
 * Nome file: WSTipoAssenza.java
 * Data creazione: March 13, 2007, 1:48 PM
 * Info svn: $Id: WSTipoAssenza.java 727 2007-03-25 18:33:13Z eric $
 */

package nu.mine.egoless.didattica.ws;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;

import nu.mine.egoless.ws.tipoassenzaclient.TipoAssenzaWSService;
import nu.mine.egoless.ws.tipoassenzaclient.TipoAssenzaWS;
import nu.mine.egoless.ws.tipoassenzaclient.TipoAssenza;
import nu.mine.egoless.ws.tipoassenzaclient.OperazioneNonValida_Exception;

/**
 * Web Service per gestire i tipi di assenza.
 */
@Stateless()
@WebService()
public class WSTipoAssenza {
   
   @WebServiceRef(wsdlLocation = "conf/xml-resources/web-service-references/TipoAssenzaWSService/wsdl/TipoAssenzaWSService.wsdl")
   private TipoAssenzaWSService service;
   
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
         try {
            TipoAssenzaWS port = service.getTipoAssenzaWSPort();
            return port.aggiungiTipoAssenza(nuovoTipoAssenza);
         } catch (OperazioneNonValida_Exception ex) {
            //Mascheriamo l'eccezione
            throw new WSDidatticaException(ex.getMessage());
         }
      }
   }
   
   /**
    * Cancella un tipo di assenza.
    * @param idTipoAssenza Identificativo del tipo di assenza da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaTipoAssenza(@WebParam(name = "idTipoAssenza") int idTipoAssenza) throws WSDidatticaException{
      try {
         TipoAssenzaWS port = service.getTipoAssenzaWSPort();
         port.cancellaTipoAssenza(idTipoAssenza);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /**
    * Ottiene tutti i tipi di assenza.
    * @return Insieme di tutti i tipi di assenza.
    * @throws WSDidatticaException Lanciata quando si verificano
    * errori nel Web Service.
    */
   @WebMethod
   public TipoAssenza[] recuperaTipiAssenza() throws WSDidatticaException{
      try {
         TipoAssenzaWS port = service.getTipoAssenzaWSPort();
         List<Integer> result = port.getTipiAssenza();
         List<TipoAssenza> tipiAssenza=new ArrayList<TipoAssenza>();
         
         if (result==null) {
            return null;
         } else {
            //Cicliamo sui risultati e recuperiamo i tipi di Assenza
            for(Integer i: result) {
               TipoAssenza obj=port.caricaTipoAssenza(i);
               tipiAssenza.add(obj);
            }
            
            return tipiAssenza.toArray(new TipoAssenza[] {});
         }
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
}
