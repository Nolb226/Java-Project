/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fastfoodstore.gui.form;

import com.fastfoodstore.bus.IngredientBUS;
import com.fastfoodstore.bus.ProductDetailBUS;
import com.fastfoodstore.bus.ProductsBUS;
import com.fastfoodstore.bus.ReceiptDetailBUS;
import com.fastfoodstore.bus.ReceiptsBUS;
import com.fastfoodstore.dto.IngredientDTO;
import com.fastfoodstore.dto.ProductDetailDTO;
import com.fastfoodstore.dto.ProductsDTO;
import com.fastfoodstore.dto.ReceiptDetailDTO;
import com.fastfoodstore.dto.ReceiptsDTO;
import com.fastfoodstore.gui.ProjectUtil;
import com.fastfoodstore.gui.components.IngredientList;
import com.fastfoodstore.gui.item.Button;
import com.fastfoodstore.gui.item.ScrollBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class PackForm extends JPanel {

    private JTextField validate;

    private JPanel headerPanel = new JPanel();
    private JPanel leftPanel;
    private JPanel rightPanel;
    private Button receitComponent;
    private Button productDetailComponent;

    private JTable table;
    private Button addIngredient;
    private Button receipIngredient;
    private Button submitReceip;
    private Button deleteIngredient;
    private JPanel formReceip;
    private JLabel totalPriceField;
    private Button subIngreButton;
    private Button addIngreButton;

    private JPanel formEdit;
    private JTextField ingredientId;
    private JTextField ingredientName;
    private JTextField ingredientAmount;
    private JTextField ingredientPrice;
    private int isIngredient = -1;
    private Button updateButton;
    private Button addButton;
    private Button editButton;
    private boolean checkInput;

    private JScrollPane ingredientList;
    private IngredientList<String> List;

    private ArrayList<IngredientDTO> listIngredientDTOs = new ArrayList<>();
    private JPanel menuComponent;
    private ArrayList<IngredientDTO> billReceiptList = new ArrayList<>();
    private DefaultTableModel model;
    private boolean isFormReceipt = false;
//  Nguyên liệuForm
    private JTable tableProductDetail;
    private DefaultTableModel ProductDetailModel;
    private ArrayList<ProductDetailDTO> productDetailList = new ArrayList<>();
    private ArrayList<ProductsDTO> productsList = new ArrayList<>();
    private JPanel formEditProductDetail;
    private Button SubmitButton;
    private Button addProductDetailButton;
    private Button editProductDetailButton;
    private Button deleteProductDetailButton;
    private JComboBox<String> productNameBox;
    private JComboBox<String> ingredientNameBox;
    private JTextField recipeField;
    private int isProductDetail = -1;

    public PackForm() {
        initComponent();
        setOpaque(false);
    }

    private void initComponent() {
        //left
        leftPanel = new JPanel();
        setLayout(new BorderLayout());

        //right
        rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(400, 610));

        //header
        setReceiptBtn();
        setProductDetailBtn();
        headerPanel.add(receitComponent);
        headerPanel.add(productDetailComponent);
        this.add(headerPanel, BorderLayout.NORTH);

        Dimension preferredSize = new Dimension(570, 0);
        leftPanel.setPreferredSize(preferredSize);

        setTable();
        this.add(leftPanel, BorderLayout.WEST);

        setRightPanel();
        this.add(rightPanel, BorderLayout.EAST);

        JPanel emty = new JPanel();
        emty.setPreferredSize(new Dimension(0, 30));
        this.add(emty, BorderLayout.SOUTH);
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

    private void setReceiptBtn() {
        receitComponent = new Button("Kho hàng", 100, 35, 5, 213, 245);
        receitComponent.setColorText(Color.BLACK);
        receitComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                receitComponent.setThisColor(Color.decode("#05D5F5"));
                productDetailComponent.setThisColor(Color.white);
                leftPanel.removeAll();
                rightPanel.removeAll();
                setTable();
                setRightPanel();
                validate();
                repaint();
            }
        });
    }

    private void setProductDetailBtn() {
        productDetailComponent = new Button("Công thức", 100, 35, Color.WHITE);
        productDetailComponent.setColorText(Color.BLACK);
        productDetailComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                receitComponent.setThisColor(Color.WHITE);
                productDetailComponent.setThisColor(Color.decode("#05D5F5"));
                leftPanel.removeAll();
                rightPanel.removeAll();
                setProductDetailForm();
                validate();
                repaint();
            }
        });
    }

    private void createTable() {
        String[] columnNames = {"Mã nguyên liệu", "Tên nguyên liệu", "Số lượng", "Giá"};
        model.setRowCount(0);
        model.setColumnIdentifiers(columnNames);
        listIngredientDTOs.clear();
        listIngredientDTOs = IngredientBUS.getAllIngredient();
        for (IngredientDTO a : listIngredientDTOs) {
            Object objectList[] = {a.getIngredientCode(), a.getIngredientName(), a.getAmount(), a.getCost()};
//            System.out.println(a.getIngredientCode());
            model.addRow(objectList);
        }
        table = new JTable(model);
        table.setShowHorizontalLines(false);

        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        table.revalidate();
        table.repaint();
    }

    private void setTable() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Chỉ cho phép chỉnh sửa cột Age
                return column == 5;
            }
        };
        createTable();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(570, 600));
//        leftPanel.removeAll();
        leftPanel.add(scroll);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (isFormReceipt) {
                    setData(row);
                } else {
                    setData2(row);
                }
            }

        });
    }

    private void setRightPanel() {
        setFormReceip();
        setFormEdit();
        setMenu();
        rightPanel.add(formEdit, BorderLayout.NORTH);
        rightPanel.add(menuComponent, BorderLayout.SOUTH);
    }

    private void setIngredientList() {
        ingredientList = new JScrollPane();
//        setListenKey();
        ingredientList.setBounds(5, 50, 380, 400);
        ingredientList.setBorder(null);
        ingredientList.setVerticalScrollBar(new ScrollBar(Color.decode("#333333"), Color.decode("#333333")));
        ingredientList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ingredientList.setViewportView(List);
    }

    private void addList(int select) {
        List.removeData(select);
        for (IngredientDTO a : billReceiptList) {
            List.addItem(a);
        }
        List.setSelectedIndex(select);
        updateTotalPrice();
        validate();
        repaint();
    }

    private void setList() {
        List = new IngredientList<>();
        setIngredientList();
    }

    private void setData(int index) {
        boolean check = true;
        if (isFormReceipt) {
            if (billReceiptList.size() != 0) {
                for (int i = 0; i < billReceiptList.size(); i++) {
                    if (billReceiptList.get(i).getIngredientCode().equals(listIngredientDTOs.get(index).getIngredientCode())) {
                        billReceiptList.get(i).addAmount();
                        check = false;
                    }
                }

                if (check) {
                    billReceiptList.add(listIngredientDTOs.get(index));
                    billReceiptList.get(billReceiptList.size() - 1).setAmount(1);
                }
            } else {
                billReceiptList.add(listIngredientDTOs.get(index));
                billReceiptList.get(billReceiptList.size() - 1).setAmount(1);
            }
            addList(-1);
        }
    }

    private void setData2(int index) {
        if (isIngredient != -2) {
            isIngredient = index;
            ingredientId.setText(listIngredientDTOs.get(index).getIngredientCode());
            ingredientName.setText(listIngredientDTOs.get(index).getIngredientName());
            ingredientAmount.setText(Integer.toString(listIngredientDTOs.get(index).getAmount()));
            ingredientPrice.setText(Integer.toString(listIngredientDTOs.get(index).getCost()));

            validate();
            repaint();
        }
    }

    private void removeList() {
        List.removeData(-1);
        billReceiptList.removeAll(billReceiptList);
        updateTotalPrice();
        validate();
        repaint();
    }

    private void setSunmitBtn() {
        submitReceip = new Button("Thanh toán", 100, 50, ProjectUtil.getMyGreenColor());
        submitReceip.setBounds(20, 10, 100, 30);
        submitReceip.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (billReceiptList.size() != 0) {
                    String code = "";
                    do {
                        code = ProjectUtil.getCurrentDate() + ProjectUtil.getRandom2Numbers();
                    } while (ReceiptsBUS.checkReceipt(code));

                    float sum = 0;
                    ArrayList<ReceiptDetailDTO> submit = new ArrayList<>();
                    for (IngredientDTO a : billReceiptList) {
                        ReceiptDetailDTO temp = new ReceiptDetailDTO(code, a.getIngredientCode(), a.getAmount(), a.getAmount() * a.getCost());
                        submit.add(temp);
                        sum += temp.getPrice();
                    }

                    try {
                        ReceiptsDTO receipt = new ReceiptsDTO(code, ProjectUtil.stringToDate(ProjectUtil.getCurrentDateTime()), sum);

                        ReceiptsBUS.insertReceipt(receipt);
                        for (ReceiptDetailDTO b : submit) {
                            ReceiptDetailBUS.insert(b);
                        }
                        for (IngredientDTO c : billReceiptList) {
                            IngredientBUS.updateAmout(c);
                        }

                        JFrame frame = new JFrame("JOptionPane showMessageDialog example");
                        JOptionPane.showMessageDialog(frame,
                                "Thanh toán thành công",
                                "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        createTable();
                        removeList();
                    } catch (Exception error) {
                        JFrame frame = new JFrame("JOptionPane showMessageDialog example");
                        JOptionPane.showMessageDialog(frame,
                                "Thanh toán thất bại",
                                "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JFrame frame = new JFrame("JOptionPane showMessageDialog example");
                    JOptionPane.showMessageDialog(frame,
                            "Không có nguyên liệu để thanh toán",
                            "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
    }

    private void setDeleteBtn() {
        deleteIngredient = new Button("Xóa", 100, 50, ProjectUtil.getMyRedColor());
        deleteIngredient.setBounds(150, 10, 100, 30);
        deleteIngredient.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int a = List.getSelectedIndex();

                if (a != -1) {
                    billReceiptList.remove(billReceiptList.get(a));
                    updateTotalPrice();
                    addList(-1);
                }
            }
        });

    }

    private void setTotalPrice() {
        totalPriceField = new JLabel("Tổng :  ");
        totalPriceField.setOpaque(true);
        totalPriceField.setBackground(Color.WHITE);
        totalPriceField.setBounds(250, 449, 135, 30);
    }

    private void updateTotalPrice() {
        if (billReceiptList.size() == 0) {
            totalPriceField.setText("Tổng:  ");
        } else {
            int sum = 0;
            for (IngredientDTO a : billReceiptList) {
                sum += a.getCost() * a.getAmount();
            }
            totalPriceField.setText("Tổng:   " + Integer.toString(sum));
        }
    }

    private void setAddIngreBtn() {
        addIngreButton = new Button("+", 30, 30, 169, 169, 169);
        addIngreButton.setBounds(35, 450, 30, 30);
        addIngreButton.setColorText(Color.BLACK);
        addIngreButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int a = List.getSelectedIndex();
                if (a != -1) {
                    billReceiptList.get(a).addAmount();
                    addList(a);
                }
            }
        });
    }

    private void setSubIngreBtn() {
        subIngreButton = new Button("-", 30, 30, 169, 169, 169);
        subIngreButton.setBounds(5, 450, 30, 30);
        subIngreButton.setColorText(Color.BLACK);
        subIngreButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int a = List.getSelectedIndex();
                if (a != -1) {
                    if (billReceiptList.get(a).getAmount() > 0) {
                        billReceiptList.get(a).removeAmount();
                        addList(a);
//                        List.setSelectedIndex(a);
                    }
                }
            }
        });
    }

    private void setFormReceip() {
        formReceip = new JPanel(null);
        JPanel head = new JPanel(new GridLayout(1, 4, 0, 10));
        JLabel label1 = new JLabel("Mã nguyên liệu");

        JLabel label2 = new JLabel("Tên nguyên liệu");

        JLabel label3 = new JLabel("Số lượng");

        JLabel label4 = new JLabel("Giá");

        head.add(label1);
        head.add(label2);
        head.add(label3);
        head.add(label4);
        head.setBounds(5, 0, 380, 50);

        formReceip.setPreferredSize(new Dimension(400, 550));
        formReceip.add(head);

//        Mid
        setList();
        formReceip.add(ingredientList);
//        formReceip.setBackground(Color.red);

        JPanel button = new JPanel(null);
        button.setBounds(5, 480, 380, 50);
        setDeleteBtn();
        setSunmitBtn();

        button.add(submitReceip);
        button.add(deleteIngredient);
        formReceip.add(button);

        setTotalPrice();
        formReceip.add(totalPriceField);

        setAddIngreBtn();
        setSubIngreBtn();
        formReceip.add(addIngreButton);
        formReceip.add(subIngreButton);
    }

    private void setReceipIngredient() {
        receipIngredient = new Button("Nhập nguyên liệu", 100, 50, ProjectUtil.getMyGreenColor());
        receipIngredient.setBounds(180, 10, 150, 40);
        receipIngredient.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                rightPanel.add(formReceip, BorderLayout.NORTH);
                rightPanel.remove(formEdit);
                isFormReceipt = true;
                validate();
                repaint();
            }
        });
    }

    private void setAddIngredient() {
        addIngredient = new Button("Thêm, sửa nguyên liệu", 100, 50, ProjectUtil.getMyGreenColor());
        addIngredient.setBounds(10, 10, 150, 40);
        addIngredient.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                rightPanel.remove(formReceip);
                rightPanel.add(formEdit, BorderLayout.NORTH);
                isFormReceipt = false;
                validate();
                repaint();
            }
        });
    }

    private void setMenu() {
        menuComponent = new JPanel(null);
        menuComponent.setPreferredSize(new Dimension(400, 60));
        setAddIngredient();
        setReceipIngredient();
        menuComponent.add(addIngredient);
        menuComponent.add(receipIngredient);
    }

    private void setUpdateBtn() {
        updateButton = new Button("Lưu", 100, 40, 5, 213, 245);
        updateButton.setBounds(150, 250, 100, 30);
        updateButton.setFontTextLabel(new Font("Arial", Font.PLAIN, 15));
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isIngredient != -1) {
                    if (isIngredient == -2) {
                        if (checkInput) {
                            IngredientDTO newIngre = new IngredientDTO(ingredientId.getText(),
                                    ingredientName.getText(),
                                    Integer.parseInt(ingredientAmount.getText()),
                                    Integer.parseInt(ingredientPrice.getText()));
                            try {
                                IngredientBUS.insertIngredient(newIngre);
                                JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"),
                                        "Thêm nguyên liệu thành công",
                                        "Thông báo",
                                        JOptionPane.INFORMATION_MESSAGE);
                                createTable();
                                setAddForm();
                            } catch (Exception lException) {
                                JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"),
                                        "Thêm nguyên liệu thất bại",
                                        "Thông báo",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JFrame frame = new JFrame("JOptionPane showMessageDialog example");
                            JOptionPane.showMessageDialog(frame,
                                    "Thông tin nguyên liệu mới không được để trống",
                                    "Thông báo",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        IngredientDTO Ingre = new IngredientDTO(ingredientId.getText(),
                                ingredientName.getText(),
                                Integer.parseInt(ingredientAmount.getText()),
                                Integer.parseInt(ingredientPrice.getText()));
                        try {
                            IngredientBUS.update(Ingre);
                            System.out.println(Ingre.getCost());
                            JFrame frame = new JFrame("JOptionPane showMessageDialog example");
                            JOptionPane.showMessageDialog(frame,
                                    "Cập nhật thành công",
                                    "Thông báo",
                                    JOptionPane.INFORMATION_MESSAGE);
                            resetInput();
                            createTable();
                        } catch (Exception a) {
                            JFrame frame = new JFrame("JOptionPane showMessageDialog example");
                            JOptionPane.showMessageDialog(frame,
                                    "Cập nhật thất bại",
                                    "Thông báo",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JFrame frame = new JFrame("JOptionPane showMessageDialog example");
                    JOptionPane.showMessageDialog(frame,
                            "Chưa chọn sản phẩm để cập nhật",
                            "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void resetInput() {
        ingredientId.setText("");
        ingredientName.setText("");
        ingredientAmount.setText("");
        ingredientPrice.setText("");
        isIngredient = -1;
    }

    private void setAddForm() {
        resetInput();
        String code = String.format("IN%03d", listIngredientDTOs.size() + 1);
        ingredientId.setText(code);
        ingredientAmount.setText("0");
        ingredientAmount.setEnabled(false);
        isIngredient = -2;
    }

    private void setAddBtn() {
        addButton = new Button("Thêm", 50, 30, Color.WHITE);
        addButton.setBounds(10, 0, 50, 30);
        addButton.setColorText(Color.black);
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                editButton.setThisColor(Color.white);
                addButton.setThisColor(Color.decode("#05D5F5"));
                updateButton.setContent("Thêm");
                setAddForm();
                checkValidate();
                isIngredient = -2;
                validate();
                repaint();
            }
        });
    }

    private void setEditBtn() {
        editButton = new Button("Sửa", 50, 30, 5, 213, 245);
        editButton.setBounds(60, 0, 50, 30);
        editButton.setColorText(Color.black);
        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                addButton.setThisColor(Color.white);
                editButton.setThisColor(Color.decode("#05D5F5"));
                updateButton.setContent("Lưu");
                ingredientAmount.setEnabled(true);
                isIngredient = -1;
                resetInput();
                formEdit.remove(validate);
                validate();
                repaint();
            }
        });
    }

    private void checkValidate() {
        if (ingredientName.getText().isEmpty()) {
            validate.setText("Tên nguyên liệu không được để trống");
            formEdit.add(validate);
            checkInput = false;
        } else {
            if (ingredientAmount.getText().isEmpty()) {
                validate.setText("Số lượng không được để trống");
                formEdit.add(validate);
                checkInput = false;
            } else {
                if (!ingredientAmount.getText().matches("-?\\d+(\\.\\d+)?")) {
                    validate.setText("Số lượng phải là số");
                    formEdit.add(validate);
                    checkInput = false;
                } else {
                    if (Integer.parseInt(ingredientAmount.getText()) < 0 || Integer.parseInt(ingredientAmount.getText()) > 999) {
                        validate.setText("Số lượng phải lớn hơn bằng 0 và bé hơn 999");
                        formEdit.add(validate);
                        checkInput = false;
                    } else {
                        if (ingredientPrice.getText().isEmpty()) {
                            validate.setText("Giá tiền không được để trống");
                            formEdit.add(validate);
                            checkInput = false;
                        } else {
                            if (!ingredientPrice.getText().matches("-?\\d+(\\.\\d+)?")) {
                                validate.setText("Giá tiền phải là số");
                                formEdit.add(validate);
                                checkInput = false;
                            } else {
                                if (Integer.parseInt(ingredientPrice.getText()) < 0 || Integer.parseInt(ingredientPrice.getText()) > 1000000) {
                                    validate.setText("Giá tiền phải lớn hơn 0 và bé hơn 1000000");
                                    formEdit.add(validate);
                                    checkInput = false;
                                } else {
                                    formEdit.remove(validate);
                                    checkInput = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void setIngredientId() {
        ingredientId = new JTextField();
        ingredientId.setBounds(180, 50, 150, 30);
        ingredientId.setEnabled(false);
    }

    private void setIngredientName() {
        ingredientName = new JTextField();
        ingredientName.setBounds(180, 100, 150, 30);
        ingredientName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                checkValidate();
                validate();
                repaint();
            }
        });

        ingredientName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent event) {
                checkValidate();
                validate();
                repaint();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkValidate();
                validate();
                repaint();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkValidate();
                validate();
                repaint();
            }
        });
    }

    private void setIngredientAmount() {
        ingredientAmount = new JTextField();
        ingredientAmount.setBounds(180, 150, 150, 30);
        ingredientAmount.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                checkValidate();
                validate();
                repaint();
            }
        });

        ingredientAmount.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent event) {
                checkValidate();
                validate();
                repaint();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkValidate();
                validate();
                repaint();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkValidate();
                validate();
                repaint();
            }
        });
    }

    private void setIngredientPrice() {
        ingredientPrice = new JTextField();
        ingredientPrice.setBounds(180, 200, 150, 30);
        ingredientPrice.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                checkValidate();
                validate();
                repaint();
            }
        });
        ingredientPrice.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent event) {
                checkValidate();
                validate();
                repaint();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkValidate();
                validate();
                repaint();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkValidate();
                validate();
                repaint();
            }
        });
    }

    private void setFormEdit() {
        Font a = new Font("Arial", Font.PLAIN, 14);

        validate = new JTextField();
        validate.setBackground(new Color(255, 0, 0, 100));
        validate.setFont(new Font("Arial", Font.PLAIN, 16));
        validate.setHorizontalAlignment(JTextField.CENTER);

        formEdit = new JPanel(null);
        JLabel ID = new JLabel("Mã nguyên liệu");
        JLabel name = new JLabel("Tên Nguyên Liệu");
        JLabel amount = new JLabel("Số lượng");
        JLabel price = new JLabel("Giá");

        ID.setFont(a);
        name.setFont(a);
        amount.setFont(a);
        price.setFont(a);

        ID.setBounds(10, 50, 150, 40);
        name.setBounds(10, 100, 150, 40);
        amount.setBounds(10, 150, 150, 40);
        price.setBounds(10, 200, 150, 40);
        formEdit.add(ID);
        formEdit.add(name);
        formEdit.add(amount);
        formEdit.add(price);

        setIngredientId();
        setIngredientName();
        setIngredientAmount();
        setIngredientPrice();

        formEdit.add(ingredientId);
        formEdit.add(ingredientName);
        formEdit.add(ingredientAmount);
        formEdit.add(ingredientPrice);

        setUpdateBtn();
        formEdit.add(updateButton);
        setAddBtn();
        formEdit.add(addButton);
        setEditBtn();
        formEdit.add(editButton);

        validate.setBounds(50, 350, 300, 150);
        formEdit.setPreferredSize(new Dimension(400, 510));

    }

    //Form Công thức.
    private void createTableProcductDetail() {
        String[] columnNames = {"Tên sản phẩm", "Tên nguyên liệu", "Số lượng"};
        ProductDetailModel.setRowCount(0);
        ProductDetailModel.setColumnIdentifiers(columnNames);
        productDetailList.clear();
        productDetailList = ProductDetailBUS.getProductDetail();
        for (ProductDetailDTO a : productDetailList) {
            Object objectList[] = {a.getProductName(), a.getIngredientName(), a.getRecipe()};
//            System.out.println(a.getIngredientCode());
            ProductDetailModel.addRow(objectList);
        }
        tableProductDetail = new JTable(ProductDetailModel);
        tableProductDetail.setShowHorizontalLines(false);

        tableProductDetail.setFont(new Font("Arial", Font.PLAIN, 14));
        tableProductDetail.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 16));
        tableProductDetail.setRowHeight(30);
        tableProductDetail.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = tableProductDetail.rowAtPoint(e.getPoint());
                setData3(row);
            }

        });
        tableProductDetail.revalidate();
        tableProductDetail.repaint();
    }

    private void setData3(int index) {
//        System.out.println(index);
        if (index != -1) {
            isProductDetail = index;
            for (int i = 0; i < productsList.size(); i++) {
                if (productsList.get(i).getProductCode().equals(productDetailList.get(index).getProductCode())) {
                    productNameBox.setSelectedIndex(i);
                }
            }
            for (int j = 0; j < listIngredientDTOs.size(); j++) {
                if (listIngredientDTOs.get(j).getIngredientCode().equals(productDetailList.get(index).getIngredientCode())) {
                    ingredientNameBox.setSelectedIndex(j);
                }
            }
            recipeField.setText(productDetailList.get(index).getRecipe());
        }
    }

    private void setAddProductDetaiBtn() {
        addProductDetailButton = new Button("Thêm", 50, 30, Color.WHITE);
        addProductDetailButton.setBounds(10, 0, 50, 30);
        addProductDetailButton.setColorText(Color.black);
        addProductDetailButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                editProductDetailButton.setThisColor(Color.white);
                addProductDetailButton.setThisColor(Color.decode("#05D5F5"));
                SubmitButton.setContent("Thêm");
                validate();
                repaint();
            }
        });
    }

    private void setEditProductDetail() {
        editProductDetailButton = new Button("Sửa", 50, 30, 5, 213, 245);
        editProductDetailButton.setBounds(60, 0, 50, 30);
        editProductDetailButton.setColorText(Color.black);
        editProductDetailButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                addProductDetailButton.setThisColor(Color.white);
                editProductDetailButton.setThisColor(Color.decode("#05D5F5"));
                SubmitButton.setContent("Lưu");
                validate();
                repaint();
            }
        });
    }

    private void setSubmitProductDetail() {
        SubmitButton = new Button("Lưu", 100, 40, 5, 213, 245);
        SubmitButton.setBounds(10, 250, 100, 30);
        SubmitButton.setFontTextLabel(new Font("Arial", Font.PLAIN, 15));
        SubmitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (recipeField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"),
                            "Ô số lượng không được để trống",
                            "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    System.out.println(isProductDetail);
                    if (isProductDetail == -1) {
                        ProductDetailDTO newProductDetail = new ProductDetailDTO(productsList.get(productNameBox.getSelectedIndex()).getProductCode(),
                                listIngredientDTOs.get(ingredientNameBox.getSelectedIndex()).getIngredientCode(),
                                recipeField.getText(), false, "", "");
                        try {
                            ProductDetailBUS.insertProductDetail(newProductDetail);
                            JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"),
                                    "Thêm chi tiết của sản phẩm thành công",
                                    "Thông báo",
                                    JOptionPane.INFORMATION_MESSAGE);
                            createTableProcductDetail();
                        } catch (Exception l) {
                            JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"),
                                    "Thêm chi tiết của sản phẩm thất bại",
                                    "Thông báo",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        productDetailList.get(isProductDetail).setRecipe(recipeField.getText());
                        try {
                            ProductDetailBUS.updateProductDetail(productDetailList.get(isProductDetail));
                            JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"),
                                    "Cập nhật tiết của sản phẩm thành công",
                                    "Thông báo",
                                    JOptionPane.INFORMATION_MESSAGE);
                            createTableProcductDetail();

                        } catch (Exception l) {
                            JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"),
                                    "Cập nhật tiết của sản phẩm thất bại",
                                    "Thông báo",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
    }

    private void setDeleteProductDetail() {
        deleteProductDetailButton = new Button("Xóa", 100, 40, ProjectUtil.getMyRedColor());
        deleteProductDetailButton.setBounds(130, 250, 100, 30);
        deleteProductDetailButton.setFontTextLabel(new Font("Arial", Font.PLAIN, 15));
        deleteProductDetailButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(isProductDetail);
                if (isProductDetail == -1) {
                    JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"),
                            "Vui lòng chọn đối tượng xóa",
                            "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);;
                } else {
                    try {
                        ProductDetailBUS.deleteProductDetail(productDetailList.get(isProductDetail));
                        JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"),
                                "Xóa tiết của sản phẩm thành công",
                                "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        createTableProcductDetail();
                    } catch (Exception l) {
                        JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"),
                                "Xóa tiết của sản phẩm thất bại",
                                "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    private void checkComboBox() {
        boolean check = false;
        for (int i = 0; i < productDetailList.size(); i++) {
            if (productDetailList.get(i).getProductCode().equals(productsList.get(productNameBox.getSelectedIndex()).getProductCode())) {
                for (int j = 0; j < listIngredientDTOs.size(); j++) {
                    if (productDetailList.get(i).getIngredientCode().equals(listIngredientDTOs.get(ingredientNameBox.getSelectedIndex()).getIngredientCode())) {
                        recipeField.setText(productDetailList.get(i).getRecipe());
                        isProductDetail = i;
                        check = true;
                    } else {
                        if (!check) {
                            isProductDetail = -1;
                        }
                    }
                }
            }
        }
//        System.out.println(isProductDetail);
    }

    private void setProductNameBox() {
        productNameBox = new JComboBox<>();
        productNameBox.setBounds(120, 50, 180, 30);
//        productNameBox.setEnabled(false);
        productsList = ProductsBUS.getAllProducts();
//        ArrayList<String> option = new ArrayList<>();
        for (ProductsDTO a : productsList) {
            productNameBox.addItem(a.getProductName());
        }

        productNameBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    checkComboBox();
                }
            }
        });

    }

    private void setIngredientNameBox() {
        ingredientNameBox = new JComboBox<>();
        ingredientNameBox.setBounds(120, 100, 180, 30);
//        productNameBox.setEnabled(false);
        for (IngredientDTO a : listIngredientDTOs) {
            ingredientNameBox.addItem(a.getIngredientName());
        }

        ingredientNameBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    checkComboBox();
                }
            }
        });
    }

    private void setRecipeField() {
        recipeField = new JTextField();
        recipeField.setBounds(120, 150, 180, 30);
    }

    private void setFormEditProductDetail() {
        Font a = new Font("Arial", Font.PLAIN, 14);

        validate = new JTextField();
        validate.setBackground(new Color(255, 0, 0, 100));
        validate.setFont(new Font("Arial", Font.PLAIN, 16));
        validate.setHorizontalAlignment(JTextField.CENTER);

        formEditProductDetail = new JPanel(null);
        JLabel product = new JLabel("Sản phẩm");
        JLabel ingredient = new JLabel("Nguyên liệu");
        JLabel amount = new JLabel("Số lượng");

        product.setFont(a);
        ingredient.setFont(a);
        amount.setFont(a);

        product.setBounds(10, 50, 150, 40);
        ingredient.setBounds(10, 100, 150, 40);
        amount.setBounds(10, 150, 150, 40);
        formEditProductDetail.add(product);
        formEditProductDetail.add(ingredient);
        formEditProductDetail.add(amount);

        setSubmitProductDetail();
        formEditProductDetail.add(SubmitButton);
//        setAddProductDetaiBtn();
//        formEditProductDetail.add(addProductDetailButton);
//        setEditProductDetail();
//        formEditProductDetail.add(editProductDetailButton);
        setDeleteProductDetail();
        formEditProductDetail.add(deleteProductDetailButton);

        setProductNameBox();
        formEditProductDetail.add(productNameBox);
        setIngredientNameBox();
        formEditProductDetail.add(ingredientNameBox);
        setRecipeField();
        formEditProductDetail.add(recipeField);

        validate.setBounds(50, 350, 300, 150);
        formEditProductDetail.setPreferredSize(new Dimension(400, 510));

    }

    private void setProductDetailForm() {
        ProductDetailModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Chỉ cho phép chỉnh sửa cột Age
                return column == 5;
            }
        };
        createTableProcductDetail();
        JScrollPane scroll = new JScrollPane(tableProductDetail);
        scroll.setPreferredSize(new Dimension(570, 600));
//        leftPanel.removeAll();
        leftPanel.add(scroll);

        setFormEditProductDetail();
        rightPanel.add(formEditProductDetail);

        checkComboBox();
    }
}
