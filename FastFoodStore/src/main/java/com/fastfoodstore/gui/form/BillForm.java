/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form;

import com.fastfoodstore.bus.BillBUS;
import com.fastfoodstore.bus.ComboBUS;
import com.fastfoodstore.bus.IngredientBUS;
import com.fastfoodstore.bus.ProductsBUS;
import com.fastfoodstore.bus.ReceiptsBUS;
import com.fastfoodstore.bus.Staff_BUS;
import com.fastfoodstore.dto.BillDetail2DTO;
import com.fastfoodstore.dto.BillDetailDTO;
import com.fastfoodstore.dto.BillsDTO;
import com.fastfoodstore.dto.IngredientDTO;
import com.fastfoodstore.dto.ReceiptDetailDTO;
import com.fastfoodstore.dto.ReceiptsDTO;
import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.form.billform.BillDetailList;
import com.fastfoodstore.gui.form.billform.ViewBillList;
import com.fastfoodstore.gui.form.billform.BillDetailItem;
import com.fastfoodstore.gui.form.billform.ReceiptDetailItem;
import com.fastfoodstore.gui.form.billform.ReceiptDetailList;
import com.fastfoodstore.gui.form.billform.ViewReceiptList;
import com.fastfoodstore.gui.item.Button;
import com.fastfoodstore.gui.item.ScrollBar;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import static com.itextpdf.io.font.PdfEncodings.IDENTITY_H;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
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

    private JDateChooser startDate;
    private JDateChooser endDate;

    private Button filterButton;
    private Button billButton;
    private Button receiptButton;

    private JScrollPane leftScrollPane;
    private ViewBillList viewBillList;

    private ArrayList<BillsDTO> billsDTOs;
    private ArrayList<BillDetailDTO> billDetails1;
    private ArrayList<BillDetail2DTO> billDetails2;

    private JScrollPane rightScrollPane;
    private BillDetailList billDetailList;

    private Button exButton;
    private Button printButton;

    private ArrayList<ReceiptsDTO> receiptsDTOs;
    private ArrayList<ReceiptDetailDTO> receiptDetailDTOs;

    private JScrollPane leftScrollPane2;
    private ViewReceiptList viewReceiptList;

    private JScrollPane rightScrollPane2;
    private ReceiptDetailList receiptDetailList;

//    private ArrayList<IngredientDTO> ingredientDTOs;
    private boolean isBillForm = true;

    private Font f1 = new Font("Segoe UI Semibold", Font.PLAIN, 16);
    private Font f2 = new Font("Segoe UI Semibold", Font.PLAIN, 12);
//    private String fontProgram ;
    private PdfFont vietnameseFont;

    public BillForm() {
        initComponent();
        setOpaque(false);
    }

    private void initComponent() {
        setLayout(null);
        JPanel wallPanel = new JPanel();
        wallPanel.setBounds(35, 65, 900, 3);
        wallPanel.setBackground(Color.decode("#aaaaaa"));
        add(wallPanel);
        setBillButton();
        setReceiptButton();
        setStartDate();
        setEndDate();
        setFilterButton();
        setViewBillList();
        setBillDetailList();
        setPrintButton();
        setExButton();
        add(startDate);
        add(endDate);
        add(filterButton);
        add(leftScrollPane);
        add(rightScrollPane);
        add(printButton);
        add(exButton);
        setBillsDTOs(BillBUS.selectAllBill());
        setReceiptDTOs(ReceiptsBUS.selectAll());
        setData();
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

    public void setStartDate() {
        this.startDate = new JDateChooser();
        startDate.setBounds(40, 75, 150, 30);
        startDate.setDateFormatString("yyyy-MM-dd");
    }

    public void setEndDate() {
        this.endDate = new JDateChooser();
        endDate.setBounds(200, 75, 150, 30);
        endDate.setDateFormatString("yyyy-MM-dd");
    }

    public void setFilterButton() {
        this.filterButton = new Button("Lọc", 80, 30, Color.decode("#444444"));
        filterButton.setBounds(360, 75, 80, 30);
        filterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String pattern = "yyyy-MM-dd";
                DateFormat formatter = new SimpleDateFormat(pattern);
                if (isBillForm) {
                    if (billsDTOs != null) {
                        billsDTOs.clear();
                    }
                    setBillsDTOs(BillBUS.selectBillByDate(
                            startDate.getDate() != null ? formatter.format(startDate.getDate()) : null,
                            endDate.getDate() != null ? formatter.format(endDate.getDate()) : null
                    ));
                    setData();
                } else {
                    if (receiptsDTOs != null) {
                        receiptsDTOs.clear();
                    }
                    setReceiptDTOs(ReceiptsBUS.selectBillByDate(
                            startDate.getDate() != null ? formatter.format(startDate.getDate()) : null,
                            endDate.getDate() != null ? formatter.format(endDate.getDate()) : null
                    ));
                    setData3();
                }
            }

        });
    }

    public void setBillButton() {
        this.billButton = new Button("Hóa đơn", 80, 30, ProjectUtil.getMyGreenColor());
        billButton.setBounds(35, 30, 80, 30);
        billButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isBillForm = true;
                billButton.setBackground(ProjectUtil.getMyGreenColor());
                receiptButton.setBackground(Color.decode("#333333"));
                remove(leftScrollPane2);
                remove(rightScrollPane2);
                add(leftScrollPane);
                add(rightScrollPane);
                setData();
            }

        });
        add(billButton);
    }

    public void setReceiptButton() {
        setViewReceiptList();
        setReceiptDetailList();
        createFont();
        this.receiptButton = new Button("Phiếu nhập", 80, 30, Color.decode("#333333"));
        receiptButton.setBounds(120, 30, 80, 30);
        receiptButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isBillForm = false;
                add(leftScrollPane2);
                add(rightScrollPane2);
                receiptButton.setBackground(ProjectUtil.getMyGreenColor());
                billButton.setBackground(Color.decode("#333333"));
                setData3();
            }
        });
        add(receiptButton);
    }

    public void setLeftScrollPane() {
        this.leftScrollPane = new JScrollPane(viewBillList);
        leftScrollPane.setBorder(null);
        leftScrollPane.setVerticalScrollBar(new ScrollBar(Color.decode("#333333"), Color.decode("#333333")));
        leftScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        leftScrollPane.setBorder(new LineBorder(Color.decode("#444444"), 2));
        leftScrollPane.setBounds(40, 110, 400, 530);
    }

    public void setRightScrollPane() {
        this.rightScrollPane = new JScrollPane(billDetailList);
        rightScrollPane.setBorder(null);
        rightScrollPane.setVerticalScrollBar(new ScrollBar(Color.decode("#333333"), Color.decode("#333333")));
        rightScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        rightScrollPane.setBounds(500, 75, 400, 525);
    }

    public void setBillDetailList() {
        this.billDetailList = new BillDetailList<>();
        billDetailList.setBounds(500, 70, 400, 530);
        setRightScrollPane();
    }

    public void setViewBillList() {
        this.viewBillList = new ViewBillList<>(this);
        viewBillList.setBackground(Color.decode("#eeeeee"));
        setLeftScrollPane();
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
        validate();
        repaint();
    }

    public void setData2() {
        billDetailList.removeData();
        billDetailList.addItem(new BillDetailItem("Mã hóa đơn: " + billsDTOs.get(viewBillList.getMySelectedIndex()).getBillCode()));
        billDetailList.addItem(new BillDetailItem("Thời gian: " + billsDTOs.get(viewBillList.getMySelectedIndex()).getDate()));
        billDetailList.addItem(new BillDetailItem("Vị trí: " + billsDTOs.get(viewBillList.getMySelectedIndex()).getLocation()));
        if (billsDTOs.get(viewBillList.getMySelectedIndex()).getStaffId() != null) {
            billDetailList.addItem(new BillDetailItem("Mã nhân viên: " + billsDTOs.get(viewBillList.getMySelectedIndex()).getStaffId()));
        }
        billDetailList.addItem(new BillDetailItem("-------------------------------"));
        billDetailList.addItem(new BillDetailItem("Chi tiết hóa đơn"));
        billDetailList.addItem(new BillDetailItem("Tên sản phẩm", "Giá tiền"));
        if (billDetails1 != null) {
            for (BillDetailDTO a : billDetails1) {
                String name = ProductsBUS.getProductsByCode(a.getProductCode()).getProductName().split("/")[0];
                BillDetailItem b = new BillDetailItem(a.getAmountProduct() + "x " + name, ProjectUtil.toMoney(a.getPrice() * a.getAmountProduct()));
                billDetailList.addItem(b);
            }
        }
        if (billDetails2 != null) {
            for (BillDetail2DTO a : billDetails2) {
                String name = ComboBUS.getComboByCode(a.getComboCode()).getComboName().split("/")[0];
                BillDetailItem b = new BillDetailItem(a.getAmountCombo() + "x " + name, ProjectUtil.toMoney(a.getPrice() * a.getAmountCombo()));
                billDetailList.addItem(b);
            }
        }
        billDetailList.addItem(new BillDetailItem("-------------------------------"));
        billDetailList.addItem(new BillDetailItem("Tổng cộng: ", ProjectUtil.toMoney(billsDTOs.get(viewBillList.getMySelectedIndex()).getTotalPrice()), 3));
        billDetailList.repaint();
    }

    public void setData3() {

        remove(leftScrollPane);
        remove(rightScrollPane);
//        if (viewReceiptList != null) {
        viewReceiptList.removeData();
//        }
        if (receiptsDTOs != null) {
//            System.out.println("a");
            for (ReceiptsDTO a : receiptsDTOs) {
                viewReceiptList.addItem(a);
            }
        }
        validate();
        repaint();
    }

    public void setData4() {
        receiptDetailList.removeData();
        receiptDetailList.addItem(new ReceiptDetailItem("PHIẾU NHẬP KHO"));
        receiptDetailList.addItem(new ReceiptDetailItem("Thời gian: " + receiptsDTOs.get(viewReceiptList.getMySelectedIndex()).getDate()));
        receiptDetailList.addItem(new ReceiptDetailItem("Mã phiếu nhập: " + receiptsDTOs.get(viewReceiptList.getMySelectedIndex()).getReceiptCode()));
//        receiptDetailList.addItem(new ReceiptDetailItem("Vị trí: " + receiptsDTOs.get(viewReceiptList.getMySelectedIndex()).getLocation()));
        if (receiptsDTOs.get(viewReceiptList.getMySelectedIndex()).getStaffId() != null) {
            receiptDetailList.addItem(new ReceiptDetailItem("Người lập phiếu: " + new Staff_BUS().getOne(receiptsDTOs.get(viewReceiptList.getMySelectedIndex()).getStaffId()).getName(), ""));
        }
        receiptDetailList.addItem(new ReceiptDetailItem("Nhập tại: Fast Food Store", ""));
        receiptDetailList.addItem(new ReceiptDetailItem("---------------------------------------"));
        receiptDetailList.addItem(new ReceiptDetailItem("Tên sản phẩm", "Thành tiền", "Giá", "Số lượng"));

        for (ReceiptDetailDTO a : receiptDetailDTOs) {

            ReceiptDetailItem b = new ReceiptDetailItem(IngredientBUS.getIngredientByCode(a.getIngredientCode()).getIngredientName(), ProjectUtil.toMoney(a.getPrice()),
                    ProjectUtil.toMoney(IngredientBUS.getIngredientByCode(a.getIngredientCode()).getCost()), Integer.toString((int) a.getAmountInReceipt()));
            receiptDetailList.addItem(b);
        }

        receiptDetailList.addItem(new ReceiptDetailItem("---------------------------------------"));
        receiptDetailList.addItem(new ReceiptDetailItem("Tổng cộng: ", ProjectUtil.toMoney((int) receiptsDTOs.get(viewReceiptList.getMySelectedIndex()).getTotalPrice()), "", ""));
        receiptDetailList.addItem(new ReceiptDetailItem("", "Ngày " + receiptsDTOs.get(viewReceiptList.getMySelectedIndex()).getDateString().split("-")[2] + " tháng " + receiptsDTOs.get(viewReceiptList.getMySelectedIndex()).getDateString().split("-")[1] + " năm " + receiptsDTOs.get(viewReceiptList.getMySelectedIndex()).getDateString().split("-")[0] + " "));
        receiptDetailList.addItem(new ReceiptDetailItem("Người lập phiếu ", "Kế toán trưởng", "Người giao hàng", "Thủ kho"));
        receiptDetailList.addItem(new ReceiptDetailItem("(Ký, họ tên) ", "(Ký, họ tên)", "(Ký, họ tên)", "(Ký, họ tên)", 4));
        receiptDetailList.addItem(new ReceiptDetailItem(""));
        receiptDetailList.addItem(new ReceiptDetailItem(""));
        receiptDetailList.repaint();
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
                if (isBillForm) {
                    try {
                        ProjectUtil.openFile(export());
                    } catch (IOException ex) {
                        Logger.getLogger(BillForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        ProjectUtil.openFile(export2());
                    } catch (IOException ex) {
                        Logger.getLogger(BillForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public String export() throws FileNotFoundException, IOException {
        BillsDTO b = new BillsDTO(billsDTOs.get(viewBillList.getMySelectedIndex()));
        String path = "./bill-pdf/" + b.getBillCode() + ".pdf";

        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A6);
        Document document = new Document(pdfDocument);

        float colHeader[] = {520f};
        float colContent[] = {260f, 260f};

        Table tableHeader = new Table(colHeader);
        tableHeader.addCell(getTextHeaderPdfCenter("Order number: " + b.getBillCode()));
        tableHeader.addCell(getTextHeaderPdfCenter("Time: " + b.getDate()));
        tableHeader.addCell(getTextHeaderPdfCenter("-------------------------------------------------"));

        Table tableContent = new Table(colContent);
        Table tableFooter = new Table(colContent);
        tableContent.addCell(getTextContentPdfLeft("Item", 10));
        tableContent.addCell(getTextContentPdfRight("Price", 10));
        if (billDetails1 != null) {
            for (BillDetailDTO a : billDetails1) {
                String name = ProductsBUS.getProductsByCode(a.getProductCode()).getProductName().split("/")[1];
                tableContent.addCell(getTextContentPdfLeft(a.getAmountProduct() + "x " + name, 8));
                tableContent.addCell(getTextContentPdfRight(ProjectUtil.toMoney(a.getPrice() * a.getAmountProduct()), 8));
            }
        }

        if (billDetails2 != null) {
            for (BillDetail2DTO a : billDetails2) {
                String name = ComboBUS.getComboByCode(a.getComboCode()).getComboName().split("/")[1];
                tableContent.addCell(getTextContentPdfLeft(a.getAmountCombo() + "x " + name, 8));
                tableContent.addCell(getTextContentPdfRight(ProjectUtil.toMoney(a.getPrice() * a.getAmountCombo()), 8));
            }
        }

        tableHeader.addCell(new Cell().add(tableContent).setBorder(Border.NO_BORDER));
        tableHeader.addCell(getTextHeaderPdfCenter("-------------------------------------------------"));

        tableFooter.addCell(getTextContentPdfLeft("Total: ", 10));
        tableFooter.addCell(getTextContentPdfRight(ProjectUtil.toMoney(b.getTotalPrice()), 10));
        tableHeader.addCell(new Cell().add(tableFooter).setBorder(Border.NO_BORDER));

        document.add(tableHeader);
        document.close();

        return path;
    }

    public Cell getTextHeaderPdfCenter(String text) {

        return new Cell().add(new Paragraph(text))
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(12)
                .setBorder(Border.NO_BORDER);
    }

    public Cell getTextContentPdfLeft(String text, int size) {
        return new Cell().add(new Paragraph(text))
                .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(size)
                .setBorder(Border.NO_BORDER);
    }

    public Cell getTextContentPdfRight(String text, int size) {
        return new Cell().add(new Paragraph(text))
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontSize(size)
                .setBorder(Border.NO_BORDER);
    }

    //receipt
    public void setLeftScrollPane2() {
        this.leftScrollPane2 = new JScrollPane(viewReceiptList);
        leftScrollPane2.setBorder(null);
        leftScrollPane2.setVerticalScrollBar(new ScrollBar(Color.decode("#333333"), Color.decode("#333333")));
        leftScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        leftScrollPane2.setBorder(new LineBorder(Color.decode("#444444"), 2));
        leftScrollPane2.setBounds(40, 110, 400, 530);
    }

    public void setRightScrollPane2() {
        this.rightScrollPane2 = new JScrollPane(receiptDetailList);
        rightScrollPane2.setBorder(null);
        rightScrollPane2.setVerticalScrollBar(new ScrollBar(Color.decode("#333333"), Color.decode("#333333")));
        rightScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        rightScrollPane2.setBounds(450, 75, 500, 525);
    }

    public void setReceiptDetailList() {
        this.receiptDetailList = new ReceiptDetailList<>();
        receiptDetailList.setBounds(500, 70, 400, 530);
        setRightScrollPane2();
    }

    public void setViewReceiptList() {
        this.viewReceiptList = new ViewReceiptList<>(this);
        viewReceiptList.setBackground(Color.decode("#eeeeee"));
        setLeftScrollPane2();
    }

    public void setReceiptDTOs(ArrayList<ReceiptsDTO> receiptsDTOs) {
        this.receiptsDTOs = receiptsDTOs;
    }

    public void setReceiptDetailDTOs(ArrayList<ReceiptDetailDTO> receiptDetailDTOs) {
        this.receiptDetailDTOs = receiptDetailDTOs;
    }

    public ArrayList<ReceiptsDTO> getReceiptDTOs() {
        return receiptsDTOs;
    }

    public String export2() throws FileNotFoundException, IOException {
        ReceiptsDTO b = new ReceiptsDTO(receiptsDTOs.get(viewReceiptList.getMySelectedIndex()));
        String path = "./receipt-pdf/" + b.getReceiptCode() + ".pdf";

        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A5);
        Document document = new Document(pdfDocument);

        float colHeader[] = {620f};
        float colContent[] = {155f, 155f, 155f, 155f};
        float colDate[] = {320f, 320f};

        Table tableHeader = new Table(colHeader);
        tableHeader.addCell(getTextHeaderPdfCenter2("PHIẾU NHẬP KHO"));
        tableHeader.addCell(getTextHeaderPdfCenter2("Thời gian: " + b.getDateString()));
        tableHeader.addCell(getTextHeaderPdfCenter2("Mã phiếu nhập: " + b.getReceiptCode()));

        Table tableContent = new Table(colContent);
        Table tableFooter = new Table(colContent);
        tableHeader.addCell(getTextContentPdfLeft2("Người lập phiếu: " + new Staff_BUS().getOne(b.getStaffId()).getName(), 12));
        tableHeader.addCell(getTextContentPdfLeft2("Nhập tại: Fast Food Store", 12));
//        tableContent.addCell(getTextContentPdfLeft2("", 12));
//        tableContent.addCell(getTextContentPdfLeft2("", 12));
//        tableContent.addCell(getTextContentPdfLeft2("", 12));
        tableHeader.addCell(getTextHeaderPdfCenter2("-------------------------------------------------"));
        tableContent.addCell(getTextContentPdfLeft2("Tên sản phẩm:", 12));
        tableContent.addCell(getTextContentPdfMid("Giá", 12));
        tableContent.addCell(getTextContentPdfMid("Số lượng", 12));
        tableContent.addCell(getTextContentPdfRight2("Thành tiền", 12));

        for (ReceiptDetailDTO a : receiptDetailDTOs) {
            tableContent.addCell(getTextContentPdfLeft2(IngredientBUS.getIngredientByCode(a.getIngredientCode()).getIngredientName(), 10));
            tableContent.addCell(getTextContentPdfMid(Integer.toString(IngredientBUS.getIngredientByCode(a.getIngredientCode()).getCost()), 10));
            tableContent.addCell(getTextContentPdfMid(Integer.toString((int) a.getAmountInReceipt()), 10));
            tableContent.addCell(getTextContentPdfRight2(ProjectUtil.toMoney(a.getPrice()), 10));
        }

        tableHeader.addCell(new Cell().add(tableContent).setBorder(Border.NO_BORDER));
        tableHeader.addCell(getTextHeaderPdfCenter("-------------------------------------------------"));

        tableFooter.addCell(getTextContentPdfLeft2("Tổng: ", 10));
        tableFooter.addCell(getTextContentPdfLeft2("", 10));
        tableFooter.addCell(getTextContentPdfLeft2("", 10));
        tableFooter.addCell(getTextContentPdfRight2(ProjectUtil.toMoney((int) b.getTotalPrice()), 10));
        tableHeader.addCell(new Cell().add(tableFooter).setBorder(Border.NO_BORDER));

        Table tableDate = new Table(colDate);
        tableDate.addCell(getTextContentPdfLeft2("", 10));
        tableDate.addCell(getTextContentPdfRight2("Ngày " + b.getDateString().split("-")[2] + " tháng " + b.getDateString().split("-")[1] + " năm " + b.getDateString().split("-")[0] + " ", 9));
        tableHeader.addCell(new Cell().add(tableDate).setBorder(Border.NO_BORDER));

        Table tableconfirm = new Table(colContent);
        tableconfirm.addCell(getTextContentPdfMid("Người lập phiếu", 10));
        tableconfirm.addCell(getTextContentPdfMid("Người giao hàng", 10));
        tableconfirm.addCell(getTextContentPdfMid("Thủ kho", 10));
        tableconfirm.addCell(getTextContentPdfMid("Kế toán trưởng", 10));
        tableconfirm.addCell(getTextContentPdfMid("(Ký,họ tên)", 8));
        tableconfirm.addCell(getTextContentPdfMid("(Ký,họ tên)", 8));
        tableconfirm.addCell(getTextContentPdfMid("(Ký,họ tên)", 8));
        tableconfirm.addCell(getTextContentPdfMid("(Ký,họ tên)", 8));
        tableconfirm.addCell(getTextContentPdfMid("", 8));
        tableconfirm.addCell(getTextContentPdfMid("", 8));
        tableconfirm.addCell(getTextContentPdfMid("", 8));
        tableconfirm.addCell(getTextContentPdfMid("", 8));

        tableHeader.addCell(new Cell().add(tableconfirm).setBorder(Border.NO_BORDER));

        document.add(tableHeader);
        document.close();

        return path;
    }

    public Cell getTextHeaderPdfCenter2(String text) {

        return new Cell().add(new Paragraph(text))
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(14)
                .setBorder(Border.NO_BORDER)
                .setFont(vietnameseFont);
    }

    public Cell getTextContentPdfLeft2(String text, int size) {
        return new Cell().add(new Paragraph(text))
                .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(size)
                .setBorder(Border.NO_BORDER)
                .setFont(vietnameseFont);

    }

    public Cell getTextContentPdfRight2(String text, int size) {
        return new Cell().add(new Paragraph(text))
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontSize(size)
                .setBorder(Border.NO_BORDER)
                .setFont(vietnameseFont);

    }

    public Cell getTextContentPdfMid(String text, int size) {
        return new Cell().add(new Paragraph(text))
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(size)
                .setBorder(Border.NO_BORDER)
                .setFont(vietnameseFont);

    }

    public void createFont() {
        try {
//            FontProgram fontProgram = FontProgramFactory.createFont(FontConstants.TIMES_ROMAN);
            vietnameseFont = PdfFontFactory.createFont("./Font/font-times-new-roman/font-times-new-roman.ttf", PdfEncodings.IDENTITY_H);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
