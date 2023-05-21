/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.ReceiptsDAO;
import com.fastfoodstore.dto.ReceiptsDTO;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ReceiptsBUS {
    
    public static ArrayList<ReceiptsDTO> selectAll(){
        return ReceiptsDAO.getInstance().selectAll();
    }
    
    public static void insertReceipt(ReceiptsDTO a){
        ReceiptsDAO.getInstance().insert(a);
    }
    
    public static boolean checkReceipt (String code){
        ReceiptsDTO a = ReceiptsDAO.getInstance().selectById(code);
        if(a == null){
            return false;
        }else{
            return true;
        }
    }
    
    public static ArrayList<ReceiptsDTO> selectBillByDate(String start, String end) {
        if(start != null && end != null) {
            return ReceiptsDAO.getInstance().selectByCondition("where DATE(date) >= '" + start + "' "
                                                            + "and DATE(date) <='" + end + "' "
                                                            + "order by date DESC, TIME(date)", "");
        } else if(start != null) {
            return ReceiptsDAO.getInstance().selectByCondition("where DATE(date) >= '" + start + "' order by date DESC, TIME(date)", "");
        } else if(end != null) {
            return ReceiptsDAO.getInstance().selectByCondition("where DATE(date) <= '" + end + "' order by date DESC, TIME(date)", "");
        } else {
            return null;
        }
        
    }
}
