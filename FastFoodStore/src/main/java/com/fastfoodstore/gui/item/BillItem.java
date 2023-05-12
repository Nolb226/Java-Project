/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.item;

import com.fastfoodstore.dto.ComboDTO;
import com.fastfoodstore.dto.ProductsDTO;
import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.components.CountButton;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class BillItem extends JPanel{
    
    private ProductsDTO dataProduct;
    private ComboDTO dataCombo;
    
    private JPanel topPanel;
    private JLabel topLabel;
    
    private JPanel midPanel;
    
    private JPanel footerPanel;
    
    private Button deleteButton;
    private Button changeButton;
    private CountButton countButton;
    

    public BillItem(ProductsDTO data) {
        this.dataProduct = data;
        initComponent();
        initComponentProduct();
    }
    
    public BillItem(ComboDTO data) {
        this.dataCombo = data;
        initComponent();
        initComponentCombo();
    }
    
    private void initComponent() {
        setPreferredSize(new Dimension(260,40)); 
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
        this.topPanel = new JPanel();
        topPanel.setBounds(0, 0, 260, 40);
        topPanel.setBackground(Color.decode("#d9d9d9")); 
        topPanel.setLayout(null);
        this.topLabel = new JLabel(text);
        topLabel.setBounds(0,0, 260, 40);
        topPanel.add(topLabel);
    }

    public void setDeleteButton() {
        this.deleteButton = new Button("Xóa", 60, 20, ProjectUtil.getMyRedColor());
        deleteButton.setBounds(20, 10, 60, 20);
    }

    public void setChangeButton() {
        this.changeButton = new Button("Điều chỉnh",60,20,ProjectUtil.getMyGreenColor()); 
        deleteButton.setBounds(180, 10, 60, 20);
    }

    
    
    
    
}
