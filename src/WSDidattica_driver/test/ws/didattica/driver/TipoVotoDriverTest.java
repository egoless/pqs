/*
 * TipoVotoDriverTest.java
 * JUnit based test
 *
 * Created on 17 marzo 2007, 16.51
 */

package ws.didattica.driver;

import junit.framework.*;
import ws.didattica.driver.tipovotoclient.TipoVoto;


/**
 *
 * @author Roberto
 */
public class TipoVotoDriverTest extends TestCase {
   
   ws.didattica.driver.tipovotoclient.TipoVoto tipoVotoDaAggiungere=new ws.didattica.driver.tipovotoclient.TipoVoto();
   ws.didattica.driver.testclient.TipoVoto tipoVotoDaCancellare=new ws.didattica.driver.testclient.TipoVoto();
   
   public TipoVotoDriverTest(String testName) {
      super(testName);
   }
   
   protected void setUp() throws Exception {
      tipoVotoDaAggiungere.setNome("TipoDaAggiungere");
      tipoVotoDaAggiungere.setId(1);
      tipoVotoDaCancellare.setNome("TipoDaCancellare");
      tipoVotoDaCancellare.setId(99);
      
      // Salvo un TipoVoto da cancellare
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         // TODO initialize WS operation arguments here
         
         long result = port.salvaTipoVoto(tipoVotoDaCancellare);
         System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      
      
   }
   
   protected void tearDown() throws Exception {
      //Cancello dal DB il voto inserito
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         port.eliminaTipoVoto(tipoVotoDaAggiungere.getId());
      } catch (Exception ex) {

      }
      
      //Cancello dal DB il da cancellare (il test di cancellazione puo' fallire)
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         port.eliminaTipoVoto(tipoVotoDaCancellare.getId());
      } catch (Exception ex) {

      }
      
   }
   
   /**
    * Test of driverAggiungiTipoVoto method, of class ws.didattica.driver.TipoVotoDriver.
    */
   public void testDriverAggiungiTipoVoto() throws Exception {
      System.out.println("driverAggiungiTipoVoto");
      
      
      TipoVotoDriver instance = new TipoVotoDriver();
      
      // il risultato atteso e' l'id
      int expResult = tipoVotoDaAggiungere.getId();
      // chiamo il driver
      long result = instance.driverAggiungiTipoVoto(tipoVotoDaAggiungere);
      // test che l'id ritornato sia esatto
      assertEquals(expResult, result);
      
      //creo un tipoVoto per provare a caricare quello salvato
      ws.didattica.driver.testclient.TipoVoto loaded=null;
      
      // carico il TipoVoto che ho appena memorizzato
      ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
      ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();

      loaded = port.caricaTipoVoto(expResult);

      assertNotNull(loaded);
      assertEquals(tipoVotoDaAggiungere.getNome(),loaded.getNome());
      assertEquals(tipoVotoDaAggiungere.getId(),loaded.getId());
      
      // provo a passare null
      result=instance.driverAggiungiTipoVoto(null);
      // il driver dovrebbe acchiappare l'eccezione e ritornare -2
      assertEquals(result,-2);
   }
   
   /**
    * Test of driverCancellaTipoVoto method, of class ws.didattica.driver.TipoVotoDriver.
    */
   public void testDriverCancellaTipoVoto() {
      System.out.println("driverCancellaTipoVoto");
      
      TipoVotoDriver instance = new TipoVotoDriver();
      
      // chiamo il driver che cancelli il tipoVoto che ho creato
      instance.driverCancellaTipoVoto(tipoVotoDaCancellare.getId());
      
      // verra' sostituito da null se il TipoVoto e' cancellato correttamente
      ws.didattica.driver.testclient.TipoVoto result=new ws.didattica.driver.testclient.TipoVoto();
      
      // provo a caricare il tipovoto che ho cancellato
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         // se e' stato cancellato dovrebbe ritornare null
         result = port.caricaTipoVoto(tipoVotoDaCancellare.getId());
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      // verifico l'eliminazione
      assertEquals(null,result);
   }
   
   /**
    * Test of driverRecuperaTipiVoto method, of class ws.didattica.driver.TipoVotoDriver.
    */
   public void testDriverRecuperaTipiVoto() throws Exception {
      System.out.println("driverRecuperaTipiVoto");
      
      TipoVotoDriver instance = new TipoVotoDriver();
      
      java.util.List<ws.didattica.driver.tipovotoclient.TipoVoto> result = instance.driverRecuperaTipiVoto();
      
      assertNotNull(result);
      assertEquals(1, result.size());
      TipoVoto tp=result.get(0);
      assertEquals(1, tp.getId());
      assertEquals("5", tp.getValore());
      assertEquals("UnTipoDiVoto", tp.getNome());
   }
   
   
   
}
