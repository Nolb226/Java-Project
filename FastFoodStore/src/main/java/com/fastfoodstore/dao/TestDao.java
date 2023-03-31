/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.dao;

import com.fastfoodstore.dto.ProductsDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class TestDao {
    
    
    public static void main(String[] args) {
        ProductsDTO data = ProductsDAO.getInstance().selectById("B02R");
        
       
            System.out.println(data.getProductName());
        
    }
    
}
