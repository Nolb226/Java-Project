package com.fastfoodstore.DTO;

public class productsDTO {
    
    private String productCode;
    private String productName;	
    private float productPrice;	
    private int productGenre;	
    private char productSize	;
    private String productImage;	
    private String groupCode	;
    private int inMenu;

    public productsDTO() {

    }

    public productsDTO(String productCode, String productName, float productPrice, int productGenre, char productSize, String productImage, String groupCode, int inMenu) {
        this.groupCode = groupCode;
        this.inMenu = inMenu;
        this.productCode = productCode;
        this.productGenre = productGenre;
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productSize = productSize;
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
