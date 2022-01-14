/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.controllers;

import com.example.Website.dto.Customer;
import com.example.Website.model.CustomerModel;
import com.sun.tracing.dtrace.Attributes;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Hoang Xoan
 */
@Controller
@SessionAttributes("customer")
public class PurchaseHistory {
    
    @GetMapping("/order-history")
    public String history(Model model, HttpSession session) throws Exception {
        Customer cus = (Customer) model.getAttribute("customer");
        if (cus != null) {
            
            return "personalInformation";
        } else {
//            
            return "redirect:/login";
        }
    }
    @PostMapping("/changepassword")
    public String changePass(Model model, @ModelAttribute Customer c,RedirectAttributes reat) throws Exception {
        CustomerModel customerModel = new CustomerModel();
        Customer cus = (Customer) model.getAttribute("customer");
        int update = customerModel.changepass(c.getPassword(), cus.getId());
        if(update>0){
            reat.addFlashAttribute("change_success", "Thay đổi thành công");
        }
        return "redirect:/order-history";
    }
}
