package com.fastfoodstore.dto;

import java.sql.Date;

public class ShiftsDTO {
    private String shiftsCode;
    private Date date;

    public ShiftsDTO() {

    }

    public ShiftsDTO(String shiftsCode, Date date) {
        this.date = date;
        this.shiftsCode = shiftsCode;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setShiftsCode(String shiftsCode) {
        this.shiftsCode = shiftsCode;
    }

    public Date getDate() {
        return date;
    }

    public String getShiftsCode() {
        return shiftsCode;
    }
    
}
