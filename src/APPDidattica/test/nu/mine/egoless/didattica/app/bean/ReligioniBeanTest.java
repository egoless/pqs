/*
 * Nome file: ReligioniBeanTest.java
 * Data creazione: March 16, 2007, 9:20 AM
 * Info svn: $Id: ReligioniBeanTest.java 828 2007-03-26 19:48:13Z roberto $
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStub;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStubService;
import nu.mine.egoless.didattica.ws.religioneclient.Religione;
import java.beans.PropertyChangeListener;

/**
 * Classe di test per testare {@link ReligioniBean}.
 */
public class ReligioniBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public ReligioniBeanTest(String testName) {
      super(testName);
   }
   
   protected void setUp() throws Exception {
   }
   
   protected void tearDown() throws Exception {
   }
   
   /**
    * Funzione di aiuto per impostare il comportamente del Web Service.
    * @param value {@true} se {@code recuperaReligioni} deve restituire {@code null};
    *              {@false} altrimenti.
    */
   public static void recuperaReligioniReturnsNull(boolean value) {
      try { // Call Web Service Operation
         WSDidaStubService service = new WSDidaStubService();
         WSDidaStub port = service.getWSDidaStubPort();
         port.recuperaReligioniReturnsNull(value);
      } catch (Exception ex) {
         //Non gestiamo le eccezioni: lasciamo che il test continui
      }
   }
   
   /**
    * Test of caricaReligioni method, of class nu.mine.egoless.didattica.app.bean.ReligioniBean.
    */
   public void testCaricaReligioni() throws Exception {
      System.out.println("caricaReligioni");
      
      eventFired=false;
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      
      ReligioniBean instance = new ReligioniBean();
      instance.addPropertyChangeListener(ReligioniBean.CARICAMENTO_AVVENUTO, l);
      
      instance.caricaReligioni();
      assertEquals(true, eventFired);
   }
   
   /**
    * Test of ritornaNumeroDiReligioni method, of class nu.mine.egoless.didattica.app.bean.ReligioniBean.
    */
   public void testRitornaNumeroDiReligioni() throws Exception {
      System.out.println("ritornaNumeroDiReligioni");
      
      ReligioniBean instance = new ReligioniBean();
      
      //Uso senza caricare nulla
      int expResult = 0;
      int result = instance.ritornaNumeroDiReligioni();
      assertEquals(expResult, result);
      
      //Ci vengono restituite 2 religioni
      recuperaReligioniReturnsNull(false);
      //expResult = 2;
      instance.caricaReligioni();
      result = instance.ritornaNumeroDiReligioni();
      //assertEquals(expResult, result);
      assertTrue(result>0);
      
      //Non ci viene restituita alcuna religione
      recuperaReligioniReturnsNull(true);
      expResult = 0;
      instance.caricaReligioni();
      result = instance.ritornaNumeroDiReligioni();
      assertEquals(expResult, result);
   }
   
   /**
    * Test of getReligioneAt method, of class nu.mine.egoless.didattica.app.bean.ReligioniBean.
    */
   public void testGetReligioneAt() throws Exception {
      System.out.println("getReligioneAt");
      
      int posizione;
      ReligioniBean instance = new ReligioniBean();
      Religione result;
      
      //Uso senza caricare nulla
      posizione=0;
      result = instance.getReligioneAt(posizione);
      assertEquals(null, result);
      
      //Non ci viene restituita alcuna nazione
      recuperaReligioniReturnsNull(true);
      posizione=0;
      instance.caricaReligioni();
      result = instance.getReligioneAt(posizione);
      assertEquals(null, result);
      
      //Ci vengono restituite due nazioni
      recuperaReligioniReturnsNull(false);
      instance.caricaReligioni();
      
      posizione=0;
      result = instance.getReligioneAt(posizione);
      assertNotNull(result);
      
      posizione=instance.ritornaNumeroDiReligioni();
      result = instance.getReligioneAt(posizione);
      assertNull(result);
      
      posizione=-1;
      result = instance.getReligioneAt(posizione);
      assertNull(result);
      
      posizione=instance.ritornaNumeroDiReligioni()+2;
      result = instance.getReligioneAt(posizione);
      assertNull(result);
      
      posizione=1;
      result = instance.getReligioneAt(posizione);
      assertNotNull(result);
   }
   
}
