/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.cui;

import com.mycompany.mavenproject1.Mavenproject1;
import com.mycompany.mavenproject1.cui.functions.DeckCreator;
import com.mycompany.mavenproject1.cui.functions.InputGetter;
import com.mycompany.mavenproject1.models.Card;
import com.mycompany.mavenproject1.models.Deck;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DeckCreatorCUI {

    public static void editDeck(DeckCreator c, Scanner s) {
        DeckCreator dc = c;
        System.out.println("Current Deck: " + dc.getCreatedDeck().getDeckName());
        boolean loop = true;
        while (loop == true) {
            int options = InputGetter.getOption(5, "Please select from the following options, or press 0 to exit:\n"
                    + "1 add a card to mainboard\n"
                    + "2 add a card to sideboard\n"
                    + "3 show entire decklist\n"
                    + "4 save deck and exit deckcreator", s);

            switch (options) {
                case 1:
                    String message = "please type the quantity of card to be added followed by a space and then the name of the card. \n e.g. 1 Force of Will ";
                    String input = InputGetter.getString(message, s);
                    try {
                        DeckCreatorCUI.addCard(input, dc, false);
                        
                    } catch (NoSuchElementException e) {
                        System.err.print("could not find card" + input);
                    }
                    break;
                case 2:
                    message = "please type the quantity of card to be added followed by a space and then the name of the card. \n e.g. 1 Force of Will ";
                    input = InputGetter.getString(message, s);
                    try {
                        DeckCreatorCUI.addCard(input, dc, true);
                        
                    } catch (NoSuchElementException e) {
                        System.err.print("could not find card" + input);
                    }
                    break;
                case 3:
                    System.out.println(dc.getCreatedDeck().entireDecktoString());
                    break;
                case 4:
                    Deck deck = dc.getCreatedDeck();
                {
                    try {
                        Mavenproject1.allDecks.saveDeck(deck);
                        loop = false;
                    } catch (FileNotFoundException ex) {
                        System.err.println("Could not save the deck for some reason.");
                    }
                }
                    


            }
        }
    }
    public static void StartCUI(Deck deck ,Scanner s){
        DeckCreator dc = new DeckCreator(deck, Mavenproject1.database);
        DeckCreatorCUI.editDeck(dc, s);
    }
        public static void startCUI(String name, Scanner s) {
        DeckCreator dc = new DeckCreator(name, Mavenproject1.database);
        DeckCreatorCUI.editDeck(dc, s);}
    private static void addCard(String inputString, DeckCreator dc, boolean isSideboard) {
        try {
            String[] lineInfo = inputString.split(" ", 2);
            int quantity = Integer.valueOf(lineInfo[0]);
            String cardName = lineInfo[1];
            dc.setInSideboard(isSideboard);
            try {
                dc.addCard(cardName, quantity);
                System.out.println("card added");
            } catch (NoSuchElementException e) {
                throw new NoSuchElementException(e.toString());
            }
        } catch (Exception e) {
            System.err.println("The formatting is incorrect");
        }
    }

}
