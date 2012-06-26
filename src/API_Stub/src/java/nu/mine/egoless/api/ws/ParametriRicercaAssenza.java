/*
 * Nome file:ParametriRicercaAssenza.java
 * Data creazione:1 marzo 2007, 17.16
 * Info svn: $Id: ParametriRicercaAssenza.java 830 2007-03-26 20:23:21Z eric $
 */

package nu.mine.egoless.api.ws;

import com.swellsys.pqs.ws.api.Date;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author STEFANO
 */
public class ParametriRicercaAssenza 
extends ParametriRicerca implements Serializable {
   
   /** Creates a new instance of ParametriRicercaAssenza */
   public ParametriRicercaAssenza() {
   }
   
   /**
    * Holds value of property idStudente.
    */
   private int idStudente;
   
   /**
    * Getter for property idStudente.
    * @return Value of property idStudente.
    */
   public int getIdStudente() {
      return this.idStudente;
   }
   
   /**
    * Setter for property idStudente.
    * @param idStudente New value of property idStudente.
    */
   public void setIdStudente(int idStudente) {
      this.idStudente = idStudente;
   }
   
   /**
    * Holds value of property giustificata.
    */
   private Boolean giustificata;
   
   /**
    * Getter for property giustificata.
    * @return Value of property giustificata.
    */
   public Boolean getGiustificata() {
      return this.giustificata;
   }
   
   /**
    * Setter for property giustificata.
    * @param giustificata New value of property giustificata.
    */
   public void setGiustificata(Boolean giustificata) {
      this.giustificata = giustificata;
   }
   
   /**
    * Holds value of property dataInizio.
    */
   private Date dataInizio;
   
   /**
    * Getter for property dataInizio.
    * @return Value of property dataInizio.
    */
   public Date getDataInizio() {
      return this.dataInizio;
   }
   
   /**
    * Setter for property dataInizio.
    * @param dataInizio New value of property dataInizio.
    */
   public void setDataInizio(Date dataInizio) {
      this.dataInizio = dataInizio;
   }
   
   /**
    * Holds value of property dataFine.
    */
   private Date dataFine;
   
   /**
    * Getter for property dataFine.
    * @return Value of property dataFine.
    */
   public Date getDataFine() {
      return this.dataFine;
   }
   
   /**
    * Setter for property dataFine.
    * @param dataFine New value of property dataFine.
    */
   public void setDataFine(Date dataFine) {
      this.dataFine = dataFine;
   }

   /**
    * Holds value of property data.
    */
   @Column(name="_data")
   private Date data;

   /**
    * Getter for property data.
    * @return Value of property data.
    */
   public Date getData() {
      return this.data;
   }

   /**
    * Setter for property data.
    * @param data New value of property data.
    */
   public void setData(Date data) {
      this.data = data;
   }
}
