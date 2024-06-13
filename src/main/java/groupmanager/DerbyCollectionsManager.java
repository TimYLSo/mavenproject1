/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package groupmanager;

import database.DerbyCardDatabase;
import database.DerbyDatabase;
import fileio.CSVCardReader;
import fileio.ListWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Card;
import models.CollectionList;
import models.Deck;
import models.OracleCard;

/**
 *
 * @author User
 */
public class DerbyCollectionsManager {

    private DerbyDatabase dbManager;
    private DerbyCardDatabase cardDB;

    public DerbyCollectionsManager(DerbyCardDatabase database) {
        dbManager = database.get_DBManager();
        cardDB = database;
        try {

            String tableName = "LISTS";
            Statement statement = dbManager.getConnection().createStatement();
            if (!(dbManager.tableExists(tableName, statement))) {
                String sqlCreate = "create table " + tableName + " (ListID varchar(50) not null,"
                        + "ListName varchar(70),"
                        + "PRIMARY KEY (DeckID))";
                statement.executeUpdate(sqlCreate);

            }
            String tableName2 = "LISTCARDS";
            Statement statement2 = dbManager.getConnection().createStatement();
            if (!(dbManager.tableExists(tableName2, statement2))) {
                String sqlCreate = "create table " + tableName2
                        + " (ListID varchar(50) not null,"
                        + "CardID varchar(50)  not null,"
                        + "Foil BOOLEAN,"
                        + "Condition varchar(5),"
                        + "Language varchar(50),"
                        + "FOREIGN KEY (ListID) REFERENCES LISTS(ListID),"
                        + "FOREIGN KEY (CardID) REFERENCES ORACLECARDS(ScryfallID))";
                statement2.executeUpdate(sqlCreate);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDecksManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Connection getConnection() {
        return dbManager.getConnection();
    }

    public DerbyDatabase get_DBManager() {
        return dbManager;

    }

    public ArrayList<String> getAllLists() throws SQLException {
        ArrayList<String> lists = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        String sql = "SELECT * FROM LISTS";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String deckName = resultSet.getString("ListID");
            lists.add(deckName);
        }
        return lists;
    }

    private String getNameFromID(String deckID) throws SQLException, NoSuchElementException {
        try {
            String sql = "SELECT * FROM LISTS WHERE ListID = ? FETCH FIRST 1 ROW ONLY";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, deckID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                String ListName = resultSet.getString("ListName");
                return ListName;
            } else {
                System.out.println("No matching rows found.");
                throw new NoSuchElementException("There is no such List in the database: " + deckID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDecksManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException();

        }

    }

    public boolean listInTable(String listID) throws NoSuchElementException, SQLException {

        try {
            String sql = "SELECT * FROM LISTS WHERE ListID = ? FETCH FIRST 1 ROW ONLY";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, listID);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDecksManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException();

        }

    }

    public CollectionList getList(String listID) throws NoSuchElementException, SQLException {

        String deckName = getNameFromID(listID);
        CollectionList list = new CollectionList(deckName, listID);
        String sql = "SELECT * FROM LISTCARDS WHERE ListID = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1, listID);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String scryfallID = resultSet.getString("CardID");
            OracleCard parent = cardDB.findCardByOracleID(scryfallID);
            Card card = new Card(parent);
            String language = resultSet.getString("Language");
            boolean foil = resultSet.getBoolean("Foil");
            String condition = resultSet.getString("Condition");
            card.setLanguage(language);
            card.setFoil(foil);
            card.setCondition(condition);
            list.addCard(card);
        }
        return list;
    }

    public void deleteList(String listID) throws SQLException {
        String sql = "DELETE  FROM LISTCARDS WHERE ListID = ?";
        PreparedStatement deleteStatement = getConnection().prepareStatement(sql);
        deleteStatement.setString(1, listID);
        deleteStatement.execute();
        sql = "DELETE  FROM LISTS WHERE ListID = ?";
        PreparedStatement deleteDeckStatement = getConnection().prepareStatement(sql);
        deleteDeckStatement.setString(1, listID);
        deleteDeckStatement.execute();
    }

    public void saveList(CollectionList list) throws SQLException {
        String tableName = "LISTCARDS";
        String listID = list.getId().toString();
        if (listInTable(listID)) {
            deleteList(listID);
        }
        // inserts kname and id into LISTS
        String sqlInsertDeck = "INSERT INTO LISTS (ListID,ListName) VALUES (?, ?)";
        PreparedStatement insertDeckStatement = getConnection().prepareStatement(sqlInsertDeck);
        insertDeckStatement.setString(1, listID);
        insertDeckStatement.setString(2, list.getCollectionName());
        insertDeckStatement.execute();
        //inserts cards into LISTS
        String sqlInsert = "INSERT INTO " + tableName + " (ListID,CardID,Foil, Condition, Language) VALUES (?, ?, ?, ?,?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sqlInsert);
        preparedStatement.setString(1, listID);
        ArrayList<Card> cardList = list.getCardList();
        for (Card c : cardList) {
            String cardID = c.getScryfallID();
            boolean foil= c.isFoil();
            String condition = c.getCondition();
            String language = c.getLanguage();
            preparedStatement.setString(2, cardID);
            preparedStatement.setBoolean(3, foil);
            preparedStatement.setString(4, condition);
            preparedStatement.setString(5, language);
            preparedStatement.execute();
        }

    }

    
}
