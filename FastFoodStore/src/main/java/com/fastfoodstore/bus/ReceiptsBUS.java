/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.ReceiptsDAO;
import com.fastfoodstore.dto.ReceiptsDTO;

/**
 *
 * @author ASUS
 */
public class ReceiptsBUS {
    
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
}
