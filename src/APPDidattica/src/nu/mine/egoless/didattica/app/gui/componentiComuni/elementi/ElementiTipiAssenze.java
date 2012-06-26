/*
 * ElementiTipiAssenze.java
 *
 * Created on 18 marzo 2007, 21.51
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.componentiComuni.elementi;

import java.util.Vector;
import nu.mine.egoless.didattica.app.bean.TipiAssenzeBean;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ElementiComboBox;

/**
 *
 * @author Admin
 */
public class ElementiTipiAssenze extends ElementiComboBox {
   
   TipiAssenzeBean tipiAssenze;
   
   public ElementiTipiAssenze(TipiAssenzeBean tipiAssenze, boolean modifica){
      super(new Vector(), modifica);
      this.tipiAssenze = tipiAssenze;
      inizializzaLista();
   }
   
   
   private void inizializzaLista() {
      lista.add ( new ElementoTipoAssenza( -1, "Non Assegnato" ) );
      for(int i=0; i<tipiAssenze.ritornaNumeroDiTipiAssenze(); i++ ) {
         lista.add (  (ElementoTipoAssenza) new ElementoTipoAssenza( tipiAssenze.getTipoAssenzaAt(i).getId (), 
                                                                     tipiAssenze.getTipoAssenzaAt(i).getDescrizione () ) );
      }
   }
   
}   