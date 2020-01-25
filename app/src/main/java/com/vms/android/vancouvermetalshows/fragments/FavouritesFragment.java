package com.vms.android.vancouvermetalshows.fragments;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.vms.android.vancouvermetalshows.adapters.FavouritesListAdapter;
import com.vms.android.vancouvermetalshows.R;
import com.vms.android.vancouvermetalshows.classes.ShowComparator;
import com.vms.android.vancouvermetalshows.classes.Shows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class FavouritesFragment extends Fragment{


    private ListView favsListView;
    //private ImageButton mDeleteButton;
    private ArrayList<Shows> mFavouritesArrayList;
    private FavouritesListAdapter adapter;
    //private Button mExitButton;


    public FavouritesFragment() {
    }


    private DocumentReference mDocumentReference = FirebaseFirestore.getInstance().document("vancouver-metal-shows/myshows");
    FirebaseFirestore  db = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View favouritesView = inflater.inflate(R.layout.fragment_favourites, container, false);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mFavouritesArrayList = new ArrayList<>();
        Context context = favouritesView.getContext();
        adapter = new FavouritesListAdapter(context, R.layout.listview_row, mFavouritesArrayList);
        favsListView = favouritesView.findViewById(R.id.list_view);


        setHasOptionsMenu(true);
        getListItems();

        return favouritesView;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.edit_favs_list, menu);
        super.onCreateOptionsMenu(menu, menuInflater);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:


                if(mFavouritesArrayList.size()==0)
                {
                    Toast.makeText(getContext(), "List empty", LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "Press and hold to delete.", LENGTH_SHORT).show();
                    favsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                                       int pos, long id) {

                            //Log.v("long clicked","pos: " + pos);
                            deleteFromDB(adapter.getItem(pos));
                            adapter.remove(pos);
                            deleteN(view);

                            return true;
                        }
                    });
//
                }


//                mExitButton = getView().findViewById(R.id.exit_btn);
//                mExitButton.setText("Cancel");
//                mExitButton.setVisibility(View.VISIBLE);
                //addExitListenerOnButton();


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

//    public void addExitListenerOnButton()
//    {
//        mExitButton = getView().findViewById(R.id.exit_btn);
//        mExitButton.setText("Cancel");
//        mExitButton.setVisibility(View.VISIBLE);
//        mExitButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                mExitButton.setVisibility(View.GONE);
//                mDeleteButton.setVisibility(View.INVISIBLE);
//
//            }
//
//        });
//    }



    private void getListItems() {
        db.collection("myshows").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d("firebase", "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            // Convert the whole Query Snapshot to a list
                            // of objects directly! No need to fetch each
                            // document.
                            List<Shows> listShows = documentSnapshots.toObjects(Shows.class);

                            // Add all to your list
                            mFavouritesArrayList.addAll(listShows);
                            Log.d("firebase", "onSuccess: " + "firebase");
                            //Log.d("fbsize", Integer.toString(mFavouritesArrayList.size()));
                           // return arrayList;
                            Collections.sort(mFavouritesArrayList, new ShowComparator());
                            favsListView.setAdapter(adapter);
                        }
                    }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext().getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();

                        }
                    });

                }

    public void deleteN (View v)
    {
        mDocumentReference.delete();
    }

    private void deleteFromDB(Shows show) {
        final CollectionReference collectionReference = db.collection("myshows");
        Query query = collectionReference.whereEqualTo("artist", show.getArtist());
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        collectionReference.document(document.getId()).delete();
                    }
                } else {
                    Log.d("delete_error", "Error getting documents: ", task.getException());
                }
            }
        });

    }




}
