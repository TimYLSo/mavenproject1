/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.database.JSONCardDatabase;
import com.mycompany.mavenproject1.models.Card;
import com.mycompany.mavenproject1.models.CollectionList;
import com.mycompany.mavenproject1.models.groupmanager.CollectionsManager;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ListManagerTesting {

    /**
     * @param args the command line arguments
     */
public static void main(String[] args) {
        String path = "src/files/CollectionLists/listLocations.txt";
        String path2 = "src/files/oracle-cards-20240412210228.json";
//         path = "src/files/minidatabase.json";
        JSONCardDatabase db = new JSONCardDatabase(path2);
        CollectionsManager manager = new CollectionsManager(path, db);
        ArrayList<CollectionList> decks = manager.getAllLists();
//        System.out.println(manager.isInManager("DargoThrasios"));
//
//        Deck testDeck = new Deck();
//        Card testcard = new Card("Force of Will");
            Card testcard2 = new Card("Force of negation");
//        Card testcard2again = new Card("Force of negation");
//        Card removecard = new Card("Pact of negation");
//        testDeck.addCard(removecard, 4, false);
//        testDeck.addCard(testcard2, 4, true);
//        testDeck.addCard(testcard, 4, true);
//        testDeck.addCard(testcard, 4, false);
//        testDeck.setDeckName("semantics");
//        manager.saveDeck(testDeck);
            CollectionList list = new CollectionList("list1");
            list.addCard(testcard2);
            manager.addList(list, "list2");
            System.out.print(manager.getCollectionPaths());

    }

}
    

