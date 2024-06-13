/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import database.DerbyCardDatabase;
import java.util.Set;
import java.util.NoSuchElementException;
/**
 *
 * @author User
 */
public class OracleCard {
    protected String OracleID;
    protected String ScryfallID;
    protected String OracleText;
    protected String ManaCost;
    protected int ManaValue;
    protected String CardName;
    protected String SetName;
    protected String SetCode;
    protected boolean hasBeenUpdated;
    public String getSetCode() {
        return SetCode;
    }

    public void setSetCode(String SetCode) {
        this.SetCode = SetCode;
    }
    
   public String getOracleID() {
        return OracleID;
    }

    public boolean isHasBeenUpdated() {
        return hasBeenUpdated;
    }

    public void setHasBeenUpdated(boolean hasBeenUpdated) {
        this.hasBeenUpdated = hasBeenUpdated;
    }

    public void setOracleID(String OracleID) {
        this.OracleID = OracleID;
    }

    public String getScryfallID() {
        return ScryfallID;
    }

    public void setScryfallID(String ScryfallID) {
        this.ScryfallID = ScryfallID;
    }

    public String getOracleText() {
        return OracleText;
    }

    public void setOracleText(String OracleText) {
        this.OracleText = OracleText;
    }

    public String getManaCost() {
        return ManaCost;
    }

    public void setManaCost(String ManaCost) {
        this.ManaCost = ManaCost;
    }

    public int getManaValue() {
        return ManaValue;
    }

    public void setManaValue(int ManaValue) {
        this.ManaValue = ManaValue;
    }


    public String getCardName() {
        return CardName;
    }

    public void setCardName(String CardName) {
        this.CardName = CardName;
    }

    public String getSetName() {
        return SetName;
    }

    public void setSetName(String SetName) {
        this.SetName = SetName;
    }
    
    @Override
    public String toString() {
        String returnString = this.CardName + " ("+this.getSetCode()+")";
    return returnString;
       
}
    public void updateCardInfo(DerbyCardDatabase database){
    try{ 
        OracleCard cardinfo = database.findCardByName(this.getCardName());
        if(cardinfo == null){throw new NoSuchElementException ();}
        else{
        this.setManaCost(cardinfo.getManaCost());
        this.setManaValue(cardinfo.getManaValue());
        this.setSetName(cardinfo.getSetName());
        this.setOracleID(cardinfo.getOracleID());
        
        this.setSetCode(cardinfo.getSetCode());
        this.setHasBeenUpdated(true);
        if (this.getScryfallID() == null)
        {this.setScryfallID(cardinfo.getScryfallID());}
        
        }
        
    }
    catch(NoSuchElementException ex){
    System.out.println("The card "+this.getCardName() +" can not be found in the database, please check formatting or the card may not exist.");
    throw new NoSuchElementException ();
    }
    catch(Exception e){
     System.out.print("The following error occured \n" + e);
    }
    
    }
}
