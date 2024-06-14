/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import groupmanager.DerbyCollectionsManager;
import groupmanager.DerbyDecksManager;
import gui.MainFrame;
import gui.navigationpanels.ViewListPanel;
import gui.navigationpanels.viewDeckPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ShowListButton extends PanelButton {

    protected JFrame linkFrame;
    protected DerbyCollectionsManager lists;

    public void addLinkFrame(JFrame linkFrame) {
        this.linkFrame = linkFrame;
    }

    public ShowListButton(String listID, DerbyCollectionsManager lists, JPanel originalPanel) throws NoSuchElementException, SQLException {
        super(lists.getList(listID).getCollectionName());
        setForeground(Color.BLUE);
        this.lists = lists;

        addActionListener((ActionEvent e) -> {
            MainFrame parent = this.getFrameParent();
            try {

                ViewListPanel deckPanel = new ViewListPanel(lists.getList(listID), originalPanel);

                parent.navigate_to_panel(deckPanel);
            } catch (NoSuchElementException ex) {
                Logger.getLogger(ShowDeckButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ShowDeckButton.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {
                Logger.getLogger(ShowDeckButton.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
