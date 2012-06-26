/*
 * Nome file: TipoVotoBeanTest.java
 * Data creazione: March 16, 2007, 11:02 AM
 * Info svn: $Id: TipoVotoBeanTest.java 798 2007-03-26 13:49:14Z roberto $
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.tipovotoclient.TipoVoto;
import nu.mine.egoless.didattica.ws.tipovotoclient.WSDidatticaException_Exception;
import nu.mine.egoless.didattica.ws.tipovotoclient.WSTipoVotoService;
import nu.mine.egoless.didattica.ws.tipovotoclient.WSTipoVoto;

/**
 * Classe di test per testare {@link TipoVotoBean}.
 */
public class TipoVotoBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public TipoVotoBeanTest(String testName) {
      super(testName);
   }
   
   protected void setUp() throws Exception {
   }
   
   protected void tearDown() throws Exception {
   }
      
   /**
    * Test of getNome method, of class nu.mine.egoless.didattica.app.bean.TipoVotoBean.
    */
   public void testGetNome() {
      System.out.println("getNome");
      
      TipoVotoBean instance = new TipoVotoBean();
      
      String expResult = "Pippo";
      instance.setNome(expResult);
      String result = instance.getNome();
      assertEquals(expResult, result);
   }
   
   /**
    * Test of setNome method, of class nu.mine.egoless.didattica.app.bean.TipoVotoBean.
    */
   public void testSetNome() {
      System.out.println("setNome");
      eventFired=false;
      
      //Passaggio da due valori
      String nome = "primoValore";
      TipoVotoBean instance = new TipoVotoBean();
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
   private static boolean esisteTipoVoto(long id) throws Exception {
      TipiVotiBeanTest.recuperaTipiVotoBehavior(1);
      TipiVotiBean tipi=new TipiVotiBean();
      tipi.caricaTipiVoti();
      
      boolean found=false;
      int i=0;
      while(!found && i<tipi.ritornaNumeroDiTipiVoti()) {
         if(tipi.getTipoVotoAt(i).getId()==id) {
            found=true;
         } else {
            i++;
         }
      }
      
      return found;
   }
   
   /**
    * Test of salvaSuWS method, of class nu.mine.egoless.didattica.app.bean.TipoVotoBean.
    */
   public void testSalvaSuWS() throws Exception {
      System.out.println("salvaSuWS");
      
      TipoVotoBean instance = new TipoVotoBean();
      assertEquals(instance.getId(), Costanti.ID_NUOVO_TIPO_VOTO);
      
      instance.setNome("Orale");
      instance.salvaSuWS();
      assertFalse(instance.getId()==Costanti.ID_NUOVO_TIPO_VOTO);
      assertEquals(true, esisteTipoVoto(instance.getId()));
   }
   
   /**
    * Test of cancellaTipoVoto method, of class nu.mine.egoless.didattica.app.bean.TipoVotoBean.
    */
   public void testCancellaTipoVoto() throws Exception {
      System.out.println("cancellaTipoVoto");
      TipoVotoBean instance = new TipoVotoBean();
      instance.setNome("Prova");
      
      instance.salvaSuWS();
      instance.cancellaTipoVoto();
      assertEquals(false, esisteTipoVoto(instance.getId()));
   }

   /**
    * Test of getId method, of class nu.mine.egoless.didattica.app.bean.TipoVotoBean.
    */
   public void testGetId() {
      System.out.println("getId");
      
      TipoVotoBean instance = new TipoVotoBean();
      
      int expResult = 0;
      int result = instance.getId();
      assertEquals(expResult, result);
      
   
   }

   /**
    * Test of getValore method, of class nu.mine.egoless.didattica.app.bean.TipoVotoBean.
    */
   public void testGetValore() {
      System.out.println("getValore");
      
      TipoVotoBean instance = new TipoVotoBean();
      
      String expResult = "Valore";
      instance.setValore(expResult);
      String result = instance.getValore();
      assertEquals(expResult, result);
 
   }

   /**
    * Test of setValore method, of class nu.mine.egoless.didattica.app.bean.TipoVotoBean.
    */
   public void testSetValore() {
      System.out.println("setValore");
      
     eventFired=false;
      
      //Passaggio da due valori
      String valore = "primoValore";
      TipoVotoBean instance = new TipoVotoBean();
      instance.setValore(valore);
      
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
      instance.addPropertyChangeListener(TipoVotoBean.VALORE, l);
      valore="secondoValore";
      instance.setValore(valore);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(TipoVotoBean.VALORE, l);
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
      instance.setValore(null);
      instance.addPropertyChangeListener(TipoVotoBean.VALORE, l);
      instance.setValore("secondoValore");
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(TipoVotoBean.VALORE, l);
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
      instance.setValore("primoValore");
      instance.addPropertyChangeListener(TipoVotoBean.VALORE, l);
      instance.setValore(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(TipoVotoBean.VALORE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setValore("primoValore");
      instance.addPropertyChangeListener(TipoVotoBean.VALORE, l);
      instance.setValore("primoValore");
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(TipoVotoBean.VALORE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setValore(null);
      instance.addPropertyChangeListener(TipoVotoBean.VALORE, l);
      instance.setValore(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getOrdine method, of class nu.mine.egoless.didattica.app.bean.TipoVotoBean.
    */
   public void testGetOrdine() {
      System.out.println("getOrdine");
      
      TipoVotoBean instance = new TipoVotoBean();
      
      Integer expResult = new Integer(1);
      instance.setOrdine(expResult);
      Integer result = instance.getOrdine();
      assertEquals(expResult, result);
 
   }

   /**
    * Test of setOrdine method, of class nu.mine.egoless.didattica.app.bean.TipoVotoBean.
    */
   public void testSetOrdine() {
      System.out.println("setOrdine");
      
      eventFired=false;
      
      //Passaggio da due valori
      final Integer ordine = new Integer(1);
      final Integer nuovoOrdine = new Integer(2);
      TipoVotoBean instance = new TipoVotoBean();
      instance.setOrdine(ordine);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(ordine) &&
                    evt.getNewValue().equals(nuovoOrdine)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(TipoVotoBean.ORDINE, l);
      
      instance.setOrdine(nuovoOrdine);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(TipoVotoBean.ORDINE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals(nuovoOrdine)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setOrdine(null);
      instance.addPropertyChangeListener(TipoVotoBean.ORDINE, l);
      instance.setOrdine(nuovoOrdine);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(TipoVotoBean.ORDINE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(ordine) &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setOrdine(ordine);
      instance.addPropertyChangeListener(TipoVotoBean.ORDINE, l);
      instance.setOrdine(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(TipoVotoBean.ORDINE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setOrdine(ordine);
      instance.addPropertyChangeListener(TipoVotoBean.ORDINE, l);
      instance.setOrdine(ordine);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(TipoVotoBean.ORDINE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setOrdine(null);
      instance.addPropertyChangeListener(TipoVotoBean.ORDINE, l);
      instance.setOrdine(null);
      
      assertEquals(false, eventFired);
   }
   
}
