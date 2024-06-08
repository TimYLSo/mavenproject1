/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.navigationpanels;

import gui.basePanels.AppPanel;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ExportListPanel extends AppPanel {
    public ExportListPanel(){
        JPanel main = this.getMainPanel();
    main.add(new JLabel("Please select the list to export"));
    }
}
