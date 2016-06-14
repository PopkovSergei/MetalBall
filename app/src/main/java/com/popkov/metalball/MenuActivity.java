package com.popkov.metalball;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.popkov.metalball.PackageGame.Game;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final TextView textStartGame = (TextView)findViewById(R.id.start);
        final TextView textQuickGame = (TextView)findViewById(R.id.quick);
        final TextView textSettingsGame = (TextView)findViewById(R.id.settings);
        final TextView textExit = (TextView)findViewById(R.id.exit);

        textStartGame.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/tuschtouch3.ttf"));
        textQuickGame.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/tuschtouch3.ttf"));
        textSettingsGame.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/tuschtouch3.ttf"));
        textExit.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/tuschtouch3.ttf"));

        textStartGame.setOnClickListener(this);
        textQuickGame.setOnClickListener(this);
        textSettingsGame.setOnClickListener(this);
        textExit.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                StartGame(false);
                break;
            case R.id.quick:
                StartGame(true);
                break;
            case R.id.settings:
                Intent settings = new Intent(this, Settings.class);
                startActivity(settings);
                break;
            case R.id.exit:

                stopService(new Intent(this, FonMusic.class));
                finish();
                break;

        }
    }


    public void StartGame(boolean quick_game){


        SharedPreferences mySharedPreferences = getSharedPreferences("SETTINGS_MetalBall", Context.MODE_PRIVATE);
        if(!mySharedPreferences.contains("CART")) {
            SharedPreferences.Editor editor = mySharedPreferences.edit();

            editor.putInt("ENUMERATOR", 0);

            editor.putInt("CART", 6);
            editor.putInt("COLORS", 4);
            editor.putInt("BEST_ENUMERATOR_6_4", 0);

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    editor.putInt("CART_SIZE"+i+j, 0);
                }
            }
            editor.apply();

            Intent settings = new Intent(this, Settings.class);
            settings.putExtra("quick", quick_game);
            settings.putExtra("Game", true);
            startActivityForResult(settings,1);


        } else {
            Intent Game = new Intent(this, com.popkov.metalball.PackageGame.Game.class);
            Game.putExtra("quick", quick_game);
            startActivity(Game);
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //if (data == null) {return;}
        int action = data.getIntExtra("action",0);
        if (action == 1) {
            Intent Game = new Intent(this, Game.class);
            Game.putExtra("quick", false);
            startActivity(Game);
        }
        if (action == 2) {
            Intent Game = new Intent(this, Game.class);
            Game.putExtra("quick", true);
            startActivity(Game);
        }


    }



    @Override
    public void onBackPressed() {
        stopService(new Intent(this, FonMusic.class));
        finish();
    }

}
