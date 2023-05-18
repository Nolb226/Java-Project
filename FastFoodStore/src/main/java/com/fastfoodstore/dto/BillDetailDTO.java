package com.fastfoodstore.dto;

public class BillDetailDTO {
    private String billCode;
    private String productCode ;
    private String productNote;
    private int amountProduct;
    private int price;

    public BillDetailDTO() {

    }

    public BillDetailDTO(String billCode,String productCode, String productNote, int amountProduct, int price) {
        this.amountProduct = amountProduct;
        this.billCode = billCode;
        this.price = price;
        this.productCode = productCode;
        this.productNote = productCode;
    }

    public void setAmountProduct(int amountProduct) {
        this.amountProduct = amountProduct;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductNote(String productNote) {
        this.productNote = productNote;
    }

    public int getAmountProduct() {
        return amountProduct;
    }

    public String getBillCode() {
        return billCode;
    }

    public int getPrice() {
        return price;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductNote() {
        return productNote;
    }

    public void addAmount() {
        this.amountProduct ++;
    }
}
