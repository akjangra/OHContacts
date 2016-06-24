package com.orionhealth.ohcontacts.utils;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by ashokjangra70@gmail.com on 11/06/16.
 */
public class OkHttpHelper {
    OkHttpClient client = new OkHttpClient();

    // GET request code here
    public String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
