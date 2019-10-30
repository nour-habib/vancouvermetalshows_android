package com.vms.android.vancouvermetalshows;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ShowsRecyclerViewAdapter extends RecyclerView.Adapter<ShowsRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Shows> mShowsArrayList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    // data is passed into the constructor
    ShowsRecyclerViewAdapter(Context context, ArrayList<Shows> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mShowsArrayList = data;

    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Shows show = mShowsArrayList.get(position);
        holder.artistTextView.setText(show.getArtist());

        String date = show.getDate();
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

        Log.d("ShowDate: ", (newDateStr));


        holder.dateTextView.setText(newDateStr);
        holder.venueTextView.setText(show.getVenue());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mShowsArrayList.size();
    }




    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView artistTextView;
        TextView dateTextView;
        TextView venueTextView;


        ViewHolder(View itemView) {
            super(itemView);
            artistTextView = itemView.findViewById(R.id.artist);
            dateTextView = itemView.findViewById(R.id.date);
            venueTextView = itemView.findViewById(R.id.venue);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Shows getItem(int id) {
        return mShowsArrayList.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }



}
