/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.item;

import com.fastfoodstore.dto.BillsDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.html.HTML;

/**
 *
 * @author ADMIN
 */
public class ViewBillItem extends JPanel{
    
    private BillsDTO billData;
    
    private JLabel billCodeLabel;
    private JLabel billDateLabel;
    private JLabel billPriceLabel;
    
    private boolean selected = false;

    public ViewBillItem(BillsDTO data) {
        this.billData = data;
        initComponent();
    }
    
    private void initComponent() {
        setLayout(new GridLayout(0,3)); 
        setPreferredSize(new Dimension(310,30)); 
        setBillCodeLabel();
        setBillDateLabel();
        setBillPriceLabel();
        add(billCodeLabel);
        add(billDateLabel);
        add(billPriceLabel);
    }

    public void setBillCodeLabel() {
        this.billCodeLabel = new JLabel("  " + billData.getBillCode(), JLabel.LEFT); 
    }

    public void setBillDateLabel() {
        this.billDateLabel = new JLabel(billData.getDate(), JLabel.LEFT);
    }

    public void setBillPriceLabel() {
        this.billPriceLabel = new JLabel(billData.getTotalPrice() + " VND  ",JLabel.RIGHT); 
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if(selected) {
            setBackground(Color.decode("#444444"));
            billCodeLabel.setForeground(Color.decode("#eeeeee"));
            billDateLabel.setForeground(Color.decode("#eeeeee"));
            billPriceLabel.setForeground(Color.decode("#eeeeee"));
        } else {
            setBackground(Color.decode("#eeeeee"));
            billCodeLabel.setForeground(Color.decode("#444444"));
            billDateLabel.setForeground(Color.decode("#444444"));
            billPriceLabel.setForeground(Color.decode("#444444"));
        }
    }
    
    
}
