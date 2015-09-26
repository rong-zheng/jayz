package com.jaydenzheng;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class HelloAndroidActivity extends Activity {

    private Button button1;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down then this Bundle contains the data it most
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it
     * is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_main);
//        this.button1 = (Button) findViewById(R.id.button1);
//        View.OnClickListener buttonClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                onButton1Click();
//            }
//
//        };
//        
//        this.button1.setOnClickListener(buttonClickListener);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout btnLayout = new LinearLayout(this);
        btnLayout.setOrientation(LinearLayout.HORIZONTAL);
        Button btn1 = new Button(this);
        btn1.setText("Btn1");
        btn1.setPadding(0,0,0,0);
        LayoutParams lp1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp1.setMargins(0,0,0,0);
        //btn1.setLayoutParams(lp1);
        // btn1.setGravity(Gravity.RIGHT);

        Button btn2 = new Button(this);
        btn2.setText("Btn2");
        btn2.setPadding(0, 0, 0, 0);

        LayoutParams lp2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp2.setMargins(-21,0,0,0);
        //btn2.setLayoutParams(lp2);

        btnLayout.addView(btn1,lp1);
        btnLayout.addView(btn2, lp2);

        TextView text = new TextView(this);

        text.setText("Login Name:");
        text.setTextSize(24f);

        EditText textBox = new EditText(this);
        textBox.setTextSize(18f);
        textBox.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        LinearLayout loginRow = new LinearLayout(this);
        loginRow.setOrientation(LinearLayout.VERTICAL);
        loginRow.addView(text);
        loginRow.addView(textBox);
        loginRow.setPadding(10, 20, 10, 10);

        layout.addView(btnLayout);
        layout.addView(loginRow);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        Button btn3 = new Button(this);
        btn3.setText("Btn3");
        btn3.setId(3);
        //btn3.setLayoutParams(lp1);

        Button btn4 = new Button(this);
        btn4.setText("Btn4");
        btn4.setId(4);
        //btn4.setLayoutParams(lp1);

        Button btn5 = new Button(this);
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
        this.setContentView(layout);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.jaydenzheng.R.menu.main, menu);
        return true;
    }

    public void runJayzClient() {

        Thread jayzThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://192.168.1.18:8084/jayz-web/testJaysService";

                    JayzProxyFactory factory = new JayzProxyFactory(TestInterface.class, url);
                    TestInterface ti = (TestInterface) factory.create();

                    double r = ti.calculate(192.5, 20);
                    Log.d("Proxy", "result=" + r);
                    final String resultStr = "Result=" + r;
                    HelloAndroidActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            makeText(HelloAndroidActivity.this, resultStr, Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                    final String msg = ex.getMessage();
                    Log.d("Proxy", msg);
                    HelloAndroidActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            makeText(HelloAndroidActivity.this, msg, Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });
        try {
            jayzThread.start();
            jayzThread.join();
            // makeText(HelloAndroidActivity.this, "calling of web service ended", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            makeText(HelloAndroidActivity.this, ex.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void onButton1Click() {
        //makeText(this, "onButton1Click...", Toast.LENGTH_LONG).show();
        runJayzClient();
    }

}
