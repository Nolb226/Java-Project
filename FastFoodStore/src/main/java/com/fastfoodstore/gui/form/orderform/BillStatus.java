/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.orderform;

import com.fastfoodstore.gui.ProjectUtil;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BillStatus extends JPanel {

    private JPanel imgPanel;
    private BufferedImage img;
    private JPanel topPanel;

    private JLabel textLabel;
    private String content;

    public BillStatus(String path, String text) {
        img = ProjectUtil.addImg(path);
        content = text;
        initComponent();
    }

    private void initComponent() {
        setLayout(null);
        setBackground(Color.decode("#333333"));
        setTopPanel();
        setTextLabel();
        add(topPanel);
        add(textLabel);
    }

    public void setTopPanel() {
        this.topPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode("#eeeeee")); 
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
            }
        };
        topPanel.setLayout(null);
        topPanel.setOpaque(false); 
        topPanel.setBounds(0, 0, 80, 120);
        setImgPanel();
        topPanel.add(imgPanel);

    }

    public void setImgPanel() {
        this.imgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.setClip(roundedRect);
                g2d.setColor(topPanel.getBackground());
                g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                g2d.draw(roundedRect);
            }
        };
        imgPanel.setBounds(0, 15, 80, 80);
    }

    public void setTextLabel() {
        this.textLabel = new JLabel(content, JLabel.CENTER);
        textLabel.setForeground(Color.decode("#eeeeee"));
        textLabel.setBounds(0, 120, 80, 20);
    }

}
