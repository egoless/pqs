/*
 * OraLezione.java
 *
 * Created on 22 febbraio 2007, 12.21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

//package classibeans;

/**
 *
 * @author marghe
 */
public class OraLezioneBean implements java.lang.Cloneable{
    
    /** Creates a new instance of OraLezione */
    public OraLezioneBean() {
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
     * Holds value of property giornoSettimana.
     */
    private int giornoSettimana;

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
     * Getter for property giornoSettimana.
     * @return Value of property giornoSettimana.
     */
    public int getGiornoSettimana() {
        return this.giornoSettimana;
    }

    /**
     * Setter for property giornoSettimana.
     * @param giornoSettimana New value of property giornoSettimana.
     */
    public void setGiornoSettimana(int giornoSettimana) {
        int oldGiornoSettimana = this.giornoSettimana;
        this.giornoSettimana = giornoSettimana;
        propertyChangeSupport.firePropertyChange ("giornoSettimana", new Integer (oldGiornoSettimana), new Integer (giornoSettimana));
    }

    /**
     * Holds value of property oraGiorno.
     */
    private int oraGiorno;

    /**
     * Getter for property oraGiorno.
     * @return Value of property oraGiorno.
     */
    public int getOraGiorno() {
        return this.oraGiorno;
    }

    /**
     * Setter for property oraGiorno.
     * @param oraGiorno New value of property oraGiorno.
     */
    public void setOraGiorno(int oraGiorno) {
        int oldOraGiorno = this.oraGiorno;
        this.oraGiorno = oraGiorno;
        propertyChangeSupport.firePropertyChange ("oraGiorno", new Integer (oldOraGiorno), new Integer (oraGiorno));
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
