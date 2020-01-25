package com.vms.android.vancouvermetalshows.activities;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vms.android.vancouvermetalshows.adapters.ShowsRecyclerViewAdapter;
import com.vms.android.vancouvermetalshows.fragments.FavouritesFragment;
import com.vms.android.vancouvermetalshows.fragments.HomeFragment;
import com.vms.android.vancouvermetalshows.R;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();

        getSupportActionBar().setTitle(" ");

        if(savedInstanceState==null)
        {
            Fragment fragment = new HomeFragment();
            loadFragment(fragment);
        }


        BottomNavigationView bottomNavigationView =  findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.shows:
                        fragment = new ShowsRecyclerViewAdapter.ShowsFragment();
                        break;
                    case R.id.favs:
                        fragment = new FavouritesFragment();
                        break;

                }
                return loadFragment(fragment);
            }
        });





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
