/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form;

import com.fastfoodstore.bus.ComboBUS;
import com.fastfoodstore.bus.GroupsBus;
import com.fastfoodstore.bus.ProductsBUS;
import com.fastfoodstore.dto.ComboDTO;
import com.fastfoodstore.dto.GroupDTO;
import com.fastfoodstore.dto.ProductsDTO;
import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.form.orderform.BillDetailGUI;
import com.fastfoodstore.gui.item.Button;
import com.fastfoodstore.gui.item.Card;
import com.fastfoodstore.gui.form.orderform.GroupItem;
import com.fastfoodstore.gui.form.orderform.ProductItem;
import com.fastfoodstore.gui.item.ScrollBar;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

/**
 *
 * @author ADMIN
 */
public class OrderForm extends JPanel {

    private JPanel parentCardPanel;
    private Card card1;
    private Card card2;
    private Card card3;

    private JPanel groupMenuPanel;
    private ArrayList<GroupItem> groupItems = new ArrayList<>();

    private JPanel wallPanel;

    private JScrollPane scrollPane;
    private JPanel productMenuPanel;
    private ArrayList<ProductItem> productItem = new ArrayList<>();

    private Button homePageButton;
    private Button promoButton;
    private Button backButton;

    private Timer productMenuTimer;
    private int y = 680;
    
    private BillDetailGUI billDetailPanel;

    public OrderForm() {
        initComponent();
        setOpaque(false);
    }

    private void initComponent() {
        setLayout(null);
        setParentCardPanel();
        setGroupMenuPanel();
        setProductMenuPanel();
        setWallPanel();
        setHomePageButton();
        setPromoButton();
        setBackButton();
        setBillDetailPanel();
        add(parentCardPanel);
        add(groupMenuPanel);
        add(wallPanel);
        add(scrollPane);
        add(homePageButton);
        add(promoButton);
        add(backButton);
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

    public void setParentCardPanel() {
        this.parentCardPanel = new JPanel();
        parentCardPanel.setLayout(new GridLayout(1, 0, 20, 0));
        parentCardPanel.setBounds(20, 20, 930, 170);

        card1 = new Card("./landing/home-slice.jpg");
        card2 = new Card("./landing/home-hamburger.png");
        card3 = new Card("./landing/home-chicken.png");

        parentCardPanel.add(card1);
        parentCardPanel.add(card2);
        parentCardPanel.add(card3);

    }

    public void setGroupMenuPanel() {
        this.groupMenuPanel = new JPanel();
        groupMenuPanel.setLayout(new GridLayout(0, 1, 0, 30));
        groupMenuPanel.setBounds(20, 220, 70, 400);

        ArrayList<GroupDTO> data = GroupsBus.getGroupZero();
        for (GroupDTO a : data) {
            GroupItem item = new GroupItem(a);
            item.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    setDataMenu(item.getData().getGroupCode());
                    animation();
                }
            });
            groupItems.add(item);
        }

        for (GroupItem a : groupItems) {
            groupMenuPanel.add(a);
        }
    }

    public void setWallPanel() {
        this.wallPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode("#d9d9d9"));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                super.paintComponent(g);
            }

        };
        wallPanel.setBounds(120, 220, 2, 400);
        wallPanel.setBackground(Color.decode("#d9d9d9"));
    }

    public void setScrollPane() {
        this.scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBar(new ScrollBar(new Color(255, 115, 55),new Color(242,242,242)));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(productMenuPanel);
        animation();
    }

    public void setProductMenuPanel() {
        this.productMenuPanel = new JPanel();
        productMenuPanel.setLayout(new GridLayout(0, 3, 20, 20));
        setScrollPane();
        setDataMenu3();
    }

    // Lấy tất cả nhóm, sản phẩm và combo để hiển thị.
    public void setDataMenu(String code) {

        this.productMenuPanel.removeAll();

        ArrayList<GroupDTO> data = GroupsBus.getGroupUnder(code);
        ArrayList<ProductsDTO> data1 = ProductsBUS.getProductsInGroup(code);
        ArrayList<ComboDTO> data2 = ComboBUS.getComboInGroup(code);

        productItem.clear();

        if (data != null) {
            for (GroupDTO a : data) {
                productItem.add(new ProductItem(a));
            }
        }

        if (data1 != null) {
            for (ProductsDTO a : data1) {
                productItem.add(new ProductItem(a));
            }
        }

        if (data2 != null) {
            for (ComboDTO a : data2) {
                productItem.add(new ProductItem(a));
            }
        }

        for (ProductItem a : productItem) {
            a.getImgPanel().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (a.getData() instanceof GroupDTO) {
                        setDataMenu(a.getDataGroup().getGroupCode());
                        animation();
                    } else if(a.getData() instanceof ProductsDTO) {
                        if(billDetailPanel.isOrdering()) {
                            billDetailPanel.addData1(a.getDataProduct());  
                        }
                    } else {
                        if(billDetailPanel.isOrdering()) {
                            billDetailPanel.addData2(a.getDataCombo());
                        }
                    }
                }

            });
            productMenuPanel.add(a);
        }

        this.productMenuPanel.validate();
        this.productMenuPanel.repaint();
    }

    // Hàm lấy cả nhóm, sản phẩm và combo dùng cho nút quay lại.
    public void setDataMenu2(String code) {

        if (code.length() != 2) {
            this.productMenuPanel.removeAll();

            GroupDTO tempGroupDTO = GroupsBus.getGroupByCode(code);
            ProductsDTO tempProductsDTO = ProductsBUS.getProductsByCode(code);
            ComboDTO tempComboDTO = ComboBUS.getComboByCode(code);

            ArrayList<GroupDTO> data = null;
            ArrayList<ProductsDTO> data1 = null;
            ArrayList<ComboDTO> data2 = null;

            if (tempGroupDTO != null) {
                data = GroupsBus.getGroupUnder(tempGroupDTO.getIN_groupCode());
            }

            if (tempProductsDTO != null) {
                data1 = ProductsBUS.getProductsInGroup(tempProductsDTO.getGroupCode());
            }

            if (tempComboDTO != null) {
                data2 = ComboBUS.getComboInGroup(tempComboDTO.getGroupCode());
            }

            productItem.clear();

            if (data != null) {
                for (GroupDTO a : data) {
                    productItem.add(new ProductItem(a));
                }
            }

            if (data1 != null) {
                for (ProductsDTO a : data1) {
                    productItem.add(new ProductItem(a));
                }
            }

            if (data2 != null) {
                for (ComboDTO a : data2) {
                    productItem.add(new ProductItem(a));
                }
            }

            for (ProductItem a : productItem) {
                a.getImgPanel().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (a.getDataGroup() != null) {
                            setDataMenu(a.getDataGroup().getGroupCode());
                            animation();
                        }
                    }

                });
                productMenuPanel.add(a);
            }

            this.productMenuPanel.validate();
            this.productMenuPanel.repaint();
        }

    }

    // Hàm lấy nhóm sản phẩm để hiển thị.
    public void setDataMenu3() {
        this.productMenuPanel.removeAll();

        ArrayList<GroupDTO> data = GroupsBus.getAllGroupUnder(3);

        productItem.clear();

        for (GroupDTO a : data) {
            productItem.add(new ProductItem(a));
        }

        for (ProductItem a : productItem) {
            a.getImgPanel().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (a.getDataGroup() != null) {
                        setDataMenu(a.getDataGroup().getGroupCode());
                        animation();
                    }
                }

            });
            productMenuPanel.add(a);
        }

        this.productMenuPanel.validate();
        this.productMenuPanel.repaint();
    }

    public void setHomePageButton() {
        this.homePageButton = new Button("Trang chủ", 100, 30, ProjectUtil.getMyGreenColor());
        homePageButton.setBounds(320, 220, 90, 30);
        homePageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setDataMenu3();
                animation();
            }
        });
    }

    public void setPromoButton() {
        this.promoButton = new Button("Khuyến mãi", 100, 30, ProjectUtil.getMyGreenColor());
        promoButton.setBounds(420, 220, 90, 30);
        promoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ConFirmForm conFirmForm = new ConFirmForm();
                String result = conFirmForm.show();
            }
            
        });
    }

    public void setBackButton() {
        this.backButton = new Button("Quay lại", 100, 30, ProjectUtil.getMyGreenColor());
        backButton.setBounds(520, 220, 90, 30);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (productItem.get(0).getData() instanceof GroupDTO) {
                    setDataMenu2(productItem.get(0).getDataGroup().getIN_groupCode());
                } else if (productItem.get(0).getData() instanceof ProductsDTO) {
                    setDataMenu2(productItem.get(0).getDataProduct().getGroupCode());
                } else if (productItem.get(0).getData() instanceof  ComboDTO) {
                    setDataMenu2(productItem.get(0).getDataCombo().getGroupCode());
                }
                animation();
            }
        });
    }

    public void animation() {
//        productMenuTimer = new Timer(0, (ActionEvent e1) -> {
//            if (y > 260) {
//                scrollPane.setBounds(160, y, 450, 360);
//                y = y - 25;
//            } else {
//                
//                productMenuTimer.stop();
//                y = 680;
//            }
//        });
//        productMenuTimer.start();
        scrollPane.setBounds(160, 260, 450, 360);
    }
    
     public void setBillDetailPanel() {
        this.billDetailPanel = new BillDetailGUI();
        billDetailPanel.setBounds(650, 220, 300, 400); 
    }

}
