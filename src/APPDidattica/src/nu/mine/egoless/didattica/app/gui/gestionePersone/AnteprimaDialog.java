/*
 * AnteprimaDialog.java
 *
 * Created on 18 marzo 2007, 0.06
 */

package nu.mine.egoless.didattica.app.gui.gestionePersone;

import nu.mine.egoless.didattica.app.bean.PersonaBean;
import nu.mine.egoless.didattica.app.bean.StudenteBean;
import nu.mine.egoless.didattica.app.bean.InsegnanteBean;
import nu.mine.egoless.didattica.app.bean.ContattoBean;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import nu.mine.egoless.didattica.app.bean.ReligioniBean;
import nu.mine.egoless.didattica.app.bean.NazioniBean;
import nu.mine.egoless.didattica.app.bean.ClassiBean;
import nu.mine.egoless.didattica.app.bean.MaterieInsegnamentoBean;
import nu.mine.egoless.didattica.app.bean.TipiVotiBean;
import nu.mine.egoless.didattica.app.bean.TipiAssenzeBean;
import nu.mine.egoless.didattica.ws.religioneclient.Religione;
import nu.mine.egoless.didattica.ws.nazioneclient.Nazione;
import nu.mine.egoless.didattica.ws.classesupport.Classe;
import nu.mine.egoless.didattica.ws.materiaclient.MateriaInsegnamento;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ContenitoreDati;
import nu.mine.egoless.didattica.app.gui.componentiComuni.UtilitaGUI;

/**
 *
 * @author  Lorenzo Daniele
 *
 * E' un dialog che ha lo scopo di mostrare i dati del personaBean che gli viene passato
 */
public class AnteprimaDialog extends javax.swing.JDialog {
    
    /**
     * Creates new form AnteprimaDialog
     * @ parent Frame che detiene tale dialog
     * @ modal per rendere modale il dialog
     * @ persona il personaBean di cui si vogliono mostrare i dati
     * @ contatto il contatoBean relativo al personaBEan di cui si vogliono mostrare i dati
     * Non vine verificata la consistenza della relazione persona-contatto
     */
    public AnteprimaDialog(java.awt.Frame parent, boolean modal, PersonaBean persona, ContattoBean contatto, ContenitoreDati contenitore) {
        super(parent, modal);
        if(persona==null || contatto==null || contenitore==null) throw new IllegalArgumentException();
        this.personaBean = persona;
        this.contattoBean = contatto;
        this.contenitoreDati = contenitore;
        initComponents();
        this.setModal(modal);
        this.setTitle("Anteprima - "+personaBean.getNome()+" - "+personaBean.getCognome());
        //Listener generali
        aggiungiPropertyListener();
        aggiornaTesto();
    }
 
    /**
     * Aggiunge i listener per i cambiamenti di stato sul personaBean
     * Inoltre aggiunge listener specifici per alcune proprietà in base al rtipo dinamico di personaBean
     */
    private void aggiungiPropertyListener(){  
        personaBean.addPropertyChangeListener( personaBean.NOME, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    main.setTitle("Anteprima - "+personaBean.getNome()+" - "+personaBean.getCognome());
                    aggiornaTesto();
                }
            }              
        );
        personaBean.addPropertyChangeListener( personaBean.COGNOME, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    main.setTitle("Anteprima - "+personaBean.getNome()+" - "+personaBean.getCognome());
                    aggiornaTesto();
                }
            }              
        );
        personaBean.addPropertyChangeListener( personaBean.DATA_NASCITA, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    aggiornaTesto();
                }
            }              
        );
        personaBean.addPropertyChangeListener( personaBean.CODICE_FISCALE, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    aggiornaTesto();
                }
            }              
        );
        personaBean.addPropertyChangeListener( personaBean.NAZIONALITA, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                        aggiornaTesto();
                }
            }              
        );
        personaBean.addPropertyChangeListener( personaBean.RELIGIONE, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    aggiornaTesto();
                }
            }              
        );
        personaBean.addPropertyChangeListener( personaBean.PORTATORE_HANDICAP, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    aggiornaTesto();          
                }
            }              
        ); 
        contattoBean.addPropertyChangeListener( contattoBean.VIA, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    aggiornaTesto();    
                }
            }              
        );
        contattoBean.addPropertyChangeListener( contattoBean.CIVICO, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    aggiornaTesto();    
                }
            }              
        );
        contattoBean.addPropertyChangeListener( contattoBean.CAP, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    aggiornaTesto();    
                }
            }              
        );
        contattoBean.addPropertyChangeListener( contattoBean.PROVINCIA, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    aggiornaTesto();    
                }
            }              
        );            
        contattoBean.addPropertyChangeListener( contattoBean.TELEFONO_PRINCIPALE, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    aggiornaTesto();    
                }
            }              
        );
        contattoBean.addPropertyChangeListener( contattoBean.TELEFONO_SECONDARIO, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    aggiornaTesto();    
                }
            }              
        ); 
        contattoBean.addPropertyChangeListener( contattoBean.FAX, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    aggiornaTesto();    
                }
            }              
        ); 
        //Listener specifici
        if(personaBean instanceof StudenteBean){
            listenerSpecificiStudente();
        }
        //Listener specifici
        if(personaBean instanceof InsegnanteBean){
            listenerSpecificiInsegnante();       
        }        
    }    
    
    //aggiunge listener specifici
    private void listenerSpecificiStudente(){
        StudenteBean studenteBean = (StudenteBean) personaBean;
        studenteBean.addPropertyChangeListener( studenteBean.MATRICOLA, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    aggiornaTesto();    
                }
            }              
        );             
        studenteBean.addPropertyChangeListener( studenteBean.DATA_ISCRIZIONE, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    aggiornaTesto();    
                }
            }              
        );  
        studenteBean.addPropertyChangeListener( studenteBean.ID_CLASSE, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    aggiornaTesto();    
                }
            }              
        );             
    }

    //aggiunge listener specifici
    private void listenerSpecificiInsegnante(){
        InsegnanteBean insegnanteBean = (InsegnanteBean) personaBean;
        insegnanteBean.addPropertyChangeListener( insegnanteBean.DATA_ASSUNZIONE, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    aggiornaTesto();    
                }
            }              
        ); 
        insegnanteBean.addPropertyChangeListener( insegnanteBean.MATERIA_INSEGNAMENTO, new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent event){
                    aggiornaTesto();    
                }
            }              
        );             
    }
    
    /**
     * forza il riaggiornamento del testo mostrato, in base ai dati contenuti nel personaBean
     */
    private void aggiornaTesto(){
        //String nome,cognome,dataNascita,codiceFiscale,portatoreHandicap,religione,nazionalita,via,civico,cap,provincia,telefonoPrincipale,telefonoSecondario,Fax;
        String dataNascita = "-", nomeReligione="-", nomeNazione="-";
        if(personaBean.getDataNascita() != null) dataNascita = personaBean.getDataNascita().toString(); 
        int r = personaBean.getIdReligione();
        Religione religione = UtilitaGUI.getReligione( contenitoreDati.getReligioni(), r );
        Nazione nazione = UtilitaGUI.getNazione( contenitoreDati.getNazioni(), personaBean.getIdNazionalita() );
        if(religione != null) nomeReligione = religione.getNome();
        if(nazione != null) nomeNazione = nazione.getNome();
        //aggiorna la stringa da immettere nella text area
        String anteprima = new String( 
            "Cognome: " +personaBean.getCognome()+"\n"+
            "Nome: " +personaBean.getNome()+"\n"+
            "Data di Nascita: " +dataNascita+"\n"+
            "Codice Fiscale: " +personaBean.getCodiceFiscale()+"\n"+
            "Portatore di Handicap: " +UtilitaGUI.valoreBoolean((new Boolean(personaBean.isPortatoreHandicap())))+"\n"+
            "Religione: " +nomeReligione+"\n"+
            "Nazionalita: " +nomeNazione+"\n"+
            "Via: " +contattoBean.getVia()+"\n"+
            "Civico: " +contattoBean.getCivico()+"\n"+
            "C.A.P.: " +contattoBean.getCAP()+"\n"+
            "Provincia: " +contattoBean.getProvincia()+"\n"+
            "Telefono Principale: " +contattoBean.getTelefonoPrincipale()+"\n"+
            "Telefono Secondario: " +contattoBean.getTelefonoSecondario()+"\n"+
            "Fax: " +contattoBean.getFax()+"\n"
        );
        int row=0;
        //aggiorna la table
        insertRow(row++, new String("Cognome"), personaBean.getCognome());
        insertRow(row++, new String("Nome"), personaBean.getNome());
        insertRow(row++, new String("Data di Nascita"), dataNascita);
        insertRow(row++, new String("Codice Fiscale"), personaBean.getCodiceFiscale());
        insertRow(row++, new String("Portatore di Handicap"), UtilitaGUI.valoreBoolean((new Boolean(personaBean.isPortatoreHandicap()))));
        insertRow(row++, new String("Religione"), nomeReligione);
        insertRow(row++, new String("Nazionalita"), nomeNazione);
        insertRow(row++, new String("Via"), contattoBean.getVia());
        insertRow(row++, new String("Civico"), contattoBean.getCivico());
        insertRow(row++, new String("C.A.P."), contattoBean.getCAP());
        insertRow(row++, new String("Provincia"), contattoBean.getProvincia());
        insertRow(row++, new String("Telefono Principale"), contattoBean.getTelefonoPrincipale());
        insertRow(row++, new String("Telefono Secondario"), contattoBean.getTelefonoSecondario());
        insertRow(row++, new String("Fax"), contattoBean.getFax());
      
        //aggiorna la table
        if(personaBean instanceof StudenteBean){      
            StudenteBean studenteBean = (StudenteBean) personaBean;
            String dataIscrizione = "-", classe ="-";
            if( studenteBean.getDataIscrizione() != null ) dataIscrizione = studenteBean.getDataIscrizione().toString();
            Classe c = UtilitaGUI.getClasse( contenitoreDati.getClassi(), studenteBean.getId() );
            if(c != null) classe = UtilitaGUI.getNomeClasse(c);
            anteprima+=
                "Matricola: "+studenteBean.getMatricola()+"\n"+
                "Data di Iscrizone: "+dataIscrizione+"\n";//+
                //"Classe: "+classe+"\n"; // TO DO
            //aggiorna la table
            insertRow(row++, new String("Matricola"), studenteBean.getMatricola());
            insertRow(row++, new String("Data di Iscrizone"), dataIscrizione);
            //insertRow(row++, new String("Classe"), classe); //TO DO
        }
        
        if(personaBean instanceof InsegnanteBean){      
            InsegnanteBean insegnanteBean = (InsegnanteBean) personaBean;
            String dataAssunzione = "-", materie="";
            if( insegnanteBean.getDataAssunzione() != null ) dataAssunzione = insegnanteBean.getDataAssunzione().toString();
            anteprima+=
                "Data di Iscrizone: "+dataAssunzione+"\n";
            insertRow( row++, new String("Data di Iscrizone"), dataAssunzione );    
            //raccolta delle materie in un unica stringa           
            /*int[] ids = insegnanteBean.getIdMaterie();
            for( int i=0; i<ids.length; i++ ){
                MateriaInsegnamento materia = UtilitaGUI.getMateriaInsegnamento( contenitoreDati.getMaterieInsegnamento(), ids[i] );
                if(materia!=null) materie+=materia.getNome()+"\n";
                else System.out.println("Anteprima: Errore imprevedibile -> id non corrispondente ad alcuna materia");
            }*/
            materie = UtilitaGUI.getStringaElencoMaterie(insegnanteBean.getIdMaterie(), contenitoreDati);
            //insertRow( row++, new String("Materie"), materie ); //TO DO    
        }        
        jta.setText(anteprima);      
    }
    
    /**
     * Aggiunge una riga alla table con i valori specificati ed alla riga specificata
     */
    private void insertRow(int row, Object attName, Object value){
        Object[] temp = {attName, value};
        UtilitaGUI.inserisciRiga(jtbl, row, temp);          
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jpnlAux = new javax.swing.JPanel();
        jscpTextArea = new javax.swing.JScrollPane();
        jta = new javax.swing.JTextArea();
        jlblAnteprima = new javax.swing.JLabel();
        jcbAlwaysOnTop = new javax.swing.JCheckBox();
        jscpTable = new javax.swing.JScrollPane();
        jtbl = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Anteprima");
        setForeground(java.awt.Color.gray);
        jpnlAux.setBackground(new java.awt.Color(204, 204, 255));
        jta.setColumns(20);
        jta.setEditable(false);
        jta.setLineWrap(true);
        jta.setRows(5);
        jscpTextArea.setViewportView(jta);

        jlblAnteprima.setBackground(new java.awt.Color(153, 153, 255));
        jlblAnteprima.setText("Anteprima:");

        jcbAlwaysOnTop.setBackground(new java.awt.Color(204, 204, 255));
        jcbAlwaysOnTop.setText("Sempre in primo piano");
        jcbAlwaysOnTop.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jcbAlwaysOnTop.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jcbAlwaysOnTop.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbAlwaysOnTopItemStateChanged(evt);
            }
        });

        jtbl.setAutoCreateRowSorter(true);
        jtbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Attributo", "Title 2"
            }
        ));
        jtbl.setDragEnabled(true);
        jscpTable.setViewportView(jtbl);

        org.jdesktop.layout.GroupLayout jpnlAuxLayout = new org.jdesktop.layout.GroupLayout(jpnlAux);
        jpnlAux.setLayout(jpnlAuxLayout);
        jpnlAuxLayout.setHorizontalGroup(
            jpnlAuxLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnlAuxLayout.createSequentialGroup()
                .addContainerGap()
                .add(jpnlAuxLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jscpTextArea, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .add(jscpTable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .add(jlblAnteprima, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .add(jcbAlwaysOnTop))
                .addContainerGap())
        );
        jpnlAuxLayout.setVerticalGroup(
            jpnlAuxLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnlAuxLayout.createSequentialGroup()
                .addContainerGap()
                .add(jlblAnteprima)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jscpTable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jscpTextArea, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jcbAlwaysOnTop)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnlAux, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnlAux, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbAlwaysOnTopItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbAlwaysOnTopItemStateChanged
        setAlwaysOnTop(jcbAlwaysOnTop.isSelected());
    }//GEN-LAST:event_jcbAlwaysOnTopItemStateChanged
        
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //ContenitoreDati c = new ContenitoreDati(new ReligioniBean(), new NazioniBean(), new ClassiBean(), new MaterieInsegnamentoBean(), new TipiVotiBean(), new TipiAssenzeBean());
                //AnteprimaDialog a = new AnteprimaDialog(new javax.swing.JFrame(), true, new StudenteBean(), new ContattoBean(), c);
                //a.setVisible(true);
                //a.aggiornaTesto();
            }
        });
    }
    
    private AnteprimaDialog main = this;
    private PersonaBean personaBean = null;
    private ContattoBean contattoBean = null;
    private ContenitoreDati contenitoreDati = null;
    
    private static int COL_ATTNAME = 0; //colonna del nome dell'attributo
    private static int COL_VALUE = 1; //colonna del valore dell'attributo'
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jcbAlwaysOnTop;
    private javax.swing.JLabel jlblAnteprima;
    private javax.swing.JPanel jpnlAux;
    private javax.swing.JScrollPane jscpTable;
    private javax.swing.JScrollPane jscpTextArea;
    private javax.swing.JTextArea jta;
    private javax.swing.JTable jtbl;
    // End of variables declaration//GEN-END:variables
    
}
