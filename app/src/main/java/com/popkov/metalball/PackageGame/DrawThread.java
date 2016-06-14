package com.popkov.metalball.PackageGame;


import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class DrawThread extends Thread {

    float chastota = 100.0f;
    int i;
    private  SurfaceHolder surfaceHolder;

    public DrawThread(SurfaceHolder surfaceHolder, Resources resources) {

        this.surfaceHolder = surfaceHolder;


    }

    @Override
    public void run() {
        super.run();

        Canvas canvas;
        for (i=0;i<(int)chastota;){

            canvas = null;
            try {

            }finally {
                if (canvas != null)
                {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

        }

    }
}
