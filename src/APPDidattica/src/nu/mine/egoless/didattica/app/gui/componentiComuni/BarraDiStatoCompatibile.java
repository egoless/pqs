/*
 * BarraDiStatoCompatibile.java
 *
 * Created on 13 marzo 2007, 18.49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.componentiComuni;

/**
 *
 * @author Lorenzo Daniele
 *
 * Interfaccia che devono implementare tutti gli oggetti (grafici) che contengano
 * e permattano di manipolare una barra di stato (BarraDiStatoPanel)
 */
public interface BarraDiStatoCompatibile {
    
    /**Setta il testo nella label di stato*/
    public void setTesto(String testo);
    
    /**Setta il valore nella barra indicante il progresso di uploading appartenente a [1...100]*/
    public void setValoreUpload(int n);
    /**Setta il valore nella barra indicante il progresso di downloading appartenente a [1...100]*/
   
    public void setValoreDownload(int n);   
    
    public void setUploadIndeterminato(boolean b);

    public void setDownloadIndeterminato(boolean b);
    
}
