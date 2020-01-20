package com.vms.android.vancouvermetalshows;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

public class ShowsFragment extends Fragment implements ShowsRecyclerViewAdapter.ItemClickListener {

    private ArrayList<Shows> mShowsArrayList;
    private ShowsRecyclerViewAdapter mShowsRecyclerViewAdapter;
    private SearchView mSearchView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View showsView = inflater.inflate(R.layout.fragment_shows, container, false);
        Context context = getContext();


        mShowsArrayList = getShows(context);
        Collections.sort(mShowsArrayList, new ShowComparator());


        String s = "printShows";
        for (int j = 0; j < mShowsArrayList.size(); j++) {
            Log.d("mShowsArrayList: ", s);
            mShowsArrayList.get(j).printShow();
        }

        RecyclerView recyclerView = showsView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));



        mShowsRecyclerViewAdapter = new ShowsRecyclerViewAdapter(context, mShowsArrayList);
        mShowsRecyclerViewAdapter.setClickListener(this);
        recyclerView.setAdapter(mShowsRecyclerViewAdapter);

        mSearchView = showsView.findViewById(R.id.searchview);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String userInput = newText.toLowerCase();
                ArrayList<Shows> newArrayList = new ArrayList<>();
                for(int i=0;i<mShowsArrayList.size();i++)
                {
                    Shows show = mShowsArrayList.get(i);
                    if(show.getArtist().toLowerCase().contains(userInput))
                    {
                        newArrayList.add(show);
                    }
                }

                mShowsRecyclerViewAdapter.updateList(newArrayList);
                return false;
            }
        });


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        return showsView;
    }





    private class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setTag(this);
        }
    }

    public ArrayList<Shows> getShows(Context context)
    {
        ArrayList<Shows> arrayList = new ArrayList<>();

        String file = "data.json";
        String showsStr = loadJSONFromAsset(context, file);
        Log.d("Shows: ", (showsStr));


        try {

            JSONObject jsonObject = new JSONObject(showsStr);
            JSONArray jsonArray = jsonObject.getJSONArray("shows");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Log.i("ShowsJson", "artist" + object.getString("artist"));

                Shows shows = new Shows(object.getInt("id"), object.getString("artist"),
                        object.getString("date"), object.getString("venue"), object.getString("supporting_artists"),
                        object.getString("tickets"), object.getInt("resource"));

                String uri = "@drawable/"+ object.getString("artist").toLowerCase();
                Activity activity = getActivity();
                int resourceID;
                if(activity != null)
                {
                    resourceID = getContext().getResources().getIdentifier(uri, "drawable", activity.getPackageName());
                    Log.i("resourceShow", Integer.toString(resourceID));
                    shows.setResource(resourceID);
                }
                arrayList.add(shows);


            }
        } catch (JSONException jsonEx) {
            jsonEx.printStackTrace();
        }



        return arrayList;
    }

    public String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + mShowsRecyclerViewAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), ShowsDetailActivity.class);
        intent.putExtra("showDetails", mShowsRecyclerViewAdapter.getItem(position));
        startActivity(intent);
    }

//    private String changeDateFormat(String currentFormat,String requiredFormat,String dateString){
//        String result="";
//        if (Strings.isNullOrEmpty(dateString)){
//            return result;
//        }
//        SimpleDateFormat formatterOld = new SimpleDateFormat(currentFormat, Locale.getDefault());
//        SimpleDateFormat formatterNew = new SimpleDateFormat(requiredFormat, Locale.getDefault());
//        Date date=null;
//        try {
//            date = formatterOld.parse(dateString);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        if (date != null) {
//            result = formatterNew.format(date);
//        }
//        return result;
//    }

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

    public String convertDate(String date)
    {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat newFormat = new SimpleDateFormat("EEEE, MMM d, yyyy");
        String newDateStr = "";
        Date newDate = null;
        try{
            newDate = dt.parse(date);
        } catch (ParseException e){
            e.printStackTrace();
        }
        if(newDate!= null){
            newDateStr = newFormat.format(newDate);
        }

        return newDateStr;
    }
}