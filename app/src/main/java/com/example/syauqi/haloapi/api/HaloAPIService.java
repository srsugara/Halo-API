package com.example.syauqi.haloapi.api;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by syauqi on 15/08/17.
 */

public interface HaloAPIService {

    @GET("storyofme")
    Call<ResponseBody> getStoryOfMe(@QueryMap HashMap<String,String> params);

    @FormUrlEncoded
    @POST("post_message")
    Call<ResponseBody> postMessage(@FieldMap HashMap<String, String> params);
}
