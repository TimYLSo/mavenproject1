/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.mtgCardManager;

import database.DerbyCardDatabase;
import database.DerbyDatabase;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import models.OracleCard;

/**
 *
 * @author User
 */
public class DBTesting {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        //DerbyDatabase dbManager = new DerbyDatabase();
        DerbyDatabase dbManager = new DerbyDatabase();
        DerbyCardDatabase cardDB = new DerbyCardDatabase(dbManager);
//        cardDB.updateDatabase("src/files/default-cards-20240613090617.json");
        ResultSet rs = dbManager.myQuery("Select * from ORACLECARDS");
        //If it's an online database: You will find: org.apache.derby.client.net.NetConnection40@7fbe847c
        //That means: Connection conn = new NetConnection(); 
        //If it's an embedded database: You will find: org.apache.derby.impl.jdbc.EmbedConnection40@681384962 (XID = 16), (SESSIONID = 1), (DATABASE = CarDB), (DRDAID = null) 
        //That means: Connection conn = new EmbedConnection40(); 
        cardDB.getConnection().commit();


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

        System.out.println(cardDB.getConnection().getSchema());
    }

}
