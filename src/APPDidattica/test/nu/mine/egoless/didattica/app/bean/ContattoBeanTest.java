/*
 * ContattoBeanTest.java
 * JUnit based test
 *
 * Created on 23 marzo 2007, 20.37
 */

package nu.mine.egoless.didattica.app.bean;

import java.beans.PropertyChangeEvent;
import junit.framework.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;
import nu.mine.egoless.didattica.ws.contattoclient.Contatto;
import nu.mine.egoless.didattica.ws.contattoclient.ParametriRicercaContatto;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStub;
import nu.mine.egoless.didattica.ws.didastubclient.WSDidaStubService;
import nu.mine.egoless.didattica.ws.personaclient.Persona;
import nu.mine.egoless.didattica.ws.contattoclient.WSContattoService;
import nu.mine.egoless.didattica.ws.contattoclient.WSContatto;
import nu.mine.egoless.didattica.ws.contattoclient.WSDidatticaException_Exception;
import java.util.List;

/**
 *
 * @author Roberto
 */
public class ContattoBeanTest extends TestCase {
   
   private boolean eventFired;
   
   public ContattoBeanTest(String testName) {
      super(testName);
   }

   protected void setUp() throws Exception {
   }

   protected void tearDown() throws Exception {
   }

   /**
    * Test of getId method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testGetId() {
      System.out.println("getId");
      
      ContattoBean instance = new ContattoBean();
      
      int expResult = 0;
      int result = instance.getId();
      assertEquals(expResult, result);
     
   }

   /**
    * Test of getVia method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testGetVia() {
      System.out.println("getVia");

      ContattoBean instance = new ContattoBean();
      
      String expResult = "unaVia";
      instance.setVia(expResult);
      String result = instance.getVia();
      assertEquals(expResult, result);

   }

   /**
    * Test of setVia method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testSetVia() {
      System.out.println("setVia");
       eventFired=false;
      
      //Passaggio da due valori
      String via="unaVia";
      String nuovaVia="unaNuovaVia";
      ContattoBean instance = new ContattoBean();
      instance.setVia(via);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unaVia") &&
                    evt.getNewValue().equals("unaNuovaVia")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(ContattoBean.VIA, l);
      
      instance.setVia(nuovaVia);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.VIA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals("unaNuovaVia")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setVia(null);
      instance.addPropertyChangeListener(ContattoBean.VIA, l);
      instance.setVia(nuovaVia);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.VIA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unaVia") &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setVia(via);
      instance.addPropertyChangeListener(ContattoBean.VIA, l);
      instance.setVia(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.VIA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setVia(via);
      instance.addPropertyChangeListener(ContattoBean.VIA, l);
      instance.setVia(via);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.VIA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setVia(null);
      instance.addPropertyChangeListener(ContattoBean.VIA, l);
      instance.setVia(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getCivico method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testGetCivico() {
      System.out.println("getCivico");
      
      ContattoBean instance = new ContattoBean();
      
      String expResult = "unCivico";
      instance.setCivico(expResult);
      String result = instance.getCivico();
      assertEquals(expResult, result);
   }

   /**
    * Test of setCivico method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testSetCivico() {
      System.out.println("setCivico");
       eventFired=false;
      
      //Passaggio da due valori
      String civico="unCivico";
      String nuovoCivico="unNuovoCivico";
      ContattoBean instance = new ContattoBean();
      instance.setCivico(civico);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unCivico") &&
                    evt.getNewValue().equals("unNuovoCivico")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(ContattoBean.CIVICO, l);
      
      instance.setCivico(nuovoCivico);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.CIVICO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals("unNuovoCivico")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setCivico(null);
      instance.addPropertyChangeListener(ContattoBean.CIVICO, l);
      instance.setCivico(nuovoCivico);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.CIVICO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unCivico") &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setCivico(civico);
      instance.addPropertyChangeListener(ContattoBean.CIVICO, l);
      instance.setCivico(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.CIVICO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setCivico(civico);
      instance.addPropertyChangeListener(ContattoBean.CIVICO, l);
      instance.setCivico(civico);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.CIVICO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setCivico(null);
      instance.addPropertyChangeListener(ContattoBean.CIVICO, l);
      instance.setCivico(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getCAP method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testGetCAP() {
      System.out.println("getCAP");
      
      ContattoBean instance = new ContattoBean();
      
      String expResult = "unCAP";
      instance.setCAP(expResult);
      String result = instance.getCAP();
      assertEquals(expResult, result);
   }

   /**
    * Test of setCAP method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testSetCAP() {
      System.out.println("setCAP");
        eventFired=false;
      
      //Passaggio da due valori
      String cap="unCAP";
      String nuovoCAP="unNuovoCAP";
      ContattoBean instance = new ContattoBean();
      instance.setCAP(cap);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unCAP") &&
                    evt.getNewValue().equals("unNuovoCAP")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(ContattoBean.CAP, l);
      
      instance.setCAP(nuovoCAP);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.CAP, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals("unNuovoCAP")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setCAP(null);
      instance.addPropertyChangeListener(ContattoBean.CAP, l);
      instance.setCAP(nuovoCAP);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.CAP, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unCAP") &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setCAP(cap);
      instance.addPropertyChangeListener(ContattoBean.CAP, l);
      instance.setCAP(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.CAP, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setCAP(cap);
      instance.addPropertyChangeListener(ContattoBean.CAP, l);
      instance.setCAP(cap);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.CAP, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setCAP(null);
      instance.addPropertyChangeListener(ContattoBean.CAP, l);
      instance.setCAP(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getCitta method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testGetCitta() {
      System.out.println("getCitta");
      ContattoBean instance = new ContattoBean();
      
      String expResult = "unaCitta";
      instance.setCitta(expResult);
      String result = instance.getCitta();
      assertEquals(expResult, result);
   }

   /**
    * Test of setCitta method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testSetCitta() {
      System.out.println("setCitta");
       eventFired=false;
      
      //Passaggio da due valori
      String citta="unaCitta";
      String nuovaCitta="unaNuovaCitta";
      ContattoBean instance = new ContattoBean();
      instance.setCitta(citta);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unaCitta") &&
                    evt.getNewValue().equals("unaNuovaCitta")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(ContattoBean.CITTA, l);
      
      instance.setCitta(nuovaCitta);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.CITTA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals("unaNuovaCitta")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setCitta(null);
      instance.addPropertyChangeListener(ContattoBean.CITTA, l);
      instance.setCitta(nuovaCitta);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.CITTA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unaCitta") &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setCitta(citta);
      instance.addPropertyChangeListener(ContattoBean.CITTA, l);
      instance.setCitta(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.CITTA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setCitta(citta);
      instance.addPropertyChangeListener(ContattoBean.CITTA, l);
      instance.setCitta(citta);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.CITTA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setCitta(null);
      instance.addPropertyChangeListener(ContattoBean.CITTA, l);
      instance.setCitta(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getProvincia method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testGetProvincia() {
      System.out.println("getProvincia");
      ContattoBean instance = new ContattoBean();
      
      String expResult = "unaProvincia";
      instance.setProvincia(expResult);
      String result = instance.getProvincia();
      assertEquals(expResult, result);
   }

   /**
    * Test of setProvincia method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testSetProvincia() {
      System.out.println("setProvincia");
        eventFired=false;
      
      //Passaggio da due valori
      String provincia="unaProvincia";
      String nuovaProvincia="unaNuovaProvincia";
      ContattoBean instance = new ContattoBean();
      instance.setProvincia(provincia);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unaProvincia") &&
                    evt.getNewValue().equals("unaNuovaProvincia")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(ContattoBean.PROVINCIA, l);
      
      instance.setProvincia(nuovaProvincia);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.PROVINCIA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals("unaNuovaProvincia")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setProvincia(null);
      instance.addPropertyChangeListener(ContattoBean.PROVINCIA, l);
      instance.setProvincia(nuovaProvincia);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.PROVINCIA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unaProvincia") &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setProvincia(provincia);
      instance.addPropertyChangeListener(ContattoBean.PROVINCIA, l);
      instance.setProvincia(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.PROVINCIA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setProvincia(provincia);
      instance.addPropertyChangeListener(ContattoBean.PROVINCIA, l);
      instance.setProvincia(provincia);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.PROVINCIA, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setProvincia(null);
      instance.addPropertyChangeListener(ContattoBean.PROVINCIA, l);
      instance.setProvincia(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getTelefonoPrincipale method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testGetTelefonoPrincipale() {
      System.out.println("getTelefonoPrincipale");
      ContattoBean instance = new ContattoBean();
      
      String expResult = "unTelefonoPrincipale";
      instance.setTelefonoPrincipale(expResult);
      String result = instance.getTelefonoPrincipale();
      assertEquals(expResult, result);
   }

   /**
    * Test of setTelefonoPrincipale method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testSetTelefonoPrincipale() {
      System.out.println("setTelefonoPrincipale");
        eventFired=false;
      
      //Passaggio da due valori
      String telefonoPrincipale="unTelefonoPrincipale";
      String nuovoTelefonoPrincipale="unNuovoTelefonoPrincipale";
      ContattoBean instance = new ContattoBean();
      instance.setTelefonoPrincipale(telefonoPrincipale);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unTelefonoPrincipale") &&
                    evt.getNewValue().equals("unNuovoTelefonoPrincipale")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(ContattoBean.TELEFONO_PRINCIPALE, l);
      
      instance.setTelefonoPrincipale(nuovoTelefonoPrincipale);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.TELEFONO_PRINCIPALE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals("unNuovoTelefonoPrincipale")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setTelefonoPrincipale(null);
      instance.addPropertyChangeListener(ContattoBean.TELEFONO_PRINCIPALE, l);
      instance.setTelefonoPrincipale(nuovoTelefonoPrincipale);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.TELEFONO_PRINCIPALE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unTelefonoPrincipale") &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setTelefonoPrincipale(telefonoPrincipale);
      instance.addPropertyChangeListener(ContattoBean.TELEFONO_PRINCIPALE, l);
      instance.setTelefonoPrincipale(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.TELEFONO_PRINCIPALE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setTelefonoPrincipale(telefonoPrincipale);
      instance.addPropertyChangeListener(ContattoBean.TELEFONO_PRINCIPALE, l);
      instance.setTelefonoPrincipale(telefonoPrincipale);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.TELEFONO_PRINCIPALE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setTelefonoPrincipale(null);
      instance.addPropertyChangeListener(ContattoBean.TELEFONO_PRINCIPALE, l);
      instance.setTelefonoPrincipale(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getTelefonoSecondario method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testGetTelefonoSecondario() {
      System.out.println("getTelefonoSecondario");
          
      ContattoBean instance = new ContattoBean();
      
      String expResult = "unTelefonoSecondario";
      instance.setTelefonoSecondario(expResult);
      String result = instance.getTelefonoSecondario();
      assertEquals(expResult, result);
   }

   /**
    * Test of setTelefonoSecondario method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testSetTelefonoSecondario() {
      System.out.println("setTelefonoSecondario");
        eventFired=false;
      
      //Passaggio da due valori
      String telefonoSecondario="unTelefonoSecondario";
      String nuovoTelefonoSecondario="unNuovoTelefonoSecondario";
      ContattoBean instance = new ContattoBean();
      instance.setTelefonoSecondario(telefonoSecondario);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unTelefonoSecondario") &&
                    evt.getNewValue().equals("unNuovoTelefonoSecondario")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(ContattoBean.TELEFONO_SECONDARIO, l);
      
      instance.setTelefonoSecondario(nuovoTelefonoSecondario);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.TELEFONO_SECONDARIO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals("unNuovoTelefonoSecondario")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setTelefonoSecondario(null);
      instance.addPropertyChangeListener(ContattoBean.TELEFONO_SECONDARIO, l);
      instance.setTelefonoSecondario(nuovoTelefonoSecondario);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.TELEFONO_SECONDARIO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unTelefonoSecondario") &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setTelefonoSecondario(telefonoSecondario);
      instance.addPropertyChangeListener(ContattoBean.TELEFONO_SECONDARIO, l);
      instance.setTelefonoSecondario(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.TELEFONO_SECONDARIO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setTelefonoSecondario(telefonoSecondario);
      instance.addPropertyChangeListener(ContattoBean.TELEFONO_SECONDARIO, l);
      instance.setTelefonoSecondario(telefonoSecondario);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.TELEFONO_SECONDARIO, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setTelefonoSecondario(null);
      instance.addPropertyChangeListener(ContattoBean.TELEFONO_SECONDARIO, l);
      instance.setTelefonoSecondario(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of getFax method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testGetFax() {
      System.out.println("getFax");
      ContattoBean instance = new ContattoBean();
      
      String expResult = "unFax";
      instance.setFax(expResult);
      String result = instance.getFax();
      assertEquals(expResult, result);
   }

   /**
    * Test of setFax method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testSetFax() {
      System.out.println("setFax");
       eventFired=false;
      
      //Passaggio da due valori
      String fax="unFax";
      String nuovoFax="unNuovoFax";
      ContattoBean instance = new ContattoBean();
      instance.setFax(fax);
      
      PropertyChangeListener l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unFax") &&
                    evt.getNewValue().equals("unNuovoFax")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.addPropertyChangeListener(ContattoBean.FAX, l);
      
      instance.setFax(nuovoFax);
      assertEquals(true, eventFired);
      
      //Passaggio da null ad un valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.FAX, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue()==null &&
                    evt.getNewValue().equals("unNuovoFax")) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setFax(null);
      instance.addPropertyChangeListener(ContattoBean.FAX, l);
      instance.setFax(nuovoFax);
      
      assertEquals(true, eventFired);
      
      //Passaggio da un valore ad null
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.FAX, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getOldValue().equals("unFax") &&
                    evt.getNewValue()==null) {
               
               eventFired=true;
            } else {
               eventFired=false;
            }
         }
      };
      instance.setFax(fax);
      instance.addPropertyChangeListener(ContattoBean.FAX, l);
      instance.setFax(null);
      
      assertEquals(true, eventFired);
      
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.FAX, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setFax(fax);
      instance.addPropertyChangeListener(ContattoBean.FAX, l);
      instance.setFax(fax);
      
      assertEquals(false, eventFired);
      
      //Doppia impostazione di null
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.FAX, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setFax(null);
      instance.addPropertyChangeListener(ContattoBean.FAX, l);
      instance.setFax(null);
      
      assertEquals(false, eventFired);
   }

   /**
    * Test of setIdNazione method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testSetIdNazione() {
      System.out.println("setIdNazione");
      
      //Passaggio da un valore a un altro
      
      final int id=1;
      final int nuovoId=4;
      
      ContattoBean instance = new ContattoBean();
      instance.setIdNazione(id);
      
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
      instance.addPropertyChangeListener(ContattoBean.ID_NAZIONE, l);
      
      instance.setIdNazione(nuovoId);
      assertEquals(true, eventFired);
      
           
      //Impostazione dello stesso valore
      eventFired=false;
      instance.removePropertyChangeListener(ContattoBean.ID_NAZIONE, l);
      l = new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            eventFired=true;
         }
      };
      instance.setIdNazione(1);
      instance.addPropertyChangeListener(ContattoBean.ID_NAZIONE, l);
      instance.setIdNazione(1);
      
      assertEquals(false, eventFired);
   }

      /**
    * Test of getIdNazione method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testGetIdNazione() {
      System.out.println("getIdNazione");
      
      ContattoBean instance = new ContattoBean();
      
      int expResult = 1;
      instance.setIdNazione(expResult);
      int result = instance.getIdNazione();
      assertEquals(expResult, result);
   }


 

      /**
    * Funzione di aiuto per determinare se esiste un contatto con un determinato id.
    * @param id id dell'occorrenza da trovare.
    * @return {@code true} se l'occorrenza esiste, {@code false}.
    * @throws Exception Vengono rilanciate tutte le eccezioni che si verificano.
    **/
   private static boolean esisteContatto(int id) throws Exception {
      cercaContattoBehavior(1);
      java.util.List<nu.mine.egoless.didattica.ws.contattoclient.Contatto> result=null;
      
      try { // Call Web Service Operation
         nu.mine.egoless.didattica.ws.contattoclient.WSContattoService service = new nu.mine.egoless.didattica.ws.contattoclient.WSContattoService();
         nu.mine.egoless.didattica.ws.contattoclient.WSContatto port = service.getWSContattoPort();
         // TODO initialize WS operation arguments here
         nu.mine.egoless.didattica.ws.contattoclient.ParametriRicercaContatto insiemeParametri = new nu.mine.egoless.didattica.ws.contattoclient.ParametriRicercaContatto();
         insiemeParametri.setId(id);
         // TODO process result here
          result= port.cercaContatto(insiemeParametri);
         
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
    * @param value {@true} se {@code recuperaTipiContatto} deve restituire {@code null};
    *              {@false} altrimenti.
    */
   public static void cercaContattoBehavior(int value) {
      try { // Call Web Service Operation
         WSDidaStubService service = new WSDidaStubService();
         WSDidaStub port = service.getWSDidaStubPort();
         
         port.cercaContattoBehavior(value);
      } catch (Exception ex) {
         //Non gestiamo le eccezioni: lasciamo che il test continui
      }
   }
   
   /**
    * Test of salvaSuWS method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testSalvaSuWS() throws Exception {
      System.out.println("salvaSuWS");
      
      ContattoBean instance = new ContattoBean();
      assertEquals(instance.getId(), Costanti.ID_NUOVO_CONTATTO);
      
      instance.setCitta("cittaDiProva");
      instance.salvaSuWS();
      assertFalse(instance.getId()==Costanti.ID_NUOVO_CONTATTO);
      assertEquals(true,esisteContatto(instance.getId()));
   }

   /**
    * Test of caricaDaWS method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testCaricaDaWS() throws Exception {
      System.out.println("caricaDaWS");
      
     // provo a caricare un elemento presente
      cercaContattoBehavior(2);
      ContattoBean instance = new ContattoBean();
      
      // carico l'elemento con id=1
      instance.caricaDaWS(1);
      assertEquals(1, instance.getId());
      
      // provo a caricare un elemento non presente
      cercaContattoBehavior(0);
      instance = new ContattoBean();
      instance.caricaDaWS(1);
      assertEquals(new ContattoBean().getId(), instance.getId());
      
      // provo a richiedere l'id di default
      instance = new ContattoBean();
          
      instance.caricaDaWS(Costanti.ID_NUOVO_CONTATTO);
      
      assertEquals(Costanti.ID_NUOVO_CONTATTO, instance.getId());
      
   }

   /**
    * Test of cancellaContatto method, of class nu.mine.egoless.didattica.app.bean.ContattoBean.
    */
   public void testCancellaContatto() throws Exception {
      System.out.println("cancellaContatto");
      ContattoBean instance = new ContattoBean();
            
      instance.salvaSuWS();
      //verifico comunque il caricamento
      assertTrue(esisteContatto(instance.getId()));
      instance.cancellaContatto();
      // verifico la cancellazione
      assertEquals(false, esisteContatto(instance.getId()));
   }



   
}
