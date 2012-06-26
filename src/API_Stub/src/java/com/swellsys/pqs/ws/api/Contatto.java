/*
 * Nome file:Contatto.java
 * Data creazione:27 febbraio 2007, 20.04
 * Info svn: $Id: Contatto.java 819 2007-03-26 18:41:34Z eric $
 */

package com.swellsys.pqs.ws.api;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe che rappresenta un contatto.
 */
@Entity
public class Contatto 
        extends Elemento implements Serializable{
   
   private String via;
   private String civico;
   private String cap;
   private String citta;
   private String provincia;
   private String telefonoPrincipale;
   private String telefonoSecondario;
   private String fax;
   private int nazioneId;
   
   /** Creates a new instance of Contatto */
   public Contatto() {
   }
   
   /**
    * Getter for property via.
    * @return Value of property via.
    */
   public String getVia() {
      return this.via;
   }
   
   /**
    * Setter for property via.
    * @param via New value of property via.
    */
   public void setVia(String via) {
      this.via = via;
   }
   
   /**
    * Getter for property civico.
    * @return Value of property civico.
    */
   public String getCivico() {
      return this.civico;
   }
   
   /**
    * Setter for property civico.
    * @param civico New value of property civico.
    */
   public void setCivico(String civico) {
      this.civico = civico;
   }

   /**
    * Getter for property CAP.
    * 
    * @return Value of property cap.
    */
   public String getCap() {
      return this.cap;
   }
   
   /**
    * Setter for property cap.
    * 
    * @param cap New value of property cap.
    */
   public void setCap(String cap) {
      this.cap = cap;
   }

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

   /**
    * Getter for property provincia.
    * @return Value of property provincia.
    */
   public String getProvincia() {
      return this.provincia;
   }
   
   /**
    * Setter for property provincia.
    * @param provincia New value of property provincia.
    */
   public void setProvincia(String provincia) {
      this.provincia = provincia;
   }
   
   /**
    * Getter for property telefonoPrincipale.
    * @return Value of property telefonoPrincipale.
    */
   public String getTelefonoPrincipale() {
      return this.telefonoPrincipale;
   }
   
   /**
    * Setter for property telefonoPrincipale.
    * @param telefonoPrincipale New value of property telefonoPrincipale.
    */
   public void setTelefonoPrincipale(String telefonoPrincipale) {
      this.telefonoPrincipale = telefonoPrincipale;
   }
      
   /**
    * Getter for property telefonoSecondario.
    * @return Value of property telefonoSecondario.
    */
   public String getTelefonoSecondario() {
      return this.telefonoSecondario;
   }
   
   /**
    * Setter for property telefonoSecondario.
    * @param telefonoSecondario New value of property telefonoSecondario.
    */
   public void setTelefonoSecondario(String telefonoSecondario) {
      this.telefonoSecondario = telefonoSecondario;
   }
   
   /**
    * Getter for property fax.
    * @return Value of property fax.
    */
   public String getFax() {
      return this.fax;
   }
   
   /**
    * Setter for property fax.
    * @param fax New value of property fax.
    */
   public void setFax(String fax) {
      this.fax = fax;
   }

   /**
    * Getter for property idNazione.
    * @return Value of property idNazione.
    */
   public int getNazioneId() {
      return this.nazioneId;
   }

   /**
    * Setter for property idNazione.
    * @param idNazione New value of property idNazione.
    */
   public void setNazioneId(int nazioneId) {
      this.nazioneId = nazioneId;
   }
   
}
