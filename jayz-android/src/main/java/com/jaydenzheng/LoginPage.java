/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaydenzheng;

import android.app.Activity;
import android.content.res.Configuration;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.jaydenzheng.widget.ClockView;

/**
 *
 * @author rong
 */
public class LoginPage extends ViewManager {

    private TextView textViewLogin;
    private TextView textViewPassword;
    private EditText editTextLogin;
    private EditText editTextPassword;

    private Button buttonLogin;
    private RelativeLayout layout;
    private ScrollView scrool;

    private ClockView clock;

    public LoginPage(Activity act) {
        super(act);
    }

    @Override
    public void init() {

        int id = 1;
//        scrool = new ScrollView(act);
//        this.scrool.setId(id);
//        scrool.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        this.layout = new RelativeLayout(act);
        this.layout.setId(++id);
        this.layout.setPadding(50, 50, 50, 20);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//        scrool.addView(layout);

        this.textViewLogin = new TextView(act);
        this.textViewLogin.setText("User Name:");
        this.textViewLogin.setId(++id);
        this.textViewLogin.setTextSize(24f);

        this.editTextLogin = new EditText(act);
        this.editTextLogin.setId(++id);
        this.editTextLogin.setTextSize(24f);
        this.editTextLogin.setSingleLine(true);

        this.textViewPassword = new TextView(act);
        this.textViewPassword.setText("Password:");
        this.textViewPassword.setTextSize(24f);
        this.textViewPassword.setId(++id);

        this.editTextPassword = new EditText(act);
        this.editTextPassword.setId(++id);
        this.editTextPassword.setTextSize(24f);
        this.editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        this.editTextPassword.setSingleLine(true);

        this.buttonLogin = new Button(act);
        this.buttonLogin.setId(++id);
        this.buttonLogin.setText("Sign on");

        this.clock = new ClockView(act);
        this.clock.setId(++id);
     //   this.clock.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                onLoginButtonClick();
            }
        };
        this.buttonLogin.setOnClickListener(buttonClickListener);

        this.refreshLayout();
    }

    @Override
    public void refreshLayout() {
        android.content.res.Configuration config = act.getResources().getConfiguration();
        Log.d("refreshLayout", "orientation:" + config.orientation);
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            this.scrool.removeAllViews();
            this.layout.removeAllViews();
            RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp1.addRule(RelativeLayout.ALIGN_PARENT_START);
            this.layout.addView(this.textViewLogin, lp1);

            RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp2.addRule(RelativeLayout.BELOW, this.textViewLogin.getId());
            lp2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            this.layout.addView(this.editTextLogin, lp2);

            RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp3.addRule(RelativeLayout.BELOW, this.editTextLogin.getId());
            lp3.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            lp3.setMargins(0, 50, 0, 0);
            this.layout.addView(this.textViewPassword, lp3);

            RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp4.addRule(RelativeLayout.BELOW, this.textViewPassword.getId());
            lp4.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            this.layout.addView(this.editTextPassword, lp4);

            RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp5.addRule(RelativeLayout.BELOW, this.editTextPassword.getId());
            lp5.addRule(RelativeLayout.CENTER_HORIZONTAL);
            this.layout.addView(this.buttonLogin, lp5);

            RelativeLayout.LayoutParams lp6 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp6.addRule(RelativeLayout.BELOW, this.buttonLogin.getId());
           // lp6.addRule(RelativeLayout.CENTER_HORIZONTAL);
            lp6.addRule(RelativeLayout.ALIGN_BOTTOM);
            this.layout.addView(clock, lp6);
            
         //   this.scrool.addView(layout);
        } else if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
    //        this.scrool.removeAllViews();
            this.layout.removeAllViews();
            RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp1.addRule(RelativeLayout.ALIGN_PARENT_START);
            this.layout.addView(this.textViewLogin, lp1);

            RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp2.addRule(RelativeLayout.RIGHT_OF, this.textViewLogin.getId());
            lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            this.layout.addView(this.editTextLogin, lp2);

            RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp3.addRule(RelativeLayout.BELOW, this.textViewLogin.getId());
            lp3.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            lp3.setMargins(0, 100, 0, 0);
            this.layout.addView(this.textViewPassword, lp3);

            RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp4.addRule(RelativeLayout.RIGHT_OF, this.textViewLogin.getId());
            lp4.addRule(RelativeLayout.ALIGN_TOP, this.textViewPassword.getId());
            lp4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            //  lp4.addRule(RelativeLayout.ALIGN_PARENT_END);
            this.editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            this.layout.addView(this.editTextPassword, lp4);

            RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams(500, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp5.addRule(RelativeLayout.RIGHT_OF, this.textViewLogin.getId());
            lp5.addRule(RelativeLayout.BELOW, this.editTextPassword.getId());
            lp5.setMargins(0, 80, 0, 0);
            this.layout.addView(this.buttonLogin, lp5);
         //   this.scrool.addView(layout);
        }

    }

    @Override
    public ViewGroup getViewGroup() {
        return this.layout;
    }

    public void onLoginButtonClick() {
        String user = this.editTextLogin.getText().toString();
        String password = this.editTextPassword.getText().toString();
        Log.d(this.getClass().getName(), "Login=" + user + ", password=" + password);
        HelloAndroidActivity mainAct = (HelloAndroidActivity) this.act;
        this.stopClock();
        mainAct.switchToPage1();
    }

    public void stopClock() {
        clock.stop();
    }

    public void startClock() {
        clock.start();
    }
}
