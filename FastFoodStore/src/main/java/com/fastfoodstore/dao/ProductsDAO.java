/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.dao;

import com.fastfoodstore.dto.ProductsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductsDAO implements DAOInterface<ProductsDTO>{
    
    public static ProductsDAO getInstance() {
        return new ProductsDAO();
    }

    @Override
    public int insert(ProductsDTO t) {
        
        int change = 0;
        
        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `products`(`productCode`, `productName`, `productPrice`, `productGenre`, `productSize`, `productImage`, `groupCode`, `inMenu`) "
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getProductCode());
            pst.setString(2, t.getProductName());
            pst.setFloat(3, t.getProductPrice());
            pst.setInt(4, t.getProductGenre());
            pst.setString(5, t.getProductSize() + "");
            pst.setString(6, t.getProductImage());
            pst.setString(7, t.getGroupCode());
            pst.setInt(8, t.getInMenu());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int update(ProductsDTO t) {
        
        int change = 0;
        
        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "UPDATE products "
                    + "SET productCode=?,productName=?,productPrice=?,productGenre=?,productSize=?,productImage=?,groupCode=?,inMenu=? " 
                    + "WHERE products.productCode =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getProductCode());
            pst.setString(2, t.getProductName());
            pst.setFloat(3, t.getProductPrice());
            pst.setInt(4, t.getProductGenre());
            pst.setString(5, t.getProductSize() + "");
            pst.setString(6, t.getProductImage());
            pst.setString(7, t.getGroupCode());
            pst.setInt(8, t.getInMenu());
            pst.setString(9, t.getProductCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int delete(ProductsDTO t) {
        
        return 0;
    }

    @Override
    public ArrayList<ProductsDTO> selectAll() {
        
        ArrayList<ProductsDTO> productList = new ArrayList<ProductsDTO>();
        boolean isData = false;
        
        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM products";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                ProductsDTO data = new ProductsDTO(
                    rs.getString("productCode"),
                    rs.getString("productName"),
                    rs.getFloat("productPrice"),
                    rs.getInt("productGenre"),
                    rs.getString("productSize").charAt(0), 
                    rs.getString("productImage"),
                        rs.getString("groupCode"),
                        rs.getInt("inMenu")
                    );
                productList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }
        
        if(isData) {
            return productList;
        } else {
            return null;
        }
    }

    @Override
    public ProductsDTO selectById(String id) {
        
        ProductsDTO product = new ProductsDTO();
        boolean isData = false;
        
        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM products WHERE products.productCode = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                product.setGroupCode(id);
                product.setProductName(rs.getString("productName"));
                product.setProductPrice(rs.getFloat("productPrice"));
                product.setProductGenre(rs.getInt("productGenre"));
                product.setProductSize(rs.getString("productSize").charAt(0));
                product.setProductImage(rs.getString("productImage"));
                product.setGroupCode(rs.getString("groupCode"));
                product.setInMenu(rs.getInt("inMenu")); 
                isData = true;
            }

            ConnectionData.closeConnection(connection);
            
        } catch (Exception e) {
            
            System.out.println("Select data failture " + e);
        }
        
        if(isData) {
            return product;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<ProductsDTO> selectByCondition(String condition, String colName) {
        
        ArrayList<ProductsDTO> productList = new ArrayList<ProductsDTO>();
        boolean isData = false;
        
        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * from products WHERE products." + colName + " = ? ";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, condition);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                ProductsDTO data = new ProductsDTO(
                    rs.getString("productCode"),
                    rs.getString("productName"),
                    rs.getFloat("productPrice"),
                    rs.getInt("productGenre"),
                    rs.getString("productSize").charAt(0), 
                    rs.getString("productImage"),
                    rs.getString("groupCode"),
                    rs.getInt("inMenu")
                    );
                    productList.add(data);
                    isData = true;
                }
           
            ConnectionData.closeConnection(connection);
            
        } catch (Exception e) {
            
            System.out.println("Select data failture " + e);
            
        }
        
        if(isData) {
            return productList;
        } else {
            return null;
        }
    }
    
}
