/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package groupmanager;

import database.DerbyCardDatabase;
import fileio.DeckWriter;
import fileio.TextFileCardReader;
import models.Card;
import models.Deck;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DecksManager extends Manager {

    ArrayList<Deck> listOfDecks = new ArrayList();
    DerbyCardDatabase database;
    HashMap<String, String> deckPaths = new HashMap<>();

    public HashMap<String, String> getDeckPaths() {
        return this.deckPaths;
    }

    private void updateDecks(DerbyCardDatabase database) {
        for (int i = 0; i < this.listOfDecks.size(); i++) {
            Deck deck = this.listOfDecks.get(i);
            if (!deck.isUpdated()) {
                deck.updateDeckOracleInfo(database);
            }

        }
    }

    private void readFile(String filePath) {
        String filename = filePath;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = "";
            while ((line = br.readLine()) != null) {

                try {
                    String[] lineInfo = line.split(",", 2);
                    String deckName = lineInfo[0];
                    String deckPath = lineInfo[1];
                    TextFileCardReader reader = new TextFileCardReader(deckPath);
                    Deck newDeck = reader.readDeckFromFile();
                    newDeck.setDeckName(deckName);
                    newDeck.updateDeckOracleInfo(this.database);
                    if (deckPaths.containsKey(deckName)) {
                        throw new FileFormatException();
                    }
                    deckPaths.put(deckName, deckPath);
                    listOfDecks.add(newDeck);
                } catch (FileFormatException e) {
                    System.err.println("There is a problem with the fileformat, please fix it and try again.: " + e.getMessage());
                } catch (IOException | NoSuchElementException | IndexOutOfBoundsException e) {
                    System.err.println("Problem with reading the line " + line);
                }

            }
            
            br.close();
            this.updateDeckPaths();
        } catch (IOException | IndexOutOfBoundsException e) {
            System.err.println("There is a problem with the fileformat, please fix it and try again.: " + e.getMessage());

        }
    }

    public ArrayList<Deck> getAllDecks() {
        return this.listOfDecks;
    }

    public DecksManager(String pathToFile, DerbyCardDatabase database) {
        super(pathToFile);
        this.currentSavePath = "src/files/Decks/";
        this.database = database;
        this.readFile(this.pathToFile);
        this.updateDecks(database);

    }

    public Deck getDeck(String deckName) throws NoSuchElementException {

        for (int i = 0; i < listOfDecks.size(); i++) {
            Deck deck = listOfDecks.get(i);
            if (deck.getDeckName().equals(deckName)) {
                return deck;
            }
        }

        throw new NoSuchElementException("Deck not found: " + deckName);
    }

    public Deck getDeck(int i) throws IndexOutOfBoundsException {
        return listOfDecks.get(i);
    }

    public void saveDeck(Deck deck) throws FileNotFoundException {
        String deckName = deck.getDeckName();
        String currentPath;
        DeckWriter dw;
        if (this.deckPaths.containsKey(deckName)) {
            currentPath = this.deckPaths.get(deckName);
            dw = new DeckWriter(currentPath, deck);
            dw.writeFileAsTextFile(currentPath);
        } else {
            try {
                currentPath = this.currentSavePath;
                deck.updateDeckOracleInfo(database);
                dw = new DeckWriter(currentPath, deck);

                dw.writeFileAsTextFile(deckName, true);
                this.deckPaths.put(deckName, dw.getWholePath());
                this.updateDeckPaths();
                this.listOfDecks.add(deck);
                
            } catch (NoSuchElementException e) {
                System.out.println("The following Deck could not be saved into the database because it is invalid.A card is not found in the database");
            }

        }
    }

    protected void updateDeckPaths() {
        PrintWriter pw = null;
        try {
            HashMap s = this.deckPaths;
            Set deckPathSet = s.keySet();
            Iterator<String> iterator = deckPathSet.iterator();
            String path = this.pathToFile;
            File textFile = new File(path);
            pw = new PrintWriter(path);
            textFile.createNewFile();
            while (iterator.hasNext()) {
                String name = iterator.next();

                String outputString = name + "," + s.get(name);
                pw.println(outputString);
            }
        } catch (IOException ex) {
            Logger.getLogger(DecksManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    public boolean isInManager(String DeckName) {
        return deckPaths.containsKey(DeckName);
    }

    public void showAllDecks() {

        for (int i = 0; i < this.listOfDecks.size(); i++) {
            Deck deck = this.listOfDecks.get(i);
            System.out.println("Deck Index:" + (i + 1) + " Deck Name:" + deck.getDeckName());
        }
    }
}
