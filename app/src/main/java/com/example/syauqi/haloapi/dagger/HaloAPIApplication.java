package com.example.syauqi.haloapi.dagger;

import android.app.Application;

import com.example.syauqi.haloapi.util.Const;

/**
 * Created by syauqi on 16/08/17.
 */

public class HaloAPIApplication extends Application {
    private HaloAPIComponent myComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        myComponent = DaggerHaloAPIComponent.builder()// This also corresponds to the name of your module: %component_name%Module
                .haloAPIModule(new HaloAPIModule(Const.BASE_API_URL))
                .build();
    }

    public HaloAPIComponent getMyComponent(){
        return myComponent;
    }
}
