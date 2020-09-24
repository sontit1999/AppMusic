package com.example.appmusic.base;


import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


public class BingdingUtils {
    @BindingAdapter({ "setAdapter"})
    public static void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide
                .with(view.getContext())
                .load(imageUrl)
                .into(view);

    }
    @BindingAdapter({"bind:imageAssest"})
    public static void loadImageAssestToview(ImageView view, String imageAssest) {
        String assetPath =   "file:///android_asset/" + imageAssest;
        Glide.with(view.getContext())
                .load(Uri.parse(assetPath))
                .centerCrop()
                .into(view);

    }
}
