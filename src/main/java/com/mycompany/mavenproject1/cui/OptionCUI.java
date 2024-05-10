/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.cui;

import com.mycompany.mavenproject1.Mavenproject1;
import com.mycompany.mavenproject1.cui.functions.InputGetter;
import com.mycompany.mavenproject1.database.JSONCardDatabase;
import com.mycompany.mavenproject1.models.Deck;
import com.mycompany.mavenproject1.models.groupmanager.CollectionsManager;
import com.mycompany.mavenproject1.models.groupmanager.DecksManager;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class OptionCUI {

    public static void importDeckCUI(Scanner s) {
        String message = "To Continue, please give an absolute path of the text file to be imported or input 0 to exit.";
        while (true) {

            String path = InputGetter.getString(message, s,true);
            try {
                DeckImporterCUI.editDeck(path, s);
                break;
            } catch (FileNotFoundException e) {
                message = "The path to the file is incorrect, file is not found. Please input a valid path.";
            }
        }
    }

    public static void ExportDeck(Scanner s) {
        DecksManager manager = Mavenproject1.allDecks;
        manager.showAllDecks();
        String message = "please choose the deck to be exported, please enter the deck index or 0 to exit";
        int Index = InputGetter.getOption(manager.getAllDecks().size(), message, s);
        while (true) {
            try {
                message = "please choose the path to export to, if no path is selected, please find the file under src/files/exportedFiles";
                String path = InputGetter.getString(message, s);
                DeckExporterCUI.exportDeck(Index, path);

                break;
            } catch (FileNotFoundException e) {
            }
        }
    }

    public static void importCardsCUI(Scanner s) {
        CollectionsManager manager = Mavenproject1.collections;
        Integer option = InputGetter.getOption(2, "Please select from the following options, \n1 Import Collection to an existing List. \n2 Import Collection to a new List", s);
        if (option == 1) {
            
            manager.showAllLists();
            String message = "please choose the List to add to, please enter the deck index or 0 to exit";

            int index = InputGetter.getOption(manager.getAllLists().size(), message, s);
            message = "To Continue, please give an absolute path of the file to be imported or input 0 to exit.";
            while (true) {

                String path = InputGetter.getString(message, s);
                try {
                    CardImporterCUI.ImportCards(path, index, s);
                    break;
                } catch (FileNotFoundException e) {
                    message = "The path to the file is incorrect, file is not found. Please input a valid path.";
                }
            }
            
        }
        else if(option ==2){
         String message = "please give the Name of the List you wish to create,or 0 to exit";
         String name = InputGetter.getString(message, s);
            message = "To Continue, please give an absolute path of the file to be imported or input 0 to exit.";
            while (true) {

                String path = InputGetter.getString(message, s);
                try {
                    CardImporterCUI.ImportCards(path, name,s);
                    break;
                } catch (FileNotFoundException e) {
                    message = "The path to the file is incorrect, file is not found. Please input a valid path.";
                }
            }
        
        }

    }

    public static void exportCardsCUI(Scanner s) {
                CollectionsManager manager = Mavenproject1.collections;
        manager.showAllLists();
        String message = "please choose the list to be exported, please enter the index or 0 to exit";
        int Index = InputGetter.getOption(manager.getAllLists().size(), message, s);
        while (true) {
            try {
                message = "please choose the path to export to, if no path is selected, please find the file under src/files/exportedFiles";
                String path = InputGetter.getString(message, s);
                CardExporterCUI.exportCards(Index, path);

                break;
            } catch (FileNotFoundException e) {
            }catch(Exception e){}
        }
        
        
    }

    public static void selectDecks(Scanner s) {
         DecksManager manager = Mavenproject1.allDecks;
        manager.showAllDecks();
        String message = "please choose the deck to be you wish to view, please enter the deck index or 0 to exit";
        int Index = InputGetter.getOption(manager.getAllDecks().size(), message, s);
        CUIViewDecks.display(Index);
    }

    public static void createDeck(Scanner s) {
        String message = "To Continue, please give the name of the deck or input 0 to exit.";
        try {
            String name = InputGetter.getString(message, s);
            if ("0".equals(name)) {
                System.out.println("Now exiting...");
                System.exit(0);
            }
            DeckCreatorCUI.startCUI(name, s);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void SearchCards() {
        JSONCardDatabase db = Mavenproject1.database;

    }

}
