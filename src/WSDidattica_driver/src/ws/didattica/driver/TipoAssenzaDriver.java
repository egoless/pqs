/*
 * TipoAssenzaDriver.java
 *
 * Created on 19 marzo 2007, 10.28
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ws.didattica.driver;

/**
 *
 * @author Roberto
 */
public class TipoAssenzaDriver {
   
   /** Creates a new instance of TipoAssenzaDriver */
   public TipoAssenzaDriver() {
   }
   public long driverAggiungiTipoAssenza(ws.didattica.driver.tipoassenzaclient.TipoAssenza nuovoTipoAssenza){
      
      long result=-1;
      
      try { // Call Web Service Operation
         ws.didattica.driver.tipoassenzaclient.WSTipoAssenzaService service = new ws.didattica.driver.tipoassenzaclient.WSTipoAssenzaService();
         ws.didattica.driver.tipoassenzaclient.WSTipoAssenza port = service.getWSTipoAssenzaPort();
         
         // TODO process result here
         result = port.aggiungiTipoAssenza(nuovoTipoAssenza);
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         if (ex.getMessage().equals("Non e' stato passato alcun tipo di assenza.")) result=-2;
      }
      
      return result;
      
   }
   
   public void driverCancellaTipoAssenza(int idTipoAssenza){
                  
      try { // Call Web Service Operation
         ws.didattica.driver.tipoassenzaclient.WSTipoAssenzaService service = new ws.didattica.driver.tipoassenzaclient.WSTipoAssenzaService();
         ws.didattica.driver.tipoassenzaclient.WSTipoAssenza port = service.getWSTipoAssenzaPort();
         // TODO initialize WS operation arguments here
         
         port.cancellaTipoAssenza(idTipoAssenza);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
            
   }
   
   public java.util.List<ws.didattica.driver.tipoassenzaclient.TipoAssenza> driverRecuperaTipiAssenza(){
      
      java.util.List<ws.didattica.driver.tipoassenzaclient.TipoAssenza> result=null;
      
      try { // Call Web Service Operation
         ws.didattica.driver.tipoassenzaclient.WSTipoAssenzaService service = new ws.didattica.driver.tipoassenzaclient.WSTipoAssenzaService();
         ws.didattica.driver.tipoassenzaclient.WSTipoAssenza port = service.getWSTipoAssenzaPort();
         // TODO process result here
         result = port.recuperaTipiAssenza();
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      return result;
   }
}
