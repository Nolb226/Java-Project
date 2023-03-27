package com.fastfoodstore.dto;

public class ComboDTO {
    private String comboCode;
    private String comboName;
    private float comboPrice;
    private int numberOfProduct;
    private String comboImage;
    private String groupCode;
    private boolean inMenu;

    public ComboDTO() {

    }

    public ComboDTO(String comboCode, String comboName, float comboPrice, int numberOfProduct, String comboImage, String groupCode, boolean inMenu) {
        this.comboCode = comboCode;
        this.comboImage = comboImage;
        this.comboName = comboName;
        this.comboPrice = comboPrice;
        this.groupCode = groupCode;
        this.inMenu = inMenu;
        this.numberOfProduct = numberOfProduct;
    }

    public void setComboCode(String comboCode) {
        this.comboCode = comboCode;
    }

    public void setComboImage(String comboImage) {
        this.comboImage = comboImage;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public void setComboPrice(float comboPrice) {
        this.comboPrice = comboPrice;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public void setInMenu(boolean inMenu) {
        this.inMenu = inMenu;
    }

    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }

    public String getComboCode() {
        return comboCode;
    }

    public String getComboImage() {
        return comboImage;
    }

    public String getComboName() {
        return comboName;
    }

    public float getComboPrice() {
        return comboPrice;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public int getNumberOfProduct() {
        return numberOfProduct;
    }

    public boolean getInMenu() {
        return inMenu;
    }

}