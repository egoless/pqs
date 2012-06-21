/*
 * Assenza.java
 *
 * Created on 22 febbraio 2007, 11.36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

//package classibeans;

import java.util.Date;

/**
 *
 * @author marghe
 */
public class AssenzaBean implements java.lang.Cloneable  {
    
    /** Creates a new instance of Assenza */
    public AssenzaBean() {
    }

    /**
     * Holds value of property id.
     */
    private boolean modificato;
    
    public boolean getModificato() {
        return this.modificato;
    }
    
    public void setModificato(boolean Modificato) {
    
    }
    
    private long id;

    /**
     * Utility field used by bound properties.
     */
    private java.beans.PropertyChangeSupport propertyChangeSupport =  new java.beans.PropertyChangeSupport(this);

    /**
     * Adds a PropertyChangeListener to the listener list.
     * @param l The listener to add.
     */
    public void addPropertyChangeListener(java.beans.PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    /**
     * Removes a PropertyChangeListener from the listener list.
     * @param l The listener to remove.
     */
    public void removePropertyChangeListener(java.beans.PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    /**
     * Getter for property id.
     * @return Value of property id.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Setter for property id.
     * @param id New value of property id.
     */
    public void setId(long id) {
        long oldId = this.id;
        this.id = id;
        propertyChangeSupport.firePropertyChange ("id", new Long (oldId), new Long (id));
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
        Date oldDataInizio = this.dataInizio;
        this.dataInizio = dataInizio;
        propertyChangeSupport.firePropertyChange ("dataInizio", oldDataInizio, dataInizio);
    }

    /**
     * Holds value of property dataFine.
     */
    private String dataFine;

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
        Date oldDataFine = this.dataFine;
        this.dataFine = dataFine;
        propertyChangeSupport.firePropertyChange ("dataFine", oldDataFine, dataFine);
    }

    /**
     * Holds value of property giustificata.
     */
    private boolean giustificata;

    /**
     * Getter for property giustificata.
     * @return Value of property giustificata.
     */
    public boolean isGiustificata() {
        return this.giustificata;
    }

    /**
     * Setter for property giustificata.
     * @param giustificata New value of property giustificata.
     */
    public void setGiustificata(boolean giustificata) {
        boolean oldGiustificata = this.giustificata;
        this.giustificata = giustificata;
        propertyChangeSupport.firePropertyChange ("giustificata", new Boolean (oldGiustificata), new Boolean (giustificata));
    }

    /**
     * Holds value of property note.
     */
    private String note;

    /**
     * Getter for property note.
     * @return Value of property note.
     */
    public String getNote() {
        return this.note;
    }

    /**
     * Setter for property note.
     * @param note New value of property note.
     */
    public void setNote(String note) {
        String oldNote = this.note;
        this.note = note;
        propertyChangeSupport.firePropertyChange ("note", oldNote, note);
    }

    /**
     * Holds value of property extra.
     */
    private ListaAttributi extra;

    /**
     * Getter for property extra.
     * @return Value of property extra.
     */
    public ListaAttributi getExtra() {
        return this.extra;
    }

    /**
     * Setter for property extra.
     * @param extra New value of property extra.
     */
    public void setExtra(ListaAttributi extra) {
        ListaAttributi oldExtra = this.extra;
        this.extra = extra;
        propertyChangeSupport.firePropertyChange ("extra", oldExtra, extra);
    }

    public void salvaSuWS() {
    }

    public void caricaDaWS() {
    }
    
    public Object clone()
    {
    }

    
}
