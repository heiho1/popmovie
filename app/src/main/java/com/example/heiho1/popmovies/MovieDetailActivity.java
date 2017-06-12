package com.example.heiho1.popmovies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.heiho1.popmovies.api.MovieDb;
import com.example.heiho1.popmovies.model.MovieDetailResponse;
import com.example.heiho1.popmovies.service.MovieDetailsReceiver;
import com.example.heiho1.popmovies.service.MovieDetailsService;
import com.example.heiho1.popmovies.service.PopularMoviesReceiver;
import com.example.heiho1.popmovies.service.PopularMoviesService;
import com.example.heiho1.popmovies.view.recycler.MovieDetailHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.heiho1.popmovies.api.MovieDb.THE_MOVIE_DB_POSTER_URL;

/**
 * Displays the details of a single movie
 */
public class MovieDetailActivity extends AppCompatActivity
            implements MovieDetailsReceiver.Listener,  Response.Listener<Bitmap>, Response.ErrorListener ,
                        Button.OnClickListener {
    private MovieDb.Images imagesApi;

    /**
     * Identifier of the movie to display
     */
    private Integer movieId;

    @BindView(R.id.detail_poster_image)
    ImageView poster;

    @BindView(R.id.detail_title)
    TextView title;

    @BindView(R.id.detail_duration)
    TextView duration;

    @BindView(R.id.detail_rating)
    TextView rating;

    @BindView(R.id.detail_synopsis)
    TextView synopsis;

    @BindView(R.id.video_btn)
    Button videoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imagesApi = MovieDb.Images.singleton(this.getApplicationContext());
        setContentView(R.layout.movie_details_recyclable);
        ButterKnife.bind(this);
        Integer movieId = getIntent().getIntExtra("movie_id", 0);
        startService(createMovieDetailsIntent(movieId));
        videoButton.setOnClickListener(this);
    }

    private Intent createMovieDetailsIntent(Integer movieId) {
        Intent i = new Intent(this, MovieDetailsService.class);
        MovieDetailsReceiver receiver = new MovieDetailsReceiver(new Handler());
        receiver.setListener(this);
        i.putExtra("movie_id", movieId);
        i.putExtra("receiver", receiver);
        return i;
    }

    @Override
    public void onDetailsResult(int resultCode, Bundle resultData) {
        MovieDetailResponse details = resultData.getParcelable("details");
        from(details);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        poster.setImageDrawable(poster.getResources().getDrawable(R.drawable.ic_movie_black_24dp));
    }

    @Override
    public void onResponse(Bitmap response) {
        poster.setImageBitmap(response);
    }


    /**
     * Populate the encapsulated  views of this holder from the given
     * origin
     *
     * @param origin  a MovieDetailResponse to populate views against
     */
    public void from(MovieDetailResponse origin) {
        String path = origin.getPosterPath();
        path = path.replace("/", "");
        String url = String.format("%s%s", THE_MOVIE_DB_POSTER_URL, path);
        ImageRequest req = MovieDb.Images.newRequest(url, this, this);
        req.setShouldCache(true);
        imagesApi.addToRequestQueue(req);

        title.setText(origin.getTitle());
        rating.setText(String.format("%s     %s", "Rating:", Double.toString(origin.getVoteAverage())));
        duration.setText(String.format("%s    %s minutes", "Duration:", Long.toString(origin.getRuntime())));
        synopsis.setText(String.format("%s%s", "Synopsis:\n\n\n", origin.getOverview()));
    }

    @Override
    public void onClick(View view) {
        Intent videoIntent = new Intent(getApplicationContext(), TheaterActivity.class);
        videoIntent.putExtra("movie_url", "http://sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4");
        startActivity(videoIntent);
    }
}
