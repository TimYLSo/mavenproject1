/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.database.JSONCardDatabase;
import com.mycompany.mavenproject1.fileio.CSVCardReader;
import com.mycompany.mavenproject1.fileio.ListWriter;
import com.mycompany.mavenproject1.fileio.TextFileCardReader;
import com.mycompany.mavenproject1.models.CollectionList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class csvCardReaderTesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                String path = "src/files/oracle-cards-20240412210228.json";
//         path = "src/files/minidatabase.json";
        JSONCardDatabase db = new JSONCardDatabase(path);
        String file = "C:\\Users\\User\\Documents\\school\\2024\\pdc\\assignment\\decks\\DomainZoo.txt";
        String secondFile = "src/files/collection2.csv";
//        Deck deck = TextFileCardReader.readFile("src/files/DargoThras.txt");
//            TextFileCardReader cardreader = new TextFileCardReader(file);
//           Deck deck = cardreader.readDeckFromFile();
//           deck.updateDeckOracleInfo(db);
//        System.out.print(deck);
        CSVCardReader cardreader = new CSVCardReader(secondFile);
        
        try {
            CollectionList list;
            list = cardreader.readListFromFile("list1");
        list.updateDeckOracleInfo(db);
        System.out.print(list);
        ListWriter listWriter = new ListWriter("src/files/",list);
        listWriter.writeFileAsCSV(list.getCollectionName(),true);
        } catch (IOException ex) {
            Logger.getLogger(csvCardReaderTesting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
