/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaydenzheng.jayz.web;

import com.jaydenzheng.JayzService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rong Zheng
 */
@WebServlet(name = "testJaysService", urlPatterns = {"/testJaysService"})
public class testJaysService extends JayzService implements TestInterface {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.processRequest(request, response);
    }

    @Override
    public void method1() {
        System.out.println("Calling method1 from " + this.getClass().getName());
    }

    @Override
    public String method2(int a, String b) {
        System.out.println("Calling method2 from " + this.getClass().getName());
        return "return from servlet: " + a + " -- " + b;
    }

    @Override
    public double calculate(double a1, double a2) throws IOException {
        if (a2 == 0 ) {
            throw new IOException("a2 is zero exception");
        }
        return a1 * a2;
    }
    
    
}
