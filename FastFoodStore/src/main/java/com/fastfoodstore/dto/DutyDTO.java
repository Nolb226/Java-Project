package com.fastfoodstore.dto;

public class DutyDTO {
    private String dutyCode;
    private String dutyName;
    private String dutypass;

    public DutyDTO() {

    }

    public DutyDTO(String dutyCode, String dutyName) {
        this.dutyCode = dutyCode;
        this.dutyName = dutyName;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public void setDutypass(String dutypass) {
        this.dutypass = dutypass;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public String getDutyName() {
        return dutyName;
    }

    public String getDutypass() {
        return dutypass;
    }
    
}
