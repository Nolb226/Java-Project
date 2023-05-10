package com.fastfoodstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fastfoodstore.dto.GroupDTO;

public class GroupDAO implements DAOInterface<GroupDTO> {
    
    public static GroupDAO getInstance() {
        return new GroupDAO();
    }

    @Override
    public int insert(GroupDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `groups` (`groupCode`, `groupName`, `groupIcon`, `IN_groupCode`)"
                        +" VALUES (?, ?, ?, ?);";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getGroupCode());
            pst.setString(2, t.getGroupName());
            pst.setString(3, t.getGroupIcon());
            pst.setString(4, t.getIN_groupCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int update(GroupDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "UPDATE `groups`"
                        +" SET `groupCode` = ?, `groupName` = ?, `groupIcon` = ?, `IN_groupCode` = ?"
                        +" WHERE `groups`.`groupCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getGroupCode());
            pst.setString(2, t.getGroupName());
            pst.setString(3, t.getGroupIcon());
            pst.setString(4, t.getIN_groupCode());
            pst.setString(5, t.getGroupCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int delete(GroupDTO t) {
        int change = 0;

        try {
            Connection  connection = ConnectionData.getConnection();
            String sql = "DELETE FROM groups"
                        + "WHERE `groups`.`groupCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.setString(1, t.getGroupCode());
            
            change = pst.executeUpdate();
            
            ConnectionData.closeConnection(connection); 
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public ArrayList<GroupDTO> selectAll() {
        ArrayList<GroupDTO> groupList = new ArrayList<GroupDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM groups";
            PreparedStatement pst = connection.prepareStatement(sql);            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                GroupDTO data = new GroupDTO(
                    rs.getString("groupCode"),
                    rs.getString("groupName"),
                    rs.getString("groupIcon"),
                    rs.getString("IN_groupCode")
                    );
                groupList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return groupList;
        } else {
            return null;
        }
    }

    @Override
    public GroupDTO selectById(String id) {
        GroupDTO group = new GroupDTO();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM groups WHERE groupCode = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, id);            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                group.setGroupCode(rs.getString("groupCode"));
                group.setGroupIcon(rs.getString("groupIcon"));
                group.setGroupName(rs.getString("groupName"));
                group.setIN_groupCode(rs.getString("IN_groupCode"));
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return group;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<GroupDTO> selectByCondition(String condition, String colName) {
        ArrayList<GroupDTO> groupList = new ArrayList<GroupDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM groups where " + condition + "";
            PreparedStatement pst = connection.prepareStatement(sql);
//            System.out.println(sql); 
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                GroupDTO data = new GroupDTO(
                    rs.getString("groupCode"),
                    rs.getString("groupName"),
                    rs.getString("groupIcon"),
                    rs.getString("IN_groupCode")
                    );
                groupList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if(isData) {
            return groupList;
        } else {
            return null;
        }
    }
    
}
