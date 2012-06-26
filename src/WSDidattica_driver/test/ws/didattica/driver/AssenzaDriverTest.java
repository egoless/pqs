/*
 * AssenzaDriverTest.java
 * JUnit based test
 *
 * Created on 17 marzo 2007, 23.23
 */

package ws.didattica.driver;

import junit.framework.*;
import ws.didattica.driver.assenzaclient.Assenza;
import ws.didattica.driver.assenzaclient.ParametriRicercaAssenza;
import ws.didattica.driver.assenzaclient.WSDidatticaException_Exception;

/**
 *
 * @author Roberto
 */
public class AssenzaDriverTest extends TestCase {
   
   ws.didattica.driver.assenzaclient.Assenza assenzaDaAggiungere=new  ws.didattica.driver.assenzaclient.Assenza();
   ws.didattica.driver.testclient.Assenza assenzaDaCancellare=new  ws.didattica.driver.testclient.Assenza();
   
   public AssenzaDriverTest(String testName) {
      super(testName);
   }
   
   protected void setUp() throws Exception {
      ws.didattica.driver.assenzaclient.Date dt=new ws.didattica.driver.assenzaclient.Date();
      dt.setDate("2007-05-09T17:00:00.000+02:00");
      assenzaDaAggiungere.setDataOraFine(dt);
      
      dt=new ws.didattica.driver.assenzaclient.Date();
      dt.setDate("2007-05-07T17:00:00.000+02:00");
      assenzaDaAggiungere.setDataOraInizio(dt);
      
      assenzaDaAggiungere.setId(1);
      assenzaDaAggiungere.setPersonaId(10);
      assenzaDaAggiungere.setTipoAssenzaId(20);
      
      ws.didattica.driver.testclient.Date dt2=new ws.didattica.driver.testclient.Date();
      dt2.setDate("2007-04-11T17:00:00.000+02:00");
      assenzaDaCancellare.setDataOraFine(dt2);
      dt2=new ws.didattica.driver.testclient.Date();
      dt2.setDate("2007-04-01T17:00:00.000+02:00");
      assenzaDaCancellare.setDataOraInizio(dt2);
      assenzaDaCancellare.setId(99);
      assenzaDaCancellare.setPersonaId(90);
      assenzaDaCancellare.setTipoAssenzaId(80);
      
      //salvo un' assenza da cancellare
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         long result = port.salvaAssenza(assenzaDaCancellare);
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
   }
   
   protected void tearDown() throws Exception {
      //elimino l'assenza inserita
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         port.eliminaAssenza(assenzaDaAggiungere.getId());
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      // elimino quella da cancellare (caso in cui il test fallisca)
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         port.eliminaAssenza(assenzaDaCancellare.getId());
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      
      
   }
   
   /**
    * Test of driverAggiungiAssenza method, of class ws.didattica.driver.AssenzaDriver.
    */
   public void testDriverAggiungiAssenza() throws Exception {
      System.out.println("driverAggiungiAssenza");
      
      
      AssenzaDriver instance = new AssenzaDriver();
      
      // caso 1: aggiungo correttamente l'assenza
      long expResult = assenzaDaAggiungere.getId();
      long result = instance.driverAggiungiAssenza(assenzaDaAggiungere);
      assertEquals(expResult, result);
      
      //provo a caricare l'assenza aggiunta
      ws.didattica.driver.testclient.Assenza loaded=null;
      
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         loaded = port.caricaAssenza(assenzaDaAggiungere.getId());
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      assertEquals(assenzaDaAggiungere.getDataOraFine().getDate(), loaded.getDataOraFine().getDate());
      assertEquals(assenzaDaAggiungere.getDataOraInizio().getDate(), loaded.getDataOraInizio().getDate());
      assertEquals(assenzaDaAggiungere.getId(),loaded.getId());
      assertEquals(assenzaDaAggiungere.getPersonaId(),loaded.getPersonaId());
      assertEquals(assenzaDaAggiungere.getTipoAssenzaId(),loaded.getTipoAssenzaId());
      
      //caso2: provo ad aggiungere null
      result=instance.driverAggiungiAssenza(null);
      // dovrebbe acchiappare l'eccezione e ritornare -2
      assertEquals(-2, result);
      
      //caso3: metto la dataInizio successiva alla dataFine
      ws.didattica.driver.assenzaclient.Date dt=new ws.didattica.driver.assenzaclient.Date();
      dt.setDate("2007-05-11T17:00:00.000+02:00");
      assenzaDaAggiungere.setDataOraInizio(dt);
      result=instance.driverAggiungiAssenza(assenzaDaAggiungere);
      // dovrebbe acchiappare l'eccezione e ritornare -2
      assertEquals(-2, result);
   }
   
   /**
    * Test of driverModificaAssenza method, of class ws.didattica.driver.AssenzaDriver.
    */
   public void testDriverModificaAssenza() {
      System.out.println("driverModificaAssenza");
      
      AssenzaDriver instance = new AssenzaDriver();
      
      assenzaDaAggiungere.setGiustificazione("AssenzaModificata");
      instance.driverModificaAssenza(assenzaDaAggiungere);
      
      ws.didattica.driver.testclient.Assenza loaded=new ws.didattica.driver.testclient.Assenza();
      
      ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
      ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
      // TODO initialize WS operation arguments here
      
      loaded = port.caricaAssenza(assenzaDaAggiungere.getId());
      
      
      assertEquals(assenzaDaAggiungere.getDataOraFine().getDate(), loaded.getDataOraFine().getDate());
      assertEquals(assenzaDaAggiungere.getDataOraInizio().getDate(),loaded.getDataOraInizio().getDate());
      assertEquals(assenzaDaAggiungere.getId(),loaded.getId());
      assertEquals(assenzaDaAggiungere.getPersonaId(),loaded.getPersonaId());
      assertEquals(assenzaDaAggiungere.getTipoAssenzaId(),loaded.getTipoAssenzaId());
      assertEquals(assenzaDaAggiungere.getGiustificazione(),loaded.getGiustificazione());
      
   }
   
   /**
    * Test of driverCancellaAssenza method, of class ws.didattica.driver.AssenzaDriver.
    */
   public void testDriverCancellaAssenza() {
      System.out.println("driverCancellaAssenza");
      
      AssenzaDriver instance = new AssenzaDriver();
      
      //cancello l'assenza appositamente creata
      instance.driverCancellaAssenza(assenzaDaCancellare.getId());
      
      // verra' sostituito da null se l' assenza e' cancellata correttamente
      ws.didattica.driver.testclient.Assenza result=new ws.didattica.driver.testclient.Assenza();
      
      // provo a caricare l'assenza che ho cancellato
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         // se e' stato cancellata dovrebbe ritornare null
         result = port.caricaAssenza(assenzaDaCancellare.getId());
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      // verifico l'eliminazione
      assertEquals(null,result);
      
      // TODO review the generated test code and remove the default call to fail.
      //fail("The test case is a prototype.");
   }
   
   /**
    * Test of driverCercaAssenza method, of class ws.didattica.driver.AssenzaDriver.
    */
   public void testDriverCercaAssenza() throws Exception {
      System.out.println("driverCercaAssenza");
      
      ws.didattica.driver.assenzaclient.ParametriRicercaAssenza parametri = null;
      AssenzaDriver instance = new AssenzaDriver();
      
      boolean exceptionThrown=false;
      
      try {
         java.util.List<Assenza> result = instance.driverCercaAssenza(parametri);
      } catch(WSDidatticaException_Exception ex){
         exceptionThrown=true;
      }
      
      assertEquals(true, exceptionThrown);
      
      parametri=new ParametriRicercaAssenza();
      java.util.List<Assenza> result = instance.driverCercaAssenza(parametri);
      
      assertNotNull(result);
      Assenza v=result.get(0);
      assertNotNull(v);
      assertEquals(1, v.getId());
      assertEquals("UnAssenza",   v.getGiustificazione());
   }
   
}
