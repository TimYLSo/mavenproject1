/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import fileio.DeckWriter;
import fileio.ListWriter;
import groupmanager.DerbyCollectionsManager;
import groupmanager.DerbyDecksManager;
import gui.MainFrame;
import gui.MainMenu;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.CollectionList;
import models.Deck;

/**
 *
 * @author User
 */
public class ExportListButton extends PanelButton {

    public ExportListButton(String id, String filePath, String listName, DerbyCollectionsManager lists) throws SQLException {
        super(lists.getList(id).getCollectionName());
        addActionListener((ActionEvent e) -> {
            MainFrame parent = this.getFrameParent();
            try {
                CollectionList list = lists.getList(id);
                System.out.print(filePath);
                ListWriter writer = new ListWriter(filePath, list);
                writer.writeFileAsCSV(listName, true);
                JPanel returnPanel = new MainMenu();
                JOptionPane.showMessageDialog(this, "Operation Complete.");
                parent.navigate_to_panel(returnPanel);
            } catch (NoSuchElementException ex) {
                Logger.getLogger(ShowDeckButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "There is a problem with the database");
                Logger.getLogger(ShowDeckButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {
                Logger.getLogger(ShowDeckButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "There is a problem with locating the file");
                Logger.getLogger(ExportDeckButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "There is a problem with locating the file");
                Logger.getLogger(ExportListButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex);
                Logger.getLogger(ExportListButton.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }
}
