/*
 * InsegnantiBeanTest.java
 * JUnit based test
 *
 * Created on 25 marzo 2007, 9.15
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.personaclient.Docente;
import nu.mine.egoless.didattica.ws.personaclient.ParametriRicercaDocente;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.personaclient.WSPersona;
import nu.mine.egoless.didattica.ws.personaclient.WSPersonaService;
import nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception;
import java.util.List;
import java.net.PortUnreachableException;

/**
 *
 * @author Roberto
 */
public class InsegnantiBeanTest extends TestCase {
   
   private boolean eventFired;
   ParametriRicercaDocente insiemeParametri;
   
   public InsegnantiBeanTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
      insiemeParametri=new ParametriRicercaDocente();
   }

   protected void tearDown() throws Exception {
   }

   /**
    * Test of caricaInsegnanti method, of class nu.mine.egoless.didattica.app.bean.InsegnantiBean.
    */
   public void testCaricaInsegnanti() throws Exception {
      System.out.println("caricaInsegnanti");
      
      
      InsegnantiBean instance = new InsegnantiBean();
            
      InsegnanteBeanTest.cercaInsegnanteBehavior(2);
            
      // passo null come parametro
      insiemeParametri=null;
      boolean thrown=false;
      // dovrebbe rilanciare l'eccezione
      try {instance.caricaInsegnanti(insiemeParametri);}
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
            
      instance.addPropertyChangeListener(InsegnantiBean.CARICAMENTO_AVVENUTO, l);
      insiemeParametri=new ParametriRicercaDocente();
      instance.caricaInsegnanti(insiemeParametri);
      assertEquals(true, eventFired);
   }

   /**
    * Test of ritornaNumeroDiInsegnanti method, of class nu.mine.egoless.didattica.app.bean.InsegnantiBean.
    */
   public void testRitornaNumeroDiInsegnanti() throws Exception {
      System.out.println("ritornaNumeroDiInsegnanti");
      
      InsegnantiBean instance = new InsegnantiBean();
      
      //Uso del bean appena creato
      int expResult = 0;
      int result = instance.ritornaNumeroDiInsegnanti();
      assertEquals(expResult, result);
      
      
      //Ci vengono restituiti risultati
      InsegnanteBeanTest.cercaInsegnanteBehavior(2);
      expResult = 3;
      instance.caricaInsegnanti(insiemeParametri);
      result = instance.ritornaNumeroDiInsegnanti();
      assertEquals(expResult, result);
      
      //Non vengono restituiti risultati
      InsegnanteBeanTest.cercaInsegnanteBehavior(0);
      expResult = 0;
      instance.caricaInsegnanti(insiemeParametri);
      result = instance.ritornaNumeroDiInsegnanti();
      assertEquals(expResult, result);
    
     
   }

   /**
    * Test of getInsegnanteAt method, of class nu.mine.egoless.didattica.app.bean.InsegnantiBean.
    */
   public void testGetInsegnanteAt() throws Exception{
      System.out.println("getInsegnanteAt");
      
      int posizione = 0;
      InsegnantiBean instance = new InsegnantiBean();
      
      Docente result;
      
      //Uso senza caricare nulla
      result = instance.getInsegnanteAt(posizione);
      assertEquals(null, result);
      
      //Non ci viene restituito niente
      InsegnanteBeanTest.cercaInsegnanteBehavior(0);
      posizione=0;
      instance.caricaInsegnanti(insiemeParametri);
      result = instance.getInsegnanteAt(posizione);
      assertEquals(null, result);
      
      //Ci vengono restituiti 3 risultati
      InsegnanteBeanTest.cercaInsegnanteBehavior(2);
      instance.caricaInsegnanti(insiemeParametri);
      
      posizione=0;
      result = instance.getInsegnanteAt(posizione);
      assertNotNull(result);
      
      posizione=instance.ritornaNumeroDiInsegnanti();
      result = instance.getInsegnanteAt(posizione);
      assertNull(result);
      
      posizione=-1;
      result = instance.getInsegnanteAt(posizione);
      assertNull(result);
      
      posizione=instance.ritornaNumeroDiInsegnanti()+2;
      result = instance.getInsegnanteAt(posizione);
      assertNull(result);
      
      posizione=1;
      result = instance.getInsegnanteAt(posizione);
      assertNotNull(result);
   }

   /**
    * Test of rimuoviInsegnanteAt method, of class nu.mine.egoless.didattica.app.bean.InsegnantiBean.
    */
   public void testRimuoviInsegnanteAt() throws Exception{
      System.out.println("rimuoviInsegnanteAt");
      
      int posizione = 0;
      int expNumeroElementi;
      InsegnantiBean instance = new InsegnantiBean();
            
      InsegnanteBeanTest.cercaInsegnanteBehavior(2);
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.addPropertyChangeListener(InsegnantiBean.RIMOZIONE, l);
      
      //Cancelliamo un elemento con indice -1
      eventFired=false;
      posizione=-1;
      instance.caricaInsegnanti(insiemeParametri);
      expNumeroElementi=instance.ritornaNumeroDiInsegnanti();
      instance.rimuoviInsegnanteAt(posizione);
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiInsegnanti());
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento con indice pari al numero di elementi
      eventFired=false;
      instance.caricaInsegnanti(insiemeParametri);
      expNumeroElementi=instance.ritornaNumeroDiInsegnanti();
      posizione=expNumeroElementi;
      instance.rimuoviInsegnanteAt(posizione);
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiInsegnanti());
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento esistente
      eventFired=false;
      posizione=1;
      instance.caricaInsegnanti(insiemeParametri);
      expNumeroElementi=instance.ritornaNumeroDiInsegnanti()-1;
      Docente curr=instance.getInsegnanteAt(posizione);
      instance.rimuoviInsegnanteAt(posizione);
      
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiInsegnanti());
      assertFalse(esisteElemento(curr, instance));
      assertEquals(true, eventFired);
   
   }

   private static boolean esisteElemento(Docente elemento, InsegnantiBean bean) {
      boolean found=false;
      int i=0;
      while(!found && i<bean.ritornaNumeroDiInsegnanti()) {
         if(bean.getInsegnanteAt(i)==elemento) {
            found=true;
         } else {
            i++;
         }
      }
      
      return found;
   }
   
   /**
    * Test of sostituisciInsegnanteAt method, of class nu.mine.egoless.didattica.app.bean.InsegnantiBean.
    */
   public void testSostituisciInsegnanteAt() throws Exception {
      System.out.println("sostituisciInsegnanteAt");
      
      int posizione = 0;
      
      InsegnantiBean instance = new InsegnantiBean();
      
      Docente rimpiazzo=new Docente();
      rimpiazzo.setNome("PincoPallo");
      
      InsegnanteBeanTest.cercaInsegnanteBehavior(2);
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.addPropertyChangeListener(InsegnantiBean.MODIFICA, l);
      
      //Sostituiamo un elemento con indice -1
      eventFired=false;
      posizione=-1;
      instance.caricaInsegnanti(insiemeParametri);
      instance.sostituisciInsegnanteAt(posizione, rimpiazzo);
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento con indice pari al numero di elementi
      eventFired=false;
      instance.caricaInsegnanti(insiemeParametri);
      posizione=instance.ritornaNumeroDiInsegnanti();
      instance.sostituisciInsegnanteAt(posizione, rimpiazzo);
      assertEquals(false, eventFired);
      
      //Sostituiamo un elemento esistente
      eventFired=false;
      posizione=1;
      instance.caricaInsegnanti(insiemeParametri);
      Docente curr=instance.getInsegnanteAt(posizione);
      instance.sostituisciInsegnanteAt(posizione, rimpiazzo);
     
      assertSame(rimpiazzo, instance.getInsegnanteAt(posizione));
      assertFalse(esisteElemento(curr, instance));
      assertEquals(true, eventFired);
      
  
   }
   
}
