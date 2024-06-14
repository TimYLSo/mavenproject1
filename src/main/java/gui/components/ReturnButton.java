/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import gui.MainFrame;
import gui.navigationpanels.viewDeckPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ReturnButton extends PanelButton {

    public ReturnButton(String buttonText, JPanel returnPanel) {
        super(buttonText);

        setForeground(Color.BLUE);
        addActionListener((ActionEvent e) -> {
            MainFrame parent = this.getFrameParent();

            parent.navigate_to_panel(returnPanel);

        });

    }
}
