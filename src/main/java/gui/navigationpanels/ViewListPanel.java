/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.navigationpanels;

import com.mycompany.mtgCardManager.MtgCardManager;
import gui.basePanels.AppPanel;
import gui.components.CardLabel;
import gui.components.CardTableModel;
import gui.components.ReturnButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import models.Card;
import models.CollectionList;
import models.Deck;
import models.OracleCard;

/**
 *
 * @author User
 */
public class ViewListPanel extends AppPanel {
     protected JPanel listViewer;

    public ViewListPanel(CollectionList list, JPanel originalPanel) {
        super();
        JLabel deckNameLabel = new JLabel("Deck Name: " + list.getCollectionName());
        addButtonToNavPanel(new ReturnButton("return to lists",originalPanel));
        JPanel mainPanel = getMainPanel();
        listViewer = new JPanel();

        mainPanel.add(deckNameLabel, BorderLayout.NORTH);

        JScrollPane deckViewerscrollPane = new JScrollPane(listViewer);


        try {
            viewList(list);
        } catch (NoSuchElementException ex) {
            Logger.getLogger(viewDeckPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(viewDeckPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPanel.setLayout(new FlowLayout());

        mainPanel.add(listViewer, BorderLayout.CENTER);
        
    }

    private void viewList(CollectionList list) throws NoSuchElementException, SQLException {
        ArrayList<Card> cardList = list.getCardList();


        CardTableModel model = new CardTableModel(cardList);

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        listViewer.add(scrollPane);
        

    }
}
