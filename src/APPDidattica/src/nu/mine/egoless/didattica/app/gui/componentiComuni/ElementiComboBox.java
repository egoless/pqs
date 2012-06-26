/*
 * ElementiComboBox.java
 *
 * Created on 11 marzo 2007, 4.09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.componentiComuni;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Vector;
import nu.mine.egoless.didattica.app.gui.componentiComuni.elementi.ElementoClasse;



/**
 * Visualizza una collezione di oggetti di ElementiTipo
 * all'interno di una lista di CamboBox.
 *
 * @author  Alberto Meneghello
 */
public class ElementiComboBox <Tipo extends Listable> extends javax.swing.JComboBox{
   
   
   /**
    * Creates a new instance of ElementiComboBox
    */
   public ElementiComboBox (Vector<Tipo> lista, boolean modifica) {
       super(lista);
       this.lista = lista;
       //this.propertyChangeSupport =  new PropertyChangeSupport(this);
       
       if (modifica) addItem(MODIFICA);
             
       
       addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxActionPerformed(evt);
            }
       });
   }
   
   
   
   public void aggiornaLista () {
   }
   
   
   public Tipo getElementoSelezionato() {
      return (Tipo)(this.getSelectedItem ());
   }

   
   
   public void setElementoSelezionato(Tipo elem) {
      this.setSelectedItem(elem);
   }
   
   
   public void setElementoSelezionato(int elemIndex) {
      if( elemIndex < lista.size () )
        this.setSelectedIndex( (int)elemIndex);
   }
   
   /**
    * Seleziona un elemento della lista in base al suo id.
    * @param elemId Id dell'elemento da selezionare.
    */
   public void setElementoSelezionatoPerId(int elemId) {
      for(int i=0; i<lista.size (); i++ ){
         if( lista.elementAt (i).getId()==elemId ){
            this.setSelectedIndex(i);
            return;
         }
      }
   }
   
   
   
   protected void comboBoxActionPerformed (java.awt.event.ActionEvent evt) {                                         
      if(getSelectedItem() == MODIFICA)
      {
         removeItem (MODIFICA);
         synchronized(lista) { new ModificaListaElementiDialog (null, true, "Colori", lista).setVisible (true); }
         synchronized(lista) { addItem (MODIFICA); }
      } /*
      else
      {
         System.out.println( getElementoSelezionato() );
         //propertyChangeSupport.firePropertyChange (EVENTO, null, getElementoSelezionato()  );
      }*/
   }                                
   
   
   
   
   
   
   
   /**
    * Aggiunge un propertyChangeListener alla lista dei listener
    * @param l Il listener da aggiungere.
    * @param nomeProprieta proprieta' da ascoltare
    */  /*
   public void addPropertyChangeListener(PropertyChangeListener l) {
      propertyChangeSupport.addPropertyChangeListener(EVENTO,l);
   }*/
   
   
   /**
    * Rimuove un propertyChangeListener alla lista dei listener
    * @param l Il listener da rimuovere.
    */ /*
   public void removePropertyChangeListener(PropertyChangeListener l) {
      propertyChangeSupport.removePropertyChangeListener(EVENTO,l);
   }*/
   
   
   
   
   
   
   protected Vector<Tipo> lista;
   protected static final String MODIFICA = "- Modifica Lista -";
   
   //protected static final String EVENTO = "Action Event";
   //protected PropertyChangeSupport propertyChangeSupport;
   
   
   
   
   /**
    * Avvia un'anteprima dell'interfaccia grafica.
    */
   public static void main (String[] args) {
      try { javax.swing.UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); }
      catch (Exception ex) { System.out.println ("Failed loading Look And Feel:\n"+ex+'\n'); }
      
      javax.swing.JFrame temp=new javax.swing.JFrame ();
      temp.setLayout (new java.awt.BorderLayout ());
      
      //String[] str = { "Red", "Green", "Blue", "Yellow", "Orange", "Violet" };
      String[] str = { "1A", "2A", "3B", "4B", "5A", "5B" };
      Vector<ElementoClasse> elementi = new Vector<ElementoClasse>();
      for(int i=0; i<str.length; i++) { elementi.add( new ElementoClasse( 0, str[i]) ); }
      
      ElementiComboBox jpnlData = new ElementiComboBox(elementi, true);
      jpnlData.setElementoSelezionato(0);
      temp.add (jpnlData);
      temp.setSize (200,56);
      temp.setVisible (true);
   }
   
}

   
 
   
   


