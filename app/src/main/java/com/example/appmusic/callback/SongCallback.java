package com.example.appmusic.callback;

import com.example.appmusic.base.CBAdapter;
import com.example.appmusic.model.Song;

public interface SongCallback extends CBAdapter {
    void onSongClick(Song song);
}
