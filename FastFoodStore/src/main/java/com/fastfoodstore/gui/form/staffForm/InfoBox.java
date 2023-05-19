/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.staffForm;

import com.fastfoodstore.dto.DutyDTO;
import com.fastfoodstore.dto.StaffDTO;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
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
    private JList<DutyDTO> _dutyList;
    private int _type = 1;

    public static final int INPUT = 1;
    public static final int SELECT = 2;

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
    public void setType(int type, ArrayList<DutyDTO> duties, StaffDTO staff) {

        if (type == 2) {
            DefaultListModel<DutyDTO> model = new DefaultListModel<>();

            DefaultListCellRenderer cellRenderer = new DefaultListCellRenderer() {
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    DutyDTO view = (DutyDTO) value;
                    Component c = super.getListCellRendererComponent(list, view.getDutyName(), index, isSelected, cellHasFocus);

//                JLabel duty = new JLabel(view.getDutyName());
                    return c;
                }
            };
            setPreferredSize(null);
            model.addAll(duties);
            int index = -1; // Initialize the index with a default value

            for (int i = 0; i < duties.size(); i++) {
                DutyDTO duty = duties.get(i);
                if (duty.getDutyName().equals(staff.getDutyCode())) {
                    index = i; // Assign the index when a match is found
                    break; // Exit the loop once a match is found (optional)
                }
            }
//            System.out.println(index);

            _dutyList = new JList<DutyDTO>(model);
            _dutyList.setCellRenderer(cellRenderer);
            _dutyList.setSelectedIndex(index);
            add(_dutyList);

            remove(_input);
        }

        if (type == 1) {
            add(_input);
            remove(_dutyList);
        }

        _type = type;

    }

    public void setType(int type, ArrayList<DutyDTO> duties) {

        if (type == 2) {
            DefaultListModel<DutyDTO> model = new DefaultListModel<>();

            DefaultListCellRenderer cellRenderer = new DefaultListCellRenderer() {
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    DutyDTO view = (DutyDTO) value;
                    Component c = super.getListCellRendererComponent(list, view.getDutyName(), index, isSelected, cellHasFocus);

//                JLabel duty = new JLabel(view.getDutyName());
                    return c;
                }
            };
            setPreferredSize(null);
            model.addAll(duties);
            int index = -1; // Initialize the index with a default value

//            System.out.println(index);
            _dutyList = new JList<DutyDTO>(model);
            _dutyList.setCellRenderer(cellRenderer);
            _dutyList.setSelectedIndex(index);
            add(_dutyList);

            remove(_input);
        }

        if (type == 1) {
            add(_input);
            remove(_dutyList);
        }

        _type = type;

    }

    public String getData() {
        switch (_type) {
            case 1 -> {

                return _input.getText();
            }

            case 2 -> {
                if (_dutyList.getSelectedIndex() == -1) {
                    return "";
                }
                return _dutyList.getSelectedValue().getDutyCode();
            }

        }
        return "";
    }

}
