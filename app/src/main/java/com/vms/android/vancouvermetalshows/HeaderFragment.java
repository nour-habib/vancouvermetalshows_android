package com.vms.android.vancouvermetalshows;

import android.media.Image;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HeaderFragment extends Fragment {

    private TextView mVenueTextView;
    private TextView mDateTextView;
    private ImageView mArtistImageView;
    private Shows mShows;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View headerView = inflater.inflate(R.layout.fragment_header_details, container,false);

        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null)
        {
            mShows = extras.getParcelable("showDetails");
            //show.printShow();
            //Log.d("ShowDetail: ", extras.getString("showDetails"));
        }

        mArtistImageView = headerView.findViewById(R.id.artist_image);
        mArtistImageView.setImageResource(mShows.getImgResourceId());

        mVenueTextView = headerView.findViewById(R.id.venue_text);
        mVenueTextView.setText(mShows.getDate());
        //Log.d("ShowVenue: ", mShows.getVenue());

        mDateTextView = headerView.findViewById(R.id.date_text);
        ShowsFragment showsFragment = new ShowsFragment();
        mDateTextView.setText(showsFragment.convertDate(mShows.getVenue()));



        return headerView;
    }
}
