/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author User
 */
public class Card extends OracleCard {

    protected String language = "english";
    protected boolean foil = false;
    protected String condition = "nm";

    public Card(String name) {
        super.setCardName(name);
    }

    public Card(OracleCard parent) {
        this.CardName = parent.CardName;
        this.ManaCost = parent.ManaCost;
        this.OracleID = parent.OracleID;
        this.ScryfallID = parent.ScryfallID;
        this.OracleText = parent.OracleText;
        this.ManaCost = parent.ManaCost;
        this.ManaValue = parent.ManaValue;
        this.CardName = parent.CardName;
        this.SetName = parent.SetName;
        this.SetCode = parent.SetCode;

    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isFoil() {
        return foil;
    }

    public void setFoil(boolean isFoil) {
        this.foil = isFoil;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

}
