/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.DutyDAO;
import com.fastfoodstore.dao.StaffDAO;
import com.fastfoodstore.dto.DutyDTO;
import com.fastfoodstore.dto.StaffDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class DutyBUS {
    
    public static int insertDuty(DutyDTO t) {
        return DutyDAO.getInstance().insert(t);
    }
    
    public static ArrayList<DutyDTO> selectAllDuty() {
        return DutyDAO.getInstance().selectAll();
    }
    
    public static StaffDTO getStaffByCode(String staffCode) {
        return StaffDAO.getInstance().selectById(staffCode);
    }
    
    public static String getDutyCodeByName(String name) {
        return DutyDAO.getInstance().selectByName(name).getDutyCode();
    }
    
    public static String getDutyNameByCode(String code) {
        return DutyDAO.getInstance().selectById(code).getDutyName();
    }
}
