package com.vms.android.vancouvermetalshows;

import java.util.Comparator;

public class ShowComparator implements Comparator<Shows> {

    @Override
    public int compare(Shows show1, Shows show2)
    {
        return show1.stringToDate(show1.getDate()).compareTo(show2.stringToDate(show2.getDate()));
    }
}
