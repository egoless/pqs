/*
 * ContattoDriverTest.java
 * JUnit based test
 *
 * Created on 18 marzo 2007, 22.11
 */

package ws.didattica.driver;

import junit.framework.*;
import ws.didattica.driver.contattoclient.Contatto;
import ws.didattica.driver.contattoclient.ParametriRicercaContatto;
import ws.didattica.driver.contattoclient.WSDidatticaException_Exception;

/**
 *
 * @author Roberto
 */
public class ContattoDriverTest extends TestCase {
   
   ws.didattica.driver.contattoclient.Contatto contattoDaAggiungere=new ws.didattica.driver.contattoclient.Contatto();
   ws.didattica.driver.testclient.Contatto contattoDaCancellare=new ws.didattica.driver.testclient.Contatto();
   
   public ContattoDriverTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
      contattoDaAggiungere.setCap("TestCAP");
      contattoDaAggiungere.setCitta("TestCitta");
      contattoDaAggiungere.setCivico("TestCivico");
      contattoDaAggiungere.setFax("TestFax");
      contattoDaAggiungere.setId(1);
      contattoDaAggiungere.setNazioneId(10);
      contattoDaAggiungere.setProvincia("TestProvincia");
      contattoDaAggiungere.setTelefonoPrincipale("TestTelPrinc");
      contattoDaAggiungere.setTelefonoSecondario("TestTelSec");
      contattoDaAggiungere.setVia("TestVia");
      
      contattoDaCancellare.setCap("TestCAP");
      contattoDaCancellare.setCitta("TestCitta");
      contattoDaCancellare.setCivico("TestCivico");
      contattoDaCancellare.setFax("TestFax");
      contattoDaCancellare.setId(99);
      contattoDaCancellare.setNazioneId(90);
      contattoDaCancellare.setProvincia("TestProvincia");
      contattoDaCancellare.setTelefonoPrincipale("TestTelPrinc");
      contattoDaCancellare.setTelefonoSecondario("TestTelSec");
      contattoDaCancellare.setVia("TestVia");
      
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         long result = port.salvaContatto(contattoDaCancellare);
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      
      
   }

   protected void tearDown() throws Exception {
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         port.eliminaContatto(contattoDaAggiungere.getId());
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         port.eliminaContatto(contattoDaCancellare.getId());
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
   }

   /**
    * Test of driverAggiungiContatto method, of class ws.didattica.driver.ContattoDriver.
    */
   public void testDriverAggiungiContatto() {
      System.out.println("driverAggiungiContatto");
      
     
      ContattoDriver instance = new ContattoDriver();
      
      long expResult = contattoDaAggiungere.getId();
      long result = instance.driverAggiungiContatto(contattoDaAggiungere);
      assertEquals(expResult, result);
      
      ws.didattica.driver.testclient.Contatto loaded=null;
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         loaded = port.caricaContatto(contattoDaAggiungere.getId());
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      assertEquals(contattoDaAggiungere.getCap() ,loaded.getCap() );
      assertEquals(contattoDaAggiungere.getCitta() ,loaded.getCitta() );
      assertEquals(contattoDaAggiungere.getCivico() ,loaded.getCivico() );
      assertEquals(contattoDaAggiungere.getFax() ,loaded.getFax() );
      assertEquals(contattoDaAggiungere.getId() ,loaded.getId() );
      assertEquals(contattoDaAggiungere.getNazioneId() ,loaded.getNazioneId() );
      assertEquals(contattoDaAggiungere.getProvincia() ,loaded.getProvincia() );
      assertEquals(contattoDaAggiungere.getTelefonoPrincipale() ,loaded.getTelefonoPrincipale() );
      assertEquals(contattoDaAggiungere.getTelefonoSecondario() ,loaded.getTelefonoSecondario() );
      assertEquals(contattoDaAggiungere.getVia() ,loaded.getVia() );
      
      //caso2: provo ad aggiungere null
      result=instance.driverAggiungiContatto(null);
      // dovrebbe acchiappare l'eccezione e ritornare -2
      assertEquals(-2, result);
      
      // TODO review the generated test code and remove the default call to fail.
      //fail("The test case is a prototype.");
   }

   /**
    * Test of driverModificaContatto method, of class ws.didattica.driver.ContattoDriver.
    */
   public void testDriverModificaContatto() {
      System.out.println("driverModificaContatto");
      
      contattoDaAggiungere.setCap("CAPmodificato");
      contattoDaAggiungere.setProvincia("ProvinciaModificata");
      ContattoDriver instance = new ContattoDriver();
      
      instance.driverModificaContatto(contattoDaAggiungere);
      
      ws.didattica.driver.testclient.Contatto loaded=new ws.didattica.driver.testclient.Contatto();
      
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         loaded= port.caricaContatto(contattoDaAggiungere.getId());
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      assertEquals(contattoDaAggiungere.getCap() ,loaded.getCap() );
      assertEquals(contattoDaAggiungere.getCitta() ,loaded.getCitta() );
      assertEquals(contattoDaAggiungere.getCivico() ,loaded.getCivico() );
      assertEquals(contattoDaAggiungere.getFax() ,loaded.getFax() );
      assertEquals(contattoDaAggiungere.getId() ,loaded.getId() );
      assertEquals(contattoDaAggiungere.getNazioneId() ,loaded.getNazioneId() );
      assertEquals(contattoDaAggiungere.getProvincia() ,loaded.getProvincia() );
      assertEquals(contattoDaAggiungere.getTelefonoPrincipale() ,loaded.getTelefonoPrincipale() );
      assertEquals(contattoDaAggiungere.getTelefonoSecondario() ,loaded.getTelefonoSecondario() );
      assertEquals(contattoDaAggiungere.getVia() ,loaded.getVia() );
      
      // TODO review the generated test code and remove the default call to fail.
      //fail("The test case is a prototype.");
   }

   /**
    * Test of driverCancellaContatto method, of class ws.didattica.driver.ContattoDriver.
    */
   public void testDriverCancellaContatto() {
      System.out.println("driverCancellaContatto");
      
      ContattoDriver instance = new ContattoDriver();
      
      instance.driverCancellaContatto(contattoDaCancellare.getId());
      
      // verra' sostituito da null se il contatto e' cancellato correttamente
      ws.didattica.driver.testclient.Contatto result=new ws.didattica.driver.testclient.Contatto();
           
      // provo a caricare il contatto che ho cancellato
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         // se e' stato cancellata dovrebbe ritornare null
         result = port.caricaContatto(contattoDaCancellare.getId());
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      // verifico l'eliminazione
      assertEquals(null,result);
      
      // TODO review the generated test code and remove the default call to fail.
      //fail("The test case is a prototype.");
   }

   /**
    * Test of driverCercaContatto method, of class ws.didattica.driver.ContattoDriver.
    */
   public void testDriverCercaContatto() throws Exception{
      System.out.println("driverCercaContatto");

      boolean exceptionThrown=false;
      
      ws.didattica.driver.contattoclient.ParametriRicercaContatto insiemeParametri = null;
      ContattoDriver instance = new ContattoDriver();
      
      try {
         java.util.List<ws.didattica.driver.contattoclient.Contatto> result = instance.driverCercaContatto(insiemeParametri);
      } catch(WSDidatticaException_Exception ex){
         exceptionThrown=true;
      }
      
      assertEquals(true, exceptionThrown);
      
      insiemeParametri=new ParametriRicercaContatto();
      java.util.List<Contatto> result = instance.driverCercaContatto(insiemeParametri);
      
      assertNotNull(result);
      Contatto v=result.get(0);
      assertNotNull(v);
      assertEquals(5, v.getId());
      assertEquals("Belzoni",   v.getVia());
      assertEquals("5",   v.getCivico());
   }
   
}
