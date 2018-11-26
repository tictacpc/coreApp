package com.api.aspire.data.restapi;

import com.api.aspire.data.entity.oauth.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OAuthApi {

    @FormUrlEncoded
    @POST("notes/user/register")
    Call<User> register(@Field("device_id") String deviceId);
}