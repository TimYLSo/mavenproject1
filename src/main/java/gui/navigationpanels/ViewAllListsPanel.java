/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.navigationpanels;

import com.mycompany.mtgCardManager.MtgCardManager;
import groupmanager.DerbyCollectionsManager;
import groupmanager.DerbyDecksManager;
import gui.basePanels.AppPanel;
import gui.components.ShowDeckButton;
import gui.components.ShowListButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author User
 */
public class ViewAllListsPanel extends AppPanel {
    protected JPanel decksPanel = new JPanel();
        public ViewAllListsPanel() {
        super();
        this.titleMessage = "<html>Here you can view all of the Lists currently stored in the database, <br>"
                + "Click on the deck to view the deck, or delete to delete the Lists.</html>";
        JLabel title = new JLabel(titleMessage);

        try {
            decksPanel.setLayout(new BoxLayout(decksPanel, BoxLayout.Y_AXIS));
            DerbyCollectionsManager manager = MtgCardManager.collections;
            ArrayList<String> listsID = manager.getAllLists();
            for (String id : listsID) {
                String listName = manager.getList(id).getCollectionName();

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
                Dimension currentSize = buttonPanel.getPreferredSize();
                int newHeight = 20;
                Dimension newSize = new Dimension(currentSize.width, newHeight);
                buttonPanel.setPreferredSize(newSize);
                buttonPanel.add(new ShowListButton(id,manager,this));
                JButton deleteButton = new JButton("delete");
                buttonPanel.add(deleteButton);
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            // Action to be performed when the button is clicked
                            manager.deleteList(id);
                            buttonPanel.removeAll();
                            decksPanel.revalidate();
                            decksPanel.repaint();
                        } catch (SQLException ex) {
                            Logger.getLogger(ViewAllDecksPanel.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(decksPanel, "There is a problem with the formatting of the CSV file.");
                        }
                    }
                });
                decksPanel.add(buttonPanel);

            }
            JPanel mainPanel = getMainPanel();
            mainPanel.add(title, BorderLayout.NORTH);
            JScrollPane scrollPane = new JScrollPane(decksPanel);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
        } catch (SQLException ex) {
            Logger.getLogger(ViewAllDecksPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
