/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.navigationpanels;

import gui.basePanels.AppPanel;
import gui.basePanels.ExportPanel;
import gui.components.ChooseFolderPanel;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ExportListPanel extends ExportPanel {

    public ExportListPanel() {
        JPanel main = this.getMainPanel();
        main.add(new JLabel("Please select the Location of the exported file and the name"));
        ChooseFolderPanel panel = new ChooseFolderPanel(this);
        main.add(panel);
    }

    @Override
    public void FolderChosen(String folderPath, String fileName) {
    }
}
