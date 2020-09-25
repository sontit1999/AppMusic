package com.example.appmusic.adapter;

import com.example.appmusic.BR;
import com.example.appmusic.R;
import com.example.appmusic.base.BaseAdapter;
import com.example.appmusic.base.CBAdapter;
import com.example.appmusic.callback.SongCallback;
import com.example.appmusic.databinding.ItemSongBinding;
import com.example.appmusic.model.Song;

public class SongAdapter extends BaseAdapter<Song, ItemSongBinding> {
    SongCallback callback;

    public void setCallback(SongCallback callback) {
        this.callback = callback;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_song;
    }

    @Override
    public int getIdVariable() {
        return BR.song;
    }

    @Override
    public int getIdVariableOnclick() {
        return BR.callback;
    }

    @Override
    public CBAdapter getOnclick() {
        return callback;
    }
}
