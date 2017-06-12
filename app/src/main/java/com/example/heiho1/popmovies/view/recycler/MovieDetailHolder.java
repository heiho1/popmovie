package com.example.heiho1.popmovies.view.recycler;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heiho1.popmovies.R;
import com.example.heiho1.popmovies.model.MovieDetailResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.heiho1.popmovies.api.MovieDb.THE_MOVIE_DB_POSTER_URL;

/**
 * A simple view holder which houses a MovieDetailResponse for recycling
 *
 * @see com.example.heiho1.popmovies.model.MovieDetailResponse
 */
public class MovieDetailHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.summary_poster_image)
    protected ImageView posterImage;

    @BindView(R.id.summary_title)
    protected TextView title;

    @BindView(R.id.detail_duration)
    protected TextView duration;

    @BindView(R.id.detail_rating)
    protected TextView rating;

    @BindView(R.id.detail_synopsis)
    protected TextView synopsis;

    /**
     * Attach this recycler to a given View
     *
     * @param itemView to recycle for
     */
    public MovieDetailHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    /**
     * Populate the encapsulated  views of this holder from the given
     * origin
     *
     * @param origin  a MovieDetailResponse to populate views against
     */
    public void from(MovieDetailResponse origin) {
        String path = origin.getPosterPath();
        path = path.replace("\\/", "/");// fix REST path prefixing
        String url = String.format("%s%s", THE_MOVIE_DB_POSTER_URL, path);
        posterImage.setImageURI(Uri.parse(url));
        title.setText(origin.getTitle());
        rating.setText(Double.toString(origin.getVoteAverage()));
        duration.setText(Long.toString(origin.getRuntime()));
        synopsis.setText(origin.getOverview());
    }


}
