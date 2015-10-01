/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jayztest;

import com.jaydenzheng.JayzProxyFactory;
import com.jaydenzheng.TestInterface;

/**
 *
 * @author Rong Zheng
 */
public class JayzTest {

    public static void main(String[] args) {

        try {
            String url = "http://localhost:8084/jayz-web/testJaysService";

            JayzProxyFactory factory = new JayzProxyFactory(TestInterface.class, url);

            long t1 = System.currentTimeMillis();
            TestInterface ti = (TestInterface) factory.create();

//
            String retmsg = ti.method2(100, "hello message");
            System.out.println("Return message:" + retmsg);

            ti.method1();

            double r = ti.calculate(192.5, 20);
            long t2 = System.currentTimeMillis();
            System.out.println("Time spent:" + (t2 - t1) + " ms ");
            System.out.println("r=" + r);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
