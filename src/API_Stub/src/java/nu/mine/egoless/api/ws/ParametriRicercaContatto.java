/*
 * Nome file:ParametriRicercaContatto.java
 * Data creazione:1 marzo 2007, 17.16
 * Info svn: $Id: ParametriRicercaContatto.java 830 2007-03-26 20:23:21Z eric $
 */

package nu.mine.egoless.api.ws;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author STEFANO
 */
public class ParametriRicercaContatto
extends ParametriRicerca implements Serializable{
   
   /** Creates a new instance of ParametriRicercaContatto */
   public ParametriRicercaContatto() {
   }
   
   /**
    * Holds value of property idPersona.
    */
   private int idPersona;
   
   /**
    * Getter for property idPersona.
    * @return Value of property idPersona.
    */
   public int getIdPersona() {
      return this.idPersona;
   }
   
   /**
    * Setter for property idPersona.
    * @param idPersona New value of property idPersona.
    */
   public void setIdPersona(int idPersona) {
      this.idPersona = idPersona;
   }
   
   /**
    * Holds value of property idNazione.
    */
   private int idNazione;
   
   /**
    * Getter for property idNazione.
    * @return Value of property idNazione.
    */
   public int getIdNazione() {
      return this.idNazione;
   }
   
   /**
    * Setter for property idNazione.
    * @param idNazione New value of property idNazione.
    */
   public void setIdNazione(int idNazione) {
      this.idNazione = idNazione;
   }
   
   /**
    * Holds value of property citta.
    */
   private String citta;
   
   /**
    * Getter for property citta.
    * @return Value of property citta.
    */
   public String getCitta() {
      return this.citta;
   }
   
   /**
    * Setter for property citta.
    * @param citta New value of property citta.
    */
   public void setCitta(String citta) {
      this.citta = citta;
   }
}
