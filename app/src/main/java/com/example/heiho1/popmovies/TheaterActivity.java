package com.example.heiho1.popmovies;

import android.app.Activity;
import android.os.Bundle;
import android.widget.VideoView;

import com.danikula.videocache.HttpProxyCacheServer;
import com.example.heiho1.popmovies.api.MovieDb;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity which plays a movie for an audience
 */
public class TheaterActivity extends Activity {
    @BindView(R.id.video_view)
    VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_theater);
        ButterKnife.bind(this);
        String movieUrl = getIntent().getStringExtra("movie_url");
        HttpProxyCacheServer proxy = PopMoviesApplication.getProxy(getApplicationContext());
        String url = proxy.getProxyUrl(movieUrl);
        videoView.setVideoPath(url);
    }
}
