/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import database.DerbyCardDatabase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author User
 */
public class CollectionList {

    protected String collectionName;
    protected ArrayList<Card> cardList = new ArrayList<>();
    protected boolean updated = false;
    protected UUID id;

    public UUID getId() {
        return id;
    }



    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public CollectionList(String name) {
        this.collectionName = name;
        id = UUID.randomUUID();
    }

    public CollectionList(String name, String id) {
        this.collectionName = name;
        this.id = UUID.fromString(id);
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public ArrayList getCardList() {
        return this.cardList;
    }

    public void addCard(Card card) {
        this.getCardList().add(card);
    }

    public void removeCard(int index) {
        try {
            this.getCardList().remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("this index is outside of the range of the card");
        }
    }

    public void updateDeckOracleInfo(DerbyCardDatabase database) throws NoSuchElementException {
        try {
            Iterator<Card> listIterator = this.getCardList().iterator();
            while (listIterator.hasNext()) {
                Card card = listIterator.next();

                if (!card.isHasBeenUpdated()) {

                    card.updateCardInfo(database);

                }
            }
            this.setUpdated(true);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException(ex.toString());

        } catch (Exception e) {
            System.out.print("The following error occured \n" + e);
        }

    }

    public ArrayList findCards(String name) {// looks through the array to find cards with the given card name and then returns an arraylist with their incicies, returns an emty array if none are found.
        ArrayList foundCards = new ArrayList();
        for (int i = 0; i < this.getCardList().size(); i++) {
            if (this.cardList.get(i).getCardName().equals(name)) {
                foundCards.add(i);
            }
        }
        return foundCards;
    }

    @Override
    public String toString() {
        String outputString = this.collectionName + "\n";
        ArrayList array = this.cardList;
        for (int i = 0; i < this.cardList.size(); i++) {
            Card card = this.cardList.get(i);
            String cardName = card.getCardName();
            String condiiton = card.getCondition();
            String language = card.getLanguage();
            boolean Foil = card.isFoil();
            String id = card.getScryfallID();
            String temp = cardName + " " + Foil + " " + condiiton + " " + language + " " + id + "\n";
            outputString += temp;
        }
        return outputString;
    }

}
