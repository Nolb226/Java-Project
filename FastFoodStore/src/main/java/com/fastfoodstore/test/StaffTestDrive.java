/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license   
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.fastfoodstore.test;

import com.fastfoodstore.gui.form.StaffForm;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author k
 */
public class StaffTestDrive {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {    
        // TODO code application logic here
        
        JFrame a = new JFrame();
        a.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        a.add(new StaffForm());
        a.setVisible(true);
        a.pack();
        a.setSize(970,680);
        a.setResizable(false);

//        a.setSize(100,100);
    }
}
