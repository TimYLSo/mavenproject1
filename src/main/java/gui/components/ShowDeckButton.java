/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import groupmanager.DerbyDecksManager;
import gui.MainFrame;
import gui.navigationpanels.viewDeckPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ShowDeckButton extends PanelButton{
    protected JFrame linkFrame;
    protected DerbyDecksManager decks;
  public void addLinkFrame(JFrame linkFrame){
  this.linkFrame = linkFrame;
  }
public ShowDeckButton(String deckID,DerbyDecksManager decks,JPanel originalPanel) throws NoSuchElementException, SQLException{
super(decks.getNameFromID(deckID));
setForeground(Color.BLUE);
this.decks = decks;


addActionListener((ActionEvent e) -> {
    MainFrame parent = this.getFrameParent();
    try {

        viewDeckPanel deckPanel = new viewDeckPanel(decks.getDeck(deckID),originalPanel);

        parent.navigate_to_panel(deckPanel);
    } catch (NoSuchElementException ex) {
        Logger.getLogger(ShowDeckButton.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(ShowDeckButton.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch(NullPointerException ex){ Logger.getLogger(ShowDeckButton.class.getName()).log(Level.SEVERE, null, ex);}
});
}
}
