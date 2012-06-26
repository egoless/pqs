/*
 * Nome file: PersonaleATA.java
 * Data creazione: 25 marzo 2007, 14.10
 * Info svn: $Id: PersonaleATA.java 701 2007-03-25 14:31:37Z eric $
 */

package com.swellsys.pqs.ws.api;

/**
 * Class description
 */
public class PersonaleATA
    extends Persona
{

    protected Date dataAssunzione;
    protected Date dataScandenzaContratto;
    protected String matricola;

    /**
     * Gets the value of the dataAssunzione property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDataAssunzione() {
        return dataAssunzione;
    }

    /**
     * Sets the value of the dataAssunzione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDataAssunzione(Date value) {
        this.dataAssunzione = value;
    }

    /**
     * Gets the value of the dataScandenzaContratto property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDataScandenzaContratto() {
        return dataScandenzaContratto;
    }

    /**
     * Sets the value of the dataScandenzaContratto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDataScandenzaContratto(Date value) {
        this.dataScandenzaContratto = value;
    }

    /**
     * Gets the value of the matricola property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatricola() {
        return matricola;
    }

    /**
     * Sets the value of the matricola property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatricola(String value) {
        this.matricola = value;
    }

}
