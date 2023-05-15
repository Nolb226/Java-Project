/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.menuform;

import com.fastfoodstore.dto.ProductsDTO;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class MenuTable extends JTable {

    public MenuTable() {
        setShowHorizontalLines(true);
        setGridColor(Color.decode("#d9d9d9")); 
        setShowVerticalLines(false); 
        setRowHeight(35);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                MenuTableHeader header = new MenuTableHeader(value + "");
                header.setHorizontalAlignment(JLabel.CENTER);
                return header;
            }

        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                MenuTableItem label;
                if (column != 5) {
                    label = new MenuTableItem(String.valueOf(value));
                    if (column != 2) {
                        label.getContentLabel().setHorizontalAlignment(CENTER);
                    }
                    
                    if (isSelected) {
                        label.getContentLabel().setForeground(new Color(13, 113, 182));
                    } else {
                        label.getContentLabel().setForeground(new Color(102, 102, 102));
                    }
                } else {
                    label = new MenuTableItem();
                }
                return label;
            }
        });
    }
    
    public void setModelTable(String[] col) {
        setModel(new DefaultTableModel(
                new Object[][]{},
                col
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        if (getColumnModel().getColumnCount() > 0) {
            getColumnModel().getColumn(0).setPreferredWidth(5);
            getColumnModel().getColumn(1).setPreferredWidth(55);
            getColumnModel().getColumn(2).setPreferredWidth(300);
            getColumnModel().getColumn(5).setPreferredWidth(50);
            getColumnModel().getColumn(5).setCellEditor(new MenuTableEditor()); 
        }
    }

    public void myAddRow(int stt, ProductsDTO row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(new Object[]{
            stt,
            row.getProductCode(),
            row.getProductName(),
            row.getProductPrice(),
            row.getGroupCode()
        });
    }
}
