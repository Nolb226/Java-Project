/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.IngredientDAO;
import com.fastfoodstore.dto.IngredientDTO;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class IngredientBUS {
    
    public static IngredientDTO getIngredientByCode(String code) {
        return IngredientDAO.getInstance().selectById(code);
    }
    
    public static  ArrayList<IngredientDTO> getAllIngredient() {
        return IngredientDAO.getInstance().selectAll();
    }
    
    public static void updateAmout(IngredientDTO upTO){
        IngredientDAO a = IngredientDAO.getInstance();
        IngredientDTO b = a.selectById(upTO.getIngredientCode());
        upTO.setAmount(upTO.getAmount()+b.getAmount());
        a.update(upTO);
    }
    
    public static void update(IngredientDTO upTO){
        IngredientDAO.getInstance().update(upTO);
    }
    
    public static void insertIngredient(IngredientDTO newIngre){
        IngredientDAO.getInstance().insert(newIngre);
    }
}
