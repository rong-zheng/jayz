package com.jaydenzheng;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class HelloAndroidActivity extends Activity {

    private Button button1;
   // private OrientationEventListener mOrientationListener;
    private int currentOrientation;
    private ViewManager currentPage;

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

        /*
        mOrientationListener = new OrientationEventListener(this,
                SensorManager.SENSOR_DELAY_NORMAL) {

                    @Override
                    public void onOrientationChanged(int orientation) {
                        onOrientationChange(orientation);
                    }
                };
        */

        android.content.res.Configuration config = this.getResources().getConfiguration();
        Log.d("onCreate", "---------------------------------");
        this.currentOrientation  = config.orientation;

       // mOrientationListener.disable();
        /*
        if (mOrientationListener.canDetectOrientation() == true) {
            Log.d("act", "Can detect orientation");
            mOrientationListener.enable();
        } else {
            Log.d("act", "Cannot detect orientation");
            mOrientationListener.disable();
        }
        */
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
//        MyViewManager viewMgr = new MyViewManager(this);
//    //    Scene scene1 = new Scene(null)
//        this.setContentView(viewMgr.getViewGroup());
        LoginPage ciMgr = new LoginPage(this);
        this.currentPage = ciMgr;
        this.setContentView(this.currentPage.getViewGroup());

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

    public void switchToPage1() {
        MyViewManager viewMgr = new MyViewManager(this);
        this.setContentView(viewMgr.getViewGroup());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(this.getClass().getName(), "orientation:" + newConfig.orientation);
        this.currentPage.refreshLayout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // mOrientationListener.disable();
    }

    public void onOrientationChange(int orientation) {
     //   Log.d("act", "Orientation changed to " + orientation);

        android.content.res.Configuration config = this.getResources().getConfiguration();
       Log.d(this.getClass().getName(), "orientation:" + config.orientation +", currentOrientaion:" + this.currentOrientation);
        if (config.orientation != this.currentOrientation) {
            Log.d("act", "ORIENTATION CHANGED...");
            this.currentOrientation = config.orientation;
        }

    }
}
