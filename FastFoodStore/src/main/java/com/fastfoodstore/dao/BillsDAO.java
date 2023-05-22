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
            Connection connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `bills` (`billCode`, `date`, `totalNumber`, `totalPrice`, `promoCode`,`billStatus`,`location`,`staffCode`)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, t.getBillCode());
            pst.setString(2, t.getDate());
            pst.setInt(3, t.getTotalNumber());
            pst.setInt(4, t.getTotalPrice());
            pst.setString(5, t.getPromoCode());
            pst.setString(6, t.getBillStatus());
            pst.setString(7, t.getLocation());
            pst.setString(8, t.getStaffId());
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
            String sql = "UPDATE `bills`"
                    + " SET `billCode` = ?, `date` = ?, `totalNumber` = ?, `totalPrice` = ?, `billStatus` = ?, `location` = ?, staffCode = ?"
                    + " WHERE `bills`.`billCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, t.getBillCode());
            pst.setString(2, t.getDate());
            pst.setInt(3, t.getTotalNumber());
            pst.setFloat(4, t.getTotalPrice());
            pst.setString(5, t.getPromoCode());
            pst.setString(6, t.getBillStatus());
            pst.setString(7, t.getBillCode());
            pst.setString(7, t.getLocation());
            pst.setString(8, t.getStaffId());
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
            Connection connection = ConnectionData.getConnection();
            String sql = "DELETE FROM bills"
                    + "WHERE `bills`.`billCode` = ?";
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
            String sql = "SELECT * FROM bills order by date DESC";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                BillsDTO data = new BillsDTO(
                        rs.getString("billCode"),
                        rs.getString("date"),
                        rs.getInt("totalNumber"),
                        rs.getInt("totalPrice"),
                        rs.getString("promoCode"),
                        rs.getString("billStatus"),
                        rs.getString("location"),
                        rs.getString("staffCode")
                );
                billDetailList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if (isData) {
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

            if (rs.next()) {
                bill.setBillCode(id);
                bill.setDate(rs.getString("date"));
                bill.setTotalNumber(rs.getInt("totalNumber"));
                bill.setTotalPrice(rs.getInt("totalPrice"));
                bill.setPromoCode(rs.getString("promoCode"));
                bill.setBillStatus(rs.getString("billStatus"));
                bill.setLocation(rs.getString("location"));
                bill.setStaffId(rs.getString("staffCode"));
                isData = true;
            }

            ConnectionData.closeConnection(connection);

        } catch (Exception e) {

            System.out.println("Select data failture " + e);
        }

        if (isData) {
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

            while (rs.next()) {
                BillsDTO data = new BillsDTO(
                        rs.getString("billCode"),
                        rs.getString("date"),
                        rs.getInt("totalNumber"),
                        rs.getInt("totalPrice"),
                        rs.getString("promoCode"),
                        rs.getString("billStatus"),
                        rs.getString("location"),
                        rs.getString("staffCode")
                );
                billList.add(data);
                isData = true;
            }

            ConnectionData.closeConnection(connection);

        } catch (Exception e) {

            System.out.println("Select data failture " + e);

        }

        if (isData) {
            return billList;
        } else {
            return null;
        }
    }
    
     public int selectInt(String sql) {
        try {
            Connection connection = ConnectionData.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                int n = rs.getInt("total");
                ConnectionData.closeConnection(connection);
                return n;
            } else {
                ConnectionData.closeConnection(connection);
                return -1;
            }
        } catch (Exception e) {

            System.out.println("Select data failture " + e);
            return -1;
        }
    }

}
