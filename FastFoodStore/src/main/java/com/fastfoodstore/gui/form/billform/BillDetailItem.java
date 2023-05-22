/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.billform;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 *
 * @author ADMIN
 */
public class BillDetailItem extends JPanel {

    private JPanel contentPanel;

    private JTextArea leftLabel;
    private JLabel rightLabel;

    private String a;
    private String b;

    private int type;
    private Font f1 = new Font("Segoe UI Semibold", Font.PLAIN, 16);
    private Font f2 = new Font("Segoe UI Semibold", Font.PLAIN, 12);

    public BillDetailItem() {
    }

    public BillDetailItem(String a) {
        this.a = a;
        this.type = 1;
        initComponent();
        initComponent1();
    }

    public BillDetailItem(String a, String b) {
        this.a = a;
        this.b = b;
        this.type = 2;
        initComponent();
        initComponent2();
    }

    public BillDetailItem(String a, String b, int type) {
        this.a = a;
        this.b = b;
        this.type = type;
        initComponent();
        initComponent3();
    }

    private void initComponent() {
        setBackground(Color.decode("#ffffff"));
        setBorder(BorderFactory.createEmptyBorder(
                0, 10, 18, 10));
        setContentPanel();
        contentPanel.setBackground(Color.decode("#ffffff"));

    }

    private void initComponent1() {
        contentPanel.setLayout(new GridLayout(0, 1));
        setPreferredSize(new Dimension(0, 30));
        setLeftLabel();
        contentPanel.add(leftLabel);
        add(contentPanel);
    }

    private void initComponent2() {
        contentPanel.setLayout(new GridLayout(0, 2));
        if (a.length() >= 35) {
            setPreferredSize(new Dimension(0, 40));
            contentPanel.setPreferredSize(new Dimension(380, 40));
        } else {
            setPreferredSize(new Dimension(0, 30));
            contentPanel.setPreferredSize(new Dimension(380, 30));
        }
        setLeftLabel();
        setRightLabel();
        contentPanel.add(leftLabel);
        contentPanel.add(rightLabel);
        add(contentPanel);
    }

    private void initComponent3() {
        contentPanel.setLayout(new GridLayout(0, 2));
        setPreferredSize(new Dimension(0, 40));
        contentPanel.setPreferredSize(new Dimension(380, 40));
        setLeftLabel();
        setRightLabel();
        contentPanel.add(leftLabel);
        contentPanel.add(rightLabel);
        add(contentPanel);
    }

    public void setContentPanel() {
        this.contentPanel = new JPanel();
    }

    public void setLeftLabel() {
        if (type == 1) {
            this.leftLabel = new JTextArea();
            leftLabel.setText(a);
            leftLabel.setFont(f1);
        } else {
            this.leftLabel = new JTextArea(35, 2);
            leftLabel.setText(a);
            if (type == 3) {
                leftLabel.setFont(f1);
            } else {
                leftLabel.setFont(f2);
            }
            leftLabel.setWrapStyleWord(true);
            leftLabel.setLineWrap(true);
        }

    }

    public void setRightLabel() {
        this.rightLabel = new JLabel(b);
        rightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        rightLabel.setVerticalAlignment(SwingConstants.TOP);
        if(type == 3) {
            rightLabel.setFont(f1);
        } else {
            rightLabel.setFont(f2);

        }
    }

}
