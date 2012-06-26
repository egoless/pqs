/*
 * Nome file: ReligioneDriver.java
 * Data creazione: 26 marzo 2007, 7.22
 * Info svn: $Id: ReligioneDriver.java 776 2007-03-26 08:21:20Z eric $
 */

package ws.didattica.driver;

import java.util.List;
import ws.didattica.driver.religioneclient.Religione;
import ws.didattica.driver.religioneclient.WSReligione;
import ws.didattica.driver.religioneclient.WSReligioneService;

/**
 * Class description
 */
public class ReligioneDriver {
   
   /** Creates a new instance of ReligioneDriver */
   public ReligioneDriver() {
   }
   
   
    public List<Religione> driverRecuperaReligioni(){
       List<Religione> result=null;
       try { // Call Web Service Operation
           WSReligioneService service = new WSReligioneService();
           WSReligione port = service.getWSReligionePort();
           
           result = port.recuperaReligioni();
           System.out.println("Result = "+result);
           
       } catch (Exception ex) {
           
       }
      return result;
   }
}
