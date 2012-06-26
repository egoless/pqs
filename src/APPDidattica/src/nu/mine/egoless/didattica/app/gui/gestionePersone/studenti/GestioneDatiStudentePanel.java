/*
 * GestioneDatiStudentePanel.java
 *
 * Created on 11 marzo 2007, 3.53
 */

package nu.mine.egoless.didattica.app.gui.gestionePersone.studenti;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.Date;
import nu.mine.egoless.didattica.app.bean.StudenteBean;
import nu.mine.egoless.didattica.app.bean.NazioniBean;
import nu.mine.egoless.didattica.app.bean.ReligioniBean;
import nu.mine.egoless.didattica.app.bean.ClassiBean;
import nu.mine.egoless.didattica.app.bean.MaterieInsegnamentoBean;
import nu.mine.egoless.didattica.app.bean.TipiVotiBean;
import nu.mine.egoless.didattica.app.bean.TipiAssenzeBean;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ContenitoreDati;
import nu.mine.egoless.didattica.app.gui.componentiComuni.UtilitaGUI;
import nu.mine.egoless.didattica.ws.classesupport.Classe;
import nu.mine.egoless.didattica.app.adapter.*;
import nu.mine.egoless.didattica.app.gui.registroDocente.ListaAssenzeStudenteDialog;
import nu.mine.egoless.didattica.app.gui.registroDocente.ListaVotiStudenteDialog;

/**
 *
 * @author  Lorenzo Daniele
 *
 * Pannello per gestire i dati specifici di uno studente (matricola, 
 * data di iscrizione e classe di frequenza)
 */
public class GestioneDatiStudentePanel extends javax.swing.JPanel {
    
    /** 
     * Creates new form GestioneDatiStudentePanel 
     * @param studente lo studente i cui dati devono essere gestiti
     * @param cont il contenitore dei dati caricati inizialmente dall'applicazione
     */
    public GestioneDatiStudentePanel(StudenteBean studente, ContenitoreDati cont) {
        if(studente==null || cont== null) throw new IllegalArgumentException();
        inizializzato = false;
        this.contenitore = cont;
        this.studenteBean = studente;
        inizializzaComponenti(); //inizializza le componenti grafiche
        //associazione dei listener per i cambiamenti dei dati sui bean
        aggiungiPropertyListener();
        inizializzaForm();
        inizializzato = true;
    }

    /**
     * rende agibili o inagibili tutte le componenti del pannello
     */
    public void setEnabled(boolean b){
        jtfMatricola.setEnabled(b);
        jpnlDataIscrizione.setEnabled(b);
        jcmbClasse.setEnabled(b);
    }    

    
    /**
     * main di prova
     */
    public static void main(String[] args) {
        //System.out.println("AVVIO");
        javax.swing.JDialog temp=new javax.swing.JDialog();
        temp.setLayout(new java.awt.BorderLayout());
        //ContenitoreDati c = new ContenitoreDati(new ReligioniBean(), new NazioniBean(), new ClassiBean(), new MaterieInsegnamentoBean(), new TipiVotiBean(), new TipiAssenzeBean());
        //temp.add( new GestioneDatiStudentePanel(new StudenteBean(), c ));
        temp.setSize(500,500);
        temp.setVisible(true);
        //System.out.println("TERMINEAVVIO");
    }    
    
    /**
     * Aggiunge i listener per i cambiamenti di stato sullo studenteBean
     */
    private void aggiungiPropertyListener(){
        studenteBean.addPropertyChangeListener( studenteBean.MATRICOLA, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    System.out.println("PCEMatricola "); //DEBUGCODE
                    if(event.getNewValue()!=null) jtfMatricola.setText( (String) event.getNewValue() );
                    else jtfMatricola.setText( "" );      
                }
            }              
        );  
        studenteBean.addPropertyChangeListener( studenteBean.DATA_ISCRIZIONE, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    System.out.println("PCEData "); //DEBUGCODE
                    if(event.getNewValue()!=null) jpnlDataIscrizione.setData( (Date) event.getNewValue() );
                    else jpnlDataIscrizione.setData( new Date() );                                    
                }
            }              
        );  
        studenteBean.addPropertyChangeListener( studenteBean.ID_CLASSE, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    System.out.println("PCEClasse "); //DEBUGCODE
                    if(event.getNewValue()!=null){
                        int id = ( (Integer) event.getNewValue() ).intValue();                  
                        Classe classe = UtilitaGUI.getClasse( contenitore.getClassi(), id );
                        if(classe==null) throw new IllegalArgumentException();
                        jcmbClasse.setSelectedItem( new ClasseAdapter( classe ) );
                    }
                    else jcmbClasse.setSelectedIndex(0);
                }
            }              
        );                    
    }
    
    /**
     * inizializza le componenti grafiche del pannello
     */
    private void inizializzaComponenti(){
        initComponents();
        //Listener per i cambiamenti sul DataPanel
        jpnlDataIscrizione.addPropertyChangeListener( new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent e){
                    jpnlDataIscrizionePropertyChanged(e);
                }
            }
        );       
        //inizializza il comboBox per la classe
        inizializzaComboBoxClassi();        
    }
    
    private void inizializzaForm(){
       try{
       jtfMatricola.setText(studenteBean.getMatricola()); 
       jpnlDataIscrizione.setData(studenteBean.getDataIscrizione());
       
       //TODO: codice non completamente testato
       //Impostiamo la classe direttamente nello StudenteBean, dopodiche'
       //viene aggiornata automaticamente l'interfaccia.
       //ClasseAdapter c = new ClasseAdapter(contenitore.getClassi().getClasseAt(0));
       //studenteBean.setIdClasse(c.getClasse().getId());
       
       //Vecchio Codice
       ClasseAdapter c = new ClasseAdapter( UtilitaGUI.getClasse(contenitore.getClassi(), studenteBean.getIdClasse()) );
       jcmbClasse.setSelectedItem( c );
       //studenteBean.setIdClasse(c.getClasse().getId());
       }catch(Exception e){System.out.println (e.getClass()); e.printStackTrace ();}
    } 
    
    /**
     * Inizializza il combo box per la scelta della classe da asseggnare allo studente
     */    
    private void inizializzaComboBoxClassi(){
        ClassiBean classi = contenitore.getClassi(); 
        for(int i=0; i<classi.ritornaNumeroDiClassi(); i++){
            Classe c = classi.getClasseAt(i);
            jcmbClasse.addItem( new ClasseAdapter(c) );
        }   
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jpnlDatiSpecifici = new javax.swing.JPanel();
        jlblMatricola = new javax.swing.JLabel();
        jtfMatricola = new javax.swing.JTextField();
        jlblDataIscrizione = new javax.swing.JLabel();
        jbVisualizzaVotazioni = new javax.swing.JButton();
        jbVisualizzaAssenze = new javax.swing.JButton();
        jpnlDataIscrizione = new nu.mine.egoless.didattica.app.gui.componentiComuni.DataPanel();
        jlblClasse = new javax.swing.JLabel();
        jcmbClasse = new javax.swing.JComboBox();

        jpnlDatiSpecifici.setBackground(new java.awt.Color(204, 204, 255));
        jpnlDatiSpecifici.setBorder(javax.swing.BorderFactory.createTitledBorder("Dati Scolastici"));
        jlblMatricola.setDisplayedMnemonic('M');
        jlblMatricola.setText("Matricola: ");

        jtfMatricola.setText("518667");
        jtfMatricola.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfMatricolaKeyReleased(evt);
            }
        });

        jlblDataIscrizione.setDisplayedMnemonic('I');
        jlblDataIscrizione.setText("Data Iscrizione: ");

        jbVisualizzaVotazioni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/Properties16.gif")));
        jbVisualizzaVotazioni.setMnemonic('V');
        jbVisualizzaVotazioni.setText("Visualizza Votazioni");
        jbVisualizzaVotazioni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVisualizzaVotazioniActionPerformed(evt);
            }
        });

        jbVisualizzaAssenze.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/Properties16.gif")));
        jbVisualizzaAssenze.setMnemonic('A');
        jbVisualizzaAssenze.setText("Visualizza Assenze");
        jbVisualizzaAssenze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVisualizzaAssenzeActionPerformed(evt);
            }
        });

        jlblClasse.setText("Classe: ");

        jcmbClasse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbClasseItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jpnlDatiSpecificiLayout = new javax.swing.GroupLayout(jpnlDatiSpecifici);
        jpnlDatiSpecifici.setLayout(jpnlDatiSpecificiLayout);
        jpnlDatiSpecificiLayout.setHorizontalGroup(
            jpnlDatiSpecificiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlDatiSpecificiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlDatiSpecificiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbVisualizzaAssenze, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlDatiSpecificiLayout.createSequentialGroup()
                        .addGroup(jpnlDatiSpecificiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblMatricola)
                            .addComponent(jlblDataIscrizione)
                            .addComponent(jlblClasse))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnlDatiSpecificiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcmbClasse, 0, 179, Short.MAX_VALUE)
                            .addComponent(jpnlDataIscrizione, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(jtfMatricola, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)))
                    .addComponent(jbVisualizzaVotazioni, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnlDatiSpecificiLayout.setVerticalGroup(
            jpnlDatiSpecificiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlDatiSpecificiLayout.createSequentialGroup()
                .addGroup(jpnlDatiSpecificiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblMatricola)
                    .addComponent(jtfMatricola, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlDatiSpecificiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblDataIscrizione)
                    .addComponent(jpnlDataIscrizione, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlDatiSpecificiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcmbClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblClasse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jbVisualizzaVotazioni, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbVisualizzaAssenze, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlDatiSpecifici, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlDatiSpecifici, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcmbClasseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbClasseItemStateChanged
        System.out.println("ISCClasse"); //DEBUGCODE
        ClasseAdapter classe = ( (ClasseAdapter) jcmbClasse.getSelectedItem() );
        if(inizializzato) studenteBean.setIdClasse( classe.getClasse().getId() );
    }//GEN-LAST:event_jcmbClasseItemStateChanged

    /**
     * metodi richiamati dai listener agganciati alle varie componenti del pannello
     */        
    private void jtfMatricolaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfMatricolaKeyReleased
        System.out.println("KRMatricola"); //DEBUGCODE
        if(inizializzato) studenteBean.setMatricola(jtfMatricola.getText());
    }//GEN-LAST:event_jtfMatricolaKeyReleased

    private void jbVisualizzaAssenzeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizzaAssenzeActionPerformed
        ListaAssenzeStudenteDialog t = new ListaAssenzeStudenteDialog(studenteBean.getNome ()+" "+studenteBean.getCognome (), studenteBean.getId (), contenitore.getTipiAssenze());
        t.setVisible(true);
    }//GEN-LAST:event_jbVisualizzaAssenzeActionPerformed

    private void jbVisualizzaVotazioniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizzaVotazioniActionPerformed
        ListaVotiStudenteDialog t = new ListaVotiStudenteDialog( studenteBean.getId (), -1, contenitore);
        t.setVisible(true);        
    }//GEN-LAST:event_jbVisualizzaVotazioniActionPerformed
    
    private void jpnlDataIscrizionePropertyChanged(PropertyChangeEvent e){
        if(inizializzato) studenteBean.setDataIscrizione(jpnlDataIscrizione.getDataSelezionata());
    }
    
    /**variabili locali*/
    private StudenteBean studenteBean = null;
    private ContenitoreDati contenitore = null;
    
    private boolean inizializzato = false;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbVisualizzaAssenze;
    private javax.swing.JButton jbVisualizzaVotazioni;
    private javax.swing.JComboBox jcmbClasse;
    private javax.swing.JLabel jlblClasse;
    private javax.swing.JLabel jlblDataIscrizione;
    private javax.swing.JLabel jlblMatricola;
    private nu.mine.egoless.didattica.app.gui.componentiComuni.DataPanel jpnlDataIscrizione;
    private javax.swing.JPanel jpnlDatiSpecifici;
    private javax.swing.JTextField jtfMatricola;
    // End of variables declaration//GEN-END:variables
    
}
