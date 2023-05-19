package com.fastfoodstore.dto;

public class ProductsDTO {
    
    private String productCode;
    private String productName;	
    private int productPrice;	
    private char productSize;
    private String productImage;	
    private String groupCode;
    private int inMenu;

    public ProductsDTO() {

    }

    public ProductsDTO(String productCode, String productName, int productPrice, char productSize, String productImage, String groupCode, int inMenu) {
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productSize = productSize;
        this.productImage = productImage;
        this.groupCode = groupCode;
        this.inMenu = inMenu;
    }

    

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    
    public void setInMenu(int inMenu) {
        this.inMenu = inMenu;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
    
    public void setProductSize(char productSize) {
        this.productSize = productSize;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public int getInMenu() {
        return inMenu;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public char getProductSize() {
        return productSize;
    }
}
