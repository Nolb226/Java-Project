package com.fastfoodstore.dto;

public class IngredientDTO {
    private String ingredientCode;
    private String ingredientName;

    public IngredientDTO() {

    }

    public IngredientDTO(String ingredientCode, String ingredientName) {
        this.ingredientCode = ingredientCode;
        this.ingredientName = ingredientName;
    }

    public void setIngredientCode(String ingredientCode) {
        this.ingredientCode = ingredientCode;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientCode() {
        return ingredientCode;
    }

    public String getIngredientName() {
        return ingredientName;
    }
    
}
