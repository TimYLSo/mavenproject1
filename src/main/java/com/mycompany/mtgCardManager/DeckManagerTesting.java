/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.mtgCardManager;

import database.DerbyCardDatabase;
import database.DerbyDatabase;
import fileio.TextFileCardReader;
import groupmanager.DerbyDecksManager;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import models.Deck;
import models.OracleCard;

/**
 *
 * @author User
 */
public class DeckManagerTesting {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException, IOException {
        //DerbyDatabase dbManager = new DerbyDatabase();
        DerbyDatabase dbManager = new DerbyDatabase();
        DerbyCardDatabase cardDB = new DerbyCardDatabase(dbManager);
        DerbyDecksManager decksManager = new DerbyDecksManager(cardDB);
        ResultSet rs = dbManager.myQuery("Select * from DECKS");
        ResultSet rs2 = dbManager.myQuery("Select * from DECKCARDS");
        //cardDB.updateDatabase("src/files/oracle-cards-20240412210228.json");
        //If it's an online database: You will find: org.apache.derby.client.net.NetConnection40@7fbe847c
        //That means: Connection conn = new NetConnection(); 
        //If it's an embedded database: You will find: org.apache.derby.impl.jdbc.EmbedConnection40@681384962 (XID = 16), (SESSIONID = 1), (DATABASE = CarDB), (DRDAID = null) 
        //That means: Connection conn = new EmbedConnection40(); 
cardDB.getConnection().commit();
        String file = "C:\\Users\\User\\Documents\\school\\2024\\pdc\\assignment\\MtgCardManager\\mavenproject1\\src\\files\\Decks\\DargoThrasios.txt";
            TextFileCardReader cardreader = new TextFileCardReader(file);
           Deck deck = cardreader.readDeckFromFile();
           deck.updateDeckOracleInfo(cardDB);
           deck.setDeckName("Darg Thras");
//        try {
//           decksManager.saveDeck(deck);
//        } catch (NoSuchElementException e) {
//        }

        DatabaseMetaData dbmd = cardDB.getConnection().getMetaData();
        System.out.println("Database product name : " + dbmd.getDatabaseProductName());
        System.out.println("Database version : " + dbmd.getDatabaseProductVersion());
        System.out.println("URL: " + dbmd.getURL());
        System.out.println("driver name: " + dbmd.getDriverName());
        System.out.println("driver version: " + dbmd.getDriverVersion());
        ResultSetMetaData rsmd = rs.getMetaData();
        int rowCount = 0;
        while (rs.next()) {
            rowCount++;
        }
        System.out.println("rs n:" + rowCount);
        System.out.println("Number of Columns:" + rsmd.getColumnCount());
        System.out.println("Table Name (column 2):" + rsmd.getTableName(2));
        System.out.println("Data Type (column 2):" + rsmd.getColumnType(2));
         rowCount = 0;
        while (rs2.next()) {
            rowCount++;
        }
        rsmd = rs2.getMetaData();
        System.out.println("rs n:" + rowCount);
        System.out.println("Number of Columns:" + rsmd.getColumnCount());
        System.out.println("Table Name (column 2):" + rsmd.getTableName(2));
        System.out.println("Data Type (column 2):" + rsmd.getColumnType(2));
        System.out.println(cardDB.getConnection().getSchema());
        System.out.println(decksManager.getAllDecks());
        ArrayList<String> decks = decksManager.getAllDecks();
        for (String i : decks)

            // Printing the elements of ArrayList
        {System.out.println(decksManager.getDeck(i).getDeckName());}
//        System.out.println(decksManager.getDeck("28af4dca-97c8-4e3b-a6c8-09b031e3d47c").getDeckContents());
//        System.out.println(decksManager.getDeck("01292a5c-5ab1-4dab-910b-ed5680f9aad4").getDeckContents());
        Deck deck2 = decksManager.getDeck("01292a5c-5ab1-4dab-910b-ed5680f9aad4");
        deck2.removeCardByName("spell pierce", 4, false);
        System.out.println(deck2.getDeckContents());
        
//                try {
//           decksManager.saveDeck(deck2);
//        } catch (NoSuchElementException e) {
//        }
                 System.out.println(decksManager.getDeck("01292a5c-5ab1-4dab-910b-ed5680f9aad4").getDeckContents());
    }

}
