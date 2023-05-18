/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.staffForm;

import com.fastfoodstore.dto.StaffDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import org.w3c.dom.Text;

/**
 *
 * @author k
 */
public class InfoBox extends JPanel {

    private String _textField;
    private String _textData;
    
    private int _type;

    private JTextField _input = new JTextField();
    private JLabel _textRow = new JLabel();

//    private boolean isEdit;

    public InfoBox() {

        InitComponent();
    }

    
    

    public InfoBox(String _textField, String _textData) {
        this._textField = _textField;
        this._textData = _textData;
    }
    
    

//    public void test ()
    private void InitComponent() {
//        GridBagLayout layout = new GridBagLayout();

        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        setPreferredSize(new Dimension(220, 50));
        setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.decode("#1d1d1f")), new EmptyBorder(10, 10, 10, 10)));
        setBackground(Color.WHITE);
        _textRow.setText(_textField);
        _textRow.setBackground(Color.WHITE);
        _textRow.setOpaque(true);
        _textRow.setHorizontalAlignment(JLabel.LEFT);
        _textRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        _input.setText(_textData);
        _input.setAlignmentX(Component.LEFT_ALIGNMENT);
        _input.setBackground(Color.WHITE);
        _input.setBorder(new EmptyBorder(2, 2, 2, 2));
        _input.setEditable(false);

        add(_textRow);
        add(_input);
    }

    public void isEditable(boolean is) {
        _input.setEditable(is);

        if (is) {
            _input.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.decode("#1d1d1f")), new EmptyBorder(2, 2, 2, 2)));

        } else {
            _input.setBorder(new EmptyBorder(2, 2, 2, 2));
        }

//        _input.setBackground(Color.WHITE);
    }

    public void setText(String textField, String textData) {
        _input.setText(textData);
        _textRow.setText(textField);

    }
//    
//    public void setType(int type,Object[]) {
//        _type=type;
//        
//    }

}
