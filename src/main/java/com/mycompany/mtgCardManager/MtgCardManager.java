/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.mtgCardManager;

import cui.m.CUIMenu;
import cui.m.OptionCUI;
import database.JSONCardDatabase;
import models.Card;
import groupmanager.CollectionsManager;
import groupmanager.DecksManager;
import gui.MainFrame;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class MtgCardManager {
final static String deckspath = "src/files/Decks/deckLocations.txt";
public final static JSONCardDatabase database = new JSONCardDatabase("src/files/oracle-cards-20240412210228.json");
public static CollectionsManager collections = new CollectionsManager("src/files/CollectionLists/listLocations.txt",database);
public static DecksManager allDecks = new DecksManager(deckspath,database);


    public static void main(String[] args) {
             MainFrame menu = new MainFrame();
        menu.setVisible(true);
    }
}
