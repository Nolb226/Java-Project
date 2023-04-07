package com.fastfoodstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fastfoodstore.dto.ReceiptsDTO;

public class ReceiptsDAO implements DAOInterface<ReceiptsDTO> {

    @Override
    public int insert(ReceiptsDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `receipts` (`receiptCode`, `date`, `totalPrice`) "
                        +" VALUES (?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getReceiptCode());
            pst.setDate(2, t.getDate());
            pst.setFloat(3, t.getTotalPrice());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int update(ReceiptsDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "UPDATE `receipts`"
                        +" SET `receiptCode` = ?, `date` = ?, `totalPrice` = ?"
                        +" WHERE `receipts`.`receiptCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getReceiptCode());
            pst.setDate(2, t.getDate());
            pst.setFloat(3, t.getTotalPrice());
            pst.setString(4, t.getReceiptCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int delete(ReceiptsDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql =  "DELETE FROM receipts "
                        +" WHERE `receipts`.`receiptCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getReceiptCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public ArrayList<ReceiptsDTO> selectAll() {
        ArrayList<ReceiptsDTO> receiptList = new ArrayList<ReceiptsDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM receipt";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                ReceiptsDTO data = new ReceiptsDTO(
                    rs.getString("receiptCode"),
                    rs.getDate("date"),
                    rs.getFloat("totalPrice")
                    );
                receiptList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return receiptList;
        } else {
            return null;
        }
    }

    @Override
    public ReceiptsDTO selectById(String id) {
        ReceiptsDTO receipt = new ReceiptsDTO();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM receipt"+
                        " WHERE receiptCode = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, id);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                receipt.setReceiptCode(rs.getString("receiptCode"));    
                receipt.setDate(rs.getDate("date"));    
                receipt.setTotalPrice(rs.getFloat("totalPrice"));    
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return receipt;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<ReceiptsDTO> selectByCondition(String condition, String colName) {
        ArrayList<ReceiptsDTO> receiptList = new ArrayList<ReceiptsDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM receipt " + condition + "";
            PreparedStatement pst = connection.prepareStatement(sql);
            // pst.setString(1, condition);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                ReceiptsDTO data = new ReceiptsDTO(
                    rs.getString("receiptCode"),
                    rs.getDate("date"),
                    rs.getFloat("totalPrice")
                    );
                receiptList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return receiptList;
        } else {
            return null;
        }
    }
    
}
