/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaydenzheng;

import android.app.Activity;
import android.view.ViewGroup;

/**
 *
 * @author rong
 */
public abstract class ViewManager {
    
    protected final Activity act;
    
    public ViewManager(Activity act) {
        this.act = act;
        this.init();
    }
    
    public abstract void init();
    
    public abstract ViewGroup getViewGroup();

    public abstract void refreshLayout();
    
}
