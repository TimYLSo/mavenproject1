/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.cui.CUIMenu;
import com.mycompany.mavenproject1.cui.OptionCUI;
import com.mycompany.mavenproject1.database.JSONCardDatabase;
import com.mycompany.mavenproject1.models.Card;
import com.mycompany.mavenproject1.models.groupmanager.CollectionsManager;
import com.mycompany.mavenproject1.models.groupmanager.DecksManager;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Mavenproject1 {
final static String deckspath = "src/files/Decks/deckLocations.txt";
public final static JSONCardDatabase database = new JSONCardDatabase("src/files/oracle-cards-20240412210228.json");
public static CollectionsManager collections = new CollectionsManager("src/files/CollectionLists/listLocations.txt",database);
public static DecksManager allDecks = new DecksManager(deckspath,database);


    public static void main(String[] args) {
     CUIMenu.printIntroMessage();
    }
}
