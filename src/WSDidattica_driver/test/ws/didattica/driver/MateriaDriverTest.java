/*
 * MateriaDriverTest.java
 * JUnit based test
 *
 * Created on 20 marzo 2007, 18.35
 */

package ws.didattica.driver;

import junit.framework.*;

/**
 *
 * @author Roberto
 */
public class MateriaDriverTest extends TestCase {
   
   ws.didattica.driver.materiaclient.MateriaInsegnamento materiaDaAggiungere=new ws.didattica.driver.materiaclient.MateriaInsegnamento();
   ws.didattica.driver.testclient.MateriaInsegnamento materiaDaCancellare=new ws.didattica.driver.testclient.MateriaInsegnamento();
   
   public MateriaDriverTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception { 
      
      materiaDaAggiungere.setNome("MateriaDaAggiungere");
      materiaDaAggiungere.setId(1);
      materiaDaCancellare.setNome("MateriaDaCancellare");
      materiaDaCancellare.setId(99);
      
      // Salvo una Materia da cancellare
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         long result = port.salvaMateria(materiaDaCancellare);
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      
   }

   protected void tearDown() throws Exception {
      //Cancello dal DB la materia inserita
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         port.eliminaMateria(materiaDaAggiungere.getId());
                  
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      //Cancello dal DB la materia da cancellare (il test di cancellazione puo' fallire)
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         port.eliminaMateria(materiaDaCancellare.getId());
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }  
      
   }

   /**
    * Test of driverAggiungiMateria method, of class ws.didattica.driver.MateriaDriver.
    */
   public void testDriverAggiungiMateria() {
      System.out.println("driverAggiungiMateria");
      
      
      MateriaDriver instance = new MateriaDriver();
           
     
      // il risultato atteso e' l'id
      int expResult = materiaDaAggiungere.getId();
      // chiamo il driver
      long result = instance.driverAggiungiMateria(materiaDaAggiungere);
      // test che l'id ritornato sia esatto
      assertEquals(expResult, result);
      
      //creo una materia per provare a caricare quella salvata
      ws.didattica.driver.testclient.MateriaInsegnamento loaded=null;
      
      // carico la Materia che ho appena memorizzato
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         // TODO process result here
          loaded = port.caricaMateria(expResult);
         //System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
            
      
      assertEquals(materiaDaAggiungere.getNome(),loaded.getNome());
      assertEquals(materiaDaAggiungere.getId(),loaded.getId());
          
      // provo a passare null
      result=instance.driverAggiungiMateria(null);
      // il driver dovrebbe acchiappare l'eccezione e ritornare -2
      assertEquals(result,-2);
      
      // TODO review the generated test code and remove the default call to fail.
      //fail("The test case is a prototype.");
   }

   /**
    * Test of driverCancellaMateria method, of class ws.didattica.driver.MateriaDriver.
    */
   public void testDriverCancellaMateria() {
      System.out.println("driverCancellaMateria");
     
      MateriaDriver instance = new MateriaDriver();
      
      // chiamo il driver che cancelli la materia che ho creato
      instance.driverCancellaMateria(materiaDaCancellare.getId());
      
      // verra' sostituito da null se la Materia e' cancellata correttamente
      ws.didattica.driver.testclient.MateriaInsegnamento result=new ws.didattica.driver.testclient.MateriaInsegnamento();
           
      // provo a caricare la materia che ho cancellato
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         // se e' stata cancellata dovrebbe ritornare null
         result = port.caricaMateria(materiaDaCancellare.getId());
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      // verifico l'eliminazione
      assertEquals(null,result);
 
      
      
      // TODO review the generated test code and remove the default call to fail.
      //fail("The test case is a prototype.");
   }

   /**
    * Test of driverRecuperaTipiAssenza method, of class ws.didattica.driver.MateriaDriver.
    */
   public void testDriverRecuperaMaterie() {
      System.out.println("driverRecuperaMaterie");
      
      MateriaDriver instance = new MateriaDriver();
      
      java.util.List<ws.didattica.driver.materiaclient.MateriaInsegnamento> result = instance.driverRecuperaMaterie();
      assertNotNull(result);
      
      ws.didattica.driver.materiaclient.MateriaInsegnamento m=result.get(0);
      assertNotNull(m);
      assertEquals(5, m.getId());
      assertEquals("UnaMateria", m.getNome());
   }
   
   
   
}
