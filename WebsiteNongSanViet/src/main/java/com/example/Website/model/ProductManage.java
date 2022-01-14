/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.model;

import com.example.Website.db.DBConnector;
import com.example.Website.dto.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Hoang Xoan
 */
public class ProductManage {

    private Connection conn;

    public ProductManage() throws Exception {
        this.conn = DBConnector.getConnection();
    }

    public int add(Product product) throws Exception {
        String sql = "INSERT INTO `products` (`name`, `desc`, `brief_desc`,`cateId`, `real_price`,`percent_discount`,`quantityProd`,`isHot`,`image`) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1, product.getName());
        pstmt.setString(2, product.getDesc());
        pstmt.setString(3, product.getBrief_desc());
        pstmt.setInt(4, product.getCateId());
        pstmt.setDouble(5, product.getReal_price());
        pstmt.setDouble(6, product.getPercent_discount());
        pstmt.setInt(7, product.getQuantityProd());
        pstmt.setBoolean(8, product.isIsHot());
        pstmt.setString(9, product.getImage());

        return pstmt.executeUpdate();
    }

    public int delete(int id) throws Exception {
        String sql = "DELETE FROM products WHERE id = ?";
        PreparedStatement pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        return pstmt.executeUpdate();
    }
}
