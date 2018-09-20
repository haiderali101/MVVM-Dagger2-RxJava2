package com.learn.mac.learning.base;

import android.app.Activity;

import com.learn.mac.learning.home.MainActivity;
import com.learn.mac.learning.home.TestMainActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = TestMainActivityComponent.class)
public abstract class TestActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector<? extends Activity> bindMainActivityInjector(TestMainActivityComponent.Builder builder);
}
