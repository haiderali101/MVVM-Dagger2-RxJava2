package com.learn.mac.learning.base;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
})
public interface ApplicationComponent {

    void inject(MyApplication myApplication);
}
