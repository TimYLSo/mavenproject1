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
    public Card(String name){
        super.setCardName(name);
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
