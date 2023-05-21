package com.fastfoodstore.dto;

import java.sql.Date;

public class ReceiptsDTO {
    private String receiptCode;
    private Date date;
    private float totalPrice;
    private String staffId;

    public ReceiptsDTO() {

    }

    public ReceiptsDTO(String receiptCode, Date date, float totalPrice) {
        this.date = date;
        this.receiptCode = receiptCode;
        this.totalPrice = totalPrice;
    }
    
    public ReceiptsDTO(String receiptCode, Date date, float totalPrice, String staffId) {
        this.date = date;
        this.receiptCode = receiptCode;
        this.totalPrice = totalPrice;
        this.staffId = staffId;
    }
    
    public ReceiptsDTO(ReceiptsDTO a){
        this.receiptCode = a.receiptCode;
        this.totalPrice = a.totalPrice;
        this.staffId = a.staffId;
        this.date = a.date;
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
    
    public void setStaffId(String staffId){
        this.staffId = staffId;
    }

    public Date getDate() {
        return date;
    }
    
    public String getDateString(){
        return date.toString();
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
    
    public String getStaffId(){
        return staffId;
    }
    
}
