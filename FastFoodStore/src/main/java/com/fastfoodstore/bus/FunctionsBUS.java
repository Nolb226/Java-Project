/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.DutyDAO;
import com.fastfoodstore.dao.FunctionsDAO;
import com.fastfoodstore.dto.DutyDTO;
import com.fastfoodstore.dto.FunctionsDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class FunctionsBUS {

    public static ArrayList<FunctionsDTO> getAllFunctionList() {
        return FunctionsDAO.getInstance().selectAll();
    }

    public static ArrayList<FunctionsDTO> getGroupFunctionList(String pass) {
        
        ArrayList<FunctionsDTO> a = null;
        
        DutyDTO duty = DutyDAO.getInstance().selectById(pass);
        
        if(duty != null) {
            a = FunctionsDAO.getInstance().selectByCondition(
                "join dutyhasfunc on functions.functionCode = dutyhasfunc.functionCode",
                "dutyCode = '" + duty.getDutyCode() + "'");
        } else {
             a = FunctionsDAO.getInstance().selectByCondition(
                "join dutyhasfunc on functions.functionCode = dutyhasfunc.functionCode",
                "dutyCode = '" + "DUTY05" + "'");
        }
        
        if(a != null) {
            return a;
        } else {
            return FunctionsDAO.getInstance().selectByCondition(
                "join dutyhasfunc on functions.functionCode = dutyhasfunc.functionCode",
                "dutyCode = ''");
        }
        
    }

}
