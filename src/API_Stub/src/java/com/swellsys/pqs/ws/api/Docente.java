/*
 * Nome file:Docente.java
 * Data creazione:27 febbraio 2007, 20.05
 * Info svn: $Id: Docente.java 830 2007-03-26 20:23:21Z eric $
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
public class Docente
        extends Persona implements Serializable {
   
   /**
    * Creates a new instance of Docente
    */
   public Docente() {
   }
   
   /**
    * Holds value of property dataAssunzione.
    */
   @OneToOne(cascade=CascadeType.ALL)
   private Date dataAssunzione;
   
   /**
    * Getter for property dataAssunzione.
    * @return Value of property dataAssunzione.
    */
   public Date getDataAssunzione() {
      return this.dataAssunzione;
   }
   
   /**
    * Setter for property dataAssunzione.
    * @param dataAssunzione New value of property dataAssunzione.
    */
   public void setDataAssunzione(Date dataAssunzione) {
      this.dataAssunzione = dataAssunzione;
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

}
