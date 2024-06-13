/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cui.m;

import groupmanager.DecksManager;
import com.mycompany.mtgCardManager.MtgCardManager;

/**
 *
 * @author User
 */
public class CUIViewDecks {
    public static void display(int i){
        int index = i-1;
    DecksManager manager = MtgCardManager.allDecks;
    String output = manager.getDeck(index).entireDecktoString();
    System.out.println(output);
    }
}
