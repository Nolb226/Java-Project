package com.fastfoodstore.dto;

public class BillDetail2DTO {
    private String billCode;
    private String comboCode;
    private String productCode;
    private String productNote;
    private int amountCombo;
    private float price;

    public BillDetail2DTO() {

    }

    public BillDetail2DTO(String billCode, String comboCode, String productCode, String procductNote, int amountCombo, float price) {
        this.amountCombo = amountCombo;
        this.billCode = billCode;
        this.comboCode = comboCode;
        this.price = price;
        this.productCode = productCode;
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

    public void setPrice(float price) {
        this.price = price;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public float getPrice() {
        return price;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductNote() {
        return productNote;
    }

}
