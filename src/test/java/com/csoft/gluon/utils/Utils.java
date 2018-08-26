package com.csoft.gluon.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Utils {

    private Utils() {
    }

    public static Response sendHttpRequest(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        return client.newCall(request).execute();
    }
}
