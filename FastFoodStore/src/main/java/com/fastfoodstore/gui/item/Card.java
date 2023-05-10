
package com.fastfoodstore.gui.item;

import com.fastfoodstore.gui.ProjectUtil;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Card extends JPanel {
    
    private BufferedImage img;

    public Card(String path) {
        img = ProjectUtil.addImg(path);
        initcomponent();
        setOpaque(false); 
    }
    
    private void initcomponent() {
    }
   
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
        g2d.setClip(roundedRect);
        g2d.setColor(Color.WHITE); 
        g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        g2d.draw(roundedRect);
    }

    
}
