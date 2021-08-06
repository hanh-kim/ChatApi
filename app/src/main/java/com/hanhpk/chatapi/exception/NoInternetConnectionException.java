package com.hanhpk.chatapi.exception;

import java.io.IOException;

public class NoInternetConnectionException  extends IOException {
    public NoInternetConnectionException() {
        super("No internet connection");
    }
}
