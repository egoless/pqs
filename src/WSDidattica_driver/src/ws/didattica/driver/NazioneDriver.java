/*
 * Nome file: NazioneDriver.java
 * Data creazione: 26 marzo 2007, 7.22
 * Info svn: $Id: NazioneDriver.java 776 2007-03-26 08:21:20Z eric $
 */

package ws.didattica.driver;

import java.util.List;
import ws.didattica.driver.nazioneclient.Nazione;
import ws.didattica.driver.nazioneclient.WSNazione;
import ws.didattica.driver.nazioneclient.WSNazioneService;

/**
 * Class description
 */
public class NazioneDriver {
   
   /** Creates a new instance of NazioneDriver */
   public NazioneDriver() {
   }
   
   public List<Nazione> driverRecuperaNazioni(){
       List<Nazione> result=null;
       try { // Call Web Service Operation
           WSNazioneService service = new WSNazioneService();
           WSNazione port = service.getWSNazionePort();
           
           result = port.recuperaNazioni();
           System.out.println("Result = "+result);
           
       } catch (Exception ex) {
           
       }
      return result;
   }
   
}
