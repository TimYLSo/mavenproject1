/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.mtgCardManager;
import database.DerbyCardDatabase;
import database.InMemoryCardDatabase;
import fileio.TextFileCardReader;
import java.io.IOException;
import models.CollectionList;
import models.Deck;

/**
 *
 * @author User
 */
public class CardReaderTesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String path = "src/files/oracle-cards-20240412210228.json";
//         path = "src/files/minidatabase.json";
        DerbyCardDatabase db = new DerbyCardDatabase();
        String file = "C:\\Users\\User\\Documents\\school\\2024\\pdc\\assignment\\MtgCardManager\\mavenproject1\\src\\files\\Decks\\DomainZoo.txt";
        String secondFile = "src/files/collection.txt";
//        Deck deck = TextFileCardReader.readFile("src/files/DargoThras.txt");
            TextFileCardReader cardreader = new TextFileCardReader(file);
           Deck deck = cardreader.readDeckFromFile();
           deck.updateDeckOracleInfo(db);
           deck.setDeckName("domain zoo");
        System.out.print(deck);

    }
    
}
