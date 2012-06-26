/*
 * TipoProvaBeanTest.java
 * JUnit based test
 *
 * Created on 26 marzo 2007, 15.04
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.tipoprovaclient.TipoProva;
import nu.mine.egoless.didattica.ws.tipoprovaclient.WSDidatticaException_Exception;
import nu.mine.egoless.didattica.ws.tipoprovaclient.WSTipoProva;
import nu.mine.egoless.didattica.ws.tipoprovaclient.WSTipoProvaService;

/**
 *
 * @author Roberto
 */
public class TipoProvaBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public TipoProvaBeanTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
   }

   protected void tearDown() throws Exception {
   }

   /**
    * Test of getId method, of class nu.mine.egoless.didattica.app.bean.TipoProvaBean.
    */
   public void testGetId() {
      System.out.println("getId");
      
      TipoProvaBean instance = new TipoProvaBean();
      
      int expResult = 0;
      int result = instance.getId();
      assertEquals(expResult, result);
 
   }

   /**
    * Test of getNome method, of class nu.mine.egoless.didattica.app.bean.TipoProvaBean.
    */
   public void testGetNome() {
      System.out.println("getNome");
      
      TipoProvaBean instance = new TipoProvaBean();
      
      String expResult = "Pippo";
      instance.setNome(expResult);
      String result = instance.getNome();
      assertEquals(expResult, result);
   }

   /**
    * Test of setNome method, of class nu.mine.egoless.didattica.app.bean.TipoProvaBean.
    */
   public void testSetNome() {
      System.out.println("setNome");
      
     eventFired=false;
      
      //Passaggio da due valori
      String nome = "primoValore";
      TipoProvaBean instance = new TipoProvaBean();
      instance.setNome(nome);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("primoValore") &&
                    evt.getNewValue().equals("secondoValore")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(TipoVotoBean.NOME, l);
      nome="secondoValore";
      instance.setNome(nome);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(TipoVotoBean.NOME, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals("secondoValore")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setNome(null);
      instance.addPropertyChangeListener(TipoVotoBean.NOME, l);
      instance.setNome("secondoValore");
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(TipoVotoBean.NOME, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("primoValore") &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setNome("primoValore");
      instance.addPropertyChangeListener(TipoVotoBean.NOME, l);
      instance.setNome(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(TipoVotoBean.NOME, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setNome("primoValore");
      instance.addPropertyChangeListener(TipoVotoBean.NOME, l);
      instance.setNome("primoValore");
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(TipoVotoBean.NOME, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setNome(null);
      instance.addPropertyChangeListener(TipoVotoBean.NOME, l);
      instance.setNome(null);
      
      assertEquals(false, eventFired);
   }
 
   /**
    * Funzione di aiuto per determinare se esiste un tipo voto con un determinato id.
    * @param id id dell'occorrenza da trovare.
    * @return {@code true} se l'occorrenza esiste, {@code false}.
    * @throws Exception Vengono rilanciate tutte le eccezioni che si verificano.
    **/
   private static boolean esisteTipoProva(long id) throws Exception {
      TipiProveBeanTest.recuperaTipiProvaBehavior(1);
      TipiProveBean tipi=new TipiProveBean();
      tipi.caricaTipiProve();
      
      boolean found=false;
      int i=0;
      while(!found && i<tipi.ritornaNumeroDiTipiProve()) {
         if(tipi.getTipoProvaAt(i).getId()==id) {
            found=true;
         } else {
            i++;
         }
      }
      
      return found;
   }
   
   /**
    * Test of salvaSuWS method, of class nu.mine.egoless.didattica.app.bean.TipoProvaBean.
    */
   public void testSalvaSuWS() throws Exception {
      System.out.println("salvaSuWS");
      
      TipoProvaBean instance = new TipoProvaBean();
      assertEquals(instance.getId(), Costanti.ID_NUOVO_TIPO_PROVA);
      
      instance.setNome("Orale");
      instance.salvaSuWS();
      assertFalse(instance.getId()==Costanti.ID_NUOVO_TIPO_PROVA);
      assertEquals(true, esisteTipoProva(instance.getId()));
   }
   
   /**
    * Test of cancellaTipoProva method, of class nu.mine.egoless.didattica.app.bean.TipoProvaBean.
    */
   public void testCancellaTipoProva() throws Exception {
      System.out.println("cancellaTipoProva");
      TipoProvaBean instance = new TipoProvaBean();
      instance.setNome("Prova");
      
      instance.salvaSuWS();
      instance.cancellaTipoProva();
      assertEquals(false, esisteTipoProva(instance.getId()));
   }
}
