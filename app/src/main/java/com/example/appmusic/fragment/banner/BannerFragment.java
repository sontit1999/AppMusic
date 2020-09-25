package com.example.appmusic.fragment.banner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.appmusic.R;
import com.example.appmusic.base.BaseFragment;
import com.example.appmusic.databinding.FragBannerBinding;
import com.example.appmusic.model.Song;

public class BannerFragment extends BaseFragment<FragBannerBinding,BannerViewmodel> {
    Song song = null;
    public BannerFragment() {
    }
    public BannerFragment(Song song) {
       this.song = song;
    }
    @Override
    public Class<BannerViewmodel> getViewmodel() {
        return BannerViewmodel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.frag_banner;
    }

    @Override
    public void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
    }

    @Override
    public void ViewCreated() {
         viewmodel.getSongMutableLiveData().observe(this, new Observer<Song>() {
             @Override
             public void onChanged(Song song) {
                 binding.setSong(song);
             }
         });
         if(song!=null){
             viewmodel.setSongMutableLiveData(song);
         }
    }
}
