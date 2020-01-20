package com.vms.android.vancouvermetalshows;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.DatabaseMetaData;
import java.util.HashMap;
import java.util.Map;

public class ButtonOptionsFragment extends Fragment {

    Button mInterestedButton;
    Button mShareButton;
    Button mGetTixButton;
    Shows show;

    public static final String ARTIST_KEY = "artist";
    public static final String DATE_KEY = "date";
    public static final String VENUE_KEY = "venue";
    public static final String SUP_ART_KEY = "supporting_artists";
    public static final String TICKETS_KEY = "tickets";


    private DocumentReference mDocumentReference = FirebaseFirestore.getInstance().document("vancouver-metal-shows/myshows");
    FirebaseFirestore  db = FirebaseFirestore.getInstance();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View buttonOptionsView = inflater.inflate(R.layout.button_options_fragment, container, false);

       // FirebaseFirestore db = FirebaseFirestore.getInstance();


        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null)
        {
            show = extras.getParcelable("showDetails");
            //show.printShow();
            //Log.d("ShowDetail: ", extras.getString("showDetails"));
        }



        final Map<String, Object> selectedShow = new HashMap<>();
        selectedShow.put(ARTIST_KEY, show.getArtist());
        selectedShow.put(DATE_KEY, show.getDate());
        selectedShow.put(VENUE_KEY, show.getVenue());
        selectedShow.put(SUP_ART_KEY, show.getSupporting_artists());
        selectedShow.put(TICKETS_KEY, show.getTickets());



        mInterestedButton = buttonOptionsView.findViewById(R.id.interested_button);
        mInterestedButton.setText("Interested");
        mInterestedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("myshows").add(selectedShow).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("checkSave", "show saved");
                        Toast.makeText(getContext(), "Added to My Shows", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "not saved", Toast.LENGTH_LONG).show();
                        Log.d("checkFail", "not saved",e);
                    }
                });

            }
        });

        mShareButton =  buttonOptionsView.findViewById(R.id.share_button);
        mShareButton.setText("Share");
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationInfo applicationInfo = getContext().getApplicationContext().getApplicationInfo();
                String apkpath = applicationInfo.sourceDir;
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("application/vnd.android.package-archive");
                intent.putExtra(Intent.EXTRA_TEXT, Uri.fromFile(new File(apkpath)));
                startActivity(Intent.createChooser(intent, "Share"));
            }
        });

        Button mGetTixButton = buttonOptionsView.findViewById(R.id.get_tickets_button);
        mGetTixButton.setText("Get Tickets");
        mGetTixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.ticketmaster.ca"));
                startActivity(intent);
            }
        });

        return buttonOptionsView;

    }


}
