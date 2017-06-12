package com.example.heiho1.popmovies.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.heiho1.popmovies.model.MovieDetailResponse;
import com.example.heiho1.popmovies.model.MovieListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * A Retrofit API interface to themoviedb.org
 */
public interface MovieDb {
    String THE_MOVIE_DB_HOST_URL="https://api.themoviedb.org/3/movie/";
    String THE_MOVIE_DB_POSTER_URL="https://image.tmdb.org/t/p/w500/";

    @GET("popular")
    Call<MovieListResponse> listPopular(@Query("api_key") String api_key, @Query("page") Integer page);

    @GET("top_rated")
    Call<MovieListResponse> listTopRated(@Query("api_key") String api_key, @Query("page") Integer page);

    @GET("{movie_id}")
    Call<MovieDetailResponse> get(@Path("movie_id") Integer movie_id, @Query("api_key") String api_key, @Query("append_to_response") String appendage);

    /**
     * Images which are accessible from themoviedb.org as HTTP URL requests will be available
     * via a singleton request queue
     *
     * For reference: developer.android.com/training/volley/requestqueue.html
     */
    class Images {
        /**
         * The expected width of a poster image
         */
        public static final int POSTER_WIDTH = 500;

        /**
         * The expected height of a poster imagee
         */
        public static final int POSTER_HEIGHT = 100;

        private static Images _images; // the singleton
        private static Context _context; // the context of the request
        private final RequestQueue mRequestQueue;
        private final Cache cache;
        private final ImageLoader mImageLoader;

        private Images(Context context) {
            _context = context;
            cache = new DiskBasedCache(_context.getCacheDir(), 1024 * 1024 * 5); // 5MB cap
            mRequestQueue = Volley.newRequestQueue(_context.getApplicationContext());
            mImageLoader = new ImageLoader(mRequestQueue,
                    new ImageLoader.ImageCache() {
                        private final LruCache<String, Bitmap>
                                cache = new LruCache<String, Bitmap>(100);

                        @Override
                        public Bitmap getBitmap(String url) {
                            return cache.get(url);
                        }

                        @Override
                        public void putBitmap(String url, Bitmap bitmap) {
                            cache.put(url, bitmap);
                        }
                    });
        }

        /**
         * Singleton factory for instantiating a unique request queue
         *
         * @param context of the given queue
         * @return Images  the singleton API instance
         */
        public static Images singleton(Context context) {
            if (_images == null) {
                _images = new Images(context);
            }
            return _images;
        }

        /**
         * Static factory for generating a new ImageRequest upon the given url with response to
         * the given listener and any error to the given errorListener
         *
         * @param listener  to handle image responses
         * @param errorListener  to handler errors during i/o
         * @return ImageRequest  suitable for queueing
         */
        public static ImageRequest newRequest(String url,
                                              Response.Listener<Bitmap> listener,
                                              Response.ErrorListener errorListener) {
            return new ImageRequest(url, listener, POSTER_WIDTH, POSTER_HEIGHT, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_4444, errorListener);
        }

        public RequestQueue getRequestQueue() {
            return mRequestQueue;
        }

        public <T> void addToRequestQueue(Request<T> req) {
            getRequestQueue().add(req);
        }

        public ImageLoader getImageLoader() {
            return mImageLoader;
        }
    }
}
