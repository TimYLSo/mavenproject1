/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.mtgCardManager;


import database.DerbyCardDatabase;

import groupmanager.DerbyCollectionsManager;
import groupmanager.DerbyDecksManager;
import gui.MainFrame;


/**
 *
 * @author User
 */
public class MtgCardManager {
public final static DerbyCardDatabase database = new DerbyCardDatabase();
public static DerbyCollectionsManager collections = new DerbyCollectionsManager(database);
public static DerbyDecksManager allDecks = new DerbyDecksManager(database);
public static MainFrame menu = new MainFrame();

    public static void main(String[] args) {

            
        menu.setVisible(true);

    }
}
