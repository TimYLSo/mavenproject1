/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.navigationpanels;

import com.mycompany.mtgCardManager.MtgCardManager;
import groupmanager.DecksManager;
import groupmanager.DerbyDecksManager;
import gui.basePanels.AppPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import models.Deck;

/**
 *
 * @author User
 */
public class ViewAllDecksPanel extends AppPanel {

    protected JTable deckList;
    protected JPanel decksPanel = new JPanel();

    public ViewAllDecksPanel() {
        super();
        this.titleMessage ="<html>Here you can view all of the decks currently stored in the database, <br>" +
"Click on the deck to view the deck, or delete to delete the deck.</html>";
        JLabel title = new JLabel(titleMessage); 
        
        try {
            decksPanel.setLayout(new BoxLayout(decksPanel, BoxLayout.Y_AXIS));
            DerbyDecksManager manager = MtgCardManager.allDecks;
            ArrayList<String> decksID = manager.getAllDecks();
            for (String id : decksID) {
                String deckName = manager.getDeck(id).getDeckName();

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
                Dimension currentSize = buttonPanel.getPreferredSize();
                int newHeight = 20;
                Dimension newSize = new Dimension(currentSize.width, newHeight);
                buttonPanel.setPreferredSize(newSize);
                buttonPanel.add(new JButton(deckName));
                buttonPanel.add(new JButton("delete"));
                decksPanel.add(buttonPanel);

            }
            JPanel mainPanel = getMainPanel();
            mainPanel.add(title,BorderLayout.NORTH);
            JScrollPane scrollPane = new JScrollPane(decksPanel);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
        } catch (SQLException ex) {
            Logger.getLogger(ViewAllDecksPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
