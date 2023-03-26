package com.fastfoodstore.DTO;

public class billDetailDTO {
    private String billCode;
    private String productCode ;
    private String productNote;
    private int amountProduct;
    private float price;

    public billDetailDTO() {

    }

    public billDetailDTO(String billCode,String productCode, String productNote, int amountProduct, float price) {
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

    public void setPrice(float price) {
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
