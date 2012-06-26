/*
 * GestioneDatiAnagraficiPanel.java
 *
 * Created on 3 marzo 2007, 0.30
 *
 */

package nu.mine.egoless.didattica.app.gui.gestionePersone;

import nu.mine.egoless.didattica.app.bean.*;
import nu.mine.egoless.didattica.app.gui.componentiComuni.DataPanel;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ContenitoreDati;
import nu.mine.egoless.didattica.app.bean.ReligioniBean;
import nu.mine.egoless.didattica.app.bean.NazioniBean;
import nu.mine.egoless.didattica.app.bean.PersonaBean;
import nu.mine.egoless.didattica.app.bean.ContattoBean;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.Date;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import nu.mine.egoless.didattica.ws.contattoclient.WSDidatticaException_Exception;
import java.net.PortUnreachableException;
import nu.mine.egoless.didattica.ws.religioneclient.Religione;
import nu.mine.egoless.didattica.ws.nazioneclient.Nazione;
import nu.mine.egoless.didattica.app.gui.componentiComuni.UtilitaGUI;
import nu.mine.egoless.didattica.app.adapter.*;

/**
 *
 * @author  Lorenzo Daniele
 *
 * Estensione di JPanel che si occupa della gestione dei dati anagrafici di una persona.
 * Il PersonaBean che contiene i dati relativi alla persona da gestire viene 
 *  passato come parametro.
 */
public class GestioneDatiAnagraficiPanel extends javax.swing.JPanel {
    
   
    /**
     * Creates new form GestioneDatiAnagraficiPanel
     * @param persona il PersonaBean da associare a tale pannello per la gestione dei suoi dati
     * @param contatto il contattoBean relativo a tale persona
     * @param religioni il bean che contiene la lista delle religioni possibili
     * @param nazioni il bean che contiene la lista delle nazioni possibili
     */
    public GestioneDatiAnagraficiPanel(PersonaBean persona, ContattoBean contatto, ContenitoreDati contenitore){
        if( persona==null || contatto==null || contenitore==null || contenitore.getReligioni()==null || contenitore.getNazioni()==null) throw new IllegalArgumentException();
        //if( persona.getIdIndirizzoResidenza() != contatto.getId() ) throw new IllegalArgumentException("Id di persona e contatto non coerenti");
        inizializzato = false;
        this.personaBean = persona;
        this.contattoBean = contatto;
        this.religioniBean = contenitore.getReligioni();
        this.nazioniBean =  contenitore.getNazioni();
        //Creazione delle componenti
        inizializzaComponenti();
        //Inizializzazione delle componenti
        inizializzaForm();
        //Registrazione dei listener sul bean per i cambi di properiatà
        aggiungiPropertyListener();
        inizializzato = true;
    }

    /**
     * main di prova
     */
    public static void main(String[] args) {
        //System.out.println("AVVIO");
        javax.swing.JDialog temp=new javax.swing.JDialog();
        temp.setLayout(new java.awt.BorderLayout());
        /*ContenitoreDati c = new ContenitoreDati(new ReligioniBean(), new NazioniBean(), new ClassiBean(), new MaterieInsegnamentoBean(), new TipiVotiBean(), new TipiAssenzeBean());
        GestioneDatiAnagraficiPanel g = new GestioneDatiAnagraficiPanel(new StudenteBean(), new ContattoBean(), c);
        temp.add(g);
        g.setEnabled(false);
        temp.setSize(500,500);
        temp.setVisible(true);       */ 
        //System.out.println("TERMINEAVVIO");
    }    

    /**
     * rende agibili o inagibili i componenti del pannello
     */
    public void setEnabled(boolean b){
        jtfCognome.setEnabled(b);
        jtfNome.setEnabled(b);
        jtfCodiceFiscale.setEnabled(b);
        jpnlDataNascita.setEnabled(b);
        jcbPortatoreHandicap.setEnabled(b);
        jcmbReligione.setEnabled(b);
        jcmbNazionalita.setEnabled(b);
        jbContatto.setEnabled(b);
    }    
    
    /**
     * Aggiunge i listener per i cambiamenti di stato sul personaBean
     */
    private void aggiungiPropertyListener(){    
        personaBean.addPropertyChangeListener( personaBean.NOME, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    System.out.println("PCE ");
                    if(event.getNewValue()!=null) jtfNome.setText( (String) event.getNewValue() );
                    else jtfNome.setText("");
                }
            }              
        );
        personaBean.addPropertyChangeListener( personaBean.COGNOME, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    System.out.println("PCE ");                   
                    if(event.getNewValue()!=null) jtfCognome.setText( (String) event.getNewValue() );
                    else jtfCognome.setText("");
                }
            }              
        );
        personaBean.addPropertyChangeListener( personaBean.DATA_NASCITA, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    System.out.println("PCEDataNascita ");
                    if(event.getNewValue()!=null) jpnlDataNascita.setData( (Date) event.getNewValue() );
                    else jpnlDataNascita.setData( new Date() );
                }
            }              
        );
        personaBean.addPropertyChangeListener( personaBean.CODICE_FISCALE, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    System.out.println("PCE ");
                    if(event.getNewValue()!=null) jtfCodiceFiscale.setText( (String) event.getNewValue() );
                    else jtfCodiceFiscale.setText("");
                }
            }              
        );
        personaBean.addPropertyChangeListener( personaBean.NAZIONALITA, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    System.out.println("PCE ");
                    if(event.getNewValue()!=null){ 
                        Nazione nazione = getNazione( ((Integer) event.getNewValue()).intValue());
                        if(nazione!=null) jcmbNazionalita.setSelectedItem( new NazioneAdapter( nazione ) );
                        else throw new IllegalArgumentException();
                    }
                    else throw new IllegalArgumentException("Selezione nel jcmbNazionalita non possibile -> evento imprevedibile1n");
                }
            }              
        );
        personaBean.addPropertyChangeListener( personaBean.RELIGIONE, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    System.out.println("PCE ");
                    //System.out.println("nn  "+event.getNewValue().getClass().toString());  //output: LONG
                    if(event.getNewValue()!=null){ 
                        Religione religione = getReligione( ((Integer) event.getNewValue()).intValue());
                        if(religione!=null) jcmbReligione.setSelectedItem( new ReligioneAdapter( religione ) );
                        else throw new IllegalArgumentException();
                    }
                    else throw new IllegalArgumentException("Selezione nel jcmbNazionalita non possibile -> evento imprevedibile1n");
                }
            }              
        );
        personaBean.addPropertyChangeListener( personaBean.PORTATORE_HANDICAP, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    System.out.println("PCE ");
                    if(event.getNewValue()!=null) jcbPortatoreHandicap.setSelected( ((Boolean) event.getNewValue()).booleanValue() );
                    else jcbPortatoreHandicap.setSelected(false);                
                }
            }              
        );
        personaBean.addPropertyChangeListener( personaBean.SESSO, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    System.out.println("PCE ");
                    if(event.getNewValue()!=null){
                        int sesso  = ( ((Integer) event.getNewValue()).intValue() );
                        if(sesso == MASCHIO) jrbMaschio.setSelected(true);
                        else jrbFemmina.setSelected(true);
                    }
                    else jcbPortatoreHandicap.setSelected(false);                
                }
            }              
        );        
    }
    
    /**
     * Inizializza il form con i valori delle variabile locale rappresentante los tudente
     */
    private void inizializzaForm(){
        if(personaBean.getCognome()!=null) jtfCognome.setText(personaBean.getCognome());
        if(personaBean.getDataNascita()!=null) jpnlDataNascita.setData(personaBean.getDataNascita());      
        if(personaBean.getNome()!=null) jtfNome.setText(personaBean.getNome());
        if(personaBean.getCodiceFiscale()!=null) jtfCodiceFiscale.setText(personaBean.getCodiceFiscale()); 
        
        /* Inserito brutalmente l'id dell'istituto perchè nella nostra form non viene trattato*/
        personaBean.setIdIstituto(Costanti.ID_ISTITUTO_DUMMY);
        //personaBean.setIdNazionalita(Costanti.ID_NAZIONE_DUMMY);
        
        ReligioneAdapter r = new ReligioneAdapter( UtilitaGUI.getReligione( religioniBean , personaBean.getIdReligione() ) );
        //System.out.println("RELIGIONECMB: "+r.getReligione()); //DEBUG CODE        
        //System.out.println("*********IDRel: "+personaBean.getIdReligione()); //DEBUG CODE        
        jcmbReligione.setSelectedItem( r );
        //System.out.println("*********"); //DEBUG CODE        
        NazioneAdapter n = new NazioneAdapter( UtilitaGUI.getNazione( nazioniBean , personaBean.getIdNazionalita() ) );
        //System.out.println("*********IDNaz: "+personaBean.getIdNazionalita()); //DEBUG CODE                
        jcmbNazionalita.setSelectedItem( n );
        //System.out.println("*********"); //DEBUG CODE
        Boolean b = personaBean.isPortatoreHandicap();
        if( b == null ) b = new Boolean(false);
        jcbPortatoreHandicap.setSelected(b);        
        Integer temp = personaBean.getSesso();
        if(temp == null) temp = new Integer(MASCHIO);
        int sesso = temp.intValue();
        if(sesso == MASCHIO) jrbMaschio.setSelected(true);
        else jrbFemmina.setSelected(true);
    }
    
    /** 
     * Dato un id ritorna la relativa Religione dal ReligioniBean 
     */
    private Religione getReligione( int id ){
        return UtilitaGUI.getReligione(religioniBean, id);
    }
    
    /**
     * Data una string ritorna la relativa Religione dal ReligioniBean 
     */
    private Religione getReligione( String religione ){        
        return UtilitaGUI.getReligione(religioniBean, religione); 
    }

    /** 
     * Dato un id ritorna la relativa Nazione dal NazioniBean 
     */
    private Nazione getNazione( int id ){
        return UtilitaGUI.getNazione(nazioniBean, id);
    }    
    
    /** 
     * Data una string ritorna la relativa Nazione dal NazioniBean 
     */
    private Nazione getNazione( String nazione ){        
        return UtilitaGUI.getNazione(nazioniBean, nazione);  
    }
    
    /**
     * Inizializza il combo box per la scelta della nazionalità
     */  
    private void inizializzaComboBoxNazionalita(){
        jcmbNazionalita.removeAllItems();
        for(int i=0; i<nazioniBean.ritornaNumeroDiNazioni(); i++){
            NazioneAdapter n = new NazioneAdapter( nazioniBean.getNazioneAt(i) );
            jcmbNazionalita.addItem( n );
            //System.out.println("NAZIONE: "+n.getNazione().getId()+" "+n.getNazione().getNome()); //DEBUG CODE            
        }              
    }

    /**
     * Inizializza il combo box per la scelta della religione
     */  
    private void inizializzaComboBoxReligione(){
        jcmbReligione.removeAllItems();
        for(int i=0; i<religioniBean.ritornaNumeroDiReligioni(); i++){
            ReligioneAdapter r = new ReligioneAdapter( religioniBean.getReligioneAt(i) );
            //System.out.println("RELIGIONE: "+r.getReligione().getId()+" "+r.getReligione().getNome()); //DEBUG CODE
            jcmbReligione.addItem( r );
        }        
    }
    
    /**
     * inizializza le componenti grafiche del pannello
     */
    private void inizializzaComponenti(){
        initComponents();
        bgSesso.add(jrbMaschio);
        bgSesso.add(jrbFemmina);
        jpnlDataNascita.addPropertyChangeListener( new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent e){
                    jpnlDataNascitaPropertyChanged(e);
                }
            }
        );
        //inizializza i combo box della nazionalita e della religione
        inizializzaComboBoxNazionalita();
        inizializzaComboBoxReligione();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jlblCognome = new javax.swing.JLabel();
        jtfCognome = new javax.swing.JTextField();
        jtfNome = new javax.swing.JTextField();
        jpnlDataNascita = new nu.mine.egoless.didattica.app.gui.componentiComuni.DataPanel();
        jtfCodiceFiscale = new javax.swing.JTextField();
        jcmbReligione = new javax.swing.JComboBox();
        jcmbNazionalita = new javax.swing.JComboBox();
        jbContatto = new javax.swing.JButton();
        jlblNome = new javax.swing.JLabel();
        jlblDataNascita = new javax.swing.JLabel();
        jlblCodiceFiscale = new javax.swing.JLabel();
        jcbPortatoreHandicap = new javax.swing.JCheckBox();
        jlblPortatoreHandicap = new javax.swing.JLabel();
        jlblReligione = new javax.swing.JLabel();
        jlblNazionalita = new javax.swing.JLabel();
        jlblContatto = new javax.swing.JLabel();
        jlblSesso = new javax.swing.JLabel();
        jrbMaschio = new javax.swing.JRadioButton();
        jrbFemmina = new javax.swing.JRadioButton();

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dati Anagrafici"));
        jPanel1.setEnabled(false);
        jlblCognome.setDisplayedMnemonic('C');
        jlblCognome.setLabelFor(jtfCognome);
        jlblCognome.setText("Cognome: ");

        jtfCognome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfCognomeKeyReleased(evt);
            }
        });

        jtfNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfNomeKeyReleased(evt);
            }
        });

        jtfCodiceFiscale.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfCodiceFiscaleKeyReleased(evt);
            }
        });

        jcmbReligione.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbReligioneItemStateChanged(evt);
            }
        });

        jcmbNazionalita.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbNazionalitaItemStateChanged(evt);
            }
        });

        jbContatto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/Properties16.gif")));
        jbContatto.setMnemonic('V');
        jbContatto.setText("Visualizza");
        jbContatto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbContattoActionPerformed(evt);
            }
        });

        jlblNome.setDisplayedMnemonic('N');
        jlblNome.setLabelFor(jtfNome);
        jlblNome.setText("Nome: ");

        jlblDataNascita.setDisplayedMnemonic('D');
        jlblDataNascita.setText("Data di Nascita: ");

        jlblCodiceFiscale.setDisplayedMnemonic('F');
        jlblCodiceFiscale.setLabelFor(jtfCodiceFiscale);
        jlblCodiceFiscale.setText("Codice Fiscale: ");

        jcbPortatoreHandicap.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jcbPortatoreHandicap.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jcbPortatoreHandicap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPortatoreHandicapActionPerformed(evt);
            }
        });

        jlblPortatoreHandicap.setDisplayedMnemonic('H');
        jlblPortatoreHandicap.setLabelFor(jcbPortatoreHandicap);
        jlblPortatoreHandicap.setText("Portatore di Handicap: ");

        jlblReligione.setDisplayedMnemonic('R');
        jlblReligione.setText("Religione: ");

        jlblNazionalita.setDisplayedMnemonic('Z');
        jlblNazionalita.setText("Nazionalit\u00e0: ");

        jlblContatto.setDisplayedMnemonic('T');
        jlblContatto.setLabelFor(jbContatto);
        jlblContatto.setText("Contatto: ");

        jlblSesso.setText("Sesso");

        jrbMaschio.setBackground(new java.awt.Color(204, 204, 255));
        jrbMaschio.setText("M");
        jrbMaschio.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jrbMaschio.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jrbMaschio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbMaschioActionPerformed(evt);
            }
        });

        jrbFemmina.setBackground(new java.awt.Color(204, 204, 255));
        jrbFemmina.setText("F");
        jrbFemmina.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jrbFemmina.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jrbFemmina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbFemminaActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jlblPortatoreHandicap)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jcbPortatoreHandicap))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jlblContatto)
                            .add(jlblNazionalita)
                            .add(jlblReligione)
                            .add(jlblCodiceFiscale)
                            .add(jlblDataNascita)
                            .add(jlblNome)
                            .add(jlblCognome)
                            .add(jlblSesso))
                        .add(4, 4, 4)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jrbMaschio)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jrbFemmina))
                            .add(jpnlDataNascita, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                            .add(jtfNome, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                            .add(jtfCognome, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                            .add(jtfCodiceFiscale, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                            .add(jcmbReligione, 0, 189, Short.MAX_VALUE)
                            .add(jcmbNazionalita, 0, 189, Short.MAX_VALUE)
                            .add(jbContatto, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jtfCognome, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlblCognome))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jtfNome, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jpnlDataNascita, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jlblNome)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jlblDataNascita)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jtfCodiceFiscale, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlblCodiceFiscale))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jlblPortatoreHandicap)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jcbPortatoreHandicap)
                        .add(2, 2, 2)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jlblSesso)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jrbMaschio)
                        .add(jrbFemmina)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jcmbReligione, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlblReligione))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlblNazionalita)
                    .add(jcmbNazionalita, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jlblContatto)
                    .add(jbContatto))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jrbFemminaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbFemminaActionPerformed
        System.out.println("APFemmina");
        if(inizializzato) personaBean.setSesso(FEMMINA);
    }//GEN-LAST:event_jrbFemminaActionPerformed

    private void jrbMaschioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbMaschioActionPerformed
        System.out.println("APMaschio");
        if(inizializzato) personaBean.setSesso(MASCHIO);
    }//GEN-LAST:event_jrbMaschioActionPerformed

    private void jcmbNazionalitaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbNazionalitaItemStateChanged
        System.out.println("ISCNazionalita ");
        NazioneAdapter nazione = (NazioneAdapter) jcmbNazionalita.getSelectedItem();
        int id = nazione.getNazione().getId() ;
        if(inizializzato) personaBean.setIdNazionalita(id);
    }//GEN-LAST:event_jcmbNazionalitaItemStateChanged

    private void jcmbReligioneItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbReligioneItemStateChanged
        System.out.println("ISCReligione ");
        ReligioneAdapter religione = (ReligioneAdapter) jcmbReligione.getSelectedItem();
        if(inizializzato) personaBean.setIdReligione( religione.getReligione().getId() );
    }//GEN-LAST:event_jcmbReligioneItemStateChanged

    /**
     * Segue la lista dei metodi richiamati dai listener associati alle varie componenti
     * Sono il cuore del Controller nel pattern MVC utilizzato per la GUI
     */ 
    private void jtfCodiceFiscaleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfCodiceFiscaleKeyReleased
        if(inizializzato) personaBean.setCodiceFiscale( jtfCodiceFiscale.getText() );
    }//GEN-LAST:event_jtfCodiceFiscaleKeyReleased

    private void jtfCognomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfCognomeKeyReleased
        if(inizializzato) personaBean.setCognome( jtfCognome.getText() );
    }//GEN-LAST:event_jtfCognomeKeyReleased

    private void jtfNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfNomeKeyReleased
        if(inizializzato) personaBean.setNome( jtfNome.getText() );
    }//GEN-LAST:event_jtfNomeKeyReleased

    /**Lista dei metodi per i listener sui componenti*/
    private void jcbPortatoreHandicapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPortatoreHandicapActionPerformed
        if(inizializzato) personaBean.setPortatoreHandicap( jcbPortatoreHandicap.isSelected() );
    }//GEN-LAST:event_jcbPortatoreHandicapActionPerformed

    private void jbContattoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbContattoActionPerformed
        System.out.println("->-> "+personaBean.getNome()+"\n"+
                            personaBean.getCognome()+"\n"+
                            personaBean.getCognome()+"\n"+
                            personaBean.isPortatoreHandicap()+"\n");
        if(personaBean.getDataNascita()!=null) System.out.println(personaBean.getDataNascita().toString());  //DEBUG CODE
        new GestioneContattoDialog(null,true,contattoBean).setVisible(true);   
    }//GEN-LAST:event_jbContattoActionPerformed
    
    private void jpnlDataNascitaPropertyChanged(PropertyChangeEvent e){
        System.out.println("PCDataPanel ");
        if(inizializzato) personaBean.setDataNascita(jpnlDataNascita.getDataSelezionata());
    }
    
    
    /** variabili locali */
    private PersonaBean personaBean = null; //Bean associato
    private ContattoBean contattoBean = null;
    private NazioniBean nazioniBean = null;
    private ReligioniBean religioniBean = null;
    
    private static int MASCHIO = UtilitaGUI.MASCHIO;
    private static int FEMMINA = UtilitaGUI.FEMMINA;
    private boolean inizializzato = false;
    
    javax.swing.ButtonGroup bgSesso = new javax.swing.ButtonGroup();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbContatto;
    private javax.swing.JCheckBox jcbPortatoreHandicap;
    private javax.swing.JComboBox jcmbNazionalita;
    private javax.swing.JComboBox jcmbReligione;
    private javax.swing.JLabel jlblCodiceFiscale;
    private javax.swing.JLabel jlblCognome;
    private javax.swing.JLabel jlblContatto;
    private javax.swing.JLabel jlblDataNascita;
    private javax.swing.JLabel jlblNazionalita;
    private javax.swing.JLabel jlblNome;
    private javax.swing.JLabel jlblPortatoreHandicap;
    private javax.swing.JLabel jlblReligione;
    private javax.swing.JLabel jlblSesso;
    private nu.mine.egoless.didattica.app.gui.componentiComuni.DataPanel jpnlDataNascita;
    private javax.swing.JRadioButton jrbFemmina;
    private javax.swing.JRadioButton jrbMaschio;
    private javax.swing.JTextField jtfCodiceFiscale;
    private javax.swing.JTextField jtfCognome;
    private javax.swing.JTextField jtfNome;
    // End of variables declaration//GEN-END:variables
    
}
