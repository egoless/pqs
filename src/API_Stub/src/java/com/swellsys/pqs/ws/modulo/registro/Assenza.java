/*
 *Nome File: Assenza.java
 *Data creazione: 27 febbraio 2007, 20.04
 *Info svn: $Id: Assenza.java 830 2007-03-26 20:23:21Z eric $
 */

package com.swellsys.pqs.ws.modulo.registro;

import com.swellsys.pqs.ws.api.Date;
import com.swellsys.pqs.ws.api.Elemento;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author STEFANO
 */
@Entity
public class Assenza
        extends Elemento implements Serializable {
   
   /** Creates a new instance of Assenza */
   public Assenza() {
   }
   
   /**
    * Holds value of property dataOraInizio.
    */
   @OneToOne(cascade=CascadeType.ALL)
   private Date dataOraInizio;
   
   /**
    * Getter for property dataInizio.
    * @return Value of property dataInizio.
    */
   public Date getDataOraInizio() {
      return this.dataOraInizio;
   }
   
   /**
    * Setter for property dataInizio.
    * @param dataInizio New value of property dataInizio.
    */
   public void setDataOraInizio(Date dataOraInizio) {
      this.dataOraInizio = dataOraInizio;
   }
   
   /**
    * Holds value of property dataOraFine.
    */
   @OneToOne(cascade=CascadeType.ALL)
   private Date dataOraFine;
   
   /**
    * Getter for property dataFine.
    * @return Value of property dataFine.
    */
   public Date getDataOraFine() {
      return this.dataOraFine;
   }
   
   /**
    * Setter for property dataFine.
    * @param dataFine New value of property dataFine.
    */
   public void setDataOraFine(Date dataOraFine) {
      this.dataOraFine = dataOraFine;
   }
   
   /**
    * Holds value of property giustificazione.
    */
   private String giustificazione;
   
   /**
    * Getter for property giustificata.
    * @return Value of property giustificata.
    */
   public String getGiustificazione() {
      return this.giustificazione;
   }
   
   /**
    * Setter for property giustificata.
    * @param giustificata New value of property giustificata.
    */
   public void setGiustificazione(String giustificazione) {
      this.giustificazione = giustificazione;
   }
   
   /**
    * Holds value of property eventoId.
    */
   private int eventoId;
   
   /**
    * Getter for property note.
    * @return Value of property note.
    */
   public int getEventoId() {
      return this.eventoId;
   }
   
   /**
    * Setter for property note.
    * @param note New value of property note.
    */
   public void setEventoId(int eventoId) {
      this.eventoId = eventoId;
   }

   /**
    * Holds value of property personaId.
    */
   private int personaId;

   /**
    * Getter for property idStudente.
    * @return Value of property idStudente.
    */
   public int getPersonaId() {
      return this.personaId;
   }

   /**
    * Setter for property idStudente.
    * @param idStudente New value of property idStudente.
    */
   public void setPersonaId(int personaId) {
      this.personaId = personaId;
   }

   /**
    * Holds value of property tipoAssenzaId.
    */
   private int tipoAssenzaId;

   /**
    * Getter for property idTipoAssenza.
    * @return Value of property idTipoAssenza.
    */
   public int getTipoAssenzaId() {
      return this.tipoAssenzaId;
   }

   /**
    * Setter for property idTipoAssenza.
    * @param idTipoAssenza New value of property idTipoAssenza.
    */
   public void setTipoAssenzaId(int tipoAssenzaId) {
      this.tipoAssenzaId = tipoAssenzaId;
   }
   
}
