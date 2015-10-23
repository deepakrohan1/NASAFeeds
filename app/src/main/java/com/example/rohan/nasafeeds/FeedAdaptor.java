package com.example.rohan.nasafeeds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by rohan on 10/22/15.
 */
public class FeedAdaptor extends ArrayAdapter<Feed> {

    Context mContext;
    int mResource;
    List<Feed> mObjects;

    public FeedAdaptor(Context context, int resource, List<Feed> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.mObjects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
        }
        Feed f = mObjects.get(position);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageViewNasa);
        Picasso.with(mContext).load(f.getImageLink()).resize(100,100).into(imageView);

        TextView tvTitle = (TextView)convertView.findViewById(R.id.textViewTitle);
        tvTitle.setText(f.getTitle());

        TextView tvDate = (TextView)convertView.findViewById(R.id.textViewPubDate);
        tvDate.setText(f.getDate());

        return convertView;
    }
}
