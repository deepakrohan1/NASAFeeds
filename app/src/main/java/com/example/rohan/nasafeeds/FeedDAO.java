package com.example.rohan.nasafeeds;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohan on 10/23/15.
 */
public class FeedDAO {

    private SQLiteDatabase db;

    public FeedDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long saveFeed(Feed feed){
        ContentValues values = new ContentValues();
        values.put(FeedTable.COLUMN_TITLE, feed.getTitle());
        values.put(FeedTable.COLUMN_DESC, feed.getDescription());
        values.put(FeedTable.COLUMN_IMG, feed.getImageLink());
        values.put(FeedTable.COLUMN_LINK, feed.getLink());
        values.put(FeedTable.COLUMN_DATE,feed.getDate());
        return db.insert(FeedTable.TABLE_NAME,null,values);

    }

    public boolean deleteNote(Feed feed){
        return db.delete(FeedTable.TABLE_NAME,FeedTable.COLUMN_DATE +"=?",new String[]{feed.getDate()}) > 0;
    }

    public Feed getFeed(String title){
        Feed f = null;
        Cursor c = db.query(true, FeedTable.TABLE_NAME, new String[]{FeedTable.COLUMN_ID,
                FeedTable.COLUMN_TITLE, FeedTable.COLUMN_DESC, FeedTable.COLUMN_LINK, FeedTable.COLUMN_IMG,
                FeedTable.COLUMN_DATE}, FeedTable.COLUMN_TITLE + "=?", new String[]{title}, null, null, null, null);

        if(c != null && c.moveToFirst()){
            f = getFeedFromCursor(c);
            if(!c.isClosed()){
                c.close();
            }
        }
        return f;
    }

    public List<Feed> getAll(){
        List<Feed> feeds = new ArrayList<>();
        Cursor c = db.query(FeedTable.TABLE_NAME, new String[]{FeedTable.COLUMN_ID,
                FeedTable.COLUMN_TITLE, FeedTable.COLUMN_DESC, FeedTable.COLUMN_LINK, FeedTable.COLUMN_IMG,
                FeedTable.COLUMN_DATE},null,null,null,null,null);
        if(c != null && c.moveToFirst()){
            do{
                Feed f = getFeedFromCursor(c);
                if(f!=null){
                    feeds.add(f);
                }

            }while (c.moveToNext());
            if(!c.isClosed()){
                c.close();
            }
        }

        return feeds;
    }

    public Feed getFeedFromCursor(Cursor c){
        Feed f = null;
        if(c!=null) {
            f = new Feed();
            f.setId(c.getLong(0));
            f.setTitle(c.getString(1));
            f.setDescription(c.getString(2));
            f.setLink(c.getString(3));
            f.setImageLink(c.getString(4));
            f.setDate(c.getString(5));
        }
        return f;
    }


}
