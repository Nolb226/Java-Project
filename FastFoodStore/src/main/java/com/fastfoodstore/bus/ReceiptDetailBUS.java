/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.ReceiptDetailDAO;
import com.fastfoodstore.dto.ReceiptDetailDTO;

/**
 *
 * @author ASUS
 */
public class ReceiptDetailBUS {
    public static void insert(ReceiptDetailDTO a){
        ReceiptDetailDAO.getInstance().insert(a);
    }
}
