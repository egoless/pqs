/*
 * StudentiBeanTest.java
 * JUnit based test
 *
 * Created on 25 marzo 2007, 9.25
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.personaclient.Studente;
import nu.mine.egoless.didattica.ws.personaclient.ParametriRicercaStudente;
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
public class StudentiBeanTest extends TestCase {
   
   private boolean eventFired;
   ParametriRicercaStudente insiemeParametri;
   
   public StudentiBeanTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
      insiemeParametri=new ParametriRicercaStudente();
   }

   protected void tearDown() throws Exception {
   }

   /**
    * Test of caricaStudenti method, of class nu.mine.egoless.didattica.app.bean.StudentiBean.
    */
   public void testCaricaStudenti() throws Exception {
      System.out.println("caricaStudenti");
      
      
      StudentiBean instance = new StudentiBean();
            
      StudenteBeanTest.cercaStudenteBehavior(2);
            
      // passo null come parametro
      insiemeParametri=null;
      boolean thrown=false;
      // dovrebbe rilanciare l'eccezione
      try {instance.caricaStudenti(insiemeParametri);}
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
       
      instance.addPropertyChangeListener(StudentiBean.CARICAMENTO_AVVENUTO, l);
      insiemeParametri=new ParametriRicercaStudente();
      instance.caricaStudenti(insiemeParametri);
      assertEquals(true, eventFired);
   }

   /**
    * Test of ritornaNumeroDiStudenti method, of class nu.mine.egoless.didattica.app.bean.StudentiBean.
    */
   public void testRitornaNumeroDiStudenti() throws Exception {
      System.out.println("ritornaNumeroDiStudenti");
      
      StudentiBean instance = new StudentiBean();
      
      //Uso del bean appena creato
      int expResult = 0;
      int result = instance.ritornaNumeroDiStudenti();
      assertEquals(expResult, result);
      
      
      //Ci vengono restituiti risultati
      StudenteBeanTest.cercaStudenteBehavior(2);
      expResult = 3;
      instance.caricaStudenti(insiemeParametri);
      result = instance.ritornaNumeroDiStudenti();
      assertEquals(expResult, result);
      
      //Non vengono restituiti risultati
      StudenteBeanTest.cercaStudenteBehavior(0);
      expResult = 0;
      instance.caricaStudenti(insiemeParametri);
      result = instance.ritornaNumeroDiStudenti();
      assertEquals(expResult, result);
    
     
   }

   /**
    * Test of getStudenteAt method, of class nu.mine.egoless.didattica.app.bean.StudentiBean.
    */
   public void testGetStudenteAt() throws Exception{
      System.out.println("getStudenteAt");
      
      int posizione = 0;
      StudentiBean instance = new StudentiBean();
      
      Studente result;
      
      //Uso senza caricare nulla
      result = instance.getStudenteAt(posizione);
      assertEquals(null, result);
      
      //Non ci viene restituito niente
      StudenteBeanTest.cercaStudenteBehavior(0);
      posizione=0;
      instance.caricaStudenti(insiemeParametri);
      result = instance.getStudenteAt(posizione);
      assertEquals(null, result);
      
      //Ci vengono restituiti 3 risultati
      StudenteBeanTest.cercaStudenteBehavior(2);
      instance.caricaStudenti(insiemeParametri);
      
      posizione=0;
      result = instance.getStudenteAt(posizione);
      assertNotNull(result);
      
      posizione=instance.ritornaNumeroDiStudenti();
      result = instance.getStudenteAt(posizione);
      assertNull(result);
      
      posizione=-1;
      result = instance.getStudenteAt(posizione);
      assertNull(result);
      
      posizione=instance.ritornaNumeroDiStudenti()+2;
      result = instance.getStudenteAt(posizione);
      assertNull(result);
      
      posizione=1;
      result = instance.getStudenteAt(posizione);
      assertNotNull(result);
   }

   /**
    * Test of rimuoviStudenteAt method, of class nu.mine.egoless.didattica.app.bean.StudentiBean.
    */
   public void testRimuoviStudenteAt() throws Exception{
      System.out.println("rimuoviStudenteAt");
      
      int posizione = 0;
      int expNumeroElementi;
      StudentiBean instance = new StudentiBean();
            
      StudenteBeanTest.cercaStudenteBehavior(2);
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.addPropertyChangeListener(StudentiBean.RIMOZIONE, l);
      
      //Cancelliamo un elemento con indice -1
      eventFired=false;
      posizione=-1;
      instance.caricaStudenti(insiemeParametri);
      expNumeroElementi=instance.ritornaNumeroDiStudenti();
      instance.rimuoviStudenteAt(posizione);
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiStudenti());
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento con indice pari al numero di elementi
      eventFired=false;
      instance.caricaStudenti(insiemeParametri);
      expNumeroElementi=instance.ritornaNumeroDiStudenti();
      posizione=expNumeroElementi;
      instance.rimuoviStudenteAt(posizione);
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiStudenti());
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento esistente
      eventFired=false;
      posizione=1;
      instance.caricaStudenti(insiemeParametri);
      expNumeroElementi=instance.ritornaNumeroDiStudenti()-1;
      Studente curr=instance.getStudenteAt(posizione);
      instance.rimuoviStudenteAt(posizione);
      
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiStudenti());
      assertFalse(esisteElemento(curr, instance));
      assertEquals(true, eventFired);
      
   
   }

   private static boolean esisteElemento(Studente elemento, StudentiBean bean) {
      boolean found=false;
      int i=0;
      while(!found && i<bean.ritornaNumeroDiStudenti()) {
         if(bean.getStudenteAt(i)==elemento) {
            found=true;
         } else {
            i++;
         }
      }
      
      return found;
   }
   
   /**
    * Test of sostituisciStudenteAt method, of class nu.mine.egoless.didattica.app.bean.StudentiBean.
    */
   public void testSostituisciStudenteAt() throws Exception {
      System.out.println("sostituisciStudenteAt");
      
      int posizione = 0;
      
      StudentiBean instance = new StudentiBean();
      
      Studente rimpiazzo=new Studente();
      rimpiazzo.setMatricola("MatricolaRimpiazzo");
      
      StudenteBeanTest.cercaStudenteBehavior(2);
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.addPropertyChangeListener(StudentiBean.MODIFICA, l);
      
      //Sostituiamo un elemento con indice -1
      eventFired=false;
      posizione=-1;
      instance.caricaStudenti(insiemeParametri);
      instance.sostituisciStudenteAt(posizione, rimpiazzo);
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento con indice pari al numero di elementi
      eventFired=false;
      instance.caricaStudenti(insiemeParametri);
      posizione=instance.ritornaNumeroDiStudenti();
      instance.sostituisciStudenteAt(posizione, rimpiazzo);
      assertEquals(false, eventFired);
      
      //Sostituiamo un elemento esistente
      eventFired=false;
      posizione=1;
      instance.caricaStudenti(insiemeParametri);
      Studente curr=instance.getStudenteAt(posizione);
      instance.sostituisciStudenteAt(posizione, rimpiazzo);
     
      assertSame(rimpiazzo, instance.getStudenteAt(posizione));
      assertFalse(esisteElemento(curr, instance));
      assertEquals(true, eventFired);
      
     
   }
   
}
