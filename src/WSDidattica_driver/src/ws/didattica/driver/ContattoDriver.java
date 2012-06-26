/*
 * ContattoDriver.java
 *
 * Created on 18 marzo 2007, 21.57
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ws.didattica.driver;

/**
 *
 * @author Roberto
 */
public class ContattoDriver {
   
   /** Creates a new instance of ContattoDriver */
   public ContattoDriver() {
   }
   
   public long driverAggiungiContatto(ws.didattica.driver.contattoclient.Contatto nuovoContatto){
      
      long result=-1;
      
      try { // Call Web Service Operation
         ws.didattica.driver.contattoclient.WSContattoService service = new ws.didattica.driver.contattoclient.WSContattoService();
         ws.didattica.driver.contattoclient.WSContatto port = service.getWSContattoPort();
         
         // TODO process result here
         result = port.aggiungiContatto(nuovoContatto);
         
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         if (ex.getMessage().equals("Non e' stato passatto alcun contatto.")) return -2;
         // TODO handle custom exceptions here
      }
      
      return result;
   }
   
   public void driverModificaContatto(ws.didattica.driver.contattoclient.Contatto contatto){
      try { // Call Web Service Operation
         ws.didattica.driver.contattoclient.WSContattoService service = new ws.didattica.driver.contattoclient.WSContattoService();
         ws.didattica.driver.contattoclient.WSContatto port = service.getWSContattoPort();
         
         port.modificaContatto(contatto);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
   }
   
   public void driverCancellaContatto(int idContatto){
      try { // Call Web Service Operation
         ws.didattica.driver.contattoclient.WSContattoService service = new ws.didattica.driver.contattoclient.WSContattoService();
         ws.didattica.driver.contattoclient.WSContatto port = service.getWSContattoPort();
         // TODO initialize WS operation arguments here
         
         port.cancellaContatto(idContatto);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
   }
   
   public java.util.List<ws.didattica.driver.contattoclient.Contatto>
           driverCercaContatto(ws.didattica.driver.contattoclient.ParametriRicercaContatto insiemeParametri) throws Exception {
      
      java.util.List<ws.didattica.driver.contattoclient.Contatto> result=null;
      
      ws.didattica.driver.contattoclient.WSContattoService service = new ws.didattica.driver.contattoclient.WSContattoService();
      ws.didattica.driver.contattoclient.WSContatto port = service.getWSContattoPort();
      
      result = port.cercaContatto(insiemeParametri);
      System.out.println("Result = "+result);
      
      return result;
      
   }
}
