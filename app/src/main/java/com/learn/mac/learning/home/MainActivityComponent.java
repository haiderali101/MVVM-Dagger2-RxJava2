package com.learn.mac.learning.home;

import com.learn.mac.learning.di.ActivityScope;
import com.learn.mac.learning.ui.NavigationModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = {
        MainScreenBindingModule.class,
        NavigationModule.class,
})
public interface MainActivityComponent extends AndroidInjector<MainActivity>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{

        @Override
        public void seedInstance(MainActivity instance) {

        }
    }
}
