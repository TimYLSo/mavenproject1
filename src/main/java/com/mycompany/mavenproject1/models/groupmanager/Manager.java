/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.models.groupmanager;

/**
 *
 * @author User
 */
abstract class Manager {
    protected String pathToFile;
    protected String currentSavePath;

    public String getPathToFile() {
        return pathToFile;
    }
    
    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }
    public Manager(String pathToFile){
    this.pathToFile = pathToFile;
    }
}
