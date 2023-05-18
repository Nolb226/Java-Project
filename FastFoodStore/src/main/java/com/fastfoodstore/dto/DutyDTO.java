package com.fastfoodstore.dto;

public class DutyDTO {
    private String dutyCode;
    private String dutyName;
    private String dutyPass;

    public DutyDTO() {

    }

    public DutyDTO(String dutyCode, String dutyName, String dutyPass) {
        this.dutyCode = dutyCode;
        this.dutyName = dutyName;
        this.dutyPass = dutyPass;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public void setDutypass(String dutyPass) {
        this.dutyPass = dutyPass;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public String getDutyName() {
        return dutyName;
    }

    public String getDutypass() {
        return dutyPass;
    }
    
}
