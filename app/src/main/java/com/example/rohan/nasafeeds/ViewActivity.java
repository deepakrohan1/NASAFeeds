package com.example.rohan.nasafeeds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewActivity extends AppCompatActivity {
TextView tvTitle;
TextView tvDesc;
    ImageView imDetailed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        tvTitle = (TextView) findViewById(R.id.textViewTitle);
        imDetailed = (ImageView)findViewById(R.id.imageViewDetailed);
        tvDesc = (TextView)findViewById(R.id.textViewDesc);

        if(getIntent().getExtras()!=null){
            Feed f = (Feed) getIntent().getExtras().getSerializable(MainActivity.FEED_SENT);
            tvTitle.setText(f.getTitle());
            if(!f.getDescription().equals(null)){
                tvDesc.setText(f.getDescription());
            }
            Picasso.with(this).load(f.getImageLink()).into(imDetailed);
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
