package com.example.appmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.appmusic.adapter.BannerPageAdapter;
import com.example.appmusic.base.BaseActivity;
import com.example.appmusic.databinding.ActivityMainBinding;
import com.example.appmusic.fragment.TopSongFragment;
import com.example.appmusic.fragment.banner.BannerFragment;
import com.example.appmusic.fragment.banner.BannerViewmodel;
import com.example.appmusic.model.Song;
import com.example.appmusic.service.MusicService;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainViewmodel> {
    boolean isPlaying = false;
    MusicService musicService = null;
    Handler handler;
    int pageNow = 0;
    Animation animation;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(musicService!=null){
                binding.seekBar.setProgress(musicService.getCurrenDurationMP3());
            }
            handler.postDelayed(this,300);
        }
    };
    @Override
    public Class<MainViewmodel> getViewmodel() {
        return MainViewmodel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void setBindingViewmodel() {
        initAnimation();
        binding.setViewmodel(viewmodel);
        // add frag TOP
        AddFragment(new TopSongFragment());
        // set up viewPager
      //  setUpviewPager();
        initHandlerAutoSwipe();
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                final MusicService.MyBindler bindler = (MusicService.MyBindler) iBinder;
                musicService = bindler.getService();
                musicService.setCallback(new MusicService.MusicCallback() {
                    @Override
                    public void onStartPlay(int duration) {
                        binding.circleImageView.startAnimation(animation);
                        binding.seekBar.setMax(duration);
                        binding.btnPlayOrPause.setImageDrawable(getDrawable(R.drawable.ic_baseline_pause_24));
                    }

                    @Override
                    public void onEndPlay() {
                        binding.circleImageView.clearAnimation();
                        binding.seekBar.setMax(0);
                        binding.btnPlayOrPause.setImageDrawable(getDrawable(R.drawable.ic_baseline_play_arrow_24));
                    }
                });
                Toast.makeText(MainActivity.this, "Service connected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);

        viewmodel.SongToPlay.observe(this, new Observer<Song>() {
            @Override
            public void onChanged(Song song) {
                binding.tvnameSong.setText(song.getNameSong());
                binding.tvSinger.setText(song.getNameSinger());
                if(musicService!=null){
                    isPlaying = true;
                    musicService.startNewMp3(song.getLinkSong());
                }
            }
        });
        binding.btnPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlaying){
                    musicService.PausePlay();
                    isPlaying = false;
                    binding.btnPlayOrPause.setImageDrawable(getDrawable(R.drawable.ic_baseline_play_arrow_24));
                }else{
                    musicService.ContinuePlay();
                    isPlaying = true;
                    binding.btnPlayOrPause.setImageDrawable(getDrawable(R.drawable.ic_baseline_pause_24));
                }
            }
        });
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(musicService!=null){
                    musicService.Seekto(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initAnimation() {
       animation = AnimationUtils.loadAnimation(this,R.anim.rotate);
    }

    public MainViewmodel getViewmodelOfActivity(){
        return viewmodel;
    }
    private void initHandlerAutoSwipe() {
          handler = new Handler();
          handler.postDelayed(runnable,300);
    }

    private void setUpviewPager() {
        BannerPageAdapter bannerPageAdapter = new BannerPageAdapter(getSupportFragmentManager());
        bannerPageAdapter.addFragment(new BannerFragment(new Song("https://www.mboxdrive.com/Cam-Tay-Anh-Va-Di-Linh-Hee-C-A-O.mp3","https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/cover/a/0/d/a/a0dae53dbcd9d02478769b6a6a1b07f0.jpg","Cầm tay anh và đi","Linh hee","")),"fragment");
        bannerPageAdapter.addFragment(new BannerFragment(new Song("https://www.mboxdrive.com/bennhauthatkho.mp3","https://photo-zmp3.zadn.vn/covers/0/0/001d278979f5979f8d9010e3f3482981_1507106621.jpg","Bên nhau thật khó","Châu khải phong","")),"fragment");
        bannerPageAdapter.addFragment(new BannerFragment(new Song("https://www.mboxdrive.com/1-Phut-Andiez.mp3","https://i1.sndcdn.com/artworks-000281765348-xvrwga-t500x500.jpg","Một phút","Andz","")),"fragment");
        binding.viewPagerBanner.setAdapter(bannerPageAdapter);
    }

    public void AddFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.framelayout,fragment,fragment.getTag()).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}