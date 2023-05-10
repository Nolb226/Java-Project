package com.fastfoodstore.gui.item;

import com.fastfoodstore.dto.FunctionsDTO;
import com.fastfoodstore.gui.ProjectUtil;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuItem extends JPanel {

    private JLabel iconLabel;
    private JLabel nameLabel;
    private Boolean selected;
    private Boolean overed;

    public MenuItem(FunctionsDTO data) {
        initComponent();
        setOpaque(false);
        if (data.getFunctionIcon() != "") {
            ProjectUtil.setPicture(iconLabel, data.getFunctionIcon());    
        }
        nameLabel.setText(data.getFunctionName());
    }

    private void initComponent() {
        setIconLabel();
        setNameLabel();

        iconLabel.setForeground(new Color(255, 255, 255));

        nameLabel.setForeground(new Color(255, 255, 255));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(iconLabel)
                                .addGap(20, 20, 20)
                                .addComponent(nameLabel)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addComponent(iconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    public void setIconLabel() {
        this.iconLabel = new JLabel();
        iconLabel.setSize(20, 20);
    }

    public void setNameLabel() {
        this.nameLabel = new JLabel();
        nameLabel.setFont(new Font("Roboto", Font.BOLD, 12));
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    public void setOver(boolean overed) {
        this.overed = overed;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        if (selected || overed) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (selected) {
                g2.setColor(new Color(255, 255, 255, 80));
            } else {
                g2.setColor(new Color(255, 255, 255, 20));
            }
            g2.fillRoundRect(10, 0, getWidth() - 20, getHeight(), 5, 5);
        }
        super.paintComponent(g);
    }

}
