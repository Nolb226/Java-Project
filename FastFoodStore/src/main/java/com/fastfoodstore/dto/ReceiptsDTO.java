package com.fastfoodstore.dto;

import java.sql.Date;

public class ReceiptsDTO {
    private String receiptCode;
    private Date date;
    private float totalPrice;

    public ReceiptsDTO() {

    }

    public ReceiptsDTO(String receiptCode, Date date, float totalPrice) {
        this.date = date;
        this.receiptCode = receiptCode;
        this.totalPrice = totalPrice;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
    
}
