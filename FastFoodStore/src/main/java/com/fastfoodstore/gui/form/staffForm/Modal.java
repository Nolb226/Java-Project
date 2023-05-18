/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.staffForm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author k
 */
public class Modal {

    private Frame _frame;

    private JPanel _modal = new JPanel();
    private JPanel _header = new JPanel();
    private JPanel _body = new JPanel();
    private JPanel _footer = new JPanel();
    private JLabel headerJLabel = new JLabel();
    UIButton ConfirmButton;
    UIButton CancelButton;

    private String _header_text = "Modal";
    private String _body_text = "Modal";

    private int _type;
    private int _dialog_type;

    private String Confirm_text;
    private String Cancel_text;

    private int result;

    private String color = "#000000";

    private final String Success_Color = "#198754";
    private final String Danger_Color = "#dc3545";
    private final String Warning_Color = "#ffc107";
    private final String Light_Color = "#f8f9fa";
    private final String Dark_Color = "#212529";

    public static final int SUCCESS = 1;
    public static final int DANGER = 2;
    public static final int WARNING = 3;
    public static final int LIGHT = 4;
    public static final int DARK = 5;

    public static final int OPTIONS = 1;
    public static final int CONFIRM = 2;

    public Modal(int _type, int _dialog_type, String Confirm_text, String Cancel_text) {
        this._type = _type;
        this._dialog_type = _dialog_type;
        this.Confirm_text = Confirm_text;
        this.Cancel_text = Cancel_text;
        this.result = -1;
        InitComponent();

    }

    public Modal(int _type, int _dialog_type, String Confirm_text) {
        this._type = _type;
        this._dialog_type = _dialog_type;
        this.Confirm_text = Confirm_text;
        InitComponent();

    }

    public String getHeader_text() {
        return _header_text;
    }

    public void setHeader_text(String _header_text) {
        this._header_text = _header_text;
    }

    public String getBody_text() {
        return _body_text;
    }

    public void setBody_text(String _body_text) {
        headerJLabel.setText(_body_text);
        this._body_text = _body_text;
    }

    public int show() {
        result = -1;
        Object[] options = {ConfirmButton, CancelButton};
        int choise = JOptionPane.showOptionDialog(_frame, _modal, _body_text, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
        return result;
    }

    private void InitComponent() {
        InitType();
        _frame = new JFrame();
        _modal.setLayout(new BorderLayout());
        _modal.add(_body);

        //Header
        _body.setBackground(Color.decode(color));
        _body.setBorder(new LineBorder(Color.decode(color), 20, true));
        headerJLabel.setForeground(Color.WHITE);
        _body.add(headerJLabel);

//        _header.setPreferredSize(new Dimension(_frame.getPreferredSize().width,100));
        //Body
        //Footer
//        _footer.setLayout(new BoxLayout(_footer, BoxLayout.X_AXIS));
        ConfirmButton = new UIButton(Confirm_text, UIButton.SUCCESS, UIButton.SMALL);
        ConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.err.println("1");
                result = 1;
                _frame.dispose();

            }
        });
        CancelButton = new UIButton(Cancel_text, UIButton.SECONDARY, UIButton.SMALL);
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _frame.dispose();
            }
        });

//        switch (_dialog_type) {
//            case 1 -> {
//                _footer.add(CancelButton);
//                CancelButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
//                _footer.add(ConfirmButton);
//                ConfirmButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
//            }
//        }
    }

    private void InitType() {
        _body.setForeground(Color.WHITE);
        switch (_type) {
            case 1 -> {
                this.color = Success_Color;
                break;

            }
            case 2 -> {
                color = Danger_Color;
                break;
            }
            case 3 -> {
                color = Warning_Color;
                break;

            }
            case 4 -> {
                color = Light_Color;
                _body.setForeground(Color.BLACK);

                break;

            }
            case 5 -> {
                color = Dark_Color;
                break;

            }
        }
    }

}
