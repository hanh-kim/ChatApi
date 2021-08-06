package com.hanhpk.chatapi.api;

import android.content.Context;

import com.hanhpk.chatapi.exception.NoInternetConnectionException;
import com.hanhpk.chatapi.util.Utils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectivityInterceptor implements Interceptor {
    private Context context;

    public ConnectivityInterceptor(Context context) {
        this.context = context;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        if (!Utils.checkNetwork(context)){
            throw new NoInternetConnectionException();
        }
        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}
