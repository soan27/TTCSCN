/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.model;

import com.example.Website.dto.Customer;
import com.example.Website.dto.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Hoang Xoan
 */
public class CartModel {
    
    private Connection conn;
    
    public int order(Order order) throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String sql = "INSERT INTO orders (custId, amount,status,totalprice) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, order.getCusId());
        pstmt.setInt(2, order.getAmount());
        pstmt.setBoolean(3, order.isStatus());
        pstmt.setLong(4,order.getTotalprice());

        return pstmt.executeUpdate();
    }
}
