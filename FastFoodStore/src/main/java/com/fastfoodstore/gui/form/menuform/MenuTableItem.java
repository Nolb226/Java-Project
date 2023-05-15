/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.menuform;

import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.item.Button;
import com.fastfoodstore.gui.toggle.ToggleButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class MenuTableItem extends JPanel {

    private JLabel contentLabel;
    private String text;
    private JButton detailButton;

    public MenuTableItem() {
        initComponent();
        setDetailButton();
        setLayout(null);
        setBackground(Color.red);
        add(detailButton);
    }

    public MenuTableItem(String text) {
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

    public void initEvent() {
        detailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("haha");
            }

        });
    }

    public void setContentLabel() {
        this.contentLabel = new JLabel(text);
        contentLabel.setBounds(0, 0, 100, 35);
    }

    public JLabel getContentLabel() {
        return contentLabel;
    }

    public void setDetailButton() {
        this.detailButton = new JButton("Chi tiết") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(ProjectUtil.getMyGreenColor());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }

        };
        detailButton.setBorder(BorderFactory.createEmptyBorder()); 
        detailButton.setFocusable(false); 
        detailButton.setOpaque(false);
        detailButton.setContentAreaFilled(false); 
        detailButton.setForeground(Color.decode("#eeeeee")); 
        detailButton.setBounds(15, 5, 70, 25);
    }

}
