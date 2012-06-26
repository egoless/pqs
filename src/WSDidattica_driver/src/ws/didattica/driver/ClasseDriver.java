/*
 * ClasseDriver.java
 *
 * Created on 20 marzo 2007, 18.45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ws.didattica.driver;

/**
 *
 * @author Roberto
 */
public class ClasseDriver {
   
   /** Creates a new instance of ClasseDriver */
   public ClasseDriver() {
   }
   public long driverAggiungiClasse(ws.didattica.driver.classeclient.Classe nuovaClasse){
      
      long result=-1;
      
      try { // Call Web Service Operation
         ws.didattica.driver.classeclient.WSClasseService service = new ws.didattica.driver.classeclient.WSClasseService();
         ws.didattica.driver.classeclient.WSClasse port = service.getWSClassePort();
         
         // TODO process result here
         result = port.aggiungiClasse(nuovaClasse);
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         if (ex.getMessage().equals("Non e' stato passato alcuna classe.")) result=-2;
         if (ex.getMessage().equals("L'anno del corso non e' compreso tra 1 e 5.")) result=-3;
      }
      
      return result;
      
   }
   
   public void driverCancellaClasse(int idClasse){
                  
      try { // Call Web Service Operation
         ws.didattica.driver.classeclient.WSClasseService service = new ws.didattica.driver.classeclient.WSClasseService();
         ws.didattica.driver.classeclient.WSClasse port = service.getWSClassePort();
         // TODO initialize WS operation arguments here
         
         port.cancellaClasse(idClasse);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
            
   }
   
   public java.util.List<ws.didattica.driver.classeclient.Classe> driverRecuperaClassi(){
      
      java.util.List<ws.didattica.driver.classeclient.Classe> result=null;
      
      try { // Call Web Service Operation
         ws.didattica.driver.classeclient.WSClasseService service = new ws.didattica.driver.classeclient.WSClasseService();
         ws.didattica.driver.classeclient.WSClasse port = service.getWSClassePort();
         // TODO process result here
         result = port.recuperaClassi();
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      return result;
   }
}
