/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.fileio;

import com.mycompany.mavenproject1.models.Card;
import com.mycompany.mavenproject1.models.CollectionList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ListWriter extends CardWriter {

    protected CollectionList ListToWrite;
    protected String wholePath;

    public String getWholePath() {
        return wholePath;
    }

    public void setWholePath(String wholePath) {
        this.wholePath = wholePath;
    }

    public ListWriter(String filePath, CollectionList list) {
        super(filePath, list);
        this.ListToWrite = list;
    }

    public void addList(CollectionList list) {
        this.ListToWrite = list;
    }

    public void writeFileAsCSV(String filename, boolean a) throws FileNotFoundException {
        String fullPath = this.getFilePath() + filename + ".csv";
        this.setWholePath(fullPath);
        writeFileAsCSV(fullPath);
    }

    public void writeFileAsCSV(String fullpath) throws FileNotFoundException{ // creates a file in the designated path with the designated filename, if filename already exists, overwrites it.
        PrintWriter pw = null;
        try {

            String fullPath = fullpath;
            File textFile = new File(fullPath);
            textFile.createNewFile();

            pw = new PrintWriter(fullPath);
            CollectionList cardList = this.ListToWrite;

            String cardStringRep = "Name,Foil,Language,Quantity,Scryfall ID";
            pw.println(cardStringRep);
            ArrayList<Card> array = cardList.getCardList();
            for (int i = 0; i < array.size(); i++) {
                Card card = array.get(i);
                String cardName = card.getCardName().replaceAll("^|$", "\"");
                String language = card.getLanguage().replaceAll("^|$", "\"");
                String Foil = "\"\"";
                if (card.isFoil()) {
                    Foil = "Foil";
                }
                String id = card.getScryfallID().replaceAll("^|$", "\"");
                cardStringRep = cardName + "," + Foil + "," + language + ",\"1\"," + id;
                pw.println(cardStringRep);
            }
            // Write str to the output.txt.

        } catch (IOException ex) {throw new FileNotFoundException(); 

        } catch (NullPointerException ex) {
            System.out.print("Trying to write a List that does not exist." + ex);
            Logger.getLogger(ListWriter.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IllegalArgumentException e){System.err.print("The csv file is formatted incorrectly, please check the file." + e);} 
        finally {
            if (pw != null) {
                pw.close();
            }

        }
    }
}
