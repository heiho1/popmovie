package com.example.heiho1.popmovies.util;

import android.util.LruCache;

import com.example.heiho1.popmovies.model.MovieDetailResponse;

/**
 * A cache of last recently used movie details such that the most requested details
 * are retained for the longest duration
 */
public class MovieDetailResponseCache extends LruCache<Integer, MovieDetailResponse> {
    /**
     * Construct a cache capable of holding at most maxSize entries
     *
     * @param maxSize the maxmum number of cache entries
     */
    public MovieDetailResponseCache(int maxSize) {
        super(maxSize);
    }
}
