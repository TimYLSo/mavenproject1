/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileio;

import models.Card;
import models.CollectionList;
import models.Deck;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author User
 */
public class TextFileCardReader extends CardReader implements ReadsFileToDeck,ReadsFileToList {

    public TextFileCardReader(String filePath) {
        super(filePath); 
    }

    @Override
    public Deck readDeckFromFile() throws IOException {
        String filename = this.getFilePath();
        Deck outputDeck = new Deck();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = "";
            boolean sideboardCard = false;
            while ((line = br.readLine()) != null) {
                if ("".equals(line)) {
                    sideboardCard = true;
                } else {
                    String[] lineInfo = line.split(" ", 2);
                    int quantity = Integer.valueOf(lineInfo[0]);
                    String  cardName = lineInfo[1];
                    Card cardInDeck = new Card(cardName);
                    outputDeck.addCard(cardInDeck, quantity, sideboardCard);
                            //                    System.out.println(line);
                }

            }
            br.close();
        } catch (IOException ex) {
            System.err.println("IOException Error: " + ex.getMessage());
            throw new IOException();
            
            
        }
        catch(NumberFormatException e){
            System.err.println("There is a problem with the fileformat, please fix it and try again.: " + e.getMessage());
            return null;
        }
        return outputDeck;
    }


    @Override
    public CollectionList readListFromFile(String name) {
                String filename = this.getFilePath();
        CollectionList outputList = new CollectionList(name);
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = "";
            while ((line = br.readLine()) != null) {

                    String[] lineInfo = line.split(",");
                    String  cardName = lineInfo[0];
                    Card cardInDeck = new Card(cardName);
                    if (!"".equals(lineInfo[1])){
                    cardInDeck.setFoil(true);
                    }
                    if (!"".equals(lineInfo[2])){
                    cardInDeck.setLanguage(lineInfo[2]);
                    }
                    if (!"".equals(lineInfo[4])){
                    cardInDeck.setScryfallID(lineInfo[4]);
                    }
                    
                    outputList.addCard(cardInDeck);
                            //                    System.out.println(line);
                

            }
            br.close();
        } catch (IOException ex) {
            System.err.println("IOException Error: " + ex.getMessage());
            return null;
        }
        catch(NumberFormatException e){
            System.err.println("There is a problem with the fileformat, please fix it and try again.: " + e.getMessage());
            return null;
        }
        return outputList;
    }
    
}
