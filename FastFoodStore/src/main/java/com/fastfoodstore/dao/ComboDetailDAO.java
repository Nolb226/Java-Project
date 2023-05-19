package com.fastfoodstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fastfoodstore.dto.ComboDetailDTO;

public class ComboDetailDAO implements DAOInterface<ComboDetailDTO> {
    
    public static ComboDetailDAO getInstance() {
        return new ComboDetailDAO();
    }

    @Override
    public int insert(ComboDetailDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `combodetail` (`comboCode`, `productCode`)"
                        +" VALUES (?, ?);";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getComboCode());
            pst.setString(2, t.getProductCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
            return change;
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
            return change;
        }
        
    }

    @Override
    public int update(ComboDetailDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "UPDATE `combodetail`"
                        +" SET `comboCode` = ?, `productCode` = ?"
                        +" WHERE `combodetail`.`comboCode` = ? AND `combodetail`.`productCode` = ?;";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getComboCode());
            pst.setString(2, t.getProductCode());
            pst.setString(3, t.getComboCode());
            pst.setString(4, t.getProductCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int delete(ComboDetailDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "DELETE FROM combodetail" 
                        +"WHERE `combodetail`.`comboCode` = ? AND `combodetail`.`productCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getComboCode());
            pst.setString(2, t.getProductCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public ArrayList<ComboDetailDTO> selectAll() {
        ArrayList<ComboDetailDTO> comboDetailList = new ArrayList<ComboDetailDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM combodetail";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                ComboDetailDTO data = new ComboDetailDTO(
                    rs.getString("comboCode"),
                    rs.getString("productCode")
                    );
                comboDetailList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return comboDetailList;
        } else {
            return null;
        }
    }

    @Override
    public ComboDetailDTO selectById(String id) {
        return null;
    }

    @Override
    public ArrayList<ComboDetailDTO> selectByCondition(String condition, String colName) {
        ArrayList<ComboDetailDTO> comboDetailList = new ArrayList<ComboDetailDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM combodetail" + condition + "";
            PreparedStatement pst = connection.prepareStatement(sql); 
            // pst.setString(1, condition);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                ComboDetailDTO data = new ComboDetailDTO(
                    rs.getString("comboCode"),
                    rs.getString("productCode")
                    );
                comboDetailList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return comboDetailList;
        } else {
            return null;
        }
    }
    
}
