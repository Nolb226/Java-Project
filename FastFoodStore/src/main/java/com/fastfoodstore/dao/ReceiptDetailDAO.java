package com.fastfoodstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fastfoodstore.dto.ReceiptDetailDTO;

public class ReceiptDetailDAO implements DAOInterface<ReceiptDetailDTO> {
    
    public static ReceiptDetailDAO getInstance() {
        return new ReceiptDetailDAO();
    }

    @Override
    public int insert(ReceiptDetailDTO t) {
        int change = 0;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "INSERT INTO `receiptdetail` (`receiptCode`, `ingredientCode`, `amoutInReceipt`, `price`)"
                    + " VALUES (?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, t.getReceiptCode());
            pst.setString(2, t.getIngredientCode());
            pst.setFloat(3, t.getAmountInReceipt());
            pst.setFloat(4, t.getPrice());

            change = pst.executeUpdate();

            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int update(ReceiptDetailDTO t) {
        int change = 0;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "UPDATE `receiptdetail`"
                    + " SET `receiptCode` = ?, `ingredientCode` = ?, `amoutInReceipt` = ?,  `price` = ?"
                    + " WHERE `receiptdetail`.`receiptCode` = ? AND `receiptdetail`.`ingredientCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, t.getReceiptCode());
            pst.setString(2, t.getIngredientCode());
            pst.setFloat(3, t.getAmountInReceipt());
            pst.setFloat(4, t.getPrice());
            pst.setString(5, t.getReceiptCode());
            pst.setString(6, t.getIngredientCode());

            change = pst.executeUpdate();

            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public int delete(ReceiptDetailDTO t) {
        int change = 0;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "DELETE FROM receiptdetail"
                    + "WHERE `receiptdetail`.`receiptCode` = ? AND `receiptdetail`.`ingredientCode` = ?";
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, t.getReceiptCode());
            pst.setString(2, t.getIngredientCode());

            change = pst.executeUpdate();

            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Insert data failture" + e);
        }
        return change;
    }

    @Override
    public ArrayList<ReceiptDetailDTO> selectAll() {
        ArrayList<ReceiptDetailDTO> receiptDetailList = new ArrayList<ReceiptDetailDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM receiptdetail";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ReceiptDetailDTO data = new ReceiptDetailDTO(
                        rs.getString("receiptCode"),
                        rs.getString("ingredientCode"),
                        rs.getFloat("amoutInReceipt"),
                        rs.getInt("price")
                );
                receiptDetailList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if (isData) {
            return receiptDetailList;
        } else {
            return null;
        }
    }

    @Override
    public ReceiptDetailDTO selectById(String id) {
        return null;
    }

    @Override
    public ArrayList<ReceiptDetailDTO> selectByCondition(String condition, String colName) {
        ArrayList<ReceiptDetailDTO> receiptDetailList = new ArrayList<ReceiptDetailDTO>();
        boolean isData = false;

        try {
            Connection connection = ConnectionData.getConnection();
            String sql = "SELECT * FROM receiptdetail " + condition + "";
            PreparedStatement pst = connection.prepareStatement(sql);
            // pst.setString(1, condition);            
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ReceiptDetailDTO data = new ReceiptDetailDTO(
                        rs.getString("receiptCode"),
                        rs.getString("ingredientCode"),
                        rs.getFloat("amoutInReceipt"),
                        rs.getInt("price")
                );
                receiptDetailList.add(data);
                isData = true;
            }
            ConnectionData.closeConnection(connection);
        } catch (Exception e) {
            System.out.println("Select data failture" + e);
        }

        if (isData) {
            return receiptDetailList;
        } else {
            return null;
        }
    }

}
