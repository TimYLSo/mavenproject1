/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.mtgCardManager;

import database.MySQLDatabase;

/**
 *
 * @author User
 */
public class DBTesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MySQLDatabase dbManager = new MySQLDatabase();
        //If it's an online database: You will find: org.apache.derby.client.net.NetConnection40@7fbe847c
        //That means: Connection conn = new NetConnection(); 
        //If it's an embedded database: You will find: org.apache.derby.impl.jdbc.EmbedConnection40@681384962 (XID = 16), (SESSIONID = 1), (DATABASE = CarDB), (DRDAID = null) 
        //That means: Connection conn = new EmbedConnection40(); 
        System.out.println(dbManager.getConnection());
    }
    
}
