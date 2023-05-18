/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.staffForm;

import com.fastfoodstore.dto.StaffDTO;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author k
 */
public class ListItem extends JPanel {

    private final StaffDTO _staff;

    private Object[] _infos;

    private ArrayList<JLabel> _Labels = new ArrayList<JLabel>();

    public ListItem(StaffDTO staff) {
        _staff = new StaffDTO(staff);

        _infos = _staff.data();

        for (int i = 0; i < _infos.length; i++) {
            _Labels.add(new JLabel(_infos[i].toString()));

        }
        InitComponent();

    }

    private void InitComponent() {
        GridBagLayout layout = new GridBagLayout();

        setLayout(layout);

        GridBagConstraints gbc = new GridBagConstraints();

        setBorder(new EmptyBorder(4, 4, 4, 4));
        for (int i = 0; i < _infos.length; i++) {
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            if (i == _infos.length-1) {
                System.out.println("com.fastfoodstore.gui.form.staffForm.ListItem.InitComponent()");
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridwidth = 2;
            }
            gbc.anchor = GridBagConstraints.CENTER;
//            gbc.fill=GridBagConstraints.HORIZONTAL;
            _Labels.get(i).setBorder(new LineBorder(Color.yellow, 1));
            add(_Labels.get(i), gbc);

        }

    }

    public JPanel getPanel() {
        return this;
    }
}
