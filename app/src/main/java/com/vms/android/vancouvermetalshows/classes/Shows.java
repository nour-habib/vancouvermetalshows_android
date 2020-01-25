package com.vms.android.vancouvermetalshows.classes;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Shows implements Parcelable{

    private int id;
    private String artist;
    private String date;
    private String venue;
    private String supporting_artists;
    private String tickets;
    private int resource;

    public Shows(int id, String art, String dt, String ven, String sup_art, String tix, int res) {
        this.id = id;
        this.artist = art;
        this.date = dt;
        this.venue = ven;
        this.supporting_artists = sup_art;
        this.tickets = tix;
        this.resource = res;

    }

    public Shows() {
    }

    public int getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public String getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }

    public String getSupporting_artists() {
        return supporting_artists;
    }

    public String getTickets() {
        return tickets;
    }

    public int getImgResourceId() {
        return resource;
    }

    public void setResource(int res) {
        this.resource = res;
    }


    public void printShow() {
        Log.d("printShows_id: ", Integer.toString(id));
        Log.d("printShows_artist: ", artist);
        Log.d("printShows_date: ", date);
        Log.d("printShows_venue: ", venue);
        Log.d("printShows_supp_artists", supporting_artists);
        Log.d("printShows_tickets: ", tickets);
        Log.d("printShows_resource: ", Integer.toString(resource));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(artist);
        out.writeString(date);
        out.writeString(venue);
        out.writeString(supporting_artists);
        out.writeString(tickets);
        out.writeInt(resource);
    }

    public static final Parcelable.Creator<Shows> CREATOR = new Parcelable.Creator<Shows>() {
        public Shows createFromParcel(Parcel in) {
            return new Shows(in);
        }

        public Shows[] newArray(int size) {
            return new Shows[size];
        }
    };

    private Shows(Parcel in) {
        id = in.readInt();
        artist = in.readString();
        venue = in.readString();
        date = in.readString();
        supporting_artists = in.readString();
        tickets = in.readString();
        resource = in.readInt();
    }


    public String convertDate(String date) {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat newFormat = new SimpleDateFormat("EEEE, MMM d, yyyy");
        String newDateStr = "";
        Date newDate = null;
        try {
            newDate = dt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (newDate != null) {
            newDateStr = newFormat.format(newDate);
        }

        return newDateStr;
    }

    Date stringToDate(String str)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try{
            date = dateFormat.parse(str);
        }catch(ParseException e)
        {
            e.printStackTrace();
        }

        return date;

    }


}
