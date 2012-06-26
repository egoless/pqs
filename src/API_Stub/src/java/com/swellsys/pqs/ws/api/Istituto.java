/*
 * Nome file: Istituto.java
 * Data creazione: 24 marzo 2007, 13.48
 * Info svn: $Id: Istituto.java 642 2007-03-24 14:21:42Z eric $
 */

package com.swellsys.pqs.ws.api;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Class description
 */
@Entity
public class Istituto
        extends Elemento implements Serializable {
   
   protected String nome;
   @OneToOne
   protected Contatto sedeAmministrativa;
   
   /**
    * Gets the value of the nome property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getNome() {
      return nome;
   }
   
   /**
    * Sets the value of the nome property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setNome(String value) {
      this.nome = value;
   }
   
   /**
    * Gets the value of the sedeAmministrativa property.
    *
    * @return
    *     possible object is
    *     {@link Contatto }
    *
    */
   public Contatto getSedeAmministrativa() {
      return sedeAmministrativa;
   }
   
   /**
    * Sets the value of the sedeAmministrativa property.
    *
    * @param value
    *     allowed object is
    *     {@link Contatto }
    *
    */
   public void setSedeAmministrativa(Contatto value) {
      this.sedeAmministrativa = value;
   }
   
}

