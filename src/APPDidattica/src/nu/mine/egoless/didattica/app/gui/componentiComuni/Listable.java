/*
 * Listable.java
 *
 * Created on 17 marzo 2007, 21.34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.componentiComuni;

/**
 * Specifica la compatibilita' di un oggetto con la classe
 * ElementoComboBox e ModificaListaElemento.
 * 
 * @author Alberto
 */
public interface Listable<Tipo> {
   
   /**
    * Deve essere possibile convertire l'oggetto in String.
    */
   public String toString();
   
   
   /**
    * Deve essere possibile creare un nuovo oggetto da String.
    */
   public Tipo fromString(String stringa);
   
   
   /**
    * Deve essere possibile creare un nuovo oggetto da String.
    */
   public int getId ();
   
}
