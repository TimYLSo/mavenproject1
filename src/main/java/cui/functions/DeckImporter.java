/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cui.functions;

import fileio.TextFileCardReader;
import models.Deck;
import java.io.IOException;

/**
 *
 * @author User
 */
public class DeckImporter extends Importer {

    public static Deck ImportDeck(String path, boolean isTextFile) {
Deck importedDeck = null;
        if (isTextFile) {
            TextFileCardReader reader = new TextFileCardReader(path);
            
            try {
                importedDeck = reader.readDeckFromFile();

            } catch (IOException e) {
                System.err.println("IOException Error: " + e.getMessage());
            }
            
        }
        return importedDeck;
        
    }}
