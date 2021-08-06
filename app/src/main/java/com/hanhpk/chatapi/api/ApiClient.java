package com.hanhpk.chatapi.api;

import com.hanhpk.chatapi.App;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private final String BASE_URL = "https://ai-chatbot.p.rapidapi.com/";
    private static ApiClient singleton;
    private static ApiService apiService;
    private Retrofit retrofit;

    private ApiClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        builder.addInterceptor(loggingInterceptor);

        OkHttpClient client = builder.addInterceptor(new ConnectivityInterceptor(App.getInstance()))
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60,TimeUnit.SECONDS)
                .build();


         retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static ApiClient getInstance() {
        if (singleton == null) {
            singleton = new ApiClient();
        }
        return singleton;
    }

    public ApiService getApiService() {
        return apiService;
    }

}
