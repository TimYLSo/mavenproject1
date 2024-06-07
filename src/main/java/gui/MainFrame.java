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

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Main");
        setSize(1500, 1500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel optionsPanel = new MainMenu();

        add(optionsPanel);

    }

    public void navigate_to_panel(JPanel panel) {
    this.getContentPane().removeAll(); 
    this.add(panel);                   
    this.revalidate();                
    this.repaint();    
    }
    
}
