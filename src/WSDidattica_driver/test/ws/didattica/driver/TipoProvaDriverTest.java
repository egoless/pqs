/*
 * Nome file: TipoProvaDriverTest.java
 * Data creazione: 26 marzo 2007, 12.24
 * Info svn: $Id: TipoProvaDriverTest.java 792 2007-03-26 12:20:42Z eric $
 */

package ws.didattica.driver;

import junit.framework.*;
import ws.didattica.driver.testclient.WSTest;
import ws.didattica.driver.testclient.WSTestService;
import ws.didattica.driver.tipoprovaclient.TipoProva;

/**
 * Classe di test per testare {@link SimpleJUnit}.
 */
public class TipoProvaDriverTest extends TestCase {
   
   TipoProva tipoProvaDaAggiungere=new TipoProva();
   ws.didattica.driver.testclient.TipoProva tipoProvaDaCancellare=new ws.didattica.driver.testclient.TipoProva();
   
   public TipoProvaDriverTest(String testName) {
      super(testName);
   }
   
   protected void setUp() throws Exception {
      tipoProvaDaAggiungere.setNome("TipoDaAggiungere");
      tipoProvaDaAggiungere.setId(1);
      
      tipoProvaDaCancellare.setNome("TipoDaCancellare");
      tipoProvaDaCancellare.setId(99);
      
      // Salvo un TipoProva da cancellare
      WSTestService service = new WSTestService();
      WSTest port = service.getWSTestPort();
      // TODO initialize WS operation arguments here
      
      long result = port.salvaTipoProva(tipoProvaDaCancellare);
      System.out.println("Result = "+result);
   }
   
   protected void tearDown() throws Exception {
      WSTestService service = new WSTestService();
      WSTest port = service.getWSTestPort();
      
      port.eliminaTipoProva(tipoProvaDaAggiungere.getId());
      
      //Cancello dal DB il da cancellare (il test di cancellazione puo' fallire)
      ///try {
//      service = new WSTestService();
//      port = service.getWSTestPort();
      
      port.eliminaTipoProva(tipoProvaDaCancellare.getId());
//      } catch (Exception ex) {
//         //Se ci sono errori sono perché non trova l'entità
//      }
   }
   
   /**
    * Test of driverAggiungiTipoProva method, of class ws.didattica.driver.TipoProvaDriver.
    */
   public void testDriverAggiungiTipoProva() throws Exception {
      System.out.println("driverAggiungiTipoProva");
      
      TipoProvaDriver instance = new TipoProvaDriver();
      
      // il risultato atteso e' l'id
      int expResult = tipoProvaDaAggiungere.getId();
      // chiamo il driver
      long result = instance.driverAggiungiTipoProva(tipoProvaDaAggiungere);
      // test che l'id ritornato sia esatto
      assertEquals(expResult, result);
      
      //creo un tipoProva per provare a caricare quello salvato
      ws.didattica.driver.testclient.TipoProva loaded=null;
      
      // carico il TipoProva che ho appena memorizzato
      ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
      ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
      
      loaded = port.caricaTipoProva(expResult);
      
      assertNotNull(loaded);
      assertEquals(tipoProvaDaAggiungere.getNome(),loaded.getNome());
      assertEquals(tipoProvaDaAggiungere.getId(),loaded.getId());
      
      // provo a passare null
      result=instance.driverAggiungiTipoProva(null);
      // il driver dovrebbe acchiappare l'eccezione e ritornare -2
      assertEquals(result,-2);
   }
   
   /**
    * Test of driverCancellaTipoProva method, of class ws.didattica.driver.TipoProvaDriver.
    */
   public void testDriverCancellaTipoProva() throws Exception {
      System.out.println("driverCancellaTipoProva");
      
      System.out.println("driverCancellaTipoVoto");
      
      TipoProvaDriver instance = new TipoProvaDriver();
      
      // chiamo il driver che cancelli il tipoProva che ho creato
      instance.driverCancellaTipoProva(tipoProvaDaCancellare.getId());
      
      // verra' sostituito da null se il TipoProva e' cancellato correttamente
      ws.didattica.driver.testclient.TipoProva result=new ws.didattica.driver.testclient.TipoProva();
      
      // provo a caricare il tipovoto che ho cancellato
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
         
         // se e' stato cancellato dovrebbe ritornare null
         result = port.caricaTipoProva(tipoProvaDaCancellare.getId());
         
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      // verifico l'eliminazione
      assertEquals(null,result);
   }
   
   /**
    * Test of driverRecuperaTipiProva method, of class ws.didattica.driver.TipoProvaDriver.
    */
   public void testDriverRecuperaTipiProva() throws Exception {
      System.out.println("driverRecuperaTipiProva");
      
      TipoProvaDriver instance = new TipoProvaDriver();
      
      java.util.List<TipoProva> result = instance.driverRecuperaTipiProva();
      
      assertNotNull(result);
      assertEquals(1, result.size());
      TipoProva tp=result.get(0);
      assertEquals(1, tp.getId());
      assertEquals("UnTipoDiProva", tp.getNome());
   }
   
}
