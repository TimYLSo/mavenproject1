/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fileio;

import models.CollectionList;
import java.io.IOException;

/**
 *
 * @author User
 */
public interface ReadsFileToList {
     public CollectionList readListFromFile(String listName)throws IOException;
}
