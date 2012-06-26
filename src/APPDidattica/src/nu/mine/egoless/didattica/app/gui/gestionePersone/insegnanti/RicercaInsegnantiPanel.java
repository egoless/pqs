/*
 * RicercaInsegnantiPanel.java
 *
 * Created on 8 marzo 2007, 22.43
 */

package nu.mine.egoless.didattica.app.gui.gestionePersone.insegnanti;

import nu.mine.egoless.didattica.app.gui.gestionePersone.*;
import nu.mine.egoless.didattica.ws.personaclient.Docente;
import nu.mine.egoless.didattica.ws.personaclient.ParametriRicercaDocente;
import nu.mine.egoless.didattica.app.bean.InsegnantiBean;
import nu.mine.egoless.didattica.app.bean.NazioniBean;
import nu.mine.egoless.didattica.app.bean.ReligioniBean;
import nu.mine.egoless.didattica.app.bean.MaterieInsegnamentoBean;
import nu.mine.egoless.didattica.app.bean.ClassiBean;
import nu.mine.egoless.didattica.app.bean.TipiVotiBean;
import nu.mine.egoless.didattica.app.bean.TipiAssenzeBean;
import nu.mine.egoless.didattica.app.bean.Costanti;
import nu.mine.egoless.didattica.ws.religioneclient.Religione;
import nu.mine.egoless.didattica.ws.nazioneclient.Nazione;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ContenitoreDati;
import nu.mine.egoless.didattica.app.gui.componentiComuni.UtilitaGUI;
import nu.mine.egoless.didattica.ws.materiaclient.MateriaInsegnamento;
import java.net.PortUnreachableException;
import javax.swing.SwingWorker;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import nu.mine.egoless.didattica.ws.personaclient.Date;
import nu.mine.egoless.didattica.app.adapter.*;

/**
 *
 * @author  Lorenzo Daniele
 *
 * Classe che implementa un pannello di ricerca contenente un form dove
 * immettere i parametri ed una tabella dove presentare i risultati
 */
public class RicercaInsegnantiPanel extends RicercaPersonePanel {
    
    /** 
     * Creates new form RicercaInsegnantiPanel 
     * @param jpnlAncestor rappresenta il GestioneTabPanel che contiene tale pannello
     * @param cont il contenitore dei dati caricati inizalmente dall'applicazione
     */ 
    public RicercaInsegnantiPanel(GestioneTabInsegnantiPanel jpnlGestioneInsegnanti, ContenitoreDati cont) {
        super(jpnlGestioneInsegnanti);
        if(cont == null) throw new IllegalArgumentException();
        this.contenitore = cont;
        inizializzaComponenti();
    }

     /**
     * @retrun il GestioneTabInsegnantiPanel passato come argomento al costruttore
     **/
    public GestioneTabInsegnantiPanel getAncestor(){ return (GestioneTabInsegnantiPanel) super.getAncestor(); }    

    /**
     * rende agibili o inagibili tutte le componenti del pannello
     */
    public void setEnabled(boolean b){
        jtfRicercaCognome.setEnabled(b);
        jtfRicercaNome.setEnabled(b);
        jcmbMateria.setEnabled(b);
        jbPulisciRicerca.setEnabled(b);
        jbCerca.setEnabled(b);
        jbNuovaRicerca.setEnabled(b);
        jbModifica.setEnabled(b);
        jbNuovo.setEnabled(b);   
        jbPulisciRisultatiRicerca.setEnabled(b);  
    }    
    
    /**
     * main di prova
     */    
    public static void main(String[] args) {
        /*javax.swing.JDialog temp=new javax.swing.JDialog();
        temp.setLayout(new java.awt.BorderLayout());
        ContenitoreDati c = new ContenitoreDati(new ReligioniBean(), new NazioniBean(), new ClassiBean(), new MaterieInsegnamentoBean(), new TipiVotiBean(), new TipiAssenzeBean());       
        temp.add(new RicercaInsegnantiPanel(new GestioneTabInsegnantiPanel(c), c));
        temp.setSize(500,500);
        temp.setVisible(true);*/
    }

    /**
    * lancia la ricerca secondo i parametri contenuti nella variabile locale parametriRicercaStudente
    */
    private void effettuaRicerca() throws PortUnreachableException,nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception {
        synchronized(insegnantiBean){ //evita problemi se l'utente schiaccia nuovamente ricerca prima che quella precedente sia finita
            insegnantiBean.caricaInsegnanti(parametriRicercaInsegnante); 
        }
    }    

    /**
     * mostra i valori contenuti in studentiBean nella tabella dei risultati
     */    
    private void visualizzaRisultati(){
        synchronized(insegnantiBean){
            for(int i=0; i< insegnantiBean.ritornaNumeroDiInsegnanti(); i++){
                Docente insegnante = insegnantiBean.getInsegnanteAt(i);
                Religione religione = UtilitaGUI.getReligione( contenitore.getReligioni(), insegnante.getReligioneId() );
                Nazione nazione = UtilitaGUI.getNazione( contenitore.getNazioni(), insegnante.getNazioneId() );                
                Boolean b = insegnante.isPortatoreHandicap();
                Integer sesso = insegnante.getSesso();
                String materie = "(...)", 
                       r = "not found", 
                       n = "not found",
                       nascita = "_",
                       assunzione ="_";
                if( religione != null ) r = religione.getNome();
                if( nazione != null ) n = nazione.getNome();
                if( insegnante.getDataNascita()!= null ) nascita = insegnante.getDataNascita().getDate();
                if( insegnante.getDataAssunzione()!= null ) assunzione = insegnante.getDataAssunzione().getDate();
                if( b == null) b = new Boolean(false);
                if( sesso == null ) sesso = new Integer(UtilitaGUI.MASCHIO);
                //materie = UtilitaGUI.getStringaElencoMaterie(insegnante.getIdMaterie(), contenitore); //TO DO -> in docente non c'è getIdMaterie() !
                //inizializzazione della string delle materie
                //long[] ids = insegnante.getIdMaterie();
                //for( int i=0; i<ids.length; i++ ){
                    //MateriaInsegnamento materia = UtilitaGUI.getMateriaInsegnamento( contenitore.getMaterieInsegnamento(), ids[i] );
                    //if(materia!=null) m+=materia.getNome()+"\n";
                    //else System.out.println("Anteprima: Errore imprevedibile -> id non corrispondente ad alcuna materia");
                //}                
                //jtblRisultatiRicerca.setValueAt( insegnante.getCognome(),i,COL_NOME );//Cognome
                //jtblRisultatiRicerca.setValueAt( insegnante.getNome(),i,COL_COGNOME );//Nome                
                //jtblRisultatiRicerca.setValueAt( m, i, COL_MATERIA ); //Materia     
                Object[] temp = {
                                  insegnante.getCognome(), 
                                  insegnante.getNome(), 
                                  materie,
                                  assunzione,
                                  UtilitaGUI.getStringaSesso(sesso),
                                  nascita,                                  
                                  insegnante.getCodiceFiscale(), 
                                  UtilitaGUI.valoreBoolean(b), 
                                  n,
                                  r                                 
                                };
                UtilitaGUI.inserisciRiga( jtblRisultatiRicerca, i, temp );                                          
            }
        }
    }

    /**
     * Elimina un risultato da quelli visualizzati e correntemente memorizzati in studentiBean
     */
    public void cancellaRisultatoRicercaERivisualizza(long id){
        boolean go = true;
        for( int i=0; i<insegnantiBean.ritornaNumeroDiInsegnanti() && go; i++ ){
            Docente insegnante = insegnantiBean.getInsegnanteAt(i);
            if(insegnante.getId() == id){
                go=false;
                System.out.println(insegnantiBean.ritornaNumeroDiInsegnanti());
                insegnantiBean.rimuoviInsegnanteAt(i);                
                System.out.println(insegnantiBean.ritornaNumeroDiInsegnanti());
            }
        }
        SwingUtilities.invokeLater( new Runnable(){
            public void run(){
                UtilitaGUI.rimuoviRighe(jtblRisultatiRicerca);
                visualizzaRisultati();
            }
        });
    }  
    
    /**
     * Pulisce la tabella dei risultati
     */
    public void pulisciTabellaRisultati(){
        UtilitaGUI.rimuoviRighe(jtblRisultatiRicerca);
    }
        
    /**
     * inizializza le componenti grafiche del pannello
     */
    private void inizializzaComponenti(){
        initComponents();
        //parametriRicercaInsegnante.setNome(""); //TO DO
        //parametriRicercaInsegnante.setCognome(""); //TO DO     
        //inizializza il ComboBox della materia
        inizializzaComboBoxMaterie();
    }
    
    /**
     * Inizializza il combo box per la scelta del parametro di ricerca materia
     */
    private void inizializzaComboBoxMaterie(){
        MaterieInsegnamentoBean materieBean = contenitore.getMaterieInsegnamento();       
        for(int i=0; i<materieBean.ritornaNumeroDiMaterie(); i++){
            MateriaInsegnamento materia = materieBean.getMateriaAt(i);
            jcmbMateria.addItem(  new MateriaInsegnamentoAdapter(materia) );
        }        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jpnlBottoni = new javax.swing.JPanel();
        jbModifica = new javax.swing.JButton();
        jbNuovo = new javax.swing.JButton();
        jbPulisciRisultatiRicerca = new javax.swing.JButton();
        jprg = new javax.swing.JProgressBar();
        jplnParametriRicerca = new javax.swing.JPanel();
        jlblRicercaCognome = new javax.swing.JLabel();
        jtfRicercaCognome = new javax.swing.JTextField();
        jlblRicercaNome = new javax.swing.JLabel();
        jlblRicercaMateria = new javax.swing.JLabel();
        jtfRicercaNome = new javax.swing.JTextField();
        jbChiudiRicerca = new javax.swing.JButton();
        jbPulisciRicerca = new javax.swing.JButton();
        jbCerca = new javax.swing.JButton();
        jbNuovaRicerca = new javax.swing.JButton();
        jcmbMateria = new javax.swing.JComboBox();
        jbTutti = new javax.swing.JButton();
        jscpRisultatiRicerca = new javax.swing.JScrollPane();
        jtblRisultatiRicerca = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 204, 255));
        jpnlBottoni.setBackground(new java.awt.Color(204, 204, 255));
        jbModifica.setText("Modifica");
        jbModifica.setPreferredSize(new java.awt.Dimension(85, 28));
        jbModifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbModificaActionPerformed(evt);
            }
        });

        jbNuovo.setText(" Nuovo");
        jbNuovo.setPreferredSize(new java.awt.Dimension(85, 28));
        jbNuovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNuovoActionPerformed(evt);
            }
        });

        jbPulisciRisultatiRicerca.setText("Pulisci");
        jbPulisciRisultatiRicerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPulisciRisultatiRicercaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnlBottoniLayout = new javax.swing.GroupLayout(jpnlBottoni);
        jpnlBottoni.setLayout(jpnlBottoniLayout);
        jpnlBottoniLayout.setHorizontalGroup(
            jpnlBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlBottoniLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jprg, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlBottoniLayout.createSequentialGroup()
                        .addComponent(jbPulisciRisultatiRicerca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbNuovo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbModifica, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addContainerGap())
        );

        jpnlBottoniLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jbModifica, jbNuovo, jbPulisciRisultatiRicerca});

        jpnlBottoniLayout.setVerticalGroup(
            jpnlBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlBottoniLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnlBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbModifica, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbNuovo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbPulisciRisultatiRicerca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jprg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jplnParametriRicerca.setBackground(new java.awt.Color(204, 204, 255));
        jplnParametriRicerca.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametri di Ricerca"));
        jlblRicercaCognome.setText("Cognome:");

        jtfRicercaCognome.setEnabled(false);
        jtfRicercaCognome.setMinimumSize(new java.awt.Dimension(100, 19));
        jtfRicercaCognome.setPreferredSize(new java.awt.Dimension(100, 19));
        jtfRicercaCognome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfRicercaCognomeKeyReleased(evt);
            }
        });

        jlblRicercaNome.setText("Nome:");

        jlblRicercaMateria.setText("Materia:");

        jtfRicercaNome.setEnabled(false);
        jtfRicercaNome.setMinimumSize(new java.awt.Dimension(100, 19));
        jtfRicercaNome.setPreferredSize(new java.awt.Dimension(100, 19));
        jtfRicercaNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfRicercaNomeKeyReleased(evt);
            }
        });

        jbChiudiRicerca.setText("Chiudi");
        jbChiudiRicerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbChiudiRicercaActionPerformed(evt);
            }
        });

        jbPulisciRicerca.setText("Pulisci");
        jbPulisciRicerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPulisciRicercaActionPerformed(evt);
            }
        });

        jbCerca.setText("Cerca");
        jbCerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCercaActionPerformed(evt);
            }
        });

        jbNuovaRicerca.setText("Nuova Ricerca");
        jbNuovaRicerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNuovaRicercaActionPerformed(evt);
            }
        });

        jcmbMateria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbMateriaItemStateChanged(evt);
            }
        });

        jbTutti.setText("Tutti");
        jbTutti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTuttiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jplnParametriRicercaLayout = new javax.swing.GroupLayout(jplnParametriRicerca);
        jplnParametriRicerca.setLayout(jplnParametriRicercaLayout);
        jplnParametriRicercaLayout.setHorizontalGroup(
            jplnParametriRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplnParametriRicercaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplnParametriRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplnParametriRicercaLayout.createSequentialGroup()
                        .addGroup(jplnParametriRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblRicercaCognome)
                            .addComponent(jlblRicercaNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jplnParametriRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfRicercaCognome, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(jtfRicercaNome, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addComponent(jlblRicercaMateria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplnParametriRicercaLayout.createSequentialGroup()
                        .addComponent(jbNuovaRicerca, javax.swing.GroupLayout.PREFERRED_SIZE, 63, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbTutti)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbCerca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbPulisciRicerca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbChiudiRicerca)))
                .addContainerGap())
        );

        jplnParametriRicercaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jbCerca, jbChiudiRicerca, jbPulisciRicerca, jbTutti});

        jplnParametriRicercaLayout.setVerticalGroup(
            jplnParametriRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplnParametriRicercaLayout.createSequentialGroup()
                .addGroup(jplnParametriRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblRicercaCognome)
                    .addComponent(jtfRicercaCognome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblRicercaMateria)
                    .addComponent(jcmbMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jplnParametriRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblRicercaNome)
                    .addComponent(jtfRicercaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplnParametriRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbChiudiRicerca)
                    .addComponent(jbPulisciRicerca)
                    .addComponent(jbCerca)
                    .addComponent(jbTutti)
                    .addComponent(jbNuovaRicerca))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jscpRisultatiRicerca.setBackground(new java.awt.Color(204, 204, 255));
        jscpRisultatiRicerca.setBorder(javax.swing.BorderFactory.createTitledBorder("Risultati della Ricerca"));
        jtblRisultatiRicerca.setAutoCreateRowSorter(true);
        jtblRisultatiRicerca.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jtblRisultatiRicerca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cognome", "Nome", "Materia", "Data Assunzione", "Sesso", "Data di Nascita", "Codice Fiscale", "Portatore di Handicap", "Nazionalità", "Religione"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblRisultatiRicerca.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jtblRisultatiRicerca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblRisultatiRicercaMouseClicked(evt);
            }
        });

        jscpRisultatiRicerca.setViewportView(jtblRisultatiRicerca);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlBottoni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jplnParametriRicerca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jscpRisultatiRicerca, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jplnParametriRicerca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jscpRisultatiRicerca, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlBottoni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtblRisultatiRicercaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblRisultatiRicercaMouseClicked
        synchronized(insegnantiBean){        
            if(evt.getClickCount() == 2){
                int row = jtblRisultatiRicerca.rowAtPoint(evt.getPoint());
                if( row == -1 ) return;
                Docente s = insegnantiBean.getInsegnanteAt(row);
                if(s!=null){
                    getAncestor().apriNuovoPannelloDx(s.getId()); //Apre un nuovo pannello di gestione persona
                    //System.out.println ("ID: "+studente.getId ());
                }
                else System.out.println("RicInsegPanel: ERRORE Imprevisto -> invalid teacher");    
            }              
        }
    }//GEN-LAST:event_jtblRisultatiRicercaMouseClicked

    private void jbTuttiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTuttiActionPerformed
        parametriRicercaInsegnante.setIdMateria(Costanti.ID_NUOVA_MATERIA_INSEGNAMENTO);
        jbCercaActionPerformed(evt); //richiama la funzione per la ricerca
    }//GEN-LAST:event_jbTuttiActionPerformed

    private void jbPulisciRisultatiRicercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPulisciRisultatiRicercaActionPerformed
        pulisciTabellaRisultati();
    }//GEN-LAST:event_jbPulisciRisultatiRicercaActionPerformed

    private void jcmbMateriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbMateriaItemStateChanged
        System.out.println("APMateria ");  //DEBUG CODE      
        MateriaInsegnamentoAdapter m = ( (MateriaInsegnamentoAdapter) jcmbMateria.getSelectedItem() );
        //System.out.println ("I: "+m);       
        parametriRicercaInsegnante.setIdMateria( m.getMateriaInsegnamento().getId() );
    }//GEN-LAST:event_jcmbMateriaItemStateChanged

    /**
     * metodi richiamati dai listener agganciati alle varie componenti del pannello
     */        
    private void jtfRicercaNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfRicercaNomeKeyReleased
        System.out.println("KRNome");  //DEBUG CODE        
        //parametriRicercaInsegnante.setNome(jtfRicercaNome.getText()); //TO DO
    }//GEN-LAST:event_jtfRicercaNomeKeyReleased

    private void jtfRicercaCognomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfRicercaCognomeKeyReleased
        System.out.println("KRNome");  //DEBUG CODE
        //parametriRicercaInsegnante.setCognome(jtfRicercaNome.getText()); //TO DO
    }//GEN-LAST:event_jtfRicercaCognomeKeyReleased

    private void jbNuovaRicercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNuovaRicercaActionPerformed
        getAncestor().apriNuovoPannelloSx();
    }//GEN-LAST:event_jbNuovaRicercaActionPerformed

    private void jbCercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCercaActionPerformed
        MateriaInsegnamento materia = UtilitaGUI.getMateriaInsegnamento( contenitore.getMaterieInsegnamento(), parametriRicercaInsegnante.getIdMateria() );
        System.out.println("-----------------------------\n"
                            +"Parametri di Ricerca: "
                            //+"Cognome: "+parametriRicercaInsegnante.getCognome() //TO DO
                            //+"Nomeome: "+parametriRicercaInsegnante.getNome() //TO DO
                            +"Materia: "+UtilitaGUI.getMateriaInsegnamento( contenitore.getMaterieInsegnamento(), parametriRicercaInsegnante.getIdMateria() )
                            +"\n"
                            +"------------------------------"); //DEBUG CODE
        new SwingWorker<Object, Object>(){
            public Object doInBackground(){
                try{
                    getAncestor().setTesto("Ricerca in corso...");
                    jprg.setIndeterminate(true);
                    getAncestor().setDownloadIndeterminato(true); 
                    System.out.println("Caricamento dei dati in corso"); //DEBUG CODE
                    //carica gli studenti secondo i parametri di ricerca immessi
                    effettuaRicerca();             
                    System.out.println("Visualizza risultati"); //DEBUG CODE
                    //Visualizza i risultati nella matrice
                    SwingUtilities.invokeAndWait( new Runnable(){
                        public void run(){
                            visualizzaRisultati();
                        }
                    });
                }catch(InterruptedException e){
                    System.out.println("RicercaStudentiPanel: Ecc0 "); //DEBUG CODE
                    JOptionPane.showMessageDialog(main,"Problemi di visualizzazione");
                    getAncestor().chiudiPannelloSx(main);
                    getAncestor().setTesto("Ricerca terminata con insuccesso...");                 
                }
                 catch(java.lang.reflect.InvocationTargetException e){
                    System.out.println("RicercaStudentiPanel: Ecc1 "); //DEBUG CODE
                    JOptionPane.showMessageDialog(main,"Problemi di visualizzazione");
                    getAncestor().chiudiPannelloSx(main);
                    getAncestor().setTesto("Ricerca terminata con insuccesso...");                     
                 }
                 catch(Exception e){ //Se è stata lanciata un eccezione (probabillmente dovuta al fatto che non è possibile raggiungere il server)
                    System.out.println("RicercaStudentiPanel: Ecc2 "); //DEBUG CODE
                    UtilitaGUI.messaggioWSNonRaggiungibile(main);
                    getAncestor().setTesto("Ricerca terminata con insuccesso...");
                 }
                 finally{
                    jprg.setIndeterminate(false);
                    getAncestor().setDownloadIndeterminato(false);  
                    getAncestor().setTesto("Ricerca terminata...");
                    return null;
                }
            } //doInBackground
        }.execute(); //Swing Worker        
    }//GEN-LAST:event_jbCercaActionPerformed

    private void jbPulisciRicercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPulisciRicercaActionPerformed
        jtfRicercaCognome.setText("");
        jtfRicercaNome.setText("");
        //System.out.println("->->->"+new Boolean(javax.swing.SwingUtilities.isEventDispatchThread()).toString() );  //DEBUG Code
    }//GEN-LAST:event_jbPulisciRicercaActionPerformed

    private void jbChiudiRicercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbChiudiRicercaActionPerformed
        getAncestor().chiudiPannelloSx(this);
    }//GEN-LAST:event_jbChiudiRicercaActionPerformed

    private void jbNuovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNuovoActionPerformed
        getAncestor().apriNuovoPannelloDx();
    }//GEN-LAST:event_jbNuovoActionPerformed

    private void jbModificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbModificaActionPerformed
        synchronized(insegnantiBean){
            int[] selezioni = jtblRisultatiRicerca.getSelectedRows();
            //non lo inseriamo in uno SwingWorker ma lo lasciamo nell'event dispaching thread per evitare
            //che nel frattempo venga effettuata un altra ricerca e quindi i dati diventino inconsistenti (prenderebbe dalla table dati modificati!).
            //Comunque il caricamento dei dati dello studente in GestioneStudentePanel avviene tramite uno SwingWorker              
            for(int i=0; i<selezioni.length; i++){
                Docente insegnante = insegnantiBean.getInsegnanteAt(selezioni[i]);
                if(insegnante!=null)
                    getAncestor().apriNuovoPannelloDx(insegnante.getId());  //Apre un nuovo pannello di gestione persona
                else System.out.println("RicInsegPanel: ERRORE Imprevisto -> invalid null pointer");               
            }
        }
    }//GEN-LAST:event_jbModificaActionPerformed
    
    //variabili locali
    final private RicercaInsegnantiPanel main = this;
    private ParametriRicercaDocente parametriRicercaInsegnante = new ParametriRicercaDocente();
    private InsegnantiBean insegnantiBean = new InsegnantiBean();
    private ContenitoreDati contenitore = null;
    //Identificano la colonna nalla JTable dove verrà visualizzato il particolare attributo
    private static int COL_MATERIA = 2;
    private static int COL_NOME = 1;
    private static int COL_COGNOME = 0;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbCerca;
    private javax.swing.JButton jbChiudiRicerca;
    private javax.swing.JButton jbModifica;
    private javax.swing.JButton jbNuovaRicerca;
    private javax.swing.JButton jbNuovo;
    private javax.swing.JButton jbPulisciRicerca;
    private javax.swing.JButton jbPulisciRisultatiRicerca;
    private javax.swing.JButton jbTutti;
    private javax.swing.JComboBox jcmbMateria;
    private javax.swing.JLabel jlblRicercaCognome;
    private javax.swing.JLabel jlblRicercaMateria;
    private javax.swing.JLabel jlblRicercaNome;
    private javax.swing.JPanel jplnParametriRicerca;
    private javax.swing.JPanel jpnlBottoni;
    private javax.swing.JProgressBar jprg;
    private javax.swing.JScrollPane jscpRisultatiRicerca;
    private javax.swing.JTable jtblRisultatiRicerca;
    private javax.swing.JTextField jtfRicercaCognome;
    private javax.swing.JTextField jtfRicercaNome;
    // End of variables declaration//GEN-END:variables
    
}
