/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.ComboDAO;
import com.fastfoodstore.dto.ComboDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class ComboBUS {
    public static ArrayList<ComboDTO> getComboInGroup(String code) {
        return ComboDAO.getInstance().selectByCondition("groupCode = '"+ code +"'", "");
    }
    
    public static ComboDTO getComboByCode(String code) {
        return ComboDAO.getInstance().selectById(code);
    }
}
