package com.learn.mac.learning.home;

import com.bluelinelabs.conductor.Controller;
import com.learn.mac.learning.di.ControllerKey;
import com.learn.mac.learning.trending.TrendingReposComponent;
import com.learn.mac.learning.trending.TrendingReposController;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        TrendingReposComponent.class
} )
public abstract class TestScreenBindingModule {
    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(TrendingReposComponent.Builder builder);

}
