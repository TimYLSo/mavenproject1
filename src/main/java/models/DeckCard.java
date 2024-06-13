/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author User
 */
public class DeckCard extends OracleCard {
    protected boolean isSideboard;

    public boolean isIsSideboard() {
        return isSideboard;
    }

    public void setIsSideboard(boolean isSideboard) {
        this.isSideboard = isSideboard;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    protected int quantity;
    public DeckCard(int quantity ,boolean isSideboard){
    this.isSideboard= isSideboard;
    this.quantity = quantity;
    
    }
}
