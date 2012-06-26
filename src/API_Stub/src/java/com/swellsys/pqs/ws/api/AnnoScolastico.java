/*
 * Nome file: AnnoScolastico.java
 * Data creazione: 24 marzo 2007, 13.50
 * Info svn: $Id: AnnoScolastico.java 642 2007-03-24 14:21:42Z eric $
 */

package com.swellsys.pqs.ws.api;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Class description
 */
@Entity
public class AnnoScolastico
    extends Elemento implements Serializable
{

    protected Date dataFine;
    protected Date dataInizio;
    protected Istituto istituto;

    /**
     * Gets the value of the dataFine property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getDataFine() {
        return dataFine;
    }

    /**
     * Sets the value of the dataFine property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFine(Date value) {
        this.dataFine = value;
    }

    /**
     * Gets the value of the dataInizio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getDataInizio() {
        return dataInizio;
    }

    /**
     * Sets the value of the dataInizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizio(Date value) {
        this.dataInizio = value;
    }

    /**
     * Gets the value of the istituto property.
     * 
     * @return
     *     possible object is
     *     {@link Istituto }
     *     
     */
    public Istituto getIstituto() {
        return istituto;
    }

    /**
     * Sets the value of the istituto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Istituto }
     *     
     */
    public void setIstituto(Istituto value) {
        this.istituto = value;
    }

}

