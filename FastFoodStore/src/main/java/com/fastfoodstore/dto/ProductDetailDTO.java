package com.fastfoodstore.dto;

public class ProductDetailDTO {
    private String productCode;
    private String productName;
    private String ingredientCode;
    private String ingredientName;
    private String recipe;
    private boolean toChange;

    public ProductDetailDTO() {

    }

    public ProductDetailDTO(String productCode, String ingredientCode, String recipe, boolean toChange, String productName, String ingredientName) {
        this.toChange = toChange;
        this.ingredientCode = ingredientCode;
        this.productCode = productCode;
        this.recipe = recipe;
        this.productName = productName;
        this.ingredientName = ingredientName;
    }

    public void setIngredientCode(String ingredientCode) {
        this.ingredientCode = ingredientCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public void setToChange(boolean toChange) {
        this.toChange = toChange;
    }
    
    public void setProductName(String productName){
        this.productName = productName;
    }
    
    public void setIngredientName(String ingredientName){
        this.ingredientName = ingredientName;
    }

    public String getIngredientCode() {
        return ingredientCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getRecipe() {
        return recipe;
    }

    public boolean getToChange(){
        return toChange;
    }
    
    public String getProductName(){
        return productName;
    }
    
    public String getIngredientName(){
        return ingredientName;
    }
}
