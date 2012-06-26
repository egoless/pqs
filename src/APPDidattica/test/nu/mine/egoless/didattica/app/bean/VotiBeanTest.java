/*
 * VotiBeanTest.java
 * JUnit based test
 *
 * Created on 25 marzo 2007, 9.30
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.votoclient.Voto;
import nu.mine.egoless.didattica.ws.votoclient.ParametriRicercaVoto;
import java.beans.PropertyChangeListener;
import nu.mine.egoless.didattica.ws.votoclient.WSVotoService;
import nu.mine.egoless.didattica.ws.votoclient.WSVoto;
import nu.mine.egoless.didattica.ws.votoclient.WSDidatticaException_Exception;
import java.util.List;
import java.net.PortUnreachableException;

/**
 *
 * @author Roberto
 */
public class VotiBeanTest extends TestCase {
   
   private boolean eventFired;
   ParametriRicercaVoto insiemeParametri;
   
   public VotiBeanTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
      insiemeParametri=new ParametriRicercaVoto();
   }

   protected void tearDown() throws Exception {
   }

   /**
    * Test of caricaVoti method, of class nu.mine.egoless.didattica.app.bean.VotiBean.
    */
   public void testCaricaVoti() throws Exception {
      System.out.println("caricaVoti");
      
      
      VotiBean instance = new VotiBean();
            
      VotoBeanTest.cercaVotoBehavior(2);
            
      // passo null come parametro
      insiemeParametri=null;
      boolean thrown=false;
      // dovrebbe rilanciare l'eccezione
      try {instance.caricaVoti(insiemeParametri);}
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
      
      instance.addPropertyChangeListener(VotiBean.CARICAMENTO_AVVENUTO, l);
      insiemeParametri=new ParametriRicercaVoto();
      instance.caricaVoti(insiemeParametri);
      assertEquals(true, eventFired);
   }

   /**
    * Test of ritornaNumeroDiVoti method, of class nu.mine.egoless.didattica.app.bean.VotiBean.
    */
   public void testRitornaNumeroDiVoti() throws Exception {
      System.out.println("ritornaNumeroDiVoti");
      
      VotiBean instance = new VotiBean();
      
      //Uso del bean appena creato
      int expResult = 0;
      int result = instance.ritornaNumeroDiVoti();
      assertEquals(expResult, result);
      
      
      //Ci vengono restituiti risultati
      VotoBeanTest.cercaVotoBehavior(2);
      expResult = 3;
      instance.caricaVoti(insiemeParametri);
      result = instance.ritornaNumeroDiVoti();
      assertEquals(expResult, result);
      
      //Non vengono restituiti risultati
      VotoBeanTest.cercaVotoBehavior(0);
      expResult = 0;
      instance.caricaVoti(insiemeParametri);
      result = instance.ritornaNumeroDiVoti();
      assertEquals(expResult, result);
    
     
   }

   /**
    * Test of getVotoAt method, of class nu.mine.egoless.didattica.app.bean.VotiBean.
    */
   public void testGetVotoAt() throws Exception{
      System.out.println("getVotoAt");
      
      int posizione = 0;
      VotiBean instance = new VotiBean();
      
      Voto result;
      
      //Uso senza caricare nulla
      result = instance.getVotoAt(posizione);
      assertEquals(null, result);
      
      //Non ci viene restituito niente
      VotoBeanTest.cercaVotoBehavior(0);
      posizione=0;
      instance.caricaVoti(insiemeParametri);
      result = instance.getVotoAt(posizione);
      assertEquals(null, result);
      
      //Ci vengono restituiti 3 risultati
      VotoBeanTest.cercaVotoBehavior(2);
      instance.caricaVoti(insiemeParametri);
      
      posizione=0;
      result = instance.getVotoAt(posizione);
      assertNotNull(result);
      
      posizione=instance.ritornaNumeroDiVoti();
      result = instance.getVotoAt(posizione);
      assertNull(result);
      
      posizione=-1;
      result = instance.getVotoAt(posizione);
      assertNull(result);
      
      posizione=instance.ritornaNumeroDiVoti()+2;
      result = instance.getVotoAt(posizione);
      assertNull(result);
      
      posizione=1;
      result = instance.getVotoAt(posizione);
      assertNotNull(result);
   }

   /**
    * Test of rimuoviVotoAt method, of class nu.mine.egoless.didattica.app.bean.VotiBean.
    */
   public void testRimuoviVotoAt() throws Exception{
      System.out.println("rimuoviVotoAt");
      
      int posizione = 0;
      int expNumeroElementi;
      VotiBean instance = new VotiBean();
            
      VotoBeanTest.cercaVotoBehavior(2);
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.addPropertyChangeListener(VotiBean.RIMOZIONE, l);
      
      //Cancelliamo un elemento con indice -1
      eventFired=false;
      posizione=-1;
      instance.caricaVoti(insiemeParametri);
      expNumeroElementi=instance.ritornaNumeroDiVoti();
      instance.rimuoviVotoAt(posizione);
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiVoti());
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento con indice pari al numero di elementi
      eventFired=false;
      instance.caricaVoti(insiemeParametri);
      expNumeroElementi=instance.ritornaNumeroDiVoti();
      posizione=expNumeroElementi;
      instance.rimuoviVotoAt(posizione);
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiVoti());
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento esistente
      eventFired=false;
      posizione=1;
      instance.caricaVoti(insiemeParametri);
      expNumeroElementi=instance.ritornaNumeroDiVoti()-1;
      Voto curr=instance.getVotoAt(posizione);
      instance.rimuoviVotoAt(posizione);
      
      assertEquals(expNumeroElementi, instance.ritornaNumeroDiVoti());
      assertFalse(esisteElemento(curr, instance));
      assertEquals(true, eventFired);
      
  
   }

   private static boolean esisteElemento(Voto elemento, VotiBean bean) {
      boolean found=false;
      int i=0;
      while(!found && i<bean.ritornaNumeroDiVoti()) {
         if(bean.getVotoAt(i)==elemento) {
            found=true;
         } else {
            i++;
         }
      }
      
      return found;
   }
   
   /**
    * Test of sostituisciVotoAt method, of class nu.mine.egoless.didattica.app.bean.VotiBean.
    */
   public void testSostituisciVotoAt() throws Exception {
      System.out.println("sostituisciVotoAt");
      
      int posizione = 0;
      
      VotiBean instance = new VotiBean();
      
      Voto rimpiazzo=new Voto();
      rimpiazzo.setTipoVotoId(7);
      
      VotoBeanTest.cercaVotoBehavior(2);
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.addPropertyChangeListener(VotiBean.MODIFICA, l);
      
      //Sostituiamo un elemento con indice -1
      eventFired=false;
      posizione=-1;
      instance.caricaVoti(insiemeParametri);
      instance.sostituisciVotoAt(posizione, rimpiazzo);
      assertEquals(false, eventFired);
      
      //Cancelliamo un elemento con indice pari al numero di elementi
      eventFired=false;
      instance.caricaVoti(insiemeParametri);
      posizione=instance.ritornaNumeroDiVoti();
      instance.sostituisciVotoAt(posizione, rimpiazzo);
      assertEquals(false, eventFired);
      
      //Sostituiamo un elemento esistente
      eventFired=false;
      posizione=1;
      instance.caricaVoti(insiemeParametri);
      Voto curr=instance.getVotoAt(posizione);
      instance.sostituisciVotoAt(posizione, rimpiazzo);
     
      assertSame(rimpiazzo, instance.getVotoAt(posizione));
      assertFalse(esisteElemento(curr, instance));
      assertEquals(true, eventFired);
      
   
   }
   
}