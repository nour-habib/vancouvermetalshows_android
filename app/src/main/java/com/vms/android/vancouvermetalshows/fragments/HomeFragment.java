package com.vms.android.vancouvermetalshows.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.vms.android.vancouvermetalshows.R;
import com.vms.android.vancouvermetalshows.adapters.ShowsRecyclerViewAdapter;
import com.vms.android.vancouvermetalshows.classes.Shows;

import net.alhazmy13.wordcloud.WordCloud;
import net.alhazmy13.wordcloud.WordCloudView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
//import net.alhazmy13.wordcloud.WordCloud;
//import net.alhazmy13.wordcloud.ColorTemplate;



public class HomeFragment extends Fragment {

    private ImageView mWordCloud;
    private ArrayList<Shows> mArrayList;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false);


        Context context = getContext();

        ShowsRecyclerViewAdapter.ShowsFragment showsFragment = new ShowsRecyclerViewAdapter.ShowsFragment();
        mArrayList = showsFragment.getShows(context);
        //Log.d("mArrayListSize: ", Integer.toString(mArrayList.size()));


        List<WordCloud> artistList = insertArtistToMap(mArrayList);


        WordCloudView wordCloud = homeFragmentView.findViewById(R.id.wordCloud);
        wordCloud.setDataSet(artistList);

//        Display display = getActivity().getWindowManager(). getDefaultDisplay();
//        Point size = new Point();
//        display. getSize(size);
//        int width = size. x;
//        int height = size. y;

        wordCloud.setSize(320,400);
        wordCloud.setColors(new int[] {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK });
        wordCloud.notifyDataSetChanged();


        //wordCloud.setScale(MAX,MIN);


        return homeFragmentView;

    }

    private List<WordCloud> insertArtistToMap(ArrayList<Shows> arrayList)
    {
        List<WordCloud> artistList = new ArrayList<>();

        for(int i=0;i<arrayList.size();i++)
        {
            String artist = arrayList.get(i).getArtist();
            Random random = new Random();
            artistList.add(new WordCloud(artist,random.nextInt(50)));

        }

        return artistList;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }



}
