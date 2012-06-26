/*
 * Nome file:Classe.java
 * Data creazione:27 febbraio 2007, 20.04
 * Info svn: $Id: Classe.java 694 2007-03-25 12:02:13Z eric $
 */

package com.swellsys.pqs.ws.api;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import nu.mine.egoless.api.ws.*;

/**
 * Rappresenta la classe.
 */
@Entity
public class Classe extends Elemento 
        implements Serializable{
   
   /** Creates a new instance of Classe */
   public Classe() {
   }
   
   /**
    * Holds value of property annoCorso.
    */
   private Integer annoCorso;
   
   /**
    * Getter for property annoCorso.
    * @return Value of property annoCorso.
    */
   public Integer getAnnoCorso() {
      return this.annoCorso;
   }
   
   /**
    * Setter for property annoCorso.
    * @param annoCorso New value of property annoCorso.
    */
   public void setAnnoCorso(Integer annoCorso) {
      this.annoCorso = annoCorso;
   }
   
   /**
    * Holds value of property sezione.
    */
   private char sezione;
   
   /**
    * Getter for property sezione.
    * @return Value of property sezione.
    */
   public char getSezione() {
      return this.sezione;
   }
   
   /**
    * Setter for property sezione.
    * @param sezione New value of property sezione.
    */
   public void setSezione(char sezione) {
      this.sezione = sezione;
   }

   /**
    * Holds value of property indirizzoStudiId.
    */
   private int indirizzoStudiId;

   /**
    * Getter for property idIndirizzoStudi.
    * @return Value of property idIndirizzoStudi.
    */
   public int getIndirizzoStudiId() {
      return this.indirizzoStudiId;
   }

   /**
    * Setter for property idIndirizzoStudi.
    * @param idIndirizzoStudi New value of property idIndirizzoStudi.
    */
   public void setIndirizzoStudiId(int indirizzoStudiId) {
      this.indirizzoStudiId = indirizzoStudiId;
   }

   /**
    * Holds value of property annoScolasticoId.
    */
   private int annoScolasticoId;

   /**
    * Getter for property annoScolastico.
    * @return Value of property annoScolastico.
    */
   public int getAnnoScolasticoId() {
      return this.annoScolasticoId;
   }

   /**
    * Setter for property annoScolastico.
    * @param annoScolastico New value of property annoScolastico.
    */
   public void setAnnoScolasticoId(int annoScolasticoId) {
      this.annoScolasticoId = annoScolasticoId;
   }

   /**
    * Holds value of property docenteId.
    */
   private int docenteId;

   /**
    * Getter for property coordinatore.
    * @return Value of property coordinatore.
    */
   public int getDocenteId() {
      return this.docenteId;
   }

   /**
    * Setter for property coordinatore.
    * @param coordinatore New value of property coordinatore.
    */
   public void setDocenteId(int docenteId) {
      this.docenteId = docenteId;
   }
}
