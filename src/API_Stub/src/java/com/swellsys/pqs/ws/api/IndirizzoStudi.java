/*
 * Nome file: IndirizzoStudi.java
 * Data creazione: 24 marzo 2007, 13.14
 * Info svn: $Id: IndirizzoStudi.java 642 2007-03-24 14:21:42Z eric $
 */

package com.swellsys.pqs.ws.api;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Rappresenta un indirizzo di studi dentro la scuola.
 */
@Entity
public class IndirizzoStudi
    extends Elemento implements Serializable
{

    protected String descrizione;
    protected String nome;

    /**
     * Gets the value of the descrizione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Sets the value of the descrizione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

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

}
