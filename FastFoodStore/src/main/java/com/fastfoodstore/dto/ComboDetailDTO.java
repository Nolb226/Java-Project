package com.fastfoodstore.dto;

public class ComboDetailDTO {
    private String comboCode;
    private String productCode;

    public ComboDetailDTO() {

    }

    public ComboDetailDTO(String comboCode, String productCode) {
        this.comboCode = comboCode;
        this.productCode = productCode;
    }

    public void setComboCode(String comboCode) {
        this.comboCode = comboCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getComboCode() {
        return comboCode;
    }

    public String getProductCode() {
        return productCode;
    }

    
}
