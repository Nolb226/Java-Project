/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.BillDetail2DAO;
import com.fastfoodstore.dao.BillDetailDAO;
import com.fastfoodstore.dto.BillDetail2DTO;
import com.fastfoodstore.dto.BillDetailDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class BillDetailBUS {
    public static void insertBillDetail(Object data) {
        if(data instanceof BillDetailDTO t) {
            BillDetailDAO.getInstance().insert(t);
        } else if(data instanceof BillDetail2DTO t) {
            BillDetail2DAO.getInstance().insert(t);
        }
        
    }
    
    public static ArrayList<Object> selectBillDetailByCode(String code) {
        ArrayList<Object> result = new ArrayList<>();
        try {
            ArrayList<BillDetailDTO> arr1 = BillDetailDAO.getInstance().selectByCondition("where billCode = '" + code + "'", "");
            result.addAll(arr1);
        } catch (Exception e) {
        }
        try {
            ArrayList<BillDetail2DTO> arr2 = BillDetail2DAO.getInstance().selectByCondition("where billCode = '" + code + "'", "");
            result.addAll(arr2);
        } catch (Exception e) {
        }
        
        return result;
        
    }
}
