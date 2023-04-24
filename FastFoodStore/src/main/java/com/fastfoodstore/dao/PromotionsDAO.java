package com.fastfoodstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fastfoodstore.dto.PromotionsDTO;

public class PromotionsDAO implements DAOInterface<PromotionsDTO> {

    @Override
    public int insert(PromotionsDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `promotions` (`promoCode`, `promoGenre`, `productCode`, `discount`, `dueDate`)"
                        +" VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getPromoCode());
            pst.setString(2, t.getPromoGenre());
            pst.setString(3, t.getProductCode());
            pst.setFloat(4, t.getDiscount());
            pst.setBoolean(5, t.getDueDate());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int update(PromotionsDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "UPDATE `promotions`"
                        +" SET `promoCode` = ?, `promoGenre` = ?, `productCode` = ?, `discount` = ?, `dueDate` = ?"
                        +" WHERE `promotions`.`promoCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getPromoCode());
            pst.setString(2, t.getPromoGenre());
            pst.setString(3, t.getProductCode());
            pst.setFloat(4, t.getDiscount());
            pst.setBoolean(5, t.getDueDate());
            pst.setString(6, t.getPromoCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int delete(PromotionsDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "DELETE FROM promotions "
                        +" WHERE `promotions`.`promoCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getPromoCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public ArrayList<PromotionsDTO> selectAll() {
        ArrayList<PromotionsDTO> promotionList = new ArrayList<PromotionsDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM promotion";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                PromotionsDTO data = new PromotionsDTO(
                    rs.getString("promoCode"),
                    rs.getString("promoGenre"),
                    rs.getString("productCode"),
                    rs.getBoolean("dueDate"),
                    rs.getFloat("discount")
                    );
                promotionList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return promotionList;
        } else {
            return null;
        }
    }

    @Override
    public PromotionsDTO selectById(String id) {
        PromotionsDTO promotion = new PromotionsDTO();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM promotion WHERE promoCode = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, id);            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                promotion.setPromoCode(rs.getString("promoCode"));    
                promotion.setPromoGenre(rs.getString("promoGenre"));    
                promotion.setProductCode(rs.getString("productCode"));    
                promotion.setDueDate(rs.getBoolean("dueDate"));    
                promotion.setDiscount(rs.getFloat("discount"));    
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return promotion;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<PromotionsDTO> selectByCondition(String condition, String colName) {
        ArrayList<PromotionsDTO> promotionList = new ArrayList<PromotionsDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM promotion " + condition + "";
            PreparedStatement pst = connection.prepareStatement(sql);
            // pst.setString(1, condition);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                PromotionsDTO data = new PromotionsDTO(
                    rs.getString("promoCode"),
                    rs.getString("promoGenre"),
                    rs.getString("productCode"),
                    rs.getBoolean("dueDate"),
                    rs.getFloat("discount")
                    );
                promotionList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return promotionList;
        } else {
            return null;
        }
    }
    
}
