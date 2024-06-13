/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cui.m;


import com.mycompany.mtgCardManager.MtgCardManager;
import cui.functions.InputGetter;
import fileio.CSVCardReader;
import fileio.TextFileCardReader;
import models.Deck;
import groupmanager.DecksManager;
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
    DecksManager manager = MtgCardManager.allDecks;
    manager.saveDeck(deck);
    System.out.println("Deck added.");
    }
    catch(IOException e){throw new FileNotFoundException();}
    }
}
