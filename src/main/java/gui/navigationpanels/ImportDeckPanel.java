/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.navigationpanels;

import com.mycompany.mtgCardManager.MtgCardManager;
import fileio.CSVCardReader;
import fileio.TextFileCardReader;
import groupmanager.DerbyCollectionsManager;
import groupmanager.DerbyDecksManager;
import gui.MainMenu;
import gui.basePanels.UploadsPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.CollectionList;
import models.Deck;

/**
 *
 * @author User
 */
public class ImportDeckPanel extends UploadsPanel {

    private final JTextField fileField;
    private final JTextField nameField;
    private final JButton chooseFolderButton;
    private final JButton saveButton;
    JPanel main = this.getMainPanel();

    public ImportDeckPanel() {

        JPanel folderPanel = new JPanel(new FlowLayout());
        JLabel folderLabel = new JLabel("Folder:");
        fileField = new JTextField(20);
        chooseFolderButton = new JButton("Choose...");
        chooseFolderButton.addActionListener((ActionEvent e) -> {
            chooseFolderActionPerformed(e);
        });
        folderPanel.add(folderLabel);
        folderPanel.add(fileField);
        folderPanel.add(chooseFolderButton);

        JPanel filePanel = new JPanel(new FlowLayout());
        JLabel fileLabel = new JLabel("File Name:");
        nameField = new JTextField(20);
        filePanel.add(fileLabel);
        filePanel.add(nameField);

        saveButton = new JButton("Select Folder");
        saveButton.addActionListener((ActionEvent e) -> {
            setFields(e);
        });

        // Add components to panel
        main.setLayout(new FlowLayout());
        main.add(folderPanel);
        main.add(filePanel);
        main.add(saveButton);
    }

    private void chooseFolderActionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT files", "txt");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            fileField.setText(selectedFolder.getAbsolutePath());
        }
    }

    private void setFields(ActionEvent e) {
        String path = fileField.getText();
        String name = nameField.getText();

        if (path.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No file selected.");
            return;
        } else if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No name entered.");
        }

        File file = new File(path);
        if (file.exists() && file.isFile() && file.getName().endsWith(".txt")) {
            try {
                // Perform your action with the selected file and name
                System.out.println("txt file selected: " + path);
                System.out.println("Name entered: " + name);
                TextFileCardReader reader = new TextFileCardReader(path);
                Deck deck = reader.readDeckFromFile();
                deck.setDeckName(name);
                DerbyDecksManager manager = MtgCardManager.allDecks;
                deck.updateDeckOracleInfo(MtgCardManager.database);
                manager.saveDeck(deck);
                JOptionPane.showMessageDialog(this, "Operation Complete.");
                JPanel returnPanel = new MainMenu();
                MtgCardManager.menu.navigate_to_panel(returnPanel);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "There is a problem with the formatting of the txt file. The file cannot be imported");
                Logger.getLogger(ImportListPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "There is a problem adding the deck to the database");
                Logger.getLogger(ImportListPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Selected file is not a valid CSV file.");
        }

    }
}
