/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.ProductDetailDAO;
import com.fastfoodstore.dto.ProductDetailDTO;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ProductDetailBUS {
     public static ArrayList<ProductDetailDTO> getProductDetail() {
        return new ProductDetailDAO().selectAll();
    }
     
    public static void insertProductDetail(ProductDetailDTO newProductDetail) {
        new ProductDetailDAO().insert(newProductDetail);
    }
    
    public static void updateProductDetail(ProductDetailDTO newProductDetail) {
        new ProductDetailDAO().update(newProductDetail);
    }
    
    public static void deleteProductDetail(ProductDetailDTO oldProductDetail) {
        new ProductDetailDAO().delete(oldProductDetail);
    }
}
