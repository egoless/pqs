/*
 * TipoVotoDriver.java
 *
 * Created on 17 marzo 2007, 16.50
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ws.didattica.driver;

import ws.didattica.driver.tipovotoclient.WSDidatticaException_Exception;

/**
 *
 * @author Roberto
 */
public class TipoVotoDriver {
   
   /** Creates a new instance of TipoVotoDriver */
   public TipoVotoDriver() {
   }
   
   public long driverAggiungiTipoVoto(ws.didattica.driver.tipovotoclient.TipoVoto tipoVoto) throws Exception {
      
      long result=-1;
      
      try { // Call Web Service Operation
         ws.didattica.driver.tipovotoclient.WSTipoVotoService service = new ws.didattica.driver.tipovotoclient.WSTipoVotoService();
         ws.didattica.driver.tipovotoclient.WSTipoVoto port = service.getWSTipoVotoPort();
         // TODO initialize WS operation arguments here
         ws.didattica.driver.tipovotoclient.TipoVoto nuovoTipoVoto = tipoVoto;
         // TODO process result here
         result = port.aggiungiTipoVoto(nuovoTipoVoto);
         System.out.println("Result = "+result);
      } catch (WSDidatticaException_Exception ex) {
         if (ex.getMessage().equals("Non e' stato passato alcun tipo di voto.")) {
            result=-2;
         }
      } catch (Exception ex) {
         throw ex;
      }
      
      return result;
      
   }
   
   public void driverCancellaTipoVoto(int idTipoVoto){
      
      try { // Call Web Service Operation
         ws.didattica.driver.tipovotoclient.WSTipoVotoService service = new ws.didattica.driver.tipovotoclient.WSTipoVotoService();
         ws.didattica.driver.tipovotoclient.WSTipoVoto port = service.getWSTipoVotoPort();
         // TODO initialize WS operation arguments here
         
         port.cancellaTipoVoto(idTipoVoto);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
   }
   
   public java.util.List<ws.didattica.driver.tipovotoclient.TipoVoto> driverRecuperaTipiVoto() throws Exception {
      
      java.util.List<ws.didattica.driver.tipovotoclient.TipoVoto> result=null;

      ws.didattica.driver.tipovotoclient.WSTipoVotoService service = new ws.didattica.driver.tipovotoclient.WSTipoVotoService();
      ws.didattica.driver.tipovotoclient.WSTipoVoto port = service.getWSTipoVotoPort();
      // TODO process result here
      result = port.recuperaTipiVoto();
      System.out.println("Result = "+result);
      
      return result;
   }
   
}
