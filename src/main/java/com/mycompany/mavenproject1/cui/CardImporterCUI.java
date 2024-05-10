/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.cui;

import com.mycompany.mavenproject1.Mavenproject1;
import com.mycompany.mavenproject1.fileio.CSVCardReader;
import com.mycompany.mavenproject1.models.CollectionList;
import com.mycompany.mavenproject1.models.groupmanager.CollectionsManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class CardImporterCUI {

    public static void ImportCards(String path, int i, Scanner s) throws FileNotFoundException {
        CSVCardReader reader = new CSVCardReader(path);
        try {
            int listIndex = i-1;
            CollectionList list = reader.readListFromFile("anonymousList");
            CollectionsManager manager = Mavenproject1.collections;
            manager.addList(list, listIndex);
            CollectionList currentList = manager.getAllLists().get(listIndex);
            manager.saveList(currentList);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }


public static void ImportCards(String path,String listName, Scanner s)throws FileNotFoundException{
    CSVCardReader reader = new CSVCardReader(path);         
    try {
            
            CollectionList list = reader.readListFromFile(listName);
            CollectionsManager manager = Mavenproject1.collections;
            manager.saveList(list);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
     
     
     }
}
