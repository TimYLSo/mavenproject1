/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.cui;

import com.mycompany.mavenproject1.Mavenproject1;
import com.mycompany.mavenproject1.models.groupmanager.DecksManager;

/**
 *
 * @author User
 */
public class CUIViewDecks {
    public static void display(int i){
        int index = i-1;
    DecksManager manager = Mavenproject1.allDecks;
    String output = manager.getDeck(index).entireDecktoString();
    System.out.println(output);
    }
}
