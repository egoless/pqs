/*
 * GUIFrame.java
 *
 * Created on 6 marzo 2007, 12.10
 */
package nu.mine.egoless.didattica.app.gui;

import nu.mine.egoless.didattica.app.gui.gestionePersone.*;
import nu.mine.egoless.didattica.app.gui.registroDocente.*; //TO DO
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import nu.mine.egoless.didattica.app.bean.NazioniBean;
import nu.mine.egoless.didattica.app.bean.ReligioniBean;
import nu.mine.egoless.didattica.app.bean.MaterieInsegnamentoBean;
import nu.mine.egoless.didattica.app.bean.ClassiBean;
import nu.mine.egoless.didattica.app.bean.TipiVotiBean;
import nu.mine.egoless.didattica.app.bean.TipiAssenzeBean;
import nu.mine.egoless.didattica.app.bean.TipiProveBean;
import nu.mine.egoless.didattica.app.gui.gestionePersone.insegnanti.GestioneTabInsegnantiPanel;
import nu.mine.egoless.didattica.app.gui.gestionePersone.studenti.GestioneTabStudentiPanel;
import nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception;
import java.net.PortUnreachableException;
import javax.swing.SwingWorker;
import javax.swing.SwingUtilities;
import nu.mine.egoless.didattica.app.gui.componentiComuni.UtilitaGUI;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ContenitoreDati;
import nu.mine.egoless.didattica.app.gui.componentiComuni.IconListRenderer;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author  Lorenzo Daniele
 *
 * Frame principale della GUI contenente un tabbed pane
 */
public class GUIFrame extends javax.swing.JFrame {
        
    /** 
     * Creates new form GUIFrame 
     */
    public GUIFrame() {
        //inizializza le variabili locali
        religioniBean = new ReligioniBean();        
        nazioniBean = new NazioniBean();      
        classiBean = new ClassiBean();
        materieBean = new MaterieInsegnamentoBean();        
        tipiVotiBean = new TipiVotiBean();
        tipiAssenzeBean = new TipiAssenzeBean();
        tipiProveBean = new TipiProveBean();
        //Thread di caricamento dati
        new javax.swing.SwingWorker<Object, Object>(){
            public Object doInBackground() {
                try{                    
                    avviaCaricamentoDialog();
                    //caricamento dei dati iniziali
                    boolean ok = caricaDati();  
                    if( ok == false ) confermaDiUscitaConErrore("Server Irraggiungibile", "Il server è irraggiungibile!\nImpossibile caricare i dati iniziali.\nTerminare l'applicazione?");
                }catch(Exception e){
                    confermaDiUscitaConErrore("Errore", "Problemi di visualizzazione.\nUscire?");               
                 }                   
                 finally{
                     try{
                        SwingUtilities.invokeAndWait( new Runnable(){ 
                            public void run(){
                                terminaCaricamentoDialog();
                                //inizializza i pannelli da aggiungere al jtabp passandogli i dati appena caricati
                                try{ //DEBUG CODE
                                    //inizializza le componenti grafiche
                                    main.setLocation (200,100);
                                    inizializzaComponenti();
                                    dataCorrenteUpdater.start(); //avvia il thread di aggiornamento della data
                                }catch(Exception e){System.out.println("GUIFrame(2) "+e.getClass().toString()); e.printStackTrace(); } //DEBUG CODE                                         
                            }
                        });
                     }catch(Exception e){ System.out.println("Errore imprevedibile: "+e.getClass().toString()); e.printStackTrace(); System.exit(-1); }     
                     return null;
                 }                
            }
        }.execute();
    }

    /**
     * Aggiunge una tab al tabbe pane
     */
    public void addTab(String title, javax.swing.JPanel component){
        if(title!=null) jtabp.addTab(title, component);
        else jtabp.addTab("", component);
    }

    /**
     * Aggiunge una tab al tabbed pane
     */
    public void addTab(String title, javax.swing.Icon icon, javax.swing.JPanel component){
        if(title!=null) jtabp.addTab(title, icon, component);
        else jtabp.addTab("", icon, component);
    }
    
    /**
     * Rimuove un tab dal tabbed pane
     * Bisogna passargli il component passato come parametro in addTab
     */
    public void removeTab(javax.swing.JPanel component){
        int i = jtabp.indexOfTabComponent(component);
        if(i!=-1){
            jtabp.removeTabAt(i);
        }
    }
    
    /**
     * Inizializza i pannelli nel tabbed pane
     */
    private void inizializzaPannelli(){
        final ContenitoreDati c = new ContenitoreDati(religioniBean, nazioniBean, classiBean, materieBean, tipiVotiBean, tipiAssenzeBean, tipiProveBean);
        jpnlGestioneTabStudenti = new GestioneTabStudentiPanel( c );
        addTab( "Studenti", new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/gestioneStudenti24x22.gif")), jpnlGestioneTabStudenti );
        jpnlGestioneTabInsegnanti = new GestioneTabInsegnantiPanel( c );
        addTab( "Insegnanti", new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/gestioneInsegnanti22x22.gif")), jpnlGestioneTabInsegnanti );                                
        jpnlRegistroInsegnante = new RegistroInsegnantePanel( c ); //TO DO
        addTab( "Registro", new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/registro22x22.gif")), jpnlRegistroInsegnante ); //TO DO                                         
    }

    /**
     * Rende agibile o inagibile l'intero Frame
     */
    public void setEnabled(boolean b){
        jpnlGestioneTabStudenti.setEnabled(b);
        jpnlGestioneTabInsegnanti.setEnabled(b);
        //jpnlRegistroInsegnante.setEnabled(b);
        jbToolbarNuovaRicercaInsegnanti.setEnabled(b);
        jbToolbarNuovaRicercaStudenti.setEnabled(b);
        jbToolbarNuovoInsegnante.setEnabled(b);
        jbToolbarNuovoStudente.setEnabled(b);
        jlst.setEnabled(b);
        jmiAiuto.setEnabled(b);
        jmiFile.setEnabled(b);
        jmiVisualizza.setEnabled(b);
        jtabp.setEnabled(b);   
    }    
    
    /**
     * Setta il testo nella label dis tato del GUIFrame
     */
    public void setTesto(String s){
        jlbl.setText(s);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      
        String str = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";	// Windows
	//String str = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";		// Grey
	//String str = "javax.swing.plaf.metal.MetalLookAndFeel";   			// Java Metal
	try { javax.swing.UIManager.setLookAndFeel(str); }
	catch (Exception ex) { System.out.println("Failed loading Look And Feel:\n"+ex+'\n'); }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUIFrame jfrm = new GUIFrame();
                jfrm.setVisible(true);        
            }
        });
      
    }       
    
    /**
     * Si occupa di caricare i dati relativi alle religioni ed alle nazioni rpesenti nel database
     * false se è fallito il caricamento di qualche dato
     */
    private boolean caricaDati(){
         boolean ecc = true; //traccia il fallimento nel caricamento di qualche dato
         int step = 100/7; //passo di aumento della barra di progresso
         String missed = ""; //traccia i dati che non si è riusciti a caricare
         System.out.println("GuiFrame: Caricamento Dati Iniziali") ;
         try{
             cD.setStato("Inizio del caricamento della lista delle religioni");
             religioniBean.caricaReligioni();
         }catch(Exception e){missed += "religioni; "; cD.setStato("Fallito il caricamento della lista delle religioni"); ecc=false;}
         cD.setIndeterminato(false);
         cD.setProgresso(step);
         try{
            cD.setStato("Inizio del caricamento della lista delle nazioni");
            nazioniBean.caricaNazioni();
         }catch(Exception e){missed += "nazioni; "; cD.setStato("Fallito il caricamento della lista dellle nazioni"); ecc=false;}
         cD.setProgresso(2*step);
         try{
            cD.setStato("Inizio del caricamento della lista delle classi");
            classiBean.caricaClassi();
         }catch(Exception e){missed += "classi; "; cD.setStato("Fallito il caricamento della lista classi"); ecc=false;} 
         cD.setProgresso(3*step);
         try{
            cD.setStato("Inizio del caricamento della lista delle materie");
            materieBean.caricaMaterie();
            //System.out.println("Numero di Materie "+materieBean.ritornaNumeroDiMaterie()); //DEBUG CODE
         }catch(Exception e){missed += "materie; "; cD.setStato("Fallito il caricamento della lista delle materie"); ecc=false;}
         cD.setProgresso(4*step);
         try{
            cD.setStato("Inizio del caricamento della lista dei tipi di voto");
            tipiVotiBean.caricaTipiVoti();
         }catch(Exception e){missed += "tipi di voto; "; cD.setStato("Fallito il caricamento della lista dei tipi di voto"); ecc=false;}
         cD.setProgresso(5*step);
         try{
            cD.setStato("Inizio del caricamento della lista dei tipi di assenza");
            tipiAssenzeBean.caricaTipiAssenze();
         }catch(Exception e){missed += "tipi di assenze; "; cD.setStato("Fallito il caricamento della lista dei tipi di assenza"); ecc=false;}         
         cD.setProgresso(6*step);
         try{
            cD.setStato("Inizio del caricamento della lista dei tipi di prove");
            tipiProveBean.caricaTipiProve();
         }catch(Exception e){missed += "tipi di prove; "; cD.setStato("Fallito il caricamento della lista dei tipi di prove"); ecc=false;}              
         cD.setProgresso(100);
         if(ecc) cD.setStato("Caricamento completato con successo");            
         else { //se è fallito i caricamento di qualche dato
             System.out.println("GUIFrame: Errore di Caricamento: "+missed);
             cD.setStato("Fallito il caricamento delle liste: "+missed);
         }
         return ecc;
    }        
     
    /**
     * Avvia il dialog iniziale di caricamento
     */
    private void avviaCaricamentoDialog() throws InterruptedException, InvocationTargetException { 
        SwingUtilities.invokeAndWait( new Runnable(){
            public void run(){
                //seta e visualizza il dialog di caricamento
                cD.setModal(false); //poichè è richiamato in una invokeAndWait
                cD.setLocation (300,200);                            
                cD.setIndeterminato(true);
                cD.setVisible(true);                           
            }
        });        
    }
    
    /**
     * Chiude ed elimina il dialog iniziale di caricamento
     */
    private void terminaCaricamentoDialog(){
        cD.setVisible(false);
        cD = null; //non ci servirà più!
    }

    /**
     * Mostra un messaggio per confermare la chiusura a causa di une errore
     */
    private void confermaDiUscitaConErrore(final String titolo, final String testo) throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait( new Runnable(){
            public void run(){
                //UtilitaGUI.messaggioWSNonRaggiungibile(main);
                int risposta = JOptionPane.showConfirmDialog(main, testo, titolo, JOptionPane.YES_NO_OPTION);
                if(risposta == JOptionPane.YES_OPTION) System.exit(0);
                //javax.swing.JOptionPane.showMessageDialog(main, "L'applicazione verrà terminata");
            }
        });                 
    } 
    
    /**
     * Inizializza le componenti grafiche
     */    
    private void inizializzaComponenti(){
        initComponents();               
        //inizializza il JList
        jlst.setCellRenderer(new IconListRenderer());
        jlst.setListData(iconList);       
        //inizializza la data nella toolbar
        jlblDataCorrente.setText( UtilitaGUI.dateToString(UtilitaGUI.getDataCorrente()) ) ;
        //inizializza i pannelli
        inizializzaPannelli();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
   // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
   private void initComponents() {
      jtoolb = new javax.swing.JToolBar();
      jbToolbarNuovaRicercaStudenti = new javax.swing.JButton();
      jbToolbarNuovaRicercaInsegnanti = new javax.swing.JButton();
      jsepToolbar1 = new javax.swing.JSeparator();
      jbToolbarNuovoStudente = new javax.swing.JButton();
      jbToolbarNuovoInsegnante = new javax.swing.JButton();
      jsepToolbar2 = new javax.swing.JSeparator();
      jlblDataCorrente = new javax.swing.JLabel();
      jsepToolbar3 = new javax.swing.JSeparator();
      jtabp = new javax.swing.JTabbedPane();
      jScrollPane1 = new javax.swing.JScrollPane();
      jlst = new javax.swing.JList();
      jlbl = new javax.swing.JLabel();
      jmb = new javax.swing.JMenuBar();
      jmiFile = new javax.swing.JMenu();
      jmiEsci = new javax.swing.JMenuItem();
      jmiVisualizza = new javax.swing.JMenu();
      jmStudenti = new javax.swing.JMenu();
      jmiNuovaRicercaStudenti = new javax.swing.JMenuItem();
      jmiNuovaGestioneStudente = new javax.swing.JMenuItem();
      Insegnanti = new javax.swing.JMenu();
      jmiNuovaRicercaInsegnanti = new javax.swing.JMenuItem();
      jmiNuovaGestioneInsegnante = new javax.swing.JMenuItem();
      jcbPrimoPiano = new javax.swing.JCheckBoxMenuItem();
      jmiAiuto = new javax.swing.JMenu();
      jmiInfo = new javax.swing.JMenuItem();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setTitle("APPDidattica");
      jtoolb.setFloatable(false);
      jbToolbarNuovaRicercaStudenti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/nuovaRicercaStudenti24x24.gif")));
      jbToolbarNuovaRicercaStudenti.setBorderPainted(false);
      jbToolbarNuovaRicercaStudenti.setContentAreaFilled(false);
      jbToolbarNuovaRicercaStudenti.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbToolbarNuovaRicercaStudentiActionPerformed(evt);
         }
      });

      jtoolb.add(jbToolbarNuovaRicercaStudenti);

      jbToolbarNuovaRicercaInsegnanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/nuovaRicercaInsegnanti24x24.gif")));
      jbToolbarNuovaRicercaInsegnanti.setBorderPainted(false);
      jbToolbarNuovaRicercaInsegnanti.setContentAreaFilled(false);
      jbToolbarNuovaRicercaInsegnanti.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbToolbarNuovaRicercaInsegnantiActionPerformed(evt);
         }
      });

      jtoolb.add(jbToolbarNuovaRicercaInsegnanti);

      jsepToolbar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
      jsepToolbar1.setMaximumSize(new java.awt.Dimension(5, 32767));
      jsepToolbar1.setPreferredSize(new java.awt.Dimension(1, 10));
      jtoolb.add(jsepToolbar1);

      jbToolbarNuovoStudente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/nuovaGestioneStudente24x24.gif")));
      jbToolbarNuovoStudente.setBorderPainted(false);
      jbToolbarNuovoStudente.setContentAreaFilled(false);
      jbToolbarNuovoStudente.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbToolbarNuovoStudenteActionPerformed(evt);
         }
      });

      jtoolb.add(jbToolbarNuovoStudente);

      jbToolbarNuovoInsegnante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/nuovaGestioneInsegnante24x24.gif")));
      jbToolbarNuovoInsegnante.setBorderPainted(false);
      jbToolbarNuovoInsegnante.setContentAreaFilled(false);
      jbToolbarNuovoInsegnante.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jbToolbarNuovoInsegnanteActionPerformed(evt);
         }
      });

      jtoolb.add(jbToolbarNuovoInsegnante);

      jsepToolbar2.setOrientation(javax.swing.SwingConstants.VERTICAL);
      jsepToolbar2.setMaximumSize(new java.awt.Dimension(5, 32767));
      jtoolb.add(jsepToolbar2);

      jlblDataCorrente.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
      jtoolb.add(jlblDataCorrente);

      jsepToolbar3.setOrientation(javax.swing.SwingConstants.VERTICAL);
      jsepToolbar3.setMaximumSize(new java.awt.Dimension(5, 32767));
      jtoolb.add(jsepToolbar3);

      jtabp.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

      jlst.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            jlstMouseClicked(evt);
         }
      });

      jScrollPane1.setViewportView(jlst);

      jlbl.setText(" SGIQA");

      jmiFile.setText("File");
      jmiEsci.setText("Esci");
      jmiEsci.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmiEsciActionPerformed(evt);
         }
      });

      jmiFile.add(jmiEsci);

      jmb.add(jmiFile);

      jmiVisualizza.setText("Visualizza");
      jmStudenti.setText("Studenti");
      jmiNuovaRicercaStudenti.setText("Nuova Ricerca");
      jmiNuovaRicercaStudenti.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmiNuovaRicercaStudentiActionPerformed(evt);
         }
      });

      jmStudenti.add(jmiNuovaRicercaStudenti);

      jmiNuovaGestioneStudente.setText("Nuova Gestione");
      jmiNuovaGestioneStudente.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmiNuovaGestioneStudenteActionPerformed(evt);
         }
      });

      jmStudenti.add(jmiNuovaGestioneStudente);

      jmiVisualizza.add(jmStudenti);

      Insegnanti.setText("Insegnanti");
      jmiNuovaRicercaInsegnanti.setText("Nuova Ricerca");
      jmiNuovaRicercaInsegnanti.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmiNuovaRicercaInsegnantiActionPerformed(evt);
         }
      });

      Insegnanti.add(jmiNuovaRicercaInsegnanti);

      jmiNuovaGestioneInsegnante.setText("Nuova Gestione");
      jmiNuovaGestioneInsegnante.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmiNuovaGestioneInsegnanteActionPerformed(evt);
         }
      });

      Insegnanti.add(jmiNuovaGestioneInsegnante);

      jmiVisualizza.add(Insegnanti);

      jcbPrimoPiano.setText("Primo Piano");
      jcbPrimoPiano.addItemListener(new java.awt.event.ItemListener() {
         public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jcbPrimoPianoItemStateChanged(evt);
         }
      });

      jmiVisualizza.add(jcbPrimoPiano);

      jmb.add(jmiVisualizza);

      jmiAiuto.setText("?");
      jmiInfo.setText("Info");
      jmiInfo.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jmiInfoActionPerformed(evt);
         }
      });

      jmiAiuto.add(jmiInfo);

      jmb.add(jmiAiuto);

      setJMenuBar(jmb);

      org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(jtoolb, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
         .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jtabp, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
            .addContainerGap())
         .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .add(jlbl, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(layout.createSequentialGroup()
            .add(jtoolb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(jtabp, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
               .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
            .add(7, 7, 7)
            .add(jlbl))
      );
      pack();
   }// </editor-fold>//GEN-END:initComponents

    private void jcbPrimoPianoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbPrimoPianoItemStateChanged
        main.setAlwaysOnTop(jcbPrimoPiano.isSelected());
    }//GEN-LAST:event_jcbPrimoPianoItemStateChanged

    private void jlstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlstMouseClicked
        if (evt.getClickCount() == 1) {
            int index = jlst.locationToIndex(evt.getPoint());
            System.out.println("GUIFrame - jlst: Clicked on Item " + index);
            switch(index){
                case 0: int i = jtabp.indexOfComponent(jpnlGestioneTabStudenti);
                        if(i>=0) jtabp.setSelectedIndex(i);
                        break;               
                case 1: int j = jtabp.indexOfComponent(jpnlGestioneTabInsegnanti);
                        if(j>=0) jtabp.setSelectedIndex(j);
                        break; 
                case 2: int k = jtabp.indexOfComponent(jpnlRegistroInsegnante); //TO DO
                        if(k>=0) jtabp.setSelectedIndex(k);  //TO DO
                        break;                
            }
        }       
    }//GEN-LAST:event_jlstMouseClicked

    /**
     * Metodi richiamati dai listener agganciati alle varie componenti del pannello
     */                
    private void jbToolbarNuovoInsegnanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbToolbarNuovoInsegnanteActionPerformed
        jtabp.setSelectedComponent(jpnlGestioneTabInsegnanti);
        jpnlGestioneTabInsegnanti.apriNuovoPannelloDx();
    }//GEN-LAST:event_jbToolbarNuovoInsegnanteActionPerformed

    private void jbToolbarNuovoStudenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbToolbarNuovoStudenteActionPerformed
        jtabp.setSelectedComponent(jpnlGestioneTabStudenti);        
        jpnlGestioneTabStudenti.apriNuovoPannelloDx();
    }//GEN-LAST:event_jbToolbarNuovoStudenteActionPerformed

    private void jbToolbarNuovaRicercaInsegnantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbToolbarNuovaRicercaInsegnantiActionPerformed
        jtabp.setSelectedComponent(jpnlGestioneTabInsegnanti);        
        jpnlGestioneTabInsegnanti.apriNuovoPannelloSx();
    }//GEN-LAST:event_jbToolbarNuovaRicercaInsegnantiActionPerformed

    private void jbToolbarNuovaRicercaStudentiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbToolbarNuovaRicercaStudentiActionPerformed
        jtabp.setSelectedComponent(jpnlGestioneTabStudenti);    
        jpnlGestioneTabStudenti.apriNuovoPannelloSx();
    }//GEN-LAST:event_jbToolbarNuovaRicercaStudentiActionPerformed

    private void jmiNuovaGestioneInsegnanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNuovaGestioneInsegnanteActionPerformed
        jtabp.setSelectedComponent(jpnlGestioneTabInsegnanti);       
        jpnlGestioneTabInsegnanti.apriNuovoPannelloDx();
    }//GEN-LAST:event_jmiNuovaGestioneInsegnanteActionPerformed

    private void jmiNuovaRicercaInsegnantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNuovaRicercaInsegnantiActionPerformed
        jtabp.setSelectedComponent(jpnlGestioneTabInsegnanti);        
        jpnlGestioneTabInsegnanti.apriNuovoPannelloSx();
    }//GEN-LAST:event_jmiNuovaRicercaInsegnantiActionPerformed

    private void jmiNuovaGestioneStudenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNuovaGestioneStudenteActionPerformed
        jtabp.setSelectedComponent(jpnlGestioneTabStudenti);        
        jpnlGestioneTabStudenti.apriNuovoPannelloDx();
    }//GEN-LAST:event_jmiNuovaGestioneStudenteActionPerformed

    private void jmiNuovaRicercaStudentiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNuovaRicercaStudentiActionPerformed
        jtabp.setSelectedComponent(jpnlGestioneTabStudenti);        
        jpnlGestioneTabStudenti.apriNuovoPannelloSx();
    }//GEN-LAST:event_jmiNuovaRicercaStudentiActionPerformed

    private void jmiInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiInfoActionPerformed
        CreditDialog cD = new CreditDialog(this, false);
        cD.setLocation(200,200);
        cD.setVisible(true);               
    }//GEN-LAST:event_jmiInfoActionPerformed

    private void jmiEsciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEsciActionPerformed
        int risposta = JOptionPane.showConfirmDialog(main, "Sei sicuro di voler uscire?\nTutti i dati non salvati verranno persi!",
                                        "Chiusura Applicazione", JOptionPane.YES_NO_OPTION);
        if(risposta == JOptionPane.YES_OPTION){
            dataCorrenteUpdater.interrupt(); //blocca il thread di aggiornamento della data
            System.exit(0);
        }        
    }//GEN-LAST:event_jmiEsciActionPerformed

    
    /**variabili locali*/
    //Thread di aggiornamento della data nella toolbar
    private Thread dataCorrenteUpdater = new Thread(){
        public void run() {  
            try{
                int millisec = 60;
                while(true){
                   sleep(millisec); //aggiornamento una volta ogni minuto
                   SwingUtilities.invokeLater( new Runnable(){
                       public void run(){
                          jlblDataCorrente.setText( UtilitaGUI.dateToString(UtilitaGUI.getDataCorrente()) ) ;                            
                        }
                    }); 
                }
            }catch(InterruptedException e){}
        }
    };        
    private Object[] iconList ={
                                 new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/gestioneStudentiList24x22.gif")),
                                 new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/gestioneInsegnantiList22x22.gif")),
                                 new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/registroList22x22.gif")),
                                };
    private NazioniBean nazioniBean = null;
    private ReligioniBean religioniBean = null;   
    private ClassiBean classiBean = null;
    private MaterieInsegnamentoBean materieBean = null; 
    private TipiVotiBean tipiVotiBean = null;
    private TipiAssenzeBean tipiAssenzeBean = null;
    private TipiProveBean tipiProveBean = null;
    /**variabili grafiche*/
    private final GUIFrame main = this;    
    //attenzione al fatto che non deve essere modale poichè viene richiamato all'interno di una invokeAndWait
    private CaricamentoDialog cD = new CaricamentoDialog(main, false);
    //Tab iniziali
    private GestioneTabStudentiPanel jpnlGestioneTabStudenti = null;
    private GestioneTabInsegnantiPanel jpnlGestioneTabInsegnanti = null;
    private RegistroInsegnantePanel jpnlRegistroInsegnante = null; //TO DO
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JMenu Insegnanti;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JButton jbToolbarNuovaRicercaInsegnanti;
   private javax.swing.JButton jbToolbarNuovaRicercaStudenti;
   private javax.swing.JButton jbToolbarNuovoInsegnante;
   private javax.swing.JButton jbToolbarNuovoStudente;
   private javax.swing.JCheckBoxMenuItem jcbPrimoPiano;
   private javax.swing.JLabel jlbl;
   private javax.swing.JLabel jlblDataCorrente;
   private javax.swing.JList jlst;
   private javax.swing.JMenu jmStudenti;
   private javax.swing.JMenuBar jmb;
   private javax.swing.JMenu jmiAiuto;
   private javax.swing.JMenuItem jmiEsci;
   private javax.swing.JMenu jmiFile;
   private javax.swing.JMenuItem jmiInfo;
   private javax.swing.JMenuItem jmiNuovaGestioneInsegnante;
   private javax.swing.JMenuItem jmiNuovaGestioneStudente;
   private javax.swing.JMenuItem jmiNuovaRicercaInsegnanti;
   private javax.swing.JMenuItem jmiNuovaRicercaStudenti;
   private javax.swing.JMenu jmiVisualizza;
   private javax.swing.JSeparator jsepToolbar1;
   private javax.swing.JSeparator jsepToolbar2;
   private javax.swing.JSeparator jsepToolbar3;
   private javax.swing.JTabbedPane jtabp;
   private javax.swing.JToolBar jtoolb;
   // End of variables declaration//GEN-END:variables
    
}
