/*
 * Insegnante.java
 *
 * Created on 22 febbraio 2007, 12.09
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
public class InsegnanteBean implements java.lang.Cloneable{
    
    /** Creates a new instance of Insegnante */
    public InsegnanteBean() {
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
     * Holds value of property dataAssunzione.
     */
    private Date dataAssunzione;

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
     * Getter for property dataAssunzione.
     * @return Value of property dataAssunzione.
     */
    public Date getDataAssunzione() {
        return this.dataAssunzione;
    }

    /**
     * Setter for property dataAssunzione.
     * @param dataAssunzione New value of property dataAssunzione.
     */
    public void setDataAssunzione(Date dataAssunzione) {
        Date oldDataAssunzione = this.dataAssunzione;
        this.dataAssunzione = dataAssunzione;
        propertyChangeSupport.firePropertyChange ("dataAssunzione", oldDataAssunzione, dataAssunzione);
    }

    /**
     * Holds value of property id.
     */
    private long id;

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

    /**
     * Holds value of property cognome.
     */
    private String cognome;

    /**
     * Getter for property cognome.
     * @return Value of property cognome.
     */
    public String getCognome() {
        return this.cognome;
    }

    /**
     * Setter for property cognome.
     * @param cognome New value of property cognome.
     */
    public void setCognome(String cognome) {
        String oldCognome = this.cognome;
        this.cognome = cognome;
        propertyChangeSupport.firePropertyChange ("cognome", oldCognome, cognome);
    }

    /**
     * Holds value of property dataNascita.
     */
    private Date dataNascita;

    /**
     * Getter for property dataNascita.
     * @return Value of property dataNascita.
     */
    public Date getDataNascita() {
        return this.dataNascita;
    }

    /**
     * Setter for property dataNascita.
     * @param dataNascita New value of property dataNascita.
     */
    public void setDataNascita(Date dataNascita) {
        Date oldDataNascita = this.dataNascita;
        this.dataNascita = dataNascita;
        propertyChangeSupport.firePropertyChange ("dataNascita", oldDataNascita, dataNascita);
    }

    /**
     * Holds value of property codiceFiscale.
     */
    private String codiceFiscale;

    /**
     * Getter for property codiceFiscale.
     * @return Value of property codiceFiscale.
     */
    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }

    /**
     * Setter for property codiceFiscale.
     * @param codiceFiscale New value of property codiceFiscale.
     */
    public void setCodiceFiscale(String codiceFiscale) {
        String oldCodiceFiscale = this.codiceFiscale;
        this.codiceFiscale = codiceFiscale;
        propertyChangeSupport.firePropertyChange ("codiceFiscale", oldCodiceFiscale, codiceFiscale);
    }

    /**
     * Holds value of property indirizzoResidenza.
     */
    private Contatto indirizzoResidenza;

    /**
     * Getter for property indirizzoResidenza.
     * @return Value of property indirizzoResidenza.
     */
    public Contatto getIndirizzoResidenza() {
        return this.indirizzoResidenza;
    }

    /**
     * Setter for property indirizzoResidenza.
     * @param indirizzoResidenza New value of property indirizzoResidenza.
     */
    public void setIndirizzoResidenza(Contatto indirizzoResidenza) {
        Contatto oldIndirizzoResidenza = this.indirizzoResidenza;
        this.indirizzoResidenza = indirizzoResidenza;
        propertyChangeSupport.firePropertyChange ("indirizzoResidenza", oldIndirizzoResidenza, indirizzoResidenza);
    }

    /**
     * Holds value of property portatoreHandicap.
     */
    private boolean portatoreHandicap;

    /**
     * Getter for property portatoreHandicap.
     * @return Value of property portatoreHandicap.
     */
    public boolean isPortatoreHandicap() {
        return this.portatoreHandicap;
    }

    /**
     * Setter for property portatoreHandicap.
     * @param portatoreHandicap New value of property portatoreHandicap.
     */
    public void setPortatoreHandicap(boolean portatoreHandicap) {
        boolean oldPortatoreHandicap = this.portatoreHandicap;
        this.portatoreHandicap = portatoreHandicap;
        propertyChangeSupport.firePropertyChange ("portatoreHandicap", new Boolean (oldPortatoreHandicap), new Boolean (portatoreHandicap));
    }

    public void caricaDaWS() {
    }

    public void salvaSuWS() {
    }
    
}
