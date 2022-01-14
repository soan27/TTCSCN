/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hoang Xoan
 */
public class DBConnector {
    public static Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String username = "root";
        String password = "";
        String connStr = "jdbc:mysql://localhost:3306/orfarm";
        return DriverManager.getConnection(connStr,username,password);
    }
    
}


