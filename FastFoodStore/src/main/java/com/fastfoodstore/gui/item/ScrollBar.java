package com.fastfoodstore.gui.item;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBar extends JScrollBar {

    public ScrollBar(Color c, Color bg) {
        setUI(new ModernScrollBarUI(c));
        setPreferredSize(new Dimension(2, 5));
        setBackground(bg);
        setUnitIncrement(20);
    }
}