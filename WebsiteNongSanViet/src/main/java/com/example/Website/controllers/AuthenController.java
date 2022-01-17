/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.controllers;

import com.example.Website.dto.LoginInfo;
import com.example.Website.model.CustomerModel;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.example.Website.dto.Customer;
import com.example.Website.dto.RegisterInfo;
import com.example.Website.model.AdminModel;
import org.springframework.web.bind.support.SessionStatus;
import com.example.Website.dto.Admin;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Hoang Xoan
 */
@SessionAttributes("customer")
@Controller
public class AuthenController {

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("loginInfo", new LoginInfo("", ""));
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginInfo loginInfo, Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception {
        CustomerModel customerModel = new CustomerModel();

        Customer customer = customerModel.login(loginInfo.getEmail(), loginInfo.getPassword());

        if (customer != null) {
            model.addAttribute("customer", customer);
            Cookie cookie = new Cookie("customerId", String.valueOf(customer.getId()));
            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return "redirect:/";
        }//
//        AdminModel adminModel = new AdminModel();
//        Admin admin = adminModel.login(loginInfo.getEmail(), loginInfo.getPassword());
//
//        if (admin != null) {
//
//            model.addAttribute("admin", admin);
//
//            Cookie cookie = new Cookie("adminId", String.valueOf(admin.getId()));
//            cookie.setMaxAge(7 * 24 * 60 * 60);
//            cookie.setHttpOnly(true);
//            response.addCookie(cookie);
//
//            return "redirect:/addProd";

//        }
        model.addAttribute("invalidCredentials", true);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus, HttpServletResponse response) {
        sessionStatus.setComplete();
        Cookie cookie = new Cookie("customerId", null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        RegisterInfo registerInfo = new RegisterInfo("", "", "", "");
        model.addAttribute("registerInfo", registerInfo);
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute RegisterInfo registerInfo, Model model, HttpServletResponse response, RedirectAttributes reat) throws Exception {

        CustomerModel customerModel = new CustomerModel();
        Customer customer = customerModel.register(registerInfo.getEmail());

        if (customer == null) {
            Customer c = new Customer();
            c.setFirstName(registerInfo.getFirstName());
            c.setLastName(registerInfo.getLastName());

            c.setEmail(registerInfo.getEmail());
            c.setPassword(registerInfo.getPassword());

            int add = customerModel.add(c);

            if (add > 0) {
                // add cookie để ghi nhớ tk
                Cookie cookie = new Cookie("email", registerInfo.getEmail());
                cookie.setHttpOnly(true);
                cookie.setMaxAge(7 * 24 * 60 * 60);
                response.addCookie(cookie);
                return "redirect:/login";
                // - trả về login để ng dùng đăng nhập
            }
        }
        reat.addFlashAttribute("error_register", "Đăng kí thất bại");

        return "redirect:/register";

    }


}
