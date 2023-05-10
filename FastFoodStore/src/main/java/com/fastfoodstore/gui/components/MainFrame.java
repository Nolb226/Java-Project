/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.components;

import com.fastfoodstore.gui.form.BillForm;
import com.fastfoodstore.gui.form.ConFirmForm;
import com.fastfoodstore.gui.form.MenuForm;
import com.fastfoodstore.gui.form.OrderForm;
import com.fastfoodstore.gui.form.PackForm;
import com.fastfoodstore.gui.form.StaffForm;
import com.fastfoodstore.gui.form.StatisticsForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

    private PanelBorder panelBorder;
    private LeftMenu leftMenu;
    private JPanel contentPanel;

    private OrderForm orderForm;
    private StaffForm staffForm;
    private MenuForm menuForm;
    private PackForm packForm;
    private StatisticsForm statisticsForm;
    private BillForm billForm;
    private ConFirmForm conFirmForm;

    public MainFrame(PanelBorder panelBorder, LeftMenu leftMenu, JPanel contentPanel) throws HeadlessException {
        this.panelBorder = panelBorder;
        this.leftMenu = leftMenu;
        this.contentPanel = contentPanel;
    }

    public MainFrame() {

        orderForm = new OrderForm();
        staffForm = new StaffForm();
        menuForm = new MenuForm();
        packForm = new PackForm();
        statisticsForm = new StatisticsForm();
        billForm = new BillForm();
        
        initComponent();
        setBackground(new Color(0, 0, 0, 0));
        leftMenu.initMoving(this);
        leftMenu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index >= 0) {
                    JComponent selectedItem = (JComponent) leftMenu.getListMenu().getCellRenderer().getListCellRendererComponent(leftMenu.getListMenu(), leftMenu.getListMenu().getModel().getElementAt(index), index, true, false);
                    String itemId = (String) selectedItem.getClientProperty("id");
                    switch (itemId) {
                        case "FUNC01":
                            setForm(orderForm);
                            break;
                        case "FUNC02":
                            setForm(staffForm);
                            break;
                        case "FUNC03":
                            setForm(menuForm);
                            break;
                        case "FUNC04":
                            setForm(packForm);
                            break;
                        case "FUNC05":
                            setForm(statisticsForm);
                            break;
                        case "FUNC06":
                            setForm(billForm);
                            break;
                        case "FUNC00":
                            conFirmForm = new ConFirmForm(leftMenu); 
                            break;
                        case "EXIT":
                            dispose();
                            break;

                    }
                } else {
                    System.out.println("No item selected.");
                }
            }

        });
        setVisible(true);
    }

    private void initComponent() {
        setSize(1220, 680);
        setUndecorated(true);
        setPanelBorder();
        setLeftMenu();
        setContentPanel();

        GroupLayout panelBorderLayout = new GroupLayout(panelBorder);
        panelBorder.setLayout(panelBorderLayout);
        panelBorderLayout.setHorizontalGroup(
                panelBorderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorderLayout.createSequentialGroup()
                                .addComponent(leftMenu, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 970, GroupLayout.PREFERRED_SIZE)
                        )
        );
        panelBorderLayout.setVerticalGroup(
                panelBorderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorderLayout.createSequentialGroup()
                                .addGroup(panelBorderLayout.createParallelGroup()
                                        .addComponent(leftMenu, GroupLayout.PREFERRED_SIZE, 680, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 680, GroupLayout.PREFERRED_SIZE)
                                )
                        )
        );

        GroupLayout mainLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
                mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panelBorder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainLayout.setVerticalGroup(
                mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panelBorder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        setLocationRelativeTo(null);
    }

    public void setPanelBorder() {
        this.panelBorder = new PanelBorder();
        setBackground(new Color(242, 242, 242));
    }

    public PanelBorder getPanelBorder() {
        return panelBorder;
    }

    public void setLeftMenu() {
        this.leftMenu = new LeftMenu(this);
    }

    public LeftMenu getLeftMenu() {
        return leftMenu;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel() {
        this.contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BorderLayout());
    }

    private void setForm(JComponent form) {
        contentPanel.removeAll();
        contentPanel.add(form);
        contentPanel.repaint();
        contentPanel.validate();
    }
    
}
