package com.example.appmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appmusic.base.BaseActivity;
import com.example.appmusic.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainViewmodel> {

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
        binding.setViewmodel(viewmodel);
    }
}