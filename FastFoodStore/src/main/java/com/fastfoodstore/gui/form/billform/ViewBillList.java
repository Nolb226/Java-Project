/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.billform;

import com.fastfoodstore.bus.BillDetailBUS;
import com.fastfoodstore.dto.BillsDTO;
import com.fastfoodstore.gui.form.BillForm;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

/**
 *
 * @author ADMIN
 */
public class ViewBillList<E extends Object> extends JList<E> {

    private DefaultListModel model;
    private int selectedIndex;

    private BillForm billForm;

    public ViewBillList(BillForm billForm) {
        this.model = new DefaultListModel();
        this.billForm = billForm;
        setModel(model);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int index = locationToIndex(e.getPoint());
                    selectedIndex = index;
                    billForm.setBillDetails(BillDetailBUS.
                            selectBillDetailByCode(billForm.getBillsDTOs().get(selectedIndex).getBillCode()));
                    billForm.setBillDetails2(BillDetailBUS.
                            selectBillDetail2ByCode(billForm.getBillsDTOs().get(selectedIndex).getBillCode()));
                    billForm.setData2();
                    repaint();
                }
            }
        });
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object o, int index, boolean isSelected, boolean cellHasFocus) {
                BillsDTO a = new BillsDTO();
                a = (BillsDTO) o;
                ViewBillItem item = new ViewBillItem(a);
                item.setSelected(selectedIndex == index);
                return item;
            }
        };
    }

    public void addItem(BillsDTO data) {
        model.addElement(data);
    }

    public void removeData() {
        model.removeAllElements();
        selectedIndex = -1;
    }

    public int getMySelectedIndex() {
        return selectedIndex;
    }

}
