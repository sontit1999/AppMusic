package com.example.appmusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    MediaPlayer player;
    MyBindler bindler = new MyBindler();



    MusicCallback callback;
    public void setCallback(MusicCallback callback) {
        this.callback = callback;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "On bind", Toast.LENGTH_SHORT).show();
        return bindler;
    }
    public class MyBindler extends Binder {
        public MusicService getService(){
            return MusicService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
               if(callback!=null){
                   callback.onEndPlay();
               }
            }
        });
        Toast.makeText(this, "On create Service!", Toast.LENGTH_SHORT).show();
    }
    public void PausePlay(){
        player.pause();
    }
    public void startNewMp3(String link){
        try {
            player.stop();
            player.reset();
            player.setDataSource(link);
            player.prepareAsync();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    if(callback!=null){
                        callback.onStartPlay(player.getDuration()/1000);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void ContinuePlay(){
        player.start();
    }
    public int getDurationMP3(){
        return player.getDuration()/1000;
    }
    public int getCurrenDurationMP3(){
        return player.getCurrentPosition()/1000;
    }
    public void Seekto(int postition){
        if(player!=null){
            player.seekTo(postition*1000);
        }
    }
    public interface MusicCallback{
        void onStartPlay(int duration);
        void onEndPlay();
    }
}
