package com.fastfoodstore.gui.form;

import com.fastfoodstore.bus.ComboBUS;
import com.fastfoodstore.bus.ProductsBUS;
import com.fastfoodstore.dto.ComboDTO;
import com.fastfoodstore.dto.ProductsDTO;
import com.fastfoodstore.gui.form.menuform.MenuTable;
import com.fastfoodstore.gui.form.menuform.MenuTableEditor;
import com.fastfoodstore.gui.item.ScrollBar;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class MenuForm extends JPanel {

    private MenuTable productTable;
    private MenuTable comboTable;
    private JScrollPane scrollTable;

    private ArrayList<ProductsDTO> products;
    private ArrayList<ComboDTO> combos;

    public MenuForm() {
        initComponent();
        setOpaque(false);
    }

    private void initComponent() {
        setLayout(null);
        setProductsTable();
        add(scrollTable);
        setDataproductsTable();
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
        scrollTable.setBounds(35, 120, 900, 500);
    }

    public void setProductsTable() {
        String[] colnames = {"STT", "Mã sản phẩm", "Tên sản phẩm", "Giá tiền", "Nhóm", ""};
        this.productTable = new MenuTable();
        productTable.setModelTable(colnames);
        setScrollTable();
        scrollTable.setViewportView(productTable);
    }

    public void setDataproductsTable() {
        this.products = new ArrayList<>();
        products = ProductsBUS.getAllProducts();
        for (int i = 0; i < products.size(); i++) {
            productTable.myAddRow(i + 1, products.get(i));
        }
    }

    public void setDaTaComboTable() {
        this.combos = new ArrayList<>();
        combos = ComboBUS.getAllCombo();
        this.comboTable = new MenuTable();
        for (int i = 0; i < combos.size(); i++) {
            productTable.myAddRow(i + 1, products.get(i));
        }
    }
    
    
    
    

}
