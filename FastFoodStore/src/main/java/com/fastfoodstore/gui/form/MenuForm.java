
package com.fastfoodstore.gui.form;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class MenuForm extends JPanel {

    public MenuForm() {
        initComponent();
        setOpaque(false);
    }
    
    private void initComponent() {
        setBackground(Color.BLUE);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(0, 0, getWidth()-20, getHeight());
        super.paintComponent(g); 
    }
}
