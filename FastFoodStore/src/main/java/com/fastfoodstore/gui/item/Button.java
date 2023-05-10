/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.item;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Button extends JPanel{
    
    private String textString;
    private JLabel textLabel;
    
    private int widthFloat;
    private int heightFloat;
    private int R;
    private int G;
    private int B;
    private Color thisColor;

    public Button(String text) {
        this.textString = text;
        initComponent();
        setOpaque(false); 
    }

    public Button(String textString, int widthFloat, int heightFloat, int R, int G, int B) {
        this.textString = textString;
        this.widthFloat = widthFloat;
        this.heightFloat = heightFloat;
        this.R = R;
        this.G = G;
        this.B = B;
        this.thisColor = new Color(R, G, B);
        initComponent();
        setOpaque(false); 
    }
    
    public Button(String textString, int widthFloat, int heightFloat, Color c) {
        this.textString = textString;
        this.widthFloat = widthFloat;
        this.heightFloat = heightFloat;
        this.thisColor = c;
        initComponent();
        setOpaque(false); 
    }
    
    private void initComponent() {
        setTextLabel();
        setBackground(thisColor);  
        setPreferredSize(new Dimension(widthFloat, heightFloat)); 
        
        setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        add(textLabel, gbc);
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR)); 
            }
            
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(g); 
    }

    public void setTextLabel() {
        this.textLabel = new JLabel(textString,JLabel.CENTER);  
        textLabel.setPreferredSize(new Dimension(widthFloat,heightFloat)); 
        textLabel.setFont(new Font("Sans serif",Font.BOLD, 12)); 
        textLabel.setForeground(Color.WHITE); 
    }

    public String getTextString() {
        return textString;
    }

    public void setTextString(String textString) {
        this.textString = textString;
    }

    public JLabel getTextLabel() {
        return textLabel;
    }

    public void setTextLabel(JLabel textLabel) {
        this.textLabel = textLabel;
    }

    public int getWidthFloat() {
        return widthFloat;
    }

    public void setWidthFloat(int widthFloat) {
        this.widthFloat = widthFloat;
    }

    public int getHeightFloat() {
        return heightFloat;
    }

    public void setHeightFloat(int heightFloat) {
        this.heightFloat = heightFloat;
    }

    public Integer getR() {
        return R;
    }

    public void setR(int R) {
        this.R = R;
    }

    public Integer getG() {
        return G;
    }

    public void setG(int G) {
        this.G = G;
    }

    public Integer getB() {
        return B;
    }

    public void setB(int B) {
        this.B = B;
    }

    public Color getThisColor() {
        return thisColor;
    }

    public void setThisColor(Color thisColor) {
        this.thisColor = thisColor;
    }
    
    
    
}

