package com.api.aspire.domain.model;

import com.google.gson.annotations.SerializedName;

public class AuthData {

    @SerializedName("token")
    public String accessToken;
    @SerializedName("timeExpireToken")
    public long timeExpireToken;

    public AuthData(String accessToken, long timeExpireToken) {
        this.accessToken = accessToken;
        this.timeExpireToken = timeExpireToken;
    }
}
