/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form;

import com.fastfoodstore.gui.item.SearchTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class SettingForm extends JPanel {

    private JPanel modalPanel;
    private JPanel backgroudPanel;
    private SearchTextField inputTextField;

    public SettingForm() {
        initComponent();
        setOpaque(false);
    }

    private void initComponent() {
        setModalPanel();
        setBackgroudPanel();
        setInputTextField();
        setBackground(new Color(255,255,255)); 
        setLayout(new BorderLayout()); 
        
        backgroudPanel.setLayout(new GridBagLayout()); 
        modalPanel.setLayout(null);

        modalPanel.setBackground(new Color(255, 255, 255));
        modalPanel.setFont(new Font("Segoe UI", 0, 36));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        add(backgroudPanel,BorderLayout.CENTER); 
        backgroudPanel.add(modalPanel, gbc);
        modalPanel.add(inputTextField);

    }

    public void setModalPanel() {
        this.modalPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                super.paintComponent(g);
            }

        };
        modalPanel.setOpaque(false); 
        modalPanel.setPreferredSize(new Dimension(400, 150));
    }

    public void setBackgroudPanel() {
        this.backgroudPanel = new JPanel();
        backgroudPanel.setBackground(new Color(0,0,0,50)); 
        backgroudPanel.setPreferredSize(new Dimension(getWidth(), getHeight())); 
    }

    public void setInputTextField() {
        this.inputTextField = new SearchTextField();
        inputTextField.setBounds(50, 20, 300, 30);
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
