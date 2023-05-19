/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui;

import static com.itextpdf.kernel.pdf.PdfName.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ASUS
 */
public class Validate {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String SDT_REGEX = "^0[3|5|7|8|9]\\d{8}$";

    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static Boolean checkSDT(String s) {
        boolean check = s.matches(SDT_REGEX);
        return check;
    }

    public static boolean checkDay(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = sdf.parse(s);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }
    
    public static Boolean checkNumber(String s){
        return s.matches("-?\\d+(\\.\\d+)?");
    }

}
