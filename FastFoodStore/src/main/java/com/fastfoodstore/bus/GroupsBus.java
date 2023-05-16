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
        return GroupDAO.getInstance().selectByCondition("LEFT(groupCode,1) = '0'",""); 
    } 
    
    public static ArrayList<GroupDTO> getAllGroupUnder(int under) {
        return GroupDAO.getInstance().selectByCondition("LENGTH(groupCode) = " + under,""); 
    } 
    
    public static ArrayList<GroupDTO> getGroupUnder(String code) {
        return GroupDAO.getInstance().selectByCondition("IN_groupCode = '" + code + "'",""); 
    } 
    
    public static GroupDTO getGroupByCode(String code) {
        return GroupDAO.getInstance().selectById(code);  
    } 
}
