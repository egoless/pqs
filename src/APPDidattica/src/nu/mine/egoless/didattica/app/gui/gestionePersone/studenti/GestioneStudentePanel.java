/*
 * GestioneStudentePanel.java
 *
 * Created on 9 marzo 2007, 2.38
 */

package nu.mine.egoless.didattica.app.gui.gestionePersone.studenti;

import javax.swing.SwingWorker;
import javax.swing.JOptionPane;
import nu.mine.egoless.didattica.app.bean.StudenteBean;
import nu.mine.egoless.didattica.app.bean.ContattoBean;
import nu.mine.egoless.didattica.app.bean.ReligioniBean;
import nu.mine.egoless.didattica.app.bean.NazioniBean;
import nu.mine.egoless.didattica.app.bean.ClassiBean;
import nu.mine.egoless.didattica.app.bean.MaterieInsegnamentoBean;
import nu.mine.egoless.didattica.app.bean.TipiVotiBean;
import nu.mine.egoless.didattica.app.bean.TipiAssenzeBean;
import nu.mine.egoless.didattica.app.gui.gestionePersone.*;
import nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception;
import java.net.PortUnreachableException;
import javax.swing.JOptionPane;
import nu.mine.egoless.didattica.app.gui.componentiComuni.UtilitaGUI;
import nu.mine.egoless.didattica.app.bean.Costanti;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ContenitoreDati;
import nu.mine.egoless.didattica.app.adapter.*;

/**
 *
 * @author  Lorenzo Daniele
 *
 * Pannello che contiene tutte le componenti per gestire globalmente tutti i dati
 * relativi ad uno studente
 */
public class GestioneStudentePanel extends GestionePersonaPanel {
    
    /**
     * Creates new form GestioneStudentePanel
     * @param jpnlGestioneTabStudenti il GestioneTabStudentiPanel in cui tale GestioneStudentiPanel è contenuto
     * @param contenitore il contenitore dei dati caricati inizialmente dall'applicazione     
     */
    public GestioneStudentePanel(GestioneTabStudentiPanel jpnlGestioneStudenti, ContenitoreDati contenitore) {
        super(jpnlGestioneStudenti);
        if(contenitore == null) throw new IllegalArgumentException();
        this.contenitore = contenitore;
        inizializza(Costanti.ID_NUOVA_PERSONA);
    }

    /**
     * Creates new form GestioneStudentePanel
     * @param jpnlGestioneTabStudenti il GestioneTabStudentiPanel in cui tale GestioneStudentiPanel è contenuto
     * @param contenitore il contenitore dei dati caricati inizialmente dall'applicazione     
     * @param id l'id contenuto nel database relativo alo studente i cui dati  devono essere gestiti
     */    
    public GestioneStudentePanel(GestioneTabStudentiPanel jpnlGestioneStudenti, ContenitoreDati contenitore, int idStudente){
        super(jpnlGestioneStudenti);
        if(contenitore == null) throw new IllegalArgumentException();
        this.contenitore = contenitore;
        inizializza(idStudente);
    }

     /*
     * @retrun il GestioneTabStudentiPanel passato come argomento al costruttore
     **/    
    public GestioneTabStudentiPanel getAncestor(){ return (GestioneTabStudentiPanel) super.getAncestor(); }
    
    /**
     * @return l'id passato come argomento all costrutore
     */    
    public int getId(){ return studenteBean.getId(); }    

    /**
     * rende agibili o inagibili tutte le componenti del pannello
     **/    
    public void setEnabled(boolean b){
        jpnlGestioneDatiAnagrafici.setEnabled(b);
        jpnlGestioneDatiStudente.setEnabled(b);
        jbSalva.setEnabled(b);
        jbElimina.setEnabled(b);
    }
 
    /**
     * main di prova
     */        
    public static void main(String[] args) {
        /*javax.swing.JDialog temp=new javax.swing.JDialog();
        temp.setLayout(new java.awt.BorderLayout());
        ContenitoreDati c = new ContenitoreDati(new ReligioniBean(), new NazioniBean(), new ClassiBean(), new MaterieInsegnamentoBean(), new TipiVotiBean(), new TipiAssenzeBean());
        temp.add(new GestioneStudentePanel(new GestioneTabStudentiPanel(c),c));
        temp.setSize(500,500);
        temp.setVisible(true);*/
    }    
    
    /**
     * Si occupa di caricare i dati di uno studente dipendendentemente dallo stato della variabile
     * studentiBean (se è uguale a new StudentiBean non effettua alcun caricamento)
     * In linea di massima tale metodo non dovrebbe essere modificato ma si dovrebbero modificare i metodi che chiama
     */
    private void inizializza(int idStudente){
        studenteBean = new StudenteBean(idStudente);
        contattoBean = new ContattoBean();          
        new SwingWorker<Object, Object>(){
            public Object doInBackground(){
                try{
                    //System.out.println("GestioneStudentiPanel: Caricamento dati "); //DEBUG CODE
                    getAncestor().setTesto("Caricamento in corso...");
                    //getAncestor().setDownloadIndeterminato(true); 
                    //CODICE RILEVANTE
                    if(studenteBean.getId()!=Costanti.ID_NUOVA_PERSONA)
                        caricaDatiStudente();
                    //se i dati sono stati caricati correttamente allora avvia carica la grafica
                    javax.swing.SwingUtilities.invokeAndWait( new Runnable(){
                        public void run(){
                            inizializzaComponenti();                         
                        }
                    });
                    //CODICE RILEVANTE
                    //System.out.println("GestioneStudentiPanel: Caricamento riuscito con successo"); //DEBUG CODE
                    getAncestor().setTesto("GestioneStudentePanel: Caricamento terminato...");
                }catch(InterruptedException e){
                    System.out.println("GestioneStudentiPanel: Ecc0 "); //DEBUG CODE
                    JOptionPane.showMessageDialog(main,"Problemi di visualizzazione");
                    getAncestor().chiudiPannelloDx(main);
                    getAncestor().setTesto("Caricamento terminato con insuccesso...");                 
                }
                 catch(java.lang.reflect.InvocationTargetException e){
                    System.out.println("GestioneStudentiPanel: Ecc1 "); //DEBUG CODE
                    JOptionPane.showMessageDialog(main,"Problemi di visualizzazione"); e.printStackTrace ();
                    getAncestor().chiudiPannelloDx(main);
                    getAncestor().setTesto("Caricamento terminato con insuccesso...");                     
                 }
                 catch(Exception e){
                    System.out.println("GestioneStudentiPanel: Ecc2 "); //DEBUG CODE
                    String message = "Server non raggiungibile oppure Id invalido!";
                    if( e instanceof WSDidatticaException_Exception ) ((WSDidatticaException_Exception)e).getMessage();
                    JOptionPane.showMessageDialog(main,message+" Chiusura della finestra.");
                    getAncestor().chiudiPannelloDx(main);
                    getAncestor().setTesto("Caricamento terminato con insuccesso...");
                 }                   
                 finally{
                    System.out.println("Fine caricamento"); //DEBUG CODE
                    //getAncestor().setDownloadIndeterminato(false);                     
                    return null;
                 }
            }
        }.execute(); //SwingWorker        
    }
    
    /**
     * Si occupa di caricare i dati dell'eventuale studente (già presente nel database) da gestire (compreso il contatto)
     * Metodo d'usilio epr modularizzare
     */
    private void caricaDatiStudente() throws nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception, nu.mine.egoless.didattica.ws.contattoclient.WSDidatticaException_Exception, PortUnreachableException {
        synchronized(studenteBean){
            if( studenteBean.getId() != Costanti.ID_NUOVA_PERSONA){   
                //System.out.println("GestioneStudentePanel  IDIDID: "+studenteBean.getId ()); //DEBUG CODE
                //System.out.println("Studente già caricato");
                System.out.println ("GestioneStudentePanel ID1Caricamento: "+studenteBean.getId ());
                //carica lo studente...
                studenteBean.caricaDaWS(studenteBean.getId());
                System.out.println ("GestioneStudentePanel ID2Caricamento: "+studenteBean.getId ());               
            }
            else{
                System.out.println("Nuovo Utente");
                return;
            }
        }
        //se l'utente non è nuovo allora carica anche il contatto
        synchronized(contattoBean){
            //...ed il relativo contatto con l'id appropriato appena caricato'
            int id = studenteBean.getIdIndirizzoResidenza();
            if( id!= Costanti.ID_NUOVO_CONTATTO ) //tale controllo risparmia il dover cercare un contatto che non esiste
                contattoBean.caricaDaWS(id);         
        }
                   System.out.println ("->->3 "/*+idClasse*/+" "+studenteBean.getIdReligione()+" "+studenteBean.getIdNazionalita());
    }

   /**
     * salva sul database i dati contenuti nella variabile studenteBean
     */    
    private void salvaDatiStudente() throws nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception, nu.mine.egoless.didattica.ws.contattoclient.WSDidatticaException_Exception, PortUnreachableException {
        System.out.println("GestioneStudentePanel: ID prima del salvataggio: "+studenteBean.getId());
        synchronized(contattoBean){
            //contattoBean.setIdPersona(studenteBean.getId ()); //per mantenere il riferimento alla persona giusta
            contattoBean.salvaSuWS(); //salvataggio relativo contatto 
        }        
        synchronized(studenteBean){    
            studenteBean.setIdIndirizzoResidenza (contattoBean.getId());
            this.studenteBean.salvaSuWS(); //salvataggio utente
            System.out.println("GestioneStudentePanel: ID dopo il salvataggio: "+studenteBean.getId());                
        }
        System.out.println("GestioneStudentePanel: SALVATO"); 
    }

    /**
     * cancella dal database tutti i dati relativi all'insegnante con id uguale a quello del parametro passato al costruttore
     */    
    private void cancellaDatiStudente() throws nu.mine.egoless.didattica.ws.personaclient.WSDidatticaException_Exception, PortUnreachableException {
        synchronized(studenteBean){
            studenteBean.cancellaStudente();
        }
    }

    /**
     * Codice utile a fini di debug
     */
    private void salvaDebugCode(){
        System.out.println(
            "------------------------------\n"+
            " "+"Dati dello Studente:\n"+
            " "+studenteBean.getNome()+"\n"+
            " "+studenteBean.getCognome()+"\n"+
            " "+studenteBean.getDataNascita()+"\n"+
            " "+studenteBean.getCodiceFiscale()+"\n"+
            " "+studenteBean.isPortatoreHandicap()+"\n"+
            //" "+new ReligioneAdapter( UtilitaGUI.getReligione( contenitore.getReligioni(), studenteBean.getIdReligione() ) )+"\n"+
            //" "+new NazioneAdapter( UtilitaGUI.getNazione( contenitore.getNazioni(), studenteBean.getIdNazionalita() ) )+"\n"+
            " "+contattoBean.getVia()+"\n"+
            " "+contattoBean.getCivico()+"\n"+
            " "+contattoBean.getCAP()+"\n"+
            " "+contattoBean.getProvincia()+"\n"+
            " "+contattoBean.getTelefonoPrincipale()+"\n"+ 
            " "+contattoBean.getTelefonoSecondario()+"\n"+
            " "+contattoBean.getFax()+"\n"+  
            " "+studenteBean.getMatricola()+"\n"+
            " "+studenteBean.getDataIscrizione()+"\n"+
            //" "+new ClasseAdapter( UtilitaGUI.getClasse( contenitore.getClassi(), studenteBean.getIdClasse() ) )+"\n"+
            "------------------------------"                
        );
    }
    
    /**
     * inizializza le componenti grafiche del pannello
     * Notare che i form vengono caricati con i dati correntemente contenuti in studenteBean
     */
    private void inizializzaComponenti(){
        //try{ //DEBUG CODE
        jpnlGestioneDatiAnagrafici = new GestioneDatiAnagraficiPanel(studenteBean, contattoBean, contenitore);
        //}catch(Exception e){System.out.println("GestioneStudentiPanel: ERRORE1"+e.getClass().toString()+"\n"); e.printStackTrace();} //DEBUG CODE 
        //try{ //DEBUG CODE
        jpnlGestioneDatiStudente = new GestioneDatiStudentePanel(studenteBean, contenitore); 
        //}catch(Exception e){System.out.println("GestioneStudentiPanel: ERRORE2"+e.getClass().toString()+"\n"); e.printStackTrace();} //DEBUG CODE        
        main.initComponents();                    
        jpnlAux.add(jpnlGestioneDatiAnagrafici);
        jpnlAux.add(jpnlGestioneDatiStudente);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jpnlBottoni = new javax.swing.JPanel();
        jbChiudi = new javax.swing.JButton();
        jbElimina = new javax.swing.JButton();
        jbSalva = new javax.swing.JButton();
        jbAnteprima = new javax.swing.JButton();
        jprg = new javax.swing.JProgressBar();
        jpnlAux = new javax.swing.JPanel();

        setBackground(new java.awt.Color(204, 204, 255));
        jpnlBottoni.setBackground(new java.awt.Color(204, 204, 255));
        jbChiudi.setMnemonic('C');
        jbChiudi.setText("Chiudi");
        jbChiudi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbChiudiActionPerformed(evt);
            }
        });

        jbElimina.setMnemonic('E');
        jbElimina.setText("Elimina");
        jbElimina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEliminaActionPerformed(evt);
            }
        });

        jbSalva.setMnemonic('A');
        jbSalva.setText("Salva");
        jbSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalvaActionPerformed(evt);
            }
        });

        jbAnteprima.setText("Anteprima");
        jbAnteprima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnteprimaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnlBottoniLayout = new javax.swing.GroupLayout(jpnlBottoni);
        jpnlBottoni.setLayout(jpnlBottoniLayout);
        jpnlBottoniLayout.setHorizontalGroup(
            jpnlBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlBottoniLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jprg, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlBottoniLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jbAnteprima, javax.swing.GroupLayout.PREFERRED_SIZE, 73, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSalva, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbElimina)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbChiudi)))
                .addContainerGap())
        );

        jpnlBottoniLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jbChiudi, jbElimina, jbSalva});

        jpnlBottoniLayout.setVerticalGroup(
            jpnlBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlBottoniLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnlBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbChiudi)
                    .addComponent(jbElimina)
                    .addComponent(jbSalva)
                    .addComponent(jbAnteprima))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jprg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpnlAux.setLayout(new javax.swing.BoxLayout(jpnlAux, javax.swing.BoxLayout.Y_AXIS));

        jpnlAux.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlBottoni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnlAux, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jpnlAux, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlBottoni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * metodi richiamati dai listener agganciati alle varie componenti del pannello
     */            
    private void jbAnteprimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnteprimaActionPerformed
        AnteprimaDialog a= (new AnteprimaDialog(null, false, studenteBean, contattoBean, contenitore));
        a.setLocation(400,50);
        a.setVisible(true);
    }//GEN-LAST:event_jbAnteprimaActionPerformed

    /* Segue la lista dei metodi relativi ai listener associati ad ogni pulsante.
     * Sono il cuore del COntroller nel pattern MVC utilizzato per la GUI*/ 
    private void jbSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvaActionPerformed
        salvaDebugCode();  
        //Conferma in caso di sovrascrizione              
        if(studenteBean.getId() != Costanti.ID_NUOVA_PERSONA){
            int risposta = JOptionPane.showConfirmDialog(this, "Sei sicuro di voler salvare i dati di tale studente?\nI dati precedenti verranno sovrascritti definitivamente",
                            new String("estioneStudentePanel: Salvataggios Studente"), JOptionPane.YES_NO_OPTION);
            if(risposta == JOptionPane.NO_OPTION) return;
        }
        //salvataggio
        new SwingWorker<Object, Object>(){
            public Object doInBackground(){
                try{
                    getAncestor().setTesto("Salvataggio in corso...");
                    jprg.setIndeterminate(true);
                    getAncestor().setUploadIndeterminato(true);                         
                    //CODICE RILEVANTE
                    salvaDatiStudente();    
                    //CODICE RILEVANTE
                    getAncestor().setTesto("Salvataggio terminato...");
                    JOptionPane.showMessageDialog( main, "Salvataggio di "+UtilitaGUI.getStringaIdentificativa(studenteBean)+" avvenuto con successo", "Esito Salvataggio", JOptionPane.OK_OPTION | JOptionPane.INFORMATION_MESSAGE ); 
                }catch(Exception e){
                    System.out.println ("GestioneStudentePanel: Salvataggio non riuscito: "+e.getClass ().toString ());
                    JOptionPane.showMessageDialog(main, "Server non raggiungibile!\nSalvataggio di "+UtilitaGUI.getStringaIdentificativa(studenteBean)+" non riuscito.");
                    getAncestor().setTesto("Salvataggio terminato con insuccesso...");
                }
                 finally{
                     jprg.setIndeterminate(false);
                     getAncestor().setUploadIndeterminato(false);            
                     return null;
                }
            } //doInBackround
        }.execute();                  
    }//GEN-LAST:event_jbSalvaActionPerformed

    private void jbEliminaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEliminaActionPerformed
        if( studenteBean.getId() == Costanti.ID_NUOVA_PERSONA ){
            int risposta = JOptionPane.showConfirmDialog(this, "Lo studente è nuovo! Non vi sono dati da eliminare!",
                            new String("GestioneStudentePanel: Eliminazione Studente Non Pertinente"), JOptionPane.YES_NO_OPTION);   
            return;                
        }
        int risposta = JOptionPane.showConfirmDialog(this, "Sei sicuro di voler eliminare i dati di tale studente?",
                        new String("GestioneStudentePanel: Eliminazione Studente"), JOptionPane.YES_NO_OPTION);
        if(risposta == JOptionPane.NO_OPTION) return;
        (new SwingWorker<Object, Object>(){
            public Object doInBackground(){
                try{
                    getAncestor().setTesto("Eliminazione in corso...");
                    jprg.setIndeterminate(true);
                    getAncestor().setUploadIndeterminato(true);      
                    //CODICE RILEVANTE
                    cancellaDatiStudente();
                    //CODICE RILEVANTE
                    //in teoria non dovrebbe servire eliminare il contatto!!!
                    getAncestor().setTesto("Eliminazione terminata...");                
                    getAncestor().chiudiPannelloDx(main);
                    getAncestor().cancellaDaiRisultati(studenteBean.getId());
                    JOptionPane.showMessageDialog( main, "Eliminazione di "+UtilitaGUI.getStringaIdentificativa( studenteBean)+" avvenuta con successo", "Esito Eliminazione", JOptionPane.OK_OPTION | JOptionPane.INFORMATION_MESSAGE );                    
                }catch(Exception e){
                     System.out.println ("GestioneStudentePanel: Eliminazione non riuscita: "+e.getClass ().toString ());
                     JOptionPane.showMessageDialog(main, "Server non raggiungibile!\nEliminazion di "+UtilitaGUI.getStringaIdentificativa(studenteBean)+" non riuscita.");
                     getAncestor().setTesto("Eliminazione terminata con insuccesso...");
                 }
                 finally{
                     jprg.setIndeterminate(false);
                     getAncestor().setUploadIndeterminato(false);
                     return null;                     
                 }      
            }
        }).execute();                       
    }//GEN-LAST:event_jbEliminaActionPerformed

    private void jbChiudiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbChiudiActionPerformed
        getAncestor().chiudiPannelloDx(this);
    }//GEN-LAST:event_jbChiudiActionPerformed
    
    /** variabili locali */
    final private GestioneStudentePanel main = this;
    private StudenteBean studenteBean = null;
    private ContattoBean contattoBean = null;
    private ContenitoreDati contenitore = null;
    /** variabili grafiche*/
    private GestioneDatiAnagraficiPanel jpnlGestioneDatiAnagrafici = null;
    private GestioneDatiStudentePanel jpnlGestioneDatiStudente = null; 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAnteprima;
    private javax.swing.JButton jbChiudi;
    private javax.swing.JButton jbElimina;
    private javax.swing.JButton jbSalva;
    private javax.swing.JPanel jpnlAux;
    private javax.swing.JPanel jpnlBottoni;
    private javax.swing.JProgressBar jprg;
    // End of variables declaration//GEN-END:variables
    
}
