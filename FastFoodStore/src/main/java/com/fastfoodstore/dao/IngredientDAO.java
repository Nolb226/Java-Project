package com.fastfoodstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fastfoodstore.dto.IngredientDTO;

public class IngredientDAO implements DAOInterface<IngredientDTO> {

    @Override
    public int insert(IngredientDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `ingredient` (`ingredientCode`, `ingredientName`)"
                        +" VALUES (?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getIngredientCode());
            pst.setString(2, t.getIngredientName());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int update(IngredientDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "UPDATE `ingredient`"
                        +" SET `ingredientCode` = ?, `ingredientName` = ?"
                        +" WHERE `ingredient`.`ingredientCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getIngredientCode());
            pst.setString(2, t.getIngredientName());
            pst.setString(3, t.getIngredientCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int delete(IngredientDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "DELETE FROM ingredient"
                        + "WHERE `ingredient`.`ingredientCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getIngredientCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public ArrayList<IngredientDTO> selectAll() {
        ArrayList<IngredientDTO> ingredientList = new ArrayList<IngredientDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM ingredient";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                IngredientDTO data = new IngredientDTO(
                    rs.getString("ingredientCode"),
                    rs.getString("ingredientName")
                    );
                ingredientList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return ingredientList;
        } else {
            return null;
        }
    }

    @Override
    public IngredientDTO selectById(String id) {
        IngredientDTO ingredient = new IngredientDTO();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM ingredient WHERE ingredientCode = ?";
            PreparedStatement pst = connection.prepareStatement(sql);     
            pst.setString(1, id);       
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                ingredient.setIngredientCode(rs.getString("ingredientCode"));    
                ingredient.setIngredientName(rs.getString("ingredientName"));    
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return ingredient;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<IngredientDTO> selectByCondition(String condition, String colName) {
        ArrayList<IngredientDTO> ingredientList = new ArrayList<IngredientDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM ingredient " + condition + "";
            PreparedStatement pst = connection.prepareStatement(sql);
            // pst.setString(1, condition);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                IngredientDTO data = new IngredientDTO(
                    rs.getString("ingredientCode"),
                    rs.getString("ingredientName")
                    );
                ingredientList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return ingredientList;
        } else {
            return null;
        }
    }
    
}
