/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.menuform;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ADMIN
 */
public class MenuTableHeader extends JLabel{
    public MenuTableHeader(String text) {
        super(text);
        setOpaque(true);
        setBackground(Color.WHITE);
        setFont(new Font("sansserif",1,14));
        setForeground(new Color(102,102,102)); 
        setBorder(new EmptyBorder(10,5,10,5)); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.setColor(new Color(230,230,230)); 
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }
}
