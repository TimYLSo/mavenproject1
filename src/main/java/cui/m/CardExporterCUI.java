/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cui.m;

import fileio.DeckWriter;
import fileio.ListWriter;
import models.CollectionList;
import models.Deck;
import com.mycompany.mtgCardManager.MtgCardManager;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author User
 */
public class CardExporterCUI {
    public static void exportCards(Integer i, String path)throws FileNotFoundException {
    
        int index = i-1;
    
    String filePath;
    CollectionList list = MtgCardManager.collections.getList(index);
    if (path.equals("")){filePath = "src/files/ExportedFiles/";}
    else{String lastchar = path.substring(path.length() -1);
    if (!lastchar.equals("/") || !lastchar.equals("\\")){path+="/";}
        filePath = path;}
    try{
    ListWriter writer = new ListWriter(filePath,list);
    writer.writeFileAsCSV(list.getCollectionName(), true);
    System.out.print("Deck has been imported into the path" + path);
    }catch(IOException e){
    System.err.println("Could not find file under the path: " + path);
    throw new FileNotFoundException();
    }
    
    }
}
