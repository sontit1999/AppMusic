package com.example.appmusic.fragment.banner;

import androidx.lifecycle.MutableLiveData;

import com.example.appmusic.base.BaseViewmodel;
import com.example.appmusic.databinding.FragBannerBinding;
import com.example.appmusic.model.Song;

public class BannerViewmodel extends BaseViewmodel {

    MutableLiveData<Song> songMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Song> getSongMutableLiveData() {
        return songMutableLiveData;
    }
    public void setSongMutableLiveData(Song song){
        songMutableLiveData.postValue(song);
    }
}
