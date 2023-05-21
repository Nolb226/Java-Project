/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.billform;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author ASUS
 */
public class ReceiptDetailList <E extends Object> extends JList<E> {
     private DefaultListModel model;
    
    public ReceiptDetailList() {
        model = new DefaultListModel<>();
        setBackground(Color.decode("#ffffff")); 
        setModel(model); 
    }
    
    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object o, int index, boolean isSelected, boolean cellHasFocus) {
                ReceiptDetailItem a = new ReceiptDetailItem();
                a = (ReceiptDetailItem) o;
                return a;
            }
        };
    }
    
    public void addItem(ReceiptDetailItem data) {
        model.addElement(data);
    }
    
    public void removeData() {
        model.removeAllElements();
    }
}
