package com.fastfoodstore.dto;

import java.sql.Date;

public class BillsDTO {

    private String billCode;
    private String date;
    private int totalNumber;
    private int totalPrice;
    private String promoCode;
    private String billStatus;
    private String location;
    private String staffId;

    public BillsDTO() {

    }

    public BillsDTO(String billCode, String date, int totalNumber, int totalPrice, String promoCode, String billStatus, String location, String staffId) {
        this.billCode = billCode;
        this.date = date;
        this.promoCode = promoCode;
        this.totalNumber = totalNumber;
        this.totalPrice = totalPrice;
        this.billStatus = billStatus;
        this.location = location;
        this.staffId = staffId;
    }
    
    public BillsDTO(BillsDTO orther) {
        this.billCode = orther.billCode;
        this.date = orther.date;
        this.promoCode = orther.promoCode;
        this.totalNumber = orther.totalNumber;
        this.totalPrice = orther.totalPrice;
        this.billStatus = orther.billStatus;
        this.location = orther.location;
        this.staffId = orther.staffId;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getBillCode() {
        return billCode;
    }

    public String getDate() {
        return date;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public String getLocation() {
        return location;
    }

    public String getStaffId() {
        return staffId;
    }

}
