package com.learn.mac.learning.base;

import android.app.Application;

import com.learn.mac.learning.di.ActivityInjector;

import javax.inject.Inject;

import dagger.android.DaggerApplication;

public class MyApplication extends Application {

    @Inject
    ActivityInjector activityInjector;

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder().
                applicationModule(new ApplicationModule(this)).
                build();

        component.inject(this);
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
