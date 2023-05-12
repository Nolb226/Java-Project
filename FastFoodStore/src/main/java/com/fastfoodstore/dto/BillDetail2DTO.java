package com.fastfoodstore.dto;

public class BillDetail2DTO {
    private String billCode;
    private String comboCode;
    private String productNote;
    private int amountCombo;
    private int price;

    public BillDetail2DTO() {

    }

    public BillDetail2DTO(String billCode, String comboCode, String procductNote, int amountCombo, int price) {
        this.amountCombo = amountCombo;
        this.billCode = billCode;
        this.comboCode = comboCode;
        this.price = price;
        this.productNote = procductNote;
    }

    public void setAmountCombo(int amountCombo) {
        this.amountCombo = amountCombo;
    }
    
    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public void setComboCode(String comboCode) {
        this.comboCode = comboCode;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProductNote(String productNote) {
        this.productNote = productNote;
    }

    public int getAmountCombo() {
        return amountCombo;
    }

    public String getBillCode() {
        return billCode;
    }

    public String getComboCode() {
        return comboCode;
    }

    public int getPrice() {
        return price;
    }

    public String getProductNote() {
        return productNote;
    }
    
    public void addAmount() {
        amountCombo++;
    }

}
