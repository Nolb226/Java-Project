/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.dao;

import java.util.ArrayList;

import com.fastfoodstore.dto.BillDetailDTO;
import com.fastfoodstore.dto.BillsDTO;
import com.fastfoodstore.dto.FunctionsDTO;
import com.fastfoodstore.dto.ProductsDTO;
import java.sql.Date;
// import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class TestDao {
    
    
    public static void main(String[] args) {
        // ArrayList<BillsDTO> data = BillsDAO.getInstance().selectByCondition("111","billCode");
        ArrayList<FunctionsDTO> data = FunctionsDAO.getInstance().selectAll();
        for (FunctionsDTO a : data) {
            System.out.println(a.getFunctionName());
            
        }
        // BillsDTO t = new BillsDTO("116",
        // 1,
        // new Date(2023, 4, 1),
        // 2,
        // 50000.00f,
        // 50000.00f,
        // 0.00f,
        // "11",
        // "P667942");
        // int a = BillsDAO.getInstance().insert(t);
        // System.out.println(a);
        
    }

    
}
