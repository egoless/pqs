/*
 * Nazione.java
 *
 * Created on 22 febbraio 2007, 12.23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

//package classibeans;

/**
 *
 * @author marghe
 */
public class NazioneBean implements java.lang.Cloneable{
    
    /** Creates a new instance of Nazione */
    public NazioneBean() {
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
     * Holds value of property nome.
     */
    private String nome;

    /**
     * Getter for property nome.
     * @return Value of property nome.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Setter for property nome.
     * @param nome New value of property nome.
     */
    public void setNome(String nome) {
        String oldNome = this.nome;
        this.nome = nome;
        propertyChangeSupport.firePropertyChange ("nome", oldNome, nome);
    }

    public void caricaDaWS() {
    }

    public void salvaSuWS() {
    }
    
}
