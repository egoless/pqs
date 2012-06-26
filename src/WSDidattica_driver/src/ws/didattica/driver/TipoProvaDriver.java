/*
 * Nome file: TipoProvaDriver.java
 * Data creazione: 26 marzo 2007, 7.23
 * Info svn: $Id: TipoProvaDriver.java 792 2007-03-26 12:20:42Z eric $
 */

package ws.didattica.driver;

import ws.didattica.driver.tipoprovaclient.TipoProva;
import ws.didattica.driver.tipoprovaclient.WSDidatticaException_Exception;
import ws.didattica.driver.tipoprovaclient.WSTipoProva;
import ws.didattica.driver.tipoprovaclient.WSTipoProvaService;

/**
 * Classe che pilota WSTipoProva. Non fa altro che fornire
 * delle funzioni per richiamare quelle del WS.
 */
public class TipoProvaDriver {
   
   public long driverAggiungiTipoProva(TipoProva tipoProva) throws Exception {
      
      long result=-1;
      
      try { // Call Web Service Operation
         WSTipoProvaService service = new WSTipoProvaService();
         WSTipoProva port = service.getWSTipoProvaPort();
         
         TipoProva nuovoTipoProva = tipoProva;
         
         result = port.aggiungiTipoProva(nuovoTipoProva);
         System.out.println("Result = "+result);
      } catch (WSDidatticaException_Exception ex) {
         if (ex.getMessage().equals("Non e' stato passato alcun tipo di assenza.")) {
            result=-2;
         }
      } catch (Exception ex) {
         throw ex;
      }
      
      return result;
   }
   
   public void driverCancellaTipoProva(int idTipoProva) throws Exception {
      WSTipoProvaService service = new WSTipoProvaService();
      WSTipoProva port = service.getWSTipoProvaPort();
      
      port.cancellaTipoProva(idTipoProva);
   }
   
   public java.util.List<TipoProva> driverRecuperaTipiProva() throws Exception {  
      java.util.List<TipoProva> result=null;
      
      WSTipoProvaService service = new WSTipoProvaService();
      WSTipoProva port = service.getWSTipoProvaPort();
      
      result = port.recuperaTipiProva();
      System.out.println("Result = "+result);
      
      return result;
   }
   
}
