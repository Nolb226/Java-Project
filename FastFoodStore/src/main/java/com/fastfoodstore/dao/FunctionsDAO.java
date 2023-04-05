package com.fastfoodstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fastfoodstore.dto.FunctionsDTO;

public class FunctionsDAO implements DAOInterface<FunctionsDTO> {

    public static FunctionsDAO getInstance() {
        return new FunctionsDAO();
    }

    @Override
    public int insert(FunctionsDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `functions` (`functionCode`, `functionName`)"
                        +" VALUES (?, ?);";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getFunctionCode());
            pst.setString(2, t.getFunctionName());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int update(FunctionsDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "UPDATE `functions`"
                        +" SET `functionCode` = ?, `functionName` = ?"
                        +" WHERE `functions`.`functionCode` = ?;";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getFunctionCode());
            pst.setString(2, t.getFunctionName());
            pst.setString(1, t.getFunctionCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int delete(FunctionsDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql =  "DELETE FROM functions"
                        +" WHERE `functions`.`functionCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getFunctionCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public ArrayList<FunctionsDTO> selectAll() {
        ArrayList<FunctionsDTO> functionList = new ArrayList<FunctionsDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM functions";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                FunctionsDTO data = new FunctionsDTO(
                    rs.getString("functionCode"),
                    rs.getString("functionName")
                    );
                functionList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return functionList;
        } else {
            return null;
        }
    }

    @Override
    public FunctionsDTO selectById(String id) {
        FunctionsDTO function = new FunctionsDTO();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM functions WHERE functionCode = ?";
            PreparedStatement pst = connection.prepareStatement(sql); 
            pst.setString(1, id);           
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                function.setFunctionCode(rs.getString("functionCode"));
                function.setFunctionName(rs.getString("functionName"));
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return function;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<FunctionsDTO> selectByCondition(String condition, String colName) {
        ArrayList<FunctionsDTO> functionList = new ArrayList<FunctionsDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM functions WHERE ?";
            PreparedStatement pst = connection.prepareStatement(sql); 
            pst.setString(1, condition);           
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                FunctionsDTO data = new FunctionsDTO(
                    rs.getString("functionCode"),
                    rs.getString("functionName")
                    );
                functionList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return functionList;
        } else {
            return null;
        }
    }
    
}
