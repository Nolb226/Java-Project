/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form;

import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.item.Button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class NoticeForm {

    private Frame frame;
    private JLabel textlabel;

    private Button customButton1;
    private Font f = new Font("Segoe UI Semibold", Font.PLAIN, 12);
    
    private String cont;
    private int type;

    public NoticeForm(String cont, int type) {
        this.cont = cont;
        this.type = type;
        initComponent();
    }

    private void initComponent() {
        frame = new JFrame("Custom Button Dialog Example");

        textlabel = new JLabel(cont + "!",JLabel.CENTER);
        textlabel.setPreferredSize(new Dimension(200, 30));
        textlabel.setBorder(new EmptyBorder(1, 1, 1, 1));
        textlabel.setFont(f); 
        
        if(type == 1) {
            customButton1 = new Button("Continue", 90, 30, ProjectUtil.getMyGreenColor());
        } else {
            customButton1 = new Button("Continue", 90, 30, ProjectUtil.getMyRedColor());
        }
        customButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton1.getR(),customButton1.getG(),customButton1.getB()));
                frame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton1.getR(),customButton1.getG(),customButton1.getB(),200)); 

            }
            
        });
        Object[] options = {customButton1};

        int choice = JOptionPane.showOptionDialog(frame, textlabel, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

    }

}

