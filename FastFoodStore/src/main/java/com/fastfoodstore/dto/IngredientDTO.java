package com.fastfoodstore.dto;

public class IngredientDTO {

    public static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private String ingredientCode;
    private String ingredientName;
    private int amount;
    private int cost;

    public IngredientDTO() {

    }

    public IngredientDTO(String ingredientCode, String ingredientName, int amount, int cost) {
        this.ingredientCode = ingredientCode;
        this.ingredientName = ingredientName;
        this.amount = amount;
        this.cost = cost;
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
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public int getAmount(){
        return amount;
    }
    
    public void setCost(int cost) {
        this.cost = cost;
    }
    
    public int getCost(){
        return cost;
    }
    
    public void addAmount(){
        this.amount ++;
    }
}
