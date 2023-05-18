/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.orderform;

import com.fastfoodstore.dto.GroupDTO;
import com.fastfoodstore.gui.ProjectUtil;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class GroupItem extends JPanel{
    
    private BufferedImage img;
    
    private GroupDTO data;
    
    

    public GroupItem(GroupDTO data) {
        this.data = data;
        img = ProjectUtil.addImg(data.getGroupIcon());
        initComponent();
    }
    
    private void initComponent() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g.create();
        RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
        g2d.setClip(roundedRect);
        g2d.setColor(getBackground());  
        g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        g2d.draw(roundedRect);
    }

    public GroupDTO getData() {
        return data;
    }
    
}
