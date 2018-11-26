package com.api.aspire.data.retro2client;

import com.api.aspire.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class Retro2Client {
    private final String TAG = Retro2Client.class.getSimpleName();

    /**
     * use for normal api
     * */
    protected OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpBuilder = buildOkHttpClientBuilder();
        return okHttpBuilder.build();
    }

    private OkHttpClient.Builder buildOkHttpClientBuilder() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(provideHttpLoggingInterceptor());
        }

        okHttpBuilder.connectTimeout(60, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(60, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(60, TimeUnit.SECONDS);
        return okHttpBuilder;
    }

    /**
     * @return HttpLogginInterceptor
     */
    private HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
