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
        if (data instanceof BillDetailDTO t) {
            BillDetailDAO.getInstance().insert(t);
        } else if (data instanceof BillDetail2DTO t) {
            BillDetail2DAO.getInstance().insert(t);
        }

    }

    public static ArrayList<BillDetailDTO> selectBillDetailByCode(String code) {
        return BillDetailDAO.getInstance().selectByCondition("where billCode = '" + code + "'", "");
    }

    public static ArrayList<BillDetail2DTO> selectBillDetail2ByCode(String code) {
        return BillDetail2DAO.getInstance().selectByCondition("where billCode = '" + code + "'", "");

    }

    public static int getProductSales(String code, String start, String end) {
        String sql = "SELECT\n"
                + "SUM(billdetail.amountProduct) AS totalAmount\n"
                + "FROM\n"
                + "billdetail\n"
                + "JOIN bills ON bills.billCode = billdetail.billCode\n"
                + "WHERE\n"
                + "bills.date < '" + end + "' AND bills.date > '" + start + "' AND billdetail.productCode = '" + code + "'";
        return BillDetailDAO.getInstance().selectCount(sql);
    }
    
    public static int getComboSale(String code, String start, String end) {
        String sql = "SELECT\n"
                + "SUM(billdetail2.amoutCombo) AS totalAmount\n"
                + "FROM\n"
                + "billdetail2\n"
                + "JOIN bills ON bills.billCode = billdetail2.billCode\n"
                + "WHERE\n"
                + "bills.date < '" + end + "' AND bills.date > '" + start + "' AND billdetail2.comboCode = '" + code + "'";
        return BillDetailDAO.getInstance().selectCount(sql);
    }
}
