package com.learn.mac.learning.di;

import com.bluelinelabs.conductor.Controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import dagger.MapKey;

@MapKey
@Target(ElementType.METHOD)
public @interface ControllerKey {
    Class<? extends Controller> value();
}
