package com.fastfoodstore.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ProjectUtil {

    public static void setPicture(JLabel label, String filename) {
        try {
            BufferedImage image = ImageIO.read(new File(filename));
            int x = label.getSize().width;
            int y = label.getSize().height;
            int ix = image.getWidth();
            int iy = image.getHeight();

            int dx = 0;
            int dy = 0;
            if (x / y > ix / iy) {
                dy = y;
                dx = dy * ix / iy;
            } else {
                dx = x;
                dy = dx * iy / ix;
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
    
    public static Color getMyOrangeColor() {
        return Color.decode("#F1A259");
    }

    public static Date stringToDate(String d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = null;
        try {
            date = sdf.parse(d);
        } catch (ParseException ex) {
            Logger.getLogger(ProjectUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

    public static String getRandomNumbers() {
        Random random = new Random();
        int number = random.nextInt(99999);
        return String.format("%05d", number);
    }

    public static String getRandom2Numbers() {
        Random random = new Random();
        int number = random.nextInt(99);
        return String.format("%02d", number);
    }

    public static String getCurrentDateTime() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    public static String getCurrentDate() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        return timeStamp;
    }
    
    public static String toMoney(int money) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String formattedAmount = formatter.format(money);
        return  formattedAmount + " VND";
    }
}
