package com.fastfoodstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fastfoodstore.dto.ComboDTO;

public class ComboDAO implements DAOInterface<ComboDTO> {
    
    public static ComboDAO getInstance() {
        return new ComboDAO();
    }

    @Override
    public int insert(ComboDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `combo` (`comboCode`, `comboName`, `comboPrice`, `numberOfProduct`, `comboImage`, `groupCode`, `inMenu`)"
                        +" VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getComboCode());
            pst.setString(2, t.getComboName());
            pst.setInt(3,t.getComboPrice());
            pst.setInt(4, t.getNumberOfProduct());
            pst.setString(5, t.getComboImage());
            pst.setString(6, t.getGroupCode());
            pst.setBoolean(7, t.getInMenu());

            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
            return change;
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
            return change;
        }
        
    }

    @Override
    public int update(ComboDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "UPDATE `combo`"
                        +" SET `comboCode` = ?, `comboName` = ?, `comboPrice` = ?, `numberOfProduct` = ?, `comboImage` = ?, `groupCode` = ?, `inMenu` = ?"
                        +" WHERE `combo`.`comboCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getComboCode());
            pst.setString(2, t.getComboName());
            pst.setInt(3,t.getComboPrice());
            pst.setInt(4, t.getNumberOfProduct());
            pst.setString(5, t.getComboImage());
            pst.setString(6, t.getGroupCode());
            pst.setBoolean(7, t.getInMenu());
            pst.setString(8, t.getComboCode());

            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
            return 1;
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
            return 0;
        }
    }

    @Override
    public int delete(ComboDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "DELETE FROM combo" 
                    +"WHERE `combo`.`comboCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getComboCode());

            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public ArrayList<ComboDTO> selectAll() {
        ArrayList<ComboDTO> comboList = new ArrayList<ComboDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM combo";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                ComboDTO data = new ComboDTO(
                    rs.getString("comboCode"),
                    rs.getString("comboName"),
                    rs.getInt("comboPrice"),
                    rs.getInt("numberOfProduct"),
                    rs.getString("comboImage"),
                    rs.getString("groupCode"),
                    rs.getBoolean("inMenu")
                    );
                comboList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return comboList;
        } else {
            return null;
        }
    }

    @Override
    public ComboDTO selectById(String id) {
        ComboDTO combo = new ComboDTO();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM combo WHERE comboCode = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
               combo.setComboCode(rs.getString("comboCode"));
               combo.setComboName(rs.getString("comboName"));
               combo.setComboPrice( rs.getInt("comboPrice"));
               combo.setNumberOfProduct(rs.getInt("numberOfProduct"));
               combo.setComboImage(rs.getString("comboImage"));
               combo.setGroupCode(rs.getString("groupCode"));
               combo.setInMenu(rs.getBoolean("inMenu"));
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return combo;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<ComboDTO> selectByCondition(String condition, String colName) {
        ArrayList<ComboDTO> comboList = new ArrayList<ComboDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM combo where " + condition + "";
            PreparedStatement pst = connection.prepareStatement(sql); 
            // pst.setString(1, condition);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                ComboDTO data = new ComboDTO(
                    rs.getString("comboCode"),
                    rs.getString("comboName"),
                    rs.getInt("comboPrice"),
                    rs.getInt("numberOfProduct"),
                    rs.getString("comboImage"),
                    rs.getString("groupCode"),
                    rs.getBoolean("inMenu")
                    );
                comboList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return comboList;
        } else {
            return null;
        }
    }
    
}
