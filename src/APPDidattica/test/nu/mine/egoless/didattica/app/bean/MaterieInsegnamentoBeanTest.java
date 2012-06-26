/*
 * Nome file: MaterieInsegnamentoBeanTest.java
 * Data creazione: March 21, 2007, 9:31 AM
 * Info svn: $Id: MaterieInsegnamentoBeanTest.java 560 2007-03-21 13:05:53Z eric $
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStub;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStubService;
import nu.mine.egoless.didattica.ws.materiaclient.MateriaInsegnamento;

/**
 * Classe di test per testare {@link MaterieInsegnamentoBean}
 */
public class MaterieInsegnamentoBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public MaterieInsegnamentoBeanTest(String testName) {
      super(testName);
   }
   
   protected void setUp() throws Exception {
   }
   
   protected void tearDown() throws Exception {
   }
   
   /**
    * Funzione di aiuto per impostare il comportamente del Web Service.
    * @param value 0, 1, o 2.
    */
   public static void recuperaMaterieBehavior(int value) {
      try { // Call Web Service Operation
         WSDidaStubService service = new WSDidaStubService();
         WSDidaStub port = service.getWSDidaStubPort();
         port.recuperaMaterieBehavior(value);
      } catch (Exception ex) {
         //Non gestiamo le eccezioni: lasciamo che il test continui
      }
   }
   
   /**
    * Test of caricaMaterie method, of class nu.mine.egoless.didattica.app.bean.MaterieInsegnamentoBean.
    */
   public void testCaricaMaterie() throws Exception {
      System.out.println("caricaMaterie");
      
      eventFired=false;
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      
      recuperaMaterieBehavior(2);
      MaterieInsegnamentoBean instance = new MaterieInsegnamentoBean();
      instance.addPropertyChangeListener(MaterieInsegnamentoBean.CARICAMENTO_AVVENUTO, l);
      
      instance.caricaMaterie();
      assertEquals(true, eventFired);
   }
   
   /**
    * Test of ritornaNumeroDiMaterie method, of class nu.mine.egoless.didattica.app.bean.MaterieInsegnamentoBean.
    */
   public void testRitornaNumeroDiMaterie() throws Exception {
      System.out.println("ritornaNumeroDiMaterie");
      
      MaterieInsegnamentoBean instance = new MaterieInsegnamentoBean();
      int expResult;
      
      //Uso del bean appena creato
      expResult=0;
      int result = instance.ritornaNumeroDiMaterie();
      assertEquals(expResult, result);
      
      //Ci vengono restituite 2 tipi voto
      recuperaMaterieBehavior(2);
      //expResult = 2;
      instance.caricaMaterie();
      result = instance.ritornaNumeroDiMaterie();
      assertEquals(true, result>0);
      
      //Non ci viene restituita alcun tipo voto
      recuperaMaterieBehavior(0);
      expResult = 0;
      instance.caricaMaterie();
      result = instance.ritornaNumeroDiMaterie();
      assertEquals(expResult, result);
   }
   
   /**
    * Test of getMateriaAt method, of class nu.mine.egoless.didattica.app.bean.MaterieInsegnamentoBean.
    */
   public void testGetMateriaAt() throws Exception {
      System.out.println("getMateriaAt");
      
      int posizione;
      MaterieInsegnamentoBean instance = new MaterieInsegnamentoBean();
      MateriaInsegnamento result;
      
      //Uso senza caricare nulla
      posizione=0;
      result = instance.getMateriaAt(posizione);
      assertEquals(null, result);
      
      //Non ci viene restituita alcun tipo voto
      recuperaMaterieBehavior(0);
      posizione=0;
      instance.caricaMaterie();
      result = instance.getMateriaAt(posizione);
      assertEquals(null, result);
      
      //Ci vengono restituiti tipi voto
      recuperaMaterieBehavior(2);
      instance.caricaMaterie();
      
      posizione=0;
      result = instance.getMateriaAt(posizione);
      assertNotNull(result);
      
      posizione=instance.ritornaNumeroDiMaterie();
      result = instance.getMateriaAt(posizione);
      assertNull(result);
      
      posizione=-1;
      result = instance.getMateriaAt(posizione);
      assertNull(result);
      
      posizione=instance.ritornaNumeroDiMaterie()+2;
      result = instance.getMateriaAt(posizione);
      assertNull(result);
      
      posizione=1;
      result = instance.getMateriaAt(posizione);
      assertNotNull(result);
   }
   
}
