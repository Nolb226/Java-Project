/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.components;

import com.fastfoodstore.gui.item.Button;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class CountButton extends JPanel{
    
    private Button subButton;
    private Button addButton;
    
    private JLabel countLabel;
    
    private int countData;

    public CountButton() {
        
        initComponent();
    }
    
    private void initComponent() {
        setPreferredSize(new Dimension(60,20)); 
        setLayout(null);
        setSubButton();
        setAddButton();
        setCountLabel();
        add(subButton);
        add(countLabel);
        add(addButton);
    }

    public void setCountData(int countData) {
        this.countData = countData;
    }
    
    public void countUp() {
        setCountData(countData++);
        System.out.println(countData);
        countLabel.setText(countData + ""); 
    }
    
    public void countDown() {
        setCountData(countData--);
        countLabel.setText(countData + ""); 
    }

    public void setSubButton() {
        this.subButton = new Button("-", 20, 20, 34, 34, 34);
        subButton.setBounds(0, 0, 20, 20);
        subButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("click");
                countDown();
            }
        });
    }

    public void setAddButton() {
        this.addButton = new Button("+", 20, 20, 34, 34, 34);
        addButton.setBounds(40, 0, 20, 20);
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("click");
                countUp(); 
            }
        });
    }

    public void setCountLabel() {
        setCountData(1);
        this.countLabel = new JLabel(countData + "",JLabel.CENTER);
        countLabel.setBounds(20, 0, 20, 20);
    }

    public int getCountData() {
        return countData;
    }
}
