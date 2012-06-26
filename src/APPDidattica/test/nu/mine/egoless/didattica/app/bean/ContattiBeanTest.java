/*
 * ContattiBeanTest.java
 * JUnit based test
 *
 * Created on 25 marzo 2007, 8.59
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.contattoclient.Contatto;
import nu.mine.egoless.didattica.ws.contattoclient.ParametriRicercaContatto;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.contattoclient.WSContatto;
import nu.mine.egoless.didattica.ws.contattoclient.WSContattoService;
import nu.mine.egoless.didattica.ws.contattoclient.WSDidatticaException_Exception;
import java.util.List;
import java.net.PortUnreachableException;

/**
 *
 * @author Roberto
 */
public class ContattiBeanTest extends TestCase {
   
   private boolean eventFired;
   ParametriRicercaContatto insiemeParametri;
   
   public ContattiBeanTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
      insiemeParametri=new ParametriRicercaContatto();
   }

   protected void tearDown() throws Exception {
   }

   /**
    * Test of caricaContatti method, of class nu.mine.egoless.didattica.app.bean.ContattiBean.
    */
   public void testCaricaContatti() throws Exception {
      System.out.println("caricaContatti");
      
      
      ContattiBean instance = new ContattiBean();
      
      
      ContattoBeanTest.cercaContattoBehavior(2);
            
      // passo null come parametro
      insiemeParametri=null;
      boolean thrown=false;
      // dovrebbe rilanciare l'eccezione
      try {instance.caricaContatti(insiemeParametri);}
      catch (Exception ex){
         if (ex.getMessage().equals("Non sono stati passati i parametri di ricerca.")) thrown=true;
      }
      assertTrue(thrown);      
      
      eventFired=false;
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      
      
      instance.addPropertyChangeListener(ContattiBean.CARICAMENTO_AVVENUTO, l);
      insiemeParametri=new ParametriRicercaContatto();
      instance.caricaContatti(insiemeParametri);
      assertEquals(true, eventFired);
      
   }

   /**
    * Test of ritornaNumeroDiContatti method, of class nu.mine.egoless.didattica.app.bean.ContattiBean.
    */
   public void testRitornaNumeroDiContatti() throws Exception {
      System.out.println("ritornaNumeroDiContatti");
      
      ContattiBean instance = new ContattiBean();
      
      //Uso del bean appena creato
      int expResult = 0;
      int result = instance.ritornaNumeroDiContatti();
      assertEquals(expResult, result);
      
      
      //Ci vengono restituiti risultati
      ContattoBeanTest.cercaContattoBehavior(2);
      expResult = 3;
      instance.caricaContatti(insiemeParametri);
      result = instance.ritornaNumeroDiContatti();
      assertEquals(expResult, result);
      
      //Non vengono restituiti risultati
      ContattoBeanTest.cercaContattoBehavior(0);
      expResult = 0;
      instance.caricaContatti(insiemeParametri);
      result = instance.ritornaNumeroDiContatti();
      assertEquals(expResult, result);
    
     
   }

   /**
    * Test of getContattoAt method, of class nu.mine.egoless.didattica.app.bean.ContattiBean.
    */
   public void testGetContattoAt() throws Exception{
      System.out.println("getContattoAt");
      
      int posizione = 0;
      ContattiBean instance = new ContattiBean();
      
      Contatto result;
      
      //Uso senza caricare nulla
      result = instance.getContattoAt(posizione);
      assertEquals(null, result);
      
      //Non ci viene restituito niente
      ContattoBeanTest.cercaContattoBehavior(0);
      posizione=0;
      instance.caricaContatti(insiemeParametri);
      result = instance.getContattoAt(posizione);
      assertEquals(null, result);
      
      //Ci vengono restituiti 3 risultati
      ContattoBeanTest.cercaContattoBehavior(2);
      instance.caricaContatti(insiemeParametri);
      
      posizione=0;
      result = instance.getContattoAt(posizione);
      assertNotNull(result);
      
      posizione=instance.ritornaNumeroDiContatti();
      result = instance.getContattoAt(posizione);
      assertNull(result);
      
      posizione=-1;
      result = instance.getContattoAt(posizione);
      assertNull(result);
      
      posizione=instance.ritornaNumeroDiContatti()+2;
      result = instance.getContattoAt(posizione);
      assertNull(result);
      
      posizione=1;
      result = instance.getContattoAt(posizione);
      assertNotNull(result);
   }

   /**
    * Test of rimuoviContattoAt method, of class nu.mine.egoless.didattica.app.bean.ContattiBean.
    */
   public void testRimuoviContattoAt() throws Exception{
      System.out.println("rimuoviContattoAt");
      
      int posizione = 0;
      int expNumeroElementi;
      ContattiBean instance = new ContattiBean();
            
      ContattoBeanTest.cercaContattoBehavior(2);
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.addPropertyChangeListener(ContattiBean.RIMOZIONE, l);
      
      //Cancelliamo un elemento con indice -1
      eventFired=false;
      posizione=-1;
      instance.caricaContatti(insiemeParametri);
      expNumeroElementi=instance.ritornaNumeroDiContatti();
      instance.rimuoviContattoAt(posizione);
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiContatti());
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento con indice pari al numero di elementi
      eventFired=false;
      instance.caricaContatti(insiemeParametri);
      expNumeroElementi=instance.ritornaNumeroDiContatti();
      posizione=expNumeroElementi;
      instance.rimuoviContattoAt(posizione);
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiContatti());
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento esistente
      eventFired=false;
      posizione=1;
      instance.caricaContatti(insiemeParametri);
      expNumeroElementi=instance.ritornaNumeroDiContatti()-1;
      Contatto curr=instance.getContattoAt(posizione);
      instance.rimuoviContattoAt(posizione);
      
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiContatti());
      assertFalse(esisteElemento(curr, instance));
      assertEquals(true, eventFired);
      
 
   }

      private static boolean esisteElemento(Contatto elemento, ContattiBean bean) {
         boolean found=false;
         int i=0;
         while(!found && i<bean.ritornaNumeroDiContatti()) {
            if(bean.getContattoAt(i)==elemento) {
               found=true;
            } else {
               i++;
            }
         }
      
      return found;
   }
   
   /**
    * Test of sostituisciContattoAt method, of class nu.mine.egoless.didattica.app.bean.ContattiBean.
    */
   public void testSostituisciContattoAt() throws Exception {
      System.out.println("sostituisciContattoAt");
      
      int posizione = 0;
      
      ContattiBean instance = new ContattiBean();
      
      Contatto rimpiazzo=new Contatto();
      rimpiazzo.setCivico("CivicoRimpiazzo");
      
      ContattoBeanTest.cercaContattoBehavior(2);
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.addPropertyChangeListener(ContattiBean.MODIFICA, l);
      
      //Sostituiamo un elemento con indice -1
      eventFired=false;
      posizione=-1;
      instance.caricaContatti(insiemeParametri);
      instance.sostituisciContattoAt(posizione, rimpiazzo);
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento con indice pari al numero di elementi
      eventFired=false;
      instance.caricaContatti(insiemeParametri);
      posizione=instance.ritornaNumeroDiContatti();
      instance.sostituisciContattoAt(posizione, rimpiazzo);
      assertEquals(false, eventFired);
      
      //Sostituiamo un elemento esistente
      eventFired=false;
      posizione=1;
      instance.caricaContatti(insiemeParametri);
      Contatto curr=instance.getContattoAt(posizione);
      instance.sostituisciContattoAt(posizione, rimpiazzo);
     
      assertSame(rimpiazzo, instance.getContattoAt(posizione));
      assertFalse(esisteElemento(curr, instance));
      assertEquals(true, eventFired);
      
   
   }
   
}
