/*
 * Nome file: WSReligione.java
 * Data creazione: March 23, 2007, 6:52 PM
 * Info svn: $Id: WSReligione.java 727 2007-03-25 18:33:13Z eric $
 */

package nu.mine.egoless.didattica.ws;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;

import nu.mine.egoless.ws.religioneclient.ReligioneWSService;
import nu.mine.egoless.ws.religioneclient.ReligioneWS;
import nu.mine.egoless.ws.religioneclient.Religione;

/**
 * Web Service per la gestione delle religioni.
 */
@Stateless()
@WebService()
public class WSReligione {
   
   @WebServiceRef(wsdlLocation = "conf/xml-resources/web-service-references/ReligioneWSService/wsdl/ReligioneWSService.wsdl")
   private ReligioneWSService serviceReligione;
   
   /**
    * Ottiene tutte le religioni gestite dal sistema.
    * @return Insieme di tutte le religioni.
    * @throws WSDidatticaException Lanciata quando si verificano
    * errori nel Web Service.
    */
   @WebMethod
   public Religione[] recuperaReligioni() throws WSDidatticaException{
      try {
         ReligioneWS port = serviceReligione.getReligioneWSPort();
         List<Integer> result = port.getReligioni();
         List<Religione> religioni=new ArrayList<Religione>();
         
         if (result==null) {
            return null;
         } else {
            //Cicliamo sui risultati e recuperiamo le religioni
            for(Integer i: result) {
               Religione obj=port.caricaReligione(i);
               religioni.add(obj);
            }
            
            return religioni.toArray(new Religione[] {});
         }
      } catch (nu.mine.egoless.ws.religioneclient.OperazioneNonValida_Exception ex) {
         throw new WSDidatticaException(ex.getMessage());
      }
   }
}
