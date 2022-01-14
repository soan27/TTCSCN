/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.model;

import com.example.Website.db.DBConnector;
import com.example.Website.dto.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Hoang Xoan
 */
public class CustomerModel {

    private Connection conn;

    public CustomerModel() throws Exception {
        this.conn = DBConnector.getConnection();
    }

    //findByEmailAndPassword
    public Customer getCustomerById(int id) throws SQLException {
        String sql = "SELECT * FROM customers WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) { // nếu có bản ghi tồn tại
            Customer c = new Customer();
            c.setId(rs.getInt("id"));
            c.setFirstName(rs.getString("firstName"));
            c.setLastName(rs.getString("lastName"));
            c.setEmail(rs.getString("email"));
            c.setPassword(rs.getString("password"));

            return c;
        }
        return null;

    }

    public Customer login(String email, String password) throws SQLException {
        String sql = "SELECT * FROM customers WHERE email = ? AND password = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, email);
        pst.setString(2, password);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            Customer c = new Customer();
            c.setId(rs.getInt("id"));
            c.setFirstName(rs.getString("firstName"));
            c.setLastName(rs.getString("lastName"));
            c.setEmail(rs.getString("email"));
            c.setPassword(rs.getString("password"));

            return c;
        }

        return null;
    }
    
     public Customer register(String email) throws SQLException {
        String sql = "SELECT * FROM customers WHERE email = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, email);
         System.out.println(pst);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            Customer c = new Customer();
            c.setId(rs.getInt("id"));
            c.setFirstName(rs.getString("firstName"));
            c.setLastName(rs.getString("lastName"));
            c.setEmail(rs.getString("email"));
            c.setPassword(rs.getString("password"));

            return c;
        }

        return null;
    }
    
     public int add(Customer customer) throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String sql = "INSERT INTO customers (firstName, lastName,email,password) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, customer.getFirstName());
        pstmt.setString(2, customer.getLastName());
        pstmt.setString(3, customer.getEmail());
        pstmt.setString(4, customer.getPassword());

        return pstmt.executeUpdate();
    }
     
     public int changepass(String pass, int id) throws Exception {
        String query = "update customers set password = ? where id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, pass);
        ps.setInt(2, id);
        return ps.executeUpdate();
    }

}
