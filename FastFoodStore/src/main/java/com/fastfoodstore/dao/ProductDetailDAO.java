package com.fastfoodstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fastfoodstore.dto.ProductDetailDTO;

public class ProductDetailDAO implements DAOInterface<ProductDetailDTO> {

    @Override
    public int insert(ProductDetailDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `productdetail` (`productCode`, `ingredientCode`, `recipe`, `toChange`)"
                        +" VALUES (?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getProductCode());
            pst.setString(2, t.getIngredientCode());
            pst.setString(3, t.getRecipe());
            pst.setBoolean(4, t.getToChange());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int update(ProductDetailDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "UPDATE `productdetail`"
                        +" SET `productCode` = ?, `ingredientCode` = ?, `recipe` = ?, `toChange` = ?"
                        +" WHERE `productdetail`.`productCode` = ? AND `productdetail`.`ingredientCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getProductCode());
            pst.setString(2, t.getIngredientCode());
            pst.setString(3, t.getRecipe());
            pst.setBoolean(4, t.getToChange());
            pst.setString(5, t.getProductCode());
            pst.setString(6, t.getIngredientCode());

            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int delete(ProductDetailDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "DELETE FROM productdetail" 
                        +"WHERE `productdetail`.`productCode` = ? AND `productdetail`.`ingredientCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getProductCode());
            pst.setString(2, t.getIngredientCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public ArrayList<ProductDetailDTO> selectAll() {
        ArrayList<ProductDetailDTO> productDetailList = new ArrayList<ProductDetailDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM productDetail";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                ProductDetailDTO data = new ProductDetailDTO(
                    rs.getString("productCode"),
                    rs.getString("ingredientCode"),
                    rs.getString("recipe"),
                    rs.getBoolean("toChange")
                    );
                productDetailList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return productDetailList;
        } else {
            return null;
        }
    }

    @Override
    public ProductDetailDTO selectById(String id) {
        ProductDetailDTO productDetail = new ProductDetailDTO();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM productDetail WHERE productCode = ?";
            PreparedStatement pst = connection.prepareStatement(sql);    
            pst.setString(1, id);        
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                productDetail.setProductCode(rs.getString("productCode"));    
                productDetail.setIngredientCode(rs.getString("ingredientCode"));    
                productDetail.setRecipe(rs.getString("recipe"));    
                productDetail.setToChange(rs.getBoolean("toChange"));    
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return productDetail;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<ProductDetailDTO> selectByCondition(String condition, String colName) {
        ArrayList<ProductDetailDTO> productDetailList = new ArrayList<ProductDetailDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM productDetail WHERE ?";
            PreparedStatement pst = connection.prepareStatement(sql);  
            pst.setString(1, condition);        
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                ProductDetailDTO data = new ProductDetailDTO(
                    rs.getString("productCode"),
                    rs.getString("ingredientCode"),
                    rs.getString("recipe"),
                    rs.getBoolean("toChange")
                    );
                productDetailList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return productDetailList;
        } else {
            return null;
        }
    }
    
}
