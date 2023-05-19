package com.fastfoodstore.gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
    
    public static String generateRandomNumbers(int l) {
        Random random = new Random();
        String result = "";
        int num[] = new int[l];
        for(int i=0;i<l;i++) {
            num[i] = random.nextInt(10); 
            result = result + String.valueOf(num[i]);
        }

        return result;
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
    
    public static Font getMyFont(int size) {
        return new Font("Segoe UI Semibold", Font.PLAIN, size);
    }
    
    public static int checkNumber(String num) {
        try {
            int result = Integer.parseInt(num);
            if(result >= 0) {
                return result;
            } else {
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }
    
    public static boolean isImageExtension(String extension) {
        return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif");
    }
    
    public static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return null;
    }
    
    public static void addImgToFolder(String folder, File selectedFile) {
        // Kiểm tra loại file
        String extension = getFileExtension(selectedFile.getName());
        if (extension != null && isImageExtension(extension)) {

            File destinationFolder = new File(folder);

            // Kiểm tra nếu thư mục đích không tồn tại, hãy tạo mới nó
            if (!destinationFolder.exists()) {
                destinationFolder.mkdirs();
            }

            // Đặt tên tệp đích và đường dẫn
            String fileName = selectedFile.getName();
            String destinationPath = destinationFolder.getAbsolutePath() + File.separator + fileName;
            File destinationFile = new File(destinationPath);

            try {
                // Sao chép tệp từ vị trí ban đầu vào thư mục đích
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("cusses.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Selected file is not an image.");
        }
    }
    
    public static void openFile(String filePath) {
        try {
            File file = new File(filePath);
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
}
