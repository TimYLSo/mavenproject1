/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.navigationpanels;

import gui.basePanels.AppPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class ViewDecksPanel extends AppPanel {
    protected JTable deckList = new JTable();
    public ViewDecksPanel(){
    super();
    this.add(deckList);
    this.add(new JTextField("dasfsdsdfs"));
    this.setVisible(true);
    }
}
