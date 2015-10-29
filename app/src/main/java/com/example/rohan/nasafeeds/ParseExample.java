package com.example.rohan.nasafeeds;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by rohan on 10/29/15.
 */
public class ParseExample extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "Bgyv9Ai6dejljuTV1pRvT9AsvRR8HO89ZrfNSJN9", "W7QHGsjlDBywthI4h1c2x99F3Aixxx274qHLWG0R");

    }
}
