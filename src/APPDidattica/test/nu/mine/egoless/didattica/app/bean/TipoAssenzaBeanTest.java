/*
 * Nome file: TipoAssenzaBeanTest.java
 * Data creazione: 20 marzo 2007, 10.12
 * Info svn: $Id: TipoAssenzaBeanTest.java 798 2007-03-26 13:49:14Z roberto $
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.tipoassenzaclient.TipoAssenza;
import nu.mine.egoless.didattica.ws.tipoassenzaclient.WSTipoAssenzaService;
import nu.mine.egoless.didattica.ws.tipoassenzaclient.WSTipoAssenza;
import nu.mine.egoless.didattica.ws.tipoassenzaclient.WSDidatticaException_Exception;

/**
 * Classe di test per testare {@link TipoAssenzaBean}.
 */
public class TipoAssenzaBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public TipoAssenzaBeanTest(String testName) {
      super(testName);
   }
   
   protected void setUp() throws Exception {
   }
   
   protected void tearDown() throws Exception {
   }
   
   /**
    * Test of getDescrizione method, of class nu.mine.egoless.didattica.app.bean.TipoAssenzaBean.
    */
   public void testGetDescrizione() {
      System.out.println("getDescrizione");
      
      TipoAssenzaBean instance = new TipoAssenzaBean();
      
      String expResult = "Pippo";
      instance.setDescrizione(expResult);
      String result = instance.getDescrizione();
      assertEquals(expResult, result);
   }
   
   /**
    * Test of setDescrizione method, of class nu.mine.egoless.didattica.app.bean.TipoAssenzaBean.
    */
   public void testSetDescrizione() {
      System.out.println("setDescrizione");
      
      eventFired=false;
      
      //Passaggio da due valori
      String descrizione = "primoValore";
      TipoAssenzaBean instance = new TipoAssenzaBean();
      instance.setDescrizione(descrizione);
      
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
      instance.addPropertyChangeListener(TipoAssenzaBean.DESCRIZIONE, l);
      descrizione="secondoValore";
      instance.setDescrizione(descrizione);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(TipoAssenzaBean.DESCRIZIONE, l);
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
      instance.setDescrizione(null);
      instance.addPropertyChangeListener(TipoAssenzaBean.DESCRIZIONE, l);
      instance.setDescrizione("secondoValore");
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(TipoAssenzaBean.DESCRIZIONE, l);
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
      instance.setDescrizione("primoValore");
      instance.addPropertyChangeListener(TipoAssenzaBean.DESCRIZIONE, l);
      instance.setDescrizione(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(TipoAssenzaBean.DESCRIZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setDescrizione("primoValore");
      instance.addPropertyChangeListener(TipoAssenzaBean.DESCRIZIONE, l);
      instance.setDescrizione("primoValore");
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(TipoAssenzaBean.DESCRIZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setDescrizione(null);
      instance.addPropertyChangeListener(TipoAssenzaBean.DESCRIZIONE, l);
      instance.setDescrizione(null);
      
      assertEquals(false, eventFired);
   }
   
   /**
    * Funzione di aiuto per determinare se esiste un tipo assenza con un determinato id.
    * @param id id dell'occorrenza da trovare.
    * @return {@code true} se l'occorrenza esiste, {@code false}.
    * @throws Exception Vengono rilanciate tutte le eccezioni che si verificano.
    **/
   private static boolean esisteTipoAssenza(long id) throws Exception {
      TipiAssenzeBeanTest.recuperaTipiBehavior(1);
      TipiAssenzeBean tipi=new TipiAssenzeBean();
      tipi.caricaTipiAssenze();
      
      boolean found=false;
      int i=0;
      while(!found && i<tipi.ritornaNumeroDiTipiAssenze()) {
         if(tipi.getTipoAssenzaAt(i).getId()==id) {
            found=true;
         } else {
            i++;
         }
      }
      
      return found;
   }
   
   /**
    * Test of salvaSuWS method, of class nu.mine.egoless.didattica.app.bean.TipoAssenzaBean.
    */
   public void testSalvaSuWS() throws Exception {
      System.out.println("salvaSuWS");
      
      TipoAssenzaBean instance = new TipoAssenzaBean();
      assertEquals(instance.getId(), Costanti.ID_NUOVO_TIPO_ASSENZA);
      
      instance.setDescrizione("Orale");
      instance.salvaSuWS();
      assertFalse(instance.getId()==Costanti.ID_NUOVO_TIPO_ASSENZA);
      assertEquals(true, esisteTipoAssenza(instance.getId()));
   }
   
   /**
    * Test of cancellaTipoAssenza method, of class nu.mine.egoless.didattica.app.bean.TipoAssenzaBean.
    */
   public void testCancellaTipoAssenza() throws Exception {
      System.out.println("cancellaTipoAssenza");
      
      TipoAssenzaBean instance = new TipoAssenzaBean();
      
      instance.setDescrizione("Prova");
      
      instance.salvaSuWS();
      instance.cancellaTipoAssenza();
      assertEquals(false, esisteTipoAssenza(instance.getId()));
   }

   /**
    * Test of getId method, of class nu.mine.egoless.didattica.app.bean.TipoAssenzaBean.
    */
   public void testGetId() {
      System.out.println("getId");
      
      TipoAssenzaBean instance = new TipoAssenzaBean();
      
      int expResult = 0;
      int result = instance.getId();
      assertEquals(expResult, result);
  
   }
   
}
