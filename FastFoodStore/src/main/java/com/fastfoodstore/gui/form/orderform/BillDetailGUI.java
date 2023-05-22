/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form.orderform;

import com.fastfoodstore.bus.BillBUS;
import com.fastfoodstore.bus.BillDetailBUS;
import com.fastfoodstore.dto.BillDetail2DTO;
import com.fastfoodstore.dto.BillDetailDTO;
import com.fastfoodstore.dto.BillsDTO;
import com.fastfoodstore.dto.ComboDTO;
import com.fastfoodstore.dto.ProductsDTO;
import com.fastfoodstore.dto.StaffDTO;
import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.form.ComFirmForm2;
import com.fastfoodstore.gui.form.NoticeForm;
import com.fastfoodstore.gui.item.Button;
import com.fastfoodstore.gui.item.ScrollBar;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class BillDetailGUI extends JPanel {

    private JLabel textArea;

    private Button toPayButton;
    private Button deleteButton;

    private JScrollPane billJScrollPane;
    private ListBillDetail<String> billListPanel;

    private JPanel totalPanel;
    private JLabel totalLabel;
    private JLabel priceLabel;

    private BillStatus eatIn;
    private BillStatus takeOut;

    private int totalPrice = 0;
    private boolean ordering = false;

    private ArrayList<Object> billList = new ArrayList<>();

    private BillsDTO bill;
    private StaffDTO staff;

    private Font f = new Font("Segoe UI Semibold", Font.PLAIN, 12);

    public BillDetailGUI(StaffDTO staff) {
        this.staff = staff;
        initComponent();
        setOpaque(false);
    }

    private void initComponent() {
        setLayout(null);
        setBackground(Color.decode("#333333"));
        setTextArea();
        setBillListPanel();
        setTotalPanel();
        setToPayButton();
        setDeleteButton();
        setEatIn();
        setTakeOut();
        add(textArea);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(g);
    }

    public void setTextArea() {
        this.textArea = new JLabel();
        textArea.setText("<html><body style='text-align:center'>Gọi món tại đây!<br>Order here!!!</body></html>");
        textArea.setHorizontalAlignment(JLabel.CENTER);
        textArea.setVerticalAlignment(JLabel.CENTER);
        textArea.setFont(new Font("San serif", Font.BOLD, 14));
        textArea.setForeground(Color.decode("#eeeeee"));
        textArea.setBounds(75, 10, 150, 60);
    }

    public void setToPayButton() {
        this.toPayButton = new Button("Thanh toán", 145, 39, 238, 238, 238);
        toPayButton.getTextLabel().setForeground(ProjectUtil.getMyGreenColor());
        toPayButton.setBounds(155, 360, 125, 30);
        toPayButton.getTextLabel().setFont(new Font("San serif", Font.BOLD, 14));
        toPayButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!billList.isEmpty()) {
                    ComFirmForm2 comFirmForm2 = new ComFirmForm2("thanh toán");
                    int result = comFirmForm2.show();
                    if (result == 1) {
                        finishOrder();
                    }
                }
            }

        });
    }

    public void setDeleteButton() {
        this.deleteButton = new Button("Hủy đơn hàng", 145, 39, 238, 238, 238);
        deleteButton.getTextLabel().setForeground(ProjectUtil.getMyRedColor());
        deleteButton.setBounds(20, 360, 125, 30);
        deleteButton.getTextLabel().setFont(new Font("San serif", Font.BOLD, 14));
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!billList.isEmpty() && billListPanel.getMySelectedIndex() > -1) {
                    billList.remove(billListPanel.getMySelectedIndex());
                    billListPanel.removeItem();
                    setTotalPrice();
                    if (billList.size() < 1) {
                        deleteButton.setContent("Hủy đơn hàng");
                    }
                } else if (billList.size() < 1) {
                    ComFirmForm2 comFirmForm2 = new ComFirmForm2("hủy đơn hàng");
                    int result = comFirmForm2.show();
                    if (result == 1) {
                        deleteOrder();
                    }
                }
            }
        });
    }

    public void setBillJScrollPane() {
        this.billJScrollPane = new JScrollPane(billListPanel);
        billJScrollPane.setBorder(null);
        billJScrollPane.setVerticalScrollBar(new ScrollBar(Color.decode("#333333"), Color.decode("#333333")));
        billJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        billJScrollPane.setBackground(Color.decode("#333333"));
        billJScrollPane.setBounds(20, 70, 260, 250);
    }

    public void setBillListPanel() {
        this.billListPanel = new ListBillDetail<>();
        this.billListPanel.setBackground(Color.decode("#333333"));
        setBillJScrollPane();
    }

    public void setTotalPanel() {
        this.totalPanel = new JPanel();
        totalPanel.setBounds(25, 325, 250, 25);
        totalPanel.setBackground(Color.decode("#333333"));
        totalPanel.setLayout(null);
        setTotalLabel();
        setPriceLabel();
        totalPanel.add(totalLabel);
        totalPanel.add(priceLabel);
    }

    public void setTotalLabel() {
        this.totalLabel = new JLabel("Tổng cộng: ", JLabel.LEFT);
        totalLabel.setForeground(Color.decode("#eeeeee"));
        totalLabel.setBounds(0, 0, 150, 25);

    }

    public void setPriceLabel() {
        this.priceLabel = new JLabel("", JLabel.RIGHT);
        priceLabel.setForeground(Color.decode("#eeeeee"));
        priceLabel.setBounds(150, 0, 100, 25);
    }

    public void setTotalPrice() {
        this.totalPrice = 0;
        int totalProduct = 0;
        for (Object a : billList) {
            if (a instanceof BillDetailDTO b) {
                totalPrice += b.getPrice() * b.getAmountProduct();
                totalProduct += b.getAmountProduct();
            }
            if (a instanceof BillDetail2DTO b) {
                totalPrice += b.getPrice() * b.getAmountCombo();
                totalProduct += b.getAmountCombo();
            }
        }
        bill.setTotalNumber(totalProduct);
        priceLabel.setText(ProjectUtil.toMoney(totalPrice));
    }

    public ArrayList<Object> getBillList() {
        return billList;
    }

    public void setBill(String id, String status) {
        if(staff != null) {
            this.bill = new BillsDTO(
                id, 
                ProjectUtil.getCurrentDateTime(), 
                0, 
                0, 
                null, 
                status, 
                "Order Counter", 
                staff.getID());
        } else {
            this.bill = new BillsDTO(
                id, 
                ProjectUtil.getCurrentDateTime(), 
                0, 
                0, 
                null, 
                status, 
                "Order Kiost Host", 
                null);
        }
        
    }

    public void setEatIn() {
        this.eatIn = new BillStatus("./icon/eat-in.png", "Eat In");
        eatIn.setBounds(60, 120, 80, 140);
        add(eatIn);
        eatIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setUpOrder("Eat In");
            }

        });
    }

    public void setTakeOut() {
        this.takeOut = new BillStatus("./icon/take-away.png", "Take Out");
        takeOut.setBounds(160, 120, 80, 140);
        takeOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setUpOrder("Take Out");
            }
        });
        add(takeOut);
    }

    public void setOrdering(boolean ordering) {
        this.ordering = ordering;
    }

    public boolean isOrdering() {
        return ordering;
    }

    public void addData1(ProductsDTO data) {
        billListPanel.removeData();

        totalPrice += data.getProductPrice();

        BillDetailDTO a = new BillDetailDTO(bill.getBillCode(), data.getProductCode(), "", 1, data.getProductPrice());
        if (checkBill(a.getProductCode())) {
            for (int i = 0; i < billList.size(); i++) {
                if (billList.get(i) instanceof BillDetailDTO) {
                    if (((BillDetailDTO) billList.get(i)).getProductCode().equals(data.getProductCode())) {
                        ((BillDetailDTO) billList.get(i)).addAmount();
                    }
                }
            }
        } else {
            billList.add(a);
        }

        for (Object bd : billList) {
            billListPanel.addItem(bd);
        }
        setTotalPrice();
        bill.setTotalPrice(totalPrice);
        deleteButton.setContent("Xóa");
    }

    public void addData2(ComboDTO data) {
        billListPanel.removeData();

        totalPrice += data.getComboPrice();

        BillDetail2DTO a = new BillDetail2DTO(bill.getBillCode(), data.getComboCode(), "", 1, data.getComboPrice());
        if (checkBill(a.getComboCode())) {
            for (int i = 0; i < billList.size(); i++) {
                if (billList.get(i) instanceof BillDetail2DTO) {
                    if (((BillDetail2DTO) billList.get(i)).getComboCode().equals(data.getComboCode())) {
                        ((BillDetail2DTO) billList.get(i)).addAmount();
                    }
                }
            }
        } else {
            billList.add(a);
        }

        for (Object bd : billList) {
            billListPanel.addItem(bd);
        }
        setTotalPrice();
        bill.setTotalPrice(totalPrice);
        deleteButton.setContent("Xóa");
    }

    public boolean checkBill(String code) {
        boolean same = false;
        for (Object a : billList) {
            if (a instanceof BillDetailDTO) {
                if (((BillDetailDTO) a).getProductCode().equals(code)) {
                    same = true;
                    break;
                }
            } else {
                if (((BillDetail2DTO) a).getComboCode().equals(code)) {
                    same = true;
                    break;
                }
            }
        }
        return same;
    }

    public void setUpOrder(String status) {
        setBill(ProjectUtil.getCurrentDate() + ProjectUtil.getRandomNumbers(), status);
        setOrdering(true);
        textArea.setText("<html><body style='text-align:center'>Đơn hàng của bạn có chính xác không?</body></html>");
        remove(eatIn);
        remove(takeOut);
        add(billJScrollPane);
        add(totalPanel);
        add(toPayButton);
        add(deleteButton);
        repaint();
    }

    public void finishOrder() {
        try {
            BillBUS.insertBill(bill);
            bill = null;
            for (Object detail : billList) {
                BillDetailBUS.insertBillDetail(detail);
            }
            NoticeForm noticeForm = new NoticeForm("Thanh toán thành công", 1);
        } catch (Exception e) {
            NoticeForm noticeForm = new NoticeForm("Đã xảy ra lỗi! Hãy thử lại.", 0);
        }
        billList.clear();
        deleteOrder();
    }

    public void deleteOrder() {
        setOrdering(false);
        textArea.setText("<html><body style='text-align:center'>Gọi món tại đây!<br>Order here!!!</body></html>");
        billListPanel.removeData();
        remove(billJScrollPane);
        remove(totalPanel);
        remove(toPayButton);
        remove(deleteButton);
        add(eatIn);
        add(takeOut);
        repaint();
    }

    public BillsDTO getBill() {
        return bill;
    }

}
