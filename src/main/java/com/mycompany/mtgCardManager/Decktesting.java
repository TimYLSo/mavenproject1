/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.mtgCardManager;

import fileio.DeckWriter;
import models.Card;
import models.Deck;
import java.io.PrintWriter;

/**
 *
 * @author User
 */
public class Decktesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Deck testDeck = new Deck();
        Card testcard = new Card("Force of Will");
        Card testcard2 = new Card("Force of negation");
        Card testcard2again = new Card("Force of negation");
        Card removecard = new Card("Pact of negation");
        testDeck.addCard(removecard,4, false);
        testDeck.addCard(testcard2, 4, true);
        testDeck.addCard(testcard, 4, true);
        testDeck.addCard(testcard, 4, false);
        testDeck.removeCardByName("Pacat of negation", 2, false);
        System.out.print(testDeck);
        boolean samecard = testcard2== testcard2again;
        System.out.print(samecard);
        DeckWriter dw = new DeckWriter("src/files/222",testDeck);
//        dw.writeFileAsTextFile("apple",true);
//         PrintWriter pw = new PrintWriter("src/files/222");
    }
    
}
