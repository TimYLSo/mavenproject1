/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package groupmanager;

import database.InMemoryCardDatabase;
import fileio.CSVCardReader;
import fileio.ListWriter;
import fileio.TextFileCardReader;
import models.CollectionList;
import models.Deck;
import java.io.BufferedReader;
import java.io.File;
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
public class CollectionsManager extends Manager {

    ArrayList<CollectionList> collection = new ArrayList();
    InMemoryCardDatabase database;
    HashMap<String, String> collectionPaths = new HashMap<>();

    public CollectionsManager(String pathToFile) {
        super(pathToFile);
    }

    public HashMap<String, String> getCollectionPaths() {
        return this.collectionPaths;
    }

    private void updateLists(InMemoryCardDatabase database) {
        for (int i = 0; i < this.collection.size(); i++) {
            CollectionList collectionList = this.collection.get(i);
            try{
            if (!collectionList.isUpdated()) {
                collectionList.updateDeckOracleInfo(database);
            }}catch(NoSuchElementException e){
                    System.out.println("A card is not found in the database in collectionlist "+collectionList.getCollectionName()  +e);

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
                    String listName = lineInfo[0];
                    String listPath = lineInfo[1];
                    CSVCardReader reader = new CSVCardReader(listPath);

                    CollectionList newList = reader.readListFromFile(listName);
                    newList.setCollectionName(listName);
                    newList.updateDeckOracleInfo(this.database);
                    if (this.collectionPaths.containsKey(listName)) {
                        throw new FileFormatException();
                    }

                    collection.add(newList);
                    this.collectionPaths.put(listName, listPath);
                } catch (FileFormatException e) {
                    System.err.println("There is a problem with the fileformat, please fix it and try again.: " + e.getMessage());
                } catch (IOException | NoSuchElementException | IndexOutOfBoundsException e) {
                    System.err.println("Problem with reading the line " + line);
                }

            }
            br.close();
            this.updateListPaths();
        } catch (IOException | IndexOutOfBoundsException e) {
            System.err.println("There is a problem with the fileformat, please fix it and try again.: " + e.getMessage());

        }
    }

    public ArrayList<CollectionList> getAllLists() {
        return this.collection;
    }

    public CollectionsManager(String pathToFile, InMemoryCardDatabase database) {
        super(pathToFile);
        this.currentSavePath = "src/files/CollectionLists/";
        this.database = database;
        this.readFile(this.pathToFile);
        this.updateLists(database);

    }

    public CollectionList getList(String listName) throws NoSuchElementException {

        for (int i = 0; i < this.collection.size(); i++) {
            CollectionList list = this.collection.get(i);
            if (list.getCollectionName().equals(listName)) {
                return list;
            }
        }

        throw new NoSuchElementException("Deck not found: " + listName);
    }

    public CollectionList getList(int i) throws IndexOutOfBoundsException {
        return this.collection.get(i);
    }

    public void saveList(CollectionList list) {
        String deckName = list.getCollectionName();
        String currentPath;
        ListWriter lw;
        try {
            list.updateDeckOracleInfo(database);
            if (this.collectionPaths.containsKey(deckName)) {
                currentPath = this.collectionPaths.get(deckName);
                lw = new ListWriter(currentPath, list);
                lw.writeFileAsCSV(currentPath);
            } else {

                currentPath = this.currentSavePath;

                lw = new ListWriter(currentPath, list);

                lw.writeFileAsCSV(list.getCollectionName(), true);
                this.collectionPaths.put(deckName, lw.getWholePath());
                this.updateListPaths();

            }
        } catch (IOException e) {
            System.err.print(e);
        }catch(NoSuchElementException ex){
                System.out.print("Could not save deck. \nThe following error occured:" + ex);

        
        }
    }

    protected void updateListPaths() {
        PrintWriter pw = null;
        try {
            HashMap s = this.collectionPaths;
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
        return this.collectionPaths.containsKey(DeckName);
    }

    public void showAllLists() {

        for (int i = 0; i < this.getAllLists().size(); i++) {
            CollectionList list = this.getAllLists().get(i);
            System.out.println("Deck Index:" + (i + 1) + " List Name:" + list.getCollectionName());
        }
    }

    public void addList(CollectionList list, String savedList) throws NoSuchElementException {// appends the contents of a collectionList to a collectionlist already saved that is found by name in the set, then saves.
        CollectionList currentList;
        try {
            currentList = this.getList(savedList);
                    CollectionList appendList = list;
        currentList.getCardList().addAll(appendList.getCardList());
this.saveList(currentList);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Could not find the string list" + e);
        }


    }
        public void addList(CollectionList list, int index)throws IndexOutOfBoundsException{
        CollectionList currentList = this.collection.get(index);
        currentList.getCardList().addAll(list.getCardList());
        }
}
