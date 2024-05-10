/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.cui;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class CUIMenu {

    public static void printIntroMessage() {
        
        int option = 1;
        Scanner cuiInput = new Scanner(System.in);
        System.out.print("Welcome to the mtgCardManager.");
        while (option != 0) {
            
            String introMessage = "\n To continue, please select from the following options: "
                    + "\n 1. Import Deck from text file."
                    + "\n 2. Export Deck to text file. "
                    + "\n 3. Import Cards To collection from csv file. "
                    + "\n 4. Export Collection to text file."
                    + "\n 5. Look though Decks."
                    + "\n 6. Create a deck"
                    + "\n 0. Exit \n";
            System.out.print(introMessage);
            while (option != 0) {
                try {
                    option = cuiInput.nextInt();

                    if ((option >= 7)) {
                        throw new ArithmeticException("This option does not exist");
                    }
                    
                    CUIMenu.performOption(option,cuiInput);
                    break;
                } catch (ArithmeticException e) {
                    System.out.println("This option does not exist, please enter an integer between 1 and 6.");
                } catch (InputMismatchException e) {
                    System.out.println("This is not a correct number, please enter an integer between 0 and 6.");
                    cuiInput.next();

                }
            }
               
        }
    }

    public static void performOption(int option,Scanner s) {
        System.out.print("you have selected option " + option + ": ");
        s.nextLine();
        switch (option) {
            case 1:
                System.out.println("Import Deck from text file.");
                OptionCUI.importDeckCUI(s);
                break;
            case 2:
                System.out.println("Export Deck to text file.");
                OptionCUI.ExportDeck(s);
                break;
            case 3:
                System.out.println("Import Cards To collection from text file.");
                OptionCUI.importCardsCUI(s);
                break;
            case 4:
                System.out.println("Export Collection to text file.");
                OptionCUI.exportCardsCUI(s);
                break;
            case 5:
                System.out.println("View Decks.");
                OptionCUI.selectDecks(s);
                break;
            case 6:
                System.out.println("Create a deck.");
                OptionCUI.createDeck(s);
                break;
            case 0:
                System.out.println("exit, now exiting.");
                System.exit(0);
                break;
        }
    }

}
