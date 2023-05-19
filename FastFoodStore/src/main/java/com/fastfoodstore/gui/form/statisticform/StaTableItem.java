/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.statisticform;

import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.form.menuform.MenuTableAction;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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
