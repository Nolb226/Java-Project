package com.fastfoodstore.DTO;

import java.sql.Date;
import java.sql.Time;

public class workDTO {
    private String ID;
    private String shiftsCode;
    private Date checkIn;
    private Date chechOut;
    private String session;
    private Time time;

    public workDTO() {

    }

    public workDTO(String ID, String shiftsCode, Date checkIn, Date checkOut, String session, Time time) {
        this.ID = ID;
        this.chechOut = checkOut;
        this.checkIn = checkIn;
        this.session = session;
        this.shiftsCode = shiftsCode;
        this.time = time;
    }

    public void setChechOut(Date chechOut) {
        this.chechOut = chechOut;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public void setShiftsCode(String shiftsCode) {
        this.shiftsCode = shiftsCode;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getChechOut() {
        return chechOut;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public String getID() {
        return ID;
    }

    public String getSession() {
        return session;
    }

    public String getShiftsCode() {
        return shiftsCode;
    }

    public Time getTime() {
        return time;
    }
    
}
