package com.fastfoodstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fastfoodstore.dto.DutyDTO;

public class DutyDAO implements DAOInterface<DutyDTO> {
    
    public static DutyDAO getInstance() {
        return new DutyDAO();
    }

    @Override
    public int insert(DutyDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `duty` (`dutyCode`, `dutyName`)"
                        +" VALUES (?, ?);";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getDutyCode());
            pst.setString(2, t.getDutyName());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int update(DutyDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "UPDATE `duty`"
                        +" SET `dutyCode` = ?, `dutyName` = ?"
                        +" WHERE `duty`.`dutyCode` = ?;";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getDutyCode());
            pst.setString(2, t.getDutyName());
            pst.setString(3, t.getDutyCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int delete(DutyDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "DELETE FROM duty"
                        +" WHERE `duty`.`dutyCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getDutyCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public ArrayList<DutyDTO> selectAll() {
        ArrayList<DutyDTO> dutyList = new ArrayList<DutyDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM duty";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                DutyDTO data = new DutyDTO(
                    rs.getString("dutyCode"),
                    rs.getString("dutyName")
                    );
                dutyList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return dutyList;
        } else {
            return null;
        }
    }

    @Override
    public DutyDTO selectById(String id) {
        DutyDTO duty = new DutyDTO();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM duty WHERE dutyCode = ?";
            PreparedStatement pst = connection.prepareStatement(sql);   
            pst.setString(1, id);         
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                duty.setDutyCode(rs.getString("dutyCode"));
                duty.setDutyName(rs.getString("dutyName"));
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return duty;
        } else {
            return null;
        }
    }
    
    public DutyDTO selectByName(String name) {
        DutyDTO duty = new DutyDTO();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM duty WHERE dutyName = ?";
            PreparedStatement pst = connection.prepareStatement(sql);   
            pst.setString(1, name);         
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                duty.setDutyCode(rs.getString("dutyCode"));
                duty.setDutyName(rs.getString("dutyName"));
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return duty;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<DutyDTO> selectByCondition(String condition, String colName) {
        ArrayList<DutyDTO> dutyList = new ArrayList<DutyDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM duty " + condition + "";
            PreparedStatement pst = connection.prepareStatement(sql);
            // pst.setString(1, condition);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                DutyDTO data = new DutyDTO(
                    rs.getString("dutyCode"),
                    rs.getString("dutyName")
                    );
                dutyList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return dutyList;
        } else {
            return null;
        }
    }
    
}
