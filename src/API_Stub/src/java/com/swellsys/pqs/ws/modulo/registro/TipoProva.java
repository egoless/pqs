/*
 * Nome file: TipoProva.java
 * Data creazione: 25 marzo 2007, 12.59
 * Info svn: $Id: TipoProva.java 695 2007-03-25 12:14:55Z eric $
 */

package com.swellsys.pqs.ws.modulo.registro;

import com.swellsys.pqs.ws.api.Elemento;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Class description
 */
@Entity
public class TipoProva
    extends Elemento implements Serializable
{

    protected String nome;

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