package com.example.rohan.nasafeeds;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;

import java.util.List;

/**
 * Created by rohan on 10/23/15.
 */
public class FeedDataManager {

    SQLiteDatabase db;
    FeedDAO feedDAO;
    FeedHelper feedHelper;
    Context mContext;

    public FeedDataManager(Context mContext) {
        this.mContext = mContext;
        feedHelper = new FeedHelper(this.mContext);
        db = feedHelper.getWritableDatabase();
        feedDAO = new FeedDAO(db);
    }

    public FeedDAO getFeedDAO() {
        return feedDAO;
    }

    public void closeDB(){
        if(db !=null){
            db.close();
        }
    }

    public long saveFeed(Feed f){
        return getFeedDAO().saveFeed(f);
    }
    public boolean deleteFeed(Feed f){
        return getFeedDAO().deleteNote(f);
    }
    public Feed getFeed(String title){
        return getFeedDAO().getFeed(title);
    }
    public List<Feed> getAll(){
        return getFeedDAO().getAll();
    }


}
