package com.vms.android.vancouvermetalshows;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ShowsFragment extends Fragment implements ShowsRecyclerViewAdapter.ItemClickListener  {
    private ArrayList<Shows> mShowsArrayList;
    private ShowsRecyclerViewAdapter mShowsRecyclerViewAdapter;
    private View.OnClickListener onItemClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard

        View showsView = inflater.inflate(R.layout.fragment_shows, container,false);
//        MenuInflater menuInflater = getActivity().getMenuInflater();
//        menuInflater.inflate(R.menu.search, );

        Context context = getContext();

        String file = "data.json";
        String showsStr = loadJSONFromAsset(context, file);
        //Log.d("Shows: ", (showsStr));

        mShowsArrayList = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(showsStr);
            JSONArray jsonArray = jsonObject.getJSONArray("shows");
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);
                Log.i("ShowsJson", "artist" + object.getString("artist"));
                Shows shows = new Shows(object.getInt("id"), object.getString("artist"),
                        object.getString("date"), object.getString("venue"), object.getString("supporting_artists"),
                        object.getString("tickets"));
                mShowsArrayList.add(shows);



            }
        } catch (JSONException jsonEx){
            jsonEx.printStackTrace();
        }

        String s = "printShows";
        for(int j=0;j<mShowsArrayList.size();j++)
        {
            Log.d("mShowsArrayList: ", s);
            mShowsArrayList.get(j).printShow();
        }

        RecyclerView recyclerView = showsView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mShowsRecyclerViewAdapter = new ShowsRecyclerViewAdapter(context, mShowsArrayList);
        mShowsRecyclerViewAdapter.setClickListener(this);
        recyclerView.setAdapter(mShowsRecyclerViewAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        //recyclerView.setHasFixedSize(true);


        return showsView;
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setTag(this);
        }
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
        Intent intent = new Intent(getContext(),ShowsDetailActivity.class);
        intent.putExtra("showDetails",mShowsRecyclerViewAdapter.getItem(position));
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
}
