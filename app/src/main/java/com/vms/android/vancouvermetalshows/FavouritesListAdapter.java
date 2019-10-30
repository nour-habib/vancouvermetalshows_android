package com.vms.android.vancouvermetalshows;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FavouritesListAdapter extends ArrayAdapter<Favourites> {

    private ArrayList<Favourites> mFavouritesArrayList;
    private LayoutInflater mInflater;
    private Context mContext;
    private int mResource;


    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param data
     */

    public FavouritesListAdapter(Context context, int resource, ArrayList<Favourites> data) {
        super(context, resource, data);
        this.mResource = resource;
        this.mContext = context;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup container) {
       String artist = getItem(position).getArtist();

       //LayoutInflater inflater = LayoutInflater.from(mContext);
       //convertView = inflater.inflate(mResource, container,false);

        mInflater = LayoutInflater.from(mContext);

       convertView = mInflater.inflate(mResource, container, false);


       TextView artistTextView = convertView.findViewById(R.id.artist_text);

        artistTextView.setText(artist);


       return convertView;
    }

}
