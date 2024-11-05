package com.example.canvasandgraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MyView extends SurfaceView implements Runnable {

    SurfaceHolder holder;
    boolean threadRunning = true;
    boolean isRunning = true;
    Canvas c;


    public MyView(Context context) {
        super(context);
        this.holder = getHolder();

    }

    @Override
    public void run() {
        while (threadRunning) {
            if (isRunning) {
                if (!holder.getSurface().isValid())
                    continue;
                c = null;

                try {
                    c = this.getHolder().lockCanvas();
                    synchronized (this.getHolder()) {


                    }

                } catch (Exception e) {
                } finally {
                    if (c != null) {
                        this.getHolder().unlockCanvasAndPost(c);

                    }

                }
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //code to execute when touch is detected
        return true;
    }

    public void pause() {
        isRunning = false;
    }

    public void resume() {
        isRunning = true;
    }

    public void destroy() {
        isRunning = false;
        ((MainActivity) getContext()).finish();
    }
}
