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
import javax.swing.JTextField;
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

    private JLabel codeLabel;
    private JTextField codeField;
    private Button searchButton;

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

        openSales = new Button("Lượt bán", 80, 30, Color.decode("#333333"));
        openSales.setBounds(35, 30, 80, 30);
        openSales.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setProductsTable();
                setDataProductsTable2();
            }
        });
        add(openSales);

        Button viewButton = new Button("Xem", 80, 30, Color.decode("#333333"));
        viewButton.setBounds(750, 75, 80, 30);
        viewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (type == 0) {
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
                remove(codeLabel);
                remove(codeField);
                remove(searchButton);
                repaint();
                validate();
                setRevenueTable();
                setDataRevenueTable2();
            }

        });
        add(openRevenue);

        JLabel start = new JLabel("Từ: ");
        start.setFont(ProjectUtil.getMyFont(14));
        start.setBounds(35, 75, 40, 30);
        add(start);
        this.dateStart = new JDateChooser();
        dateStart.setBounds(75, 75, 300, 30);
        dateStart.setDateFormatString("yyyy-MM-dd");
        add(dateStart);

        JLabel end = new JLabel("Đến: ");
        end.setFont(ProjectUtil.getMyFont(14));
        end.setBounds(390, 75, 40, 30);
        add(end);
        this.dateEnd = new JDateChooser();
        dateEnd.setBounds(440, 75, 300, 30);
        dateEnd.setDateFormatString("yyyy-MM-dd");
        add(dateEnd);

        codeLabel = new JLabel("Mã sản phẩm: ");
        codeLabel.setFont(ProjectUtil.getMyFont(14));
        codeLabel.setBounds(35, 110, 100, 30);

        codeField = new JTextField();
        codeField.setFont(ProjectUtil.getMyFont(14));
        codeField.setBounds(145, 110, 595, 30);

        searchButton = new Button("Tìm", 80, 30, Color.decode("#333333"));
        searchButton.setBounds(750, 110, 80, 30);
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (type == 0) {
                    setDataProductsTable();
                } 
            }
        });

        setProductsTable();
        setDataProductsTable2();

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

    public void setScrollTable() {
        scrollTable = new JScrollPane();
        scrollTable.setVerticalScrollBar(new ScrollBar(Color.decode("#888888"), Color.decode("#eeeeee")));
        scrollTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTable.setBounds(35, 145, 900, 500);
    }

    public void setProductsTable() {
        add(codeLabel);
        add(codeField);
        add(searchButton);
        repaint();
        validate();
        if (scrollTable != null) {
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
        String pattern = "yyyy-MM-dd";
        DateFormat formatter = new SimpleDateFormat(pattern);
        if (!"".equals(codeField.getText()) && dateStart.getDate() != null && dateEnd.getDate() != null) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductCode().equals(codeField.getText())) {
                    int n = BillDetailBUS.getProductSales(products.get(i).getProductCode(),
                            formatter.format(dateStart.getDate()),
                            formatter.format(dateEnd.getDate()));
                    productTable.myAddRow(i + 1, products.get(i), n);
                }
            }
            for (int i = 0; i < combos.size(); i++) {
                if (combos.get(i).getComboCode().equals(codeField.getText())) {
                    int n = BillDetailBUS.getComboSale(combos.get(i).getComboCode(),
                            formatter.format(dateStart.getDate()),
                            formatter.format(dateEnd.getDate()));
                    productTable.myAddRow(i + 1 + products.size(), combos.get(i), n);
                }
            }
        } else if ("".equals(codeField.getText()) && dateStart.getDate() != null && dateEnd.getDate() != null) {
            for (int i = 0; i < products.size(); i++) {
                int n = BillDetailBUS.getProductSales(products.get(i).getProductCode(),
                        formatter.format(dateStart.getDate()),
                        formatter.format(dateEnd.getDate()));
                productTable.myAddRow(i + 1, products.get(i), n);
            }
            for (int i = 0; i < combos.size(); i++) {
                int n = BillDetailBUS.getComboSale(combos.get(i).getComboCode(),
                        formatter.format(dateStart.getDate()),
                        formatter.format(dateEnd.getDate()));
                productTable.myAddRow(i + 1 + products.size(), combos.get(i), n);
            }
        } else if (!"".equals(codeField.getText()) && dateStart.getDate() == null && dateEnd.getDate() == null) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductCode().equals(codeField.getText())) {
                    int n = BillDetailBUS.getProductSales(products.get(i).getProductCode());
                    productTable.myAddRow(i + 1, products.get(i), n);
                }
            }
            for (int i = 0; i < combos.size(); i++) {
                if (combos.get(i).getComboCode().equals(codeField.getText())) {
                    int n = BillDetailBUS.getComboSale(combos.get(i).getComboCode());
                    productTable.myAddRow(i + 1 + products.size(), combos.get(i), n);
                }
            }
        }

    }

    public void setDataProductsTable2() {
        clearTable();
        products.clear();
        products = ProductsBUS.getAllProducts();
        combos.clear();
        combos = ComboBUS.getAllCombo();
        String pattern = "yyyy-MM-dd";
        DateFormat formatter = new SimpleDateFormat(pattern);
        for (int i = 0; i < products.size(); i++) {
            int n = BillDetailBUS.getProductSales(products.get(i).getProductCode());
            productTable.myAddRow(i + 1, products.get(i), n);
        }
        for (int i = 0; i < combos.size(); i++) {
            int n = BillDetailBUS.getComboSale(combos.get(i).getComboCode());
            productTable.myAddRow(i + 1 + products.size(), combos.get(i), n);
        }
    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0);
    }

    public void setRevenueTable() {
        if (scrollTable != null) {
            remove(scrollTable);
        }
        String[] colnames = {"Nội dung", "Kết quả"};
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
        String pattern = "yyyy-MM-dd";
        DateFormat formatter = new SimpleDateFormat(pattern);
        String start = formatter.format(dateStart.getDate());
        String end = formatter.format(dateEnd.getDate());
        int n = BillBUS.getRenevue(start, end);
        productTable.myAddRow("Tổng doanh thu: ", ""+n);
    }
    
    public void setDataRevenueTable2() {
        clearTable();
        productTable.myAddRow("Tổng doanh thu: ", ""+BillBUS.getRenevue());
        productTable.myAddRow("Tổng số đơn hàng: ", ""+BillBUS.getTotalBill());
        productTable.myAddRow("Số tiền trung bình của 1 đơn hàng: ", ProjectUtil.toMoney(BillBUS.getAVG())); 
        productTable.myAddRow("Tổng số sản phẩm bán được: ", String.valueOf(BillDetailBUS.getProductSales() + BillDetailBUS.getComboSale()));
    }
}
