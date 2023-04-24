package com.fastfoodstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fastfoodstore.dto.ShiftsDTO;

public class ShiftsDAO implements DAOInterface<ShiftsDTO> {

    @Override
    public int insert(ShiftsDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `shifts` (`shiftsCode`, `date`)"
                        +" VALUES (?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getShiftsCode());
            pst.setDate(2, t.getDate());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int update(ShiftsDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "UPDATE `shifts`"
                    +" SET `shiftsCode` = ?, `date` = ?"
                    +" WHERE `shifts`.`shiftsCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getShiftsCode());
            pst.setDate(2, t.getDate());
            pst.setString(3, t.getShiftsCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int delete(ShiftsDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql =  "DELETE FROM shifts "
                        +"WHERE `shifts`.`shiftsCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getShiftsCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public ArrayList<ShiftsDTO> selectAll() {
        ArrayList<ShiftsDTO> shiftList = new ArrayList<ShiftsDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM shifts";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                ShiftsDTO data = new ShiftsDTO(
                    rs.getString("shiftsCode"),
                    rs.getDate("date")
                    );
                shiftList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return shiftList;
        } else {
            return null;
        }
    }

    @Override
    public ShiftsDTO selectById(String id) {
        ShiftsDTO shift = new ShiftsDTO();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM shifts WHERE shiftsCode = ?";
            PreparedStatement pst = connection.prepareStatement(sql);  
            pst.setString(1, id);          
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                shift.setShiftsCode(rs.getString("shiftsCode"));    
                shift.setDate(rs.getDate("date"));    
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return shift;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<ShiftsDTO> selectByCondition(String condition, String colName) {
        ArrayList<ShiftsDTO> shiftList = new ArrayList<ShiftsDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM shifts " + condition + "";
            PreparedStatement pst = connection.prepareStatement(sql);
            // pst.setString(1, condition);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                ShiftsDTO data = new ShiftsDTO(
                    rs.getString("shiftsCode"),
                    rs.getDate("date")
                    );
                shiftList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return shiftList;
        } else {
            return null;
        }
    }
    
}
