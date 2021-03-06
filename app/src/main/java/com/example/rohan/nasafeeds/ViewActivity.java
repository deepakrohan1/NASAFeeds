package com.example.rohan.nasafeeds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.mipmap.logo);
        actionBar.setTitle("View Feed");

        tvTitle = (TextView) findViewById(R.id.textViewTitle);
        imDetailed = (ImageView)findViewById(R.id.imageViewDetailed);
        tvDesc = (TextView)findViewById(R.id.textViewDesc);

        if(getIntent().getExtras()!=null){
            Feed f = (Feed) getIntent().getExtras().getSerializable(MainActivity.FEED_SENT);
            tvTitle.setText(f.getTitle());
            if(!f.getDescription().equals(null)){
                //TODO: Yet the links wont work
                tvDesc.setText(Html.fromHtml(f.getDescription()),TextView.BufferType.SPANNABLE); //Aligns as per Html safe
                tvDesc.setMovementMethod(LinkMovementMethod.getInstance());//Makes the link active
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
