/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.controllers;

import com.example.Website.dto.Admin;
import com.example.Website.dto.Customer;
import com.example.Website.dto.LoginInfo;
import com.example.Website.dto.Product;
import com.example.Website.model.AdminModel;
import com.example.Website.model.CustomerModel;
import com.example.Website.model.ProductManage;
import com.example.Website.model.ProductModel;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Hoang Xoan
 */
@Controller
@SessionAttributes("admin")
public class AdminController {

    @GetMapping("/admin")
    public String loginAdmin(Model model) throws Exception {
        model.addAttribute("loginInfo", new LoginInfo("", ""));
        return "admin/loginAdmin";
    }

    @RequestMapping("/admin")
    public String loginAdmin(@ModelAttribute LoginInfo loginInfo, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {

        AdminModel adminModel = new AdminModel();
        Admin admin = adminModel.login(loginInfo.getEmail(), loginInfo.getPassword());

        if (admin != null) {
            model.addAttribute("admin", loginInfo.getEmail());
            return "redirect:/homeAdmin";
        }
        model.addAttribute("invalidCredentials", true);
        return "admin/loginAdmin";
    }

    @RequestMapping("/homeAdmin")
    public String admin(Model model) throws Exception {

        ProductModel productModel = new ProductModel();

        List<Product> randProducts = productModel.getRandProduct(6);
        model.addAttribute("listProduct", randProducts);
        
        return "admin/admin";
    }

    @RequestMapping("/addProd")
    public String addProduct(Model model, HttpSession session) {
//        Admin admin = (Admin) session.getAttribute("admin");
        if ((session.getAttribute("admin")) != null) {
            model.addAttribute("product", new Product());
            return "admin/addProd";
        }
        return "redirect:/admin";
    }

    @GetMapping("/ManageProd")
    public String getManageProd(Model model, HttpSession session) throws Exception {
//        Admin admin = (Admin) session.getAttribute("admin");
//        System.out.println(admin);
        if ((session.getAttribute("admin")) != null) {
            System.out.println("hello");
            ProductModel productModel = new ProductModel();
            List<Product> listProduct = productModel.getProduct();
            model.addAttribute("product", listProduct);
            return "admin/manageProduct";
        } else {
            return "redirect:/admin";
        }

    }

    @PostMapping(value = "saveProd")
    public String saveProd(Product product, BindingResult bingingResult, Model model) throws Exception {
        if (bingingResult.hasErrors()) {
            return "admin/addProd";
        } else {
            ProductManage productManage = new ProductManage();

            model.addAttribute("successProd", productManage.add(product));
            return "redirect:/ManageProd";
        }
    }

    @GetMapping(value = "/del/{id}")
    public String deleteProd(@PathVariable int id, Model model) throws Exception {
        ProductManage productManage = new ProductManage();
        model.addAttribute("listprod", productManage.delete(id));
        return "redirect:/ManageProd";
    }

}
