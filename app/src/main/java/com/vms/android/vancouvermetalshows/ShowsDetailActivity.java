package com.vms.android.vancouvermetalshows;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ShowsDetailActivity extends AppCompatActivity {

    private Shows show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows_detail);


//        assert getSupportActionBar() != null;   //null check
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button



        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            show = extras.getParcelable("showDetails");
            //show.printShow();
            //Log.d("ShowDetail: ", extras.getString("showDetails"));
        }

        getSupportActionBar().setTitle(show.getArtist().toUpperCase());
        //ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        Bundle newBundle = new Bundle();
        newBundle.putParcelable("showDetails", show);

        HeaderFragment headerFragment = new HeaderFragment();
        headerFragment.setArguments(newBundle);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.header_frame, headerFragment);
        ft.commit();


        ButtonOptionsFragment btFragment = new ButtonOptionsFragment();
        FragmentManager bt_fm = getSupportFragmentManager();
        FragmentTransaction bt_ft = bt_fm.beginTransaction();
        bt_ft.add(R.id.button_options, btFragment);
        bt_ft.commit();


        ShowDetailsFragment showDetailsFragment = new ShowDetailsFragment();
        showDetailsFragment.setArguments(newBundle);

        btFragment.setArguments(newBundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.details_frame, showDetailsFragment);
        fragmentTransaction.commit();

        MapFragment mapFragment = new MapFragment();

        FragmentManager fragmentManager2 = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
        fragmentTransaction2.add(R.id.map_frame, mapFragment);
        fragmentTransaction2.commit();



    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }



}
