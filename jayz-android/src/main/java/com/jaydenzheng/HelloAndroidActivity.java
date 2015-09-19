package com.jaydenzheng;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    public void onButton1Click() {
        Toast.makeText(this, "onButton1Click...", Toast.LENGTH_LONG).show();
        try {
            String url = "http://192.168.15.102:8084/JayzWeb/testJaysService";

            JayzProxyFactory factory = new JayzProxyFactory(TestInterface.class, url);

        //    long t1 = System.currentTimeMillis();
            TestInterface ti = (TestInterface) factory.create();

//
//            String retmsg = ti.method2(100, "hello message");
//            System.out.println("Return message:" + retmsg);

      //      ti.method1();

            double r = ti.calculate(192.5, 20);
           // long t2 = System.currentTimeMillis();
          //  System.out.println("Time spent:" + (t2 - t1) + " ms ");
          //  System.out.println("r=" + r);
            Toast.makeText(this, "r=" + r, Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
        }
    }

}

