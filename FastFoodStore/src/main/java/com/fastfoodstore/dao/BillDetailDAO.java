package com.fastfoodstore.dao;

import com.fastfoodstore.dto.BillDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BillDetailDAO implements DAOInterface<BillDetailDTO> {

    public static BillDetailDAO getInstance() {
        return new BillDetailDAO();
    }

    @Override
    public int insert(BillDetailDTO t) {

        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `billdetail` (`billCode`, `productCode`, `productNote`, `amountProduct`, `price`)"
                        +" VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getBillCode());
            pst.setString(2, t.getProductCode());
            pst.setString(3, t.getProductNote());
            pst.setInt(4, t.getAmountProduct());
            pst.setFloat(5, t.getPrice());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int update(BillDetailDTO t) {

        int change = 0;
        
        try {
            Connection connection = ConnectionData.getConnection();
            String sql ="UPDATE `billdetail`" + 
                    "SET `billCode` = ?, `productCode` = ?, `productNote` = ?, `amountProduct` = ?, `price` = ?" +
                    " WHERE `billdetail`.`billCode` = ? AND `billdetail`.`productCode` = ?;";
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, t.getBillCode());
            pst.setString(2, t.getProductCode());
            pst.setString(3, t.getProductNote());
            pst.setInt(4, t.getAmountProduct());
            pst.setFloat(5, t.getPrice());
            pst.setString(6, t.getBillCode());
            pst.setString(7, t.getProductCode());

            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int delete(BillDetailDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "DELETE FROM billdetail "+
                        "WHERE `billdetail`.`billCode` = ? AND `billdetail`.`productCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getBillCode());
            pst.setString(2, t.getProductCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public ArrayList<BillDetailDTO> selectAll() {

        ArrayList<BillDetailDTO> billDetailList = new ArrayList<BillDetailDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM billdetail";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                BillDetailDTO data = new BillDetailDTO(
                    rs.getString("billCode"),
                    rs.getString("productCode"),
                    rs.getString("productNote"),
                    rs.getInt("amountProduct"),
                    rs.getFloat("price")
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
    public BillDetailDTO selectById(String id) {
        return null;
    }

    @Override
    public ArrayList<BillDetailDTO> selectByCondition(String condition, String colName) {

        ArrayList<BillDetailDTO> billDetailList = new ArrayList<BillDetailDTO>();
        boolean isData = false;
        
        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * from billdetail" + condition + "";
            PreparedStatement pst = connection.prepareStatement(sql);
            // pst.setString(1, condition);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                BillDetailDTO data = new BillDetailDTO(
                    rs.getString("billCode"),
                    rs.getString("productCode"),
                    rs.getString("productNote"),
                    rs.getInt("amountProduct"),
                    rs.getFloat("price")
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
