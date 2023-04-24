package com.fastfoodstore.dto;

public class ProductsDTO {
    
    private String productCode;
    private String productName;	
    private float productPrice;	
    private int productGenre;	
    private char productSize;
    private String productImage;	
    private String groupCode;
    private int inMenu;

    public ProductsDTO() {

    }

    public ProductsDTO(String productCode, String productName, float productPrice, int productGenre, char productSize, String productImage, String groupCode, int inMenu) {
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productGenre = productGenre;
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

    public void setProductGenre(int productGenre) {
        this.productGenre = productGenre;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setProductPrice(float productPrice) {
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

    public int getProductGenre() {
        return productGenre;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductName() {
        return productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public char getProductSize() {
        return productSize;
    }
}