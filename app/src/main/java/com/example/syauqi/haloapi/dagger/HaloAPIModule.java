package com.example.syauqi.haloapi.dagger;

import com.example.syauqi.haloapi.util.Const;

import javax.inject.Named;
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

    @Provides
    @Singleton
    @Named("service user")
    public Retrofit initialRetrofit1(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    @Named("service twohgo")
    public Retrofit initialRetrofit2(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
