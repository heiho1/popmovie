package com.example.heiho1.popmovies.util;

import android.util.Log;

import com.example.heiho1.popmovies.PopMoviesApplication;
import com.example.heiho1.popmovies.api.Keys;
import com.example.heiho1.popmovies.model.MovieListResponse;
import com.example.heiho1.popmovies.model.MovieSummary;

import java.io.IOException;

/**
 * An "infinite" list of all possible top rated movies from themoviedb
 */
public class InfiniteTopRatedMoviesList extends InfiniteMovieList<MovieSummary> {
    private  static final String TAG = InfinitePopularMoviesList.class.getSimpleName();

    @Override
    public MovieListResponse init() {
        try {
            return API.listTopRated(Keys.keyFor(Keys.Types.the_movie_db), 1).execute().body();
        } catch (IOException e) {
            Log.e(TAG, "Failure listing top rated", e);
        }
        throw new IllegalStateException("Top rated movies not available");
    }

    @Override
    public MovieListResponseCache cache() {
        return PopMoviesApplication.topRatedCache();
    }
}

