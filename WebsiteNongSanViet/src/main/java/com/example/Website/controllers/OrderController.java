/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.controllers;

import com.example.Website.db.DBConnector;
import com.example.Website.dto.CreateOrderDto;
import com.example.Website.dto.Product;
import com.example.Website.model.CustomerModel;
import com.example.Website.model.OrderModel;
import com.example.Website.model.ProductModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hoang Xoan
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @PostMapping
    public void createOrder(@Valid @RequestBody CreateOrderDto createDto) {
        try (Connection connection = DBConnector.getConnection()) {
            String query = "INSERT INTO cust_orders(total_price, amount, product_id, cust_id, address, status) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, createDto.getTotalPrice());
            stm.setInt(2, createDto.getAmount());
            stm.setInt(3, createDto.getProductId());
            stm.setInt(4, createDto.getCustId());
            stm.setString(5, createDto.getAddress());
            stm.setString(6, createDto.getStatus());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GetMapping
    public List<OrderModel> allOrders() {
        List<OrderModel> list = new ArrayList<>();

        try (Connection connection = DBConnector.getConnection()) {
            String query = "SELECT * FROM cust_orders";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                OrderModel order = new OrderModel();
                order.setId(rs.getInt("id"));
                order.setAddress(rs.getString("address"));
                order.setAmount(rs.getInt("amount"));
                order.setCustId(rs.getInt("cust_id"));
                order.setProductId(rs.getInt("product_id"));
                order.setStatus(rs.getString("status"));
                order.setTotalPrice(rs.getInt("total_price"));
                ProductModel product = new ProductModel();
                product.findById(order.getProductId());
                order.setProduct(product);
                CustomerModel customer = new CustomerModel();
                customer.getCustomerById(order.getCustId());
                order.setCustomer(customer);
                list.add(order);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
}
