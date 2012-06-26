/*
 * Nome file: TipiAssenzeBeanTest.java
 * Data creazione: 20 marzo 2007, 10.17
 * Info svn: $Id: TipiAssenzeBeanTest.java 686 2007-03-25 10:36:43Z roberto $
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStub;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStubService;
import nu.mine.egoless.didattica.ws.tipoassenzaclient.TipoAssenza;
import java.beans.PropertyChangeListener;

/**
 * Classe di test per testare {@link TipiAssenzeBean}.
 */
public class TipiAssenzeBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public TipiAssenzeBeanTest(String testName) {
      super(testName);
   }
   
   protected void setUp() throws Exception {
   }
   
   protected void tearDown() throws Exception {
   }
   
   /**
    * Funzione di aiuto per impostare il comportamente del Web Service.
    * @param value {@true} se {@code recuperaTipiAssenza} deve restituire {@code null};
    *              {@false} altrimenti.
    */
   public static void recuperaTipiBehavior(int value) {
      try { // Call Web Service Operation
         WSDidaStubService service = new WSDidaStubService();
         WSDidaStub port = service.getWSDidaStubPort();
         
         port.recuperaTipiAssenzaBehavior(value);
      } catch (Exception ex) {
         //Non gestiamo le eccezioni: lasciamo che il test continui
      }
   }
   
   /**
    * Test of caricaTipiAssenze method, of class nu.mine.egoless.didattica.app.bean.TipiAssenzeBean.
    */
   public void testCaricaTipiAssenze() throws Exception {
      System.out.println("caricaTipiAssenze");
      
      eventFired=false;
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      
      recuperaTipiBehavior(2);
      TipiAssenzeBean instance = new TipiAssenzeBean();
      instance.addPropertyChangeListener(TipiVotiBean.CARICAMENTO_AVVENUTO, l);
      
      instance.caricaTipiAssenze();
      assertEquals(true, eventFired);
   }
   
   /**
    * Test of ritornaNumeroDiTipiAssenze method, of class nu.mine.egoless.didattica.app.bean.TipiAssenzeBean.
    */
   public void testRitornaNumeroDiTipiAssenze() throws Exception {
      System.out.println("ritornaNumeroDiTipiAssenze");
      
      TipiAssenzeBean instance = new TipiAssenzeBean();
      int expResult;
      
      //Uso del bean appena creato
      expResult=0;
      int result = instance.ritornaNumeroDiTipiAssenze();
      assertEquals(expResult, result);
      
      //Ci vengono restituite alcuni tipi assenza
      recuperaTipiBehavior(2);
      //expResult = 2;
      instance.caricaTipiAssenze();
      result = instance.ritornaNumeroDiTipiAssenze();
      assertEquals(true, result>0);
      
      //Non ci viene restituita alcun tipo assenza
      recuperaTipiBehavior(0);
      expResult = 0;
      instance.caricaTipiAssenze();
      result = instance.ritornaNumeroDiTipiAssenze();
      assertEquals(expResult, result);
   }
   
   /**
    * Test of getTipoAssenzaAt method, of class nu.mine.egoless.didattica.app.bean.TipiAssenzeBean.
    */
   public void testGetTipoAssenzaAt() throws Exception {
      System.out.println("getTipoAssenzaAt");
      
      int posizione;
      TipiAssenzeBean instance = new TipiAssenzeBean();
      TipoAssenza result;
      
      //Uso senza caricare nulla
      posizione=0;
      result = instance.getTipoAssenzaAt(posizione);
      assertEquals(null, result);
      
      //Non ci viene restituita alcun tipo assenza
      recuperaTipiBehavior(0);
      posizione=0;
      instance.caricaTipiAssenze();
      result = instance.getTipoAssenzaAt(posizione);
      assertEquals(null, result);
      
      //Ci vengono restituiti tipi assenza
      recuperaTipiBehavior(2);
      instance.caricaTipiAssenze();
      
      posizione=0;
      result = instance.getTipoAssenzaAt(posizione);
      assertNotNull(result);
      
      posizione=instance.ritornaNumeroDiTipiAssenze();
      result = instance.getTipoAssenzaAt(posizione);
      assertNull(result);
      
      posizione=-1;
      result = instance.getTipoAssenzaAt(posizione);
      assertNull(result);
      
      posizione=instance.ritornaNumeroDiTipiAssenze()+2;
      result = instance.getTipoAssenzaAt(posizione);
      assertNull(result);
      
      posizione=1;
      result = instance.getTipoAssenzaAt(posizione);
      assertNotNull(result);
   }
   
    private static boolean esisteElemento(TipoAssenza elemento, TipiAssenzeBean bean) {
       boolean found=false;
      int i=0;
      while(!found && i<bean.ritornaNumeroDiTipiAssenze()) {
         if(bean.getTipoAssenzaAt(i)==elemento) {
            found=true;
         } else {
            i++;
         }
      }
      
      return found;
   }
   
   /**
    * Test of rimuoviTipoAssenzaAt method, of class nu.mine.egoless.didattica.app.bean.TipiAssenzeBean.
    */
   public void testRimuoviTipoAssenzaAt() throws Exception {
      System.out.println("rimuoviTipoAssenzaAt");
      
    int posizione;
      int expNumeroElementi;
      TipiAssenzeBean instance = new TipiAssenzeBean();
      
      recuperaTipiBehavior(2);
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.addPropertyChangeListener(TipiAssenzeBean.RIMOZIONE, l);
      
      //Cancelliamo un elemento con indice -1
      eventFired=false;
      posizione=-1;
      instance.caricaTipiAssenze();
      expNumeroElementi=instance.ritornaNumeroDiTipiAssenze();
      instance.rimuoviTipoAssenzaAt(posizione);
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiTipiAssenze());
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento con indice pari al numero di elementieventFired=false;
      eventFired=false;
      instance.caricaTipiAssenze();
      expNumeroElementi=instance.ritornaNumeroDiTipiAssenze();
      posizione=expNumeroElementi;
      instance.rimuoviTipoAssenzaAt(posizione);
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiTipiAssenze());
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento esistente
      eventFired=false;
      posizione=1;
      instance.caricaTipiAssenze();
      expNumeroElementi=instance.ritornaNumeroDiTipiAssenze()-1;
      TipoAssenza curr=instance.getTipoAssenzaAt(posizione);
      instance.rimuoviTipoAssenzaAt(posizione);
      
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiTipiAssenze());
      assertFalse(esisteElemento(curr, instance));
      assertEquals(true, eventFired);
   }
   
   /**
    * Test of sostituisciTipoAssenzaAt method, of class nu.mine.egoless.didattica.app.bean.TipiAssenzeBean.
    */
   public void testSostituisciTipoAssenzaAt() throws Exception {
      System.out.println("sostituisciTipoAssenzaAt");
      
      int posizione;
      TipiAssenzeBean instance = new TipiAssenzeBean();
      
      TipoAssenza rimpiazzo=new TipoAssenza();
      rimpiazzo.setDescrizione("Rimpiazzo");
      
      recuperaTipiBehavior(2);
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.addPropertyChangeListener(TipiAssenzeBean.MODIFICA, l);
      
      //Sostituiamo un elemento con indice -1
      eventFired=false;
      posizione=-1;
      instance.caricaTipiAssenze();
      instance.sostituisciTipoAssenzaAt(posizione, rimpiazzo);
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento con indice pari al numero di elementi
      eventFired=false;
      instance.caricaTipiAssenze();
      posizione=instance.ritornaNumeroDiTipiAssenze();
      instance.sostituisciTipoAssenzaAt(posizione, rimpiazzo);
      assertEquals(false, eventFired);
      
      //Sostituiamo un elemento esistente
      eventFired=false;
      posizione=1;
      instance.caricaTipiAssenze();
      TipoAssenza curr=instance.getTipoAssenzaAt(posizione);
      instance.sostituisciTipoAssenzaAt(posizione, rimpiazzo);
     
      assertSame(rimpiazzo, instance.getTipoAssenzaAt(posizione));
      assertFalse(esisteElemento(curr, instance));
      assertEquals(true, eventFired);
   }
   
}
