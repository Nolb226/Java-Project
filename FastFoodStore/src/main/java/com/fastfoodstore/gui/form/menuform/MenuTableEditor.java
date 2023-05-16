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
    
    MenuTableAction eventAction;
    
    public MenuTableEditor(MenuTableAction eventAction) {
        super(new JCheckBox()); 
        this.eventAction = eventAction;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        MenuTableItem item = new MenuTableItem();
        item.setBackground(table.getBackground()); 
        item.initEvent(eventAction,row);
        return item;
    }
    
    
}
