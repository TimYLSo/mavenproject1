/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cui.functions;

import database.JSONCardDatabase;
import fileio.TextFileCardReader;
import models.Card;
import models.OracleCard;
import models.Deck;
import java.util.NoSuchElementException;

/**
 *
 * @author User
 */
public class DeckCreator extends Creator {

    protected Deck createdDeck;
    protected JSONCardDatabase database;
    protected boolean inSideboard = false;

    public boolean isInSideboard() {
        return inSideboard;
    }

    public void setInSideboard(boolean inSideboard) {
        this.inSideboard = inSideboard;
    }

    public DeckCreator(String DeckName, JSONCardDatabase database) {
        Deck newDeck = new Deck(DeckName);
        newDeck.setUpdated(true);
        this.createdDeck = newDeck;
        this.database = database;
    }
    public DeckCreator(Deck deck,JSONCardDatabase database){
    this.createdDeck = deck;
        this.database = database;
        deck.updateDeckOracleInfo(database);
        deck.setUpdated(true);
    }

    public DeckCreator(TextFileCardReader reader, JSONCardDatabase database) {
        try{
        this.createdDeck = reader.readDeckFromFile();
        }
        catch(Exception e){}
    }

    public Deck getCreatedDeck() {
        return createdDeck;
    }

    public void setCreatedDeck(Deck createdDeck) {
        this.createdDeck = createdDeck;
    }

    public JSONCardDatabase getDatabase() {
        return database;
    }

    public void setDatabase(JSONCardDatabase database) {
        this.database = database;
    }

    public void addCard(Card card, Integer quantity) { // updates the card if it hasn't been updated and adds it to the deck.
        try {
            if (!card.isHasBeenUpdated()) {
                card.updateCardInfo(database);
                card.setHasBeenUpdated(true);
            }
            this.getCreatedDeck().addCard(card, quantity, inSideboard);
        } catch (Exception e) {
        }

    }
    public void addCard(String cardName, Integer quantity) throws NoSuchElementException{
    
    Card card = new Card(cardName);
    try{
    card.updateCardInfo(database);}
    catch(NoSuchElementException e){
    throw new NoSuchElementException(e.toString());
    }
    addCard(card, quantity);
    }

    public void removeCard(String cardName, int quantity) {
        this.getCreatedDeck().removeCardByName(cardName, quantity, inSideboard);
    }
}
