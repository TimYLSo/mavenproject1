/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.basePanels;

import gui.LinkButton;
import gui.MainMenu;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class AppPanel extends JPanel {

    LinkButton returnButton = new LinkButton("Return To Menu", MainMenu.class);

    public AppPanel() {
        add(returnButton);
    }
}
