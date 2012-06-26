/*
 * Nome file:ParametriRicercaVoto.java
 * Data creazione:1 marzo 2007, 17.16
 * Info svn: $Id: ParametriRicercaVoto.java 830 2007-03-26 20:23:21Z eric $
 */

package nu.mine.egoless.api.ws;

import com.swellsys.pqs.ws.api.Date;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author STEFANO
 */
public class ParametriRicercaVoto 
extends ParametriRicerca implements Serializable{
   
   /** Creates a new instance of ParametriRicercaStudente */
   public ParametriRicercaVoto() {
   }
   
   /**
    * Holds value of property idDocente.
    */
   private int idDocente;
   
   /**
    * Getter for property cognome.
    * @return Value of property cognome.
    */
   public int getIdDocente() {
      return this.idDocente;
   }
   
   /**
    * Setter for property cognome.
    * @param cognome New value of property cognome.
    */
   public void setIdDocente(int idDocente) {
      this.idDocente = idDocente;
   }
   
   /**
    * Holds value of property idStudente.
    */
   private int idStudente;
   
   /**
    * Getter for property idClass.
    * @return Value of property idClass.
    */
   public int getIdStudente() {
      return this.idStudente;
   }
   
   /**
    * Setter for property idClass.
    * @param idClass New value of property idClass.
    */
   public void setIdStudente(int idStudente) {
      this.idStudente = idStudente;
   }
   
   /**
    * Holds value of property idMateria.
    */
   private int idMateria;
   
   /**
    * Getter for property idMateria.
    * @return Value of property idMateria.
    */
   public int getIdMateria() {
      return this.idMateria;
   }
   
   /**
    * Setter for property idMateria.
    * @param idMateria New value of property idMateria.
    */
   public void setIdMateria(int idMateria) {
      this.idMateria = idMateria;
   }

   /**
    * Holds value of property dataOra.
    */
   private Date dataOra;

   /**
    * Getter for property dataOra.
    * @return Value of property dataOra.
    */
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
}
