/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.basePanels;

import gui.buttons.LinkButton;
import gui.MainMenu;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author User
 */
public class AppPanel extends JPanel {

    LinkButton returnButton = new LinkButton("Return To Menu", MainMenu.class);
JPanel topPanel = new JPanel();
 JPanel mainPanel = new JPanel();
 protected String titleMessage = "Hi this is a panel";
    public AppPanel() {
           setLayout(new BorderLayout(0,30));
           setBorder(new EmptyBorder(0, 0, 30, 30));
           JLabel title = new JLabel(titleMessage);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(returnButton, BorderLayout.WEST);
        topPanel.add(title,BorderLayout.SOUTH);
         mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        
    }
    public JPanel getMainPanel(){
    return mainPanel;
    }
}
