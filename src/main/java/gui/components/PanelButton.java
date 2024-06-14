/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import gui.MainFrame;
import java.awt.Container;
import javax.swing.JButton;

/**
 *
 * @author User
 */
public class PanelButton extends JButton {

    public PanelButton(String text) {
        super(text);
    }

    public MainFrame getFrameParent() {
        Container parent = this.getParent();
        while (parent != null && !(parent instanceof MainFrame)) {
            parent = parent.getParent();
        }
        if (parent instanceof MainFrame) {
            return (MainFrame) parent;
             
        }
        return null;
    }
}
