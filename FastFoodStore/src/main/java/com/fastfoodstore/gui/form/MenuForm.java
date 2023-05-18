package com.fastfoodstore.gui.form;

import com.fastfoodstore.bus.ComboBUS;
import com.fastfoodstore.bus.GroupsBus;
import com.fastfoodstore.bus.ProductsBUS;
import com.fastfoodstore.dto.ComboDTO;
import com.fastfoodstore.dto.GroupDTO;
import com.fastfoodstore.dto.ProductsDTO;
import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.form.menuform.AddProductForm;
import com.fastfoodstore.gui.form.menuform.DetailForm;
import com.fastfoodstore.gui.form.menuform.MenuTable;
import com.fastfoodstore.gui.item.Button;
import com.fastfoodstore.gui.item.ScrollBar;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MenuForm extends JPanel {

    private MenuTable productTable;
    private JScrollPane scrollTable;

    private JLabel searchCodeLabel;
    private JLabel searchPriceLabel;
    private JLabel searchGroupLabel;

    private JTextField searchCodeField;
    private JComboBox searchPriceBox;
    private JTextField searchGroupField;

    private ArrayList<ProductsDTO> products;
    private ArrayList<ComboDTO> combos;
    private ArrayList<GroupDTO> groups;
    
    private ArrayList<ProductsDTO> allProducts = new ArrayList<>();
    private ArrayList<ComboDTO> allCombos = new ArrayList<>();
    private ArrayList<GroupDTO> allGroups = new ArrayList<>();

    private Button openProduct;
    private Button openCombo;
    private Button openGroup;
    private Button addproductButton;

    private Button searchButton1;
    private Button searchButton2;
    
    private int[] sta = {0,0,0};
    private Font f = new Font("Segoe UI Semibold", Font.PLAIN, 14);

    public MenuForm() {
        products = new ArrayList<>();
        allProducts = ProductsBUS.getAllProducts();
        combos = new ArrayList<>();
        allCombos = ComboBUS.getAllCombo();
        groups = new ArrayList<>();
        allGroups = GroupsBus.getAllGroup();
        initComponent();
        setOpaque(false);
    }

    private void initComponent() {
        setLayout(null);
        JPanel wallPanel = new JPanel();
        wallPanel.setBounds(35, 65, 900, 3);
        wallPanel.setBackground(Color.decode("#aaaaaa"));
        add(wallPanel);
        setProductsTable();
        setOpenProduct();
        setOpenCombo();
        setAddproductButton();
        setOpenGroup();
        add(scrollTable);
        add(openProduct);
        add(openCombo);
        add(openGroup);
        add(addproductButton);
        setDataProductsTable("");
        setSearchCode();
        setSearchGroup();
        setSearchPrice();
        setSearchButton1();
        setSearchButton2();
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
        scrollTable.setBounds(35, 140, 900, 500);
    }

    public void setProductsTable() {
        String[] colnames = {"STT", "Mã SP-Nhóm", "Tên SP-Nhóm", "Giá tiền", "Nhóm", ""};
        this.productTable = new MenuTable(this);
        productTable.setModelTable(colnames);
        setScrollTable();
        scrollTable.setViewportView(productTable);
    }

    public void setDataProductsTable(String code) {
        clearTable();
        products.clear();
        if ("".equals(code)) {
            products.addAll(allProducts);
        } else {
            products.add(ProductsBUS.getProductsByCode(code));
        }
        combos.clear();
        groups.clear();
        sta[0] = 1;
        openProduct.setBackground(ProjectUtil.getMyGreenColor());
        sta[1] = 0;
        openCombo.setBackground(Color.decode("#333333"));
        sta[2] = 0;
        openGroup.setBackground(Color.decode("#333333")); 
        for (int i = 0; i < products.size(); i++) {
            productTable.myAddRow(i + 1, products.get(i));
        }
    }

    public void setDaTaComboTable(String code) {
        clearTable();
        combos.clear();
        if ("".equals(code)) {
            combos.addAll(allCombos);
        } else {
            combos.add(ComboBUS.getComboByCode(code));
        }
        products.clear();
        groups.clear();
        sta[0] = 0;
        openProduct.setBackground(Color.decode("#333333"));
        sta[1] = 1;
        openCombo.setBackground(ProjectUtil.getMyGreenColor());
        sta[2] = 0;
        openGroup.setBackground(Color.decode("#333333")); 
        for (int i = 0; i < combos.size(); i++) {
            productTable.myAddRow(i + 1, combos.get(i));
        }
    }

    public void setDataGroupTable(String code) {
        clearTable();
        groups.clear();
        if ("".equals(code)) {
            groups.addAll(allGroups); 
        } else {
            groups.add(GroupsBus.getGroupByCode(code));
        }
        products.clear();
        combos.clear();
        sta[0] = 0;
        openProduct.setBackground(Color.decode("#333333")); 
        sta[1] = 0;
        openCombo.setBackground(Color.decode("#333333"));
        sta[2] = 1;
        openGroup.setBackground(ProjectUtil.getMyGreenColor());
        for (int i = 0; i < groups.size(); i++) {
            productTable.myAddRow(i + 1, groups.get(i));
        }
    }

    public void setDataProductsTable() {
        clearTable();
        for (int i = 0; i < products.size(); i++) {
            productTable.myAddRow(i + 1, products.get(i));
        }
    }

    public void setDaTaComboTable() {
        clearTable();
        for (int i = 0; i < combos.size(); i++) {
            productTable.myAddRow(i + 1, combos.get(i));
        }
    }

    public void setDataGroupTable() {
        clearTable();
        for (int i = 0; i < groups.size(); i++) {
            productTable.myAddRow(i + 1, groups.get(i));
        }
    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0);
    }

    public void setOpenProduct() {
        this.openProduct = new Button("Sản phẩm", 80, 30, Color.decode("#333333"));
        openProduct.setBounds(35, 30, 80, 30);
        openProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clearSearch();
                setDataProductsTable("");
            }

        });
    }

    public void setOpenCombo() {
        this.openCombo = new Button("Combo", 80, 30, Color.decode("#333333"));
        openCombo.setBounds(120, 30, 80, 30);
        openCombo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clearSearch();
                setDaTaComboTable("");
            }

        });
    }

    public void setOpenGroup() {
        this.openGroup = new Button("Nhóm", 80, 30, Color.decode("#333333"));
        openGroup.setBounds(205, 30, 80, 30);
        openGroup.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clearSearch();
                setDataGroupTable("");
            }

        });
    }

    public void setAddproductButton() {
        this.addproductButton = new Button("Thêm", 80, 30, Color.decode("#333333"));
        addproductButton.setBounds(855, 30, 80, 30);
        addproductButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddProductForm add = new AddProductForm(null);
                add.setVisible(true);
            }
            
        });
    }

    public void setSearchCode() {
        this.searchCodeLabel = new JLabel("Lọc theo mã: ");
        searchCodeLabel.setBounds(35, 75, 150, 25);
        searchCodeLabel.setFont(f);
        this.searchCodeField = new JTextField();
        searchCodeField.setBounds(195, 75, 385, 25);
        searchCodeField.setBorder(new EmptyBorder(0, 0, 0, 0));
        searchCodeField.setFont(f);
        add(searchCodeLabel);
        add(searchCodeField);
    }

    public void setSearchButton1() {
        this.searchButton1 = new Button("Lọc", 50, 25, Color.decode("#333333"));
        searchButton1.setBounds(590, 75, 50, 25);
        searchButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!"".equals(searchCodeField.getText())) {
                    if (sta[0] == 1) {
                        setDataProductsTable(searchCodeField.getText());
                    } else if (sta[1] == 1) {
                        setDaTaComboTable(searchCodeField.getText());
                    } else if (sta[2] == 1) {
                        setDataGroupTable(searchCodeField.getText());
                    }
                }

            }
        });
        add(searchButton1);
    }

    public void setSearchGroup() {
        this.searchGroupLabel = new JLabel("Lọc theo nhóm chứa: ");
        searchGroupLabel.setBounds(35, 105, 150, 25);
        searchGroupLabel.setFont(f);
        this.searchGroupField = new JTextField();
        searchGroupField.setBounds(195, 105, 150, 25);
        searchGroupField.setBorder(new EmptyBorder(0, 0, 0, 0));
        searchGroupField.setFont(f);
        add(searchGroupLabel);
        add(searchGroupField);
    }

    public void setSearchPrice() {
        this.searchPriceLabel = new JLabel("Giá bán: ");
        searchPriceLabel.setBounds(355, 105, 70, 25);
        searchPriceLabel.setFont(f);
        String[] option = {" All", " < 100,000", " 100,000 - 200,000", " > 200,000"};
        this.searchPriceBox = new JComboBox(option);
        searchPriceBox.setBounds(430, 105, 150, 25);
        searchPriceBox.setBorder(new EmptyBorder(0, 0, 0, 0));
        searchPriceBox.setFont(f);
        add(searchPriceLabel);
        add(searchPriceBox);
    }

    public void clearSearch() {
        searchCodeField.setText("");
        searchGroupField.setText("");
    }

    public void setSearchButton2() {
        this.searchButton2 = new Button("Lọc", 50, 25, Color.decode("#333333"));
        searchButton2.setBounds(590, 105, 50, 25);
        searchButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!"".equals(searchGroupField.getText())) {
                    if (sta[0] == 1) {
                        fillterProducts(searchGroupField.getText());
                        fillterProducts(searchPriceBox.getSelectedIndex(),1); 
                        setDataProductsTable();
                    } else if (sta[1] == 1) {
                        fillterCombos(searchGroupField.getText());
                        fillterCombos(searchPriceBox.getSelectedIndex(),1); 
                        setDaTaComboTable();
                    } else if (sta[2] == 1) {
                        fillterCombos(searchGroupField.getText());
                        setDaTaComboTable();
                    }
                } else {
                    if (sta[0] == 1) {
                        fillterProducts(searchPriceBox.getSelectedIndex(),0); 
                        setDataProductsTable();
                    } else if (sta[1] == 1) {
                        fillterCombos(searchPriceBox.getSelectedIndex(),0); 
                        setDaTaComboTable();
                    }
                }
            }

        });
        add(searchButton2);
    }

    public void fillterProducts(String code) {
        ProductsDTO[] temp = allProducts.toArray(new ProductsDTO[allProducts.size()]);
        products = (ArrayList<ProductsDTO>) Arrays.stream(temp)
                .filter(item -> (item.getGroupCode().equals(code)))
                .collect(Collectors.toList());
    }

    public void fillterCombos(String code) {
        ComboDTO[] temp = allCombos.toArray(new ComboDTO[allCombos.size()]);
        combos = (ArrayList<ComboDTO>) Arrays.stream(temp)
                .filter(item -> (item.getGroupCode().equals(code)))
                .collect(Collectors.toList());
    }

    public void fillterGroups(String code) {
        GroupDTO[] temp = allGroups.toArray(new GroupDTO[allGroups.size()]);
        groups = (ArrayList<GroupDTO>) Arrays.stream(temp)
                .filter(item -> (item.getIN_groupCode() == null ? code == null : item.getIN_groupCode().equals(code)))
                .collect(Collectors.toList());
    }

    public void fillterProducts(int p, int type) {
        ProductsDTO[] temp;
        if (type == 0) {
            temp = allProducts.toArray(new ProductsDTO[allProducts.size()]);
        } else {
            temp = products.toArray(new ProductsDTO[products.size()]);
        }
        
        switch (p) {
            case 0:
                products.clear();
                products.addAll(Arrays.asList(temp));
                break;
            case 1:
                products = (ArrayList<ProductsDTO>) Arrays.stream(temp)
                        .filter(item -> (item.getProductPrice() >= 0 && item.getProductPrice() < 100000))
                        .collect(Collectors.toList());
                break;
            case 2:
                products = (ArrayList<ProductsDTO>) Arrays.stream(temp)
                        .filter(item -> (item.getProductPrice() >= 100000 && item.getProductPrice() < 200000))
                        .collect(Collectors.toList());
                break;
            case 3:
                products = (ArrayList<ProductsDTO>) Arrays.stream(temp)
                        .filter(item -> (item.getProductPrice() >= 200000))
                        .collect(Collectors.toList());
                break;
        }

    }

    public void fillterCombos(int p, int type) {
         ComboDTO[] temp;
        if (type == 0) {
            temp = allCombos.toArray(new ComboDTO[allCombos.size()]);
        } else {
            temp = combos.toArray(new ComboDTO[combos.size()]);
        }
        
        switch (p) {
            case 0:
                combos.clear();
                combos.addAll(Arrays.asList(temp));
                break;
            case 1:
                combos = (ArrayList<ComboDTO>) Arrays.stream(temp)
                        .filter(item -> (item.getComboPrice() >= 0 && item.getComboPrice() < 100000))
                        .collect(Collectors.toList());
                break;
            case 2:
                combos = (ArrayList<ComboDTO>) Arrays.stream(temp)
                        .filter(item -> (item.getComboPrice() >= 100000 && item.getComboPrice() <= 200000))
                        .collect(Collectors.toList());
                break;
            case 3:
                combos = (ArrayList<ComboDTO>) Arrays.stream(temp)
                        .filter(item -> (item.getComboPrice() >= 200000))
                        .collect(Collectors.toList());
                break;
        }

    }

    public void viewDetail(int index) {
        if (!products.isEmpty()) {
            DetailForm dt = new DetailForm(null,"",products.get(index)); 
            dt.setVisible(true); 
            setDataProductsTable();
        }
        if (!combos.isEmpty()) {
            DetailForm dt = new DetailForm(null,"",combos.get(index)); 
            dt.setVisible(true); 
            setDaTaComboTable();
        }
        if (!groups.isEmpty()) {
            DetailForm dt = new DetailForm(null,"",groups.get(index)); 
            dt.setVisible(true); 
            setDataGroupTable();
        }
    }

}
