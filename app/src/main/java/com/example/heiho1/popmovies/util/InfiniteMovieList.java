package com.example.heiho1.popmovies.util;

import android.util.Log;
import android.util.LruCache;

import com.example.heiho1.popmovies.PopMoviesApplication;
import com.example.heiho1.popmovies.api.Keys;
import com.example.heiho1.popmovies.api.MovieDb;
import com.example.heiho1.popmovies.model.MovieListResponse;
import com.example.heiho1.popmovies.model.MovieSummary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract generic list implementation backed by a themoviedb search results list, i.e. popular or
 * top-rated movies.  This list will use a backing cache and network access to
 * transparently load and manages pages of movie list data
 */
public abstract class InfiniteMovieList<T extends MovieSummary> extends ArrayList<MovieSummary> {
    private static final String TAG =InfiniteMovieList.class.getSimpleName();
    public static final Integer ITEMS_PER_PAGE = 20;// should this be configurable?

    /**
     * Provides a singleton moviedb API for subclasses
     */
    protected final MovieDb API = PopMoviesApplication.api();
    private final MovieListResponseCache cache;
    private int totalResults = 0;
    private int totalPages = 0;

    /**
     * Construct an infinite movie list backed by the given LruCache
     */
    public InfiniteMovieList() {
        cache = cache();
        MovieListResponse initialData = init();
        totalResults = initialData.getTotalResults();
        totalPages = initialData.getTotalPages();
    }

    /**
     * Function to determine the page containing the item indicated by the given index
     *
     * @param itemIndex of the item as an absolute value
     * @return int  the page containing the given item
     */
    public int getPageForCumulativeIndex(int itemIndex) {
        int idx = Math.abs(itemIndex);// accept no negatives
        if (idx == totalResults) {
            return totalPages; // last item is always on the last page
        }
        int tstPage = idx / ITEMS_PER_PAGE;
        int modulus = idx % ITEMS_PER_PAGE;
        if (modulus == 0) {
            return tstPage;
        }
        return tstPage + 1; // any modulus indicates the next page after
    }

    /**
     * Returns the index of the the given item within its page of data, i.e.
     * itemIndex of 22 would return item index of 2 for page 2
     *
     * @param itemIndex  to convert to a paginated index
     * @return   int  the index of the item within its containing page
     */
    public int getIndexWithinPage(int itemIndex) {
        return totalResults % itemIndex;
    }


    /**
     * Accessor for the MovieSummary, if any, associated with the given index
     *
     * @param index to retrieve a MovieSummary for
     * @return MovieSummary for the given index
     */
    @Override
    public MovieSummary get(int index) {
        super.get(index);
        if (index > totalResults) {
            throw new IndexOutOfBoundsException(String.format("Invalid index %s with max of %s", index, totalResults));
        }
        int pageIdx = getPageForCumulativeIndex(Math.abs(index));
        MovieListResponse check = cache.get(pageIdx);
        if (check == null) {
            try {
                MovieListResponse resp = API.listPopular(Keys.keyFor(Keys.Types.the_movie_db), pageIdx).execute().body();
                cache.put(resp.getPage(), resp);
                check = cache.get(resp.getPage());
            } catch (IOException e) {
                Log.e(TAG, "get: failure getting new data", e);
            }
        }
        List<MovieSummary> summaries = check.getResults();
        return summaries.get(getIndexWithinPage(index));
    }

    /**
     * Initializer for the initial set of response data, i.e. page #1
     *
     * @return MovieListResponse  the initial response list
     */
    public abstract MovieListResponse init();

    /**
     * Initializer for the cache to be used to store movie list data
     *
     * @return MovieListResponseCache  suitable for caching MovieListResponse results
     */
    public abstract MovieListResponseCache cache();

}
