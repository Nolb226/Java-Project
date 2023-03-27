package com.fastfoodstore.DTO;

public class hasDTO {
    private String dutyCode;
    private String functionCode;

    public hasDTO() {

    }

    public hasDTO(String dutyCode, String functionCode) {
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
