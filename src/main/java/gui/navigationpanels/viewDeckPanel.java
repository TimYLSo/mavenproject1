/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.navigationpanels;

import com.mycompany.mtgCardManager.MtgCardManager;
import gui.basePanels.AppPanel;
import gui.components.CardLabel;
import gui.components.ShowDeckButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.sql.SQLException;
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
import javax.swing.JTextArea;
import models.Deck;
import models.OracleCard;

/**
 *
 * @author User
 */
public class viewDeckPanel extends AppPanel {

    protected JPanel deckViewer;
    protected JPanel cardViewer;

    public viewDeckPanel(Deck deck, JPanel originalPanel) {
        super();
        JLabel deckNameLabel = new JLabel("Deck Name: " + deck.getDeckName());

        JPanel mainPanel = getMainPanel();
        cardViewer = new JPanel();
        deckViewer = new JPanel();
        cardViewer.setPreferredSize(new Dimension(200, 200));

        deckViewer.setLayout(new BoxLayout(deckViewer, BoxLayout.Y_AXIS));
        cardViewer.setLayout(new BoxLayout(cardViewer, BoxLayout.Y_AXIS));
        mainPanel.add(deckNameLabel, BorderLayout.NORTH);

        JScrollPane cardViewerscrollPane = new JScrollPane(cardViewer);
        JScrollPane deckViewerscrollPane = new JScrollPane(deckViewer);
        deckViewerscrollPane.setPreferredSize(new Dimension(500, 300));

        try {
            viewDeck(deck);
        } catch (NoSuchElementException ex) {
            Logger.getLogger(viewDeckPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(viewDeckPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPanel.setLayout(new FlowLayout());

        mainPanel.add(cardViewerscrollPane, BorderLayout.EAST);
        mainPanel.add(deckViewerscrollPane, BorderLayout.CENTER);
    }

    private void viewDeck(Deck deck) throws NoSuchElementException, SQLException {
        HashMap<OracleCard, Integer> mainDeck = deck.getDeckContents();
        HashMap<OracleCard, Integer> sideboard = deck.getSideboard();
        Set maindeckset = mainDeck.keySet();
        Iterator<OracleCard> maindeckIterator = maindeckset.iterator();
        while (maindeckIterator.hasNext()) {
            OracleCard card = maindeckIterator.next();
            Integer quantity = mainDeck.get(card);
            String id = card.getScryfallID();
            CardLabel cardLabel = new CardLabel(MtgCardManager.database, id, quantity, this);
            deckViewer.add(cardLabel);

        }
        deckViewer.add(new JLabel("Sideboard:"));
    }

    public void updateCardViewer(OracleCard card) {
        cardViewer.removeAll();
        String details = "Card Name: " + card.getCardName()+"\n"+
        "Mana Cost: " + card.getManaCost()+"\n"+
        "Set Code: " + card.getSetCode()+"\n"+
        "Set: " + card.getSetName()+"\nCard Oracle Text: \n";
        JTextArea textArea = new JTextArea();
        textArea.setText(details+card.getOracleText()+"\n");
        textArea.setWrapStyleWord(true); // Wrap at word boundaries
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        cardViewer.add(textArea);
        cardViewer.revalidate();
        cardViewer.repaint();
    }

}
