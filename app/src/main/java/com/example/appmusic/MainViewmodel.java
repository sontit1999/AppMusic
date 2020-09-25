package com.example.appmusic;

import androidx.lifecycle.MutableLiveData;

import com.example.appmusic.adapter.BannerPageAdapter;
import com.example.appmusic.base.BaseViewmodel;
import com.example.appmusic.model.Song;

public class MainViewmodel extends BaseViewmodel {
    MutableLiveData<Song> SongToPlay = new MutableLiveData<>();
    public void setLinkToPlay(Song song){
        SongToPlay.postValue(song);
    }
}
