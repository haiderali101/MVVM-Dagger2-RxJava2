package com.learn.mac.learning.di;

import android.app.Activity;
import android.content.Context;

import com.bluelinelabs.conductor.Controller;
import com.learn.mac.learning.base.BaseActivity;
import com.learn.mac.learning.base.BaseController;
import com.learn.mac.learning.base.MyApplication;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;

@ActivityScope
public class ScreenInjector {
    private final Map<Class<? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> screenInjectors;
    private final Map<String, AndroidInjector<? extends Controller>> cache = new HashMap<>();

    @Inject
    public ScreenInjector(Map<Class<? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> screenInjectors) {
        this.screenInjectors = screenInjectors;
    }

    void inject(Controller controller) {
        if (!(controller instanceof BaseController)) {
            throw new IllegalArgumentException("Controller must extend BaseController");
        }

        String instanceId = ((BaseController) controller).getInstanceId();
        if (cache.containsKey(instanceId)) {
            //noinspection unchecked
            ((AndroidInjector<Controller>) cache.get(instanceId)).inject(controller);
            return;
        }

        //noinspection unchecked
        AndroidInjector.Factory<Controller> injectorFactory =
                (AndroidInjector.Factory<Controller>) screenInjectors.get(controller.getClass()).get();
        AndroidInjector<Controller> injector = injectorFactory.create(controller);
        cache.put(instanceId, injector);
        injector.inject(controller);
    }

    void clear(Controller controller){
        cache.remove(controller.getInstanceId());
    }

    static ScreenInjector get(Activity activity){
        if (!(activity instanceof BaseActivity)) {
            throw new IllegalArgumentException("Controller must be Hosted by BaseActivity");
        }
        return ((BaseActivity) activity).getScreenInjector();
    }
}
