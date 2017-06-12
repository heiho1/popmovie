package com.example.heiho1.popmovies.util;

import android.util.Log;

import com.example.heiho1.popmovies.PopMoviesApplication;
import com.example.heiho1.popmovies.api.Keys;
import com.example.heiho1.popmovies.model.MovieListResponse;
import com.example.heiho1.popmovies.model.MovieSummary;

import java.io.IOException;

/**
 * An "infinite" list of all possible popular movies from themoviedb
 */
public class InfinitePopularMoviesList extends InfiniteMovieList<MovieSummary> {
    private  static final String TAG = InfinitePopularMoviesList.class.getSimpleName();

    @Override
    public MovieListResponse init() {
        try {
            return API.listPopular(Keys.keyFor(Keys.Types.the_movie_db), 1).execute().body();
        } catch (IOException e) {
            Log.e(TAG, "Failure listing popular", e);
        }
        throw new IllegalStateException("Popular movies not available");
    }

    @Override
    public MovieListResponseCache cache() {
        return PopMoviesApplication.popularCache();
    }
}
