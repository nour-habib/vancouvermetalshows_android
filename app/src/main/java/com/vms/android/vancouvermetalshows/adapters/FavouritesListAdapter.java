package com.vms.android.vancouvermetalshows.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vms.android.vancouvermetalshows.R;
import com.vms.android.vancouvermetalshows.classes.Shows;

import java.util.ArrayList;

public class FavouritesListAdapter extends ArrayAdapter<Shows> {

    private ArrayList<Shows> mFavouritesArrayList;
    private LayoutInflater mInflater;
    private Context mContext;
    private int mResource;
    private ImageButton mImageButton;


    /**
     * Default constructor for the FavouritesListAdapter
     * @param context
     * @param resource
     * @param data
     */

    public FavouritesListAdapter(Context context, int resource, ArrayList<Shows> data) {
        super(context, resource, data);
        this.mResource = resource;
        this.mContext = context;
        this.mFavouritesArrayList = data;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup container) {



       String artist = getItem(position).getArtist();
       String venue = getItem(position).getVenue();
       String date = getItem(position).getDate();

       //LayoutInflater inflater = LayoutInflater.from(mContext);
       //convertView = inflater.inflate(mResource, container,false);

        mInflater = LayoutInflater.from(mContext);

       convertView = mInflater.inflate(mResource, container, false);


       TextView artistTextView = convertView.findViewById(R.id.artist_text);
       TextView venueTextView = convertView.findViewById(R.id.venue_text);
       TextView dateTextView = convertView.findViewById(R.id.date_text);
       mImageButton = convertView.findViewById(R.id.delete_button);
       mImageButton.setVisibility(View.INVISIBLE);

       ShowsRecyclerViewAdapter.ShowsFragment showsFragment = new ShowsRecyclerViewAdapter.ShowsFragment();
        artistTextView.setText(artist);
        venueTextView.setText(showsFragment.convertDate(venue));
        dateTextView.setText(date);


       return convertView;
    }

    public void remove(int position)
    {
        mFavouritesArrayList.remove(position);
        notifyDataSetChanged();
    }

}
