/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.fileio;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author User
 */
public class FileExporter extends JFileChooser {

    public FileExporter() {
        super();
        this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    public File getSelectedDirectory() {
        int result = this.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return this.getSelectedFile();
        } else {
            return null;
        }
    }
}
