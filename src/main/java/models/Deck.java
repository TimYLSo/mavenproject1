/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import database.JSONCardDatabase;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 *
 * @author User
 */
public class Deck implements Modifyable, Updateable {

    protected HashMap deckContents = new HashMap<Card, Integer>(100);
    protected HashMap sideboard = new HashMap<Card, Integer>(15);
    protected boolean updated = false;
    protected String DeckName = "new Deck";

    public String getDeckName() {
        return DeckName;
    }

    public void setDeckName(String DeckName) {
        this.DeckName = DeckName;
    }

    public Deck(String deckName) {
        this.DeckName = deckName;
    }

    public Deck() {
        this.DeckName = "Unamed Deck";
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public HashMap getDeckContents() {
        return deckContents;
    }

    public void setDeckContents(HashMap deckContents) {
        this.deckContents = deckContents;
    }

    public HashMap getSideboard() {
        return sideboard;
    }

    public void setSideboard(HashMap sideboard) {
        this.sideboard = sideboard;
    }

    @Override
    public void addCard(Card card, int quantity, boolean isSideboard) {
        if (isSideboard) {
            try {

                if (sideboard.containsKey(card)) {
                    Integer newquantity = (Integer) sideboard.get(card) + quantity;
                    sideboard.replace(card, newquantity);
                } else {
                    sideboard.put(card, quantity);
                }

            } catch (Exception e) {
            }
        } else {
            try {

                if (deckContents.containsKey(card)) {
                    Integer newquantity = (Integer) deckContents.get(card) + quantity;
                    deckContents.replace(card, newquantity);
                } else {
                    deckContents.put(card, quantity);
                }

            } catch (Exception e) {
            }
        }

    }

    @Override
    public void updateDeckOracleInfo(JSONCardDatabase database) throws NoSuchElementException {
        try {
            Set maindeckset = this.getDeckContents().keySet();
            Iterator<Card> maindeckIterator = maindeckset.iterator();

            while (maindeckIterator.hasNext()) {
                Card card = maindeckIterator.next();

                if (!card.isHasBeenUpdated()) {

                    card.updateCardInfo(database);

                }
            }
            Set sideboardset = this.getSideboard().keySet();
            Iterator<Card> sideboardIterator = sideboardset.iterator();

            while (sideboardIterator.hasNext()) {
                Card card = sideboardIterator.next();
                if (!card.isHasBeenUpdated()) {

                    card.updateCardInfo(database);
                }
            }
            this.setUpdated(true);
        } catch (NoSuchElementException ex) {
            System.out.println("The following Deck is invalid because a card is not found in the database" + this.getDeckName());
            throw new NoSuchElementException();
        } catch (Exception e) {
            System.out.print("The following error occured \n" + e);
        }

    }

    @Override
    public void removeCardByName(String cardName, int quantity, boolean isSideboard) {
        Set iteratorset;
        HashMap<Card, Integer> deckMap;

        try {
            if (isSideboard) {
                iteratorset = this.getSideboard().keySet();
                deckMap = this.getSideboard();
            } else {
                iteratorset = this.getDeckContents().keySet();
                deckMap = this.getDeckContents();
            }

            Iterator<Card> setIterator = iteratorset.iterator();
            while (setIterator.hasNext() && quantity != 0) {
                Card card = setIterator.next();
                String currentCardName = card.getCardName();
                if (cardName.equals(currentCardName)) {
                    if (deckMap.get(card) < quantity) {
                        deckMap.remove(card);
                        quantity -= deckMap.get(card);

                    } else {
                        deckMap.replace(card, deckMap.get(card) - quantity);
                        quantity = 0;
                    }
                }
                if (quantity != 0) {
                    throw new NoSuchElementException();
                }
            }

        } catch (NoSuchElementException ex) {
            System.out.println("The Card of that quantity can not be found in the deck.\n The maximum amount has been removed." + ex);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public String toString() {
        return this.getDeckName();
    }

    public String entireDecktoString() {
        return deckContents.toString() + "\n\n sideboard" + sideboard.toString();
    }
}
