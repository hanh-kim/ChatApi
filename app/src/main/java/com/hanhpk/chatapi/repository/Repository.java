package com.hanhpk.chatapi.repository;

import com.hanhpk.chatapi.api.ApiClient;
import com.hanhpk.chatapi.api.ApiService;
import com.hanhpk.chatapi.model.Result;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    private final ApiService apiService;

    public Repository() {
        apiService = ApiClient.getInstance().getApiService();
    }

    public Single<Result> getRespondMessage(String message) {
        return apiService.getRespond(message)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
