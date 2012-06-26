/*
 * AssenzeBeanTest.java
 * JUnit based test
 *
 * Created on 24 marzo 2007, 15.38
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.assenzaclient.Assenza;
import nu.mine.egoless.didattica.ws.assenzaclient.ParametriRicercaAssenza;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.assenzaclient.WSAssenzaService;
import nu.mine.egoless.didattica.ws.assenzaclient.WSAssenza;
import nu.mine.egoless.didattica.ws.assenzaclient.WSDidatticaException_Exception;
import java.util.List;
import java.net.PortUnreachableException;

/**
 *
 * @author Roberto
 */
public class AssenzeBeanTest extends TestCase {
   
   private boolean eventFired;
   ParametriRicercaAssenza insiemeParametri;
   
   public AssenzeBeanTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
      insiemeParametri=new ParametriRicercaAssenza();
   }

   protected void tearDown() throws Exception {
   }
   
 

   /**
    * Test of caricaAssenze method, of class nu.mine.egoless.didattica.app.bean.AssenzeBean.
    */
   public void testCaricaAssenze() throws Exception {
      System.out.println("caricaAssenze");
      
      AssenzeBean instance = new AssenzeBean();
      insiemeParametri=null;
              
      AssenzaBeanTest.cercaAssenzaBehavior(2);
            
      // passo null come parametro
      boolean thrown=false;
      // dovrebbe rilanciare l'eccezione
      try {instance.caricaAssenze(insiemeParametri);}
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
      
      
      instance.addPropertyChangeListener(AssenzeBean.CARICAMENTO_AVVENUTO, l);
      insiemeParametri=new ParametriRicercaAssenza();
      instance.caricaAssenze(insiemeParametri);
      assertEquals(true, eventFired);
      
   
   }

   /**
    * Test of ritornaNumeroDiAssenze method, of class nu.mine.egoless.didattica.app.bean.AssenzeBean.
    */
   public void testRitornaNumeroDiAssenze() throws Exception {
      System.out.println("ritornaNumeroDiAssenze");
      
      AssenzeBean instance = new AssenzeBean();
                  
      //Uso del bean appena creato
      int expResult = 0;
      int result = instance.ritornaNumeroDiAssenze();
      assertEquals(expResult, result);
      
      
      //Ci vengono restituite 2 assenze
      AssenzaBeanTest.cercaAssenzaBehavior(2);
      expResult = 3;
      instance.caricaAssenze(insiemeParametri);
      result = instance.ritornaNumeroDiAssenze();
      assertEquals(expResult, result);
      
      //Non ci viene restituita alcuna assenza
      AssenzaBeanTest.cercaAssenzaBehavior(0);
      expResult = 0;
      instance.caricaAssenze(insiemeParametri);
      result = instance.ritornaNumeroDiAssenze();
      assertEquals(expResult, result);
    
      // TODO review the generated test code and remove the default call to fail.
      //fail("The test case is a prototype.");
   }

   /**
    * Test of getAssenzaAt method, of class nu.mine.egoless.didattica.app.bean.AssenzeBean.
    */
   public void testGetAssenzaAt() throws Exception {
      System.out.println("getAssenzaAt");
      
      int posizione = 0;
      AssenzeBean instance = new AssenzeBean();
         
      Assenza result;
      
      //Uso senza caricare nulla
      posizione=0;
      result = instance.getAssenzaAt(posizione);
      assertEquals(null, result);
      
      //Non ci viene restituita alcuna assenza
      AssenzaBeanTest.cercaAssenzaBehavior(0);
      posizione=0;
      instance.caricaAssenze(insiemeParametri);
      result = instance.getAssenzaAt(posizione);
      assertEquals(null, result);
      
      //Ci vengono restituite tre assenze
      AssenzaBeanTest.cercaAssenzaBehavior(2);
      instance.caricaAssenze(insiemeParametri);
      
      posizione=0;
      result = instance.getAssenzaAt(posizione);
      assertNotNull(result);
      
      posizione=instance.ritornaNumeroDiAssenze();
      result = instance.getAssenzaAt(posizione);
      assertNull(result);
      
      posizione=-1;
      result = instance.getAssenzaAt(posizione);
      assertNull(result);
      
      posizione=instance.ritornaNumeroDiAssenze()+2;
      result = instance.getAssenzaAt(posizione);
      assertNull(result);
      
      posizione=1;
      result = instance.getAssenzaAt(posizione);
      assertNotNull(result);
   }

   /**
    * Test of rimuoviAssenzaAt method, of class nu.mine.egoless.didattica.app.bean.AssenzeBean.
    */
   public void testRimuoviAssenzaAt() throws Exception{
      System.out.println("rimuoviAssenzaAt");
      
      int posizione = 0;
      int expNumeroElementi;
      AssenzeBean instance = new AssenzeBean();
            
      AssenzaBeanTest.cercaAssenzaBehavior(2);
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.addPropertyChangeListener(AssenzeBean.RIMOZIONE, l);
      
      //Cancelliamo un elemento con indice -1
      eventFired=false;
      posizione=-1;
      instance.caricaAssenze(insiemeParametri);
      expNumeroElementi=instance.ritornaNumeroDiAssenze();
      instance.rimuoviAssenzaAt(posizione);
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiAssenze());
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento con indice pari al numero di elementi
      eventFired=false;
      instance.caricaAssenze(insiemeParametri);
      expNumeroElementi=instance.ritornaNumeroDiAssenze();
      posizione=expNumeroElementi;
      instance.rimuoviAssenzaAt(posizione);
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiAssenze());
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento esistente
      eventFired=false;
      posizione=1;
      instance.caricaAssenze(insiemeParametri);
      expNumeroElementi=instance.ritornaNumeroDiAssenze()-1;
      Assenza curr=instance.getAssenzaAt(posizione);
      instance.rimuoviAssenzaAt(posizione);
      
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiAssenze());
      assertFalse(esisteElemento(curr, instance));
      assertEquals(true, eventFired);
      
 
   }
   
   private static boolean esisteElemento(Assenza elemento, AssenzeBean bean) {
         boolean found=false;
         int i=0;
         while(!found && i<bean.ritornaNumeroDiAssenze()) {
            if(bean.getAssenzaAt(i)==elemento) {
               found=true;
            } else {
               i++;
            }
         }
      
      return found;
   }

   /**
    * Test of sostituisciAssenzaAt method, of class nu.mine.egoless.didattica.app.bean.AssenzeBean.
    */
   public void testSostituisciAssenzaAt() throws Exception {
      System.out.println("sostituisciAssenzaAt");
      
      int posizione = 0;
      
      AssenzeBean instance = new AssenzeBean();
      
      Assenza rimpiazzo=new Assenza();
      rimpiazzo.setGiustificazione("GistRimpiazzo");
      
      AssenzaBeanTest.cercaAssenzaBehavior(2);
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.addPropertyChangeListener(AssenzeBean.MODIFICA, l);
      
      //Sostituiamo un elemento con indice -1
      eventFired=false;
      posizione=-1;
      instance.caricaAssenze(insiemeParametri);
      instance.sostituisciAssenzaAt(posizione, rimpiazzo);
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento con indice pari al numero di elementi
      eventFired=false;
      instance.caricaAssenze(insiemeParametri);
      posizione=instance.ritornaNumeroDiAssenze();
      instance.sostituisciAssenzaAt(posizione, rimpiazzo);
      assertEquals(false, eventFired);
      
      //Sostituiamo un elemento esistente
      eventFired=false;
      posizione=1;
      instance.caricaAssenze(insiemeParametri);
      Assenza curr=instance.getAssenzaAt(posizione);
      instance.sostituisciAssenzaAt(posizione, rimpiazzo);
     
      assertSame(rimpiazzo, instance.getAssenzaAt(posizione));
      assertFalse(esisteElemento(curr, instance));
      assertEquals(true, eventFired);
      
 
   }
   
}
