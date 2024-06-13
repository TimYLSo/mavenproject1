/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.mtgCardManager;

import database.InMemoryCardDatabase;
import models.Card;
import models.OracleCard;
import java.util.NoSuchElementException;

/**
 *
 * @author User
 */
public class Databasetesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String path = "src/files/oracle-cards-20240412210228.json";
//         path = "src/files/minidatabase.json";
        InMemoryCardDatabase db = new InMemoryCardDatabase(path);
        OracleCard card =db.findCardByName("Territorial Kavu");
        Card deckCard = new Card("Forcge of Will");
        System.out.print(deckCard);
        try{
        deckCard.updateCardInfo(db);}
        catch(NoSuchElementException e){}
        System.out.print(deckCard);
        System.out.print(db);
        System.out.print(card);
        
    }
    
}
