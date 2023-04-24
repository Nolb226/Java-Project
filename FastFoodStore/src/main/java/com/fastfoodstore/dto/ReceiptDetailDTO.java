package com.fastfoodstore.dto;

public class ReceiptDetailDTO {
    private String receiptCode;
    private String ingredientCode ;
    private float amountInReceipt;

    public ReceiptDetailDTO() {

    }

    public ReceiptDetailDTO(String receiptCode,String ingredientCode, float amouuntInReceipt) {
        this.amountInReceipt = amouuntInReceipt;
        this.ingredientCode = ingredientCode;
        this.receiptCode = receiptCode;
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