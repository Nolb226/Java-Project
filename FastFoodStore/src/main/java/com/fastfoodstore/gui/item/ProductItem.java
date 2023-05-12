/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.item;

import com.fastfoodstore.dto.ComboDTO;
import com.fastfoodstore.dto.GroupDTO;
import com.fastfoodstore.dto.ProductsDTO;
import com.fastfoodstore.gui.ProjectUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author ADMIN
 */
public class ProductItem extends JPanel {

    private JPanel imgPanel;
    private BufferedImage img;

    private JTextPane nameLabel;
    private JLabel priceLabel;

    private ProductsDTO dataProduct;
    private GroupDTO dataGroup;
    private ComboDTO dataCombo;

    public ProductItem(ProductsDTO data) {
        this.dataProduct = data;
        img = ProjectUtil.addImg(dataProduct.getProductImage());
        initComponent();
        initComponentProduct();

    }

    public ProductItem(GroupDTO data) {
        this.dataGroup = data;
        img = ProjectUtil.addImg(dataGroup.getGroupIcon());
        initComponent();
        initComponentGroup();

    }

    public ProductItem(ComboDTO data) {
        this.dataCombo = data;
        img = ProjectUtil.addImg(dataCombo.getComboImage());
        initComponent();
        initComponentCombo();
    }

    private void initComponentProduct() {
        setNameLabel(dataProduct.getProductName());
        setPriceLabel(String.valueOf(dataProduct.getProductPrice()));
        setLayout(null);
        add(imgPanel);
        add(nameLabel);
        add(priceLabel);
    }

    private void initComponentGroup() {
        setNameLabel(dataGroup.getGroupName());
        setLayout(null);
        add(imgPanel);
        add(nameLabel);
    }

    private void initComponentCombo() {
        setNameLabel(dataCombo.getComboName());
        setPriceLabel(String.valueOf(dataCombo.getComboPrice()));
        setLayout(null);
        add(imgPanel);
        add(nameLabel);
        add(priceLabel);
    }
    
    private void initComponent() {
        setPreferredSize(new Dimension(130, 180));
        setBorder(new EmptyBorder(20,0,0,0));
        setImgPanel();
    }

    public void setNameLabel(String text) {
        this.nameLabel = new JTextPane();
        StyledDocument documentStyle = nameLabel.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
        nameLabel.setText(text);
        nameLabel.setBounds(0, 110, 130, 40);
        nameLabel.setBackground(getBackground());
        nameLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
        nameLabel.setForeground(Color.decode("#555555"));
        nameLabel.setEditable(false); 
    }

    public void setPriceLabel(String text) {
        this.priceLabel = new JLabel(text, JLabel.RIGHT);
        priceLabel.setBounds(0, 150, 130, 20);
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        priceLabel.setForeground(Color.decode("#F1A259"));
    }

    public void setImgPanel() {
        this.imgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.setClip(roundedRect);
                g2d.setColor(this.getBackground());
                g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                g2d.draw(roundedRect);
            }
        };
        imgPanel.setBounds(15, 0, 100, 100);
    }

    public JPanel getImgPanel() {
        return imgPanel;
    }

    public GroupDTO getDataGroup() {
        return dataGroup;
    }

    public ProductsDTO getDataProduct() {
        return dataProduct;
    }

    public ComboDTO getDataCombo() {
        return dataCombo;
    }

    public Object getData() {
        if (dataGroup != null) {
            return dataGroup;
        } else if (dataProduct != null) {
            return dataProduct;
        } else {
            return dataCombo;
        }
    }

}
