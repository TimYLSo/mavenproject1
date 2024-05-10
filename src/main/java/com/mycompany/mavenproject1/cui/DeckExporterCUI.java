/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.cui;

import com.mycompany.mavenproject1.Mavenproject1;
import com.mycompany.mavenproject1.fileio.DeckWriter;
import com.mycompany.mavenproject1.models.Deck;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author User
 */
public class DeckExporterCUI {
    public static void exportDeck(int i, String path) throws FileNotFoundException{
    int index = i-1;
    
    String filePath;
    Deck deck = Mavenproject1.allDecks.getDeck(index);
    if (path.equals("")){filePath = "src/files/ExportedFiles/";}
    else{String lastchar = path.substring(path.length() -1);
    if (!lastchar.equals("/") || !lastchar.equals("\\")){path+="/";}
        filePath = path;}
    try{
    DeckWriter writer = new DeckWriter(filePath,deck);
    writer.writeFileAsTextFile(deck.getDeckName(), true);
    System.out.print("Deck has been imported into the path" + path);
    }catch(IOException e){
    System.err.println("Could not find file under the path: " + path);
    throw new FileNotFoundException();
    }
    
    }
}
