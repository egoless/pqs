/*
 * Nome file:Voto.java
 * Data creazione:27 febbraio 2007, 20.06
 * Info svn: $Id: Voto.java 830 2007-03-26 20:23:21Z eric $
 */

package com.swellsys.pqs.ws.modulo.registro;

import com.swellsys.pqs.ws.api.Date;
import com.swellsys.pqs.ws.api.Elemento;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author STEFANO
 */
@Entity
public class Voto
        extends Elemento implements Serializable{
   
   /** Creates a new instance of Voto */
   public Voto() {
   }
   
   /**
    * Holds value of property dataOra.
    */
   @OneToOne(cascade=CascadeType.ALL)
   private Date dataOra;
   
   /**
    * Getter for property dataOra.
    * @return Value of property dataOra.
    */
   @XmlElement(name="data")
   public Date getDataOra() {
      return this.dataOra;
   }
   
   /**
    * Setter for property dataOra.
    * @param dataOra New value of property dataOra.
    */
   public void setDataOra(Date dataOra) {
      this.dataOra = dataOra;
   }
   
   /**
    * Holds value of property tipoVotoId.
    */
   private int tipoVotoId;
   
   /**
    * Getter for property valore.
    * @return Value of property valore.
    */
   public int getTipoVotoId() {
      return this.tipoVotoId;
   }
   
   /**
    * Setter for property valore.
    * @param valore New value of property valore.
    */
   public void setTipoVotoId(int tipoVotoId) {
      this.tipoVotoId = tipoVotoId;
   }

   /**
    * Holds value of property docenteId.
    */
   private int docenteId;

   /**
    * Getter for property idInsegnante.
    * @return Value of property idInsegnante.
    */
   public int getDocenteId() {
      return this.docenteId;
   }

   /**
    * Setter for property idInsegnante.
    * @param idInsegnante New value of property idInsegnante.
    */
   public void setDocenteId(int docenteId) {
      this.docenteId = docenteId;
   }

   /**
    * Holds value of property materiaId.
    */
   private int materiaId;

   /**
    * Getter for property idMateria.
    * @return Value of property idMateria.
    */
   public int getMateriaId() {
      return this.materiaId;
   }

   /**
    * Setter for property idMateria.
    * @param idMateria New value of property idMateria.
    */
   public void setMateriaId(int materiaId) {
      this.materiaId = materiaId;
   }

   /**
    * Holds value of property studenteId.
    */
   private int studenteId;

   /**
    * Getter for property idStudente.
    * @return Value of property idStudente.
    */
   public int getStudenteId() {
      return this.studenteId;
   }

   /**
    * Setter for property idStudente.
    * @param idStudente New value of property idStudente.
    */
   public void setStudenteId(int studenteId) {
      this.studenteId = studenteId;
   }

   /**
    * Holds value of property tipoProvaId.
    */
   private int tipoProvaId;

   /**
    * Getter for property idTipoVoto.
    * @return Value of property idTipoVoto.
    */
   public int getTipoProvaId() {
      return this.tipoProvaId;
   }

   /**
    * Setter for property idTipoVoto.
    * @param idTipoVoto New value of property idTipoVoto.
    */
   public void setTipoProvaId(int tipoProvaId) {
      this.tipoProvaId = tipoProvaId;
   }
   /**
    * Holds value of property descrizione.
    */
   private String descrizione;

   /**
    * Getter for property descrizione.
    * @return Value of property descrizione.
    */
   public String getDescrizione() {
      return this.descrizione;
   }

   /**
    * Setter for property descrizione.
    * @param descrizione New value of property descrizione.
    */
   public void setDescrizione(String descrizione) {
      this.descrizione = descrizione;
   }

   /**
    * Holds value of property classeId.
    */
   private int classeId;

   /**
    * Getter for property classeId.
    * @return Value of property classeId.
    */
   public int getClasseId() {
      return this.classeId;
   }

   /**
    * Setter for property classeId.
    * @param classeId New value of property classeId.
    */
   public void setClasseId(int classeId) {
      this.classeId = classeId;
   }


   
}
