/*
 * Nome file: WSTipoProva.java
 * Data creazione: 24 marzo 2007, 14.49
 * Info svn: $Id: WSTipoProva.java 927 2007-04-22 10:18:31Z eric $
 */

package nu.mine.egoless.didattica.ws;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;

import nu.mine.egoless.ws.tipoprovaclient.TipoProvaWSService;
import nu.mine.egoless.ws.tipoprovaclient.TipoProvaWS;
import nu.mine.egoless.ws.tipoprovaclient.TipoProva;
import nu.mine.egoless.ws.tipoprovaclient.OperazioneNonValida_Exception;

/**
 * Web service per gestire i tipi di prova (orale, scritto, fine anno, ...).
 */
@Stateless()
@WebService()
public class WSTipoProva {

   @WebServiceRef(wsdlLocation = "conf/xml-resources/web-service-references/TipoProvaWSService/wsdl/TipoProvaWSService.wsdl")
   private nu.mine.egoless.ws.tipoprovaclient.TipoProvaWSService service;
   
    /**
    * Aggiunge un nuovo tipo di prova.
    * @param nuovoTipoAssenza Tipo di assenza da aggiungere.
    * @return Id assegnato al tipo di assenza creato.
    * @throws WSDidatticaException Lanciata quando non e' stato passato
    *                              alcun tipo.
    */
   @WebMethod
   public int aggiungiTipoProva(@WebParam(name = "nuovoTipoProva") TipoProva nuovoTipoProva) throws WSDidatticaException{
      if (nuovoTipoProva==null) {
         throw new WSDidatticaException("Non e' stato passato alcun tipo di assenza.");
      } else {
         try {
            TipoProvaWS port = service.getTipoProvaWSPort();
            return port.aggiungiTipoProva(nuovoTipoProva);
         } catch (OperazioneNonValida_Exception ex) {
            //Mascheriamo l'eccezione
            throw new WSDidatticaException(ex.getMessage());
         }
      }
   }
   
   /**
    * Cancella un tipo di prova.
    * @param idTipoProva Identificativo del tipo di prova da rimuovere.
    * @throws WSDidatticaException Lanciata quando si verificano errori nel Web Service.
    */
   @WebMethod
   public void cancellaTipoProva(@WebParam(name = "idTipoProva") int idTipoProva) throws WSDidatticaException{
      try {
         TipoProvaWS port = service.getTipoProvaWSPort();
         port.cancellaTipoProva(idTipoProva);
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   
   /**
    * Ottiene tutti i tipi di prova.
    * @return Insieme di tutti i tipi di prova.
    * @throws WSDidatticaException Lanciata quando si verificano
    * errori nel Web Service.
    */
   @WebMethod
   public TipoProva[] recuperaTipiProva() throws WSDidatticaException{
      try {
         TipoProvaWS port = service.getTipoProvaWSPort();
         List<Integer> result = port.getTipiProva();
         List<TipoProva> tipiProva=new ArrayList<TipoProva>();
         
         if (result==null) {
            return null;
         } else {
            //Cicliamo sui risultati e recuperiamo i tipi di Prova
            for(Integer i: result) {
               TipoProva obj=port.caricaTipoProva(i);
               tipiProva.add(obj);
            }
            
            return tipiProva.toArray(new TipoProva[] {});
         }
      } catch (OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
}
