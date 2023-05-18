package com.fastfoodstore.dto;

import java.lang.reflect.Field;
import java.sql.Array;
import java.sql.Date;
import java.util.Arrays;
import java.util.Vector;

public class StaffDTO {

    private String _id;
    private String _name;
    private String _email;
    private String _numberPhone;
    private String _address;
    private Date _birthday;
    private String _dutyCode;
    private boolean _status;

    public StaffDTO() {

    }

    public static Field[] getField() {
        Class<StaffDTO> myClass = StaffDTO.class;
        Field[] fields = myClass.getDeclaredFields();
        Field[] filterFields = Arrays.stream(fields).filter(field -> !field.getName().equals("_status")).toArray(Field[]::new);

        for (Field field : filterFields) {
            System.out.println(field.getName());
        }
        
        return filterFields;
    }
    
    public StaffDTO(StaffDTO t) {
        this._id = t._id;
        this._name= t._name;
        this._address = t._address;
        this._birthday = t._birthday;
        this._dutyCode = t._dutyCode;
        this._email = t._email;
        this._status= t._status;
        this._numberPhone = t._numberPhone;
    }

    public StaffDTO(String _id, String _name, String _email, String _numberPhone, String _address, Date _birthday, String _dutyCode, boolean _status) {
        this._id = _id;
        this._address = _address;
        this._birthday = _birthday;
        this._dutyCode = _dutyCode;
        this._email = _email;
        this._name = _name;
        this._status = _status;
        this._numberPhone = _numberPhone;
    }

    public void setAddress(String _address) {
        this._address = _address;
    }

    public void setBirthday(Date _birthday) {
        this._birthday = _birthday;
    }

    public void setDutyCode(String _dutyCode) {
        this._dutyCode = _dutyCode;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public void setID(String iD) {
        _id = iD;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public void setNumberPhone(String _numberPhone) {
        this._numberPhone = _numberPhone;
    }

    public void setStatus(boolean _status) {
        this._status = _status;
    }

    public String getAddress() {
        return _address;
    }

    public Date getBirthday() {
        return _birthday;
    }

    public String getDutyCode() {
        return _dutyCode;
    }

    public String getEmail() {
        return _email;
    }

    public String getNumberPhone() {
        return _numberPhone;
    }

    public String getID() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public boolean getStatus() {
        return _status;
    }

    public Object[] data() {
        try {

            Field[] attributes = getField();
            Object[] info = new Object[attributes.length];
            for (int i = 0; i < attributes.length; i++) {
                Field field = attributes[i];

                info[i] = field.get(this);
            }

            System.out.println(info.toString());
            return info;
        } catch (Exception e) {
        }
        return null;

    }

}
