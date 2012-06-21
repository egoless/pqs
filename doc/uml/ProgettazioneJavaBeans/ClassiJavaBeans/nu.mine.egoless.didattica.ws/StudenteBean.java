/*
 * StudenteBean.java
 *
 * Created on 21 febbraio 2007, 18.44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author STEFANO
 */
import java.util.Date;

public class StudenteBean implements java.lang.Cloneable{
    
    /** Creates a new instance of StudenteBean */
    public StudenteBean() {
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

    public StudenteBean(StudenteBean studente) {
    }

    /**
     * Holds value of property modificato.
     */
    private String modificato;

    /**
     * Getter for property modificato.
     * @return Value of property modificato.
     */
    public String getModificato() {
        return this.modificato;
    }

    /**
     * Setter for property modificato.
     * @param modificato New value of property modificato.
     */
    public void setModificato(String modificato) {
        String oldModificato = this.modificato;
        this.modificato = modificato;
        propertyChangeSupport.firePropertyChange ("modificato", oldModificato, modificato);
    }

    /**
     * Holds value of property ClasseModificata.
     */
    private String m;

    /**
     * Getter for property m.
     * @return Value of property m.
     */
    public boolean isClasseModificata() {
        return this.m;
    }

    /**
     * Setter for property m.
     * @param m New value of property m.
     */
    public void setClasseModificata(boolean m) {
        this.m = m;
    }

    public void caricaDaWS() {
    }

    public void salvaSuWS() {
    }

    /**
     * Holds value of property matricola.
     */
    private String matricola;

    /**
     * Getter for property matricola.
     * @return Value of property matricola.
     */
    public String getMatricola() {
        return this.matricola;
    }

    /**
     * Setter for property matricola.
     * @param matricola New value of property matricola.
     */
    public void setMatricola(String matricola) {
        String oldMatricola = this.matricola;
        this.matricola = matricola;
        propertyChangeSupport.firePropertyChange ("matricola", oldMatricola, matricola);
    }

    /**
     * Holds value of property dataIscrizione.
     */
    private Date dataIscrizione;

    /**
     * Getter for property dataIscrizione.
     * @return Value of property dataIscrizione.
     */
    public Date getDataIscrizione() {
        return this.dataIscrizione;
    }

    /**
     * Setter for property dataIscrizione.
     * @param dataIscrizione New value of property dataIscrizione.
     */
    public void setDataIscrizione(Date dataIscrizione) {
        Date oldDataIscrizione = this.dataIscrizione;
        this.dataIscrizione = dataIscrizione;
        propertyChangeSupport.firePropertyChange ("dataIscrizione", oldDataIscrizione, dataIscrizione);
    }

    
    
}
