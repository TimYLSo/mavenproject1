/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.navigationpanels;

import com.mycompany.mtgCardManager.MtgCardManager;
import groupmanager.DecksManager;
import gui.basePanels.AppPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import models.Deck;

/**
 *
 * @author User
 */
public class ViewDecksPanel extends AppPanel {
    protected JTable deckList;
    protected JPanel decksPanel= new JPanel();
    public ViewDecksPanel(){
    super();

      decksPanel.setLayout(new BoxLayout(decksPanel, BoxLayout.Y_AXIS));
    DecksManager manager = MtgCardManager.allDecks;
    ArrayList<Deck> decks = manager.getAllDecks();
            for (Deck element : decks) {
            String deckName = element.getDeckName();
            decksPanel.add(new JButton(deckName));
            
            
            }
        JPanel mainPanel = getMainPanel();
    mainPanel.add(decksPanel,BorderLayout.CENTER);
    
    }
}
