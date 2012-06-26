/*
 * IconListRenderer.java
 *
 * Created on 24 marzo 2007, 18.31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nu.mine.egoless.didattica.app.gui.componentiComuni;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Lorenzo Daniele
 *
 * Crea un renderer che permette di visualizzare un'icona (tramite una label) all'interno
 * di una riga di un JList
 */
public class IconListRenderer extends JLabel implements ListCellRenderer {
     //final ImageIcon longIcon = new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/gestioneStudenti24x22.gif"));
     //final ImageIcon shortIcon = new javax.swing.ImageIcon(getClass().getResource("/nu/mine/egoless/didattica/app/gui/img/gestioneStudenti24x22.gif"));

     // This is the only method defined by ListCellRenderer.
     // We just reconfigure the JLabel each time we're called.

     public Component getListCellRendererComponent(
       JList list,              // the list
       Object value,            // value to display
       int index,               // cell index
       boolean isSelected,      // is the cell selected
       boolean cellHasFocus)    // does the cell have focus
     {
         //String s = value.toString();
         //setText(s);
         setIcon((ImageIcon)value);
         if (isSelected) {
             setBackground(list.getSelectionBackground());
             setForeground(list.getSelectionForeground());
         } else {
             setBackground(list.getBackground());
             setForeground(list.getForeground());
         }
         setEnabled(list.isEnabled());
         setFont(list.getFont());
         setOpaque(true);
         return this;
     }     
    
}
