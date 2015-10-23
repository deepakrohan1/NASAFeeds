package com.example.rohan.nasafeeds;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by rohan on 10/23/15.
 */
public class FeedTable {
    public static final String TABLE_NAME = "Feed";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESC = "description";
    public static final String COLUMN_LINK = "link";
    public static final String COLUMN_IMG = "image";
    public static final String COLUMN_DATE = "date";

    static StringBuilder sb = new StringBuilder();
    public static void onCreate(SQLiteDatabase db){
        sb.append("CREATE TABLE "+TABLE_NAME+"(");
        sb.append(COLUMN_ID+" integer primary key autoincrement,");
        sb.append(COLUMN_TITLE+" TEXT,");
        sb.append(COLUMN_DESC+" TEXT,");
        sb.append(COLUMN_LINK+" TEXT,");
        sb.append(COLUMN_IMG+" TEXT,");
        sb.append(COLUMN_DATE+" TEXT);");

        try{
            db.execSQL(sb.toString());
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    public static void onVersionChange(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        FeedTable.onCreate(db);
    }
}


