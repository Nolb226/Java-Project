/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.components;

import com.fastfoodstore.bus.FunctionsBUS;
import com.fastfoodstore.dto.FunctionsDTO;
import com.fastfoodstore.gui.ProjectUtil;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeftMenu extends JPanel{
    
    private JPanel movingPanel;
    private JLabel iconLabel;
    private int x;
    private int y;
    private ListLeftMenu<String> listMenu;
    private String pass;
    
    private EventMenuSelected event;
    
    private MainFrame mainFrame;

    public LeftMenu(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponent();
        setOpaque(false);
        listMenu.setOpaque(false);
        setData("");
    }
    
    private void setData(String pass) {
        listMenu.removeData();
        ArrayList<FunctionsDTO> functionList = FunctionsBUS.getGroupFunctionList(pass); 
        for(FunctionsDTO item : functionList) {
            listMenu.addItem(item); 
        }
        listMenu.addItem(new FunctionsDTO("FUNC00","Cài đặt","./icon/settings.png"));
        listMenu.addItem(new FunctionsDTO("EXIT","Thoát","./icon/exit.png"));
        listMenu.addItem(new FunctionsDTO("","",""));
        
    }

    private void initComponent() {
        setMovingPanel();
        setIconLabel();
        setListMenu();
        ProjectUtil.setPicture(iconLabel, "./landing/landing1.png");
        
        GroupLayout movingPanelLayout = new GroupLayout(movingPanel);
        movingPanel.setLayout(movingPanelLayout);
        movingPanelLayout.setHorizontalGroup(
            movingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(movingPanelLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(iconLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25,25)
            )
        );
        movingPanelLayout.setVerticalGroup(
            movingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(movingPanelLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(iconLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
            )        
        );
        
        GroupLayout mainLayout = new GroupLayout(this);
        setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(movingPanel, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            .addGroup(mainLayout.createSequentialGroup()
                .addComponent(listMenu, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addComponent(movingPanel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addGap(30)
                .addComponent(listMenu, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            )
        );
        
    }
    
    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0,0,Color.decode("#FF7337"),getWidth(),getHeight(),Color.decode("#FF9900"));
        g2.setPaint(gp); 
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(g); 
    }

    public void setMovingPanel() {
        this.movingPanel = new JPanel();
        setOpaque(false);
        movingPanel.setBackground(new Color(0,0,0,0));
    }
    
   
    public void setIconLabel() {
        this.iconLabel = new JLabel();
        iconLabel.setSize(200, 120);
    }

    public void setListMenu() {
        this.listMenu = new ListLeftMenu<>();
    }

    public ListLeftMenu<String> getListMenu() {
        return listMenu;
    }
    
    public void initMoving(JFrame mainFrame) {
        movingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
        
        movingPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mainFrame.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
            }
            
        });
    }
    
    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
        listMenu.addEventMenuSelected(event); 
    }

    public void setPass(String pass) {
        this.pass = pass;
        setData(pass); 
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
    
}
