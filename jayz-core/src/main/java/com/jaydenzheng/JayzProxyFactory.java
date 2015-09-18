/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaydenzheng;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * @author mcl
 */
public class JayzProxyFactory {

    private final String url;
    private final Class interfaceClass;
    private InvocationHandler handler;

    public JayzProxyFactory(Class interfaceClass, String url) {
        this.interfaceClass = interfaceClass;
        this.url = url;
        handler = new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return JayzProxyFactory.this.invoke(proxy, method, args);
            }
        };
    }

    public Object create() {
        Object obj = Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[]{this.interfaceClass}, this.handler);
        return obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        MethodWrapper mw = new MethodWrapper(method.getName(), args, method.getParameterTypes());
        byte[] mwData = IOUtils.serializeObj(mw);
        byte[] retData = IOUtils.postData(url, mwData);

        Object retObj = IOUtils.deSerializeObj(retData);
        if (retObj instanceof DataWrapper) {
            DataWrapper dw = (DataWrapper) retObj;
            Class returnType = method.getReturnType();

            if (dw.getData() != null) {
                // if (dw.getData().getClass() == returnType) {                 
                if (dw.getData() instanceof Exception) {

                    boolean exceptionMatched = false;
                    for (Class retEx : method.getExceptionTypes()) {
                        if (retEx == dw.getData().getClass()) {
                            exceptionMatched = true;
                            break;
                        }
                    }
                    if (exceptionMatched) {
                        throw (Exception) dw.getData();
                    }
                } else {
                    return dw.getData();
                }
            } else {
                if (returnType == Void.TYPE) {
                    return null;
                }
            }
        }
        throw new Exception("Invalid data protocol.");
    }

    public String getUrl() {
        return url;
    }

    public Class getInterfaceClass() {
        return interfaceClass;
    }

}
