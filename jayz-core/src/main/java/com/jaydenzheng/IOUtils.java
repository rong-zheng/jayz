/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaydenzheng;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author mcl
 */
public class IOUtils {

    public static byte[] serializeObj(java.io.Serializable obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(obj);
        out.close();
        byte[] data = baos.toByteArray();
        baos.close();
        return data;
    }

    public static Object deSerializeObj(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream in = new ObjectInputStream(bais);
        Object obj = in.readObject();
        in.close();
        bais.close();
        return obj;
    }

    public static Object invokeMethod(Object obj, MethodWrapper mw) throws Exception {

        if (mw == null || mw.getMethodName() == null) {
            throw new Exception("Invalid method info...");
        }
        Object[] args = mw.getArgs();

        Class callingClass = obj.getClass();
        Method method = callingClass.getMethod(mw.getMethodName(), mw.getParams());
        // System.out.println("Method name from reflection:" + method.getName());
        return method.invoke(obj, args);
    }

    public static byte[] postData(String urlPath, byte[] data) throws Exception {
       // try {

        // Create connection
        URL url = new URL(urlPath);
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection conn = (HttpURLConnection) urlConnection;
        conn.setConnectTimeout(2000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestProperty("Content-Type", "application/rmi");

        OutputStream outStream = conn.getOutputStream();
        outStream.write(data);
        outStream.flush();
        outStream.close();

        InputStream inStream = conn.getInputStream();
        boolean done = false;
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int contentLen = conn.getContentLength();
        int total = 0;
        while (!done) {
            int len = inStream.read(buffer);
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
        byte[] retData = baos.toByteArray();

        inStream.close();
        outStream.close();
        return retData;
//        } catch (Exception ex) {
//            System.out.println("Exception cought:\n" + ex.toString());
//            try {
//                return IOUtils.serializeObj(ex);
//            } catch (IOException ex1) {
//                ex.printStackTrace();
//            }
//        }
//        return null;
    }

}
