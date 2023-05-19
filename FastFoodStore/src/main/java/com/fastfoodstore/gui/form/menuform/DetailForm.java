/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.menuform;

import com.fastfoodstore.bus.ComboBUS;
import com.fastfoodstore.bus.GroupsBus;
import com.fastfoodstore.bus.ProductsBUS;
import com.fastfoodstore.dto.ComboDTO;
import com.fastfoodstore.dto.GroupDTO;
import com.fastfoodstore.dto.ProductsDTO;
import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.item.Button;
import com.fastfoodstore.gui.item.ScrollBar;
import com.fastfoodstore.gui.toggle.ToggleButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ADMIN
 */
public class DetailForm extends JDialog {

    private JPanel panel;

    private Button saveButton;
    private Button closeButton;

    private ProductsDTO product;
    private ComboDTO combo;
    private GroupDTO group;

    private JPanel imgPanel;
    private BufferedImage img;
    private String imgString;

    private JTextField codeField;

    private JTextArea nameField;

    private JTextField priceField;

    private JComboBox<String> inGBox;

    private ToggleButton inButton;

    private JFileChooser imgChooser;
    private JLabel imgLabel;
    private Button chooseButton;

    public DetailForm(JFrame parent, String title, ProductsDTO data) {
        super(parent, "Chi tiết sản phẩm", true);
        initComponent();
        this.product = data;
        this.imgString = data.getProductImage();
        img = ProjectUtil.addImg(product.getProductImage());

        setImgPanel();
        panel.add(imgPanel);

        JLabel codeLabel = new JLabel("Mã: ");
        codeLabel.setFont(ProjectUtil.getMyFont(14));
        codeLabel.setBounds(18, 220, 50, 25);
        panel.add(codeLabel);

        this.codeField = new JTextField(product.getProductCode());
        codeField.setEditable(false);
        codeField.setFont(ProjectUtil.getMyFont(14));
        codeField.setBounds(70, 220, 260, 25);
        codeField.setBorder(new LineBorder(Color.decode("#333333"), 1));
        panel.add(codeField);

        JLabel nameLabel = new JLabel("Tên: ");
        nameLabel.setFont(ProjectUtil.getMyFont(14));
        nameLabel.setBounds(18, 255, 50, 25);
        panel.add(nameLabel);

        this.nameField = new JTextArea(product.getProductName());
        nameField.setFont(ProjectUtil.getMyFont(14));
        nameField.setBounds(70, 255, 260, 50);
        nameField.setBorder(new LineBorder(Color.decode("#333333"), 1));
        nameField.setLineWrap(true);
        nameField.setWrapStyleWord(true);
        panel.add(nameField);

        JLabel priceLabel = new JLabel("Giá: ");
        priceLabel.setFont(ProjectUtil.getMyFont(14));
        priceLabel.setBounds(18, 315, 50, 25);
        panel.add(priceLabel);

        this.priceField = new JTextField(String.valueOf(product.getProductPrice()));
        priceField.setFont(ProjectUtil.getMyFont(14));
        priceField.setBounds(70, 315, 260, 25);
        priceField.setBorder(new LineBorder(Color.decode("#333333"), 1));
        panel.add(priceField);

        JLabel inGLabel = new JLabel("Nhóm");
        inGLabel.setFont(ProjectUtil.getMyFont(14));
        inGLabel.setBounds(18, 350, 50, 25);
        panel.add(inGLabel);

        ArrayList<String> items = GroupsBus.getCodeNameGroup();
        this.inGBox = new JComboBox<>();
        inGBox.setModel(new DefaultComboBoxModel<>(items.toArray(String[]::new)));
        for (String i : items) {
            if (i.split("-")[0].equals(product.getGroupCode())) {
                inGBox.setSelectedItem(i);
            }
        }
        inGBox.setBounds(70, 350, 260, 25);
        panel.add(inGBox);

        JLabel inLabel = new JLabel("Bán: ");
        inLabel.setFont(ProjectUtil.getMyFont(14));
        inLabel.setBounds(18, 385, 50, 25);
        panel.add(inLabel);

        inButton = new ToggleButton();
        inButton.setBounds(70, 385, 50, 30);
        inButton.setSelected(product.getInMenu() == 1);
        panel.add(inButton);

        this.imgChooser = new JFileChooser();
        this.imgLabel = new JLabel();
        imgLabel.setBounds(135, 180, 135, 30);
        imgLabel.setFont(ProjectUtil.getMyFont(12));
        imgLabel.setBorder(new LineBorder(Color.BLACK, 1));
        panel.add(imgLabel);

        chooseButton = new Button("File", 60, 30, Color.decode("#333333"));
        chooseButton.setBounds(70, 180, 60, 30);
        chooseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                chooseImg();
            }
        });
        panel.add(chooseButton);

        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean error = false;
                product.setProductName(nameField.getText());
                if (!"".equals(imgLabel.getText())) {
                    product.setProductImage("./img-product/" + imgChooser.getSelectedFile().getName());
                    ProjectUtil.addImgToFolder("./img-product",imgChooser.getSelectedFile());
                }
                int price = ProjectUtil.checkNumber(priceField.getText());
                if (price < 0) {
                    error = true;
                } else {
                    product.setProductPrice(price);
                }
                product.setGroupCode(String.valueOf(inGBox.getSelectedItem()).split("-")[0]);
                if (inButton.isSelected()) {
                    product.setInMenu(1);
                } else {
                    product.setInMenu(0);
                }
                if (error) {
                    showError("Giá trị nhập vào không hợp lệ!");
                } else {
                    if (ProductsBUS.updateProduct(product) == 1) {
                        showMes();
                        dispose();
                    } else {
                        showError("Đã xảy ra lỗi, hãy thử lại sau!");
                    }
                }

            }

        });

    }

    public DetailForm(JFrame parent, String title, ComboDTO data) {
        super(parent, "Chi tiết combo", true);
        initComponent();
        this.combo = data;
        this.imgString = data.getComboImage();
        img = ProjectUtil.addImg(combo.getComboImage());

        setImgPanel();
        panel.add(imgPanel);

        JLabel codeLabel = new JLabel("Mã: ");
        codeLabel.setFont(ProjectUtil.getMyFont(14));
        codeLabel.setBounds(18, 220, 50, 25);
        panel.add(codeLabel);

        this.codeField = new JTextField(combo.getComboCode());
        codeField.setEditable(false);
        codeField.setFont(ProjectUtil.getMyFont(14));
        codeField.setBounds(70, 220, 260, 25);
        codeField.setBorder(new LineBorder(Color.decode("#333333"), 1));
        panel.add(codeField);

        JLabel nameLabel = new JLabel("Tên: ");
        nameLabel.setFont(ProjectUtil.getMyFont(14));
        nameLabel.setBounds(18, 255, 50, 25);
        panel.add(nameLabel);

        this.nameField = new JTextArea(combo.getComboName());
        nameField.setFont(ProjectUtil.getMyFont(14));
        nameField.setBounds(70, 255, 260, 50);
        nameField.setBorder(new LineBorder(Color.decode("#333333"), 1));
        nameField.setLineWrap(true);
        nameField.setWrapStyleWord(true);
        panel.add(nameField);

        JLabel priceLabel = new JLabel("Giá: ");
        priceLabel.setFont(ProjectUtil.getMyFont(14));
        priceLabel.setBounds(18, 315, 50, 25);
        panel.add(priceLabel);

        this.priceField = new JTextField(String.valueOf(combo.getComboPrice()));
        priceField.setFont(ProjectUtil.getMyFont(14));
        priceField.setBounds(70, 315, 260, 25);
        priceField.setBorder(new LineBorder(Color.decode("#333333"), 1));
        panel.add(priceField);

        JLabel inGLabel = new JLabel("Nhóm");
        inGLabel.setFont(ProjectUtil.getMyFont(14));
        inGLabel.setBounds(18, 350, 50, 25);
        panel.add(inGLabel);

        ArrayList<String> items = GroupsBus.getCodeNameGroup();
        this.inGBox = new JComboBox<>();
        inGBox.setModel(new DefaultComboBoxModel<>(items.toArray(String[]::new)));
        for (String i : items) {
            if (i.split("-")[0].equals(combo.getGroupCode())) {
                inGBox.setSelectedItem(i);
            }
        }
        inGBox.setBounds(70, 350, 260, 25);
        panel.add(inGBox);

        JLabel inLabel = new JLabel("Bán: ");
        inLabel.setFont(ProjectUtil.getMyFont(14));
        inLabel.setBounds(18, 385, 50, 25);
        panel.add(inLabel);

        inButton = new ToggleButton();
        inButton.setBounds(70, 385, 50, 30);
        inButton.setSelected(combo.getInMenu());
        panel.add(inButton);
        
        JLabel detailLabel = new JLabel("Bao gồm: ");
        detailLabel.setFont(ProjectUtil.getMyFont(14));
        detailLabel.setBounds(18, 415, 70, 25);
        panel.add(detailLabel);
        
        ArrayList<String> detailList = ComboBUS.getDetail(combo.getComboCode());
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> detailJList = new JList<>(model);
        for (String item : detailList) {
            model.addElement(item);
        }
        JScrollPane detailPane = new JScrollPane(detailJList);
        detailPane.setVerticalScrollBar(new ScrollBar(Color.decode("#888888"), Color.decode("#eeeeee")));
        detailPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        detailPane.setBounds(90, 415, 240, 60);
        panel.add(detailPane);

        this.imgChooser = new JFileChooser();
        this.imgLabel = new JLabel();
        imgLabel.setBounds(135, 180, 135, 30);
        imgLabel.setFont(ProjectUtil.getMyFont(12));
        imgLabel.setBorder(new LineBorder(Color.BLACK, 1));
        panel.add(imgLabel);

        chooseButton = new Button("File", 60, 30, Color.decode("#333333"));
        chooseButton.setBounds(70, 180, 60, 30);
        chooseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                chooseImg();
            }
        });
        panel.add(chooseButton);

        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean error = false;
                combo.setComboName(nameField.getText());
                if (!"".equals(imgLabel.getText())) {
                    combo.setComboImage("./img-combo/" + imgChooser.getSelectedFile().getName());
                    ProjectUtil.addImgToFolder("./img-combo",imgChooser.getSelectedFile());
                }
                int price = ProjectUtil.checkNumber(priceField.getText());
                if (price < 0) {
                    error = true;
                } else {
                    combo.setComboPrice(price);
                }
                combo.setGroupCode(String.valueOf(inGBox.getSelectedItem()).split("-")[0]);
                combo.setInMenu(inButton.isSelected());
                if (error) {
                    showError("Giá trị nhập vào không hợp lệ!");
                } else {
                    if (ComboBUS.updateCombo(combo) == 1) {
                        showMes();
                        dispose();
                    } else {
                        showError("Đã xảy ra lỗi, hãy thử lại sau!");
                    }
                }

            }

        });
    }

    public DetailForm(JFrame parent, String title, GroupDTO data) {
        super(parent, "Chi tiết nhóm", true);
        initComponent();
        this.group = data;
        this.imgString = data.getGroupIcon();
        img = ProjectUtil.addImg(group.getGroupIcon());

        setImgPanel();
        panel.add(imgPanel);

        JLabel codeLabel = new JLabel("Mã: ");
        codeLabel.setFont(ProjectUtil.getMyFont(14));
        codeLabel.setBounds(18, 220, 50, 25);
        panel.add(codeLabel);

        this.codeField = new JTextField(group.getGroupCode());
        codeField.setEditable(false);
        codeField.setFont(ProjectUtil.getMyFont(14));
        codeField.setBounds(70, 220, 260, 25);
        codeField.setBorder(new LineBorder(Color.decode("#333333"), 1));
        panel.add(codeField);

        JLabel nameLabel = new JLabel("Tên: ");
        nameLabel.setFont(ProjectUtil.getMyFont(14));
        nameLabel.setBounds(18, 255, 50, 25);
        panel.add(nameLabel);

        this.nameField = new JTextArea(group.getGroupName());
        nameField.setFont(ProjectUtil.getMyFont(14));
        nameField.setBounds(70, 255, 260, 50);
        nameField.setBorder(new LineBorder(Color.decode("#333333"), 1));
        nameField.setLineWrap(true);
        nameField.setWrapStyleWord(true);
        panel.add(nameField);

        JLabel inGLabel = new JLabel("Nhóm");
        inGLabel.setFont(ProjectUtil.getMyFont(14));
        inGLabel.setBounds(18, 315, 50, 25);
        panel.add(inGLabel);

        ArrayList<String> items = new ArrayList<>();
        items.add("Không");
        items.addAll(GroupsBus.getCodeNameGroup());
        this.inGBox = new JComboBox<>();
        inGBox.setModel(new DefaultComboBoxModel<>(items.toArray(String[]::new)));
        for (String i : items) {
            if (i.split("-")[0].equals(group.getIN_groupCode())) {
                inGBox.setSelectedItem(i);
            }
        }
        inGBox.setBounds(70, 315, 260, 25);
        panel.add(inGBox);

        JLabel inLabel = new JLabel("Bán: ");
        inLabel.setFont(ProjectUtil.getMyFont(14));
        inLabel.setBounds(18, 350, 50, 25);
        panel.add(inLabel);

        inButton = new ToggleButton();
        inButton.setBounds(70, 350, 50, 30);
        inButton.setSelected(group.getInMenu());
        panel.add(inButton);

        this.imgChooser = new JFileChooser();
        this.imgLabel = new JLabel();
        imgLabel.setBounds(135, 180, 135, 30);
        imgLabel.setFont(ProjectUtil.getMyFont(12));
        imgLabel.setBorder(new LineBorder(Color.BLACK, 1));
        panel.add(imgLabel);

        chooseButton = new Button("File", 60, 30, Color.decode("#333333"));
        chooseButton.setBounds(70, 180, 60, 30);
        chooseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                chooseImg();
            }
        });
        panel.add(chooseButton);

        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean error = false;
                group.setGroupName(nameField.getText());
                if (!"".equals(imgLabel.getText())) {
                    group.setGroupIcon("./img-combo/" + imgChooser.getSelectedFile().getName());
                    ProjectUtil.addImgToFolder("./img-combo", imgChooser.getSelectedFile());
                }
                group.setIN_groupCode(String.valueOf(inGBox.getSelectedItem()).split("-")[0]);
                group.setInMenu(inButton.isSelected());
                if (error) {
                    showError("Giá trị nhập vào không hợp lệ!");
                } else {
                    if (GroupsBus.updateGroup(group) == 1) {
                        showMes();
                        dispose();
                    } else {
                        showError("Đã xảy ra lỗi, hãy thử lại sau!");
                    }
                }
            }

        });
    }

    private void initComponent() {

        panel = new JPanel(null);

        saveButton = new Button("Lưu thay đổi", 100, 40, ProjectUtil.getMyGreenColor());
        saveButton.getTextLabel().setFont(ProjectUtil.getMyFont(15));
        saveButton.setBounds(18, 510, 150, 40);
        closeButton = new Button("Đóng", 100, 40, ProjectUtil.getMyRedColor());
        closeButton.getTextLabel().setFont(ProjectUtil.getMyFont(15));
        closeButton.setBounds(178, 510, 150, 40);

        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
        panel.add(saveButton);
        panel.add(closeButton);

        getContentPane().add(panel);
        setSize(360, 600);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void setImgPanel() {
        this.imgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.setClip(roundedRect);
                g2d.setColor(this.getBackground());
                g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                g2d.draw(roundedRect);
            }
        };
        imgPanel.setBounds(70, 0, 200, 180);
    }

    public void chooseImg() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif");
        imgChooser.setFileFilter(filter);
        int result = imgChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = imgChooser.getSelectedFile().getName();
            img = ProjectUtil.addImg(imgChooser.getSelectedFile().getAbsolutePath());
            chooseButton.setBackground(ProjectUtil.getMyGreenColor());
            imgPanel.repaint();
            imgLabel.setText("   " + filePath);
        } else {
            chooseButton.setBackground(Color.decode("#333333"));
            img = ProjectUtil.addImg(imgString);
            imgPanel.repaint();
            imgLabel.setText("");
        }
    }

    public void showError(String mes) {
        JOptionPane.showMessageDialog(null,
                mes,
                "Đã xảy ra lỗi!",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public void showMes() {
        JOptionPane.showMessageDialog(null,
                "Thao tác thành công!",
                "Thành công!",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    
}
