/*
 * NazioneAdapter.java
 *
 * Created on 26 marzo 2007, 23.22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.adapter;

import nu.mine.egoless.didattica.ws.nazioneclient.Nazione;

/**
 *
 * @author Roberto
 */
public class NazioneAdapter {
   
   private Nazione nazione;
   
   /** Creates a new instance of NazioneAdapter */
   public NazioneAdapter(Nazione nazione) {
      this.nazione=nazione;
   }
   
   public String toString() {
      return nazione.getNome();
   }
   
   public Nazione getNazione(){
      return nazione;
   }
   
   public boolean equals(Object obj){
      if(obj == null || nazione == null) return false;      
      if (obj instanceof NazioneAdapter){
         if(((NazioneAdapter)obj).getNazione()==null) return false; 
         return (((NazioneAdapter)obj).getNazione().getId ()) == (nazione.getId ());
      }
      else return false;
   }
   
   
  
}
