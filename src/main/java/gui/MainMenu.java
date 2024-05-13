/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author User
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    private JPanel userPanel = new JPanel();
    private JPanel calcPanel = new JPanel();
    private JLabel uName = new JLabel("Username: ");
    private JLabel pWord = new JLabel("Password: ");
    private JTextField unInput = new JTextField(10);
    private JTextField pwInput = new JTextField(10);
    private JTextField usernameField;
    private JPasswordField passwordField;

    public MainMenu() {
        setTitle("Main menu");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        usernameField.setColumns(100);

        JButton ViewDecksButton = new JButton("View Decks");
        JButton ExportDecksButton = new JButton("Export Decks");
        JButton ImportDecksButton = new JButton("Import Decks");
        JButton CreateDeckButton = new JButton("Create Deck");
        JButton ImportListButton = new JButton("Import List");
        JButton ExportListButton = new JButton("Export List");

        buttonPanel.add(ViewDecksButton);
        buttonPanel.add(ExportDecksButton);
        buttonPanel.add(ImportDecksButton);
        buttonPanel.add(ImportListButton);
        buttonPanel.add(ExportListButton);
        buttonPanel.add(CreateDeckButton);

        add(buttonPanel);
        Frame[] frames = Frame.getFrames();

    }
}
