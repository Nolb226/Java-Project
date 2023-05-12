package com.fastfoodstore.gui.components;

import com.fastfoodstore.dto.FunctionsDTO;
import com.fastfoodstore.gui.item.MenuItem;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

public class ListLeftMenu<E extends Object> extends JList<E> {

    private final DefaultListModel model;
    private int selectedIndex = -1;
    private int overIndex = -1;
    private EventMenuSelected event;

    public ListLeftMenu() {
        this.model = new DefaultListModel();
        setModel(model);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int index = locationToIndex(e.getPoint());
                    if(index != getModel().getSize() - 1) {
                        Object o = model.getElementAt(index);
                        if (o instanceof FunctionsDTO) {
                            FunctionsDTO menu = (FunctionsDTO) o;
                            selectedIndex = index;
                            if (event != null) {
                                event.selected(index);
                            }
                        } else {
                            selectedIndex = index;
                        }
                        repaint();
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                overIndex = -1;
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                if(index != getModel().getSize() - 1) {
                    e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
                    if (index != overIndex) {
                        Object o = model.getElementAt(index);
                        if (o instanceof FunctionsDTO) {
                            FunctionsDTO menu = (FunctionsDTO) o;
                            overIndex = index;
                        } else {
                            overIndex = -1;
                        }
                        repaint();
                    }
                } else {
                    overIndex = -1;
                    e.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
                repaint();
            }
        });
    }

    public ListCellRenderer<? super E> getCellRenderer() {

        return new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList<?> jList, Object o, int index, boolean selected, boolean focus) {
                FunctionsDTO data;
                data = (FunctionsDTO) o;
                MenuItem item = new MenuItem(data);
                item.putClientProperty("id", data.getFunctionCode());
                item.setSelected(selectedIndex == index);
                item.setOver(overIndex == index);
                return item;
            }
        };

    }

    public void addItem(FunctionsDTO data) {
        model.addElement(data);
    }
    
    public void removeData() {
        model.removeAllElements();
        clean();
    }

    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
    }
    
    public void clean() {
        selectedIndex = -1;
        overIndex = -1;
        repaint();
    }
}
