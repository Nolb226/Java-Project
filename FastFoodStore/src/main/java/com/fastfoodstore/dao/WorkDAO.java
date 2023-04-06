package com.fastfoodstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fastfoodstore.dto.WorkDTO;

public class WorkDAO implements DAOInterface<WorkDTO> {

    @Override
    public int insert(WorkDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `work` (`ID`, `shiftsCode`, `checkIn`, `checkOut`, `session`, `time`)"
                        +" VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getId());
            pst.setString(2, t.getShiftsCode());
            pst.setDate(3, t.getCheckIn());
            pst.setDate(4, t.getChechOut());
            pst.setString(5, t.getSession());
            pst.setTime(6, t.getTime());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;  
    }

    @Override
    public int update(WorkDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "UPDATE `work`"
                        +" SET `ID` = ?, `checkIn` = ?, `checkOut` = ?, `session` = ?, `time` = ?"
                        +" WHERE `work`.`ID` = ? AND `work`.`shiftsCode` = ?;";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getId());
            pst.setString(2, t.getShiftsCode());
            pst.setDate(3, t.getCheckIn());
            pst.setDate(4, t.getChechOut());
            pst.setString(5, t.getSession());
            pst.setTime(6, t.getTime());
            pst.setString(7, t.getId());
            pst.setString(8, t.getShiftsCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;  
    }

    @Override
    public int delete(WorkDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql =  "DELETE FROM work WHERE `work`.`ID` = ? AND `work`.`shiftsCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getId());
            pst.setString(2, t.getShiftsCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;  
    }

    @Override
    public ArrayList<WorkDTO> selectAll() {
        ArrayList<WorkDTO> workList = new ArrayList<WorkDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM work";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                WorkDTO data = new WorkDTO(
                    rs.getString("ID"),
                    rs.getString("shiftsCode"),
                    rs.getDate("checkIn"),
                    rs.getDate("checkOut"),
                    rs.getString("session"),
                    rs.getTime("time")
                    );
                workList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return workList;
        } else {
            return null;
        }
    }

    @Override
    public WorkDTO selectById(String id) {
        return null;
    }

    @Override
    public ArrayList<WorkDTO> selectByCondition(String condition, String colName) {
        ArrayList<WorkDTO> workList = new ArrayList<WorkDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM work WHERE ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, condition);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                WorkDTO data = new WorkDTO(
                    rs.getString("ID"),
                    rs.getString("shiftsCode"),
                    rs.getDate("checkIn"),
                    rs.getDate("checkOut"),
                    rs.getString("session"),
                    rs.getTime("time")
                    );
                workList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return workList;
        } else {
            return null;
        }
    }
    
}
