/*
 * GestioneDatiInsegnantePanel.java
 *
 * Created on 11 marzo 2007, 3.52
 */

package nu.mine.egoless.didattica.app.gui.gestionePersone.insegnanti;

import nu.mine.egoless.didattica.app.bean.InsegnanteBean;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.Date;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ContenitoreDati;
import nu.mine.egoless.didattica.app.gui.componentiComuni.UtilitaGUI;
import java.util.Date;
import nu.mine.egoless.didattica.app.bean.StudenteBean;
import nu.mine.egoless.didattica.app.bean.NazioniBean;
import nu.mine.egoless.didattica.app.bean.ReligioniBean;
import nu.mine.egoless.didattica.app.bean.ClassiBean;
import nu.mine.egoless.didattica.app.bean.MaterieInsegnamentoBean;
import nu.mine.egoless.didattica.app.bean.TipiVotiBean;
import nu.mine.egoless.didattica.app.bean.TipiAssenzeBean;
import nu.mine.egoless.didattica.app.bean.TipiProveBean;
import nu.mine.egoless.didattica.ws.materiaclient.MateriaInsegnamento;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ContenitoreDati;
import java.util.Vector;
import nu.mine.egoless.didattica.app.adapter.*;

/**
 *
 * @author  Lorenzo Daniele
 *
 * Pannello per gestire i dati specifici di un insegnante (data di assunzione e
 * materie insegnate
 */
public class GestioneDatiInsegnantePanel extends javax.swing.JPanel {
    
    /** Creates new form GestioneDatiInsegnantePanel 
     * @param insegnante l'insegnante i cui dati devono essere gestiti
     * @param contenitore il contenitore dei dati caricati inizialmente dall'applicazione
     */
    public GestioneDatiInsegnantePanel(InsegnanteBean insegnante, ContenitoreDati cont) {
        if(insegnante == null || cont == null) throw new IllegalArgumentException();
        inizializzato = false;
        this.insegnanteBean = insegnante;
        this.contenitore = cont;
        inizializzaComponenti();
        inizializzaForm();
        aggiungiPropertyListener();
        inizializzato = true;
    }

    /**
     * rende agibili o inagibili tutte le componenti del pannello
     */
    public void setEnabled(boolean b){
        jbAggiungi.setEnabled(b);
        jbRimuovi.setEnabled(b);
        jpnlDataAssunzione.setEnabled(b);
    }    

    /**
     * main di prova
     */
    public static void main(String[] args) {
        //System.out.println("AVVIO");
        javax.swing.JDialog temp=new javax.swing.JDialog();
        temp.setLayout(new java.awt.BorderLayout());
        ContenitoreDati c = new ContenitoreDati(new ReligioniBean(), new NazioniBean(), new ClassiBean(), new MaterieInsegnamentoBean(), new TipiVotiBean(), new TipiAssenzeBean(), new TipiProveBean());
        temp.add( new GestioneDatiInsegnantePanel(new InsegnanteBean(), c ));
        temp.setSize(500,500);
        temp.setVisible(true);
        //System.out.println("TERMINEAVVIO");
    }   
    
    /**
     * Aggiunge i listener per i cambiamenti di stato sull'insegnanteBean
     */
    private void aggiungiPropertyListener(){    
        insegnanteBean.addPropertyChangeListener( insegnanteBean.DATA_ASSUNZIONE, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    System.out.println("PCEData "); //DEBUGCODE
                    if(event.getNewValue()!=null) jpnlDataAssunzione.setData( (Date) event.getNewValue() );
                    else jpnlDataAssunzione.setData( new Date() );                                    
                }
            }              
        );  
        insegnanteBean.addPropertyChangeListener( insegnanteBean.ID_MATERIE_INSEGNAMENTO, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    //reinizializzo i JList
                    System.out.println("PCEListaMaterie");
                    inizializzaListaMaterieNonAssegnate();
                    inizializzaListaMaterieAssegnate();
                }
            }              
        );                
    }
    
    private void inizializzaForm(){
        jpnlDataAssunzione.setData( insegnanteBean.getDataAssunzione() );
//        inizializzaListaMaterieNonAssegnate();
//        inizializzaListaMaterieAssegnate();    
        
        //Impostazione valori non previsti dall'interfaccia utente
        insegnanteBean.setMatricola(nu.mine.egoless.didattica.app.bean.Costanti.MATRICOLA_DOCENTE_DUMMY);
    }
    
    /**
     * inizializza le componenti grafiche del pannello
     **/
    private void inizializzaComponenti(){
        initComponents();
        jpnlDataAssunzione.addPropertyChangeListener( new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent e){
                    jpnlDataAssunzionePropertyChanged(e);
                }
            }
        );
        //Inizializza i due JList dell' add-and-remove idiom'
        inizializzaListaMaterieNonAssegnate();
        inizializzaListaMaterieAssegnate();
        //fine dell'inizializzazione dei JList'
    }   
    
    /**
     * Inizializza la lista grafica delle materie non ancora assegnate all'insegnante in considerazione
     */      
    private void inizializzaListaMaterieNonAssegnate(){
        //inizializza il JList delle materie presenti ma non ancora assegnate a tale insegnante
        MateriaInsegnamento[] nonAssegnate = getMaterieNonAssegnate(); //id delle materie non assegnate
        MateriaInsegnamentoAdapter[] temp = new MateriaInsegnamentoAdapter[nonAssegnate.length];
        for(int i=0; i<nonAssegnate.length; i++)
            temp[i] = new MateriaInsegnamentoAdapter(nonAssegnate[i]);
        jlstMateriePresenti.setListData( temp );        
    }

    /**
     * Inizializza la lista grafica delle materie già assegnate all'insegnante in considerazione
     */      
    private void inizializzaListaMaterieAssegnate(){
         //inizializza il JList delle materie assegnate con le materie assegnate all'insegnante
        int[] assegnate = insegnanteBean.getIdMaterie();
        //System.out.println("ASSEGANTEAPPENAPRESE: "+assegnate.length); //DEBUG CODE
        MateriaInsegnamento[] materie = new MateriaInsegnamento[ assegnate.length ];       
        //inizializza anche in questo caso i nomi delle materie (assegante)
        for( int i=0; i < assegnate.length; i++){
            MateriaInsegnamento m = UtilitaGUI.getMateriaInsegnamento( contenitore.getMaterieInsegnamento(), assegnate[i] );
            materie[i] = m;
        }
        MateriaInsegnamentoAdapter[] temp = new MateriaInsegnamentoAdapter[materie.length];
        for(int i=0; i<materie.length; i++)
            temp[i] = new MateriaInsegnamentoAdapter(materie[i]);
        //System.out.println("ASSEGANTE: "+temp.length); //DEBUG CODE
        jlstMaterieInsegnate.setListData(temp);       
    }
    
    /*
     * Ritorna la lista degli id delle materie non ancora assegnate all'insegnante in considerazione
     * basandosi sui dati contenuti nel ContenitoreDati ed in InsegnanteBean
     */
    private MateriaInsegnamento[] getMaterieNonAssegnate(){
        MaterieInsegnamentoBean materiePresenti = contenitore.getMaterieInsegnamento();
        Vector<MateriaInsegnamento> nonAssegnate = new Vector<MateriaInsegnamento>();
        //cico che inserisce in nonAssegnate le materie non ancora assegnate a tale insegnante
        for( int i=0; i < materiePresenti.ritornaNumeroDiMaterie(); i++){
            MateriaInsegnamento m = materiePresenti.getMateriaAt(i);
            int currentId = m.getId();
            if( ! giaAssegnata(currentId) ){
                nonAssegnate.add( m );
            }
        }
        MateriaInsegnamento[] result = nonAssegnate.toArray( new MateriaInsegnamento[0] );
        //System.out.println("NON ASSEGANTE: "+result.length); //DEBUG CODE
        return result;
    }
    
    /**
     * Confrontando l'id passato con gli i delle materie già assegnate all'insegnante in questione
     * ritorna un boolean indicante  se tale materia è già assegnata a tale insegnante
     **/
    private boolean giaAssegnata(int id){
        int[] assegnate = insegnanteBean.getIdMaterie();
        if(assegnate.length==0) return false;
        for( int i=0; i < assegnate.length; i++ ){
            if(assegnate[i] == id)
                return true;
        }
        return false;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jpnlDatiSpecifici = new javax.swing.JPanel();
        jpnlGestioneInsegnamenti = new javax.swing.JPanel();
        jlblMateriePresenti = new javax.swing.JLabel();
        jlblMaterieInsegnate = new javax.swing.JLabel();
        jpnlBottoni = new javax.swing.JPanel();
        jbRimuovi = new javax.swing.JButton();
        jbAggiungi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlstMateriePresenti = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlstMaterieInsegnate = new javax.swing.JList();
        jpnlAuxDataAssunzione = new javax.swing.JPanel();
        jlblDataAssunzione = new javax.swing.JLabel();
        jpnlDataAssunzione = new nu.mine.egoless.didattica.app.gui.componentiComuni.DataPanel();

        jpnlDatiSpecifici.setBackground(new java.awt.Color(204, 204, 255));
        jpnlDatiSpecifici.setBorder(javax.swing.BorderFactory.createTitledBorder("Dati Scolastici"));
        jpnlGestioneInsegnamenti.setBackground(new java.awt.Color(204, 204, 255));
        jpnlGestioneInsegnamenti.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestione Insegnamenti"));
        jlblMateriePresenti.setDisplayedMnemonic('P');
        jlblMateriePresenti.setText("Materie non Ins...: ");

        jlblMaterieInsegnate.setDisplayedMnemonic('I');
        jlblMaterieInsegnate.setText("Materie Insegnate: ");

        jpnlBottoni.setBackground(new java.awt.Color(204, 204, 255));
        jbRimuovi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/Back16.gif")));
        jbRimuovi.setMnemonic('<');
        jbRimuovi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRimuoviActionPerformed(evt);
            }
        });

        jbAggiungi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/Forward16.gif")));
        jbAggiungi.setMnemonic('>');
        jbAggiungi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAggiungiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnlBottoniLayout = new javax.swing.GroupLayout(jpnlBottoni);
        jpnlBottoni.setLayout(jpnlBottoniLayout);
        jpnlBottoniLayout.setHorizontalGroup(
            jpnlBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlBottoniLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbRimuovi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAggiungi, 0, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnlBottoniLayout.setVerticalGroup(
            jpnlBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlBottoniLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbAggiungi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbRimuovi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jlstMateriePresenti);

        jScrollPane2.setViewportView(jlstMaterieInsegnate);

        javax.swing.GroupLayout jpnlGestioneInsegnamentiLayout = new javax.swing.GroupLayout(jpnlGestioneInsegnamenti);
        jpnlGestioneInsegnamenti.setLayout(jpnlGestioneInsegnamentiLayout);
        jpnlGestioneInsegnamentiLayout.setHorizontalGroup(
            jpnlGestioneInsegnamentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlGestioneInsegnamentiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlGestioneInsegnamentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlGestioneInsegnamentiLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addComponent(jlblMateriePresenti, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addComponent(jpnlBottoni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlGestioneInsegnamentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(jlblMaterieInsegnate, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnlGestioneInsegnamentiLayout.setVerticalGroup(
            jpnlGestioneInsegnamentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlGestioneInsegnamentiLayout.createSequentialGroup()
                .addGroup(jpnlGestioneInsegnamentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblMateriePresenti)
                    .addComponent(jlblMaterieInsegnate))
                .addGroup(jpnlGestioneInsegnamentiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnlGestioneInsegnamentiLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE))
                    .addGroup(jpnlGestioneInsegnamentiLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE))
                    .addGroup(jpnlGestioneInsegnamentiLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpnlBottoni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnlAuxDataAssunzione.setBackground(new java.awt.Color(204, 204, 255));
        jlblDataAssunzione.setDisplayedMnemonic('A');
        jlblDataAssunzione.setText("Data Assunzione: ");

        javax.swing.GroupLayout jpnlAuxDataAssunzioneLayout = new javax.swing.GroupLayout(jpnlAuxDataAssunzione);
        jpnlAuxDataAssunzione.setLayout(jpnlAuxDataAssunzioneLayout);
        jpnlAuxDataAssunzioneLayout.setHorizontalGroup(
            jpnlAuxDataAssunzioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlAuxDataAssunzioneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlAuxDataAssunzioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblDataAssunzione)
                    .addComponent(jpnlDataAssunzione, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnlAuxDataAssunzioneLayout.setVerticalGroup(
            jpnlAuxDataAssunzioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlAuxDataAssunzioneLayout.createSequentialGroup()
                .addComponent(jlblDataAssunzione)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlDataAssunzione, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnlDatiSpecificiLayout = new javax.swing.GroupLayout(jpnlDatiSpecifici);
        jpnlDatiSpecifici.setLayout(jpnlDatiSpecificiLayout);
        jpnlDatiSpecificiLayout.setHorizontalGroup(
            jpnlDatiSpecificiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlAuxDataAssunzione, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnlGestioneInsegnamenti, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnlDatiSpecificiLayout.setVerticalGroup(
            jpnlDatiSpecificiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlDatiSpecificiLayout.createSequentialGroup()
                .addComponent(jpnlAuxDataAssunzione, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlGestioneInsegnamenti, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    /**
     * metodi richiamati dai listener agganciati alle varie componenti del pannello
     */          
    private void jbAggiungiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAggiungiActionPerformed
        System.out.println("APAggiungi"); //DEBUGCODE
        //Si ricavano le amterie da aggiungere (è un array di MateriaInsegnamento)
        Object[] materieDaAggiungere = jlstMateriePresenti.getSelectedValues();
        //Dopo averne ricavato gli id...
        int[] idDaAggiungere = new int[materieDaAggiungere.length];
        for( int i =0; i<materieDaAggiungere.length; i++ ){
            idDaAggiungere[i] = ( ( (MateriaInsegnamentoAdapter) (materieDaAggiungere[i]) ).getMateriaInsegnamento() ).getId();
        }
        //...merge con gli id già presenti
        int[] idGiaPresenti = insegnanteBean.getIdMaterie(); //id delle materie già insegnate
        int[] ids = new int[idGiaPresenti.length + idDaAggiungere.length];
        for( int i=0; i<idGiaPresenti.length; i++ ){
            ids[i] = idGiaPresenti[i];
        }
        for( int i=0; i<idDaAggiungere.length; i++ ){
            ids[i + idGiaPresenti.length] = idDaAggiungere[i];
        }
        //assegnamento all'insegnante delle materie da insegnare
        //System.out.println("GestioneDatiInsegnantePanel: LISTA insegnate:"); // DEBUG CODE
        //for(int i=0; i<ids.length; i++) System.out.println(ids[i]); //DEBUG CODE
        if(inizializzato) insegnanteBean.setIdMaterie(ids);
    }//GEN-LAST:event_jbAggiungiActionPerformed

    private void jbRimuoviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRimuoviActionPerformed
        System.out.println("APRimuovi"); //DEBUGCODE
        //lista delle materie da rimuovere
        Object[] materieDaTogliere = jlstMaterieInsegnate.getSelectedValues();
        //Dopo averne ricavato gli id... 
        int[] idDaTogliere = new int[materieDaTogliere.length];
        for( int i =0; i<materieDaTogliere.length; i++ ){
            idDaTogliere[i] = ( ( (MateriaInsegnamentoAdapter) (materieDaTogliere[i]) ).getMateriaInsegnamento() ).getId();
        }  
        //...differenza con le materie insegnate in precedeza
        int[] idGiaPresenti = insegnanteBean.getIdMaterie(); //id delle amterie insegnate fino a quel momento
        Vector<Integer> insegnate= new Vector<Integer>(); //materie insegnate correntemente
        //inizializzo il vector con tutte le materie insegnate correntemente...
        for( int i=0; i < idGiaPresenti.length; i++ ){
            insegnate.add( new Integer(idGiaPresenti[i]) );
        }
        //...e poi elimino le materie da togliere
        for( int i=0; i < idDaTogliere.length; i++ ){
            insegnate.remove( new Integer(idDaTogliere[i]) );
        }
        //un pò di conversioni necessarie a presentare i dati nel modo giusto
        Integer[] temp = insegnate.toArray(new Integer[0]);
        int[] result = new int[temp.length];
        for( int i=0; i<temp.length; i++ )
            result[i] = temp[i].intValue();
        //assegno all'insegnante la nuova lista di amterie che deve insegnare
        //System.out.println("GestioneDatiInsegnantePanel: LISTA corrente insegnate:"); // DEBUG CODE
        //for(int i=0; i<result.length; i++) System.out.println(result[i]); //DEBUG CODE
        if(inizializzato) insegnanteBean.setIdMaterie(result);
    }//GEN-LAST:event_jbRimuoviActionPerformed
   
    private void jpnlDataAssunzionePropertyChanged(PropertyChangeEvent e){
        System.out.println("PCDataPanel ");
        if(inizializzato) insegnanteBean.setDataAssunzione(jpnlDataAssunzione.getDataSelezionata());
    }
    
    /**variabili locali*/    
    private InsegnanteBean insegnanteBean = null;
    private ContenitoreDati contenitore = null;
    
    private boolean inizializzato = false;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbAggiungi;
    private javax.swing.JButton jbRimuovi;
    private javax.swing.JLabel jlblDataAssunzione;
    private javax.swing.JLabel jlblMaterieInsegnate;
    private javax.swing.JLabel jlblMateriePresenti;
    private javax.swing.JList jlstMaterieInsegnate;
    private javax.swing.JList jlstMateriePresenti;
    private javax.swing.JPanel jpnlAuxDataAssunzione;
    private javax.swing.JPanel jpnlBottoni;
    private nu.mine.egoless.didattica.app.gui.componentiComuni.DataPanel jpnlDataAssunzione;
    private javax.swing.JPanel jpnlDatiSpecifici;
    private javax.swing.JPanel jpnlGestioneInsegnamenti;
    // End of variables declaration//GEN-END:variables
    
}
