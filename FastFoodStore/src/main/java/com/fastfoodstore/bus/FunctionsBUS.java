/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.FunctionsDAO;
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

    public static ArrayList<FunctionsDTO> getGroupFunctionList(String dutyCode) {
        return FunctionsDAO.getInstance().selectByCondition(
                "join dutyhasfunc on functions.functionCode = dutyhasfunc.functionCode",
                "dutyCode = '" + dutyCode + "'");

    }

}
