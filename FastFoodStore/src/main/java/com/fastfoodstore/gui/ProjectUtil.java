
package com.fastfoodstore.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ProjectUtil {
    public static void setPicture(JLabel label, String filename){
          try {
            BufferedImage image = ImageIO.read(new File(filename));
            int x =label.getSize().width;
            int y =label.getSize().height;
            int ix =image.getWidth();
            int iy =image.getHeight();

            int dx=0;
            int dy=0;
            if(x /y > ix /iy){
                dy=y;
                dx=dy*ix /iy;
            }else{
                dx=x;
                dy=dx*iy/ix;
            }

            ImageIcon icon = new ImageIcon(image.getScaledInstance(dx, dy, BufferedImage.SCALE_SMOOTH));
            label.setIcon(icon);
        } catch (IOException ex) {
              System.out.println("Set picture failture: " + ex);
        }

    }
    
    public static BufferedImage addImg(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public static Color getMyGreenColor() {
        return new Color(25, 184, 41);
    }
    
    public static Color getMyRedColor() {
        return new Color(220, 13, 13);
    }
    
    public static Color getMyGrayColor() {
        return Color.decode("#eeeeee"); 
    }
}
