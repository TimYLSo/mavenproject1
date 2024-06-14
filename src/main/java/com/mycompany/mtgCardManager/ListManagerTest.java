/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.mtgCardManager;

import database.DerbyCardDatabase;
import database.DerbyDatabase;
import fileio.CSVCardReader;
import groupmanager.DerbyCollectionsManager;
import groupmanager.DerbyDecksManager;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import models.CollectionList;

/**
 *
 * @author User
 */
public class ListManagerTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
          DerbyDatabase dbManager = new DerbyDatabase();
        DerbyCardDatabase cardDB = new DerbyCardDatabase(dbManager);
        DerbyCollectionsManager listsManager = new DerbyCollectionsManager(cardDB);
         String file = "C:\\Users\\User\\Documents\\school\\2024\\pdc\\assignment\\MtgCardManager\\mavenproject1\\src\\files\\CollectionLists\\con binder.csv";
           CSVCardReader reader = new CSVCardReader(file); 
           CollectionList list = reader.readListFromFile("Binder");
//           listsManager.saveList(list);
         ResultSet rs = dbManager.myQuery("Select * from LISTS");
        ResultSet rs2 = dbManager.myQuery("Select * from LISTCARDS");
        System.out.println(listsManager.getAllLists());
        System.out.println(listsManager.getAllLists());
         DatabaseMetaData dbmd = cardDB.getConnection().getMetaData();
//        System.out.println("Database product name : " + dbmd.getDatabaseProductName());
//        System.out.println("Database version : " + dbmd.getDatabaseProductVersion());
//        System.out.println("URL: " + dbmd.getURL());
//        System.out.println("driver name: " + dbmd.getDriverName());
//        System.out.println("driver version: " + dbmd.getDriverVersion());
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
        
    }
    
}
