/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaydenzheng;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rong Zheng
 */
public class JayzService extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cType = request.getHeader("Content-Type");
        String cLen = request.getHeader("Content-length");

        if (cType.equals("application/rmi")) {
            InputStream is = request.getInputStream();

            // Read input data...
            boolean done = false;
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int contentLen = Integer.parseInt(cLen);
            int total = 0;
            while (!done) {
                int len = is.read(buffer);
                if (len <= 0) {
                    done = true;
                } else {
                    total = total + len;
                    baos.write(buffer, 0, len);
                    if (total == contentLen) {
                        done = true;
                    }
                }
            }
            byte[] requestData = baos.toByteArray();

            response.setContentType("application/rmi");
            try {
                Object mwObj = IOUtils.deSerializeObj(requestData);
                if (mwObj instanceof MethodWrapper) {
                    MethodWrapper mw = (MethodWrapper) mwObj;
                    DataWrapper dw;
                    try {
                        Object retData = IOUtils.invokeMethod(this, mw);
                        dw = new DataWrapper(retData);
                    } catch (InvocationTargetException ex) {
                        dw = new DataWrapper(ex.getTargetException());
                    } catch (Exception ex) {
                        dw = new DataWrapper(ex);
                    }
                    byte[] responseData = IOUtils.serializeObj(dw);
                    OutputStream out = response.getOutputStream();
                    out.write(responseData);
                    out.close();
                }
            } catch (Exception ex) {
                DataWrapper dw = new DataWrapper(ex);
                byte[] responseData = IOUtils.serializeObj(dw);
                OutputStream out = response.getOutputStream();
                out.write(responseData);
                out.close();
            }

        } else {
            response.setContentType("text/text;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                /* TODO output your page here. You may use following sample code. */
                out.println("Protocal Error.");
            } finally {
                out.close();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
