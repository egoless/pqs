/*
 * Nome file: Date.java
 * Data creazione: 24 marzo 2007, 13.47
 * Info svn: $Id: Date.java 830 2007-03-26 20:23:21Z eric $
 */

package com.swellsys.pqs.ws.api;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Surrogato della data da usare tramite WS.
 */
@Entity(name="Date_")
public class Date implements Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   protected int id;
   
    protected int anno;
    @Column(name="date_")
    protected String date;
    protected int giorno;
    protected int mese;
    protected int minuti;
    protected int ore;
    protected int secondi;

    /**
     * Gets the value of the anno property.
     * 
     */
    public int getAnno() {
        return anno;
    }

    /**
     * Sets the value of the anno property.
     * 
     */
    public void setAnno(int value) {
        this.anno = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the giorno property.
     * 
     */
    public int getGiorno() {
        return giorno;
    }

    /**
     * Sets the value of the giorno property.
     * 
     */
    public void setGiorno(int value) {
        this.giorno = value;
    }

    /**
     * Gets the value of the mese property.
     * 
     */
    public int getMese() {
        return mese;
    }

    /**
     * Sets the value of the mese property.
     * 
     */
    public void setMese(int value) {
        this.mese = value;
    }

    /**
     * Gets the value of the minuti property.
     * 
     */
    public int getMinuti() {
        return minuti;
    }

    /**
     * Sets the value of the minuti property.
     * 
     */
    public void setMinuti(int value) {
        this.minuti = value;
    }

    /**
     * Gets the value of the ore property.
     * 
     */
    public int getOre() {
        return ore;
    }

    /**
     * Sets the value of the ore property.
     * 
     */
    public void setOre(int value) {
        this.ore = value;
    }

    /**
     * Gets the value of the secondi property.
     * 
     */
    public int getSecondi() {
        return secondi;
    }

    /**
     * Sets the value of the secondi property.
     * 
     */
    public void setSecondi(int value) {
        this.secondi = value;
    }

}

