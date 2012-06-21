/*
 * MateriaInsegnamento.java
 *
 * Created on 22 febbraio 2007, 12.19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

//package classibeans;

/**
 *
 * @author marghe
 */
public class MateriaInsegnamentoBean implements java.lang.Cloneable{
    
    /** Creates a new instance of MateriaInsegnamento */
    public MateriaInsegnamentoBean() {
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
     * Holds value of property nome.
     */
    private String nome;

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
