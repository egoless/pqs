/*
 * Nome file:Studente.java
 * Data creazione:27 febbraio 2007, 20.06
 * Info svn: $Id: Studente.java 830 2007-03-26 20:23:21Z eric $
 */


package com.swellsys.pqs.ws.api;

import com.swellsys.pqs.ws.modulo.registro.Assenza;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author STEFANO
 */
@Entity
public class Studente extends Persona{
   
   /** Creates a new instance of Studente */
   public Studente() {
   }
   
   /**
    * Holds value of property matricola.
    */
   private String matricola;
   
   /**
    * Getter for property matricola.
    * @return Value of property matricola.
    */
   public String getMatricola() {
      return this.matricola;
   }
   
   /**
    * Setter for property matricola.
    * @param matricola New value of property matricola.
    */
   public void setMatricola(String matricola) {
      this.matricola = matricola;
   }
   
   /**
    * Holds value of property dataIscrizione.
    */
   @OneToOne(cascade=CascadeType.ALL)
   private Date dataIscrizione;
   
   /**
    * Getter for property dataIscrizione.
    * @return Value of property dataIscrizione.
    */
   public Date getDataIscrizione() {
      return this.dataIscrizione;
   }
   
   /**
    * Setter for property dataIscrizione.
    * @param dataIscrizione New value of property dataIscrizione.
    */
   public void setDataIscrizione(Date dataIscrizione) {
      this.dataIscrizione = dataIscrizione;
   }

   /**
    * Holds value of property idClasse.
    */
   private int idClasse;

   /**
    * Getter for property idClasse.
    * @return Value of property idClasse.
    */
   public int getIdClasse() {
      return this.idClasse;
   }

   /**
    * Setter for property idClasse.
    * @param idClasse New value of property idClasse.
    */
   public void setIdClasse(int idClasse) {
      this.idClasse = idClasse;
   }
   

   
   
}
