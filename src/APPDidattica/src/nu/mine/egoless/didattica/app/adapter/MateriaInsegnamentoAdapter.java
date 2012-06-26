/*
 * MateriaInsegnamentoAdapter.java
 *
 * Created on 26 marzo 2007, 23.44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.adapter;

import nu.mine.egoless.didattica.ws.materiaclient.MateriaInsegnamento;

/**
 *
 * @author Roberto
 */
public class MateriaInsegnamentoAdapter {
    
   private MateriaInsegnamento materia;
   
   /** Creates a new instance of MateriaInsegnamentoAdapter */
   public MateriaInsegnamentoAdapter(MateriaInsegnamento materia) {
      this.materia=materia;
   }
   
   public String toString() {
      return materia.getNome();
   }
   
   public MateriaInsegnamento getMateriaInsegnamento(){
      return materia;
   }
   
   public boolean equals(Object obj){
      if(obj == null || materia == null) return false;
      if (obj instanceof MateriaInsegnamentoAdapter){
         if( ((MateriaInsegnamentoAdapter)obj).getMateriaInsegnamento() == null ) return false;
         return (((MateriaInsegnamentoAdapter)obj).getMateriaInsegnamento().getId ()) == (materia.getId ());
      }
      else return false;
   }
   
   
  
   
}
