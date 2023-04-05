package com.fastfoodstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fastfoodstore.dto.DutyHasFuncDTO;

public class DutyHasFuncDAO implements DAOInterface<DutyHasFuncDTO> {

    @Override
    public int insert(DutyHasFuncDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `dutyhasfunc` (`dutyCode`, `functionCode`) "
                        +" VALUES (?, ?);";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getDutyCode());
            pst.setString(2, t.getFunctionCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int update(DutyHasFuncDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "UPDATE `dutyhasfunc`"
                        +" SET `dutyCode` = ?, `functionCode` = ?"
                        +" WHERE `dutyhasfunc`.`dutyCode` = ? AND `dutyhasfunc`.`functionCode` = ?;";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getDutyCode());
            pst.setString(2, t.getFunctionCode());
            pst.setString(3, t.getDutyCode());
            pst.setString(4, t.getFunctionCode());

            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int delete(DutyHasFuncDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql =  "DELETE FROM dutyhasfunc"
                        + "WHERE `dutyhasfunc`.`dutyCode` = ? AND `dutyhasfunc`.`functionCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getDutyCode());
            pst.setString(2, t.getFunctionCode());

            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public ArrayList<DutyHasFuncDTO> selectAll() {
        ArrayList<DutyHasFuncDTO> dutyHasFuncList = new ArrayList<DutyHasFuncDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM dutyhasfunc";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                DutyHasFuncDTO data = new DutyHasFuncDTO(
                    rs.getString("dutyCode"),
                    rs.getString("functionCode")
                    );
                dutyHasFuncList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return dutyHasFuncList;
        } else {
            return null;
        }
    }

    @Override
    public DutyHasFuncDTO selectById(String id) {
        return null;
    }

    @Override
    public ArrayList<DutyHasFuncDTO> selectByCondition(String condition, String colName) {
        ArrayList<DutyHasFuncDTO> dutyHasFuncList = new ArrayList<DutyHasFuncDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM duty Where ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, condition);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                DutyHasFuncDTO data = new DutyHasFuncDTO(
                    rs.getString("dutyCode"),
                    rs.getString("functionCode")
                    );
                dutyHasFuncList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return dutyHasFuncList;
        } else {
            return null;
        }
    }
    
}
