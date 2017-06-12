package com.example.heiho1.popmovies.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.example.heiho1.popmovies.PopMoviesApplication;
import com.example.heiho1.popmovies.api.Keys;
import com.example.heiho1.popmovies.api.MovieDb;
import com.example.heiho1.popmovies.model.MovieListResponse;
import com.example.heiho1.popmovies.util.MovieListResponseCache;
import com.example.heiho1.popmovies.view.recycler.MovieSummaryResponseAdapter;

import java.io.IOException;

/**
 * Service implementation which provides for popular movies retrieval
 * in a network and ui safe manner
 */
public class PopularMoviesService extends CachingService<MovieListResponseCache> {
    private static final String TAG = PopularMoviesService.class.getSimpleName();
    public static final String NAME = "popular_movies_svc";
    private final MovieDb API = PopMoviesApplication.api();

    public PopularMoviesService() {
        this(NAME);
    }


    public PopularMoviesService(String name) {
        super(name);
        setCache(PopMoviesApplication.popularCache());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            MovieListResponse popular = API.listPopular(Keys.keyFor(Keys.Types.the_movie_db),
                    intent.getIntExtra("page", 1)).execute().body();
            ResultReceiver receiver = intent.getParcelableExtra("receiver");
            Bundle b = new Bundle();
            b.putParcelable("popular", popular);
            receiver.send(0, b);
        } catch (IOException e) {
            Log.e(TAG, "Failure getting popular movies", e);
//            Snackbar.make(popularMovies, "Sadly the popular movies are not available.  Please try again.", Snackbar.LENGTH_SHORT);
        }
    }
}
