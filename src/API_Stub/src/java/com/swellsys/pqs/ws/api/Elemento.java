/*
 * Nome file: Elemento.java
 * Data creazione: 24 marzo 2007, 12.20
 * Info svn: $Id: Elemento.java 791 2007-03-26 12:18:48Z eric $
 */

package com.swellsys.pqs.ws.api;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * RAdice di tutta la gerarchia.
 */
@MappedSuperclass//@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Elemento 
        implements Serializable {

   /**
    * Holds value of property id.
    */
   @Id
   //@GeneratedValue(strategy = GenerationType.AUTO)
   protected int id;


   /**
    * Getter for property id.
    * @return Value of property id.
    */
   @XmlAttribute(required = true)
   public int getId() {
      return this.id;
   }


   /**
    * Setter for property id.
    * @param id New value of property id.
    */
   public void setId(int id) {
      this.id = id;
   }
   
}
