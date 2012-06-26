/*
 * Nome file: NazioniBeanTest.java
 * Data creazione: March 15, 2007, 10:28 PM
 * Info svn: $Id: NazioniBeanTest.java 828 2007-03-26 19:48:13Z roberto $
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStub;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStubService;
import nu.mine.egoless.didattica.ws.nazioneclient.Nazione;
import java.beans.PropertyChangeListener;

/**
 * Classe di test per testare {@link NazioniBean}.
 */
public class NazioniBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public NazioniBeanTest(String testName) {
      super(testName);
   }
   
   protected void setUp() throws Exception {
   }
   
   protected void tearDown() throws Exception {
   }
   
   /**
    * Funzione di aiuto per impostare il comportamente del Web Service.
    * @param value {@true} se {@code recuperaNazioni} deve restituire {@code null};
    *              {@false} altrimenti.
    */
   public static void recuperaNazioniReturnsNull(boolean value) {
      try { // Call Web Service Operation
         WSDidaStubService service = new WSDidaStubService();
         WSDidaStub port = service.getWSDidaStubPort();
         port.recuperaNazioniReturnsNull(value);
      } catch (Exception ex) {
         //Non gestiamo le eccezioni: lasciamo che il test continui
      }
   }
   
   // Non ha senso testare i metodi per togliere ed aggiungere i listener dato che sono banali
   
   /**
    * Test of caricaNazioni method, of class nu.mine.egoless.didattica.app.bean.NazioniBean.
    */
   public void testCaricaNazioni() throws Exception {
      System.out.println("caricaNazioni");
      
      eventFired=false;
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      
      NazioniBean instance = new NazioniBean();
      instance.addPropertyChangeListener(NazioniBean.CARICAMENTO_AVVENUTO, l);
      
      instance.caricaNazioni();
      assertEquals(true, eventFired);
   }
   
   /**
    * Test of ritornaNumeroDiNazioni method, of class nu.mine.egoless.didattica.app.bean.NazioniBean.
    */
   public void testRitornaNumeroDiNazioni() throws Exception {
      System.out.println("ritornaNumeroDiNazioni");
      
      NazioniBean instance = new NazioniBean();
      
      //Uso del bean appena creato
      int expResult = 0;
      int result = instance.ritornaNumeroDiNazioni();
      assertEquals(expResult, result);
      
      //Ci vengono restituite n nazioni
      recuperaNazioniReturnsNull(false);
      //expResult = 2;
      instance.caricaNazioni();
      result = instance.ritornaNumeroDiNazioni();
      //assertEquals(expResult, result);
      assertTrue(result>0);
      
      //Non ci viene restituita alcuna nazione
      recuperaNazioniReturnsNull(true);
      expResult = 0;
      instance.caricaNazioni();
      result = instance.ritornaNumeroDiNazioni();
      assertEquals(expResult, result);
   }
   
   /**
    * Test of getNazioneAt method, of class nu.mine.egoless.didattica.app.bean.NazioniBean.
    */
   public void testGetNazioneAt() throws Exception {
      System.out.println("getNazioneAt");
      
      int posizione;
      NazioniBean instance = new NazioniBean();
      Nazione result;
      
      //Uso senza caricare nulla
      posizione=0;
      result = instance.getNazioneAt(posizione);
      assertEquals(null, result);
      
      //Non ci viene restituita alcuna nazione
      recuperaNazioniReturnsNull(true);
      posizione=0;
      instance.caricaNazioni();
      result = instance.getNazioneAt(posizione);
      assertEquals(null, result);
      
      //Ci vengono restituite due nazioni
      recuperaNazioniReturnsNull(false);
      instance.caricaNazioni();
      
      posizione=0;
      result = instance.getNazioneAt(posizione);
      assertNotNull(result);
      
      posizione=instance.ritornaNumeroDiNazioni();
      result = instance.getNazioneAt(posizione);
      assertNull(result);
      
      posizione=-1;
      result = instance.getNazioneAt(posizione);
      assertNull(result);
      
      posizione=instance.ritornaNumeroDiNazioni()+2;
      result = instance.getNazioneAt(posizione);
      assertNull(result);
      
      posizione=1;
      result = instance.getNazioneAt(posizione);
      assertNotNull(result);
   }
   
}
