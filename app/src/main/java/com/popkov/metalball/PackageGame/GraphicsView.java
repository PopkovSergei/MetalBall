package com.popkov.metalball.PackageGame;

import android.content.Context;
import android.view.InflateException;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



public class GraphicsView extends SurfaceView implements SurfaceHolder.Callback {

    final String TAG ="GraphicsView";
    private DrawThread drewThread;

    public  GraphicsView (Context context){super(context);getHolder().addCallback(this);}

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        drewThread = new DrawThread(getHolder(),getResources());
        drewThread.run();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        boolean retry = true;
        while (retry){

            try{
            drewThread.join();
            retry = false;}
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {





        return true;
    }


}
