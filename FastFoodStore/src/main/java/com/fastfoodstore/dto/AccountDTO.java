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
    private boolean status;

    public AccountDTO() {
    }
    
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public AccountDTO(String staffCode, String pass, boolean status) {
        this.staffCode = staffCode;
        this.pass = pass;
        this.status = status;
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
