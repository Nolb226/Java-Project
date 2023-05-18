/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.menuform;

import com.fastfoodstore.bus.GroupsBus;
import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.item.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ADMIN
 */
public class AddProductForm extends JDialog{
    
    private Button addProduct;
    private Button addCombo;
    private Button addGroup;
    private Button addResult;
    private Button closeForm;
    
    private JComboBox<String> genComboBox;
    private JComboBox<String> sizeComboBox;
    private JComboBox<String> inGrComboBox;
    private JTextField nameField;
    private JTextField name1Field;
    private JTextField priceField;
    
    private JPanel contentPanel;
    
    private JPanel imgPanel;
    private BufferedImage img;
    private String imgString = "./landing/landing2.png";
    
    private JFileChooser imgChooser = new JFileChooser();
    private JLabel imgLabel;
    private Button chooseButton;
    
    private int type = 0;
    
    public AddProductForm(JFrame parent) {
        super(parent,"Thêm sản phẩm",true);
        this.img = ProjectUtil.addImg(imgString);
        initComponent();
        initProduct();
    }
    
    private void initComponent() {
        JPanel panel = new JPanel(null);
        
        contentPanel= new JPanel(null);
        contentPanel.setBounds(5, 50, 655, 300);
        panel.add(contentPanel);
        
        JPanel wallPanel = new JPanel();
        wallPanel.setBounds(5, 40, 655, 3);
        wallPanel.setBackground(Color.decode("#aaaaaa"));
        panel.add(wallPanel);
        
        addProduct = new Button("Sản phẩm",80,30,Color.decode("#333333"));
        addProduct.setBounds(5, 5, 80, 30);
        addProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                contentPanel.removeAll();
                initProduct();
                contentPanel.repaint();
                contentPanel.validate();
            }
            
        });
        
        addCombo = new Button("Combo",80,30,Color.decode("#333333"));
        addCombo.setBounds(90, 5, 80, 30);
        addCombo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                contentPanel.removeAll();
                initCombo();
                contentPanel.repaint();
                contentPanel.validate();
            }
            
        });
        
        addGroup = new Button("Nhóm",80,30,Color.decode("#333333"));
        addGroup.setBounds(175, 5, 80, 30);
        addGroup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                contentPanel.removeAll();
                initGroup();
                contentPanel.repaint();
                contentPanel.validate();
            }
            
        });
        
        addResult = new Button("Thêm", 80, 30, ProjectUtil.getMyGreenColor());
        addResult.setBounds(495, 370, 80, 30);
        addResult.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (type) {
                    case 0:
                        System.out.println("add product");
                        break;
                    
                    case 1:
                        System.out.println("add combo");
                        break;
                         
                    case 2:
                        System.out.println("add group");
                        break;
                }
            }
            
        });
        
        closeForm = new Button("Đóng",80,30,ProjectUtil.getMyRedColor());
        closeForm.setBounds(580, 370, 80, 30);
        closeForm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               dispose();
            }
        });
        panel.add(addProduct);
        panel.add(addCombo);
        panel.add(addGroup);
        panel.add(addResult);
        panel.add(closeForm);
        
        setSize(680, 440);
        getContentPane().add(panel);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
    }
    
    private void initProduct() {
        this.type = 0;
        addProduct.setBackground(ProjectUtil.getMyGreenColor());
        addCombo.setBackground(Color.decode("#333333"));
        addGroup.setBackground(Color.decode("#333333")); 
        
        setImgPanel();
        contentPanel.add(imgPanel);
        
        JLabel genreLabel = new JLabel("Loại món: ");
        genreLabel.setFont(ProjectUtil.getMyFont(14)); 
        genreLabel.setBounds(5, 5, 70, 25);
        contentPanel.add(genreLabel);
        
        String[] choices = {"A-Món chính", "P-Món ăn kèm", "D-Thức uống & Tráng miệng"};
        genComboBox = new JComboBox<>(choices);
        genComboBox.setBounds(80, 5, 200, 25);
        contentPanel.add(genComboBox);
        
        JLabel sizeLabel = new JLabel("Kích cỡ");
        sizeLabel.setFont(ProjectUtil.getMyFont(14)); 
        sizeLabel.setBounds(290, 5, 50, 25);
        contentPanel.add(sizeLabel);
        
        String[] choices1 = {"E", "R", "M" , "L"};
        sizeComboBox = new JComboBox<>(choices1);
        sizeComboBox.setBounds(345, 5, 100, 25);
        contentPanel.add(sizeComboBox);
        
        JLabel nameLabel = new JLabel("Tên: ");
        nameLabel.setFont(ProjectUtil.getMyFont(14)); 
        nameLabel.setBounds(5, 50, 70, 25);
        contentPanel.add(nameLabel);
        
        nameField = new JTextField();
        nameField.setBounds(80, 50, 365, 25);
        contentPanel.add(nameField);
        
        JLabel name1Label = new JLabel("Name: ");
        name1Label.setFont(ProjectUtil.getMyFont(14)); 
        name1Label.setBounds(5,95,70, 25);
        contentPanel.add(name1Label);
        
        name1Field = new JTextField();
        name1Field.setBounds(80, 95, 365, 25);
        contentPanel.add(name1Field);
        
        JLabel priceLabel = new JLabel("Giá bán:");
        priceLabel.setFont(ProjectUtil.getMyFont(14)); 
        priceLabel.setBounds(5, 140, 70, 25);
        contentPanel.add(priceLabel);
        
        priceField = new JTextField();
        priceField.setBounds(80, 140, 365, 25);
        contentPanel.add(priceField);
        
        JLabel inGrLabel = new JLabel("Nhóm: ");
        inGrLabel.setFont(ProjectUtil.getMyFont(14)); 
        inGrLabel.setBounds(5, 185, 70, 25);
        contentPanel.add(inGrLabel);
        
        ArrayList<String> items = GroupsBus.getCodeNameGroup();
        inGrComboBox = new JComboBox<>();
        inGrComboBox.setModel(new DefaultComboBoxModel<>(items.toArray(String[]::new)));
        inGrComboBox.setBounds(80, 185, 365, 25);
        contentPanel.add(inGrComboBox);
        
        chooseButton = new Button("File",50,25,Color.decode("#333333"));
        chooseButton.setBounds(80, 230, 50, 25);
        chooseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                chooseImg();
            }
        });
        contentPanel.add(chooseButton);
        
        imgLabel = new JLabel();
        imgLabel.setBounds(135, 230, 310, 25);
        imgLabel.setFont(ProjectUtil.getMyFont(12));
        imgLabel.setBorder(new LineBorder(Color.BLACK, 1));
        contentPanel.add(imgLabel);
        
    }
    
    private void initCombo() {
        this.type = 1;
        addCombo.setBackground(ProjectUtil.getMyGreenColor());
        addProduct.setBackground(Color.decode("#333333"));
        addGroup.setBackground(Color.decode("#333333")); 
        
        setImgPanel();
        contentPanel.add(imgPanel);
        
        JLabel sizeLabel = new JLabel("Kích cỡ");
        sizeLabel.setFont(ProjectUtil.getMyFont(14)); 
        sizeLabel.setBounds(5, 5, 50, 25);
        contentPanel.add(sizeLabel);
        
        String[] choices1 = {"M" , "L"};
        sizeComboBox = new JComboBox<>(choices1);
        sizeComboBox.setBounds(80, 5, 365, 25);
        contentPanel.add(sizeComboBox);
        
        JLabel nameLabel = new JLabel("Tên: ");
        nameLabel.setFont(ProjectUtil.getMyFont(14)); 
        nameLabel.setBounds(5, 50, 70, 25);
        contentPanel.add(nameLabel);
        
        nameField = new JTextField();
        nameField.setBounds(80, 50, 365, 25);
        contentPanel.add(nameField);
        
        JLabel name1Label = new JLabel("Name: ");
        name1Label.setFont(ProjectUtil.getMyFont(14)); 
        name1Label.setBounds(5,95,70, 25);
        contentPanel.add(name1Label);
        
        name1Field = new JTextField();
        name1Field.setBounds(80, 95, 365, 25);
        contentPanel.add(name1Field);
        
        JLabel priceLabel = new JLabel("Giá bán:");
        priceLabel.setFont(ProjectUtil.getMyFont(14)); 
        priceLabel.setBounds(5, 140, 70, 25);
        contentPanel.add(priceLabel);
        
        priceField = new JTextField();
        priceField.setBounds(80, 140, 365, 25);
        contentPanel.add(priceField);
        
        JLabel inGrLabel = new JLabel("Nhóm: ");
        inGrLabel.setFont(ProjectUtil.getMyFont(14)); 
        inGrLabel.setBounds(5, 185, 70, 25);
        contentPanel.add(inGrLabel);
        
        ArrayList<String> items = GroupsBus.getCodeNameGroup();
        inGrComboBox = new JComboBox<>();
        inGrComboBox.setModel(new DefaultComboBoxModel<>(items.toArray(String[]::new)));
        inGrComboBox.setBounds(80, 185, 365, 25);
        contentPanel.add(inGrComboBox);
        
        chooseButton = new Button("File",50,25,Color.decode("#333333"));
        chooseButton.setBounds(80, 230, 50, 25);
        chooseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                chooseImg();
            }
        });
        contentPanel.add(chooseButton);
        
        imgLabel = new JLabel();
        imgLabel.setBounds(135, 230, 310, 25);
        imgLabel.setFont(ProjectUtil.getMyFont(12));
        imgLabel.setBorder(new LineBorder(Color.BLACK, 1));
        contentPanel.add(imgLabel);
    } 
    
    private void initGroup() {
        this.type = 2;
        addGroup.setBackground(ProjectUtil.getMyGreenColor());
        addCombo.setBackground(Color.decode("#333333"));
        addProduct.setBackground(Color.decode("#333333")); 
        
        setImgPanel();
        contentPanel.add(imgPanel);
        
        JLabel nameLabel = new JLabel("Tên: ");
        nameLabel.setFont(ProjectUtil.getMyFont(14)); 
        nameLabel.setBounds(5, 5, 70, 25);
        contentPanel.add(nameLabel);
        
        nameField = new JTextField();
        nameField.setBounds(80, 5, 365, 25);
        contentPanel.add(nameField);
        
        JLabel inGrLabel = new JLabel("Nhóm: ");
        inGrLabel.setFont(ProjectUtil.getMyFont(14)); 
        inGrLabel.setBounds(5, 50, 70, 25);
        contentPanel.add(inGrLabel);
        
        ArrayList<String> items = new ArrayList<>();
        items.add("Không");
        items.addAll(GroupsBus.getCodeNameGroup());
        inGrComboBox = new JComboBox<>();
        inGrComboBox.setModel(new DefaultComboBoxModel<>(items.toArray(String[]::new)));
        inGrComboBox.setBounds(80, 50, 365, 25);
        contentPanel.add(inGrComboBox);
        
        chooseButton = new Button("File",50,25,Color.decode("#333333"));
        chooseButton.setBounds(80, 95, 50, 25);
        chooseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                chooseImg();
            }
        });
        contentPanel.add(chooseButton);
        
        imgLabel = new JLabel();
        imgLabel.setBounds(135, 95, 310, 25);
        imgLabel.setFont(ProjectUtil.getMyFont(12));
        imgLabel.setBorder(new LineBorder(Color.BLACK, 1));
        contentPanel.add(imgLabel);
    }

    private void setImgPanel() {
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
        imgPanel.setBounds(455, 5, 205, 200);
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
    
    
}
