/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form;

import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.components.BillDetailGUI;
import com.fastfoodstore.gui.components.LeftMenu;
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

public class ComFirmForm2 {

    private Frame frame;
    private JLabel textlabel;

    private Button customButton1;
    private Button customButton2;
    private Font f = new Font("Segoe UI Semibold", Font.PLAIN, 12);
    
    private String cont;
    private int result;

    public ComFirmForm2(String cont) {
        this.cont = cont;
        this.result = 0;
    }

    public int show() {
        frame = new JFrame("Custom Button Dialog Example");

        textlabel = new JLabel("Bạn chắc chắn muốn " + cont + "?",JLabel.CENTER);
        textlabel.setPreferredSize(new Dimension(200, 30));
        textlabel.setBorder(new EmptyBorder(1, 1, 1, 1));
        textlabel.setFont(f); 
        

        customButton1 = new Button("Continue", 90, 30, ProjectUtil.getMyGreenColor());
        customButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton1.getR(),customButton1.getG(),customButton1.getB()));
                result = 1;
                frame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton1.getR(),customButton1.getG(),customButton1.getB(),200)); 

            }
            
        });

       customButton2 = new Button("Cancel", 90, 30, ProjectUtil.getMyRedColor());
       customButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton2.getR(),customButton2.getG(),customButton2.getB()));
                frame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton2.getR(),customButton2.getG(),customButton2.getB(),200)); 

            }
            
        });
        

        Object[] options = {customButton1, customButton2};

        int choice = JOptionPane.showOptionDialog(frame, textlabel, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        
        return result;

    }

}

