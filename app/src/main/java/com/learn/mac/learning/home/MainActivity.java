package com.learn.mac.learning.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bluelinelabs.conductor.Controller;
import com.learn.mac.learning.R;
import com.learn.mac.learning.base.BaseActivity;
import com.learn.mac.learning.trending.TrendingReposController;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected Controller initialScreen() {
        return new TrendingReposController();
    }
}
