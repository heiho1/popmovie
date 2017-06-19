package com.example.heiho1.popmovies.service;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.heiho1.popmovies.PopMoviesApplication;
import com.example.heiho1.popmovies.api.Keys;
import com.example.heiho1.popmovies.api.MovieDb;
import com.example.heiho1.popmovies.model.MovieDetailResponse;
import com.example.heiho1.popmovies.util.MovieDetailResponseCache;

import java.io.IOException;

/**
 * Service implementation which provides for popular movies retrieval
 * in a network and ui safe manner
 */
public class MovieDetailsService extends CachingService<MovieDetailResponseCache> {
    private static final String TAG = MovieDetailsService.class.getSimpleName();
    public static final String NAME = "movie_details_svc";
    private final MovieDb API = PopMoviesApplication.api();

    public MovieDetailsService() {
        this(NAME);
    }

    public MovieDetailsService(String name) {
        super(name);
        setCache(PopMoviesApplication.detailsCache());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            MovieDetailResponse detail = API.get(intent.getIntExtra("movie_id", 0),
                    Keys.keyFor(Keys.Types.the_movie_db),
                    "videos").execute().body();

            ResultReceiver receiver = intent.getParcelableExtra("receiver");
            Bundle b = new Bundle();
            b.putParcelable("details", detail);
            receiver.send(0, b);
        } catch (IOException e) {
            Log.e(TAG, "Failure retrieving movie detail", e);
        }
    }
}
