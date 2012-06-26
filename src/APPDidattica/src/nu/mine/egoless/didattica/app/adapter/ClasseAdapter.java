/*
 * ClasseAdapter.java
 *
 * Created on 26 marzo 2007, 23.42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.adapter;

import nu.mine.egoless.didattica.ws.classesupport.Classe;

/**
 *
 * @author Administrator
 *
 * Adapter pe rla classe Classe necessario per l'utilizzo dei COmbo Box che 
 * richiedono il metodo toString
 */
public class ClasseAdapter {
    
    
private Classe classe = null;
    /** Creates a new instance of ClasseAdapter */
    public ClasseAdapter(Classe classe) {
        this.classe = classe;
    }
    
    public String toString(){ 
        return classe.getAnnoCorso()+" "+((char)classe.getSezione()); 
    }
    
    public Classe getClasse(){ 
        return classe; 
    }
    
    public boolean equals(Object obj){
      if(obj == null || classe == null) return false;
      if (obj instanceof ClasseAdapter){
         if( ((ClasseAdapter)obj).getClasse() == null ) return false;
         return (((ClasseAdapter)obj).getClasse().getId ()) == (classe.getId ());
      }
      else return false;
   }
    
}
