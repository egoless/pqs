/*
 * VotoDriver.java
 *
 * Created on 19 marzo 2007, 9.55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ws.didattica.driver;

/**
 *
 * @author Roberto
 */
public class VotoDriver {
   
   /** Creates a new instance of VotoDriver */
   public VotoDriver() {
   }
   
   public long driverAggiungiVoto(ws.didattica.driver.votoclient.Voto nuovoVoto){
      
      long result=-1;
      
      try { // Call Web Service Operation
         ws.didattica.driver.votoclient.WSVotoService service = new ws.didattica.driver.votoclient.WSVotoService();
         ws.didattica.driver.votoclient.WSVoto port = service.getWSVotoPort();
         // TODO initialize WS operation arguments here
         
         result = port.aggiungiVoto(nuovoVoto);
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         ex.printStackTrace();
         if (ex.getMessage().equals("Non e' stato passato alcun voto")) return -2;
         if (ex.getMessage().equals("Il valore del voto non e' compreso tra 1 e 10")) return -3;
         if (ex.getMessage().equals("Il momento in cui il voto e' stato assegnato e' successivo all'istante attuale.")) return -4;
      }
      
      return result;
   }
   
   public void driverModificaVoto(ws.didattica.driver.votoclient.Voto voto){
      
      try { // Call Web Service Operation
         ws.didattica.driver.votoclient.WSVotoService service = new ws.didattica.driver.votoclient.WSVotoService();
         ws.didattica.driver.votoclient.WSVoto port = service.getWSVotoPort();
         
         port.modificaVoto(voto);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
   }
   
   public void driverCancellaVoto(int idVoto){
      
      try { // Call Web Service Operation
         ws.didattica.driver.votoclient.WSVotoService service = new ws.didattica.driver.votoclient.WSVotoService();
         ws.didattica.driver.votoclient.WSVoto port = service.getWSVotoPort();
         
         port.cancellaVoto(idVoto);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
   }
   
   public java.util.List<ws.didattica.driver.votoclient.Voto>
           driverCercaVoto(ws.didattica.driver.votoclient.ParametriRicercaVoto insiemeParametri)
           throws Exception {
      
      java.util.List<ws.didattica.driver.votoclient.Voto> result=null;
      //try { // Call Web Service Operation
      ws.didattica.driver.votoclient.WSVotoService service = new ws.didattica.driver.votoclient.WSVotoService();
      ws.didattica.driver.votoclient.WSVoto port = service.getWSVotoPort();
      
      // TODO process result here
      result = port.cercaVoto(insiemeParametri);
      
//      } catch (Exception ex) {
//         // TODO handle custom exceptions here
//      }
      return result;
   }
}

