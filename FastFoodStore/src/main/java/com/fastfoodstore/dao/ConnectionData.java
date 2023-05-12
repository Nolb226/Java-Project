/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.dao;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

/**
 *
 * @author ADMIN
 */
public class ConnectionData {
    
    public static Connection getConnection() {
        Connection c =null;
        try { 
            
            DriverManager.deregisterDriver(new Driver());
            String url = "jdbc:mySQL://localhost:3306/ffs";
            String userName = "root";
            String password = "";
            
            c = DriverManager.getConnection(url, userName, password);
            
//            System.out.println("Connect successfully");
            
        } catch (Exception e) {
            
            System.out.println("Connection failure");
            System.out.println(e);
            
        }
        return c;
    }
    
    public static void closeConnection(Connection c) {
        try {
            if(c != null) {
                c.close();
//                System.out.println("Close connection successfully");
            }
        } catch (Exception e) { 
            
            System.out.println("Close connection failure");
            System.out.println(e);
        }
    }
    
     public static void printInfo(Connection c) {
        try {
            if(c != null) {
                
                DatabaseMetaData metaData = c.getMetaData();
                
                System.out.println(metaData.getDatabaseProductName());
            }
        } catch (Exception e) {
            
            System.out.println(e);
            
        }
    }
}
