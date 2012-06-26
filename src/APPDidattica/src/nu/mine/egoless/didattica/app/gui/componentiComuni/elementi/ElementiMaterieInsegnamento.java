/*
 * ElementiMaterieInsegnamento.java
 *
 * Created on 22 marzo 2007, 2.30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.componentiComuni.elementi;

import java.util.Vector;
import nu.mine.egoless.didattica.app.bean.MaterieInsegnamentoBean;
import nu.mine.egoless.didattica.app.gui.componentiComuni.ElementiComboBox;



/**
 *
 * @author Alberto Meneghello
 */
public class ElementiMaterieInsegnamento extends ElementiComboBox {
   
   MaterieInsegnamentoBean materie;
   
   public ElementiMaterieInsegnamento(MaterieInsegnamentoBean materie, boolean modifica){
      super(new Vector(), modifica);
      this.materie = materie;
      inizializzaLista();
   }
   
   
   private void inizializzaLista() {
      for(int i=0; i<materie.ritornaNumeroDiMaterie(); i++ )
         lista.add ( new ElementoMateriaInsegnamento( materie.getMateriaAt(i).getId (),
                                                      materie.getMateriaAt(i).getNome () ) );
   }
   
}   