/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form;

import com.fastfoodstore.bus.DutyBUS;
import com.fastfoodstore.bus.DutyHasFuncBUS;
import com.fastfoodstore.dto.DutyDTO;
import com.fastfoodstore.dto.FunctionsDTO;
import com.fastfoodstore.bus.FunctionsBUS;
import com.fastfoodstore.dto.DutyHasFuncDTO;
import com.fastfoodstore.gui.form.menuform.MenuTableHeader;
import com.fastfoodstore.gui.form.menuform.MenuTableItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ADMIN
 */
public class AdminForm extends JPanel {

    ArrayList<DutyDTO> dutys;
    ArrayList<FunctionsDTO> functions;
    ArrayList<DutyHasFuncDTO> dutyHasFuncs;
    ArrayList<String> dutyHas = new ArrayList<>();
    String dutyCode;

    public void setSelectedDuty(String duty) {
        this.dutyCode = duty;
    }

    public AdminForm() {
        initComponent();
        setOpaque(false);
    }

    public JTable DetailFunctionTable(String dutyCode) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Quyền");
        model.addColumn("");

        for (FunctionsDTO function : functions) {
            Boolean flag = false;

            for (DutyHasFuncDTO dhf : dutyHasFuncs) {
                if (dhf.getDutyCode().equals(dutyCode)) {
                    if (dhf.getFunctionCode().equals(function.getFunctionCode())) {
//                        JCheckBox checkBox = new JCheckBox("", true);
                        model.addRow(new Object[]{function.getFunctionName(), true});
                        dutyHas.add(function.getFunctionCode());
                        flag = true;
                    }
                }
            }

            if (!flag) {
//                JCheckBox checkBox = new JCheckBox("", false);
                model.addRow(new Object[]{function.getFunctionName(), false});
            }
        }

        JTable jt = new JTable(model) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 1:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean editCellAt(int row, int column) {
                return false;
            }
            
            
        };

        TableColumnModel columnModel = jt.getColumnModel();
        jt.setRowHeight(35);
        jt.setShowHorizontalLines(true);
        jt.setGridColor(Color.decode("#d9d9d9"));
        jt.setShowVerticalLines(false);

        jt.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                MenuTableHeader header = new MenuTableHeader(value + "");
                header.setHorizontalAlignment(JLabel.CENTER);
                return header;
            }
        });

        jt.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                MenuTableItem label;
                if (column == 0) {
                    label = new MenuTableItem(String.valueOf(value));
                    if (isSelected) {
                        label.getContentLabel().setForeground(new Color(13, 113, 182));
                    } else {
                        label.getContentLabel().setForeground(new Color(102, 102, 102));
                    }

                    return label;
                } else if (row != 0 && column > 0) {
                    com.setBackground(Color.YELLOW);
                }
                return new JLabel();
            }
        });
        
        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                boolean remove = false;
                if (jt.getSelectedColumns()[0] == 1) {
                    for (int i = 0; i < dutyHas.size(); i++) {
                        if (dutyHas.get(i).equals(functions.get(jt.getSelectedRow()).getFunctionCode())) {
                            dutyHas.remove(i);
                            System.out.println("remove" + functions.get(jt.getSelectedRow()).getFunctionCode());
                            remove = true;
                        }
                    }
                    if (!remove) {
                        dutyHas.add(functions.get(jt.getSelectedRow()).getFunctionCode());
                        System.out.println("add" + functions.get(jt.getSelectedRow()).getFunctionCode());

                    }
                }

            }

        });

        columnModel.getColumn(0).setPreferredWidth(800);
        columnModel.getColumn(1).setPreferredWidth(100);

        return jt;
    }

    public void Function() {
        // get duty array
        this.dutys = new ArrayList<>();
        dutys = DutyBUS.selectAllDuty();

        // get dutyHasFunction array
        this.dutyHasFuncs = new ArrayList<>();
        dutyHasFuncs = DutyHasFuncBUS.selectAllDutyDetail();

        // get function array
        this.functions = new ArrayList<>();
        functions = FunctionsBUS.getAllFunctionList();
        ArrayList<String> functionName = new ArrayList<>();

//        add(panel);
        add(comboBox());
    }

    public JPanel comboBox() {
        ArrayList<String> dutyName = new ArrayList<>();

        for (DutyDTO a : dutys) {
            dutyName.add(a.getDutyName());
        }

        JLabel label = new JLabel("Nhóm quyền: ");
        label.setSize(100, 30);

        DefaultComboBoxModel<String> modelComboBox = new DefaultComboBoxModel<>(dutyName.toArray(new String[0]));
        JComboBox<String> comboBox = new JComboBox<>(modelComboBox);
        comboBox.setSize(350, 30);

        JButton button = new JButton("Xem chi tiết");
        button.setSize(120, 30);
        button.setBackground(Color.white);
        button.setFocusable(false);

        JPanel findPanel = new JPanel(new GridLayout(1, 3));
        findPanel.setSize(650, 30);
        findPanel.add(label);
        findPanel.add(comboBox);
        findPanel.add(button);

        // Thêm ActionListener cho JButton
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy phần tử đang được chọn
                Object selectedItem = comboBox.getSelectedItem();
                if (selectedItem != null) {
                    for (DutyDTO a : dutys) {
                        if ((a.getDutyName().toString()).equals(selectedItem.toString())) {
                            JScrollPane sp = new JScrollPane(DetailFunctionTable(a.getDutyCode()));
                            sp.setBounds(35, 120, 900, 500);
                            setSelectedDuty(selectedItem.toString());
                            add(sp);
                        }
                    }
                    comboBox();
                }
            }
        });

        JPanel controlPanel = new JPanel(new GridLayout(1, 2));
        controlPanel.setSize(250, 30);
        controlPanel.add(addButton());
        controlPanel.add(editButton());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBounds(35, 80, 900, 30);
        panel.add(findPanel);
        panel.add(Box.createHorizontalGlue());
        panel.add(controlPanel);

        return panel;
    }

    public JButton editButton() {
        JButton editIcon = new JButton("Cập nhật");
        editIcon.setBackground(Color.white);
        editIcon.setFocusable(false);
        editIcon.setSize(125, 30);
        editIcon.addActionListener(e -> {
            edit();
        });

        return editIcon;
    }

    public JButton addButton() {
        JButton addIcon = new JButton("Thêm mới");
        addIcon.setBackground(Color.white);
        addIcon.setFocusable(false);
        addIcon.setSize(125, 30);
        addIcon.addActionListener(e -> {
            addNew();
        });
        return addIcon;
    }

    public void addNew() {
        String input = JOptionPane.showInputDialog(null, "Nhập tên nhóm quyền cần thêm:");

        if (input != null && !Objects.requireNonNull(input).trim().isEmpty()) {
            Boolean existence = true;

            for (DutyDTO duty : dutys) {
                if (duty.getDutyName().equals(input)) {
                    existence = false;
                    JOptionPane.showMessageDialog(null, "Lỗi: Nhóm quyền đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (existence) {
                String baseString = "DUTY";
                Random random = new Random();
                DutyDTO newDTO = new DutyDTO();

                while (existence) {
                    int randomNumber1 = random.nextInt(10);
                    int randomNumber2 = random.nextInt(10);

                    String dutyCode = baseString + randomNumber1 + randomNumber2;

                    for (DutyDTO duty : dutys) {
                        if (!duty.getDutyCode().contains(dutyCode)) {
                            newDTO = new DutyDTO(dutyCode, input);
                            existence = false;
                        }
                    }
                }

                int k = DutyBUS.insertDuty(newDTO);

                if (k == 0) {
                    JOptionPane.showMessageDialog(null, "Lỗi: Không thể tạo nhóm quyền mới.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm nhóm quyền thành công.", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                }

                removeAll();
                Function();
                repaint();
                validate();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Lỗi: Tên nhóm quyền không được rỗng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void edit() {
        String code = null;
        for(DutyDTO a : dutys) {
            if(a.getDutyName().equals(dutyCode)) {
                code = a.getDutyCode();
                break;
            }
        }
        int d = DutyHasFuncBUS.remove(code);
        if(d == -1) {
            JOptionPane.showMessageDialog(null, "Lỗi: Cập nhật không thành công.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            int d1 = DutyHasFuncBUS.insert(code,dutyHas);
            if(d1 == 0) {
                JOptionPane.showMessageDialog(null, "Lỗi: Cập nhật không thành công.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật thành công.", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                removeAll();
                Function();
                repaint();
                validate();
            }
            
        }
        
    }

    private void initComponent() {
        setBackground(Color.WHITE);
        setLayout(null);
        Function();
    }

}
