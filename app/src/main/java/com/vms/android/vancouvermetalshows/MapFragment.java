package com.vms.android.vancouvermetalshows;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    public MapFragment() {
        // Required empty public constructor
    }


    MapView mMapView;
    private GoogleMap mGoogleMap;
    private static final String TAG = "MapFragment";

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View mapFragmentView = inflater.inflate(R.layout.fragment_map, container, false);


        return mapFragmentView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity()!=null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            if (mapFragment != null) {
                mapFragment.getMapAsync(this);
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;
        LatLng vancouver = new LatLng(49.2827, 123.1207);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(vancouver);
        markerOptions.title("Show location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mGoogleMap.addMarker(markerOptions);

        //mGoogleMap.addMarker(new MarkerOptions().position(vancouver).title("Marker in vancouver"));
        //mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(vancouver));
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(vancouver, 16), 2000,null);


        if (mGoogleMap == null)
        {
            Log.d("MapNull", "Map is null");
        }
        else{
            Log.d("MapNull_no", "Map is not null");
        }


    }







}
