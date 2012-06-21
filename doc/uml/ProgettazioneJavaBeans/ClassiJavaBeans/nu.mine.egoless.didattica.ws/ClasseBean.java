/*
 * ClasseBean.java
 *
 * Created on 22 febbraio 2007, 11.04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

//package classibeans;

/**
 *
 * @author marghe
 */
public class ClasseBean implements java.lang.Cloneable {
    
    /** Creates a new instance of ClasseBean */
    public ClasseBean() {
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
     * Holds value of property annoCorso.
     */
    private int annoCorso;

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
     * Getter for property annoCorso.
     * @return Value of property annoCorso.
     */
    public int getAnnoCorso() {
        return this.annoCorso;
    }

    /**
     * Setter for property annoCorso.
     * @param annoCorso New value of property annoCorso.
     */
    public void setAnnoCorso(int annoCorso) {
        int oldAnnoCorso = this.annoCorso;
        this.annoCorso = annoCorso;
        propertyChangeSupport.firePropertyChange ("annoCorso", new Integer (oldAnnoCorso), new Integer (annoCorso));
    }

    public void caricaDaWS() {
    }

    /**
     * Holds value of property sezione.
     */
    private char sezione;

    /**
     * Getter for property sezione.
     * @return Value of property sezione.
     */
    public char getSezione() {
        return this.sezione;
    }

    /**
     * Setter for property sezione.
     * @param sezione New value of property sezione.
     */
    public void setSezione(char sezione) {
        char oldSezione = this.sezione;
        this.sezione = sezione;
        propertyChangeSupport.firePropertyChange ("sezione", new Character (oldSezione), new Character (sezione));
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
        String oldExtra = this.extra;
        this.extra = extra;
        propertyChangeSupport.firePropertyChange ("extra", oldExtra, extra);
    }

    public void salvaSuWS() {
    }
    
}
