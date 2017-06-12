package com.example.heiho1.popmovies.service;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * A receiver which return popular movie results
 */
public class PopularMoviesReceiver extends ResultReceiver {
    private Listener listener;

    public PopularMoviesReceiver(Handler handler) {
        super(handler);
    }

    /**
     * Mutator for the single listener which this receiver supports
     *
     * @param listener to be given results as they are received
     */
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (listener != null) {
            listener.onPopularResult(resultCode, resultData);
        }
    }

    /**
     * Declaration of the interface expected of a listener to movie details results
     */
    public interface Listener {
        void onPopularResult(int resultCode, Bundle resultData);
    }
}
