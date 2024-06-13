/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cui.functions;

import cui.m.CUIMenu;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class InputGetter {

    public static int getOption(int options, String printMessage, Scanner s) {
        System.out.println(printMessage);
        int option;
        
        while (true) {
            try {
                option = s.nextInt();

                if ((option > options)) {
                    throw new ArithmeticException("This option does not exist");

                } else if (option == 0) {
                    s.close();
                    System.out.println("Now exiting...");
                    System.exit(0);
                    
                }
                s.nextLine();
                return option;
            } catch (ArithmeticException e) {
                System.out.println("This option does not exist");
            } catch (InputMismatchException e) {
                System.out.println("This is not a correct number");
                s.nextLine();

            }
        }

    }

    public static String getString(String printMessage, Scanner s) {
        System.out.println(printMessage);
        String outputString;
       
        while (true) {
            try {
                outputString = s.nextLine(); 

                if ("0".equals(outputString)) {
                    s.close();
                    System.exit(0);
                }

                return outputString;
            } catch (InputMismatchException e) {
                System.out.println("This is not an option");
                s.nextLine();
            }
        }
        

    }
                    public static String getString(String printMessage, Scanner s, boolean noNull) {
        System.out.println(printMessage);
        String outputString;
       
        while (true) {
            try {
                outputString = s.nextLine();
                

                if ("0".equals(outputString)) {
                    s.close();
                    System.exit(0);
                }
                if ("".equals(outputString) && noNull){
                throw new InputMismatchException();
                }
                

                return outputString;
            } catch (InputMismatchException e) {
                System.out.println("This is not an option");
                s.nextLine();
            }
        }

    }

    
    
}
