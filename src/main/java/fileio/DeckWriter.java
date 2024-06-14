/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileio;

import models.Card;
import models.CollectionList;
import models.Deck;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.OracleCard;

/**
 *
 * @author User
 */
public class DeckWriter extends CardWriter {

    protected Deck deckToWrite;
    protected String wholePath;

    public String getWholePath() {
        return wholePath;
    }

    public void setWholePath(String wholePath) {
        this.wholePath = wholePath;
    }

    public DeckWriter(String filePath, Deck deck) {
        super(filePath, deck);
        this.deckToWrite = deck;
    }

    public void addDeck(Deck deck) {
        this.deckToWrite = deck;
    }

    public void writeFileAsTextFile(String filename, boolean a) throws FileNotFoundException {
        String fullPath = this.getFilePath() + filename + ".txt";
        this.setWholePath(fullPath);
        writeFileAsTextFile(fullPath);
    }

    public void writeFileAsTextFile(String fullpath) throws FileNotFoundException { // creates a file in the designated path with the designated fullpath, if fullpath already exists, overwrites it.

        PrintWriter pw = null;

        try {
            String fullPath = fullpath;
            File textFile = new File(fullPath);
            textFile.createNewFile();
            pw = new PrintWriter(fullPath);
            Deck deck = this.deckToWrite;

            Set maindeckset = deck.getDeckContents().keySet();
            HashMap<OracleCard, Integer> mainDeck = deck.getDeckContents();

            Iterator<OracleCard> maindeckIterator = maindeckset.iterator();
            while (maindeckIterator.hasNext()) {
                OracleCard card = maindeckIterator.next();
                Integer quantity = mainDeck.get(card);
                String cardAsString = quantity.toString() + " " + card.getCardName();
                pw.println(cardAsString);

            }
            pw.println("");
            Set sideboardset = deck.getSideboard().keySet();
            Iterator<OracleCard> sideboardIterator = sideboardset.iterator();
            HashMap<OracleCard, Integer> sideBoard = deck.getSideboard();
            while (sideboardIterator.hasNext()) {
                OracleCard card = sideboardIterator.next();
                Integer quantity = sideBoard.get(card);
                String cardAsString = quantity.toString() + " " + card.getCardName();
                pw.println(cardAsString);
            }
        } catch (IOException e) {
            throw new FileNotFoundException("" + e);
        } catch (NullPointerException ex) {
            System.out.print("Trying to write a List that does not exist." + ex);
            Logger.getLogger(ListWriter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pw != null) {
                pw.close();
            }

        }
    }
}
