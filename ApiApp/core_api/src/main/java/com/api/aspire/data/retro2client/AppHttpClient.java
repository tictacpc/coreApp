package com.api.aspire.data.retro2client;

import com.api.aspire.BuildConfig;
import com.api.aspire.data.restapi.OAuthApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppHttpClient extends Retro2Client {

    private OAuthApi OAuthApi;

    private static class AppHttpClientHelper {
        private static final AppHttpClient INSTANCE = new AppHttpClient();
    }

    public static AppHttpClient getInstance() {
        return AppHttpClientHelper.INSTANCE;
    }

    public OAuthApi getOAuthApi() {
        return OAuthApi;
    }

    private AppHttpClient() {

        final Retrofit aspireOauthRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.WS_TOKEN)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideOkHttpClient())
                .build();

//        final Retrofit aspireCreateProfileRetrofit = new Retrofit.Builder()
//                .baseUrl(BuildConfig.WS_TOKEN_SERVICE)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(provideOkHttpClient())
//                .build();

        OAuthApi = aspireOauthRetrofit.create(OAuthApi.class);
//        aspireCreateProfileApi = aspireCreateProfileRetrofit.create(AspireCreateProfileApi.class);


    }


}
