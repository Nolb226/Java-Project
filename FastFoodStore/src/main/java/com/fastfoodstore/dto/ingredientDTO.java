package com.fastfoodstore.DTO;

public class ingredientDTO {
    private String ingredientCode;
    private String ingredientName;

    public ingredientDTO() {

    }

    public ingredientDTO(String ingredientCode, String ingredientName) {
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
