package com.learn.mac.learning.base;

import com.learn.mac.learning.data.TestRepoServiceModule;
import com.learn.mac.learning.networking.ServiceModule;
import com.learn.mac.learning.trending.TrendingReposControllerTest;
import com.learn.mac.learning.ui.NavigationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        NavigationModule.class
})
public interface TestApplicationComponent extends ApplicationComponent{
    void inject(TrendingReposControllerTest trendingReposControllerTest);
}
