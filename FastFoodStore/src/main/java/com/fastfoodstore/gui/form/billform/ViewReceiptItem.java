/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.billform;

import com.fastfoodstore.dto.ReceiptsDTO;
import com.fastfoodstore.gui.ProjectUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class ViewReceiptItem extends JPanel{
    private ReceiptsDTO receiptData;
    
    private JLabel receiptCodeLabel;
    private JLabel receiptDateLabel;
    private JLabel receiptPriceLabel;
    
    private boolean selected = false;

    public ViewReceiptItem(ReceiptsDTO data) {
        this.receiptData = data;
        initComponent();
    }
    
    private void initComponent() {
        setLayout(new GridLayout(0,3)); 
        setPreferredSize(new Dimension(310,30)); 
        setReceiptCodeLabel();
        setReceiptDateLabel();
        setReceiptPriceLabel();
        add(receiptCodeLabel);
        add(receiptDateLabel);
        add(receiptPriceLabel);
    }

    public void setReceiptCodeLabel() {
        this.receiptCodeLabel = new JLabel("  " + receiptData.getReceiptCode(), JLabel.LEFT); 
    }

    public void setReceiptDateLabel() {
        this.receiptDateLabel = new JLabel(receiptData.getDateString(), JLabel.LEFT);
    }

    public void setReceiptPriceLabel() {
        this.receiptPriceLabel = new JLabel(ProjectUtil.toMoney((int)receiptData.getTotalPrice())+"  ",JLabel.RIGHT);  
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if(selected) {
            setBackground(Color.decode("#444444"));
            receiptCodeLabel.setForeground(Color.decode("#eeeeee"));
            receiptDateLabel.setForeground(Color.decode("#eeeeee"));
            receiptPriceLabel.setForeground(Color.decode("#eeeeee"));
        } else {
            setBackground(Color.decode("#eeeeee"));
            receiptCodeLabel.setForeground(Color.decode("#444444"));
            receiptDateLabel.setForeground(Color.decode("#444444"));
            receiptPriceLabel.setForeground(Color.decode("#444444"));
        }
    }
}
