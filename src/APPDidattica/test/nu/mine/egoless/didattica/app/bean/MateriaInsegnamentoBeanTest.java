/*
 * MateriaInsegnamentoBeanTest.java
 * JUnit based test
 *
 * Created on 24 marzo 2007, 11.52
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.materiaclient.MateriaInsegnamento;
import nu.mine.egoless.didattica.ws.materiaclient.WSMateria;
import nu.mine.egoless.didattica.ws.materiaclient.WSMateriaService;
import nu.mine.egoless.didattica.ws.materiaclient.WSDidatticaException_Exception;
import java.net.PortUnreachableException;

/**
 *
 * @author Roberto
 */
public class MateriaInsegnamentoBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public MateriaInsegnamentoBeanTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
   }

   protected void tearDown() throws Exception {
   }

   /**
    * Test of getId method, of class nu.mine.egoless.didattica.app.bean.MateriaInsegnamentoBean.
    */
   public void testGetId() {
      System.out.println("getId");
      
      MateriaInsegnamentoBean instance = new MateriaInsegnamentoBean();
      
      long expResult = 0L;
      long result = instance.getId();
      assertEquals(expResult, result);
   
   }

   /**
    * Test of getNome method, of class nu.mine.egoless.didattica.app.bean.MateriaInsegnamentoBean.
    */
   public void testGetNome() {
      System.out.println("getNome");
      
      MateriaInsegnamentoBean instance = new MateriaInsegnamentoBean();
      
      String expResult = "unNome";
      instance.setNome(expResult);
      String result = instance.getNome();
      assertEquals(expResult, result);
  
   }

   /**
    * Test of setNome method, of class nu.mine.egoless.didattica.app.bean.MateriaInsegnamentoBean.
    */
   public void testSetNome() {
      System.out.println("setNome");
      
      eventFired=false;
      
      //Passaggio da due valori
      String nome="unNome";
      String nuovoNome="unNuovoNome";
      MateriaInsegnamentoBean instance = new MateriaInsegnamentoBean();
      instance.setNome(nome);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unNome") &&
                    evt.getNewValue().equals("unNuovoNome")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(MateriaInsegnamentoBean.NOME, l);
      
      instance.setNome(nuovoNome);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(MateriaInsegnamentoBean.NOME, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals("unNuovoNome")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setNome(null);
      instance.addPropertyChangeListener(MateriaInsegnamentoBean.NOME, l);
      instance.setNome(nuovoNome);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(MateriaInsegnamentoBean.NOME, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unNome") &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setNome(nome);
      instance.addPropertyChangeListener(MateriaInsegnamentoBean.NOME, l);
      instance.setNome(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(MateriaInsegnamentoBean.NOME, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setNome(nome);
      instance.addPropertyChangeListener(MateriaInsegnamentoBean.NOME, l);
      instance.setNome(nome);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(MateriaInsegnamentoBean.NOME, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setNome(null);
      instance.addPropertyChangeListener(MateriaInsegnamentoBean.NOME, l);
      instance.setNome(null);
      
      assertEquals(false, eventFired);
   }



      /**
    * Funzione di aiuto per determinare se esiste una materia con un determinato id.
    * @param id id dell'occorrenza da trovare.
    * @return {@code true} se l'occorrenza esiste, {@code false}.
    * @throws Exception Vengono rilanciate tutte le eccezioni che si verificano.
    **/
   private static boolean esisteMateria(long id) throws Exception {
      MaterieInsegnamentoBeanTest.recuperaMaterieBehavior(1);
      MaterieInsegnamentoBean materie=new MaterieInsegnamentoBean();
      materie.caricaMaterie();
      
      boolean found=false;
      int i=0;
      while(!found && i<materie.ritornaNumeroDiMaterie()) {
         if(materie.getMateriaAt(i).getId()==id) {
            found=true;
         } else {
            i++;
         }
      }
      
      return found;
   }
   
   /**
    * Test of salvaSuWS method, of class nu.mine.egoless.didattica.app.bean.MateriaInsegnamentoBean.
    */
   public void testSalvaSuWS() throws Exception {
      System.out.println("salvaSuWS");
      
      MateriaInsegnamentoBean instance = new MateriaInsegnamentoBean();
          
      assertEquals(instance.getId(), Costanti.ID_NUOVA_MATERIA_INSEGNAMENTO);
      
      instance.setNome("unaMateria");
      instance.salvaSuWS();
      assertFalse(instance.getId()==Costanti.ID_NUOVA_MATERIA_INSEGNAMENTO);
      assertEquals(true, esisteMateria(instance.getId()));
   }
   
}
