/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.ReceiptDetailDAO;
import com.fastfoodstore.dto.ReceiptDetailDTO;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ReceiptDetailBUS {
    public static void insert(ReceiptDetailDTO a){
        ReceiptDetailDAO.getInstance().insert(a);
    }
    
    public static ArrayList<ReceiptDetailDTO> selectReceiptDetailByCode( String code){
        
        return  ReceiptDetailDAO.getInstance().selectByCondition("WHERE receiptCode = '" + code + "'", code);
    }
}
