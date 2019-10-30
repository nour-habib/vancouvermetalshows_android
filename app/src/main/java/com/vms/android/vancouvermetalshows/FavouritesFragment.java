package com.vms.android.vancouvermetalshows;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FavouritesFragment extends Fragment {

    ArrayList<Favourites> mFavouritesArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard

        View favouritesView = inflater.inflate(R.layout.fragment_favourites, container, false);

        ListView favouritesListView = favouritesView.findViewById(R.id.list_view);
        mFavouritesArrayList = new ArrayList<>();

        Shows sampleShow = new Shows(1,"Inquisition","2020-01-01","Rickshaw","","$30");
        Favourites favourites = new Favourites(sampleShow);
        mFavouritesArrayList.add(favourites);



        FavouritesListAdapter adapter = new FavouritesListAdapter(getContext(),R.layout.listview_row,mFavouritesArrayList);
        favouritesListView.setAdapter(adapter);



        return favouritesView;

    }
}
