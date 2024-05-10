/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.fileio;

import com.mycompany.mavenproject1.models.Card;
import com.mycompany.mavenproject1.models.CollectionList;
import com.mycompany.mavenproject1.models.Deck;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author User
 */
public class CSVCardReader extends CardReader implements ReadsFileToList {

    public CSVCardReader(String filePath) {
        super(filePath);
    }

    @Override
    public CollectionList readListFromFile(String listName)throws IOException {
        CollectionList outputList = new CollectionList(listName);
        try {

            Reader CSVReader = new FileReader(this.getFilePath());
            Iterable<CSVRecord> cards = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(CSVReader);
            for (CSVRecord cardRecord : cards) {
                Card card = new Card(cardRecord.get("Name"));
                card.setScryfallID(cardRecord.get("Scryfall ID"));
                if ("Foil".equals(cardRecord.get("Foil"))) {
                    card.setFoil(true);
                }
                if (!"".equals(cardRecord.get("Language"))) {
                    card.setLanguage(cardRecord.get("Language"));
                }
                int quantity = Integer.parseInt(cardRecord.get("Quantity"));
                for (int i = 0; i < quantity; i++) {
                    outputList.addCard(card);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
            throw new IOException();
        } catch (IOException | NullPointerException|IllegalArgumentException e) {
            System.out.println("There is a problem with the formatting of the CSV file.Please update the database and try again.\n" + e);
            throw new IOException();
        }

        return outputList;
    }

}
