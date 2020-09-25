package com.example.appmusic.fragment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.MainActivity;
import com.example.appmusic.R;
import com.example.appmusic.base.BaseFragment;
import com.example.appmusic.callback.SongCallback;
import com.example.appmusic.databinding.FragTopsongBinding;
import com.example.appmusic.model.Song;

import java.util.ArrayList;

public class TopSongFragment extends BaseFragment<FragTopsongBinding,TopSongViewModel> {
    MainActivity activity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getContext();
    }

    @Override
    public Class<TopSongViewModel> getViewmodel() {
        return TopSongViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.frag_topsong;
    }

    @Override
    public void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
    }

    @Override
    public void ViewCreated() {
        setUpRecyclerView();
        viewmodel.getListSong().observe(this, new Observer<ArrayList<Song>>() {
            @Override
            public void onChanged(ArrayList<Song> songs) {
                viewmodel.songAdapter.setList(songs);
            }
        });

        // getSong
        viewmodel.GetSong();
    }

    private void setUpRecyclerView() {
        binding.rvSong.setHasFixedSize(true);
        binding.rvSong.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        binding.rvSong.setAdapter(viewmodel.songAdapter);
        viewmodel.songAdapter.setCallback(new SongCallback() {
            @Override
            public void onSongClick(Song song) {
                Toast.makeText(getActivity(), "Click: " + song.getNameSong(), Toast.LENGTH_SHORT).show();
                activity.getViewmodelOfActivity().setLinkToPlay(song);
            }
        });
    }
}
