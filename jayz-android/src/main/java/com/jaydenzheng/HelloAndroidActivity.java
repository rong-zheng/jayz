package com.jaydenzheng;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class HelloAndroidActivity extends Activity {

    private Button button1;

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        this.button1 = (Button) findViewById(R.id.button1);
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                onButton1Click();
            }

        };
        this.button1.setOnClickListener(buttonClickListener);
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
        }catch (Exception ex) {
            makeText(HelloAndroidActivity.this, ex.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void onButton1Click() {
        //makeText(this, "onButton1Click...", Toast.LENGTH_LONG).show();
        runJayzClient();
    }

}

