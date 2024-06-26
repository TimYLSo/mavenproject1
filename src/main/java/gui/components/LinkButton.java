/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import gui.MainFrame;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class LinkButton extends PanelButton {

    private Class<? extends JPanel> panelClass;



    public LinkButton(String buttonText, Class<? extends JPanel> panelClass) {
        super(buttonText);
        this.panelClass = panelClass;

        setForeground(Color.BLUE);

        addActionListener((ActionEvent e) -> {
            MainFrame parent = this.getFrameParent();
            if (parent != null) {
                try {
                    JPanel panel = panelClass.getDeclaredConstructor().newInstance();
                    parent.navigate_to_panel(panel);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
    }
}
