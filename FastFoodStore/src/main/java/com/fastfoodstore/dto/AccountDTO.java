/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.dto;

/**
 *
 * @author ADMIN
 */
public class AccountDTO {
    private String staffCode;
    private String pass;

    public AccountDTO() {
    }
    
    public AccountDTO(String staffCode, String pass) {
        this.staffCode = staffCode;
        this.pass = pass;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public String getPass() {
        return pass;
    }
    
    
}
