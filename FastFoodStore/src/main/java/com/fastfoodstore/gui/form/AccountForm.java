/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form;

import com.fastfoodstore.bus.DutyHasFuncBUS;
import com.fastfoodstore.dto.AccountDTO;
import com.fastfoodstore.gui.form.statisticform.StaTable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class AccountForm extends JPanel{
    
    private JScrollPane scrollPane;
    private ArrayList<AccountDTO> accList = new ArrayList<>();
    
    private StaTable accTable;

    public AccountForm() {
        this.accList = DutyHasFuncBUS.getAccount();
        initComponent();
    }
    
    private void initComponent() {
        setLayout(null);
        scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 70, 400, 500);
        scrollPane.setBackground(Color.red);
        add(scrollPane);
    }
    
    
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(0, 0, getWidth() - 20, getHeight());
        super.paintComponent(g);
    }
}
