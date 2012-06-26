/*
 * GestioneTabPanel.java
 *
 * Created on 8 marzo 2007, 17.47
 */

package nu.mine.egoless.didattica.app.gui.gestionePersone;

import nu.mine.egoless.didattica.app.gui.componentiComuni.*;
import javax.swing.JOptionPane;
/**
 *
 * @author  Lorenzo Daniele
 *
 * Classe che implementa un pannello che contiene due tabbed pane divisi da
 * un separatore verticale. Lo scopo è quello di gestire tali due tabbed pane ed  
 * i pannelli in essi contenuti
 * Contiene un BarraDiStatoPanel
 */
public class GestioneTabPanel extends javax.swing.JPanel implements BarraDiStatoCompatibile {
    
    /**
     * Creates new form GestioneTabPanel
     */
    public GestioneTabPanel() {
        initComponents();       
        //jsppGestionePersona.setDividerLocation( this.getWidth()/2 );
    }
    
    /**
     * Ritorna un nuovo pannello del tipo contenuto nel tabbed pane di sinistra
     **/       
    protected javax.swing.JPanel getNuovoPannelloSx(){return new javax.swing.JPanel();}

    /**
     * Ritorna un nuovo pannello del tipo contenuto nel tabbed pane di destra
     **/        
    protected javax.swing.JPanel getNuovoPannelloDx(){return new javax.swing.JPanel();}

    /**
     * ritorna un reference al tabbed pane di sinistra
     */    
    protected javax.swing.JTabbedPane getTabbedPaneSx(){ return jtabpSx; }
    
    /**
     * ritorna un reference al tabbed pane di destra
     */
    protected javax.swing.JTabbedPane getTabbedPaneDx(){ return jtabpDx; }

    /**
     * apre un nuovo pannello nel tabbed pane di sinistra
     * richiamando il metodo getNuovoPannelloSx()
     */
    public void apriNuovoPannelloSx(){
        javax.swing.JPanel jpnl = getNuovoPannelloSx();
        //javax.swing.JScrollPane jscp = new javax.swing.JScrollPane( jpnl );
        jtabpSx.addTab("Ricerca", new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/Search16.gif")), jpnl);
        UtilitaGUI.initTabComponent(jtabpSx, jpnl, "");
        jtabpSx.setSelectedComponent(jpnl);   
    }
    
    /**
     * apre un nuovo pannello nel tabbed pane di sinistra
     * richiamando il metodo getNuovoPannelloDx()
     */
    public void apriNuovoPannelloDx(){
        javax.swing.JPanel jpnl = getNuovoPannelloDx();
        jtabpDx.addTab("Gestione", new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/Properties16.gif")), jpnl);
        UtilitaGUI.initTabComponent(jtabpDx, jpnl, "");
        jtabpDx.setSelectedComponent(jpnl);   
    }    

    /**
     * Chiude un pannello nel tabbed pane di sinistra
     * @param jpnl il pannello da chiudere
     */
    public void chiudiPannelloSx(javax.swing.JPanel jpnl){
        int i = jtabpSx.indexOfComponent(jpnl);
        if(i!=-1) jtabpSx.removeTabAt( i );
    }        

    /**
     * Chiude un pannello nel tabbed pane di destra
     * @param jpnl il pannello da chiudere
     */    
    public void chiudiPannelloDx(javax.swing.JPanel jpnl){
        int i = jtabpDx.indexOfComponent(jpnl);
        if(i!=-1) jtabpDx.removeTabAt( i );
    }
    
    /**
     * setta la barra di upload in stato indeterminato
     */
    public void setUploadIndeterminato(boolean b){
        jpnlBarraDiStato.setUploadIndeterminato(b);
    }
    
    /**
     * setta la barra di download in stato indeterminato
     */
    public void setDownloadIndeterminato(boolean b){
        jpnlBarraDiStato.setDownloadIndeterminato(b);
    }  

    /**
     * Setta il testo nella label di stato
     */
    public void setTesto(String testo) {
        jpnlBarraDiStato.setTesto(testo);
    }
    
    /**
     * Setta il valore nella barra indicante il progresso di uploading appartenente a [1...100]
     */
    public void setValoreUpload(int n) {
        jpnlBarraDiStato.setValoreUpload(n);
    }
    
    /**
     * Setta il valore nella barra indicante il progresso di downloading appartenente a [1...100]
     */
    public void setValoreDownload(int n) {
        jpnlBarraDiStato.setValoreDownload(n);
    }        

    /**
     * setta a 0 la barra (JProgressBar) di upload
     */
    public void resetBarraUpload(){ 
        jpnlBarraDiStato.setValoreUpload(0);
    }

    /**
     * setta a 0 la barra (JProgressBar) di download
     */    
    public void resetBarraDownloadload(){ 
        jpnlBarraDiStato.setValoreDownload(0);
    }        
    
    /**
     * main di prova
     */
    public static void main(String[] args) {
        javax.swing.JDialog temp=new javax.swing.JDialog();
        temp.setLayout(new java.awt.BorderLayout());
        GestioneTabPanel t=new GestioneTabPanel();
        temp.add(t);
        t.apriNuovoPannelloSx();       
        temp.setSize(500,500);
        temp.setVisible(true);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jsppGestionePersona = new javax.swing.JSplitPane();
        jtabpDx = new javax.swing.JTabbedPane();
        jtabpSx = new javax.swing.JTabbedPane();
        jpnlBarraDiStato = new nu.mine.egoless.didattica.app.gui.componentiComuni.BarraDiStatoPanel();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setBackground(new java.awt.Color(153, 153, 255));
        jsppGestionePersona.setBackground(new java.awt.Color(153, 153, 255));
        jtabpDx.setBackground(new java.awt.Color(204, 204, 255));
        jtabpDx.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jsppGestionePersona.setRightComponent(jtabpDx);

        jtabpSx.setBackground(new java.awt.Color(204, 204, 255));
        jtabpSx.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jsppGestionePersona.setLeftComponent(jtabpSx);

        jScrollPane1.setViewportView(jsppGestionePersona);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
            .addComponent(jpnlBarraDiStato, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlBarraDiStato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private nu.mine.egoless.didattica.app.gui.componentiComuni.BarraDiStatoPanel jpnlBarraDiStato;
    private javax.swing.JSplitPane jsppGestionePersona;
    private javax.swing.JTabbedPane jtabpDx;
    private javax.swing.JTabbedPane jtabpSx;
    // End of variables declaration//GEN-END:variables
    
}
