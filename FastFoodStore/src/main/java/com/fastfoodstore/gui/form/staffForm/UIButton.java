/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.staffForm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author k
 */
public class UIButton extends JButton {

    public String text;
    public int type;
    public int size = 2;
    public boolean outline = false;
    private String color = "#000000";

    private final String Primary_Color = "#0d6efd";
    private final String Secondary_Color = "#6c757d";
    private final String Success_Color = "#198754";
    private final String Danger_Color = "#dc3545";
    private final String Warning_Color = "#ffc107";
    private final String Light_Color = "#f8f9fa";
    private final String Dark_Color = "#212529";

    public static final int PRIMARY = 1;
    public static final int SECONDARY = 2;
    public static final int SUCCESS = 3;
    public static final int DANGER = 4;
    public static final int WARNING = 5;
    public static final int LIGHT = 6;
    public static final int DARK = 7;

    public static final int SMALL = 1;
    public static final int NORMAL = 2;
    public static final int LARGE = 3;

    public UIButton(String text, int type, int size, boolean outline) {
        this.text = text;
        this.type = type;
        this.size = size;
        this.outline = outline;
        InitComponent();

    }

    public UIButton(String text, int type, int size) {
        this.text = text;
        this.type = type;
        this.size = size;
        InitComponent();

    }

    public UIButton(String text, int type) {
        this.text = text;
        this.type = type;
        InitComponent();
    }

    public UIButton(String text) {
        this.text = text;
        InitComponent();
    }

    private void InitComponent() {
        setFont(new Font("San serif", Font.PLAIN, 12));
        setText(text);
        InitType();
        InitSize();
        InitOutline();

    }

    private void InitType() {
        setForeground(Color.WHITE);

        switch (type) {

            case 1: {
                setBackground(Color.decode(Primary_Color));
                this.color = Primary_Color;
                break;
            }

            case 2: {
                setBackground(Color.decode(Secondary_Color));
                this.color = Secondary_Color;

                break;
            }

            case 3: {
                setBackground(Color.decode(Success_Color));
                this.color = Success_Color;

                break;
            }
            case 4: {
                setBackground(Color.decode(Danger_Color));
                this.color = Danger_Color;

                break;
            }
            case 5: {
                setBackground(Color.decode(Warning_Color));
                this.color = Warning_Color;
                break;
            }

            case 6: {
                setBackground(Color.decode(Light_Color));
                setForeground(Color.BLACK);
                color = Light_Color;
                break;
            }

            case 7: {
                setBackground(Color.decode(Dark_Color));
                color = Dark_Color;
            }

            default: {
                setForeground(Color.BLACK);
                setBackground(Color.WHITE);
            }
        }
    }

    private void InitOutline() {
        int fontSize = getFont().getSize();
        int Vertical = fontSize - 7;
        int Horizontal = fontSize + 14;
        if (outline) {

//            setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.decode(color), 2, true), new EmptyBorder(5, 26, 5, 26)));
            setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.decode(color), 2, true), new EmptyBorder(Vertical, Horizontal, Vertical, Horizontal)));

            setForeground(Color.decode(color));
            setBackground(Color.WHITE);
            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    setBackground(Color.decode(color));
                    setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(Color.WHITE);
                    setForeground(Color.decode(color));
                }

            });

        } else {
            setBorder(new EmptyBorder(Vertical+2, Horizontal, Vertical+2, Horizontal));
            addMouseListener(new MouseAdapter() {

                public void mouseEntered(MouseEvent e) {
                    setBackground(Color.decode(color).darker());
                }

                public void mouseExited(MouseEvent e) {
                    setBackground(Color.decode(color));
                }

            });
        }
    }

    private void InitSize() {
        switch (size) {
            case 1: {
                setFont(new Font("San serif", Font.PLAIN, 10));
                break;
                
            }
            case 2: {
                setFont(new Font("San serif", Font.PLAIN, 12));
                break;
            }
            case 3: {
                setFont(new Font("San serif", Font.PLAIN, 14));
                break;

            }
        }
    }
}
