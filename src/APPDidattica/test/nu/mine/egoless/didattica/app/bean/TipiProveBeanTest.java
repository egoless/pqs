/*
 * TipiProveBeanTest.java
 * JUnit based test
 *
 * Created on 26 marzo 2007, 15.04
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStub;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStubService;
import nu.mine.egoless.didattica.ws.tipoprovaclient.TipoProva;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.tipoprovaclient.WSTipoProvaService;
import nu.mine.egoless.didattica.ws.tipoprovaclient.WSTipoProva;
import nu.mine.egoless.didattica.ws.tipoprovaclient.WSDidatticaException_Exception;
import java.util.List;
import java.net.PortUnreachableException;

/**
 *
 * @author Roberto
 */
public class TipiProveBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public TipiProveBeanTest(String testName) {
      super(testName);
   }
   
   protected void setUp() throws Exception {
   }
   
   protected void tearDown() throws Exception {
   }
   
   /**
    * Funzione di aiuto per impostare il comportamente del Web Service.
    * @param value 0 se il WS non deve restituire niente, 2 se il WS
    *              restituisce dati fittizi, 1 se il WS restituisce cio'
    *              che c'e' nel DB
    */
   public static void recuperaTipiProvaBehavior(int value) {
      try { // Call Web Service Operation
         WSDidaStubService service = new WSDidaStubService();
         WSDidaStub port = service.getWSDidaStubPort();
         port.recuperaTipiProvaBehavior(value);
      } catch (Exception ex) {
         //Non gestiamo le eccezioni: lasciamo che il test continui
      }
   }
   
   /**
    * Test of caricaTipiProve method, of class nu.mine.egoless.didattica.app.bean.TipiProveBean.
    */
   public void testCaricaTipiProve() throws Exception {
      System.out.println("caricaTipiProve");
      
      eventFired=false;
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      
      recuperaTipiProvaBehavior(2);
      TipiProveBean instance = new TipiProveBean();
      instance.addPropertyChangeListener(TipiProveBean.CARICAMENTO_AVVENUTO, l);
      
      instance.caricaTipiProve();
      assertEquals(true, eventFired);
   }
   
   /**
    * Test of ritornaNumeroDiTipiProve method, of class nu.mine.egoless.didattica.app.bean.TipiProveBean.
    */
   public void testRitornaNumeroDiTipiProve() throws Exception {
      System.out.println("ritornaNumeroDiTipiProve");
      
      TipiProveBean instance = new TipiProveBean();
      int expResult;
      
      //Uso del bean appena creato
      expResult=0;
      int result = instance.ritornaNumeroDiTipiProve();
      assertEquals(expResult, result);
      
      //Ci vengono restituite 2 tipi voto
      recuperaTipiProvaBehavior(2);
      //expResult = 2;
      instance.caricaTipiProve();
      result = instance.ritornaNumeroDiTipiProve();
      assertEquals(true, result>0);
      
      //Non ci viene restituita alcun tipo voto
      recuperaTipiProvaBehavior(0);
      expResult = 0;
      instance.caricaTipiProve();
      result = instance.ritornaNumeroDiTipiProve();
      assertEquals(expResult, result);
   }
   
   /**
    * Test of getTipoProvaAt method, of class nu.mine.egoless.didattica.app.bean.TipiProveBean.
    */
   public void testGetTipoProvaAt() throws Exception {
      System.out.println("getTipoProvaAt");
      
      int posizione;
      TipiProveBean instance = new TipiProveBean();
      TipoProva result;
      
      //Uso senza caricare nulla
      posizione=0;
      result = instance.getTipoProvaAt(posizione);
      assertEquals(null, result);
      
      //Non ci viene restituita alcun tipo voto
      recuperaTipiProvaBehavior(0);
      posizione=0;
      instance.caricaTipiProve();
      result = instance.getTipoProvaAt(posizione);
      assertEquals(null, result);
      
      //Ci vengono restituiti tipi voto
      recuperaTipiProvaBehavior(2);
      instance.caricaTipiProve();
      
      posizione=0;
      result = instance.getTipoProvaAt(posizione);
      assertNotNull(result);
      
      posizione=instance.ritornaNumeroDiTipiProve();
      result = instance.getTipoProvaAt(posizione);
      assertNull(result);
      
      posizione=-1;
      result = instance.getTipoProvaAt(posizione);
      assertNull(result);
      
      posizione=instance.ritornaNumeroDiTipiProve()+2;
      result = instance.getTipoProvaAt(posizione);
      assertNull(result);
      
      posizione=1;
      result = instance.getTipoProvaAt(posizione);
      assertNotNull(result);
   }
   
   private static boolean esisteElemento(TipoProva elemento, TipiProveBean bean) {
       boolean found=false;
      int i=0;
      while(!found && i<bean.ritornaNumeroDiTipiProve()) {
         if(bean.getTipoProvaAt(i)==elemento) {
            found=true;
         } else {
            i++;
         }
      }
      
      return found;
   }
   
   /**
    * Test of rimuoviTipoProvaAt method, of class nu.mine.egoless.didattica.app.bean.TipiProveBean.
    */
   public void testRimuoviTipoProvaAt() throws Exception {
      System.out.println("rimuoviTipoProvaAt");
      int posizione;
      int expNumeroElementi;
      TipiProveBean instance = new TipiProveBean();
      
      recuperaTipiProvaBehavior(2);
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.addPropertyChangeListener(TipiProveBean.RIMOZIONE, l);
      
      //Cancelliamo un elemento con indice -1
      eventFired=false;
      posizione=-1;
      instance.caricaTipiProve();
      expNumeroElementi=instance.ritornaNumeroDiTipiProve();
      instance.rimuoviTipoProvaAt(posizione);
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiTipiProve());
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento con indice pari al numero di elementi
      eventFired=false;
      instance.caricaTipiProve();
      expNumeroElementi=instance.ritornaNumeroDiTipiProve();
      posizione=expNumeroElementi;
      instance.rimuoviTipoProvaAt(posizione);
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiTipiProve());
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento esistente
      eventFired=false;
      posizione=1;
      instance.caricaTipiProve();
      expNumeroElementi=instance.ritornaNumeroDiTipiProve()-1;
      TipoProva curr=instance.getTipoProvaAt(posizione);
      instance.rimuoviTipoProvaAt(posizione);
      
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiTipiProve());
      assertFalse(esisteElemento(curr, instance));
      assertEquals(true, eventFired);
   }
   
   /**
    * Test of sostituisciTipoProvaAt method, of class nu.mine.egoless.didattica.app.bean.TipiProveBean.
    */
   public void testSostituisciTipoProvaAt() throws Exception {
      System.out.println("sostituisciTipoProvaAt");
      
      int posizione;
      TipiProveBean instance = new TipiProveBean();
      
      TipoProva rimpiazzo=new TipoProva();
      rimpiazzo.setNome("Rimpiazzo");
      
      recuperaTipiProvaBehavior(2);
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.addPropertyChangeListener(TipiProveBean.MODIFICA, l);
      
      //Sostituiamo un elemento con indice -1
      eventFired=false;
      posizione=-1;
      instance.caricaTipiProve();
      instance.sostituisciTipoProvaAt(posizione, rimpiazzo);
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento con indice pari al numero di elementi
      eventFired=false;
      instance.caricaTipiProve();
      posizione=instance.ritornaNumeroDiTipiProve();
      instance.sostituisciTipoProvaAt(posizione, rimpiazzo);
      assertEquals(false, eventFired);
      
      //Sostituiamo un elemento esistente
      eventFired=false;
      posizione=1;
      instance.caricaTipiProve();
      TipoProva curr=instance.getTipoProvaAt(posizione);
      instance.sostituisciTipoProvaAt(posizione, rimpiazzo);
     
      assertSame(rimpiazzo, instance.getTipoProvaAt(posizione));
      assertFalse(esisteElemento(curr, instance));
      assertEquals(true, eventFired);
   }
   
}
