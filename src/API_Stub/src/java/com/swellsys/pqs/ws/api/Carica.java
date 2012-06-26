/*
 * Nome file: Carica.java
 * Data creazione: 25 marzo 2007, 14.08
 * Info svn: $Id: Carica.java 701 2007-03-25 14:31:37Z eric $
 */

package com.swellsys.pqs.ws.api;

/**
 * Class description
 */
public class Carica
    extends Elemento
{

    protected int annoId;
    protected int ruoloId;
    protected int classeId;

    /**
     * Gets the value of the annoId property.
     * 
     */
    public int getAnnoId() {
        return annoId;
    }

    /**
     * Sets the value of the annoId property.
     * 
     */
    public void setAnnoId(int value) {
        this.annoId = value;
    }

    /**
     * Gets the value of the ruoloId property.
     * 
     */
    public int getRuoloId() {
        return ruoloId;
    }

    /**
     * Sets the value of the ruoloId property.
     * 
     */
    public void setRuoloId(int value) {
        this.ruoloId = value;
    }

    /**
     * Gets the value of the classeId property.
     * 
     */
    public int getClasseId() {
        return classeId;
    }

    /**
     * Sets the value of the classeId property.
     * 
     */
    public void setClasseId(int value) {
        this.classeId = value;
    }

}