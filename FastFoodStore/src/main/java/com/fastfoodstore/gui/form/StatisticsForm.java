/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form;

import com.fastfoodstore.bus.BillBUS;
import com.fastfoodstore.bus.BillDetailBUS;
import com.fastfoodstore.bus.ComboBUS;
import com.fastfoodstore.bus.ProductsBUS;
import com.fastfoodstore.dto.ComboDTO;
import com.fastfoodstore.dto.ProductsDTO;
import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.form.menuform.MenuTable;
import com.fastfoodstore.gui.form.statisticform.StaTable;
import com.fastfoodstore.gui.item.Button;
import com.fastfoodstore.gui.item.ScrollBar;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class StatisticsForm extends JPanel {
    
    private Button openSales;
    private Button openRevenue;
    
    private JDateChooser dateStart;
    private JDateChooser dateEnd;
    
    private JScrollPane scrollTable;
    private StaTable productTable;
    
    private ArrayList<ProductsDTO> products = new ArrayList<>();
    private ArrayList<ComboDTO> combos = new ArrayList<>();
    
    private int type = 0;

    public StatisticsForm() {
        initComponent();
        setOpaque(false);
    }
    
    private void initComponent() {
        setLayout(null);
        
        JPanel wallPanel = new JPanel();
        wallPanel.setBounds(35, 65, 900, 3);
        wallPanel.setBackground(Color.decode("#aaaaaa"));
        add(wallPanel);
        
        openSales = new Button("Lượt bán",80,30,Color.decode("#333333"));
        openSales.setBounds(35,30, 80, 30);
        openSales.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setProductsTable();
            }
            
        });
        add(openSales);
        
        Button viewButton = new Button("Xem",80,30,Color.decode("#333333"));
        viewButton.setBounds(750, 75, 80, 30);
        viewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(type == 0) {
                    setDataProductsTable();
                } else {
                    setDataRevenueTable();
                }
                
            }
        });
        add(viewButton);
        
        openRevenue = new Button("Doanh thu", 80, 30, Color.decode("#333333"));
        openRevenue.setBounds(120, 30, 80, 30);
        openRevenue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setRevenueTable();
            }
            
        });
        add(openRevenue);
        
        JLabel start = new JLabel("Từ: ");
        start.setFont(ProjectUtil.getMyFont(14));
        start.setBounds(35, 75, 40, 30);
        add(start);
        this.dateStart = new JDateChooser();
        dateStart.setBounds(75, 75, 300, 30);
        dateStart.setDateFormatString("yyyy-MM-dd hh:mm:ss");
        add(dateStart);
        
        JLabel end = new JLabel("Đến: ");
        end.setFont(ProjectUtil.getMyFont(14));
        end.setBounds(390, 75, 40, 30);
        add(end);
        this.dateEnd = new JDateChooser();
        dateEnd.setBounds(440, 75, 300, 30);
        dateEnd.setDateFormatString("yyyy-MM-dd hh:mm:ss");
        add(dateEnd);
        
        setProductsTable();
        
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(0, 0, getWidth()-20, getHeight());
        super.paintComponent(g); 
    }
    
    public void setScrollTable() {
        scrollTable = new JScrollPane();
        scrollTable.setVerticalScrollBar(new ScrollBar(Color.decode("#888888"), Color.decode("#eeeeee")));
        scrollTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTable.setBounds(35, 140, 900, 500);
    }

    public void setProductsTable() {
        if(scrollTable != null) {
            remove(scrollTable);
        }
        String[] colnames = {"STT", "Mã SP-Nhóm", "Tên SP-Nhóm", "Giá tiền", "Lượt bán"};
        this.productTable = new StaTable();
        productTable.setModelTable(colnames);
        setScrollTable();
        scrollTable.setViewportView(productTable);
        openSales.setBackground(ProjectUtil.getMyGreenColor());
        openRevenue.setBackground(Color.decode("#333333")); 
        this.type = 0;
        add(scrollTable);
    }
    
    public void setDataProductsTable() {
        clearTable();
        products.clear();
        products = ProductsBUS.getAllProducts();
        combos.clear();
        combos = ComboBUS.getAllCombo();
        String pattern = "yyyy-MM-dd hh:mm:ss";
        DateFormat formatter = new SimpleDateFormat(pattern);
        for (int i = 0; i < products.size(); i++) {
            int n = BillDetailBUS.getProductSales(products.get(i).getProductCode(),
                    formatter.format(dateStart.getDate()), 
                    formatter.format(dateEnd.getDate()));
            productTable.myAddRow(i + 1, products.get(i),n);
        }
        for (int i = 0; i < combos.size(); i++) {
            int n = BillDetailBUS.getComboSale(combos.get(i).getComboCode(),
                    formatter.format(dateStart.getDate()), 
                    formatter.format(dateEnd.getDate()));
            productTable.myAddRow(i + 1 + products.size(), combos.get(i),n);
        }
    }
    
    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0);
    }
    
    public void setRevenueTable() {
         if(scrollTable != null) {
            remove(scrollTable);
        }
        String[] colnames = {"Ngày", "Doanh thu"};
        this.productTable = new StaTable();
        productTable.setModelTable(colnames);
        setScrollTable();
        scrollTable.setViewportView(productTable);
        openRevenue.setBackground(ProjectUtil.getMyGreenColor());
        openSales.setBackground(Color.decode("#333333"));
        this.type = 1;
        add(scrollTable);
    }
    
    public void setDataRevenueTable() {
        clearTable();
        String pattern = "yyyy-MM-dd hh:mm:ss";
        DateFormat formatter = new SimpleDateFormat(pattern);
        String start = formatter.format(dateStart.getDate());
        String end = formatter.format(dateEnd.getDate());
        int n = BillBUS.getRenevue(start, end);
        productTable.myAddRow(start + "  -  " + end,n);
    }
}
