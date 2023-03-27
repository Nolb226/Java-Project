package com.fastfoodstore.dto;

public class FunctionsDTO {
    private String functionCode;
    private String functionName;

    public FunctionsDTO() {

    }

    public FunctionsDTO(String functionCode, String functionName) {
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
