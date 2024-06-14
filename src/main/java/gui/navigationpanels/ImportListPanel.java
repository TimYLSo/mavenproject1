/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.navigationpanels;

import gui.basePanels.UploadsPanel;
import gui.components.ChooseFilePanel;
import gui.components.ChooseFolderPanel;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class ImportListPanel extends UploadsPanel {
            main = this.getMainPanel();
        main.add(new JLabel("Please select the Location of the exported file and the name"));
        ChooseFilePanel panel = new ChooseFilePanel();
        main.add(panel);
}
