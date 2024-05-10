/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.mavenproject1.database;

import com.mycompany.mavenproject1.models.OracleCard;

/**
 *
 * @author User
 */
public interface DatabaseSearch {
    public OracleCard findCardByOracleID(String id);
    public OracleCard findCardByName(String name);
}
