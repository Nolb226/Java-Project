/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.item;

import com.fastfoodstore.bus.IngredientBUS;
import com.fastfoodstore.dto.IngredientDTO;
import com.fastfoodstore.gui.ProjectUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class IngredientItem extends JPanel {

    private IngredientDTO dataIngredient = null;

    private JLabel code;
    private JLabel name;
    private JLabel cost;
    private JLabel Sl;

    private boolean selected = false;

    private Font f = new Font("Segoe UI Semibold", Font.PLAIN, 12);

    private int amount = 1;

    public IngredientItem(Object data) {
        this.dataIngredient = IngredientBUS.getIngredientByCode(((IngredientDTO) data).getIngredientCode());
        amount = ((IngredientDTO) data).getAmount();
        initComponent();
    }

    private void initComponent() {
        setPreferredSize(new Dimension(380, 40));
        setBackground(new Color(0, 0, 0, 0));
        setLayout(new GridLayout(1, 4, 0, 10));
        setPriceLabel();
        code = new JLabel(dataIngredient.getIngredientCode());
        name = new JLabel(dataIngredient.getIngredientName());
        Sl = new JLabel(String.valueOf(amount));
        
        add(code);
        add(name);
        add(Sl);
        add(cost);

    }

    public void setPriceLabel() {
        this.cost = new JLabel("" + amount * dataIngredient.getCost() + " ", JLabel.LEFT);
        cost.setFont(f);
    }

    public void addAmount() {
        this.amount++;
    }

    public void subAmount() {
        this.amount--;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            this.setBackground(ProjectUtil.getMyGreenColor());
        } else {
            this.setBackground(Color.decode("#d9d9d9"));
        }
    }
}
