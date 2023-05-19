/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.DutyHasFuncDAO;
import com.fastfoodstore.dto.AccountDTO;
import com.fastfoodstore.dto.DutyHasFuncDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class DutyHasFuncBUS {

    public static ArrayList<DutyHasFuncDTO> selectAllDutyDetail() {
        DutyHasFuncDAO a = new DutyHasFuncDAO();
        return a.selectAll();
    }
    
    public static int insert(String dutyCode, ArrayList<String> func) {
        int i = 0;
        for(String a : func) {
            i += new DutyHasFuncDAO().insert(new DutyHasFuncDTO(dutyCode,a));
            if(i == 0) {
                break;
            }
        }
        return i;
    }
    
    public static int remove(String code) {
        return new DutyHasFuncDAO().delete1(code);
    }
    
    public static ArrayList<AccountDTO> getAccount() {
        return new DutyHasFuncDAO().selectAccount();
    }
    
    public static int insertAccount(AccountDTO t) {
        return new DutyHasFuncDAO().insertAccount(t);
    }
}
