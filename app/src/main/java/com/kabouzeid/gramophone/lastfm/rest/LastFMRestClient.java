package com.kabouzeid.gramophone.lastfm.rest;

import android.content.Context;

import com.kabouzeid.gramophone.lastfm.rest.service.LastFMService;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * @author Karim Abou Zeid (kabouzeid)
 */
public class LastFMRestClient {
    public static final String BASE_URL = "http://ws.audioscrobbler.com/2.0";

    private LastFMService apiService;

    public LastFMRestClient(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient();

        File cacheDir = new File(context.getCacheDir().getAbsolutePath(), "/okhttp-lastfm/");
        if (cacheDir.mkdirs() || cacheDir.isDirectory()) {
            okHttpClient.setCache(new Cache(cacheDir, 1024 * 1024 * 10));
        }

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setClient(new OkClient(okHttpClient))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestInterceptor.RequestFacade request) {
                        request.addHeader("Cache-Control", String.format("max-age=%d, max-stale=%d", 31536000, 31536000));
                    }
                })
                .build();

        apiService = restAdapter.create(LastFMService.class);
    }

    public LastFMService getApiService() {
        return apiService;
    }
}