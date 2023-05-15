/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.components;

import com.fastfoodstore.gui.item.BillItem;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;


public class ListBillDetail<E extends Object> extends JList<E>{
    
    private final DefaultListModel model;
    private int selectedIndex = -1;
    
    public ListBillDetail() {
        this.model = new DefaultListModel();
        setModel(model);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int index = locationToIndex(e.getPoint());
                    selectedIndex = index;
                    repaint();    
                }
            }
            
        });
    }
    
    public ListCellRenderer<? super E> getCellRenderer() {
        
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object o, int index, boolean isSelected, boolean cellHasFocus) {
                BillItem item = new BillItem(o);
                item.setSelected(selectedIndex == index); 
                return item;
            }
        };
    }
    
    public void addItem(Object data) {
        model.addElement(data); 
    }
    
    public void removeItem() {
        model.removeElementAt(selectedIndex);
        selectedIndex = -1;
    }
    
    public void removeData() {
        model.removeAllElements();
        selectedIndex = -1;
    }

    public int getMySelectedIndex() {
        return selectedIndex;
    }
    
}


