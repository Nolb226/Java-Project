package com.fastfoodstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fastfoodstore.dto.StaffDTO;

public class StaffDAO implements DAOInterface<StaffDTO> {

    @Override
    public int insert(StaffDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `staff` (`id`, `name`, `email`, `numberPhone`, `address`, `birthday`, `dutyCode`, `status`)"
                        +" VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getID());
            pst.setString(2, t.getName());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getNumberPhone());
            pst.setString(5, t.getAddress());
            pst.setDate(6, t.getBirthday());
            pst.setString(7, t.getDutyCode());
            pst.setBoolean(8, t.getStatus());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int update(StaffDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "UPDATE `staff`"
                        +" SET `id` = ?, `name` = ?, `email` = ?, `numberPhone` = ?, `address` = ?, `birthday` = ?, `dutyCode` = ?, `status` = ?"
                        +" WHERE `staff`.`id` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getID());
            pst.setString(2, t.getName());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getNumberPhone());
            pst.setString(5, t.getAddress());
            pst.setDate(6, t.getBirthday());
            pst.setString(7, t.getDutyCode());
            pst.setBoolean(8, t.getStatus());
            pst.setString(9, t.getID());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int delete(StaffDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql =  "DELETE FROM staff"
                        +" WHERE `staff`.`id` = '009'";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getID());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public ArrayList<StaffDTO> selectAll() {
        ArrayList<StaffDTO> staffList = new ArrayList<StaffDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM staff";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                StaffDTO data = new StaffDTO(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("numberPhone"),
                    rs.getString("address"),
                    rs.getDate("birthday"),
                    rs.getString("dutyCode"),
                    rs.getBoolean("status")
                    );
                staffList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return staffList;
        } else {
            return null;
        }
    }

    @Override
    public StaffDTO selectById(String id) {
        StaffDTO staff = new StaffDTO();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM staff WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);  
            pst.setString(1, id);          
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                staff.setID(rs.getString("id"));    
                staff.setName(rs.getString("name"));    
                staff.setEmail(rs.getString("email"));    
                staff.setNumberPhone(rs.getString("numberPhone"));    
                staff.setAddress(rs.getString("address"));    
                staff.setBirthday(rs.getDate("birthday"));    
                staff.setDutyCode(rs.getString("dutyCode"));    
                staff.setStatus(rs.getBoolean("status"));    
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return staff;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<StaffDTO> selectByCondition(String condition, String colName) {
        ArrayList<StaffDTO> staffList = new ArrayList<StaffDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM staff WHERE ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, condition);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                StaffDTO data = new StaffDTO(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("numberPhone"),
                    rs.getString("address"),
                    rs.getDate("birthday"),
                    rs.getString("dutyCode"),
                    rs.getBoolean("status")
                    );
                staffList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return staffList;
        } else {
            return null;
        }
    }
    
}
