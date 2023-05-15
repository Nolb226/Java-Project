/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.menuform;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author ADMIN
 */
public class MenuTableEditor extends DefaultCellEditor{
    
    public MenuTableEditor() {
        super(new JCheckBox()); 
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        MenuTableItem item = new MenuTableItem();
        item.setBackground(table.getBackground()); 
        item.initEvent();
        return item;
    }
    
    
}
