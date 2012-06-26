/*
 * Nome file:Persona.java
 * Data creazione:4 marzo 2007, 15.13
 * Info svn: $Id: Persona.java 830 2007-03-26 20:23:21Z eric $
 */


package com.swellsys.pqs.ws.api;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author STEFANO
 */
@Entity
public class Persona
        extends Elemento implements Serializable {
   
   /** Creates a new instance of Persona */
   public Persona() {
   }
   
   /**
    * Holds value of property nome.
    */
   private String nome;
   
   /**
    * Getter for property nome.
    * @return Value of property nome.
    */
   public String getNome() {
      return this.nome;
   }
   
   /**
    * Setter for property nome.
    * @param nome New value of property nome.
    */
   public void setNome(String nome) {
      this.nome = nome;
   }
   
   /**
    * Holds value of property cognome.
    */
   private String cognome;
   
   /**
    * Getter for property cognome.
    * @return Value of property cognome.
    */
   public String getCognome() {
      return this.cognome;
   }
   
   /**
    * Setter for property cognome.
    * @param cognome New value of property cognome.
    */
   public void setCognome(String cognome) {
      this.cognome = cognome;
   }
   
   /**
    * Holds value of property dataNascita.
    */
   @OneToOne(cascade=CascadeType.ALL)
   private Date dataNascita;
   
   /**
    * Getter for property dataNascita.
    * @return Value of property dataNascita.
    */
   public Date getDataNascita() {
      return this.dataNascita;
   }
   
   /**
    * Setter for property dataNascita.
    * @param dataNascita New value of property dataNascita.
    */
   public void setDataNascita(Date dataNascita) {
      this.dataNascita = dataNascita;
   }
   
   /**
    * Holds value of property codiceFiscale.
    */
   private String codiceFiscale;
   
   /**
    * Getter for property codiceFiscale.
    * @return Value of property codiceFiscale.
    */
   public String getCodiceFiscale() {
      return this.codiceFiscale;
   }
   
   /**
    * Setter for property codiceFiscale.
    * @param codiceFiscale New value of property codiceFiscale.
    */
   public void setCodiceFiscale(String codiceFiscale) {
      this.codiceFiscale = codiceFiscale;
   }
   
   /**
    * Holds value of property portatoreHandicap.
    */
   private Boolean portatoreHandicap;
   
   /**
    * Getter for property portatoreHandicap.
    * @return Value of property portatoreHandicap.
    */
   public Boolean getPortatoreHandicap() {
      return this.portatoreHandicap;
   }
   
   /**
    * Setter for property portatoreHandicap.
    * @param portatoreHandicap New value of property portatoreHandicap.
    */
   public void setPortatoreHandicap(Boolean portatoreHandicap) {
      this.portatoreHandicap = portatoreHandicap;
   }

   /**
    * Holds value of property religioneId.
    */
   private int religioneId;

   /**
    * Getter for property idReligione.
    * @return Value of property idReligione.
    */
   public int getReligioneId() {
      return this.religioneId;
   }

   /**
    * Setter for property idReligione.
    * @param idReligione New value of property idReligione.
    */
   public void setReligioneId(int religioneId) {
      this.religioneId = religioneId;
   }

   /**
    * Holds value of property nazioneId.
    */
   private int nazioneId;

   /**
    * Getter for property idNazionalita.
    * @return Value of property idNazionalita.
    */
   public int getNazioneId() {
      return this.nazioneId;
   }

   /**
    * Setter for property idNazionalita.
    * @param idNazionalita New value of property idNazionalita.
    */
   public void setNazioneId(int nazioneId) {
      this.nazioneId = nazioneId;
   }

   /**
    * Holds value of property contattoId.
    */
   private int contattoId;

   /**
    * Getter for property idIndirizzoResidenza.
    * @return Value of property idIndirizzoResidenza.
    */
   public int getContattoId() {
      return this.contattoId;
   }

   /**
    * Setter for property idIndirizzoResidenza.
    * @param idIndirizzoResidenza New value of property idIndirizzoResidenza.
    */
   public void setContattoId(int contattoId) {
      this.contattoId = contattoId;
   }

   /**
    * Holds value of property sesso.
    */
   private Integer sesso;

   /**
    * Getter for property sesso.
    * @return Value of property sesso.
    */
   public Integer getSesso() {
      return this.sesso;
   }

   /**
    * Setter for property sesso.
    * @param sesso New value of property sesso.
    */
   public void setSesso(Integer sesso) {
      this.sesso = sesso;
   }

   /**
    * Holds value of property istitutoId.
    */
   private int istitutoId;

   /**
    * Getter for property istitutoId.
    * @return Value of property istitutoId.
    */
   public int getIstitutoId() {
      return this.istitutoId;
   }

   /**
    * Setter for property istitutoId.
    * @param istitutoId New value of property istitutoId.
    */
   public void setIstitutoId(int istitutoId) {
      this.istitutoId = istitutoId;
   }

}
