package com.example.heiho1.popmovies;

import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;
import com.example.heiho1.popmovies.api.MovieDb;
import com.example.heiho1.popmovies.util.MovieDetailResponseCache;
import com.example.heiho1.popmovies.util.MovieListResponseCache;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Application level class for providing contextual services and resources such as JSON factories
 */
public class PopMoviesApplication extends Application {
    // @see https://github.com/danikula/AndroidVideoCache
    private HttpProxyCacheServer proxy;

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MovieDb.THE_MOVIE_DB_HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final MovieDetailResponseCache _detailsCache = new MovieDetailResponseCache(1000);
    private static final MovieListResponseCache _popularCache = new MovieListResponseCache(100);
    private static final MovieListResponseCache _topRatedCache = new MovieListResponseCache(100);

    public static Retrofit retrofit() {
        return retrofit;
    }

    /**
     * Application wide accessor for the MovieDb API
     *
     * @return MovieDb  suitable for REST calls
     */
    public static MovieDb api() {
        return retrofit.create(MovieDb.class);
    }

    /**
     * Accessor for the application wide cache of popular movies
     *
     * @return MovieListResponseCache  suitable for caching popular movies by page number
     */
    public static MovieListResponseCache popularCache() {
        return _popularCache;
    }


    /**
     * Accessor for the application wide cache of top rated movies
     *
     * @return MovieListResponseCache  suitable for caching top rated movies by page number
     */
    public static MovieListResponseCache topRatedCache() {
        return _topRatedCache;
    }


    /**
     * Accessor for the application wide cache of top rated movies
     *
     * @return MovieListResponseCache  suitable for caching top rated movies by movie id
     */
    public static MovieDetailResponseCache detailsCache() {
        return _detailsCache;
    }



    public static HttpProxyCacheServer getProxy(Context context) {
        PopMoviesApplication app = (PopMoviesApplication) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer.Builder(this)
                .maxCacheFilesCount(3).build();
    }
}
