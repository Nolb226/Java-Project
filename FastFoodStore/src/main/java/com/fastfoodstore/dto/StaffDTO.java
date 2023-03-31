package com.fastfoodstore.dto;

import java.sql.Date;

public class StaffDTO {
    private String ID;
    private String email;
    private String name;
    private String address;
    private Date birthday;
    private String dutyCode;
    private boolean status;

    public StaffDTO() {

    }

    public StaffDTO(String ID, String email, String name, String address, Date birthday, String dutyCode, boolean status) {
        this.ID = ID;
        this.address = address;
        this.birthday = birthday;
        this.dutyCode = dutyCode;
        this.email = email;
        this.name = name;
        this.status = status;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public String getEmail() {
        return email;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public boolean getStatus() {
        return status;
    }
}
