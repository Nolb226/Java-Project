/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.statisticform;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class StaTableItem extends JPanel{
    
    private JLabel contentLabel;
    private String text;

    public StaTableItem() {
    }

    public StaTableItem(String text) {
        this.text = text;
        initComponent();
        setLayout(new BorderLayout());
        setContentLabel();
        add(contentLabel, BorderLayout.CENTER);
    }

    private void initComponent() {
        setBackground(Color.WHITE);
        setOpaque(false);
    }

    public void setContentLabel() {
        this.contentLabel = new JLabel(text);
    }

    public JLabel getContentLabel() {
        return contentLabel;
    }

}
