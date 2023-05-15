package com.fastfoodstore.dto;

import java.sql.Date;

public class BillsDTO {

    private String billCode;
    private String date;
    private int totalNumber;
    private int totalPrice;
    private String promoCode;
    private String billStatus;

    public BillsDTO() {

    }

    public BillsDTO(String billCode, String date, int totalNumber, int totalPrice, String promoCode, String billStatus) {
        this.billCode = billCode;
        this.date = date;
        this.promoCode = promoCode;
        this.totalNumber = totalNumber;
        this.totalPrice = totalPrice;
        this.billStatus = billStatus;
    }
    
    public BillsDTO(BillsDTO orther) {
        this.billCode = orther.billCode;
        this.date = orther.date;
        this.promoCode = orther.promoCode;
        this.totalNumber = orther.totalNumber;
        this.totalPrice = orther.totalPrice;
        this.billStatus = orther.billStatus;
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

}
