package com.example.heiho1.popmovies.api

import com.example.heiho1.popmovies.model.MovieDetailResponse
import com.example.heiho1.popmovies.model.MovieListResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import spock.lang.Shared
import spock.lang.Specification

/**
 * Specifies the expected behavior of themoviedb.org's REST API
 *
 * @see https://www.themoviedb.org/documentation/api
 */
class MovieDbSpec extends Specification {
    @Shared
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MovieDb.THE_MOVIE_DB_HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    def "As an authorized user of themoviedb.org I can list popular movies"() {
        setup: "A retrofit client"
        MovieDb api = retrofit.create(MovieDb.class)

        when: "Popular movies are listed"
        Call<MovieListResponse> call = api.listPopular(Keys.keyFor(Keys.Types.the_movie_db), 1)
        Response<MovieListResponse> response = call.execute()

        then: "The query succeeds"
        response.successful

        when: "The movie results are checked"
        MovieListResponse movies = response.body()

        then: "There are many results"
        movies.getResults().size() > 10
    }

    def "As an authorized user of themoviedb.org I can list top rated movies"() {
        setup: "A retrofit client"
        MovieDb api = retrofit.create(MovieDb.class)

        when: "Popular movies are listed"
        Call<MovieListResponse> call = api.listTopRated(Keys.keyFor(Keys.Types.the_movie_db), 1)
        Response<MovieListResponse> response = call.execute()

        then: "The query succeeds"
        response.successful

        when: "The movie results are checked"
        MovieListResponse movies = response.body()

        then: "There are many results"
        movies.getResults().size() > 10
    }

    // @see https://developers.themoviedb.org/3/getting-started/search-and-query-for-details
    def "As an authorized user of themoviedb.org I can get details for a movie"() {
        setup: "A retrofit client and a known movie id"
        MovieDb api = retrofit.create(MovieDb.class)
        // Jack Reacher: Never Go Back movie id
        int testId = 343611

        when: "A movie detail is retrieved"
        Call<MovieDetailResponse> call = api.get(testId, Keys.keyFor(Keys.Types.the_movie_db), "videos")
        Response<MovieDetailResponse> response = call.execute()

        then: "The query succeeds"
        response.successful

        when: "The movie results are checked"
        MovieDetailResponse detail = response.body()

        then: "The details are as expected"
        "Jack Reacher: Never Go Back".equals(detail.title)
        "2016-10-19".equals(detail.releaseDate)
        detail.collection
        detail.tagline
        detail.posterPath
        detail.videos.results.size() > 0
    }
}