/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import com.example.Website.dto.Product;
import com.example.Website.dto.Customer;
import com.example.Website.dto.Order;
import com.example.Website.model.CartModel;
import com.sun.tracing.dtrace.Attributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Hoang Xoan
 */
@SessionAttributes({"totalcost", "listcart, customer"})
@Controller
public class CartController {
    
 @RequestMapping("/viewCart")
    public String viewCart(Model model, HttpServletRequest request){
        List<Product> cart = (List<Product>) model.getAttribute("listcart");
        return "viewCart";
    }
    @RequestMapping("/payment")
    public String payment(Model model){
        return "payment";
    }
}
