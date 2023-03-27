package com.fastfoodstore.dto;

public class HasDTO {
    private String dutyCode;
    private String functionCode;

    public HasDTO() {

    }

    public HasDTO(String dutyCode, String functionCode) {
        this.dutyCode = dutyCode;
        this.functionCode = functionCode;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public String getFunctionCode() {
        return functionCode;
    }
    
}
