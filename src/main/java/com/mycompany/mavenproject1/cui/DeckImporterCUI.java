/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.cui;

import com.mycompany.mavenproject1.Mavenproject1;
import com.mycompany.mavenproject1.cui.functions.InputGetter;
import com.mycompany.mavenproject1.fileio.CSVCardReader;
import com.mycompany.mavenproject1.fileio.TextFileCardReader;
import com.mycompany.mavenproject1.models.Deck;
import com.mycompany.mavenproject1.models.groupmanager.DecksManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class DeckImporterCUI {
    public static void editDeck(String path, Scanner s) throws FileNotFoundException{
    TextFileCardReader reader = new TextFileCardReader(path);
    try{
    Deck deck = reader.readDeckFromFile();
    String message = "File found, what would you like to name the deck?";
    String name = InputGetter.getString(message,s,true);
    
    deck.setDeckName(name);
    DecksManager manager = Mavenproject1.allDecks;
    manager.saveDeck(deck);
    System.out.println("Deck added.");
    }
    catch(IOException e){throw new FileNotFoundException();}
    }
}
