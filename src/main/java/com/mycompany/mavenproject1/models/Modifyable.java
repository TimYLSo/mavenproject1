/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.mavenproject1.models;

/**
 *
 * @author User
 */
public interface Modifyable {
    public void addCard(Card card, int quantity, boolean isSideboard);
    public void removeCardByName(String cardID,int quantity, boolean isSideboard);
}
