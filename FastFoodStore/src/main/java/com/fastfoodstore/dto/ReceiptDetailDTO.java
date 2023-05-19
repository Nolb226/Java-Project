package com.fastfoodstore.dto;

public class ReceiptDetailDTO {
    private String receiptCode;
    private String ingredientCode ;
    private float amountInReceipt;
    private int price;

    public ReceiptDetailDTO() {
    }

    public ReceiptDetailDTO(String receiptCode,String ingredientCode, float amouuntInReceipt, int price ) {
        this.amountInReceipt = amouuntInReceipt;
        this.ingredientCode = ingredientCode;
        this.receiptCode = receiptCode;
        this.price = price;
    }

    public void setAmountInReceipt(float amountInReceipt) {
        this.amountInReceipt = amountInReceipt;
    }

    public void setIngredientCode(String ingredientCode) {
        this.ingredientCode = ingredientCode;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public float getAmountInReceipt() {
        return amountInReceipt;
    }

    public String getIngredientCode() {
        return ingredientCode;
    }

    public String getReceiptCode() {
        return receiptCode;
    }
    
}
