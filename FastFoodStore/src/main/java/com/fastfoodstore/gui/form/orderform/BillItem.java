/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.item;

import com.fastfoodstore.bus.ComboBUS;
import com.fastfoodstore.bus.ProductsBUS;
import com.fastfoodstore.dto.BillDetail2DTO;
import com.fastfoodstore.dto.BillDetailDTO;
import com.fastfoodstore.dto.ComboDTO;
import com.fastfoodstore.dto.ProductsDTO;
import com.fastfoodstore.gui.ProjectUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class BillItem extends JPanel{
    
    private ProductsDTO dataProduct = null;
    private ComboDTO dataCombo = null;
    
    private JPanel topPanel;
    private JLabel nameLabel;
    private JLabel priceLabel;
    
    private JPanel emptyPanel;
    private boolean selected = false;
    
    private Font f = new Font("Segoe UI Semibold", Font.PLAIN, 12);
    
    private int amount = 1;
    
    public BillItem(Object data) {
        if(data instanceof BillDetailDTO) {
            this.dataProduct = ProductsBUS.getProductsByCode(((BillDetailDTO) data).getProductCode()); 
            amount = ((BillDetailDTO) data).getAmountProduct();
            initComponent();
            initComponentProduct();
        } else {
            this.dataCombo = ComboBUS.getComboByCode(((BillDetail2DTO) data).getComboCode());
            amount = ((BillDetail2DTO) data).getAmountCombo();
            initComponent();
            initComponentCombo();
        }
    }
    
    private void initComponent() {
        setPreferredSize(new Dimension(250,35)); 
        setBackground(new Color(0,0,0,0)); 
        setLayout(null);
        setEmptyPanel();
        add(emptyPanel);
    }
    
    private void initComponentProduct() {
        setTopPanel(dataProduct.getProductName()); 
        add(topPanel);
    }
    
    private void initComponentCombo() {
        setTopPanel(dataCombo.getComboName()); 
        add(topPanel);
    }

    public void setTopPanel(String text) {
        this.topPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                super.paintComponent(g); 
            }
        };
        topPanel.setOpaque(false); 
        topPanel.setBounds(0, 0, 250, 25);
        topPanel.setBackground(Color.decode("#d9d9d9")); 
        topPanel.setLayout(null);
        setNameLabel(text);
        setPriceLabel();
        topPanel.add(nameLabel);
        topPanel.add(priceLabel);
    }

    public void setNameLabel(String text) {
        this.nameLabel = new JLabel(" " + amount + "x " + text+"");
        nameLabel.setFont(f);
        nameLabel.setBounds(0,0, 200, 25);
    }

    public void setPriceLabel() {
        if(dataProduct != null) {
            this.priceLabel = new JLabel("" + amount*dataProduct.getProductPrice()+" ", JLabel.RIGHT);   
        } else {
            this.priceLabel = new JLabel("" + amount*dataCombo.getComboPrice()+" ", JLabel.RIGHT);
        }
        priceLabel.setBounds(200, 0, 50, 25);
        priceLabel.setFont(f);
    }

    public void setEmptyPanel() {
        this.emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(0,0,0,0)); 
        emptyPanel.setBounds(0,25,250,10);
    }

    public void addAmount() {
        this.amount ++;
    }
    
    public void subAmount() {
        this.amount --;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if(selected) {
            topPanel.setBackground(ProjectUtil.getMyGreenColor());     
        } else {
            topPanel.setBackground(Color.decode("#d9d9d9")); 
        }
    }
    
    
}
