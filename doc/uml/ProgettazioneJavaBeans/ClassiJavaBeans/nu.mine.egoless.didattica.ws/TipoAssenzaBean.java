/*
 * TipoAssenza.java
 *
 * Created on 22 febbraio 2007, 12.02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

//package classibeans;

/**
 *
 * @author marghe
 */
public class TipoAssenzaBean implements java.lang.Cloneable{
    
    /** Creates a new instance of TipoAssenza */
    public TipoAssenzaBean() {
    }
    public Object clone(){    
    }
    private boolean modificato;
    
    public boolean getModificato() {
        return this.modificato;
    }
    
    public void setModificato(boolean Modificato) {
    
    }
    /**
     * Holds value of property id.
     */
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
     * Holds value of property descrizione.
     */
    private String descrizione;

    /**
     * Getter for property descrizione.
     * @return Value of property descrizione.
     */
    public String getDescrizione() {
        return this.descrizione;
    }

    /**
     * Setter for property descrizione.
     * @param descrizione New value of property descrizione.
     */
    public void setDescrizione(String descrizione) {
        String oldDescrizione = this.descrizione;
        this.descrizione = descrizione;
        propertyChangeSupport.firePropertyChange ("descrizione", oldDescrizione, descrizione);
    }

    public void caricaDaWS() {
    }

    public void salvaSuWS() {
    }
    
}
