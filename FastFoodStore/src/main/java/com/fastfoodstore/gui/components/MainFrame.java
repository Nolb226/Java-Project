/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.components;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    
    private PanelBorder panelBorder;
    private LeftMenu leftMenu;
    private JPanel contentPanel;

    public MainFrame() {
        initComponent();
        setBackground(new Color(0,0,0,0)); 
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
                .addComponent(leftMenu, GroupLayout.PREFERRED_SIZE,250 , GroupLayout.PREFERRED_SIZE)
                .addGap(5,5,5)
                .addComponent(contentPanel,GroupLayout.PREFERRED_SIZE,960,GroupLayout.PREFERRED_SIZE)
                
            )
        );
        panelBorderLayout.setVerticalGroup(
            panelBorderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelBorderLayout.createSequentialGroup()
                .addGroup(panelBorderLayout.createParallelGroup()
                    .addComponent(leftMenu, GroupLayout.PREFERRED_SIZE,680 , GroupLayout.PREFERRED_SIZE)
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
        setBackground(new Color(242,242,242)); 
    }

    public PanelBorder getPanelBorder() {
        return panelBorder;
    }

    public void setLeftMenu() {
        this.leftMenu = new LeftMenu();
    }

    public LeftMenu getLeftMenu() {
        return leftMenu;
    }
    
    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel() {
        this.contentPanel = new JPanel();
        contentPanel.setBackground(Color.red);
    }
    
    
}
