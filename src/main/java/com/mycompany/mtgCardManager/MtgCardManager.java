/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.mtgCardManager;

import cui.m.CUIMenu;
import cui.m.OptionCUI;
import database.DerbyCardDatabase;
import database.InMemoryCardDatabase;
import models.Card;
import groupmanager.CollectionsManager;
import groupmanager.DecksManager;
import groupmanager.DerbyDecksManager;
import gui.MainFrame;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.OracleCard;

/**
 *
 * @author User
 */
public class MtgCardManager {
final static String deckspath = "src/files/Decks/deckLocations.txt";
public final static DerbyCardDatabase database = new DerbyCardDatabase();
public static CollectionsManager collections = new CollectionsManager("src/files/CollectionLists/listLocations.txt",database);
public static DerbyDecksManager allDecks = new DerbyDecksManager(database);


    public static void main(String[] args) {
        OracleCard card = database.findCardByName("Force of Will");
        System.out.print(card);
             MainFrame menu = new MainFrame();
        menu.setVisible(true);
    try {
        System.out.print(allDecks.getAllDecks());
    } catch (SQLException ex) {
        Logger.getLogger(MtgCardManager.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
