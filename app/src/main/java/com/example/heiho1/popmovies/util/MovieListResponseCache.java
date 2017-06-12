package com.example.heiho1.popmovies.util;

import android.util.LruCache;

import com.example.heiho1.popmovies.model.MovieListResponse;

/**
 * A page oriented cache of movie db response pages suitable for browsing of
 * large lists of results where the cache key is the page number
 *
 * @see https://developer.android.com/reference/android/util/LruCache.html
 */
public class MovieListResponseCache extends LruCache<Integer, MovieListResponse> {

    /**
     * Construct a cache capable of holding at most maxSize entries
     *
     * @param maxSize the maxmum number of cache entries
     */
    public MovieListResponseCache(int maxSize) {
        super(maxSize);
    }
}
