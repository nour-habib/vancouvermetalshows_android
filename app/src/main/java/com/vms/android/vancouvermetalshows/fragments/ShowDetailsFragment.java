package com.vms.android.vancouvermetalshows.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vms.android.vancouvermetalshows.R;
import com.vms.android.vancouvermetalshows.adapters.ShowsRecyclerViewAdapter;
import com.vms.android.vancouvermetalshows.classes.Shows;


public class ShowDetailsFragment extends Fragment {

    TextView mArtistTextView;
    TextView mVenueTextView;
    TextView mDateTextView;
    TextView mSuppArtistTextView;
    TextView mLineUpTextView;
    Shows show;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View showDetailsView = inflater.inflate(R.layout.fragment_show_details, container, false);

        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null)
        {
            show = extras.getParcelable("showDetails");
            //show.printShow();
            //Log.d("ShowDetail: ", extras.getString("showDetails"));
        }

        mLineUpTextView = showDetailsView.findViewById(R.id.lineup);
        mLineUpTextView.setText(R.string.lineup_string);

        mArtistTextView =  showDetailsView.findViewById(R.id.artist);
        mArtistTextView.setText(show.getArtist().toUpperCase());

        mSuppArtistTextView = showDetailsView.findViewById(R.id.supp_artist);
        mSuppArtistTextView.setText(show.getSupporting_artists());


        mDateTextView = showDetailsView.findViewById(R.id.date);
        mDateTextView.setText(show.getDate());


        ShowsRecyclerViewAdapter.ShowsFragment showsFragment = new ShowsRecyclerViewAdapter.ShowsFragment();
        mVenueTextView = showDetailsView.findViewById(R.id.venue);
        mVenueTextView.setText(showsFragment.convertDate(show.getVenue()));






        return showDetailsView;

    }
}


