package com.example.heiho1.popmovies;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.heiho1.popmovies.api.MovieDb;
import com.example.heiho1.popmovies.model.MovieListResponse;
import com.example.heiho1.popmovies.service.PopularMoviesReceiver;
import com.example.heiho1.popmovies.service.PopularMoviesService;
import com.example.heiho1.popmovies.service.TopRatedMoviesReceiver;
import com.example.heiho1.popmovies.service.TopRatedMoviesService;
import com.example.heiho1.popmovies.view.recycler.MovieSummaryResponseAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListActivity extends AppCompatActivity
        implements PopularMoviesReceiver.Listener, TopRatedMoviesReceiver.Listener {
    private static final String TAG = MoviesListActivity.class.getSimpleName();

    private MovieDb api = PopMoviesApplication.api();

    @BindView(R.id.top_rated_movies_recycler)
    RecyclerView topRated;

    @BindView(R.id.popular_movies_recycler)
    RecyclerView popular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        ButterKnife.bind(this);
        topRated.setLayoutManager(new LinearLayoutManager(this));
        popular.setLayoutManager(new LinearLayoutManager(this));
        startService(createPopularMoviesIntent());
        startService(createTopRatedMoviesIntent());
    }

    private Intent createPopularMoviesIntent() {
        Intent i = new Intent(this, PopularMoviesService.class);
        PopularMoviesReceiver receiver = new PopularMoviesReceiver(new Handler());
        receiver.setListener(this);
        i.putExtra("page", 1);
        i.putExtra("receiver", receiver);
        return i;
    }


    private Intent createTopRatedMoviesIntent() {
        Intent i = new Intent(this, TopRatedMoviesService.class);
        TopRatedMoviesReceiver receiver = new TopRatedMoviesReceiver(new Handler());
        receiver.setListener(this);
        i.putExtra("page", 1);
        i.putExtra("receiver", receiver);
        return i;
    }

    @Override
    public void onPopularResult(int resultCode, Bundle resultData) {
        MovieListResponse res = resultData.getParcelable("popular");
        popular.setAdapter(new MovieSummaryResponseAdapter(res.getResults()));

    }

    @Override
    public void onTopRatedResult(int resultCode, Bundle resultData) {
        MovieListResponse res = resultData.getParcelable("topRated");
        topRated.setAdapter(new MovieSummaryResponseAdapter(res.getResults()));
    }
}
