package com.fastfoodstore.DTO;

import java.sql.Date;

public class billsDTO {
    private String billCode;
    private int orderNumber;
    private Date date;
    private int totalNumber;
    private float totalPrice;
    private float cash;
    private float excess;
    private String billStatus;
    private String promoCode;

    public billsDTO() {

    }

    public billsDTO(String billCode, int orderNumber, Date date, int totalNumber, float totalPrice, float cash, float excess, String billStatus, String promoCode) {
        this.billCode = billCode;
        this.billStatus = billStatus;
        this.cash = cash;
        this.date = date;
        this.excess = excess;
        this.orderNumber = orderNumber;
        this.promoCode = promoCode;
        this.totalNumber = totalNumber;
        this.totalPrice = totalPrice;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    public void setExcess(float excess) {
        this.excess = excess;
    }
    
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBillCode() {
        return billCode;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public float getCash() {
        return cash;
    }

    public Date getDate() {
        return date;
    }

    public float getExcess() {
        return excess;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
    
    
}
