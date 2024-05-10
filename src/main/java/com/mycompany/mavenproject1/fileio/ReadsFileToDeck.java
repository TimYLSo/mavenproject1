/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.mavenproject1.fileio;

import com.mycompany.mavenproject1.models.CollectionList;
import com.mycompany.mavenproject1.models.Deck;
import java.io.IOException;

/**
 *
 * @author User
 */
public interface ReadsFileToDeck {
    public Deck readDeckFromFile()throws IOException;
}
