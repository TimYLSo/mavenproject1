/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package groupmanager;

import database.DerbyCardDatabase;
import database.DerbyDatabase;
import fileio.DeckWriter;
import fileio.TextFileCardReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import models.Deck;
import models.OracleCard;

/**
 *
 * @author User
 */
public class DerbyDecksManager {

    private DerbyDatabase dbManager;
    private DerbyCardDatabase cardDB;

    public DerbyDecksManager() {
        dbManager = new DerbyDatabase();
        cardDB = new DerbyCardDatabase(dbManager);
    }

    public DerbyDecksManager(DerbyCardDatabase database) {
        dbManager = database.get_DBManager();
        cardDB = database;
        try {

            String tableName = "DECKS";
            Statement statement = dbManager.getConnection().createStatement();
            if (!(dbManager.tableExists(tableName, statement))) {
                String sqlCreate = "create table " + tableName + " (DeckID varchar(50) not null,"
                        + "DeckName varchar(70),"
                        + "PRIMARY KEY (DeckID))";
                statement.executeUpdate(sqlCreate);

            }
            String tableName2 = "DECKCARDS";
            Statement statement2 = dbManager.getConnection().createStatement();
            if (!(dbManager.tableExists(tableName2, statement2))) {
                String sqlCreate = "create table " + tableName2 + " (DeckID varchar(50) not null,"
                        + "CardID varchar(50)  not null,"
                        + "Quantity int  not null,"
                        + "IsSideboard BOOLEAN,"
                        + "FOREIGN KEY (DeckID) REFERENCES DECKS(DeckID),"
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

    public ArrayList<String> getAllDecks() throws SQLException {
        ArrayList<String> decks = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        String sql = "SELECT * FROM DECKS";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String deckName = resultSet.getString("DeckID");
            decks.add(deckName);
        }
        return decks;
    }

    private String getNameFromID(String deckID) throws SQLException, NoSuchElementException {
        try {
            String sql = "SELECT * FROM DECKS WHERE DeckID = ? FETCH FIRST 1 ROW ONLY";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, deckID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                String deckName = resultSet.getString("DeckName");
                return deckName;
            } else {
                System.out.println("No matching rows found.");
                throw new NoSuchElementException("There is no such deck in the database: " + deckID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDecksManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException();

        }

    }

    public boolean deckInTable(String deckID) throws NoSuchElementException, SQLException {

        try {
            String sql = "SELECT * FROM DECKS WHERE DeckID = ? FETCH FIRST 1 ROW ONLY";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, deckID);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDecksManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException();

        }

    }

    public Deck getDeck(String deckID) throws NoSuchElementException, SQLException {
        String deckName = getNameFromID(deckID);
        Deck deck = new Deck(deckName, deckID);
        String sql = "SELECT * FROM DECKCARDS WHERE DeckID = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1, deckID);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String scryfallID = resultSet.getString("CardID");
            OracleCard card = cardDB.findCardByOracleID(scryfallID);
            boolean isSideboard = resultSet.getBoolean("IsSideboard");
            int quantity = resultSet.getInt("Quantity");
            deck.addCard(card, quantity, isSideboard);
        }
        return deck;
    }

    public void deleteDeck(String deckID) throws SQLException {
        String sql = "DELETE  FROM DECKCARDS WHERE DeckID = ?";
        PreparedStatement deleteStatement = getConnection().prepareStatement(sql);
        deleteStatement.setString(1, deckID);
        deleteStatement.execute();
        sql = "DELETE  FROM DECKS WHERE DeckID = ?";
        PreparedStatement deleteDeckStatement = getConnection().prepareStatement(sql);
        deleteDeckStatement.setString(1, deckID);
        deleteDeckStatement.execute();
    }

    public void saveDeck(Deck deck) throws SQLException {
        String tableName = "DECKCARDS";
        String deckID = deck.getUUID();
        if (deckInTable(deckID)) {
            deleteDeck(deckID);
        }
        // inserts deckname and id into DECKS
        String sqlInsertDeck = "INSERT INTO DECKS (DeckID,DeckName) VALUES (?, ?)";
        PreparedStatement insertDeckStatement = getConnection().prepareStatement(sqlInsertDeck);
        insertDeckStatement.setString(1, deckID);
        insertDeckStatement.setString(2, deck.getDeckName());
        insertDeckStatement.execute();
        //inserts cards into DECKCARDS
        String sqlInsert = "INSERT INTO " + tableName + " (DeckID,CardID, Quantity, IsSideboard) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sqlInsert);
        preparedStatement.setString(1, deckID);
        HashMap mainDeck = deck.getDeckContents();
        Set maindeckset = mainDeck.keySet();
        Iterator<OracleCard> maindeckIterator = maindeckset.iterator();

        boolean isSideboard = false;
        preparedStatement.setBoolean(4, isSideboard);
        while (maindeckIterator.hasNext()) {
            OracleCard card = maindeckIterator.next();
            int quantity = (int) mainDeck.get(card);
            preparedStatement.setInt(3, quantity);
            String cardID = card.getScryfallID();
            preparedStatement.setString(2, cardID);
            preparedStatement.execute();

        }
        isSideboard = true;
        preparedStatement.setBoolean(4, isSideboard);
        HashMap sideboard = deck.getSideboard();
        Set sideboardSet = sideboard.keySet();
        Iterator<OracleCard> sideboardIterator = sideboardSet.iterator();
        while (sideboardIterator.hasNext()) {
             OracleCard card = sideboardIterator.next();
            int quantity = (int) sideboard.get(card);
            preparedStatement.setInt(3, quantity);
            String cardID = card.getScryfallID();
            preparedStatement.setString(2, cardID);
            preparedStatement.execute();
        }
    }

    public boolean isInManager(String DeckName) {
        return true;
    }

    public void ExportDeck(Deck deck, String FilePath) {
    }

}
