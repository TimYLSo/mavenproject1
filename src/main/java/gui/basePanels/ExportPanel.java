/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.basePanels;

import gui.components.ChooseFolderPanel;
import gui.fileio.FileExporter;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author User
 */
public class ExportPanel extends AppPanel {

    protected String folderPath;
    protected String fileName;

    public ExportPanel() {

    }

    public void FolderChosen(String folderPath, String fileName) {
        this.fileName = fileName;
        char lastChar = folderPath.charAt(folderPath.length() - 1);
        if ((lastChar == '\\')|| (lastChar == '/')){this.folderPath = folderPath;}
        else{this.folderPath = folderPath+"\\";}
        
        
    }
}
