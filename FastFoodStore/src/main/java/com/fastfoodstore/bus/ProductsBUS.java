/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.ProductsDAO;
import com.fastfoodstore.dto.ProductsDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class ProductsBUS {
    
    public static ArrayList<ProductsDTO> getAllProducts() {
        return ProductsDAO.getInstance().selectAll();
    }
    
    public static ArrayList<ProductsDTO> getProductsInGroup(String code) {
        return ProductsDAO.getInstance().selectByCondition("groupCode = '"+ code +"' and inMenu = 1", "");
    }
    
    public static ProductsDTO getProductsByCode(String code) {
        return ProductsDAO.getInstance().selectById(code);
    }
    
    public static int updateProduct(ProductsDTO t) {
         return ProductsDAO.getInstance().update(t);
    }
    
    public static int insertProduct(ProductsDTO t) {
         return ProductsDAO.getInstance().insert(t);
    }
}
