/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import database.DerbyCardDatabase;
import database.InMemoryCardDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author User
 */
public class Deck implements Modifyable {

    protected HashMap deckContents = new HashMap<OracleCard, Integer>(100);
    protected HashMap sideboard = new HashMap<OracleCard, Integer>(15);
    protected ArrayList<DeckCard> deck = new ArrayList<>();
    protected boolean updated = false;
    protected String DeckName = "new Deck";
    protected UUID id;

    public String getDeckName() {
        return DeckName;
    }
    public String getUUID(){
    return this.id.toString();
    }

    public void setDeckName(String DeckName) {
        this.DeckName = DeckName;
    }

    public Deck(String deckName) {
        this.DeckName = deckName;
        id = UUID.randomUUID();
    }
    public Deck (String deckName,String id){
    this.DeckName = deckName;
    this.id = UUID.fromString(id);
    }
    public Deck() {
        this.DeckName = "Unamed Deck";
        id = UUID.randomUUID();
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
    public void addCard(OracleCard card, int quantity, boolean isSideboard) {
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

    
    public void updateDeckOracleInfo(DerbyCardDatabase database) throws NoSuchElementException {
        try {
            Set maindeckset = this.getDeckContents().keySet();
            Iterator<OracleCard> maindeckIterator = maindeckset.iterator();

            while (maindeckIterator.hasNext()) {
                OracleCard card = maindeckIterator.next();

                if (!card.isHasBeenUpdated()) {

                    card.updateCardInfo(database);

                }
            }
            Set sideboardset = this.getSideboard().keySet();
            Iterator<OracleCard> sideboardIterator = sideboardset.iterator();

            while (sideboardIterator.hasNext()) {
                OracleCard card = sideboardIterator.next();
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
        HashMap<OracleCard, Integer> deckMap;

        try {
            if (isSideboard) {
                iteratorset = this.getSideboard().keySet();
                deckMap = this.getSideboard();
            } else {
                iteratorset = this.getDeckContents().keySet();
                deckMap = this.getDeckContents();
            }

            Iterator<OracleCard> setIterator = iteratorset.iterator();
            while (setIterator.hasNext() && quantity != 0) {
                OracleCard card = setIterator.next();
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

            }
                if (quantity != 0) {
                    throw new NoSuchElementException();
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
