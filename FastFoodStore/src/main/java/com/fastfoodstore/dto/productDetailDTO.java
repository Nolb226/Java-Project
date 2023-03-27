package com.fastfoodstore.DTO;

public class productDetailDTO {
    private String productCode;
    private String ingredientCode;
    private String recipe;
    private boolean toChange;

    public productDetailDTO() {

    }

    public productDetailDTO(String productCode, String ingredientCode, String recipe, boolean toChange) {
        this.toChange = toChange;
        this.ingredientCode = ingredientCode;
        this.productCode = productCode;
        this.recipe = recipe;
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
    
}
