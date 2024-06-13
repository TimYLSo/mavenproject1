/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cui.m;

import fileio.CSVCardReader;
import models.CollectionList;
import groupmanager.CollectionsManager;
import com.mycompany.mtgCardManager.MtgCardManager;
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
            CollectionsManager manager = MtgCardManager.collections;
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
            CollectionsManager manager = MtgCardManager.collections;
            manager.saveList(list);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
     
     
     }
}
