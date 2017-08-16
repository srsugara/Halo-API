package com.example.syauqi.haloapi.dagger;

import com.example.syauqi.haloapi.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by syauqi on 16/08/17.
 */

@Singleton
@Component(
    modules={HaloAPIModule.class}
)
public interface HaloAPIComponent {
    void inject(MainActivity activity);
}
