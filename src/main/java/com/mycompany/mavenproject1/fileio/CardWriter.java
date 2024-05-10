/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.fileio;

/**
 *
 * @author User
 */
abstract class CardWriter {
        protected String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    
    public CardWriter(String filePath,Object objectToBeWritten){
    this.filePath = filePath;
    }
}
