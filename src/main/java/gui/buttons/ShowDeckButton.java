/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.buttons;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class ShowDeckButton extends JButton{
    protected JFrame linkFrame;
  public void addLinkFrame(JFrame linkFrame){
  this.linkFrame = linkFrame;
  }
public ShowDeckButton(String buttonText){
super(buttonText);
setForeground(Color.BLUE);

addActionListener((ActionEvent e) -> {
    // When the button is clicked, create and show the new window
    JFrame newFrame = new JFrame("New Window");
    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close to only close this window
    newFrame.setSize(300, 200);
    newFrame.setVisible(true);
});
}
}
