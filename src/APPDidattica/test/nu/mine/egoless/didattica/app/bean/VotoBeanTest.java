/*
 * VotoBeanTest.java
 * JUnit based test
 *
 * Created on 24 marzo 2007, 10.49
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import java.util.Calendar;
import junit.framework.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStub;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStubService;
import nu.mine.egoless.didattica.ws.personaclient.Docente;
import nu.mine.egoless.didattica.ws.personaclient.Studente;
import nu.mine.egoless.didattica.ws.tipovotoclient.TipoVoto;
import nu.mine.egoless.didattica.ws.votoclient.Voto;
import java.util.Date;
import java.lang.UnsupportedOperationException;
import java.util.List;
import nu.mine.egoless.didattica.ws.votoclient.ParametriRicercaVoto;
import nu.mine.egoless.didattica.ws.votoclient.WSVotoService;
import nu.mine.egoless.didattica.ws.votoclient.WSVoto;
import nu.mine.egoless.didattica.ws.votoclient.WSDidatticaException_Exception;
import nu.mine.egoless.supporto.DateTimeFacade;

/**
 *
 * @author Roberto
 */
public class VotoBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public VotoBeanTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
   }

   protected void tearDown() throws Exception {
   }


   /**
    * Test of getId method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testGetId() {
      System.out.println("getId");
      
      VotoBean instance = new VotoBean();
      
      int expResult = 0;
      int result = instance.getId();
      assertEquals(expResult, result);
  
   }

   /**
    * Test of getData method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testGetData() {
      System.out.println("getData");
      
      VotoBean instance = new VotoBean();
      
      Date expResult=Calendar.getInstance().getTime();
      instance.setData(expResult);
      Date result = instance.getData();
      assertEquals(expResult, result);
   }

   /**
    * Test of setData method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testSetData() {
      System.out.println("setData");
      
      eventFired=false;
      
      //Passaggio da due valori
      final Date data=DateTimeFacade.String2Date("2002-10-10T17:00:00.000+02:00");
      final Date nuovaData=DateTimeFacade.String2Date("2002-11-10T17:00:00.000+02:00");
                 
      VotoBean instance = new VotoBean();
      instance.setData(data);
     
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(data) &&
                    evt.getNewValue().equals(nuovaData)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(VotoBean.DATA, l);
     
      instance.setData(nuovaData);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(VotoBean.DATA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals(nuovaData)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setData(null);
      instance.addPropertyChangeListener(VotoBean.DATA, l);
      instance.setData(nuovaData);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(VotoBean.DATA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(data) &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setData(data);
      instance.addPropertyChangeListener(VotoBean.DATA, l);
      instance.setData(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(VotoBean.DATA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setData(data);
      instance.addPropertyChangeListener(VotoBean.DATA, l);
      instance.setData(data);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(VotoBean.DATA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setData(null);
      instance.addPropertyChangeListener(VotoBean.DATA, l);
      instance.setData(null);
      
      assertEquals(false, eventFired);
   }

 


   /**
    * Test of setIdInsegnante method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testSetIdInsegnante() {
      System.out.println("setIdInsegnante");
      
     //Passaggio da un valore a un altro
      
      final int id=1;
      final int nuovoId=4;
      
      VotoBean instance = new VotoBean();
      instance.setIdInsegnante(id);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(id) &&
                    evt.getNewValue().equals(nuovoId)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(VotoBean.INSEGNANTE, l);
      
      instance.setIdInsegnante(nuovoId);
      assertEquals(true, eventFired);
      
           
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(VotoBean.INSEGNANTE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdInsegnante(1);
      instance.addPropertyChangeListener(VotoBean.INSEGNANTE, l);
      instance.setIdInsegnante(1);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getIdInsegnante method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testGetIdInsegnante() {
      System.out.println("getIdInsegnante");
      
      VotoBean instance = new VotoBean();
      int expResult = 1;
      instance.setIdInsegnante(expResult);
      int result = instance.getIdInsegnante();
      assertEquals(expResult, result);
   }

   /**
    * Test of setIdMateria method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testSetIdMateria() {
      System.out.println("setIdMateria");
      
      //Passaggio da un valore a un altro
      
      final int id=1;
      final int nuovoId=4;
      
      VotoBean instance = new VotoBean();
      instance.setIdMateria(id);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(id) &&
                    evt.getNewValue().equals(nuovoId)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(VotoBean.MATERIA_INSEGNAMENTO, l);
      
      instance.setIdMateria(nuovoId);
      assertEquals(true, eventFired);
      
           
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(VotoBean.MATERIA_INSEGNAMENTO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdMateria(1);
      instance.addPropertyChangeListener(VotoBean.MATERIA_INSEGNAMENTO, l);
      instance.setIdMateria(1);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getIdMateria method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testGetIdMateria() {
      System.out.println("getIdMateria");
      
      VotoBean instance = new VotoBean();
      
      int expResult = 1;
      instance.setIdMateria(expResult);
      int result = instance.getIdMateria();
      assertEquals(expResult, result);
   }

   /**
    * Test of setIdStudente method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testSetIdStudente() {
      System.out.println("setIdStudente");
      
      //Passaggio da un valore a un altro
      
      final int id=1;
      final int nuovoId=4;
      
      VotoBean instance = new VotoBean();
      instance.setIdStudente(id);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(id) &&
                    evt.getNewValue().equals(nuovoId)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(VotoBean.STUDENTE, l);
      
      instance.setIdStudente(nuovoId);
      assertEquals(true, eventFired);
      
           
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(VotoBean.STUDENTE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdStudente(1);
      instance.addPropertyChangeListener(VotoBean.STUDENTE, l);
      instance.setIdStudente(1);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getIdStudente method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testGetIdStudente() {
      System.out.println("getIdStudente");
      
      VotoBean instance = new VotoBean();
      
      int expResult = 1;
      instance.setIdStudente(expResult);
      int result = instance.getIdStudente();
      assertEquals(expResult, result);
   }

   /**
    * Test of setIdTipoVoto method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testSetIdTipoVoto() {
      System.out.println("setIdTipoVoto");
      
     //Passaggio da un valore a un altro
      
      final int id=1;
      final int nuovoId=4;
      
      VotoBean instance = new VotoBean();
      instance.setIdTipoVoto(id);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(id) &&
                    evt.getNewValue().equals(nuovoId)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(VotoBean.TIPO_VOTO, l);
      
      instance.setIdTipoVoto(nuovoId);
      assertEquals(true, eventFired);
      
           
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(VotoBean.TIPO_VOTO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdTipoVoto(1);
      instance.addPropertyChangeListener(VotoBean.TIPO_VOTO, l);
      instance.setIdTipoVoto(1);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getIdTipoVoto method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testGetIdTipoVoto() {
      System.out.println("getIdTipoVoto");
      
      VotoBean instance = new VotoBean();
      
      int expResult = 1;
      instance.setIdTipoVoto(expResult);
      int result = instance.getIdTipoVoto();
      assertEquals(expResult, result);
   }
   
         /**
    * Funzione di aiuto per determinare se esiste un voto con un determinato id.
    * @param id id dell'occorrenza da trovare.
    * @return {@code true} se l'occorrenza esiste, {@code false}.
    * @throws Exception Vengono rilanciate tutte le eccezioni che si verificano.
    **/
   private static boolean esisteVoto(int id) throws Exception {
      cercaVotoBehavior(1);
      java.util.List<nu.mine.egoless.didattica.ws.votoclient.Voto> result=null;
      
      try { // Call Web Service Operation
         nu.mine.egoless.didattica.ws.votoclient.WSVotoService service = new nu.mine.egoless.didattica.ws.votoclient.WSVotoService();
         nu.mine.egoless.didattica.ws.votoclient.WSVoto port = service.getWSVotoPort();
         // TODO initialize WS operation arguments here
         nu.mine.egoless.didattica.ws.votoclient.ParametriRicercaVoto insiemeParametri = new nu.mine.egoless.didattica.ws.votoclient.ParametriRicercaVoto();
         insiemeParametri.setId(id);
         // TODO process result here
          result= port.cercaVoto(insiemeParametri);
         
         //System.out.println("Result = "+result);
      } catch (Exception ex) {
         // TODO handle custom exceptions here
      }
      if (result==null || result.isEmpty()) return false;
      if (result.get(0).getId()==id) return true;
      else return false;
      
     
   }
   
      /**
    * Funzione di aiuto per impostare il comportamente del Web Service.
    * @param value {@true} se {@code recuperaTipiVoto} deve restituire {@code null};
    *              {@false} altrimenti.
    */
   public static void cercaVotoBehavior(int value) {
      try { // Call Web Service Operation
         WSDidaStubService service = new WSDidaStubService();
         WSDidaStub port = service.getWSDidaStubPort();
         
         port.cercaVotoBehavior(value);
      } catch (Exception ex) {
         //Non gestiamo le eccezioni: lasciamo che il test continui
      }
   }

   /**
    * Test of caricaDaWS method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testCaricaDaWS() throws Exception {
      System.out.println("caricaDaWS");
      
     // provo a caricare un elemento presente
      cercaVotoBehavior(2);
      VotoBean instance = new VotoBean();
      
      // carico l'elemento con id=1
      instance.caricaDaWS(1);
      assertEquals(1, instance.getId());
      
      // provo a caricare un elemento non presente
      cercaVotoBehavior(0);
      instance = new VotoBean();
      instance.caricaDaWS(1);
      assertEquals(new VotoBean().getId(), instance.getId());
      
      // provo a richiedere l'id di default
      instance = new VotoBean();
      // dovrebbe lanciare un'eccezione
      boolean thrown=false;
      try{instance.caricaDaWS(Costanti.ID_NUOVO_VOTO);}
      catch(UnsupportedOperationException ex){
         thrown=true;
      }
      assertTrue(thrown);
   }

   /**
    * Test of salvaSuWS method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testSalvaSuWS() throws Exception {
      System.out.println("salvaSuWS");
      
      VotoBean instance = new VotoBean();
      
      assertEquals(instance.getId(), Costanti.ID_NUOVO_VOTO);
      
      instance.setIdTipoVoto(7);
      instance.salvaSuWS();
      assertFalse(instance.getId()==Costanti.ID_NUOVO_VOTO);
      assertEquals(true,esisteVoto(instance.getId()));
   }



   
   
   /**
    * Test of cancellaVoto method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testCancellaVoto() throws Exception {
      System.out.println("cancellaVoto");
      
      VotoBean instance = new VotoBean();
      
      instance.salvaSuWS();
      //verifico comunque il caricamento
      assertTrue(esisteVoto(instance.getId()));
      instance.cancellaVoto();
      // verifico la cancellazione
      assertEquals(false, esisteVoto(instance.getId()));
   }

   /**
    * Test of setIdClasse method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testSetIdClasse() {
      System.out.println("setIdClasse");
      
     //Passaggio da un valore a un altro
      
      final int id=1;
      final int nuovoId=4;
      
      VotoBean instance = new VotoBean();
      instance.setIdClasse(id);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(id) &&
                    evt.getNewValue().equals(nuovoId)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(VotoBean.CLASSE, l);
      
      instance.setIdClasse(nuovoId);
      assertEquals(true, eventFired);
      
           
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(VotoBean.CLASSE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdClasse(1);
      instance.addPropertyChangeListener(VotoBean.CLASSE, l);
      instance.setIdClasse(1);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getIdClasse method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testGetIdClasse() {
      System.out.println("getIdClasse");
      
      VotoBean instance = new VotoBean();
      
      int expResult = 1;
      instance.setIdClasse(expResult);
      int result = instance.getIdClasse();
      assertEquals(expResult, result);
  
   }

   /**
    * Test of setIdTipoProva method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testSetIdTipoProva() {
      System.out.println("setIdTipoProva");
      
       //Passaggio da un valore a un altro
      
      final int id=1;
      final int nuovoId=4;
      
      VotoBean instance = new VotoBean();
      instance.setIdTipoProva(id);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(id) &&
                    evt.getNewValue().equals(nuovoId)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(VotoBean.TIPO_PROVA, l);
      
      instance.setIdTipoProva(nuovoId);
      assertEquals(true, eventFired);
      
           
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(VotoBean.TIPO_PROVA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdTipoProva(1);
      instance.addPropertyChangeListener(VotoBean.TIPO_PROVA, l);
      instance.setIdTipoProva(1);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getIdTipoProva method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testGetIdTipoProva() {
      System.out.println("getIdTipoProva");
      
      VotoBean instance = new VotoBean();
      
      int expResult = 1;
      instance.setIdTipoProva(expResult);
      int result = instance.getIdTipoProva();
      assertEquals(expResult, result);
   }

   /**
    * Test of setDescrizione method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testSetDescrizione() {
      System.out.println("setDescrizione");
      
      eventFired=false;
      
      //Passaggio da due valori
      final String descrizione=new String("UnaDescrizione");
      final String nuovaDescrizione=new String("UnaNuovaDescrizione");
                 
      VotoBean instance = new VotoBean();
      instance.setDescrizione(descrizione);
     
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(descrizione) &&
                    evt.getNewValue().equals(nuovaDescrizione)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(VotoBean.DESCRIZIONE, l);
     
      instance.setDescrizione(nuovaDescrizione);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(VotoBean.DESCRIZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals(nuovaDescrizione)) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setDescrizione(null);
      instance.addPropertyChangeListener(VotoBean.DESCRIZIONE, l);
      instance.setDescrizione(nuovaDescrizione);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(VotoBean.DESCRIZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals(descrizione) &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setDescrizione(descrizione);
      instance.addPropertyChangeListener(VotoBean.DESCRIZIONE, l);
      instance.setDescrizione(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(VotoBean.DESCRIZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setDescrizione(descrizione);
      instance.addPropertyChangeListener(VotoBean.DESCRIZIONE, l);
      instance.setDescrizione(descrizione);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(VotoBean.DESCRIZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setDescrizione(null);
      instance.addPropertyChangeListener(VotoBean.DESCRIZIONE, l);
      instance.setDescrizione(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getDescrizione method, of class nu.mine.egoless.didattica.app.bean.VotoBean.
    */
   public void testGetDescrizione() {
      System.out.println("getDescrizione");
      
      VotoBean instance = new VotoBean();
      
      String expResult = "UnaDescrizione";
      instance.setDescrizione(expResult);
      String result = instance.getDescrizione();
      assertEquals(expResult, result);
  
   }
   
}
