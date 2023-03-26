package com.fastfoodstore.DTO;

import java.sql.Date;

public class shiftsDTO {
    private String shiftsCode;
    private Date date;

    public shiftsDTO() {

    }

    public shiftsDTO(String shiftsCode, Date date) {
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
