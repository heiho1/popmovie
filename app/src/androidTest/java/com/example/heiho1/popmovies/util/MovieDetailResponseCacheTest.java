package com.example.heiho1.popmovies.util;

import com.example.heiho1.popmovies.api.Keys;
import com.example.heiho1.popmovies.api.MovieDb;
import com.example.heiho1.popmovies.model.MovieDetailResponse;

import org.junit.Test;

import java.io.IOException;

import static com.example.heiho1.popmovies.PopMoviesApplication.retrofit;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Validation that the response cache is able to optimally cache the most recently used
 * MovieDetailResponse
 */
public class MovieDetailResponseCacheTest {
    private MovieDb api = retrofit().create(MovieDb.class);

    @Test
    public void movieDetailsCanBeCachedWithinAConstrainedRange() throws IOException {
        MovieDetailResponseCache cache = new MovieDetailResponseCache(1);
        int jackReacherMovieId = 343611;
        int wonderWomanMovieId = 297762;

        MovieDetailResponse jack = api.get(jackReacherMovieId, Keys.keyFor(Keys.Types.the_movie_db), "videos").execute().body();
        MovieDetailResponse jill = api.get(wonderWomanMovieId, Keys.keyFor(Keys.Types.the_movie_db), "videos").execute().body();

        // finding jack updates the hit count
        cache.put(jack.getId(), jack);
        assertNotNull(cache.get(jack.getId()));
        assertTrue(cache.hitCount() == 1);
        assertNotNull(cache.get(jack.getId()));
        assertTrue(cache.hitCount() == 2);

        // adding jill should remove jack
        cache.put(jill.getId(), jill);
        assertNull(cache.get(jack.getId()));
        // no jack present means no hit count update
        assertTrue(cache.hitCount() == 2);

        // now find jill
        assertNotNull(cache.get(jill.getId()));
        assertTrue(cache.hitCount() == 3);
        assertNotNull(cache.get(jill.getId()));
        assertTrue(cache.hitCount() == 4);

        // finding jill doesn't bring jack back
        assertNull(cache.get(jack.getId()));
        assertTrue(cache.hitCount() == 4);

        // test eviction
        cache.evictAll();
        // at this point we have only evicted two things in total
        assertTrue(cache.evictionCount() == 2);
    }
}
