/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.controllers;

import com.example.Website.dto.Product;
import com.example.Website.model.AdminModel;
import com.example.Website.model.ProductManage;
import com.example.Website.model.ProductModel;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Hoang Xoan
 */
@SessionAttributes("admin")
@Controller
public class AdminProductController {

    @RequestMapping("/admin")
    public String Admin(Model model) {
        return "admin";
    }
    

    @RequestMapping("/addProd")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "addProd";
    }

    @GetMapping("/ManageProd")
    public String getManageProd(Model model) {
        try {
            ProductModel productModel = new ProductModel();
            List<Product> listProduct = productModel.getProduct();
            model.addAttribute("listProduct", listProduct);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "ManageProd";
    }

    @PostMapping(value = "saveProd")
    public String saveProd(Product product, BindingResult bingingResult, Model model) throws Exception {
        if (bingingResult.hasErrors()) {
            return "addProd";
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
