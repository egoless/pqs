/*
 * Nome file: WSNazione.java
 * Data creazione: March 23, 2007, 6:56 PM
 * Info svn: $Id: WSNazione.java 727 2007-03-25 18:33:13Z eric $
 */

package nu.mine.egoless.didattica.ws;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;

import nu.mine.egoless.ws.nazioneclient.NazioneWSService;
import nu.mine.egoless.ws.nazioneclient.NazioneWS;
import nu.mine.egoless.ws.nazioneclient.Nazione;

/**
 * Web Service che gestisce le nazioni.
 */
@Stateless()
@WebService()
public class WSNazione {
   @WebServiceRef(wsdlLocation = "conf/xml-resources/web-service-references/NazioneWSService/wsdl/NazioneWSService.wsdl")
   private NazioneWSService serviceNazione;
   
   /**
    * Ottiene tutte le nazioni/nazionalita' gestite dal sistema.
    * @return Insieme di tutte le nazioni.
    * @throws WSDidatticaException Lanciata quando si verificano
    * errori nel Web Service.
    */
   @WebMethod
   public Nazione[] recuperaNazioni() throws WSDidatticaException{
      try {
         NazioneWS port = serviceNazione.getNazioneWSPort();
         List<Integer> result = port.getNazioni();
         List<Nazione> nazioni=new ArrayList<Nazione>();
         
         if (result==null) {
            return null;
         } else {
            //Cicliamo sui risultati e recuperiamo le nazioni
            for(Integer i: result) {
               Nazione n=port.caricaNazione(i);
               nazioni.add(n);
            }
            
            return nazioni.toArray(new Nazione[] {});
         }
      } catch (nu.mine.egoless.ws.nazioneclient.OperazioneNonValida_Exception ex) {
         //Mascheriamo l'eccezione
         throw new WSDidatticaException(ex.getMessage());
      }
   }
   

}
