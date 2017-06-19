package com.example.heiho1.popmovies.view.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heiho1.popmovies.PopMoviesApplication;
import com.example.heiho1.popmovies.R;
import com.example.heiho1.popmovies.api.MovieDb;
import com.example.heiho1.popmovies.model.MovieSummary;

import java.util.List;

/**
 * An adapter which optimally provides MovieSummary data to
 * a RecyclerView consumer
 */
public class MovieSummaryResponseAdapter extends RecyclerView.Adapter<MovieSummaryHolder> {
    private List<MovieSummary> summaries;

    public MovieSummaryResponseAdapter(List<MovieSummary> summaries) {
        this.summaries = summaries;
    }


    @Override
    public MovieSummaryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_summary_recyclable, parent, false);
        return new MovieSummaryHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieSummaryHolder holder, int position) {
        MovieSummary summary = summaries.get(position);
        holder.from(summary);
    }

    @Override
    public int getItemCount() {
        return summaries.size();
    }
}
