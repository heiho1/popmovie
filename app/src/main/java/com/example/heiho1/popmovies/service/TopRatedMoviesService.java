package com.example.heiho1.popmovies.service;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.heiho1.popmovies.PopMoviesApplication;
import com.example.heiho1.popmovies.api.Keys;
import com.example.heiho1.popmovies.api.MovieDb;
import com.example.heiho1.popmovies.model.MovieListResponse;
import com.example.heiho1.popmovies.util.MovieListResponseCache;

import java.io.IOException;

/**
 * Service implementation which provides for popular movies retrieval
 * in a network and ui safe manner
 */
public class TopRatedMoviesService extends CachingService<MovieListResponseCache> {
    private static final String TAG = TopRatedMoviesService.class.getSimpleName();
    public static final String NAME = "top_rated_movies_svc";
    private final MovieDb API = PopMoviesApplication.api();

    public TopRatedMoviesService() {
        this(NAME);
    }

    public TopRatedMoviesService(String name) {
        super(name);
        setCache(PopMoviesApplication.topRatedCache());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int page = intent.getIntExtra("page", 1);
        MovieListResponse topRated = null;

        if (cache.get(page) != null) {
            topRated = cache.get(page);
        } else {
            try {
                topRated = API.listTopRated(Keys.keyFor(Keys.Types.the_movie_db), page).execute().body();
                cache.put(topRated.getPage(), topRated);
            } catch (IOException e) {
                Log.e(TAG, "Failure getting top rated movies", e);
            }
        }
        ResultReceiver receiver = intent.getParcelableExtra("receiver");
        Bundle b = new Bundle();
        b.putParcelable("topRated", topRated);
        receiver.send(0, b);
    }
}
