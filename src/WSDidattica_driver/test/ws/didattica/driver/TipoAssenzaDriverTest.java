/*
 * TipoAssenzaDriverTest.java
 * JUnit based test
 *
 * Created on 19 marzo 2007, 10.31
 */

package ws.didattica.driver;

import junit.framework.*;

/**
 *
 * @author Roberto
 */
public class TipoAssenzaDriverTest extends TestCase {
   
   ws.didattica.driver.tipoassenzaclient.TipoAssenza tipoAssenzaDaAggiungere=new ws.didattica.driver.tipoassenzaclient.TipoAssenza();
   ws.didattica.driver.testclient.TipoAssenza tipoAssenzaDaCancellare=new ws.didattica.driver.testclient.TipoAssenza();
   
   public TipoAssenzaDriverTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception { 
      tipoAssenzaDaAggiungere.setDescrizione("TipoAssenzaDaAggiungere");
      tipoAssenzaDaAggiungere.setId(1);
      tipoAssenzaDaCancellare.setDescrizione("TipAssenzaDaCancellare");
      tipoAssenzaDaCancellare.setId(99);
      
      // Salvo un TipoAssenza da cancellare
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         long result = port.salvaTipoAssenza(tipoAssenzaDaCancellare);
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      
   }

   protected void tearDown() throws Exception {
      //Cancello dal DB il tipoAssenza inserito
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         port.eliminaTipoAssenza(tipoAssenzaDaAggiungere.getId());
                  
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      //Cancello dal DB il tipoAssenza da cancellare (il test di cancellazione puo' fallire)
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         port.eliminaTipoAssenza(tipoAssenzaDaCancellare.getId());
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }  
      
   }

   /**
    * Test of driverAggiungiTipoAssenza method, of class ws.didattica.driver.TipoAssenzaDriver.
    */
   public void testDriverAggiungiTipoAssenza() {
      System.out.println("driverAggiungiTipoAssenza");
      
      
      TipoAssenzaDriver instance = new TipoAssenzaDriver();
      
      
     
      // il risultato atteso e' l'id
      int expResult = tipoAssenzaDaAggiungere.getId();
      // chiamo il driver
      long result = instance.driverAggiungiTipoAssenza(tipoAssenzaDaAggiungere);
      // test che l'id ritornato sia esatto
      assertEquals(expResult, result);
      
      //creo un tipoAssenza per provare a caricare quello salvato
      ws.didattica.driver.testclient.TipoAssenza loaded=null;
      
      // carico il TipoAssenza che ho appena memorizzato
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         // TODO process result here
          loaded = port.caricaTipoAssenza(expResult);
         //System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
            
      
      assertEquals(tipoAssenzaDaAggiungere.getDescrizione(),loaded.getDescrizione());
      assertEquals(tipoAssenzaDaAggiungere.getId(),loaded.getId());
      
          
      // provo a passare null
      result=instance.driverAggiungiTipoAssenza(null);
      // il driver dovrebbe acchiappare l'eccezione e ritornare -2
      assertEquals(result,-2);
      
      // TODO review the generated test code and remove the default call to fail.
      //fail("The test case is a prototype.");
   }

   /**
    * Test of driverCancellaTipoAssenza method, of class ws.didattica.driver.TipoAssenzaDriver.
    */
   public void testDriverCancellaTipoAssenza() {
      System.out.println("driverCancellaTipoAssenza");
     
      TipoAssenzaDriver instance = new TipoAssenzaDriver();
      
      // chiamo il driver che cancelli il tipoAssenza che ho creato
      instance.driverCancellaTipoAssenza(tipoAssenzaDaCancellare.getId());
      
      // verra' sostituito da null se il TipoAssenza e' cancellato correttamente
      ws.didattica.driver.testclient.TipoAssenza result=new ws.didattica.driver.testclient.TipoAssenza();
           
      // provo a caricare il tipoassenza che ho cancellato
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         // se e' stato cancellato dovrebbe ritornare null
         result = port.caricaTipoAssenza(tipoAssenzaDaCancellare.getId());
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      // verifico l'eliminazione
      assertEquals(null,result);
   }

   /**
    * Test of driverRecuperaTipiAssenza method, of class ws.didattica.driver.TipoAssenzaDriver.
    */
   public void testDriverRecuperaTipiAssenza() {
      System.out.println("driverRecuperaTipiAssenza");
      
      TipoAssenzaDriver instance = new TipoAssenzaDriver();
      
      java.util.List<ws.didattica.driver.tipoassenzaclient.TipoAssenza> result = instance.driverRecuperaTipiAssenza();
      assertNotNull(result);
      ws.didattica.driver.tipoassenzaclient.TipoAssenza obj=result.get(0);
      assertNotNull(obj);
      assertEquals("UnTipoDiAssenza", obj.getDescrizione());
      assertEquals(1,  obj.getId());
   }
   
   
}
