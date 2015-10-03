/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaydenzheng.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.text.SimpleDateFormat;

/**
 *
 * @author Rong Zheng
 */
public class ClockView extends View implements Runnable, OnGestureListener, RotationGestureDetector.OnRotationGestureListener {

    private Context context;
    Handler mHandler = new Handler();

    private GestureDetector gestureScanner;
    private ScaleGestureDetector mScaleDetector;
    private float scaleFactor = 1.0f;
    private RotationGestureDetector mRotationDetector;
    
    private float rotatedAngle = 0;
    private boolean rotating = false;

   // private boolean actionMoved = false;

    public ClockView(Context context) {
        super(context);
        this.context = context;
     //   mHandler.removeCallbacks(this);
        //   mHandler.post(this);
        gestureScanner = new GestureDetector(context, this);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        mRotationDetector = new RotationGestureDetector(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //Thread thread = new Thread(this);
        //thread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint myPaint = new Paint();
        myPaint.setColor(Color.RED);
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(10);
        int w = this.getWidth();
        int h = this.getHeight();
        Log.d("login", "w=" + w + ", h=" + h);
        canvas.drawRect(30, 30, w - 30, h - 30, myPaint);

        myPaint.setColor(Color.BLUE);
        canvas.drawCircle(w / 2, h / 2, Math.min(w / 2, h / 2) - 50, myPaint);

        myPaint.setColor(Color.BLACK);
        //this.setTextSizeForWidth(myPaint, 200, "Hello, World. 你好吗？");
        //myPaint.setTextSize(50);
        float textSize = 18 * getResources().getDisplayMetrics().scaledDensity;
        Log.d("clock", "TextSize:" + textSize);
        FontMetrics fm = new FontMetrics();
        myPaint.getFontMetrics(fm);
        myPaint.setAntiAlias(true);
        //  myPaint.setTypeface(Typeface.createFromAsset(context.getAssets(), "arialbi.ttf"));
        myPaint.setTextSize(textSize * this.scaleFactor);

        myPaint.setStyle(Paint.Style.FILL);

        java.util.Date now = new java.util.Date();
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss:SS");
        canvas.drawText(df.format(now), 150, 300, myPaint);

    }

    /**
     * Sets the text size for a Paint object so a given string of text will be a
     * given width.
     *
     * @param paint the Paint to set the text size for
     * @param desiredWidth the desired width
     * @param text the text that should be that width
     */
    private void setTextSizeForWidth(Paint paint, float desiredWidth,
            String text) {

        // Pick a reasonably large value for the test. Larger values produce
        // more accurate results, but may cause problems with hardware
        // acceleration. But there are workarounds for that, too; refer to
        // http://stackoverflow.com/questions/6253528/font-size-too-large-to-fit-in-cache
        final float testTextSize = 48f;

        // Get the bounds of the text, using our testTextSize.
        paint.setTextSize(testTextSize);
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        // Calculate the desired size as a proportion of our testTextSize.
        float desiredTextSize = testTextSize * desiredWidth / bounds.width();

        // int scaledSize = getResources().getDimensionPixelSize()
        Log.d("clock", "desiredTextSize:" + desiredTextSize);
        // Set the paint for that size.
        paint.setTextSize(desiredTextSize * this.scaleFactor);
    }

    @Override
    public void run() {
        Log.d("clock", "run");
        invalidate();
        mHandler.postDelayed(this, 50); // 20ms == 60fps
    }

    public void stop() {
        mHandler.removeCallbacks(this);
    }

    public void start() {
      //  mHandler.removeCallbacks(this);
        //  mHandler.post(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       // this.actionMoved = (event.getActionMasked() == MotionEvent.ACTION_MOVE);
        gestureScanner.onTouchEvent(event);
        this.mScaleDetector.onTouchEvent(event);
        mRotationDetector.onTouchEvent(event);

        return true;
    }

    @Override
    public boolean onDown (MotionEvent me){
            Log.d("clock", "onDown...");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent me) {
        Log.d("clock", "onShowPress");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent me) {
        Log.d("clock", "onSingleTapUp...");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent me, MotionEvent me1, float dx, float dy) {
        Log.d("clock", "onScrool...dx=" + dx + ", dy=" + dy);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent me) {
        Log.d("clock", "onLongPress...");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float vx, float vy) {
        Log.d("clock", "onFling...vx=" + vx + ", vy=" + vy);
        if (vx > 10) {
            Log.d("clock", "swap right");
        } else if (vx < -10) {
            Log.d("Clock", "swap left");
        }

        return false;
    }

    @Override
    public void OnRotation(RotationGestureDetector rotationDetector) {
        float angle = rotationDetector.getAngle();

        Log.d("RotationGestureDetector", "Rotation: " + Float.toString(angle)  );
        this.rotatedAngle = angle;
    }

    @Override
    public void OnRotationStart() {
        this.rotatedAngle = 0;
        this.rotating = true;
        Log.d("clock", "Rotation Started." );
    }

    @Override
    public void OnRotationStop() {
        this.rotating = false;
        Log.d("clock", "Rotation Stopped." );
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 2.0f));
            Log.d("clock", "scale factor=" + scaleFactor);
            ClockView.this.invalidate();
            return true;
        }
    }
}
