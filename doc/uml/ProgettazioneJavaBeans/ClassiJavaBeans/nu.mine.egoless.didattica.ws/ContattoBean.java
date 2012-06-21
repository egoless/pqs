/*
 * Contatto.java
 *
 * Created on 22 febbraio 2007, 12.24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

//package classibeans;

/**
 *
 * @author marghe
 */
public class ContattoBean implements java.lang.Cloneable{
    
    /** Creates a new instance of Contatto */
    public ContattoBean() {
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
     * Holds value of property via.
     */
    private String via;

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
     * Getter for property via.
     * @return Value of property via.
     */
    public String getVia() {
        return this.via;
    }

    /**
     * Setter for property via.
     * @param via New value of property via.
     */
    public void setVia(String via) {
        String oldVia = this.via;
        this.via = via;
        propertyChangeSupport.firePropertyChange ("via", oldVia, via);
    }

    /**
     * Holds value of property civico.
     */
    private String civico;

    /**
     * Getter for property civico.
     * @return Value of property civico.
     */
    public String getCivico() {
        return this.civico;
    }

    /**
     * Setter for property civico.
     * @param civico New value of property civico.
     */
    public void setCivico(String civico) {
        String oldCivico = this.civico;
        this.civico = civico;
        propertyChangeSupport.firePropertyChange ("civico", oldCivico, civico);
    }

    /**
     * Holds value of property CAP.
     */
    private String CAP;

    /**
     * Getter for property CAP.
     * @return Value of property CAP.
     */
    public String getCAP() {
        return this.CAP;
    }

    /**
     * Setter for property CAP.
     * @param CAP New value of property CAP.
     */
    public void setCAP(String CAP) {
        String oldCAP = this.CAP;
        this.CAP = CAP;
        propertyChangeSupport.firePropertyChange ("CAP", oldCAP, CAP);
    }

    /**
     * Holds value of property città.
     */
    private String città;

    /**
     * Getter for property città.
     * @return Value of property città.
     */
    public String getCittà() {
        return this.città;
    }

    /**
     * Setter for property città.
     * @param città New value of property città.
     */
    public void setCittà(String città) {
        String oldCittà = this.città;
        this.città = città;
        propertyChangeSupport.firePropertyChange ("città", oldCittà, città);
    }

    /**
     * Holds value of property provincia.
     */
    private String provincia;

    /**
     * Getter for property provincia.
     * @return Value of property provincia.
     */
    public String getProvincia() {
        return this.provincia;
    }

    /**
     * Setter for property provincia.
     * @param provincia New value of property provincia.
     */
    public void setProvincia(String provincia) {
        String oldProvincia = this.provincia;
        this.provincia = provincia;
        propertyChangeSupport.firePropertyChange ("provincia", oldProvincia, provincia);
    }

    /**
     * Holds value of property telefonoPrincipale.
     */
    private String telefonoPrincipale;

    /**
     * Getter for property telefonoPrincipale.
     * @return Value of property telefonoPrincipale.
     */
    public String getTelefonoPrincipale() {
        return this.telefonoPrincipale;
    }

    /**
     * Setter for property telefonoPrincipale.
     * @param telefonoPrincipale New value of property telefonoPrincipale.
     */
    public void setTelefonoPrincipale(String telefonoPrincipale) {
        String oldTelefonoPrincipale = this.telefonoPrincipale;
        this.telefonoPrincipale = telefonoPrincipale;
        propertyChangeSupport.firePropertyChange ("telefonoPrincipale", oldTelefonoPrincipale, telefonoPrincipale);
    }

    /**
     * Holds value of property telefonoSecondario.
     */
    private String telefonoSecondario;

    /**
     * Getter for property telefonoSecondario.
     * @return Value of property telefonoSecondario.
     */
    public String getTelefonoSecondario() {
        return this.telefonoSecondario;
    }

    /**
     * Setter for property telefonoSecondario.
     * @param telefonoSecondario New value of property telefonoSecondario.
     */
    public void setTelefonoSecondario(String telefonoSecondario) {
        String oldTelefonoSecondario = this.telefonoSecondario;
        this.telefonoSecondario = telefonoSecondario;
        propertyChangeSupport.firePropertyChange ("telefonoSecondario", oldTelefonoSecondario, telefonoSecondario);
    }

    /**
     * Holds value of property fax.
     */
    private String fax;

    /**
     * Getter for property fax.
     * @return Value of property fax.
     */
    public String getFax() {
        return this.fax;
    }

    /**
     * Setter for property fax.
     * @param fax New value of property fax.
     */
    public void setFax(String fax) {
        String oldFax = this.fax;
        this.fax = fax;
        propertyChangeSupport.firePropertyChange ("fax", oldFax, fax);
    }

    public void caricaDaWS() {
    }

    public void salvaSuWS() {
    }
    
}
