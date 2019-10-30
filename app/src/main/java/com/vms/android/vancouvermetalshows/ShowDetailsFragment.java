package com.vms.android.vancouvermetalshows;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class ShowDetailsFragment extends Fragment {

    TextView mArtistTextView;
    TextView mVenueTextView;
    TextView mDateTextView;
    TextView mSuppArtistTextView;
    Shows show;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard

        View showDetailsView = inflater.inflate(R.layout.fragment_show_details, container, false);

        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null)
        {
            show = extras.getParcelable("showDetails");
            //show.printShow();
            //Log.d("ShowDetail: ", extras.getString("showDetails"));
        }


        mArtistTextView =  showDetailsView.findViewById(R.id.artist);
        mArtistTextView.setText(show.getArtist());

        mVenueTextView = showDetailsView.findViewById(R.id.venue);
        mVenueTextView.setText(show.getVenue());

        mDateTextView = showDetailsView.findViewById(R.id.date);
        mDateTextView.setText(show.getDate());

        mSuppArtistTextView = showDetailsView.findViewById(R.id.supp_artist);
        mSuppArtistTextView.setText(show.getSupporting_artists());


        return showDetailsView;

    }
}


