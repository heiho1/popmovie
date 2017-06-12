package com.example.heiho1.popmovies.util;

import com.example.heiho1.popmovies.api.Keys;
import com.example.heiho1.popmovies.api.MovieDb;
import com.example.heiho1.popmovies.model.MovieListResponse;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Response;

import static com.example.heiho1.popmovies.PopMoviesApplication.retrofit;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * Specifies the expected behavior of a MovieListResponseCache
 * for handling browsing of arbitrarily long lists of
 * movies
 */
public class MovieListResponseCacheTest {
    private MovieDb api = retrofit().create(MovieDb.class);

    @Test
    public void moviesCanBeCachedWithinAConstrainedRange() throws IOException {
        MovieListResponseCache cache = new MovieListResponseCache(10);

        for (int i : new int[] {1,2,3,4,5,6,7,8,9,10,11}) {
            Response<MovieListResponse> response = api.listPopular(Keys.keyFor(Keys.Types.the_movie_db), i).execute();
            cache.put(i, response.body());
        }

        // check the cache boundaries
        Object empty = cache.get(1);
        Object firstEntry = cache.get(2);
        Object lastEntry = cache.get(11);
        Object pastTheEnd = cache.get(12);

        // the first query should have been evicted
        assertNull(empty);
        assertNotNull(firstEntry);
        assertNotNull(lastEntry);
        // we never queried past the end
        assertNull(pastTheEnd);
    }
}
