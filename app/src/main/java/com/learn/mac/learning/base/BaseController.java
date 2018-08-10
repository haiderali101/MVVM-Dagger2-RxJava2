package com.learn.mac.learning.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bluelinelabs.conductor.Controller;
import com.learn.mac.learning.di.Injector;

public abstract class BaseController extends Controller {

    private boolean injected = false;

    @Override
    protected void onContextAvailable(@NonNull Context context) {
        if (!injected){
            Injector.inject(this);
            injected = true;
        }
        super.onContextAvailable(context);
    }
}
