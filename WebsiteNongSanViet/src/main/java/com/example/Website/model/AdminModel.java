/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.model;

import com.example.Website.db.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.Website.dto.Admin;

/**
 *
 * @author Hoang Xoan
 */
public class AdminModel {

    private Connection conn;

    public AdminModel() throws Exception {
        this.conn = DBConnector.getConnection();
    }

    //findByEmailAndPassword
    public Admin getAdminById(int id) throws SQLException {
        String sql = "SELECT * FROM ADMIN WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) { // nếu có bản ghi tồn tại
            Admin a = new Admin();
            a.setId(rs.getInt("id"));
            a.setName(rs.getString("name"));
            a.setEmail(rs.getString("email"));
            a.setPassword(rs.getString("password"));

            return a;
        }
        return null;

    }

    public Admin login(String email, String password) throws SQLException {
        String sql = "SELECT * FROM admin WHERE email = ? AND password = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, email);
        pst.setString(2, password);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            Admin a = new Admin();
            a.setId(rs.getInt("id"));
            a.setName(rs.getString("name"));
            a.setEmail(rs.getString("email"));
            a.setPassword(rs.getString("password"));

            return a;

        }

        return null;
    }
}
