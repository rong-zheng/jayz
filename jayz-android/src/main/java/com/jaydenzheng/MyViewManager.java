/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaydenzheng;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 *
 * @author rong
 */
public class MyViewManager extends ViewManager {

    private  LinearLayout layout;

    public MyViewManager(Activity act) {
        super(act);
        
        android.content.res.Configuration config = act.getResources().getConfiguration();
        Log.d(this.getClass().getName(), "orientation:" + config.orientation);
        
    }

    @Override
    public void init() {
        layout = new LinearLayout(this.act);
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout btnLayout = new LinearLayout(this.act);
        btnLayout.setOrientation(LinearLayout.HORIZONTAL);
        Button btn1 = new Button(this.act);
        btn1.setText("Btn1");
        btn1.setPadding(0, 0, 0, 0);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp1.setMargins(0, 0, 0, 0);
        //btn1.setLayoutParams(lp1);
        // btn1.setGravity(Gravity.RIGHT);

        Button btn2 = new Button(this.act);
        btn2.setText("Btn2");
        btn2.setPadding(0, 0, 0, 0);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp2.setMargins(-21, 0, 0, 0);
        //btn2.setLayoutParams(lp2);

        btnLayout.addView(btn1, lp1);
        btnLayout.addView(btn2, lp2);

        TextView text = new TextView(this.act);

        text.setText("Login Name:");
        text.setTextSize(24f);

        EditText textBox = new EditText(this.act);
        textBox.setTextSize(18f);
        textBox.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout loginRow = new LinearLayout(this.act);
        loginRow.setOrientation(LinearLayout.VERTICAL);
        loginRow.addView(text);
        loginRow.addView(textBox);
        loginRow.setPadding(10, 20, 10, 10);

        layout.addView(btnLayout);
        layout.addView(loginRow);

        RelativeLayout relativeLayout = new RelativeLayout(this.act);
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        Button btn3 = new Button(this.act);
        btn3.setText("Btn3");
        btn3.setId(3);
        //btn3.setLayoutParams(lp1);

        Button btn4 = new Button(this.act);
        btn4.setText("Btn4");
        btn4.setId(4);
        //btn4.setLayoutParams(lp1);

        Button btn5 = new Button(this.act);
        btn5.setText("Btn5");
        btn5.setId(5);
        //btn5.setLayoutParams(lp1);

        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp3.addRule(RelativeLayout.ALIGN_PARENT_START);
        relativeLayout.addView(btn3, lp3);

        RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        Log.d("layout", "btn3 id:" + btn3.getId());
        Log.d("layout", "btn4 id:" + btn4.getId());

        lp4.addRule(RelativeLayout.BELOW, btn3.getId());
        lp4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        relativeLayout.addView(btn4, lp4);

        RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp5.addRule(RelativeLayout.LEFT_OF, btn4.getId());
        lp5.addRule(RelativeLayout.BELOW, btn3.getId());
        relativeLayout.addView(btn5, lp5);

        layout.addView(relativeLayout);
    }
    
    @Override
    public ViewGroup getViewGroup() {
        return this.layout;
    }

    @Override
    public void refreshLayout() {
        
    }
}
