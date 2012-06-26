/*
 * Nome file: WSTipoVoto.java
 * Data creazione: March 13, 2007, 2:26 PM
 * Info svn: $Id: WSTipoVoto.java 727 2007-03-25 18:33:13Z eric $
 */
package nu.mine.egoless.didattica.ws;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;

import nu.mine.egoless.ws.tipovotoclient.TipoVotoWSService;
import nu.mine.egoless.ws.tipovotoclient.TipoVoto;
import nu.mine.egoless.ws.tipovotoclient.TipoVotoWS;
import nu.mine.egoless.ws.tipovotoclient.OperazioneNonValida_Exception;

/**
 * Web Service per la gestione dei tipi di voto
 */
@Stateless()
@WebService()
public class WSTipoVoto {
   
   @WebServiceRef(wsdlLocation = "conf/xml-resources/web-service-references/TipoVotoWSService/wsdl/TipoVotoWSService.wsdl")
   private TipoVotoWSService service;
   
   
   /**
    * Aggiunge un nuovo tipo di voto.
    * @param nuovoTipoVoto Tipo di voto da aggiungere.
    * @return Id assegnato al tipo di voto creato.
    * @throws WSDidatticaException Lanciata quando non e' stato passato
    *                              alcun tipo.
    */
   @WebMethod
   public int aggiungiTipoVoto(@WebParam(name = "nuovoTipoVoto") TipoVoto nuovoTipoVoto) throws WSDidatticaException{
      if (nuovoTipoVoto==null) {
         throw new WSDidatticaException("Non e' stato passato alcun tipo di voto.");
      } else {
         try {
            TipoVotoWS port= service.getTipoVotoWSPort();
            return port.aggiungiTipoVoto(nuovoTipoVoto);
         } catch (OperazioneNonValida_Exception ex) {
            //Mascheriamo l'eccezione
            throw new WSDidatticaException(ex.getMessage());
         }
      }
   }
   
   /**
    * Cancella un tipo di voto.
    * @param idTipoVoto Identificativo del tipo di voto da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaTipoVoto(@WebParam(name = "idTipoVoto") int idTipoVoto) throws WSDidatticaException{
      try {
         TipoVotoWS port= service.getTipoVotoWSPort();
         port.cancellaTipoVoto(idTipoVoto);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /**
    * Ottiene tutte i tipi di voto.
    * @return Insieme di tutti i tipi di voto.
    * @throws WSDidatticaException Lanciata quando si verificano
    * errori nel Web Service.
    */
   @WebMethod
   public TipoVoto[] recuperaTipiVoto() throws WSDidatticaException{
      try {
         TipoVotoWS port = service.getTipoVotoWSPort();
         List<Integer> result = port.getTipivoto();
         List<TipoVoto> tipiVoto=new ArrayList<TipoVoto>();
         
         if (result==null) {
            return null;
         } else {
            //Cicliamo sui risultati e recuperiamo i tipi di Voto
            for(Integer i: result) {
               TipoVoto obj=port.caricaTipoVoto(i);
               tipiVoto.add(obj);
            }
            
            return tipiVoto.toArray(new TipoVoto[] {});
         }
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
}
