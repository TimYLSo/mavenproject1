/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.basePanels;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author User
 */
public class UploadsPanel extends AppPanel {
    protected String filePath;
            JFileChooser chooser = new JFileChooser();
            File file = chooser.getSelectedFile();
    public UploadsPanel() {
        add(chooser);
    }
    
}
