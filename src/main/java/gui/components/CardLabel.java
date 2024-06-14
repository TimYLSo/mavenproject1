/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import database.DerbyCardDatabase;
import gui.navigationpanels.viewDeckPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import models.OracleCard;

/**
 *
 * @author User
 */
public class CardLabel extends JLabel {

    protected OracleCard card;

    public CardLabel(DerbyCardDatabase db, String CardID, Integer quantity, viewDeckPanel panel) {
        super(quantity.toString() + "   " + db.findCardByOracleID(CardID).getCardName());
        card = db.findCardByOracleID(CardID);

        // Add a mouse listener to handle hover events
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Action on hover (mouse enters)
                onHover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Action on hover end (mouse exits)
                onHoverEnd();
            }

            private void onHover() {
                
                String details = "<html>Card Name: " +card.getCardName()+"<br>"
                        + "<html>Mana Cost: " +card.getManaCost()+"<br>"
                        + "<html>Set Code: " +card.getSetCode()+"<br>"
                        + "<html>Set: " +card.getSetName()+"<br>"
                        + "<html>Card Oracle Text: " +card.getOracleText()+"<br>"
                        + "</html>";
                panel.updateCardViewer(card);
            }

            private void onHoverEnd() {
                
            }
        });
    }
}
