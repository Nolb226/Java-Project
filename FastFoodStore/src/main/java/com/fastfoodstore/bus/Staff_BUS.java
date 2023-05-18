/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.StaffDAO;
import com.fastfoodstore.dto.StaffDTO;
import com.fastfoodstore.gui.form.StaffForm;
import java.util.ArrayList;

/**
 *
 * @author k
 */
public class Staff_BUS {
    
    private StaffDAO _dbStaff= StaffDAO.getInstance();
    
    public Staff_BUS() {
        
    }
    
    public ArrayList<StaffDTO> getAll() {
           return _dbStaff.selectAll();
    }
    
    public void init() {
    }
}
