/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.BillDetailDAO;
import com.fastfoodstore.dao.BillsDAO;
import com.fastfoodstore.dao.PromotionsDAO;
import com.fastfoodstore.dto.BillsDTO;
import com.fastfoodstore.dto.PromotionsDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class BillBUS {

    public static void insertBill(BillsDTO data) {
        BillsDAO.getInstance().insert(data);
    }
    
    public static ArrayList<BillsDTO> selectAllBill() {
        return BillsDAO.getInstance().selectAll();
    }

    public static BillsDTO selectBillByCode(String code) {
        return BillsDAO.getInstance().selectById(code);
    }

    public static ArrayList<BillsDTO> selectBillByDate(String start, String end) {
        if(start != null && end != null) {
            return BillsDAO.getInstance().selectByCondition("where DATE(date) >= '" + start + "' "
                                                            + "and DATE(date) <='" + end + "' "
                                                            + "order by date DESC, TIME(date)", "");
        } else if(start != null) {
            return BillsDAO.getInstance().selectByCondition("where DATE(date) >= '" + start + "' order by date DESC, TIME(date)", "");
        } else if(end != null) {
            return BillsDAO.getInstance().selectByCondition("where DATE(date) <= '" + end + "' order by date DESC, TIME(date)", "");
        } else {
            return null;
        }
        
    }

    public static int getRenevue(String start, String end) {
        String sql = "SELECT SUM(bills.totalPrice) AS totalAmount FROM `bills` WHERE bills.date < '" + end + "' AND bills.date>'" + start + "'";
        return BillDetailDAO.getInstance().selectCount(sql);
    }
    
    public static ArrayList<PromotionsDTO> getAllPromo() {
        return new PromotionsDAO().selectAll();
    }
}
