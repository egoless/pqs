/*
 * Nome file: TipiVotiBeanTest.java
 * Data creazione: 19 marzo 2007, 9.53
 * Info svn: $Id: TipiVotiBeanTest.java 787 2007-03-26 10:56:40Z roberto $
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStub;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStubService;
import nu.mine.egoless.didattica.ws.tipovotoclient.TipoVoto;
import java.beans.PropertyChangeListener;

/**
 * Classe di test per testare {@link TipiVotiBean}.
 */
public class TipiVotiBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public TipiVotiBeanTest(String testName) {
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
   public static void recuperaTipiVotoBehavior(int value) {
      try { // Call Web Service Operation
         WSDidaStubService service = new WSDidaStubService();
         WSDidaStub port = service.getWSDidaStubPort();
         port.recuperaTipiVotoBehavior(value);
      } catch (Exception ex) {
         //Non gestiamo le eccezioni: lasciamo che il test continui
      }
   }
   
   /**
    * Test of caricaTipiVoti method, of class nu.mine.egoless.didattica.app.bean.TipiVotiBean.
    */
   public void testCaricaTipiVoti() throws Exception {
      System.out.println("caricaTipiVoti");
      
      eventFired=false;
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      
      recuperaTipiVotoBehavior(2);
      TipiVotiBean instance = new TipiVotiBean();
      instance.addPropertyChangeListener(TipiVotiBean.CARICAMENTO_AVVENUTO, l);
      
      instance.caricaTipiVoti();
      assertEquals(true, eventFired);
   }
   
   /**
    * Test of ritornaNumeroDiTipiVoti method, of class nu.mine.egoless.didattica.app.bean.TipiVotiBean.
    */
   public void testRitornaNumeroDiTipiVoti() throws Exception {
      System.out.println("ritornaNumeroDiTipiVoti");
      
      TipiVotiBean instance = new TipiVotiBean();
      int expResult;
      
      //Uso del bean appena creato
      expResult=0;
      int result = instance.ritornaNumeroDiTipiVoti();
      assertEquals(expResult, result);
      
      //Ci vengono restituite 2 tipi voto
      recuperaTipiVotoBehavior(2);
      //expResult = 2;
      instance.caricaTipiVoti();
      result = instance.ritornaNumeroDiTipiVoti();
      assertEquals(true, result>0);
      
      //Non ci viene restituita alcun tipo voto
      recuperaTipiVotoBehavior(0);
      expResult = 0;
      instance.caricaTipiVoti();
      result = instance.ritornaNumeroDiTipiVoti();
      assertEquals(expResult, result);
   }
   
   /**
    * Test of getTipoVotoAt method, of class nu.mine.egoless.didattica.app.bean.TipiVotiBean.
    */
   public void testGetTipoVotoAt() throws Exception {
      System.out.println("getTipoVotoAt");
      
      int posizione;
      TipiVotiBean instance = new TipiVotiBean();
      TipoVoto result;
      
      //Uso senza caricare nulla
      posizione=0;
      result = instance.getTipoVotoAt(posizione);
      assertEquals(null, result);
      
      //Non ci viene restituita alcun tipo voto
      recuperaTipiVotoBehavior(0);
      posizione=0;
      instance.caricaTipiVoti();
      result = instance.getTipoVotoAt(posizione);
      assertEquals(null, result);
      
      //Ci vengono restituiti tipi voto
      recuperaTipiVotoBehavior(2);
      instance.caricaTipiVoti();
      
      posizione=0;
      result = instance.getTipoVotoAt(posizione);
      assertNotNull(result);
      
      posizione=instance.ritornaNumeroDiTipiVoti();
      result = instance.getTipoVotoAt(posizione);
      assertNull(result);
      
      posizione=-1;
      result = instance.getTipoVotoAt(posizione);
      assertNull(result);
      
      posizione=instance.ritornaNumeroDiTipiVoti()+2;
      result = instance.getTipoVotoAt(posizione);
      assertNull(result);
      
      posizione=1;
      result = instance.getTipoVotoAt(posizione);
      assertNotNull(result);
   }
   
   private static boolean esisteElemento(TipoVoto elemento, TipiVotiBean bean) {
       boolean found=false;
      int i=0;
      while(!found && i<bean.ritornaNumeroDiTipiVoti()) {
         if(bean.getTipoVotoAt(i)==elemento) {
            found=true;
         } else {
            i++;
         }
      }
      
      return found;
   }
   
   /**
    * Test of rimuoviTipoVotoAt method, of class nu.mine.egoless.didattica.app.bean.TipiVotiBean.
    */
   public void testRimuoviTipoVotoAt() throws Exception {
      System.out.println("rimuoviTipoVotoAt");
      int posizione;
      int expNumeroElementi;
      TipiVotiBean instance = new TipiVotiBean();
      
      recuperaTipiVotoBehavior(2);
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.addPropertyChangeListener(TipiVotiBean.RIMOZIONE, l);
      
      //Cancelliamo un elemento con indice -1
      eventFired=false;
      posizione=-1;
      instance.caricaTipiVoti();
      expNumeroElementi=instance.ritornaNumeroDiTipiVoti();
      instance.rimuoviTipoVotoAt(posizione);
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiTipiVoti());
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento con indice pari al numero di elementi
      eventFired=false;
      instance.caricaTipiVoti();
      expNumeroElementi=instance.ritornaNumeroDiTipiVoti();
      posizione=expNumeroElementi;
      instance.rimuoviTipoVotoAt(posizione);
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiTipiVoti());
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento esistente
      eventFired=false;
      posizione=1;
      instance.caricaTipiVoti();
      expNumeroElementi=instance.ritornaNumeroDiTipiVoti()-1;
      TipoVoto curr=instance.getTipoVotoAt(posizione);
      instance.rimuoviTipoVotoAt(posizione);
      
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiTipiVoti());
      assertFalse(esisteElemento(curr, instance));
      assertEquals(true, eventFired);
   }
   
   /**
    * Test of sostituisciTipoVotoAt method, of class nu.mine.egoless.didattica.app.bean.TipiVotiBean.
    */
   public void testSostituisciTipoVotoAt() throws Exception {
      System.out.println("sostituisciTipoVotoAt");
      
      int posizione;
      TipiVotiBean instance = new TipiVotiBean();
      
      TipoVoto rimpiazzo=new TipoVoto();
      rimpiazzo.setNome("Rimpiazzo");
      
      recuperaTipiVotoBehavior(2);
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.addPropertyChangeListener(TipiVotiBean.MODIFICA, l);
      
      //Sostituiamo un elemento con indice -1
      eventFired=false;
      posizione=-1;
      instance.caricaTipiVoti();
      instance.sostituisciTipoVotoAt(posizione, rimpiazzo);
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento con indice pari al numero di elementi
      eventFired=false;
      instance.caricaTipiVoti();
      posizione=instance.ritornaNumeroDiTipiVoti();
      instance.sostituisciTipoVotoAt(posizione, rimpiazzo);
      assertEquals(false, eventFired);
      
      //Sostituiamo un elemento esistente
      eventFired=false;
      posizione=1;
      instance.caricaTipiVoti();
      TipoVoto curr=instance.getTipoVotoAt(posizione);
      instance.sostituisciTipoVotoAt(posizione, rimpiazzo);
     
      assertSame(rimpiazzo, instance.getTipoVotoAt(posizione));
      assertFalse(esisteElemento(curr, instance));
      assertEquals(true, eventFired);
   }
   
}
