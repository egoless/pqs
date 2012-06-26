/*
 * RicercaStudentiPanel.java
 *
 * Created on 9 marzo 2007, 2.33
 */

package nu.mine.egoless.didattica.app.gui.gestionePersone.studenti;

import nu.mine.egoless.didattica.app.gui.gestionePersone.*;
import nu.mine.egoless.didattica.ws.personaclient.ParametriRicercaStudente;
import nu.mine.egoless.didattica.ws.personaclient.Studente;
import nu.mine.egoless.didattica.app.bean.StudentiBean;
import javax.swing.SwingWorker;
import javax.swing.SwingUtilities;
import java.util.List;
import nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception;
import java.net.PortUnreachableException;
import nu.mine.egoless.didattica.app.gui.componentiComuni.UtilitaGUI;
import javax.swing.JOptionPane;
import nu.mine.egoless.didattica.app.bean.NazioniBean;
import nu.mine.egoless.didattica.app.bean.ReligioniBean;
import nu.mine.egoless.didattica.app.bean.ClassiBean;
import nu.mine.egoless.didattica.app.bean.MaterieInsegnamentoBean;
import nu.mine.egoless.didattica.app.bean.TipiVotiBean;
import nu.mine.egoless.didattica.app.bean.TipiAssenzeBean;
import nu.mine.egoless.didattica.app.bean.Costanti;
import nu.mine.egoless.didattica.ws.classesupport.Classe;
import nu.mine.egoless.didattica.ws.religioneclient.Religione;
import nu.mine.egoless.didattica.ws.nazioneclient.Nazione;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ContenitoreDati;
import java.net.PortUnreachableException;
import nu.mine.egoless.didattica.ws.personaclient.Date;

/**
 *
 * @author  Lorenzo Daniele
 *
 * Classe che implementa un pannello di ricerca contenente un form dove
 * immettere i parametri ed una tabella dove presentare i risultati
 */
public class RicercaStudentiPanel extends RicercaPersonePanel {
    
    /**
     * Creates new form RicercaStudentiPanel
     * @param jpnlAncestor rappresenta il GestioneTabPanel che contiene tale pannello
     * @param cont il contenitore dei dati caricati inizalmente dall'applicazione
     */
    public RicercaStudentiPanel(GestioneTabStudentiPanel jpnlAncestor, ContenitoreDati cont) {
        super(jpnlAncestor);
        if(cont == null) throw new IllegalArgumentException();
        this.contenitore = cont;
        inizializzaComponenti();
    }
    
    /**
     * @retrun il GestioneTabStudentiPanel passato come argomento al costruttore
     **/
    public GestioneTabStudentiPanel getAncestor(){ return (GestioneTabStudentiPanel) super.getAncestor(); }    

    /**
     * rende agibili o inagibili tutte le componenti del pannello
     */
    public void setEnabled(boolean b){
        jtfRicercaCognome.setEnabled(b);
        jtfRicercaNome.setEnabled(b);
        jtfRicercaId.setEnabled(b);
        jcmbAnnoCorso.setEnabled(b);
        jcmbSezione.setEnabled(b);
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
        javax.swing.JDialog temp=new javax.swing.JDialog();
        temp.setLayout(new java.awt.BorderLayout());
        /*ContenitoreDati c = new ContenitoreDati(new ReligioniBean(), new NazioniBean(), new ClassiBean(), new MaterieInsegnamentoBean(), new TipiVotiBean(), new TipiAssenzeBean());       
        temp.add( new RicercaStudentiPanel(new GestioneTabStudentiPanel(c), c) ) ;
        temp.setSize(500,500);
        temp.setVisible(true);*/
    }   
    
    /**
     * lancia la ricerca secondo i parametri contenuti nella variabile locale parametriRicercaStudente
     */
    private void effettuaRicerca() throws PortUnreachableException,nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception {
        synchronized(studentiBean){ //evita problemi se l'utente schiaccia nuovamente ricerca prima che quella precedente sia finita
            studentiBean.caricaStudenti(parametriRicercaStudente); 
        }
    }
    
    /**
     * mostra i valori contenuti in studentiBean nella tabella dei risultati
     */
    private void visualizzaRisultati(){
        synchronized(studentiBean){
           //try{
            for(int i=0; i< studentiBean.ritornaNumeroDiStudenti(); i++){
                Studente studente = studentiBean.getStudenteAt(i); 
                System.out.println(studente);
                //Classe classe = UtilitaGUI.getClasse( contenitore.getClassi(), studente.getIdClasse()); //TO DO
                Religione religione = UtilitaGUI.getReligione( contenitore.getReligioni(), studente.getReligioneId() );
                Nazione nazione = UtilitaGUI.getNazione( contenitore.getNazioni(), studente.getNazioneId() );
                Boolean b = studente.isPortatoreHandicap();
                Integer sesso = studente.getSesso();                
                String c = "(...)", 
                       r = "not found",
                       n = "not found",
                       nascita = "_",
                       iscrizione ="_";                        
                //if( classe != null ) c = (new Integer(classe.getAnnoCorso())).toString() +" "+classe.getSezione(); //TO DO
                if( religione != null ) r = religione.getNome();
                if( nazione != null ) n = nazione.getNome();               
                if( b == null ) b=new Boolean(false);
                if( studente.getDataNascita()!= null ) nascita = studente.getDataNascita().getDate();
                if( studente.getDataIscrizione()!= null ) iscrizione = studente.getDataIscrizione().getDate();                
                if( sesso == null ) sesso = new Integer(UtilitaGUI.MASCHIO);                
                Object[] temp = { studente.getMatricola(), 
                                  studente.getCognome(), 
                                  studente.getNome(), 
                                  c, 
                                  iscrizione,
                                  UtilitaGUI.getStringaSesso(sesso),
                                  nascita,                                  
                                  studente.getCodiceFiscale(), 
                                  UtilitaGUI.valoreBoolean(b), 
                                  n,
                                  r
                                };
                UtilitaGUI.inserisciRiga( jtblRisultatiRicerca, i, temp ); 
            } 
           //}catch(Exception e){System.out.println ("RicercaStudentiPanel Ecc10: "+e.getClass ().toString ());}
        }
    }

    /**
     * Elimina un risultato da quelli visualizzati e correntemente memorizzati in studentiBean
     */
    public void cancellaRisultatoRicercaERivisualizza(int id){
        boolean go = true;
        for( int i=0; i<studentiBean.ritornaNumeroDiStudenti() && go; i++ ){
            Studente studente = studentiBean.getStudenteAt(i);
            if(studente.getId() == id){
                go=false;
                System.out.println(studentiBean.ritornaNumeroDiStudenti());
                studentiBean.rimuoviStudenteAt(i);                
                System.out.println(studentiBean.ritornaNumeroDiStudenti());
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
        parametriRicercaStudente.setNome(null);
        parametriRicercaStudente.setCognome(null);
        //parametriRicercaStudente.setMatricola(""); //TO DO
        //inizializza i ComboBox per l'anno di corso e per la sezione
        inizializzaComboBoxSezione();
        inizializzaComboBoxAnnoCorso();  
    }
    
    /**
     * Inizializza il combo box per il parametro anno di corso
     */    
    private void inizializzaComboBoxAnnoCorso(){
        int[] anniCorso = contenitore.getAnniCorso(); 
        for(int i=0; i<anniCorso.length; i++){
            jcmbAnnoCorso.addItem(new Integer(anniCorso[i]));
        }              
    }
    
    /**
     * Inizializza il combo box per il parametro sezione
     */
    private void inizializzaComboBoxSezione(){
        char[] sezioni = contenitore.getSezioni();
        for(int i=0; i<sezioni.length; i++){
            jcmbSezione.addItem(new Character(sezioni[i]));
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
        jlblRicercaId = new javax.swing.JLabel();
        jlblRicercaClasse = new javax.swing.JLabel();
        jtfRicercaNome = new javax.swing.JTextField();
        jtfRicercaId = new javax.swing.JTextField();
        jbChiudiRicerca = new javax.swing.JButton();
        jbPulisciRicerca = new javax.swing.JButton();
        jbCerca = new javax.swing.JButton();
        jbNuovaRicerca = new javax.swing.JButton();
        jcmbAnnoCorso = new javax.swing.JComboBox();
        jcmbSezione = new javax.swing.JComboBox();
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
                    .addComponent(jprg, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlBottoniLayout.createSequentialGroup()
                        .addComponent(jbPulisciRisultatiRicerca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbNuovo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbModifica, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jpnlBottoniLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jbModifica, jbNuovo, jbPulisciRisultatiRicerca});

        jpnlBottoniLayout.setVerticalGroup(
            jpnlBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlBottoniLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnlBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbModifica, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbNuovo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbPulisciRisultatiRicerca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jprg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpnlBottoniLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jbModifica, jbNuovo, jbPulisciRisultatiRicerca});

        jplnParametriRicerca.setBackground(new java.awt.Color(204, 204, 255));
        jplnParametriRicerca.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametri di Ricerca"));
        jlblRicercaCognome.setText("Cognome:");

        jtfRicercaCognome.setMinimumSize(new java.awt.Dimension(100, 19));
        jtfRicercaCognome.setPreferredSize(new java.awt.Dimension(100, 19));
        jtfRicercaCognome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfRicercaCognomeKeyReleased(evt);
            }
        });

        jlblRicercaNome.setText("Nome:");

        jlblRicercaId.setText("Matricola: ");

        jlblRicercaClasse.setText("Classe:");

        jtfRicercaNome.setMinimumSize(new java.awt.Dimension(100, 19));
        jtfRicercaNome.setPreferredSize(new java.awt.Dimension(100, 19));
        jtfRicercaNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfRicercaNomeKeyReleased(evt);
            }
        });

        jtfRicercaId.setEnabled(false);
        jtfRicercaId.setMinimumSize(new java.awt.Dimension(100, 19));
        jtfRicercaId.setPreferredSize(new java.awt.Dimension(100, 19));
        jtfRicercaId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfRicercaIdKeyReleased(evt);
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

        jcmbAnnoCorso.setEnabled(false);
        jcmbAnnoCorso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbAnnoCorsoItemStateChanged(evt);
            }
        });

        jcmbSezione.setEnabled(false);
        jcmbSezione.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbSezioneItemStateChanged(evt);
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
                            .addComponent(jtfRicercaCognome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfRicercaNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addGroup(jplnParametriRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblRicercaId)
                            .addComponent(jlblRicercaClasse))
                        .addGap(6, 6, 6)
                        .addGroup(jplnParametriRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jplnParametriRicercaLayout.createSequentialGroup()
                                .addComponent(jtfRicercaId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(2, 2, 2))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jplnParametriRicercaLayout.createSequentialGroup()
                                .addComponent(jcmbAnnoCorso, 0, 46, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcmbSezione, 0, 50, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplnParametriRicercaLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jbNuovaRicerca, javax.swing.GroupLayout.PREFERRED_SIZE, 63, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbTutti, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbCerca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbPulisciRicerca, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jtfRicercaId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblRicercaId))
                .addGap(6, 6, 6)
                .addGroup(jplnParametriRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblRicercaNome)
                    .addComponent(jtfRicercaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblRicercaClasse)
                    .addComponent(jcmbSezione, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcmbAnnoCorso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplnParametriRicercaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbChiudiRicerca)
                    .addComponent(jbPulisciRicerca)
                    .addComponent(jbCerca)
                    .addComponent(jbTutti)
                    .addComponent(jbNuovaRicerca))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jscpRisultatiRicerca.setBackground(new java.awt.Color(204, 204, 255));
        jscpRisultatiRicerca.setBorder(javax.swing.BorderFactory.createTitledBorder("Risultati della Ricerca"));
        jtblRisultatiRicerca.setAutoCreateRowSorter(true);
        jtblRisultatiRicerca.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jtblRisultatiRicerca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricola", "Cognome", "Nome", "Classe", "Data di Iscrizione", "Sesso", "Data di Nascita", "Codice Fiscale", "Portatore di Handicap", "Nazione", "Religione"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
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
            .addComponent(jplnParametriRicerca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnlBottoni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jscpRisultatiRicerca, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jplnParametriRicerca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jscpRisultatiRicerca, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlBottoni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtblRisultatiRicercaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblRisultatiRicercaMouseClicked
        synchronized(studentiBean){        
            if(evt.getClickCount() == 2){
                int row = jtblRisultatiRicerca.rowAtPoint(evt.getPoint());
                if( row == -1 ) return;
                Studente s = studentiBean.getStudenteAt(row);
                if(s!=null){
                    getAncestor().apriNuovoPannelloDx(s.getId()); //Apre un nuovo pannello di gestione persona
                    //System.out.println ("ID: "+studente.getId ());
                }
                else System.out.println("RicStudPanel: ERRORE Imprevisto -> invalid student");    
            }              
        }
    }//GEN-LAST:event_jtblRisultatiRicercaMouseClicked

    private void jbTuttiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTuttiActionPerformed
        parametriRicercaStudente.setCognome(null);
        parametriRicercaStudente.setNome(null);
        parametriRicercaStudente.setIdClasse(Costanti.ID_NUOVA_CLASSE);
        jbCercaActionPerformed(evt);
    }//GEN-LAST:event_jbTuttiActionPerformed

    private void jbPulisciRisultatiRicercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPulisciRisultatiRicercaActionPerformed
        pulisciTabellaRisultati();
    }//GEN-LAST:event_jbPulisciRisultatiRicercaActionPerformed

    private void jcmbSezioneItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbSezioneItemStateChanged
        System.out.println("APSezione ");  //DEBUG CODE
        //parametriRicercaStudente.setSezione( ((Character)jcmbSezione.getSelectedItem()).charValue() ); //TO DO
    }//GEN-LAST:event_jcmbSezioneItemStateChanged

    private void jcmbAnnoCorsoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbAnnoCorsoItemStateChanged
        System.out.println("APAnnoCorso ");  //DEBUG CODE
        //parametriRicercaStudente.setAnnoCorso( ((Integer)jcmbAnnoCorso.getSelectedItem()).intValue() ); //TO DO
    }//GEN-LAST:event_jcmbAnnoCorsoItemStateChanged

    /**
     * metodi richiamati dai listener agganciati alle varie componenti del pannello
     */        
    private void jtfRicercaNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfRicercaNomeKeyReleased
        System.out.println("KRNome ");  //DEBUG CODE
        parametriRicercaStudente.setNome(jtfRicercaNome.getText());
    }//GEN-LAST:event_jtfRicercaNomeKeyReleased

    private void jtfRicercaIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfRicercaIdKeyReleased
        System.out.println("KRRicerca "+jtfRicercaId.getText());  //DEBUG CODE
        //parametriRicercaStudente.setMatricola(jtfRicercaId.getText()); //TO DO
    }//GEN-LAST:event_jtfRicercaIdKeyReleased

    private void jtfRicercaCognomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfRicercaCognomeKeyReleased
        System.out.println("KRCognome ");  //DEBUG CODE
        parametriRicercaStudente.setCognome(jtfRicercaCognome.getText());
    }//GEN-LAST:event_jtfRicercaCognomeKeyReleased

    private void jbNuovaRicercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNuovaRicercaActionPerformed
        getAncestor().apriNuovoPannelloSx();
    }//GEN-LAST:event_jbNuovaRicercaActionPerformed

    private void jbCercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCercaActionPerformed
        System.out.println( "------------------------------\n"
                            +"Parametri di Ricerca: "
                            //+"  ID: "+parametriRicercaStudente.getMatricola() //TO DO
                            +"  Nome: "+parametriRicercaStudente.getNome()
                            +"  Cognome: "+parametriRicercaStudente.getCognome()
                            //+"  AnnoCorso: "+parametriRicercaStudente.getAnnoCorso() //TO DO
                            //+"  Sezione:"+((char)parametriRicercaStudente.getSezione()) //TO DO
                            +"\n"+
                            "------------------------------"); //DEBUG CODE
        new SwingWorker<Object, Object>(){
            public Object doInBackground(){
                try{
                    getAncestor().setTesto("Ricerca in corso...");
                    jprg.setIndeterminate(true);
                    getAncestor().setDownloadIndeterminato(true); 
                    System.out.println("Caricamento dei dati in corso"); //DEBUG CODE
                    //carica gli studenti secondo i parametri di ricerca immessi
                    effettuaRicerca();             
                    System.out.println("Visualizza risultati di: "+studentiBean.ritornaNumeroDiStudenti()+" studenti"); //DEBUG CODE
                    //Visualizza i risultati nella matrice
                    SwingUtilities.invokeAndWait( new Runnable(){
                        public void run(){
                            visualizzaRisultati();
                        }
                    });
                }catch(InterruptedException e){
                    System.out.println("RicercaStudentiPanel: Ecc0 "); e.printStackTrace(); //DEBUG CODE
                    JOptionPane.showMessageDialog(main,"Problemi di visualizzazione");
                    getAncestor().chiudiPannelloSx(main);
                    getAncestor().setTesto("Ricerca terminata con insuccesso...");                 
                }
                 catch(java.lang.reflect.InvocationTargetException e){
                    System.out.println("RicercaStudentiPanel: Ecc1 "); e.printStackTrace(); //DEBUG CODE
                    JOptionPane.showMessageDialog(main,"Problemi di visualizzazione");
                    getAncestor().chiudiPannelloSx(main);
                    getAncestor().setTesto("Ricerca terminata con insuccesso...");                     
                 }
                 catch(Exception e){ //Se è stata lanciata un eccezione (probabillmente dovuta al fatto che non è possibile raggiungere il server)
                    System.out.println("RicercaStudentiPanel: Ecc2 "); e.printStackTrace(); //DEBUG CODE
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
        jtfRicercaId.setText("");
    }//GEN-LAST:event_jbPulisciRicercaActionPerformed

    private void jbChiudiRicercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbChiudiRicercaActionPerformed
        getAncestor().chiudiPannelloSx(this);
    }//GEN-LAST:event_jbChiudiRicercaActionPerformed

    private void jbNuovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNuovoActionPerformed
        getAncestor().apriNuovoPannelloDx();  //Apre un nuovo pannello di gestione persona
    }//GEN-LAST:event_jbNuovoActionPerformed

    private void jbModificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbModificaActionPerformed
        synchronized(studentiBean){
            int[] selezioni = jtblRisultatiRicerca.getSelectedRows();
            //non lo inseriamo in uno SwingWorker ma lo lasciamo nell'event dispaching thread per evitare
            //che nel frattempo venga effettuata un altra ricerca e quindi i dati diventino inconsistenti (prenderebbe dalla table dati modificati!).
            //Comunque il caricamento dei dati dello studente in GestioneStudentePanel avviene tramite uno SwingWorker        
            for(int i=0; i<selezioni.length; i++){
                Studente studente = studentiBean.getStudenteAt(selezioni[i]);
                if(studente!=null){
                    getAncestor().apriNuovoPannelloDx(studente.getId()); //Apre un nuovo pannello di gestione persona
                    //System.out.println ("ID: "+studente.getId ());
                }
                else System.out.println("RicStudPanel: ERRORE Imprevisto -> invalid null pointer");    
            }
        }   
    }//GEN-LAST:event_jbModificaActionPerformed

    //variabili locali
    final private RicercaStudentiPanel main = this;
    private ParametriRicercaStudente parametriRicercaStudente = new ParametriRicercaStudente();
    private StudentiBean studentiBean = new StudentiBean();
    private ContenitoreDati contenitore = null;
    //Identificano la colonna nalla JTable dove verrà visualizzato il particolare attributo
    private static int COL_MATRICOLA = 0;
    private static int COL_NOME = 2;
    private static int COL_COGNOME = 1;
    private static int COL_CLASSE = 3;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbCerca;
    private javax.swing.JButton jbChiudiRicerca;
    private javax.swing.JButton jbModifica;
    private javax.swing.JButton jbNuovaRicerca;
    private javax.swing.JButton jbNuovo;
    private javax.swing.JButton jbPulisciRicerca;
    private javax.swing.JButton jbPulisciRisultatiRicerca;
    private javax.swing.JButton jbTutti;
    private javax.swing.JComboBox jcmbAnnoCorso;
    private javax.swing.JComboBox jcmbSezione;
    private javax.swing.JLabel jlblRicercaClasse;
    private javax.swing.JLabel jlblRicercaCognome;
    private javax.swing.JLabel jlblRicercaId;
    private javax.swing.JLabel jlblRicercaNome;
    private javax.swing.JPanel jplnParametriRicerca;
    private javax.swing.JPanel jpnlBottoni;
    private javax.swing.JProgressBar jprg;
    private javax.swing.JScrollPane jscpRisultatiRicerca;
    private javax.swing.JTable jtblRisultatiRicerca;
    private javax.swing.JTextField jtfRicercaCognome;
    private javax.swing.JTextField jtfRicercaId;
    private javax.swing.JTextField jtfRicercaNome;
    // End of variables declaration//GEN-END:variables
    
}
