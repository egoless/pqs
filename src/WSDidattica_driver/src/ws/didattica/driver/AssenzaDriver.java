/*
 * AssenzaDriver.java
 *
 * Created on 17 marzo 2007, 23.12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ws.didattica.driver;

import ws.didattica.driver.assenzaclient.WSDidatticaException_Exception;

/**
 *
 * @author Roberto
 */
public class AssenzaDriver {
   
   /** Creates a new instance of AssenzaDriver */
   public AssenzaDriver() {
   }
   
   public long driverAggiungiAssenza(ws.didattica.driver.assenzaclient.Assenza assenza) throws Exception {
      
      long result=-1;
      
      try { // Call Web Service Operation
         ws.didattica.driver.assenzaclient.WSAssenzaService service = new ws.didattica.driver.assenzaclient.WSAssenzaService();
         ws.didattica.driver.assenzaclient.WSAssenza port = service.getWSAssenzaPort();
         // TODO initialize WS operation arguments here
         
         // TODO process result here
         result = port.aggiungiAssenza(assenza);
         System.out.println("Result = "+result);
      } catch (WSDidatticaException_Exception ex) {
//         if (ex.getMessage().equals("Non e' stata passata alcuna assenza")) return -2;
//         if (ex.getMessage().equals("La data di fine assenza precede quella di inizio assenza.")) return -3;
//         ex.printStackTrace();
         result=-2;
      }
      return result;
      
   }
   
   public void driverModificaAssenza(ws.didattica.driver.assenzaclient.Assenza assenza){
      try { // Call Web Service Operation
         ws.didattica.driver.assenzaclient.WSAssenzaService service = new ws.didattica.driver.assenzaclient.WSAssenzaService();
         ws.didattica.driver.assenzaclient.WSAssenza port = service.getWSAssenzaPort();
         // TODO initialize WS operation arguments here
         port.modificaAssenza(assenza);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
   }
   
   public void driverCancellaAssenza(int idAssenza){
      try { // Call Web Service Operation
         ws.didattica.driver.assenzaclient.WSAssenzaService service = new ws.didattica.driver.assenzaclient.WSAssenzaService();
         ws.didattica.driver.assenzaclient.WSAssenza port = service.getWSAssenzaPort();
         // TODO initialize WS operation arguments here
         
         port.cancellaAssenza(idAssenza);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
   }
   
   public java.util.List<ws.didattica.driver.assenzaclient.Assenza> driverCercaAssenza(
           ws.didattica.driver.assenzaclient.ParametriRicercaAssenza insiemeParametri)
   throws Exception {
      
      java.util.List<ws.didattica.driver.assenzaclient.Assenza> result=null;
      
      ws.didattica.driver.assenzaclient.WSAssenzaService service = new ws.didattica.driver.assenzaclient.WSAssenzaService();
      ws.didattica.driver.assenzaclient.WSAssenza port = service.getWSAssenzaPort();
      
      // TODO process result here
      result = port.cercaAssenza(insiemeParametri);
      System.out.println("Result = "+result);
      
      return result;
   }
}
