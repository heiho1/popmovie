package com.example.heiho1.popmovies.service;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * A receiver which returns top rated movie results
 */
public class TopRatedMoviesReceiver extends ResultReceiver {
    private Listener listener;

    public TopRatedMoviesReceiver(Handler handler) {
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
            listener.onTopRatedResult(resultCode, resultData);
        }
    }

    /**
     * Declaration of the interface expected of a listener to movie details results
     */
    public  interface Listener {
        void onTopRatedResult(int resultCode, Bundle resultData);
    }
}
