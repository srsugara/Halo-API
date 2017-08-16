package com.example.syauqi.haloapi.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by syauqi on 16/08/17.
 */

@Module
public class HaloAPIModule {
    private String url;

    public HaloAPIModule(String url){
        this.url = url;
    }

    @Provides
    @Singleton
    public Retrofit initialRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
