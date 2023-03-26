package com.fastfoodstore.DTO;

public class functionsDTO {
    private String functionCode;
    private String functionName;

    public functionsDTO() {

    }

    public functionsDTO(String functionCode, String functionName) {
        this.functionCode = functionCode;
        this.functionName = functionName;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    
}
