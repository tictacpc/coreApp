package com.api.aspire.data.entity.oauth;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("api_key")
    String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}
