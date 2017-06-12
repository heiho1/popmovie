package com.example.heiho1.popmovies.service;

import com.example.heiho1.popmovies.util.MovieDetailResponseCache;
import com.example.heiho1.popmovies.util.MovieListResponseCache;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Test to verify the generic behavior of CachingService
 * implementations
 *
 * @see CachingService
 */
public class CachingServicesTest {
    @Test
    public void servicesHaveTheirOwnCaches() {
        MovieDetailsService detailsService = new MovieDetailsService("movie_details_svc");
        PopularMoviesService popularService = new PopularMoviesService("popular_movies_svc");
        TopRatedMoviesService topRatedService = new TopRatedMoviesService("top_rated_movies_svc");

        assertNotNull(detailsService.getCache());
        assertTrue(detailsService.getCache() instanceof MovieDetailResponseCache);

        assertNotNull(popularService.getCache());
        assertTrue(popularService.getCache() instanceof MovieListResponseCache);

        assertNotNull(topRatedService.getCache());
        assertTrue(topRatedService.getCache() instanceof MovieListResponseCache);
    }

}
