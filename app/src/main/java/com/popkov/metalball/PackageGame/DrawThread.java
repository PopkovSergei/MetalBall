package com.popkov.metalball.PackageGame;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.popkov.metalball.R;


public class DrawThread extends Thread {


    private Bitmap ball,map,blockMap,startMap,finishMap;
    float chastota = 100.0f;
    boolean go = true;
    private  SurfaceHolder surfaceHolder;
    private double height,width;
    double tx,ty;
    int sizeMap;
    int mx =20 , my = 25;
    Logics logics = new Logics();


    public DrawThread(SurfaceHolder surfaceHolder, Resources resources) {

        this.surfaceHolder = surfaceHolder;

        ball = BitmapFactory.decodeResource(resources, R.drawable.ball);
        map = BitmapFactory.decodeResource(resources, R.drawable.map);
        blockMap = BitmapFactory.decodeResource(resources, R.drawable.blockmap);
        startMap = BitmapFactory.decodeResource(resources, R.drawable.startmap);
        finishMap = BitmapFactory.decodeResource(resources, R.drawable.finishmap);

    }

    @Override
    public void run() {
        super.run();

        go = true;
        Canvas canvas;
        while (go){

            canvas = null;
            try {

                canvas = surfaceHolder.lockCanvas();
                height = canvas.getHeight();
                width = canvas.getWidth();

                if ((width/mx)<(height/my)){
                    tx = ( width / 2) - ((width * 0.9) / 2);
                    sizeMap = (int) (width*0.9/mx);
                    ty = (height / 2) - ((sizeMap * my) / 2);
                }else {
                    ty = ( height / 2) - ((height * 0.9) / 2);
                    sizeMap = (int) (height*0.9/my);
                    tx = (width / 2) - ((sizeMap * mx) / 2);
                }

                for (int x = 0; x<mx; x++){

                    for (int y = 0; y<my; y++){
                        if (logics.mapMas[x][y]== 0) {canvas.drawBitmap(map, (int)(tx + (x * sizeMap)), (int)(ty+ (y * sizeMap)), null);}
                        if (logics.mapMas[x][y]== 1) {canvas.drawBitmap(blockMap, (int)(tx + (x * sizeMap)), (int)(ty+ (y * sizeMap)), null);}
                        if (logics.mapMas[x][y]== 2) {canvas.drawBitmap(startMap, (int)(tx + (x * sizeMap)), (int)(ty+ (y * sizeMap)), null);}
                        if (logics.mapMas[x][y]== 3) {canvas.drawBitmap(finishMap, (int)(tx + (x * sizeMap)), (int)(ty+ (y * sizeMap)), null);}
                    }

                }



            }finally {
                if (canvas != null)
                {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

        }

    }
}
