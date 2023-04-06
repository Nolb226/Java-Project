package com.fastfoodstore.dto;

public class PromotionsDTO {
    private String promoCode;
    private String promoGenre;
    private String productCode;
    private Float discount;

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    private boolean dueDate;

    public PromotionsDTO() {

    }

    public PromotionsDTO(String promoCode, String promoGenre, String productCode, boolean dueDate, Float discount) {
        this.dueDate = dueDate;
        this.productCode = productCode;
        this.promoCode = promoCode;
        this.promoGenre = promoGenre;
    }

    public void setDueDate(boolean dueDate) {
        this.dueDate = dueDate;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public void setPromoGenre(String promoGenre) {
        this.promoGenre = promoGenre;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public String getPromoGenre() {
        return promoGenre;
    }

    public boolean getDueDate() {
        return dueDate;
    }
    
    public Float getDiscount() {
        return discount;
    }
}
