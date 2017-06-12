package com.example.heiho1.popmovies;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.heiho1.popmovies.api.MovieDb;
import com.example.heiho1.popmovies.model.MovieListResponse;
import com.example.heiho1.popmovies.service.PopularMoviesReceiver;
import com.example.heiho1.popmovies.service.PopularMoviesService;
import com.example.heiho1.popmovies.service.TopRatedMoviesReceiver;
import com.example.heiho1.popmovies.service.TopRatedMoviesService;
import com.example.heiho1.popmovies.view.recycler.MovieSummaryResponseAdapter;
import com.example.heiho1.popmovies.view.recycler.VerticalScrollListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListActivity extends AppCompatActivity
        implements PopularMoviesReceiver.Listener, TopRatedMoviesReceiver.Listener {
    private static final String TAG = MoviesListActivity.class.getSimpleName();

    private MovieDb api = PopMoviesApplication.api();
    /**
     * Start the activity at the first data page
     */
    private int popularMax = Integer.MAX_VALUE; // is there a better default?
    private int topRatedMax = Integer.MAX_VALUE; // is there a better default?

    @BindView(R.id.top_rated_movies_recycler)
    RecyclerView topRated;

    @BindView(R.id.popular_movies_recycler)
    RecyclerView popular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        ButterKnife.bind(this);
        final RecyclerView.LayoutManager topRatedManager = new GridLayoutManager(this.getApplicationContext(), 2);
        final RecyclerView.LayoutManager popularManager = new GridLayoutManager(this.getApplicationContext(), 2);
        topRated.setLayoutManager(topRatedManager);
        topRated.addOnScrollListener(new VerticalScrollListener() {
            @Override
            public void onScrolledToTop() {
                if (!_loading && _currentPage >= 1) {
                    if (_currentPage == 1) {
                        startService(createPopularMoviesIntent(_currentPage));
                    } else {
                        startService(createPopularMoviesIntent(--_currentPage));
                    }
                    _loading = true;
                } else {
                    _loading = false;
                }
            }
            @Override
            public void onScrolledToBottom() {
                if (!_loading && _currentPage < popularMax) {
                    startService(createTopRatedMoviesIntent(++_currentPage));
                    _loading = true;
                } else {
                    _loading = false;
                }
            }
        });
        popular.setLayoutManager(popularManager);
        popular.addOnScrollListener(new VerticalScrollListener() {
            @Override
            public void onScrolledToTop() {
                if (!_loading && _currentPage >= 1) {
                    if (_currentPage == 1) {
                        startService(createPopularMoviesIntent(_currentPage));
                    } else {
                        startService(createPopularMoviesIntent(--_currentPage));
                    }
                    _loading = true;
                } else {
                   _loading = false;
                }
            }
            @Override
            public void onScrolledToBottom() {
                if (!_loading && _currentPage < topRatedMax) {
                    startService(createPopularMoviesIntent(++_currentPage));
                    _loading = true;
                } else {
                    _loading = false;
                }
            }
        });
        startService(createPopularMoviesIntent(1));
        startService(createTopRatedMoviesIntent(1));
    }

    private Intent createPopularMoviesIntent(int targetPage) {
        Intent i = new Intent(this, PopularMoviesService.class);
        PopularMoviesReceiver receiver = new PopularMoviesReceiver(new Handler());
        receiver.setListener(this);
        i.putExtra("page", targetPage);
        i.putExtra("receiver", receiver);
        return i;
    }


    private Intent createTopRatedMoviesIntent(int targetPage) {
        Intent i = new Intent(this, TopRatedMoviesService.class);
        TopRatedMoviesReceiver receiver = new TopRatedMoviesReceiver(new Handler());
        receiver.setListener(this);
        i.putExtra("page", targetPage);
        i.putExtra("receiver", receiver);
        return i;
    }

    @Override
    public void onPopularResult(int resultCode, Bundle resultData) {
        MovieListResponse res = resultData.getParcelable("popular");
        if (res.getResults().size() < 1) {
            Snackbar.make(popular, "Sadly the popular movies are not available.  Please try again.", Snackbar.LENGTH_SHORT);
        } else {
            popularMax = res.getTotalPages();
            popular.setAdapter(new MovieSummaryResponseAdapter(res.getResults()));
        }

    }

    @Override
    public void onTopRatedResult(int resultCode, Bundle resultData) {
        MovieListResponse res = resultData.getParcelable("topRated");
        if (res.getResults().size() < 1) {
            Snackbar.make(popular, "Sadly the top rated movies are not available.  Please try again.", Snackbar.LENGTH_SHORT);
        } else {
            topRatedMax = res.getTotalPages();
            topRated.setAdapter(new MovieSummaryResponseAdapter(res.getResults()));
        }
    }

}
