/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form;

import com.fastfoodstore.bus.DutyBUS;
import com.fastfoodstore.bus.DutyHasFuncBUS;
import com.fastfoodstore.dto.AccountDTO;
import com.fastfoodstore.dto.StaffDTO;
import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.item.Button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ConFirmForm {

    private Frame frame;

    private JTextField idField;
    private JPasswordField inputField;
    private JPanel inputPanel;

    private Button customButton1;
    private Button customButton2;

    private String result;
    private StaffDTO resultStaff;

    public ConFirmForm() {
        this.result = "DUTY05";
        this.resultStaff = new StaffDTO();
    }

    public StaffDTO show() {
        frame = new JFrame("Custom Button Dialog Example");
        resultStaff = null;

        idField = new JTextField();
        idField.setPreferredSize(new Dimension(200, 30));
        idField.setBorder(new EmptyBorder(1, 1, 1, 1));

        inputField = new JPasswordField();
        inputField.setPreferredSize(new Dimension(200, 30));
        inputField.setBorder(new EmptyBorder(1, 1, 1, 1));

        JLabel label1 = new JLabel("Mã nhân viên");
        label1.setPreferredSize(new Dimension(200, 30));
        JLabel label2 = new JLabel("Mật khẩu");
        label2.setPreferredSize(new Dimension(200, 30));

        inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(210, 150));
        inputPanel.add(label1);
        inputPanel.add(idField);
        inputPanel.add(label2);
        inputPanel.add(inputField);

        customButton1 = new Button("Continue", 90, 30, ProjectUtil.getMyGreenColor());
        customButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton1.getR(), customButton1.getG(), customButton1.getB()));
                if ("".equals(idField.getText()) || "".equals(String.valueOf(inputField.getPassword()))) {
                    JOptionPane.showMessageDialog(null, "Thông tin không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    ArrayList<AccountDTO> accList = DutyHasFuncBUS.getAccount();
                    boolean find = false;
                    for (AccountDTO a : accList) {
                        if (idField.getText().equals(a.getStaffCode())) {
                            if (String.copyValueOf(inputField.getPassword()).equals(a.getPass())) {
                                resultStaff = DutyBUS.getStaffByCode(idField.getText());
                                find = true;
                                break;
                            }
                        }
                    }
                    if (find) {
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        idField.setText("");
                        idField.requestFocus();
                        inputField.setText("");
                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton1.getR(), customButton1.getG(), customButton1.getB(), 150));

            }

        });

        customButton2 = new Button("Cancel", 90, 30, ProjectUtil.getMyRedColor());
        customButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton2.getR(), customButton2.getG(), customButton2.getB()));
                frame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton2.getR(), customButton2.getG(), customButton2.getB(), 150));

            }

        });

        Object[] options = {customButton1, customButton2};

        int choice = JOptionPane.showOptionDialog(frame, inputPanel, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        return resultStaff;

    }

    public String show1() {
        frame = new JFrame("Custom Button Dialog Example");

        idField = new JTextField();
        idField.setPreferredSize(new Dimension(200, 30));
        idField.setBorder(new EmptyBorder(1, 1, 1, 1));

        inputField = new JPasswordField();
        inputField.setPreferredSize(new Dimension(200, 30));
        inputField.setBorder(new EmptyBorder(1, 1, 1, 1));

        JLabel label1 = new JLabel("Mã nhân viên");
        label1.setPreferredSize(new Dimension(200, 30));
        JLabel label2 = new JLabel("Mật khẩu");
        label2.setPreferredSize(new Dimension(200, 30));

        inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(210, 150));
        inputPanel.add(label1);
        inputPanel.add(idField);
        inputPanel.add(label2);
        inputPanel.add(inputField);

        customButton1 = new Button("Continue", 90, 30, ProjectUtil.getMyGreenColor());
        customButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton1.getR(), customButton1.getG(), customButton1.getB()));
                ArrayList<AccountDTO> accList = DutyHasFuncBUS.getAccount();
                if (!"".equals(idField.getText()) && !"".equals(String.copyValueOf(inputField.getPassword()))) {
                    result = idField.getText() + "-" + String.copyValueOf(inputField.getPassword());
                }
                frame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton1.getR(), customButton1.getG(), customButton1.getB(), 150));

            }

        });

        customButton2 = new Button("Cancel", 90, 30, ProjectUtil.getMyRedColor());
        customButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton2.getR(), customButton2.getG(), customButton2.getB()));
                frame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton2.getR(), customButton2.getG(), customButton2.getB(), 150));

            }

        });

        Object[] options = {customButton1, customButton2};

        int choice = JOptionPane.showOptionDialog(frame, inputPanel, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        return result;

    }

    public String show2() {
        frame = new JFrame("Custom Button Dialog Example");

        idField = new JTextField();
        idField.setPreferredSize(new Dimension(200, 30));
        idField.setBorder(new EmptyBorder(1, 1, 1, 1));

        JLabel label1 = new JLabel("Mã khuyến mãi");
        label1.setPreferredSize(new Dimension(200, 30));

        inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(210, 150));
        inputPanel.add(label1);
        inputPanel.add(idField);

        customButton1 = new Button("Continue", 90, 30, ProjectUtil.getMyGreenColor());
        customButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton1.getR(), customButton1.getG(), customButton1.getB()));
                result = idField.getText();
                frame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton1.getR(), customButton1.getG(), customButton1.getB(), 150));

            }

        });

        customButton2 = new Button("Cancel", 90, 30, ProjectUtil.getMyRedColor());
        customButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton2.getR(), customButton2.getG(), customButton2.getB()));
                frame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                e.getComponent().setBackground(new Color(customButton2.getR(), customButton2.getG(), customButton2.getB(), 150));

            }

        });

        Object[] options = {customButton1, customButton2};

        int choice = JOptionPane.showOptionDialog(frame, inputPanel, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        return result;

    }

}
