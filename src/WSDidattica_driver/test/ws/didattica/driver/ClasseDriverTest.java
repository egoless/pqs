/*
 * ClasseDriverTest.java
 * JUnit based test
 *
 * Created on 20 marzo 2007, 18.47
 */

package ws.didattica.driver;

import junit.framework.*;

/**
 *
 * @author Roberto
 */
public class ClasseDriverTest extends TestCase {
   
  ws.didattica.driver.classeclient.Classe classeDaAggiungere=new ws.didattica.driver.classeclient.Classe();
   ws.didattica.driver.testclient.Classe classeDaCancellare=new ws.didattica.driver.testclient.Classe();
   
   public ClasseDriverTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception { 
      
      classeDaAggiungere.setId(1);
      classeDaAggiungere.setAnnoCorso(2);
      classeDaAggiungere.setIndirizzoStudiId(20);
      classeDaAggiungere.setSezione(30);
      
      classeDaCancellare.setId(99);
      classeDaCancellare.setAnnoCorso(4);
      classeDaCancellare.setIndirizzoStudiId(20);
      classeDaCancellare.setSezione(30);
           
      // Salvo una Classe da cancellare
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         long result = port.salvaClasse(classeDaCancellare);
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      
   }

   protected void tearDown() throws Exception {
      //Cancello dal DB la classe inserita
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         port.eliminaClasse(classeDaAggiungere.getId());
                  
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      //Cancello dal DB la classe da cancellare (il test di cancellazione puo' fallire)
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         port.eliminaClasse(classeDaCancellare.getId());
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }  
      
   }

   /**
    * Test of driverAggiungiClasse method, of class ws.didattica.driver.ClasseDriver.
    */
   public void testDriverAggiungiClasse() {
      System.out.println("driverAggiungiClasse");
      
      ClasseDriver instance = new ClasseDriver();
      
        
      // il risultato atteso e' l'id
      int expResult = classeDaAggiungere.getId();
      // chiamo il driver
      long result = instance.driverAggiungiClasse(classeDaAggiungere);
      // test che l'id ritornato sia esatto
      assertEquals(expResult, result);
      
      //creo una classe per provare a caricare quella salvata
      ws.didattica.driver.testclient.Classe loaded=null;
      
      // carico la Classe che ho appena memorizzato
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         // TODO process result here
          loaded = port.caricaClasse(expResult);
       
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
            
           
      assertEquals(classeDaAggiungere.getId(),loaded.getId());
      assertEquals(classeDaAggiungere.getAnnoCorso(),loaded.getAnnoCorso());
      assertEquals(classeDaAggiungere.getIndirizzoStudiId(),loaded.getIndirizzoStudiId());
      assertEquals(classeDaAggiungere.getSezione(),loaded.getSezione());
          
      // provo a passare null
      result=instance.driverAggiungiClasse(null);
      // il driver dovrebbe acchiappare l'eccezione e ritornare -2
      assertEquals(result,-2);
      
      // provo a passare un anno non valido
      classeDaAggiungere.setAnnoCorso(6);
      result=instance.driverAggiungiClasse(classeDaAggiungere);
      // il driver dovrebbe acchiappare l'eccezione e ritornare -3
      assertEquals(result,-3);
      
      // TODO review the generated test code and remove the default call to fail.
      //fail("The test case is a prototype.");
   }

   /**
    * Test of driverCancellaClasse method, of class ws.didattica.driver.ClasseDriver.
    */
   public void testDriverCancellaClasse() {
      System.out.println("driverCancellaClasse");
     
      ClasseDriver instance = new ClasseDriver();
      
      // chiamo il driver che cancelli la classe che ho creato
      instance.driverCancellaClasse(classeDaCancellare.getId());
      
      // verra' sostituito da null se la Classe e' cancellata correttamente
      ws.didattica.driver.testclient.Classe result=new ws.didattica.driver.testclient.Classe();
           
      // provo a caricare la classe che ho cancellato
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         // se e' stata cancellata dovrebbe ritornare null
         result = port.caricaClasse(classeDaCancellare.getId());
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      // verifico l'eliminazione
      assertEquals(null,result);
 
      
      
      // TODO review the generated test code and remove the default call to fail.
      //fail("The test case is a prototype.");
   }

   /**
    * Test of driverRecuperaTipiAssenza method, of class ws.didattica.driver.ClasseDriver.
    */
   public void testDriverRecuperaClassi() {
      System.out.println("driverRecuperaClassi");
      
      ClasseDriver instance = new ClasseDriver();
      
      java.util.List<ws.didattica.driver.classeclient.Classe> result = instance.driverRecuperaClassi();
      assertNotNull(result);
      
      ws.didattica.driver.classeclient.Classe c=result.get(0);
      assertNotNull(c);
      assertEquals(5, c.getId());
      assertEquals('A',  c.getSezione());
      assertEquals(new Integer(5), c.getAnnoCorso());
   }
   
   
}
