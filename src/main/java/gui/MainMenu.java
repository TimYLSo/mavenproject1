/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import gui.buttons.LinkButton;
import gui.navigationpanels.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class MainMenu extends JPanel {

    LinkButton ViewDecksButton = new LinkButton("View Decks",ViewDecksPanel.class);
    JButton ExportDecksButton = new LinkButton("Export Decks",ExportDecksPanel.class);
    JButton ImportDecksButton = new LinkButton("Import Decks",UploadDecksPanel.class);
    JButton CreateDeckButton = new LinkButton("Create Deck",CreateDeckPanel.class);
    JButton ImportListButton = new LinkButton("Import List",ImportListPanel.class);
    JButton ExportListButton = new LinkButton("Export List",ExportListPanel.class);
    JLabel IntroMessage = new JLabel("Welcome to the mtgCardManager.");

    public MainMenu() {
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(IntroMessage);
        this.add(ViewDecksButton);
        this.add(ExportDecksButton);
        this.add(ImportDecksButton);
        this.add(ImportListButton);
        this.add(ExportListButton);
        this.add(CreateDeckButton);
    }

}
