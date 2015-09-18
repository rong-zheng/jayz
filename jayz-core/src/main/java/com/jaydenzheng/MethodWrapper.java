/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jaydenzheng;

import java.io.Serializable;

/**
 *
 * @author mcl
 */
public class MethodWrapper implements Serializable {
    private String methodName;
    private Object[] args;
    private Class[] params;

    

    public MethodWrapper(String methodName, Object[] args, Class[] params) {
        this.methodName = methodName;
        this.args = args;
        this.params = params;
    }

    
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Class[] getParams() {
        return params;
    }

    public void setParams(Class[] params) {
        this.params = params;
    }
    
    
    
}
