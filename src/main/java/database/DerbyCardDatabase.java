/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.OracleCard;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author User
 */
public class DerbyCardDatabase extends CardDatabase {

    private DerbyDatabase dbManager;

    public DerbyCardDatabase() {
        dbManager = new DerbyDatabase();
    }
    public DerbyCardDatabase(DerbyDatabase database){
     dbManager = database;
    }

    public Connection getConnection() {
        return dbManager.getConnection();
    }

    public DerbyDatabase get_DBManager() {
        return dbManager;

    }

    public void updateDatabase(String filename) {

        try {
            FileReader jsonReader = new FileReader(filename);
            JSONTokener jsonTokener = new JSONTokener(jsonReader);

            JSONArray jsonCardList = new JSONArray(jsonTokener);
            String newTableName = "ORACLECARDS";
            Statement statement = getConnection().createStatement();
            if (dbManager.tableExists(newTableName, statement)) {
                String sqlDrop = "DROP TABLE " + newTableName;
                statement.executeUpdate(sqlDrop);
            }
            String sqlCreate = "create table " + newTableName + " (ScryfallID varchar(50) not null,"
                    + "Name varchar(150), OracleID varchar(50),"
                    + "OracleText varchar(2000), ManaCost varchar(50),SetName varchar(100),"
                    + "SetCode varchar(5),LowerCaseName varchar(150), PRIMARY KEY (ScryfallID))";
            String sqlInsert = "INSERT INTO " + newTableName + " (ScryfallID, Name, OracleID, OracleText, ManaCost, SetName, SetCode,LowerCaseName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            statement.executeUpdate(sqlCreate);
            PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(sqlInsert);
            for (int i = 0; i < jsonCardList.length(); i++) {
                JSONObject jsonCard = jsonCardList.getJSONObject(i);
                OracleCard currentCard = new OracleCard();
                String layout = jsonCard.getString("layout");

                if (!"token".equals(layout) && !"emblem".equals(layout) && !"art_series".equals(layout) && !"double_faced_token".equals(layout)) {
                    String name = jsonCard.getString("name");
                    String lower_case_name = name.toLowerCase();
                    currentCard.setCardName(lower_case_name);
                    currentCard.setOracleID(jsonCard.getString("oracle_id"));
                    currentCard.setScryfallID(jsonCard.getString("id"));
                    currentCard.setOracleText(jsonCard.optString("oracle_text"));
                    currentCard.setManaCost(jsonCard.optString("mana_cost"));
                    currentCard.setSetName(jsonCard.getString("set_name"));
                    currentCard.setSetCode(jsonCard.getString("set"));

                    // Set the values for the prepared statement
                    preparedStatement.setString(1, currentCard.getScryfallID());
                    preparedStatement.setString(2, currentCard.getCardName());
                    preparedStatement.setString(3, currentCard.getOracleID());
                    preparedStatement.setString(4, currentCard.getOracleText());
                    preparedStatement.setString(5, currentCard.getManaCost());
                    preparedStatement.setString(6, currentCard.getSetName());
                    preparedStatement.setString(7, currentCard.getSetCode());
                    preparedStatement.setString(8, lower_case_name);

                    // Execute the insert
                    preparedStatement.executeUpdate();
                }
            }

            jsonReader.close();
        } catch (IOException | JSONException | NullPointerException e) {
            System.out.println("There is a problem with the formatting of the JSON file.Please update the database and try again.\n" + e);

        } catch (SQLException ex) {
            Logger.getLogger(DerbyCardDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



    @Override
    public OracleCard findCardByName(String name) {
        OracleCard returnCard = null;
        try {
            String lower_case_name = reformatName(name);

            String sql = "SELECT * FROM ORACLECARDS WHERE LowerCaseName = ? FETCH FIRST 1 ROW ONLY";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, lower_case_name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                
                String id = resultSet.getString("ScryfallID");
                String resultName = resultSet.getString("Name");
                String oracleID = resultSet.getString("OracleID");
                String oracleText = resultSet.getString("OracleText");
                String manaCost = resultSet.getString("ManaCost");
                String setName = resultSet.getString("SetName");
                String SetCode = resultSet.getString("SetCode");
                returnCard = new OracleCard();
                returnCard.setCardName(resultName);
                returnCard.setScryfallID(id);
                returnCard.setOracleID(oracleID);
                returnCard.setOracleText(oracleText);
                returnCard.setManaCost(manaCost);
                returnCard.setSetName(setName);
                returnCard.setSetCode(SetCode);
            } else {
                System.out.println("No matching rows found.");
                 throw new NoSuchElementException("There is no such card in the database: " + name);
            }
            return returnCard;
        } catch (SQLException ex) {
            Logger.getLogger(DerbyCardDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(NoSuchElementException ex){
         throw new NoSuchElementException("There is no such card in the database: " + name);
        }
        return returnCard;
    }

    private static String reformatName(String name) { // this method reformats the name string because some files use different formats for certain card names.
        String newName = name.toLowerCase();
        if (name.contains("/") && name.indexOf("/") == name.lastIndexOf("/")) {
            newName = newName.replaceFirst("/", " // ");
        }
        return newName;
    }

    @Override
    public OracleCard findCardByOracleID(String id) {
                OracleCard returnCard = null;
        try {

            String sql = "SELECT * FROM ORACLECARDS WHERE ScryfallID = ? FETCH FIRST 1 ROW ONLY";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                
                String resultName = resultSet.getString("Name");
                String oracleID = resultSet.getString("OracleID");
                String oracleText = resultSet.getString("OracleText");
                String manaCost = resultSet.getString("ManaCost");
                String setName = resultSet.getString("SetName");
                String SetCode = resultSet.getString("SetCode");
                returnCard = new OracleCard();
                returnCard.setCardName(resultName);
                returnCard.setScryfallID(id);
                returnCard.setOracleID(oracleID);
                returnCard.setOracleText(oracleText);
                returnCard.setManaCost(manaCost);
                returnCard.setSetName(setName);
                returnCard.setSetCode(SetCode);
            } else {
                System.out.println("No matching rows found.");
                 throw new NoSuchElementException("There is no such card in the database: " + id);
            }
            return returnCard;
        } catch (SQLException ex) {
            Logger.getLogger(DerbyCardDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(NoSuchElementException ex){
         throw new NoSuchElementException("There is no such card in the database: " + id);
        }
        return returnCard;}
}
