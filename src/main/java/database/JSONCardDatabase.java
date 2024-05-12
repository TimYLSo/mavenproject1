/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import models.Card;
import models.OracleCard;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import org.json.*;
/**
 *
 * @author User
 */
public class JSONCardDatabase extends CardDatabase{
    protected HashMap  CardDatabase = new HashMap<String,Card>(40000);
    
    
    public JSONCardDatabase(String filename){
        
    try{
    FileReader jsonReader = new FileReader(filename);
    JSONTokener jsonTokener = new JSONTokener(jsonReader);

    JSONArray jsonCardList = new JSONArray(jsonTokener);
        
    for (int i = 0; i < jsonCardList.length(); i++) 
    {
    JSONObject jsonCard = jsonCardList.getJSONObject(i);
    OracleCard currentCard = new OracleCard();
    String layout = jsonCard.getString("layout");
    if("token".equals(layout) ||"emblem".equals(layout)||"art_series".equals(layout)||"double_faced_token".equals(layout) ){
    }
    else
    {
    String name = jsonCard.getString("name");
    String key = name.toLowerCase();
    currentCard.setCardName(name);
    currentCard.setOracleID(jsonCard.getString("oracle_id"));
    currentCard.setScryfallID(jsonCard.getString("id"));
    currentCard.setOracleText(jsonCard.optString("oracle_text"));
    currentCard.setManaCost(jsonCard.optString("mana_cost"));
    currentCard.setSetName(jsonCard.getString("set_name"));
    currentCard.setSetCode(jsonCard.getString("set"));

    CardDatabase.put(key,currentCard);
    }
    }
    jsonReader.close();
    }
    catch (IOException | JSONException |NullPointerException e){
        System.out.println("There is a problem with the formatting of the JSON file.Please update the database and try again.\n"+e);
        
    }
    }
    @Override
    public OracleCard findCardByOracleID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public OracleCard findCardByName(String name) {
        String key = JSONCardDatabase.reformatName(name);
        OracleCard card = (OracleCard) this.CardDatabase.get(key);
        if (card == null){ throw new NoSuchElementException("There is no such card in the database: " + name);}
        return card;
    }
    private static String reformatName(String name){ // this method reformats the name string because some files use different formats for certain card names.
        String newName = name.toLowerCase();
        if(name.contains("/") && name.indexOf("/")==name.lastIndexOf("/")){
           newName= newName.replaceFirst("/", " // ");
        }
        return newName;
    }
    @Override
    public String toString(){
    String string = "The size of this database is: ";
    return string + this.CardDatabase.size()+ "\n";
    }
}
