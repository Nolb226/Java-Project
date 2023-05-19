/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.GroupDAO;
import com.fastfoodstore.dto.GroupDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class GroupsBus {

    public static ArrayList<GroupDTO> getAllGroup() {
        return GroupDAO.getInstance().selectAll();
    }

    public static ArrayList<GroupDTO> getGroupZero() {
        return GroupDAO.getInstance().selectByCondition("LENGTH(groupCode) = 2 and inMenu = 1", "");
    }

    public static ArrayList<GroupDTO> getAllGroupUnder(int under) {
        return GroupDAO.getInstance().selectByCondition("LENGTH(groupCode) = " + under + " and inMenu = 1", "");
    }

    public static ArrayList<GroupDTO> getGroupUnder(String code) {
        return GroupDAO.getInstance().selectByCondition("IN_groupCode = '" + code + "' and inMenu = 1", "");
    }

    public static GroupDTO getGroupByCode(String code) {
        return GroupDAO.getInstance().selectById(code);
    }
    
    public static int updateGroup(GroupDTO t) {
        return GroupDAO.getInstance().update(t);
    }
    
    public static int insertGroup(GroupDTO t) {
        return GroupDAO.getInstance().insert(t);
    }

    public static ArrayList<String> getCodeNameGroup() {
        ArrayList<GroupDTO> a = GroupDAO.getInstance().selectAll();
        ArrayList<String> result = new ArrayList<>();
        for(int i=0;i<a.size();i++) {
            result.add(a.get(i).getGroupCode() + "-" + a.get(i).getGroupName());
        }
        return result;
    }
}
