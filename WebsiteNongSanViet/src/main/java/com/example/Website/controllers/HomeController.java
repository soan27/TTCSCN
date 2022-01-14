/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.controllers;

import com.example.Website.db.DBConnector;
import com.example.Website.dto.Category;
import com.example.Website.dto.Product;
import com.example.Website.model.ProductModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Hoang Xoan
 */
@SessionAttributes("customer")
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model) throws Exception {
        try {
            //lấy category
            ProductModel prodModel = new ProductModel();
            List<Category> category = prodModel.getCategory();
            model.addAttribute("category", category);

            //lấy sp ngẫu nhiên
            List<Product> randProducts = prodModel.getRandProduct(6);
            model.addAttribute("randProducts", randProducts);

            //Lấy sp giảm giá
            List<Product> product_discount = prodModel.getProductDiscount(8);
            model.addAttribute("product_discount", product_discount);
            
            int count = prodModel.countSearch("Táo");
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "home";
    }
}
