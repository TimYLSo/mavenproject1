/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.navigationpanels;

import com.mycompany.mtgCardManager.MtgCardManager;
import groupmanager.DerbyCollectionsManager;
import gui.basePanels.AppPanel;
import gui.basePanels.ExportPanel;
import gui.components.ChooseFolderPanel;
import gui.components.ExportListButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author User
 */
public class ExportListPanel extends ExportPanel {

 protected JPanel main;

    public ExportListPanel() {
        main = this.getMainPanel();
        main.add(new JLabel("Please select the Location of the exported file and the name"));
        ChooseFolderPanel panel = new ChooseFolderPanel(this);
        main.add(panel);
    }

    @Override
    public void FolderChosen(String folderPath, String fileName) {
        super.FolderChosen(folderPath, fileName);
        JPanel deckSelector = new JPanel();
        try {
            deckSelector.setLayout(new BoxLayout(deckSelector, BoxLayout.Y_AXIS));
            DerbyCollectionsManager manager = MtgCardManager.collections;
            ArrayList<String> listsID = manager.getAllLists();
            for (String id : listsID) {
                String collectionName = manager.getList(id).getCollectionName();

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
                Dimension currentSize = buttonPanel.getPreferredSize();
                int newHeight = 20;
                Dimension newSize = new Dimension(currentSize.width, newHeight);
                buttonPanel.setPreferredSize(newSize);
                ExportListButton button = new ExportListButton(id,this.folderPath,fileName ,manager);
                buttonPanel.add(button);
                deckSelector.add(buttonPanel);

            }
            JScrollPane scrollPane = new JScrollPane(deckSelector);
            main.add(new JLabel("please select a deck to be exported"));
            main.add(scrollPane, BorderLayout.CENTER);

            scrollPane.revalidate();
            scrollPane.repaint();
            main.revalidate();
            main.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(ViewAllDecksPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
