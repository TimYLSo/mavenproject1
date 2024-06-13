/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.basePanels;

import gui.fileio.FileExporter;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author User
 */
public class ExportPanel extends AppPanel {
        protected String filePath;
            JFileChooser chooser = new FileExporter();
            File file = chooser.getSelectedFile();
    public ExportPanel() {
        add(chooser);
    }
}
