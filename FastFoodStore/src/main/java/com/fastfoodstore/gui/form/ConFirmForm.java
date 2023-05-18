/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form;

import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.item.Button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class ConFirmForm {

    private Frame frame;
    private JPasswordField inputField;
    private JPanel inputPanel;

    private Button customButton1;
    private Button customButton2;
    
    private String result;

    public ConFirmForm() {
        this.result = null;
    }

    public String show() {
        frame = new JFrame("Custom Button Dialog Example");

        inputField = new JPasswordField();
        inputField.setPreferredSize(new Dimension(200, 30));
        inputField.setBorder(new EmptyBorder(1, 1, 1, 1));
        inputPanel = new JPanel();
        inputPanel.add(inputField);

        customButton1 = new Button("Continue", 90, 30, ProjectUtil.getMyGreenColor());
        customButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton1.getR(),customButton1.getG(),customButton1.getB()));
                result = String.copyValueOf(inputField.getPassword());
                frame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton1.getR(),customButton1.getG(),customButton1.getB(),150)); 

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
                e.getComponent().setBackground(new Color(customButton2.getR(),customButton2.getG(),customButton2.getB(),150)); 

            }
            
        });
        

        Object[] options = {customButton1, customButton2};

        int choice = JOptionPane.showOptionDialog(frame, inputPanel, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        
        return result;

    }

}

