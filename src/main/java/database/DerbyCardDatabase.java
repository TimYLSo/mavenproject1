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

    public DerbyCardDatabase(DerbyDatabase database) {
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
            if (!(dbManager.tableExists(newTableName, statement))) {
            String sqlCreate = "create table " + newTableName + " (ScryfallID varchar(50) not null,"
                    + "Name varchar(150), OracleID varchar(50),"
                    + "OracleText varchar(2000), ManaCost varchar(50),SetName varchar(100),"
                    + "SetCode varchar(20),LowerCaseName varchar(150), PRIMARY KEY (ScryfallID))";
            statement.executeUpdate(sqlCreate);
            }

            String sqlInsert = "INSERT INTO " + newTableName + " (ScryfallID, Name, OracleID, OracleText, ManaCost, SetName, SetCode,LowerCaseName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            String sqlUpdate = "UPDATE " + newTableName + " SET Name = ?, OracleID = ?, OracleText = ?, ManaCost = ?, SetName = ?, SetCode = ?, LowerCaseName = ? WHERE ScryfallID = ?";;

            
            PreparedStatement insertStatement = dbManager.getConnection().prepareStatement(sqlInsert);
            PreparedStatement updateStatement = dbManager.getConnection().prepareStatement(sqlUpdate);
            for (int i = 0; i < jsonCardList.length(); i++) {
                JSONObject jsonCard = jsonCardList.getJSONObject(i);
                OracleCard currentCard = new OracleCard();
                String layout = jsonCard.getString("layout");

                if (!"token".equals(layout) && !"emblem".equals(layout) && !"art_series".equals(layout) && !"double_faced_token".equals(layout)) {
                    String name = jsonCard.getString("name");
                    String lower_case_name = name.toLowerCase();
                    currentCard.setCardName(lower_case_name);
                    if ("reversible_card".equals(layout)){
                     JSONObject face=   jsonCard.getJSONArray("card_faces").getJSONObject(0);
                    currentCard.setOracleID(face.getString("oracle_id"));
                    
                    }
                    else{currentCard.setOracleID(jsonCard.getString("oracle_id"));}
                    
                    currentCard.setScryfallID(jsonCard.getString("id"));
                    currentCard.setOracleText(jsonCard.optString("oracle_text"));
                    currentCard.setManaCost(jsonCard.optString("mana_cost"));
                    currentCard.setSetName(jsonCard.getString("set_name"));
                    currentCard.setSetCode(jsonCard.getString("set"));
                    if (idInDatabase(currentCard.getScryfallID())) {
                        updateStatement.setString(1, currentCard.getScryfallID());
                        updateStatement.setString(2, currentCard.getCardName());
                        updateStatement.setString(3, currentCard.getOracleID());
                        updateStatement.setString(4, currentCard.getOracleText());
                        updateStatement.setString(5, currentCard.getManaCost());
                        updateStatement.setString(6, currentCard.getSetName());
                        updateStatement.setString(7, currentCard.getSetCode());
                        updateStatement.setString(8, lower_case_name);
                        updateStatement.executeUpdate();

                    } // Set the values for the prepared statement
                    else {
                        insertStatement.setString(1, currentCard.getScryfallID());
                        insertStatement.setString(2, currentCard.getCardName());
                        insertStatement.setString(3, currentCard.getOracleID());
                        insertStatement.setString(4, currentCard.getOracleText());
                        insertStatement.setString(5, currentCard.getManaCost());
                        insertStatement.setString(6, currentCard.getSetName());
                        insertStatement.setString(7, currentCard.getSetCode());
                        insertStatement.setString(8, lower_case_name);
                        insertStatement.executeUpdate();
                    }

                    // Execute the insert
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
        } catch (NoSuchElementException ex) {
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

    public boolean idInDatabase(String id) throws SQLException {
        try {
            String sql = "SELECT * FROM ORACLECARDS WHERE ScryfallID = ? FETCH FIRST 1 ROW ONLY";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyCardDatabase.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        
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
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("There is no such card in the database: " + id);
        }
        return returnCard;
    }
}
