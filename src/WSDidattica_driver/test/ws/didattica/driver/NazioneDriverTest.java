/*
 * Nome file: NazioneDriverTest.java
 * Data creazione: 26 marzo 2007, 7.28
 * Info svn: $Id: NazioneDriverTest.java 779 2007-03-26 08:47:17Z eric $
 */

package ws.didattica.driver;

import junit.framework.*;
import java.util.List;
import ws.didattica.driver.nazioneclient.Nazione;
import ws.didattica.driver.nazioneclient.WSNazione;
import ws.didattica.driver.nazioneclient.WSNazioneService;

/**
 * Classe di test per testare {@link NazioneDriver}.
 */
public class NazioneDriverTest extends TestCase {
   
   public NazioneDriverTest(String testName) {
      super(testName);
   }
   
   protected void setUp() throws Exception {
   }
   
   protected void tearDown() throws Exception {
   }
   
   public static void recuperaNazioniBehavior(boolean returnNull) {
      try { // Call Web Service Operation
         ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
         ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();

         port.recuperaNazioniBehavior(returnNull);
      } catch (Exception ex) {
         
      }
   }
   
   /**
    * Test of driverRecuperaNazioni method, of class ws.didattica.driver.NazioneDriver.
    */
   public void testDriverRecuperaNazioni() {
      System.out.println("driverRecuperaNazioni");
      
      
      /** Dico a WSTest di restituire null*/
      recuperaNazioniBehavior(true);
      
      NazioneDriver instance = new NazioneDriver();
      
      java.util.List<Nazione> expResult = new java.util.LinkedList<Nazione>();
      java.util.List<Nazione> result = instance.driverRecuperaNazioni();
      assertEquals(expResult, result);
      
      /** Dico a WSTest di restituire qualcosa*/
      recuperaNazioniBehavior(false);
      
      Nazione n=new Nazione();
      n.setNome("Pippo");
      n.setId(95);
      expResult = new java.util.LinkedList<Nazione>();
      expResult.add(n);
      result = instance.driverRecuperaNazioni();
      assertEquals(expResult.get(0).getNome(), result.get(0).getNome());
      assertEquals(expResult.get(0).getId(),result.get(0).getId());
      System.out.println(result.get(0).getNome());
   } 
}
