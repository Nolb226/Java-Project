/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.statisticform;

import com.fastfoodstore.dto.ComboDTO;
import com.fastfoodstore.dto.ProductsDTO;
import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.form.menuform.MenuTableHeader;
import com.fastfoodstore.gui.form.menuform.MenuTableItem;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class StaTable extends JTable {

    public StaTable() {
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
                if (column != 2) {
                    label = new MenuTableItem(String.valueOf(value));
                    label.getContentLabel().setHorizontalAlignment(CENTER);
                } else {
                    label = new MenuTableItem("   " + String.valueOf(value));
                }
                if (isSelected) {
                    label.getContentLabel().setForeground(ProjectUtil.getMyOrangeColor());
                } else {
                    label.getContentLabel().setForeground(new Color(102, 102, 102));
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
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        if (getColumnModel().getColumnCount() >= 5) {
            getColumnModel().getColumn(0).setPreferredWidth(5);
            getColumnModel().getColumn(1).setPreferredWidth(55);
            getColumnModel().getColumn(2).setPreferredWidth(370);
            getColumnModel().getColumn(3).setPreferredWidth(50);
            getColumnModel().getColumn(4).setPreferredWidth(50);
        }
    }

    public void myAddRow(int stt, ProductsDTO row, int data) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(new Object[]{
            stt,
            row.getProductCode(),
            row.getProductName(),
            row.getProductPrice(),
            data
        });
    }

    public void myAddRow(int stt, ComboDTO row, int data) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(new Object[]{
            stt,
            row.getComboCode(),
            row.getComboName(),
            row.getComboPrice(),
            data
        });
    }
    
    public void myAddRow(String date, String data) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(new Object[]{
            date,
            data
        });
    }
}
