package com.popkov.metalball.PackageGame;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;


public class Game extends Activity {

    int nMap = 1;
    final String TAG ="Game";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        Log.d(TAG,"width ="+ width + "height = " + height);

        setContentView(new GraphicsView(this));

    }



}

