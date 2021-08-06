package com.hanhpk.chatapi.api;

import com.hanhpk.chatapi.model.Result;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    @Headers({
            "x-rapidapi-key:0f3958b02bmsh5384da24ab89880p1663a4jsn928eb910a6c5",
            "x-rapidapi-host:ai-chatbot.p.rapidapi.com"
    })
    @GET("chat/free?uid=user1")
    Single<Result> getRespond(@Query("message") String message);
}
