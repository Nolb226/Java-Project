/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.bus;

import com.fastfoodstore.dao.DutyDAO;
import com.fastfoodstore.dao.StaffDAO;
import com.fastfoodstore.dto.DutyDTO;
import com.fastfoodstore.dto.StaffDTO;
import com.fastfoodstore.gui.form.StaffForm;
import java.io.File;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author k
 */
public class Staff_BUS {

    private StaffDAO _dbStaff = StaffDAO.getInstance();
    private DutyDAO _dbDuty = DutyDAO.getInstance();

    public Staff_BUS() {

    }

    public ArrayList<StaffDTO> getAll() {
        return _dbStaff.selectAll();
    }

    public ArrayList<DutyDTO> getAllDutyDTOs() {

        return _dbDuty.selectAll();
    }

    public ArrayList<StaffDTO> update(StaffDTO staff) {

        _dbStaff.update(staff);

        return _dbStaff.selectAll();
    }

    public StaffDTO getOne(String id) {

        return _dbStaff.selectById(id);
    }

    public StaffDTO delete(StaffDTO t) {
        _dbStaff.delete(t);
        return t;
    }

    public StaffDTO create(StaffDTO t) {
        _dbStaff.insert(t);
        return t;
    }

    public void ExportExcel() {
        _dbStaff.ExportExcelDatabase();
    }

    public void ImportExcel(File file) {
        _dbStaff.ImportExcelDatabase(file);
    }

    public void init() {
    }

    public void ImportExcel(FileNameExtensionFilter file) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
