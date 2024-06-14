/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import fileio.DeckWriter;
import groupmanager.DerbyDecksManager;
import gui.MainFrame;
import gui.MainMenu;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.Deck;

/**
 *
 * @author User
 */
public class ExportDeckButton extends PanelButton {

    public ExportDeckButton(String id,String filePath, String deckName,DerbyDecksManager decks) throws SQLException {
        super(decks.getDeck(id).getDeckName());
        addActionListener((ActionEvent e) -> {
    MainFrame parent = this.getFrameParent();
    try {
        Deck deck = decks.getDeck(id);
        System.out.print(filePath);
        DeckWriter writer = new DeckWriter(filePath,deck);
        writer.writeFileAsTextFile(deckName, true);
        JPanel returnPanel = new MainMenu();
        JOptionPane.showMessageDialog(this, "Operation Complete.");
        parent.navigate_to_panel(returnPanel);
    } catch (NoSuchElementException ex) {
        Logger.getLogger(ShowDeckButton.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(ShowDeckButton.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch(NullPointerException ex){ Logger.getLogger(ShowDeckButton.class.getName()).log(Level.SEVERE, null, ex);} catch (FileNotFoundException ex) {
                Logger.getLogger(ExportDeckButton.class.getName()).log(Level.SEVERE, null, ex);
            }
});

    }

}
