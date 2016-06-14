package com.popkov.metalball;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class FonMusic extends Service {

        MediaPlayer player;

        @Override
        public IBinder onBind(Intent intent) {
                return null;
        }

        @Override
        public void onCreate() {


                player = MediaPlayer.create(this, R.raw.fon);
                player.setLooping(true);
                player.setVolume(0.5f, 0.5f);
        }

        @Override
        public void onDestroy() {

                player.stop();
        }

        @Override
        public void onStart(Intent intent, int startid) {

                player.start();
        }
}