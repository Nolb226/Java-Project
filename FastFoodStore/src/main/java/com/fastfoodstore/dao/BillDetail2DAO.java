package com.fastfoodstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fastfoodstore.dto.BillDetail2DTO;

public class BillDetail2DAO implements DAOInterface<BillDetail2DTO> {

    public static BillDetail2DAO getInstance() {
        return new BillDetail2DAO();
    }

    @Override
    public int insert(BillDetail2DTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `billdetail2` (`billCode`, `comboCode`, `productNote`, `amoutCombo`, `price`)"
                        +" VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getBillCode());
            pst.setString(2, t.getComboCode());
            pst.setString(3, t.getProductNote());
            pst.setInt(4, t.getAmountCombo());
            pst.setInt(5, t.getPrice());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int update(BillDetail2DTO t) {
        int change = 0;
        
        try {
            Connection connection = ConnectionData.getConnection();
            String sql ="UPDATE `billdetail2`"+
                    " SET `billCode` = ?, `comboCode` = ?, `productNote` = ?, `amoutCombo` = ?, `price` = ?"+
                    " WHERE `billdetail2`.`billCode` = ? AND `billdetail2`.`comboCode` = ?;";
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, t.getBillCode());
            pst.setString(2, t.getComboCode());
            pst.setString(3, t.getProductNote());
            pst.setInt(4, t.getAmountCombo());
            pst.setInt(5, t.getPrice());
            pst.setString(6, t.getBillCode());
            pst.setString(7, t.getComboCode());

            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int delete(BillDetail2DTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "DELETE FROM billdetail2 "+
                        "WHERE `billdetail2`.`billCode` = ? AND `billdetail2`.`comboCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getBillCode());
            pst.setString(2, t.getComboCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public ArrayList<BillDetail2DTO> selectAll() {
        ArrayList<BillDetail2DTO> billDetailList = new ArrayList<BillDetail2DTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM billdetail2";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                BillDetail2DTO data = new BillDetail2DTO(
                    rs.getString("billCode"),
                    rs.getString("comboCode"),
                    rs.getString("productNote"),
                    rs.getInt("amountCombo"),
                    rs.getInt("price")
                    );
                billDetailList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return billDetailList;
        } else {
            return null;
        }
    }

    @Override
    public BillDetail2DTO selectById(String id) {
        return null;
    }

    @Override
    public ArrayList<BillDetail2DTO> selectByCondition(String condition, String colName) {
        ArrayList<BillDetail2DTO> billDetailList = new ArrayList<BillDetail2DTO>();
        boolean isData = false;
        
        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * from billdetail2 " + condition + " ";
            PreparedStatement pst = connection.prepareStatement(sql);
            // pst.setString(1, condition);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                BillDetail2DTO data = new BillDetail2DTO(
                    rs.getString("billCode"),
                    rs.getString("comboCode"),
                    rs.getString("productNote"),
                    rs.getInt("amoutCombo"),
                    rs.getInt("price")
                    );
                billDetailList.add(data);
                isData = true;
            }
           
            ConnectionData.closeConnection(connection);
            
        } catch (Exception e) {
            
            System.out.println("Select data failture " + e);
            
        }
        
        if(isData) {
            return billDetailList;
        } else {
            return null;
        }
    }
    
}
