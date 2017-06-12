package com.example.heiho1.popmovies.view.recycler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.heiho1.popmovies.MovieDetailActivity;
import com.example.heiho1.popmovies.R;
import com.example.heiho1.popmovies.api.MovieDb;
import com.example.heiho1.popmovies.model.MovieSummary;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.heiho1.popmovies.api.MovieDb.THE_MOVIE_DB_POSTER_URL;

/**
 * A simple view holder which houses a MovieSummaryResponse for recycling
 *
 * @see com.example.heiho1.popmovies.model.MovieSummary
 */
public class MovieSummaryHolder extends RecyclerView.ViewHolder
        implements Response.Listener<Bitmap>, Response.ErrorListener, View.OnClickListener {

    private final MovieDb.Images API;

    // identifier of the movie touched upon by the user
    private Integer movieTouchedId;

    @BindView(R.id.summary_poster_image)
    protected ImageView posterImage;

    @BindView(R.id.summary_title)
    protected TextView title;

    @BindView(R.id.summary_rating)
    protected TextView rating;

    public MovieSummaryHolder(View itemView) {
        super(itemView);

        API = MovieDb.Images.singleton(itemView.getContext());
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }


    /**
     * Populate the encapsulated  views of this holder from the given
     * origin
     *
     * @param origin  a MovieSummary to populate views against
     */
    public void from(MovieSummary origin) {
        movieTouchedId = origin.getId();
        String path = origin.getPosterPath();
        path = path.replace("/", "");
        String url = String.format("%s%s", THE_MOVIE_DB_POSTER_URL, path);
        //posterImage.setImageURI(Uri.parse(url));
        ImageRequest req = MovieDb.Images.newRequest(url, this, this);
        req.setShouldCache(true);
        API.addToRequestQueue(req);
        title.setText(origin.getTitle());
        rating.setText(Double.toString(origin.getVoteAverage()));
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        posterImage.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_movie_black_24dp));
    }

    @Override
    public void onResponse(Bitmap response) {
        posterImage.setImageBitmap(response);
    }

    @Override
    public void onClick(View view) {
        Intent details = new Intent(view.getContext(), MovieDetailActivity.class);
        details.putExtra("movie_id", movieTouchedId);
        view.getContext().startActivity(details);
    }
}
