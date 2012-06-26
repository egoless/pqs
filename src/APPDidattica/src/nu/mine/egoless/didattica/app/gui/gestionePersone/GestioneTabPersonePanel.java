/*
 * GestioneTabPersonePanel.java
 *
 * Created on 13 marzo 2007, 18.28
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.gestionePersone;

import nu.mine.egoless.didattica.app.gui.componentiComuni.*;
import nu.mine.egoless.didattica.app.bean.Costanti;
import javax.swing.JOptionPane;
import nu.mine.egoless.didattica.app.bean.NazioniBean;
import nu.mine.egoless.didattica.app.bean.ReligioniBean;
/**
 *
 * @author Lorenzo Daniele
 *
 * Estensione di JPanel che implementa, anche tramite metodi pubblici, la gestione
 * di due JTabbePane separati da un JSplitPane.
 * Estende le funzionalità di GestioneTabPanel fornendo metodi più specifici
 */
public abstract class GestioneTabPersonePanel extends GestioneTabPanel{
    
    /** 
     * Creates a new instance of GestioneTabPersonePanel 
     */
    public GestioneTabPersonePanel() {
    }
    
    /**
     * Ritorna un nuovo JPanel del tipo di quelli ceh si trovano nel JTabedPane di destra
     * @param id un identificativo univoco
     */
    protected abstract javax.swing.JPanel getNuovoPannelloDx(int id);
    
    /**
     * Apre un nuovo JPanel nel JTabbedPane di destra del tipo ritornato da getNuovoPannelloDx
     */    
    public void apriNuovoPannelloDx(int id){
        if(isAperto(id)){
            JOptionPane.showMessageDialog(this, "E' già aperto un form che gestisce tale persona");
        }
        else{
            javax.swing.JPanel jpnl = getNuovoPannelloDx(id);
            getTabbedPaneDx().addTab("Gestione", new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/Properties16.gif")), jpnl);
            UtilitaGUI.initTabComponent(getTabbedPaneDx(), jpnl, "");
            getTabbedPaneDx().setSelectedComponent(jpnl);
        }        
    }
    
    /*
     * Ritorna true se un GestionePersonaPanel con tale id è già aperto
     */
    public boolean isAperto(int id){
        if( id == Costanti.ID_NUOVA_PERSONA ) return false;
        int count = getTabbedPaneDx().getTabCount();
        for(int i=0; i<count; i++){                  
            java.awt.Component jpnl = getTabbedPaneDx().getComponentAt(i);
            if(jpnl instanceof GestionePersonaPanel){            
                if( ((GestionePersonaPanel) jpnl).getId() == id && id != Costanti.ID_NUOVA_PERSONA){
                    return true;
                }    
            }
            else System.out.println (jpnl.getClass ().toString ());        
        }
        return false;
    }

}
