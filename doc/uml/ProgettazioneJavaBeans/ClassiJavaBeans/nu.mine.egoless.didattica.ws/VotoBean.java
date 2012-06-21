/*
 * Voto.java
 *
 * Created on 22 febbraio 2007, 12.05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

//package classibeans;

/**
 *
 * @author marghe
 */
public class VotoBean implements java.lang.Cloneable{
    
    /** Creates a new instance of Voto */
    public VotoBean() {
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
     * Holds value of property dataOra.
     */
    private Date dataOra;

    /**
     * Getter for property dataOra.
     * @return Value of property dataOra.
     */
    public Date getDataOra() {
        return this.dataOra;
    }

    /**
     * Setter for property dataOra.
     * @param dataOra New value of property dataOra.
     */
    public void setDataOra(Date dataOra) {
        Date oldDataOra = this.dataOra;
        this.dataOra = dataOra;
        propertyChangeSupport.firePropertyChange ("dataOra", oldDataOra, dataOra);
    }

    /**
     * Holds value of property valore.
     */
    private int valore;

    /**
     * Getter for property valore.
     * @return Value of property valore.
     */
    public int getValore() {
        return this.valore;
    }

    /**
     * Setter for property valore.
     * @param valore New value of property valore.
     */
    public void setValore(int valore) {
        int oldValore = this.valore;
        this.valore = valore;
        propertyChangeSupport.firePropertyChange ("valore", new Integer (oldValore), new Integer (valore));
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

    public void caricaDaWS() {
    }

    public void salvaSuWS() {
    }

    
}
