package com.example.rohan.nasafeeds;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity implements AsyncDTTrends.showData {
    List<Feed> feedsSaved;
    ListView listView;
    String typeFeeds = "NASA";
    FeedDataManager dm;
    AlertDialog alert;
    CharSequence[] items;
    public static final String FEED_SENT="feedsent";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
        new NASAFeedParser().execute("http://www.nasa.gov/rss/dyn/lg_image_of_the_day.rss");

        dm = new FeedDataManager(this);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.mipmap.logo);
        actionBar.setTitle("Feeds");
        feedsSaved = new ArrayList<>();
        feedsSaved = dm.getAll();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.nasa_image:
                typeFeeds = "NASA";
                new NASAFeedParser().execute("http://www.nasa.gov/rss/dyn/lg_image_of_the_day.rss");
                return true;
            case R.id.dt_computer:
                typeFeeds="DTCOMP";
                new AsyncDTTrends(MainActivity.this).execute("http://www.digitaltrends.com/computing/feed/");
                return true;
            case R.id.dt_mobile:
                typeFeeds="DTMOB";
                new AsyncDTTrends(MainActivity.this).execute("http://www.digitaltrends.com/mobile/feed/");
                return true;
            case R.id.dt_podcast:
                typeFeeds="DTPOD";
                new AsyncDTTrends(MainActivity.this).execute("http://www.digitaltrends.com/podcasts/feed/");
                return true;
            case R.id.get_fav:
                typeFeeds = "FAV";
                feedsSaved = dm.getAll();
                FeedAdaptor adaptor = new FeedAdaptor(MainActivity.this,R.layout.nasa_main,feedsSaved);
                listView.setAdapter(adaptor);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void showsFeeds(final ArrayList<Feed> feeds) {
        if(feeds == null){
            Log.d("ap","error");
        }else{
            for(Feed f : feeds){
                Log.d("ap",f.toString());
            }

            FeedAdaptor feedAdaptor = new FeedAdaptor(MainActivity.this,R.layout.nasa_main,feeds);
            listView.setAdapter(feedAdaptor);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Feed feed = feeds.get(position);
                    Intent i = new Intent(MainActivity.this,ViewActivity.class);
                    i.putExtra(FEED_SENT,feed);
                    startActivity(i);
                }
            });
        }
    }


    class NASAFeedParser extends AsyncTask<String, Void, ArrayList<Feed>> {

        @Override
        protected ArrayList<Feed> doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
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
        }

        @Override
        protected void onPostExecute(final ArrayList<Feed> feeds) {
            super.onPostExecute(feeds);
            if (feeds.size() == 0) {
                Log.d("ap", "Err");
            } else {
                ArrayList<String> feedsTitle = new ArrayList<>();
                for (Feed f : feeds) {
                    Log.d("ap", f.toString());
                    feedsTitle.add(f.getTitle());
                }
                FeedAdaptor adaptor = null;
                items = feedsTitle.toArray(new CharSequence[feedsTitle.size()]);

//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Add as Fav")
//                        .setP
//                builder.setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "Selected Item: " + items[which], Toast.LENGTH_SHORT).show();
//                    }
//                });

//                alert = builder.create();

                if(typeFeeds == "FAV"){

                    adaptor = new FeedAdaptor(MainActivity.this,R.layout.nasa_main,feedsSaved);
                }else {
                     adaptor = new FeedAdaptor(MainActivity.this, R.layout.nasa_main, feeds);
                }
                listView.setAdapter(adaptor);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (typeFeeds == "FAV") {
                            Feed f = feedsSaved.get(position);
                            Intent i = new Intent(MainActivity.this, ViewActivity.class);
                            i.putExtra(FEED_SENT, f);
                            startActivity(i);

                        } else {
                            Feed f = feeds.get(position);
                            Intent i = new Intent(MainActivity.this, ViewActivity.class);
                            i.putExtra(FEED_SENT, f);
                            startActivity(i);
                        }
                    }
                });


                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        Feed feedClicked = feeds.get(position);
                        Log.d("as", feedClicked.toString());
                        final int positionVar = position;
                       final Feed alreadySaved = dm.getFeed(feedClicked.getTitle());
                        final ImageView star = (ImageView) view.findViewById(R.id.imageViewFav);

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Add as Fav")
                                .setMessage("This will either Add/Delete Content from the Favourites")
                                .setCancelable(false)
                                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (alreadySaved == null) {
                                            dm.saveFeed(feeds.get(positionVar));
                                            star.setImageDrawable(getResources().getDrawable(R.drawable.fill));
                                            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                                        } else {
                                            dm.deleteFeed(alreadySaved);
                                            Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                            star.setImageDrawable(getResources().getDrawable(R.drawable.empty));
                                        }
                                    }
                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alert = builder.create();
                        alert.show();


                        return true;
                    }
                });

            }
        }
    }

}
