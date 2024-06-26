package gui.components;

import gui.basePanels.ExportPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import models.Deck;
// class generated by ChatGPT
public class ChooseFolderPanel extends JPanel {
    
    private JTextField folderField;
    private JTextField fileField;
    private JButton chooseFolderButton;
    private JButton saveButton;
    private Deck deckToWrite;
    private ExportPanel parentPanel;
    
    public ChooseFolderPanel(ExportPanel parentPanel) {
       
        
        // Components
        this.parentPanel = parentPanel;
        JPanel folderPanel = new JPanel(new BorderLayout());
        JLabel folderLabel = new JLabel("Folder:");
        folderField = new JTextField(20);
        chooseFolderButton = new JButton("Choose...");
        chooseFolderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseFolderActionPerformed(e);
            }
        });
        folderPanel.add(folderLabel, BorderLayout.WEST);
        folderPanel.add(folderField, BorderLayout.CENTER);
        folderPanel.add(chooseFolderButton, BorderLayout.EAST);
        
        JPanel filePanel = new JPanel(new BorderLayout());
        JLabel fileLabel = new JLabel("File Name:");
        fileField = new JTextField(20);
        filePanel.add(fileLabel, BorderLayout.WEST);
        filePanel.add(fileField, BorderLayout.CENTER);
        
        saveButton = new JButton("Select Folder");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFields(e);
            }
        });
        
        // Add components to panel
        add(folderPanel);
        add(filePanel);
        add(saveButton);
    }
    
    private void chooseFolderActionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            folderField.setText(selectedFolder.getAbsolutePath());
        }
    }
    
    private void setFields(ActionEvent e) {
        String folderPath = folderField.getText();
        String fileName = fileField.getText();
        
        if (folderPath.isEmpty() || fileName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a folder and enter a file name.");
            return;
        }
        
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            JOptionPane.showMessageDialog(this, "Selected folder does not exist or is not a directory.");
        }
        parentPanel.FolderChosen(folderPath,fileName);

    }
    

}
