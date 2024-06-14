/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import gui.components.LinkButton;
import gui.components.QuitButton;
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

    LinkButton ViewDecksButton = new LinkButton("View Decks",ViewAllDecksPanel.class);
    JButton ExportDecksButton = new LinkButton("Export Decks",ExportDecksPanel.class);
    JButton ImportDecksButton = new LinkButton("Import Decks",ImportDeckPanel.class);
    JButton ViewListsButton = new LinkButton("View Lists",ViewAllListsPanel.class);
    JButton ImportListButton = new LinkButton("Import List",ImportListPanel.class);
    JButton ExportListButton = new LinkButton("Export List",ExportListPanel.class);
    JLabel IntroMessage = new JLabel("Welcome to the mtgCardManager.");

    public MainMenu() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        
        panel1.add(IntroMessage);
        JPanel newpan = new JPanel();
        newpan.setSize(30, 30);
        panel1.add(newpan);
        panel1.add(new JLabel("Click here to view all the decks in the manager"));
        panel1.add(ViewDecksButton);
        panel1.add(new JLabel("Click here to export a deck to a txt file"));
        panel1.add(ExportDecksButton);
        panel1.add(new JLabel("Click here to import a deck into the database from a txt file"));
        panel1.add(ImportDecksButton);
        panel1.add(new JLabel("Click here to import a list into the database from a csv file"));
        panel1.add(ImportListButton);
        panel1.add(new JLabel("Click here to export a deck to a csv file"));
        panel1.add(ExportListButton);
        panel1.add(new JLabel("Click here to view all the lists in the manager"));
        panel1.add(ViewListsButton);
        panel1.add(new QuitButton());
        this.add(panel1);
        
    }

}
