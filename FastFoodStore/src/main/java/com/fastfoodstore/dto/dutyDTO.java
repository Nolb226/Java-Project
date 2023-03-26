package com.fastfoodstore.DTO;

public class dutyDTO {
    private String dutyCode;
    private String dutyName;

    public dutyDTO() {

    }

    public dutyDTO(String dutyCode, String dutyName) {
        this.dutyCode = dutyCode;
        this.dutyName = dutyName;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public String getDutyName() {
        return dutyName;
    }
    
}
