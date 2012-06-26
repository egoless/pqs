/*
 * ReligioneAdapter.java
 *
 * Created on 26 marzo 2007, 23.27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.adapter;

import nu.mine.egoless.didattica.ws.religioneclient.Religione;

/**
 *
 * @author Roberto
 */
public class ReligioneAdapter {
   
   private Religione religione;
   
   /** Creates a new instance of NazioneAdapter */
   public ReligioneAdapter(Religione religione) {
      this.religione=religione;
   }
   
   public String toString() {
      return religione.getNome();
   }
   
   public Religione getReligione(){
      return religione;
   }
   
   public boolean equals(Object obj){
      if(obj == null || religione == null) return false;      
      boolean b = false; 
      if (obj instanceof ReligioneAdapter) {
          if(((ReligioneAdapter)obj).getReligione()==null) return false;
          b = (((ReligioneAdapter)obj).getReligione().getId ()) == (religione.getId ());
      }
      else b = false;
      //System.out.println("ClasseAdapter->-> "+b); //DEBUG CODE
      return b;
   }
}