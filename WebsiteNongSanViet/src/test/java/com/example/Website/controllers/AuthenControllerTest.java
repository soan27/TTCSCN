/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.controllers;

import com.example.Website.dto.LoginInfo;
import com.example.Website.dto.RegisterInfo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Hoang Xoan
 */
public class AuthenControllerTest {
    
    public AuthenControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getLogin method, of class AuthenController.
     */
    @org.junit.Test
    public void testGetLogin() {
        System.out.println("getLogin");
        Model model = null;
        AuthenController instance = new AuthenController();
        String expResult = "";
        String result = instance.getLogin(model);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class AuthenController.
     */
    @org.junit.Test
    public void testLogin() throws Exception {
        System.out.println("login");
        LoginInfo loginInfo = null;
        Model model = null;
        HttpServletResponse response = null;
        HttpServletRequest request = null;
        HttpSession session = null;
        AuthenController instance = new AuthenController();
        String expResult = "";
        String result = instance.login(loginInfo, model, response, request, session);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logout method, of class AuthenController.
     */
    @org.junit.Test
    public void testLogout() {
        System.out.println("logout");
        SessionStatus sessionStatus = null;
        HttpServletResponse response = null;
        AuthenController instance = new AuthenController();
        String expResult = "";
        String result = instance.logout(sessionStatus, response);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRegister method, of class AuthenController.
     */
    @org.junit.Test
    public void testGetRegister() {
        System.out.println("getRegister");
        Model model = null;
        AuthenController instance = new AuthenController();
        String expResult = "";
        String result = instance.getRegister(model);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleRegister method, of class AuthenController.
     */
    @org.junit.Test
    public void testHandleRegister() throws Exception {
        System.out.println("handleRegister");
        RegisterInfo registerInfo = null;
        Model model = null;
        HttpServletResponse response = null;
        RedirectAttributes reat = null;
        AuthenController instance = new AuthenController();
        String expResult = "";
        String result = instance.handleRegister(registerInfo, model, response, reat);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
