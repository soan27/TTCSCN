/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.controllers;

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
public class OtherController {
    @RequestMapping("/information")
    public String information(Model model){
        return "information";
    }
    
    @RequestMapping("/license")
    public String license(Model model){
        return "license";
    }
    
    @RequestMapping("/blog")
    public String blog(Model model){
        return "blog";
    }
}
