package com.example.heiho1.popmovies.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.heiho1.popmovies.MoviesListActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Verifies that poster and detail images may be retrieved and optimally cached to minimize
 * themoviedb.org interaction.
 */
@RunWith(AndroidJUnit4.class)
public class ImagesTest implements Response.Listener<Bitmap>, Response.ErrorListener {
    private Context context;

    @Rule
    public ActivityTestRule<MoviesListActivity> mActivityRule = new ActivityTestRule<>(
            MoviesListActivity.class);


    @Test
    public void theMovieDbHasAnImagesApi() {
        String wonderWomanPoster = "gfJGlDaHuWimErCr5Ql0I8x9QSy.jpg";
        context = mActivityRule.getActivity().getApplicationContext();
        assertNotNull(context);
        MovieDb.Images api = MovieDb.Images.singleton(context);
        ImageRequest posterReq = MovieDb.Images.newRequest(
                String.format("%s%s", MovieDb.THE_MOVIE_DB_POSTER_URL, wonderWomanPoster), this, this);
        api.addToRequestQueue(posterReq);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        fail();
    }

    @Override
    public void onResponse(Bitmap response) {
        assertTrue(response.getWidth() == MovieDb.Images.POSTER_WIDTH);
        assertTrue(response.getHeight() == MovieDb.Images.POSTER_HEIGHT);
    }
}
