package com.example.rohan.nasafeeds;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rohan on 10/23/15.
 */
public class AsyncDTTrends extends AsyncTask<String,Void,ArrayList<Feed>> {

    showData currentActivity;
    ProgressDialog Dt;


    public AsyncDTTrends(showData currentActivity) {
        this.currentActivity = currentActivity;
    }

    @Override
    protected ArrayList<Feed> doInBackground(String... params) {
        try{
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream in = con.getInputStream();
                return FeedParser.NasaFeedParser.NasaParser(in);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Dt = new ProgressDialog((Context)currentActivity);
        Dt.setTitle("Loadin Feeds");
        Dt.setMessage("Loading...");
        Dt.setCancelable(false);
        Dt.show();
    }

    @Override
    protected void onPostExecute(ArrayList<Feed> feeds) {
        super.onPostExecute(feeds);
        Dt.hide();
        currentActivity.showsFeeds(feeds);
    }

    public static  interface showData{
        public void showsFeeds(ArrayList<Feed> feeds);
    }
}
