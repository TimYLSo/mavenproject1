/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fileio;

import models.CollectionList;
import models.Deck;
import java.io.IOException;

/**
 *
 * @author User
 */
public interface ReadsFileToDeck {
    public Deck readDeckFromFile()throws IOException;
}
