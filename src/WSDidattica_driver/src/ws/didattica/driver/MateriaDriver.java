/*
 * MateriaDriver.java
 *
 * Created on 20 marzo 2007, 18.27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ws.didattica.driver;

/**
 *
 * @author Roberto
 */
public class MateriaDriver {
   
   /** Creates a new instance of MateriaDriver */
   public MateriaDriver() {
   }
   public long driverAggiungiMateria(ws.didattica.driver.materiaclient.MateriaInsegnamento nuovaMateria){
      
      long result=-1;
      
      try { // Call Web Service Operation
         ws.didattica.driver.materiaclient.WSMateriaService service = new ws.didattica.driver.materiaclient.WSMateriaService();
         ws.didattica.driver.materiaclient.WSMateria port = service.getWSMateriaPort();
         
         // TODO process result here
         result = port.aggiungiMateria(nuovaMateria);
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         if (ex.getMessage().equals("Non e' stato passato alcuna materia.")) return -2;
      }
      
      return result;
      
   }
   
   public void driverCancellaMateria(int idMateria){
                  
      try { // Call Web Service Operation
         ws.didattica.driver.materiaclient.WSMateriaService service = new ws.didattica.driver.materiaclient.WSMateriaService();
         ws.didattica.driver.materiaclient.WSMateria port = service.getWSMateriaPort();
         // TODO initialize WS operation arguments here
         
         port.cancellaMateria(idMateria);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
            
   }
   
   public java.util.List<ws.didattica.driver.materiaclient.MateriaInsegnamento> driverRecuperaMaterie(){
      
      java.util.List<ws.didattica.driver.materiaclient.MateriaInsegnamento> result=null;
      
      try { // Call Web Service Operation
         ws.didattica.driver.materiaclient.WSMateriaService service = new ws.didattica.driver.materiaclient.WSMateriaService();
         ws.didattica.driver.materiaclient.WSMateria port = service.getWSMateriaPort();
         // TODO process result here
         result = port.recuperaMaterie();
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      return result;
   }
}
