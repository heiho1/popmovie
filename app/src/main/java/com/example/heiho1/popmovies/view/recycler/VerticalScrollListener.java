package com.example.heiho1.popmovies.view.recycler;

import android.support.v7.widget.RecyclerView;

/**
 * Apply a vertical scroll listener so that we can know when have scrolled to the end or beginning of a recycler.
 * The scroll listener will maintain a reference to its starting page and maximum number of pages and will restrict scrolling to within those ranges
 */
public class VerticalScrollListener extends RecyclerView.OnScrollListener {

    /**
     * Indicator that specifies if we are in a data page loading state or should accept scrolling to
     * another data page
     */
    protected boolean _loading = false;
    protected int _currentPage = 1;

    @Override
    public final void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (!recyclerView.canScrollVertically(-1)) {
            onScrolledToTop();
        } else if (!recyclerView.canScrollVertically(1)) {
            onScrolledToBottom();
        } else if (dy < 0) {
            onScrolledUp();
        } else if (dy > 0) {
            onScrolledDown();
        }
    }

    public void onScrolledUp() {
        ;
    }

    public void onScrolledDown() {
        ;
    }

    public void onScrolledToTop() {
        ;
    }

    public void onScrolledToBottom() {
        ;
    }
}
