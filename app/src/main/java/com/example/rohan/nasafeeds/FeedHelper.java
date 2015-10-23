package com.example.rohan.nasafeeds;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rohan on 10/23/15.
 */
public class FeedHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="feeds.db";
    public static final int DB_VERSION=1;


    public FeedHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       FeedTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        FeedTable.onVersionChange(db,oldVersion,newVersion);
    }
}
