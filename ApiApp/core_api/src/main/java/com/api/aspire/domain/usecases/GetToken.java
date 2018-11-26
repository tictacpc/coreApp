package com.api.aspire.domain.usecases;

import android.text.TextUtils;

import com.api.aspire.common.constant.ErrCode;
import com.api.aspire.data.restapi.OAuthApi;
import com.api.aspire.data.retro2client.AppHttpClient;
import com.api.aspire.data.entity.oauth.User;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;

public class GetToken {

    public GetToken() {
    }

    public Single<String> getTokenService(String uniqueId) {
        return Single.create(e -> {
            OAuthApi OAuthApi = AppHttpClient.getInstance().getOAuthApi();
            Call<User> requestTokenCall = OAuthApi.register(uniqueId);

            Response<User> requestTokenResponse = requestTokenCall.execute();

            if (!requestTokenResponse.isSuccessful()) {
                throw new Exception(requestTokenResponse.errorBody().string());
            }

            String accessToken = requestTokenResponse.body().getApiKey();

            if (TextUtils.isEmpty(accessToken)) {
                throw new Exception(ErrCode.API_ERROR.name());
            } else {
                e.onSuccess(accessToken);
            }
        });
    }

}