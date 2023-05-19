/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.staffForm;

import com.fastfoodstore.dto.StaffDTO;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

    private String _searchString;
    
    
    
    private Object[] _infos;

    private ArrayList<JLabel> _Labels = new ArrayList<JLabel>();

    public ListItem(StaffDTO staff, String searchString) {
        _searchString = searchString;
        _staff = new StaffDTO(staff);

        _infos = _staff.data();

        for (int i = 0; i < 2; i++) {
            _Labels.add(new JLabel(_infos[i].toString()));

        }
        InitComponent();

    }

    private void InitComponent() {
        GridBagLayout layout = new GridBagLayout();

        setLayout(layout);

        GridBagConstraints gbc = new GridBagConstraints();

        setBorder(new EmptyBorder(4, 4, 4, 4));
        for (int i = 0; i < 2; i++) {
            gbc.gridx=1-i;
            gbc.gridy=0;
            JLabel label = _Labels.get(i);
            String s = "<html><body>";
            char[] charArr = label.getText().toCharArray();
            for (int j = 0; j < charArr.length; j++) {
                if(j==0 && i ==0) {
                    s+=" ( ";
                }
                char c = charArr[j];
                if(_searchString.contains(Character.toString(c)) && (i==0 || i == 1)){
                    s+="<span style=\"font-size:14pt ;background-color:yellow; "+(i==0 ?"color:#ccc":"")+"\">"+Character.toString(c)+"</span>";
                } else {
                     s+="<span style=\"font-size:14pt ;"+(i==0 ?"color:#ccc":"")+"\">"+Character.toString(c)+"</span>";
                }
                
                if(j== charArr.length-1 && i ==0) {
                    s+=" )";
                }
                
            }
            s+="</body></html>";
            gbc.insets = new Insets(2, 2, 2, 2);
            System.out.println(s);
            label.setText(s);
            add(label, gbc);
            

        }

    }

    public JPanel getPanel() {
        return this;
    }
    
    public Object[] getData() {
        return _infos;
    }
    
    public StaffDTO getStaff() {
        return _staff;
    }
}
