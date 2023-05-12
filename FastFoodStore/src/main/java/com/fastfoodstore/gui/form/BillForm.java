/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form;

import com.fastfoodstore.bus.BillBUS;
import com.fastfoodstore.bus.BillDetailBUS;
import com.fastfoodstore.dto.BillsDTO;
import com.fastfoodstore.gui.components.ViewBillList;
import com.fastfoodstore.gui.item.Button;
import com.fastfoodstore.gui.item.ScrollBar;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ADMIN
 */
public class BillForm extends JPanel {

    private JDateChooser dateInput;

    private Button filterButton;
    
    private JScrollPane scrollPane;
    private ViewBillList viewBillList;
    
    private Button viewButton;
    
    private ArrayList<BillsDTO> billsDTOs;
    private ArrayList<Object> billDetails;
    
    private JPanel billDetailPanel;
    
    private JLabel code1;
    private JLabel code2;

    public BillForm() {
        initComponent();
        setOpaque(false);
    }

    private void initComponent() {
        setLayout(null);
        setDateInput();
        setFilterButton();
        setViewBillList();
        setViewButton();
        setBillDetailPanel();
        add(dateInput);
        add(filterButton);
        add(scrollPane);
        add(viewButton);
        add(billDetailPanel);
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

    public void setDateInput() {
        this.dateInput = new JDateChooser();
        dateInput.setBounds(40, 40, 310, 30);
        dateInput.setDateFormatString("yyyy-MM-dd");
    }

    public void setFilterButton() {
        this.filterButton = new Button("Lọc", 80, 30, Color.decode("#444444")); 
        filterButton.setBounds(360, 40, 80, 30);
        filterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String pattern = "yyyy-MM-dd";
                DateFormat formatter = new SimpleDateFormat(pattern);
                if(billsDTOs != null) {
                    billsDTOs.clear();
                }
                setBillsDTOs(BillBUS.selectBillByDate(formatter.format(dateInput.getDate()))); 
                setData();
            }

        });
    }

    public void setScrollPane() {
       this.scrollPane = new JScrollPane(viewBillList);
       scrollPane.setBorder(null);
       scrollPane.setVerticalScrollBar(new ScrollBar(Color.decode("#333333"), Color.decode("#333333")));
       scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
       scrollPane.setBorder(new LineBorder(Color.decode("#444444"), 2)); 
       scrollPane.setBounds(40, 80, 400, 520);
    }

    public void setViewButton() {
        this.viewButton = new Button("Xem", 400, 40, Color.decode("#444444")); 
        viewButton.setBounds(40, 610, 400, 30);
        viewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setBillDetails(BillDetailBUS.
                        selectBillDetailByCode(billsDTOs.get(viewBillList.getMySelectedIndex()).getBillCode())); 
                setData2();
            }
            
        });
    }

    public void setBillDetailPanel() {
        this.billDetailPanel = new JPanel();
        billDetailPanel.setLayout(new GridLayout(0,2));
        billDetailPanel.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        billDetailPanel.setBounds(500, 40, 400, 600);
    }

    public void setViewBillList() {
        this.viewBillList = new ViewBillList<>();
        viewBillList.setBackground(Color.decode("#eeeeee")); 
        setScrollPane();
    }

    public void setBillsDTOs(ArrayList<BillsDTO> billsDTOs) {
        this.billsDTOs = billsDTOs;
    }

    public void setBillDetails(ArrayList<Object> billDetails) {
        this.billDetails = billDetails;
    }
    
    public void setData() {
        viewBillList.removeData();
        if(billsDTOs != null) {
            for(BillsDTO a : billsDTOs) {
                viewBillList.addItem(a); 
            }
        }
        repaint();
    }
    
    public void setData2() {
        this.code1 = new JLabel("Mã hóa đơn: ");
        this.code2 = new JLabel(billsDTOs.get(viewBillList.getMySelectedIndex()).getBillCode());
        billDetailPanel.add(code1);
        billDetailPanel.add(code2);
    }
    

}
