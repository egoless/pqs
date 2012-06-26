/*
 * Nome file: ReligioneDriverTest.java
 * Data creazione: 26 marzo 2007, 7.42
 * Info svn: $Id: ReligioneDriverTest.java 776 2007-03-26 08:21:20Z eric $
 */

package ws.didattica.driver;

import junit.framework.*;
import java.util.List;
import ws.didattica.driver.religioneclient.Religione;
import ws.didattica.driver.religioneclient.WSReligione;
import ws.didattica.driver.religioneclient.WSReligioneService;

/**
 * Classe di test per testare {@link SimpleJUnit}.
 */
public class ReligioneDriverTest extends TestCase {
   
   public ReligioneDriverTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
   }

   protected void tearDown() throws Exception {
   }
   
   public static void recuperaReligioniBehavior(boolean returnNull) {
       /** Dico a WSTest di restituire null*/
        try { // Call Web Service Operation
            ws.didattica.driver.testclient.WSTestService service = new ws.didattica.driver.testclient.WSTestService();
            ws.didattica.driver.testclient.WSTest port = service.getWSTestPort();
            // TODO initialize WS operation arguments here
            
            port.recuperaReligioniBehavior(returnNull);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
   }

   /**
    * Test of driverRecuperaReligioni method, of class ws.didattica.driver.ReligioneDriver.
    */
   public void testDriverRecuperaReligioni() {
      System.out.println("driverRecuperaReligioni");
        
        
        /** Dico a WSTest di restituire null*/
        recuperaReligioniBehavior(true);
        
        ReligioneDriver instance = new ReligioneDriver();
        
        java.util.List<Religione> expResult = new java.util.LinkedList<Religione>();
        java.util.List<Religione> result = instance.driverRecuperaReligioni();
        assertEquals(expResult, result);
        
        /** Dico a WSTest di restituire qualcosa*/
         recuperaReligioniBehavior(false);
               
        Religione n=new Religione();
        n.setNome("Mormone");
        n.setId(12);
        expResult = new java.util.LinkedList<Religione>();
        expResult.add(n);
        result = instance.driverRecuperaReligioni();
        assertEquals(expResult.get(0).getNome(), result.get(0).getNome());
        assertEquals(expResult.get(0).getId(),result.get(0).getId());
        System.out.println(result.get(0).getNome());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
   }
   
}
