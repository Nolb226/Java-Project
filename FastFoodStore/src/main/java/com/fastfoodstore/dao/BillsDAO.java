package com.fastfoodstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fastfoodstore.dto.BillsDTO;

public class BillsDAO implements DAOInterface<BillsDTO> {

    public static BillsDAO getInstance() {
        return new BillsDAO();
    }

    @Override
    public int insert(BillsDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `bills` (`billCode`, `orderNumber`, `date`, `totalNumber`, `totalPrice`, `cash`, `excess`, `billStatus`, `promoCode`)"
                        +" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getBillCode());
            pst.setInt(2, t.getOrderNumber());
            pst.setDate(3, t.getDate());
            pst.setInt(4, t.getTotalNumber());
            pst.setFloat(5, t.getTotalPrice());
            pst.setFloat(6, t.getCash());
            pst.setFloat(7, t.getExcess());
            pst.setString(8, t.getBillStatus());
            pst.setString(9, t.getPromoCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int update(BillsDTO t) {
        int change = 0;
        
        try {
            Connection connection = ConnectionData.getConnection();
            String sql ="UPDATE `bills`"
            +" SET `billCode` = ?, `orderNumber` = ?, `date` = ?, `totalNumber` = ?, `totalPrice` = ?, `cash` = ?, `excess` = ?, `billStatus` = ?"
            +" WHERE `bills`.`billCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, t.getBillCode());
            pst.setInt(2, t.getOrderNumber());
            pst.setDate(3, t.getDate());
            pst.setInt(4, t.getTotalNumber());
            pst.setFloat(5, t.getTotalPrice());
            pst.setFloat(6, t.getCash());
            pst.setFloat(7, t.getExcess());
            pst.setString(8, t.getBillStatus());
            pst.setString(9, t.getPromoCode());
            pst.setString(10, t.getBillCode());

            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int delete(BillsDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "DELETE FROM bills" 
                        +"WHERE `bills`.`billCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getBillCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public ArrayList<BillsDTO> selectAll() {
        ArrayList<BillsDTO> billDetailList = new ArrayList<BillsDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM billdetail2";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                BillsDTO data = new BillsDTO(
                    rs.getString("billCode"),
                    rs.getInt("orderNumber"),
                    rs.getDate("date"),
                    rs.getInt("totalNumber"),
                    rs.getFloat("totalPrice"),
                    rs.getFloat("cash"),
                    rs.getFloat("excess"),
                    rs.getString("billStatus"),
                    rs.getString("promoCode")
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
    public BillsDTO selectById(String id) {
        BillsDTO bill = new BillsDTO();
        boolean isData = false;
        
        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM bills WHERE bills.billsCode = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
               bill.setBillCode(id);
               bill.setOrderNumber(rs.getInt("orderNumber"));
               bill.setDate(rs.getDate("date"));
               bill.setTotalNumber(rs.getInt("totalNumber"));
               bill.setTotalPrice(rs.getFloat("totalPrice"));
               bill.setCash(rs.getFloat("cash"));
               bill.setExcess( rs.getFloat("excess"));
               bill.setBillStatus(rs.getString("billStatus"));
               bill.setPromoCode(rs.getString("promoCode"));
                isData = true;
            }

            ConnectionData.closeConnection(connection);
            
        } catch (Exception e) {
            
            System.out.println("Select data failture " + e);
        }
        
        if(isData) {
            return bill;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<BillsDTO> selectByCondition(String condition, String colName) {
        ArrayList<BillsDTO> billList = new ArrayList<BillsDTO>();
        boolean isData = false;
        
        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * from bills " + condition + " ";
            PreparedStatement pst = connection.prepareStatement(sql);
            // pst.setString(1, condition);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                BillsDTO data = new BillsDTO(
                    rs.getString("billCode"),
                    rs.getInt("orderNumber"),
                    rs.getDate("date"),
                    rs.getInt("totalNumber"),
                    rs.getFloat("totalPrice"),
                    rs.getFloat("cash"),
                    rs.getFloat("excess"),
                    rs.getString("billStatus"),
                    rs.getString("promoCode")
                    );
                billList.add(data);
                isData = true;
            }
           
            ConnectionData.closeConnection(connection);
            
        } catch (Exception e) {
            
            System.out.println("Select data failture " + e);
            
        }
        
        if(isData) {
            return billList;
        } else {
            return null;
        }
    }
    
}
