/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.ComboDAO;
import com.fastfoodstore.dao.ComboDetailDAO;
import com.fastfoodstore.dto.ComboDTO;
import com.fastfoodstore.dto.ComboDetailDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class ComboBUS {
    
    public static ArrayList<ComboDTO> getAllCombo() {
        return ComboDAO.getInstance().selectAll();
    }
    
    public static ArrayList<ComboDTO> getComboInGroup(String code) {
        return ComboDAO.getInstance().selectByCondition("groupCode = '"+ code +"' and inMenu = 1", "");
    }
    
    public static ComboDTO getComboByCode(String code) {
        return ComboDAO.getInstance().selectById(code);
    }
    
    public static int updateCombo(ComboDTO t) {
        return ComboDAO.getInstance().update(t);
    }
    
    public static int insertCombo(ComboDTO t) {
        return ComboDAO.getInstance().insert(t);
    }
    
    public static ArrayList<String> getDetail(String code) {
        ArrayList<ComboDetailDTO> a = ComboDetailDAO.getInstance().selectByCondition(" where comboCode = '" + code + "'","");
        ArrayList<String> result = new ArrayList<>();
        for(ComboDetailDTO b : a) {
            result.add(ProductsBUS.getProductsByCode(b.getProductCode()).getProductName());
        }
        return result;
    }
    
    public static int insertDetailCombo(String comboCode, ArrayList<String> p) {
        int k = 0;
        for(String a : p) {
            k = ComboDetailDAO.getInstance().insert(new ComboDetailDTO(comboCode,a));
            if(k == 0) {
                break;
            }
        }
        return k;
    }
}
