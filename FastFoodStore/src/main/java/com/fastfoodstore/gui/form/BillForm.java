/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form;

import com.fastfoodstore.bus.BillBUS;
import com.fastfoodstore.bus.ComboBUS;
import com.fastfoodstore.bus.ProductsBUS;
import com.fastfoodstore.dto.BillDetail2DTO;
import com.fastfoodstore.dto.BillDetailDTO;
import com.fastfoodstore.dto.BillsDTO;
import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.form.billform.BillDetailList;
import com.fastfoodstore.gui.form.billform.ViewBillList;
import com.fastfoodstore.gui.form.billform.BillDetailItem;
import com.fastfoodstore.gui.item.Button;
import com.fastfoodstore.gui.item.ScrollBar;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class BillForm extends JPanel {

    private JDateChooser dateInput;

    private Button filterButton;

    private JScrollPane scrollPane;
    private ViewBillList viewBillList;

    private ArrayList<BillsDTO> billsDTOs;
    private ArrayList<BillDetailDTO> billDetails1;
    private ArrayList<BillDetail2DTO> billDetails2;

    private BillDetailList billDetailPanel;

    private Button exButton;
    private Button printButton;
    
    private Font f1 = new Font("Segoe UI Semibold", Font.PLAIN, 16);
    private Font f2 = new Font("Segoe UI Semibold", Font.PLAIN, 12);

    public BillForm() {
        initComponent();
        setOpaque(false);
    }

    private void initComponent() {
        setLayout(null);
        setDateInput();
        setFilterButton();
        setViewBillList();
        setBillDetailPanel();
        setPrintButton();
        setExButton();
        add(dateInput);
        add(filterButton);
        add(scrollPane);
        add(billDetailPanel);
        add(printButton);
        add(exButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(0, 0, getWidth() - 20, getHeight());
        super.paintComponent(g);
    }

    public void setDateInput() {
        this.dateInput = new JDateChooser();
        dateInput.setBounds(40, 40, 310, 30);
        dateInput.setDateFormatString("yyyy-MM-dd");
    }

    public void setFilterButton() {
        this.filterButton = new Button("Lọc", 80, 30, Color.decode("#444444"));
        filterButton.setBounds(360, 40, 80, 30);
        filterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String pattern = "yyyy-MM-dd";
                DateFormat formatter = new SimpleDateFormat(pattern);
                if (billsDTOs != null) {
                    billsDTOs.clear();
                }
                setBillsDTOs(BillBUS.selectBillByDate(formatter.format(dateInput.getDate())));
                setData();
            }

        });
    }

    public void setScrollPane() {
        this.scrollPane = new JScrollPane(viewBillList);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBar(new ScrollBar(Color.decode("#333333"), Color.decode("#333333")));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new LineBorder(Color.decode("#444444"), 2));
        scrollPane.setBounds(40, 80, 400, 560);
    }

    public void setBillDetailPanel() {
        this.billDetailPanel = new BillDetailList<>();
        billDetailPanel.setBounds(500, 40, 400, 560);

    }

    public void setViewBillList() {
        this.viewBillList = new ViewBillList<>(this);
        viewBillList.setBackground(Color.decode("#eeeeee"));
        setScrollPane();
    }

    public void setBillsDTOs(ArrayList<BillsDTO> billsDTOs) {
        this.billsDTOs = billsDTOs;
    }

    public void setBillDetails(ArrayList<BillDetailDTO> billDetails) {
        this.billDetails1 = billDetails;
    }

    public void setBillDetails2(ArrayList<BillDetail2DTO> billDetails) {
        this.billDetails2 = billDetails;
    }

    public void setData() {
        viewBillList.removeData();
        if (billsDTOs != null) {
            for (BillsDTO a : billsDTOs) {
                viewBillList.addItem(a);
            }
        }
        repaint();
    }

    public void setData2() {
        billDetailPanel.removeData();
        billDetailPanel.addItem(new BillDetailItem("Mã hóa đơn: " + billsDTOs.get(viewBillList.getMySelectedIndex()).getBillCode()));
        billDetailPanel.addItem(new BillDetailItem("Thời gian: " + billsDTOs.get(viewBillList.getMySelectedIndex()).getDate()));
        billDetailPanel.addItem(new BillDetailItem("-------------------------------"));
        billDetailPanel.addItem(new BillDetailItem("Chi tiết hóa đơn"));
        billDetailPanel.addItem(new BillDetailItem("Tên sản phẩm", "Giá tiền"));
        if (billDetails1 != null) {
            for (BillDetailDTO a : billDetails1) {
                String name = ProductsBUS.getProductsByCode(a.getProductCode()).getProductName().split("/")[0];
                BillDetailItem b = new BillDetailItem(a.getAmountProduct() + "x " + name, ProjectUtil.toMoney(a.getPrice() * a.getAmountProduct()));
                billDetailPanel.addItem(b);
            }
        }
        if (billDetails2 != null) {
            for (BillDetail2DTO a : billDetails2) {
                String name = ComboBUS.getComboByCode(a.getComboCode()).getComboName();
                BillDetailItem b = new BillDetailItem(a.getAmountCombo() + "x " + name, ProjectUtil.toMoney(a.getPrice() * a.getAmountCombo()));
                billDetailPanel.addItem(b);
            }
        }
        billDetailPanel.addItem(new BillDetailItem("-------------------------------"));
        billDetailPanel.addItem(new BillDetailItem("Tổng cộng: ", ProjectUtil.toMoney(billsDTOs.get(viewBillList.getMySelectedIndex()).getTotalPrice()), 3));
        billDetailPanel.repaint();
    }

    public ArrayList<BillsDTO> getBillsDTOs() {
        return billsDTOs;
    }

    public void setPrintButton() {
        this.printButton = new Button("In", 190, 30, Color.decode("#333333"));
        this.printButton.setBounds(710, 610, 190, 30);
    }

    public void setExButton() {
        this.exButton = new Button("Xuất file", 190, 30, Color.decode("#333333"));
        this.exButton.setBounds(500, 610, 190, 30);
        this.exButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    export();
                } catch (IOException ex) {
                    Logger.getLogger(BillForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void export() throws FileNotFoundException, IOException {
        BillsDTO b = new BillsDTO(billsDTOs.get(viewBillList.getMySelectedIndex()));
        String path = "./bill-pdf/" + b.getBillCode() + ".pdf";

        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A6);
        Document document = new Document(pdfDocument);

        float colHeader[] = {520f};
        float colContent[] = {260f,260f};

        Table tableHeader = new Table(colHeader);
        tableHeader.addCell(getTextHeaderPdfCenter("Order number: " + b.getBillCode()));
        tableHeader.addCell(getTextHeaderPdfCenter("Time: " + b.getDate()));
        tableHeader.addCell(getTextHeaderPdfCenter("-------------------------------------------------"));
        
        Table tableContent = new Table(colContent);
        Table tableFooter = new Table(colContent);
        tableContent.addCell(getTextContentPdfLeft("Item",10));
        tableContent.addCell(getTextContentPdfRight("Price",10));
        for(BillDetailDTO a : billDetails1) {
            String name = ProductsBUS.getProductsByCode(a.getProductCode()).getProductName().split("/")[1];
            tableContent.addCell(getTextContentPdfLeft(a.getAmountProduct() + "x " + name,8));
            tableContent.addCell(getTextContentPdfRight( ProjectUtil.toMoney(a.getPrice() * a.getAmountProduct()),8));
        }
        
        for(BillDetail2DTO a : billDetails2) {
            String name = ComboBUS.getComboByCode(a.getComboCode()).getComboName().split("/")[1];
            tableContent.addCell(getTextContentPdfLeft(a.getAmountCombo()+ "x " + name,8));
            tableContent.addCell(getTextContentPdfRight( ProjectUtil.toMoney(a.getPrice() * a.getAmountCombo()),8));
        }
        tableHeader.addCell(new Cell().add(tableContent).setBorder(Border.NO_BORDER)); 
        tableHeader.addCell(getTextHeaderPdfCenter("-------------------------------------------------"));
        
        tableFooter.addCell(getTextContentPdfLeft("Total: ",10));
        tableFooter.addCell(getTextContentPdfRight( ProjectUtil.toMoney(b.getTotalPrice()),10));
        tableHeader.addCell(new Cell().add(tableFooter).setBorder(Border.NO_BORDER)); 
        
        document.add(tableHeader);
        document.close();
        
    }

    public Cell getTextHeaderPdfCenter(String text) {

        return new Cell().add(new Paragraph(text))
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(12)
                .setBorder(Border.NO_BORDER);    
    }
    
    public Cell getTextContentPdfLeft(String text,int size) {
        return new Cell().add(new Paragraph(text))
                .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(size)
                .setBorder(Border.NO_BORDER);
    }
    
    public Cell getTextContentPdfRight(String text,int size) {
        return new Cell().add(new Paragraph(text))
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontSize(size)
                .setBorder(Border.NO_BORDER);
    }

}
