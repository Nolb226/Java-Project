/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.components;

import com.fastfoodstore.dto.ProductsDTO;
import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.item.BillItem;
import com.fastfoodstore.gui.item.Button;
import com.fastfoodstore.gui.item.ScrollBar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class BillDetailGUI extends JPanel {

    private JLabel textArea;
    
    private Button toPayButton;
    
    private JScrollPane billJScrollPane;
    private JList<BillItem> billListPanel;
    
    private ArrayList<BillItem> billItems = new ArrayList<>();
    
    private ArrayList<ProductsDTO> productList = new ArrayList<>();
    
    DefaultListModel<BillItem> model = new DefaultListModel<>();

    public BillDetailGUI() {
        initComponent();
        setOpaque(false);
    }

    private void initComponent() {
        setLayout(null);
        setBackground(Color.decode("#333333"));
        setTextArea();
        setBillListPanel();
        setToPayButton();
        add(textArea);
        add(billJScrollPane);
        add(toPayButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(g);
    }

    public void setTextArea() {
        this.textArea = new JLabel();
        textArea.setText("<html><body style='text-align:center'>Đơn hàng của bạn có chính xác không?</body></html>");
        textArea.setHorizontalAlignment(JLabel.CENTER);
        textArea.setVerticalAlignment(JLabel.CENTER);
        textArea.setFont(new Font("San serif",Font.BOLD,14)); 
        textArea.setForeground(Color.decode("#eeeeee")); 
        textArea.setBounds(75, 10, 150, 60);
    }

    public void setToPayButton() {
        this.toPayButton = new Button("Tiến hành thanh toán", 145, 39, 238, 238, 238);
        toPayButton.getTextLabel().setForeground(ProjectUtil.getMyGreenColor()); 
        toPayButton.setBounds(20, 360, 260, 30);
        toPayButton.getTextLabel().setFont(new Font("San serif",Font.BOLD,14)); 
    }

    public void setBillJScrollPane() {
        this.billJScrollPane = new JScrollPane(billListPanel);
        billJScrollPane.setBorder(null);
        billJScrollPane.setVerticalScrollBar(new ScrollBar(Color.decode("#333333"), Color.decode("#333333"))); 
        billJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        billJScrollPane.setBounds(20, 70 , 260, 270);
    }

    public void setBillListPanel() {
        this.billListPanel = new JList<>(model);
        this.billListPanel.setBackground(Color.decode("#333333")); 
        setBillJScrollPane();
    }
    
    public void addData(ProductsDTO data) {
        
        model.removeAllElements();
        
        productList.add(data);
        billItems.add(new BillItem(data));
        
        for(BillItem a : billItems) {
            this.model.addElement(a);
        }
        
        billListPanel.validate();
        billListPanel.repaint();
    }
    
}
