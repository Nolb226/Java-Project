/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form;

import com.fastfoodstore.bus.Staff_BUS;
import com.fastfoodstore.dto.StaffDTO;
import com.fastfoodstore.gui.form.staffForm.InfoBox;
import com.fastfoodstore.gui.form.staffForm.ListItem;
import com.fastfoodstore.gui.form.staffForm.UIButton;
import com.mysql.cj.util.SearchMode;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.DefaultListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ADMIN
 */
public class StaffForm extends JPanel {

    private JTable _StaffTable;
    private JPanel _InfoViewBox;
    private JPanel _TableBox;
    private JPanel _MenuBox;
    private JPanel _EditBox;
    private JLayeredPane _SearchBox;
    private JPanel _ResultBox;
    private StaffDTO staff;

    private Field[] _fields = StaffDTO.getField();

    private ArrayList<InfoBox> _InfoList;

    private Staff_BUS _Controller = new Staff_BUS();

    private List<StaffDTO> dataList = _Controller.getAll();

    public StaffForm() {
        initComponent();
        setOpaque(false);
    }

    private void initComponent() {

        _InfoList = new ArrayList<InfoBox>();
        _EditBox = new JPanel();
        _SearchBox = new JLayeredPane();
        _ResultBox = new JPanel();

        for (int i = 0; i < _fields.length; i++) {
            _InfoList.add(new InfoBox());
        }

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        _TableBox = new JPanel(new BorderLayout());
        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.fill = GridBagConstraints.FIRST_LINE_START;
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        gbc.gridwidth = 3;

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
//        _TableBox.setBackground(Color.red);
        add(_TableBox, gbc);
        _TableBox.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new LineBorder(Color.WHITE, 1, true)));
        _TableBox.setBackground(Color.WHITE);
        createStaffTable(_Controller.getAll(), gbc, _TableBox);

        JPanel leftJPanel = new JPanel();
        BoxLayout leftLayout = new BoxLayout(leftJPanel, BoxLayout.Y_AXIS);
        leftJPanel.setLayout(leftLayout);

        _MenuBox = new JPanel();

        _InfoViewBox = new JPanel();
//        _InfoViewBox.setBackground(Color.YELLOW);

        gbc.gridx = 0;
        gbc.gridy = 0;
//        gbc.gridwidth = 3;

        add(leftJPanel, gbc);

        leftJPanel.add(_SearchBox);
        leftJPanel.add(_InfoViewBox);
        leftJPanel.add(_MenuBox);
        leftJPanel.add(_EditBox);

        //Search
        JLabel searchJLabel = new JLabel("Search:");
        JTextField search = new JTextField();
//        search.addKeyListener(new KeyAdapter() {
//            public void keyPressed() {
//                Arrays.stream(dataList.toArray()).filter(item->{
//                    System.out.println(item);
//                return true;
//                });
//            }
//        });
        DefaultListModel<ListItem> staffList = new DefaultListModel<ListItem>();

        search.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();

            }

            private void filter() {
                System.out.println(search.getText());
                Stream<StaffDTO> newData = dataList.stream();

                Stream<StaffDTO> test = newData.filter((t) -> {
                    return t.getID().contains(search.getText());
                });
                staffList.clear();
                test.forEach((t) -> {
                    staffList.addElement(new ListItem(t));
                });
            }
        });

        UIButton searchButton = new UIButton("Search", UIButton.DARK, UIButton.SMALL, true);

        JList<ListItem> list = new JList<ListItem>(staffList);
        JScrollPane resultList = new JScrollPane(list);

        DefaultListCellRenderer cellRendererList = new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                JPanel panel = (JPanel) value;
//            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                return panel;
            }
        ;
        };
        
        list.setCellRenderer(cellRendererList);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    System.out.println(
                            list.getSelectedValue()
                    );
                }
            }
        });

        resultList.setBorder(new EmptyBorder(2, 2, 2, 2));

        _SearchBox.setOpaque(true);
        _SearchBox.setBackground(Color.BLUE);
        _SearchBox.setPreferredSize(new Dimension(670, 200));
        search.setColumns(18);
        search.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.decode("#1d1d1f")), new EmptyBorder(2, 2, 2, 2)));
        searchJLabel.setBounds(50, 0, 50, 50);

        _SearchBox.add(searchJLabel);
        _SearchBox.add(resultList);
        resultList.setBounds(120, 40, 26 * 15, 80);
        _SearchBox.add(search);
        search.setBounds(120, 12, 26 * 15, 30);
        _SearchBox.add(searchButton);
        _SearchBox.setBorder(new EmptyBorder(2, 2, 2, 2));
        _SearchBox.setBackground(Color.WHITE);

        UIButton EditButton = new UIButton("<html><p><u>E</u>dit</p></html>", UIButton.PRIMARY, UIButton.NORMAL, true);
        EditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < _InfoList.size(); i++) {
                    _InfoList.get(i).isEditable(true);
                }
                _EditBox.setVisible(true);
                _MenuBox.setVisible(false);
            }
        });

        UIButton SaveButton = new UIButton("<html><p><u>S</u>ave</p></html>", UIButton.PRIMARY);

        UIButton CancelButton = new UIButton("<html><p><u>C</u>ancel</p></html>", UIButton.SECONDARY);

        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < _InfoList.size(); i++) {
                    _InfoList.get(i).isEditable(false);
                }
                _EditBox.setVisible(false);
                _MenuBox.setVisible(true);
            }
        });

        _EditBox.setPreferredSize(new Dimension(leftJPanel.getPreferredSize().width, 50));
        _EditBox.add(SaveButton);
        _EditBox.add(CancelButton);
        _EditBox.setVisible(false);

        _MenuBox.setPreferredSize(new Dimension(leftJPanel.getPreferredSize().width, 50));

        _MenuBox.add(EditButton);
        _MenuBox.setVisible(false);
        createStaffInfo(_InfoViewBox);

//               this.pack();
    }

    public void createStaffTable(ArrayList<StaffDTO> args, GridBagConstraints gbc, JPanel TableBox) {
        _StaffTable = new JTable() {

            public Point getToolTipLocation(MouseEvent event) {
                int row = rowAtPoint(event.getPoint());
                Rectangle cellRect = getCellRect(row, 0, false);
                return new Point(cellRect.x - 260, cellRect.y + 35 / 2);

            }

            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        JPopupMenu ContextMenu = new JPopupMenu();
        ContextMenu.add(new JMenuItem("<html><p><u>C</u>opy</p></html>"));
        ContextMenu.setBorderPainted(false);
        ContextMenu.setBackground(Color.decode("#f5f5f7"));
        ContextMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
//        _StaffTable.resiz
//         Header
        _StaffTable.getTableHeader()
                .setReorderingAllowed(false);
        _StaffTable.getTableHeader().setBackground(Color.WHITE);
//        _StaffTable.getTableHeader().se
        _StaffTable.getTableHeader().setFont(new Font("Consolas", Font.BOLD, 14));
        _StaffTable.setShowGrid(false);

        _StaffTable.setFont(new Font("San serif", Font.PLAIN, 12));
        _StaffTable.setSelectionBackground(Color.decode("#a5d8ff"));
        _StaffTable.setComponentPopupMenu(ContextMenu);
//        _StaffTable.setSelectionForeground(Color.lightGray);
        _StaffTable.setRowHeight(35);

        _StaffTable.setFillsViewportHeight(
                true);

        _StaffTable.setShowVerticalLines(false);

        ToolTipManager.sharedInstance().setInitialDelay(100);
        ToolTipManager.sharedInstance().setDismissDelay(2000);
        ToolTipManager.sharedInstance().registerComponent(_StaffTable);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            public Component getTableCellRenderComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//               
                if (isSelected) {
                    c.setBackground(Color.PINK);

                    c.setForeground(table.getSelectionForeground());

                } else {

                    c.setBackground(table.getBackground());

                    c.setForeground(table.getForeground());

                }

                if (isSelected) {
                    setBackground(table.getSelectionBackground());
                } else {
                    setBackground(table.getBackground());
                }
                if (hasFocus) {
                    Border selectedBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.PINK);//Blue-Border
                    setBorder(selectedBorder);
                } else {
                    setBorder(null);
                }
                return c;
            }

        };

        _StaffTable.setDefaultRenderer(Object.class, cellRenderer);
//        _StaffTable.setSelectionBackground(Color.decode("#FF7337"));
        _StaffTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = _StaffTable.getSelectedRow();

                if (row >= 0) {
                    Object[] data = new Object[_StaffTable.getColumnCount()];
                    for (int i = 0; i < _StaffTable.getColumnCount(); i++) {
                        data[i] = _StaffTable.getValueAt(row, i);

                    }
                    updateCard(_InfoViewBox, data);

                }
            }

        });

        _StaffTable.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                int row = _StaffTable.rowAtPoint(e.getPoint());

                Object[] data = new Object[_StaffTable.getColumnCount()];
                if (row >= 0) {
                    for (int i = 0; i < _StaffTable.getColumnCount(); i++) {
                        data[i] = _StaffTable.getValueAt(row, i);

                    }
                }
//                System.out.println(row);
                UIManager.put("ToolTip.background", Color.white);
//                UIManager.put("ToolTip.border", new LineBorder(Color.BLACK,1,true));

                String tooltipText = "<html>"
                        + "<body>"
                        + "<div style=\"display:flex;flex-direction:column;border-radius:10px;padding:10px;\">\n"
                        + "    <div style=\"position:relative;background-color:#1d1d1f;width:100%;height:200px;border-radius:10px;\">\n"
                        + "        <p style=\"color:#fff;line-height: 200px;text-align:center;\">Image not found!</p>\n"
                        + "    </div>\n"
                        + "    <div style=\"display:flex;flex-direction:column;gap:1.6rem;justify-content:center\">\n"
                        + "        <h1 style=\"text-align:center;font-size:16pt\">" + data[0] + "</h1>\n"
                        + "        <h2 style=\"font-family:San-serif;font-size:14pt\">Tên:" + data[1] + "</h2>\n"
                        + "        <h3 style=\"font-family:San-serif;font-size:12pt\">" + data[2] + "</h3>\n"
                        + "        <h3 style=\"font-family:San-serif;font-size:12pt\">" + data[3] + "</h3>\n"
                        + "    </div>\n"
                        + "</div>"
                        + "</body>"
                        + "</html>";
                _StaffTable.setToolTipText(row >= 0 ? tooltipText : null);
            }

        });

        // Data 
        DefaultTableModel tableModel = (DefaultTableModel) _StaffTable.getModel();
        Field[] column = StaffDTO.getField();
        for (Field field : column) {
            tableModel.addColumn(field.getName().split("_")[1]);
        }

        for (int i = 0;
                i < args.size();
                i++) {
            String id = args.get(i).getID();
//            Object[] test =
            String name = args.get(i).getName();
            Object[] row = args.get(i).data();
            tableModel.addRow(row);
        }

        JScrollPane tableScrollPane = new JScrollPane(_StaffTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        TableBox.add(tableScrollPane, BorderLayout.CENTER);

        //Size
        tableScrollPane.setPreferredSize(
                new Dimension(300, tableScrollPane.getPreferredSize().height));
    }

    public void createStaffInfo(JPanel InfoViewBox) {
        InfoViewBox.setLayout(new GridBagLayout());
        InfoViewBox.setPreferredSize(new Dimension(300, _StaffTable.preferredSize().height));
        GridBagConstraints gbc = new GridBagConstraints();
        Field[] column = StaffDTO.getField();
        for (int i = 0; i < _StaffTable.getColumnCount(); i++) {
            _InfoList.get(i).setText(column[i].getName().split("_")[1], "");
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            gbc.insets = (new Insets(2, 2, 2, 2));
            if (i == _StaffTable.getColumnCount() - 1 && _StaffTable.getColumnCount() % 2 != 0) {
                gbc.gridwidth = 2;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.CENTER;

//                _InfoList.get(i).setPreferredSize(null);
            }
            InfoViewBox.add(_InfoList.get(i), gbc);
            InfoViewBox.repaint();
            InfoViewBox.revalidate();
        }
    }

    public void updateCard(JPanel InfoViewBox, Object[] rowData) {
        InfoViewBox.removeAll();
        Field[] column = StaffDTO.getField();

        InfoViewBox.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        for (int i = 0; i < _StaffTable.getColumnCount(); i++) {
            _InfoList.get(i).setText(column[i].getName().split("_")[1], rowData[i].toString());

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            gbc.insets = (new Insets(2, 2, 2, 2));
            if (i == _StaffTable.getColumnCount() - 1 && _StaffTable.getColumnCount() % 2 != 0) {
                gbc.gridwidth = 2;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.CENTER;
            }
            InfoViewBox.add(_InfoList.get(i), gbc);
        }

        InfoViewBox.repaint();
        InfoViewBox.revalidate();

        _MenuBox.setVisible(true);
        _EditBox.setVisible(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(0, 0, getWidth() - 20, getHeight());
        super.paintComponent(g);
    }

}
