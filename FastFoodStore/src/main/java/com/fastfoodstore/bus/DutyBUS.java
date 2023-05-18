/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.DutyDAO;
import com.fastfoodstore.dto.DutyDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class DutyBUS {
    public static ArrayList<DutyDTO> selectAllDuty() {
        return DutyDAO.getInstance().selectAll();
    }
}
