package com.vms.android.vancouvermetalshows;

import androidx.annotation.NonNull;


public class Favourites {
    @NonNull


    private Shows mShows;

    public Favourites (Shows show)
    {
        this.mShows = show;
    }

    public int getId()
    {
        return mShows.getId();
    }

    public String getArtist()
    {
        return mShows.getArtist();
    }

    public String getDate()
    {
        return mShows.getDate();
    }

    public String getVenue()
    {
        return mShows.getVenue();
    }

    public String getSupporting_artists()
    {
        return mShows.getSupporting_artists();
    }

    public String getTickets()
    {
        return mShows.getTickets();
    }
}
