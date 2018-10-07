package com.example.dibya.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import java.util.HashMap;

public class OngoingTab_Fragment extends android.support.v4.app.Fragment {

    OngoingAdapter rcAdapter;
    RecyclerView rc;
    HashMap<String, Ongoing> details;
    private DatabaseReference mDatabase;
    ProgressDialog mProgressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.ongoing_tab, container, false);
        rc= v.findViewById(R.id.ongoing);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        details = new HashMap<>();
        setData();
        rcAdapter = new OngoingAdapter(getActivity(), details);
        rc.setAdapter(rcAdapter);
        makegrid();
        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        setData();
    }
    private void setData() {
        showProgressDialog();

        Query myTopPostsQuery = mDatabase.child("Ongoing")
                .orderByChild("timeStamp");

        myTopPostsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Ongoing Ongoing = dataSnapshot.getValue(Ongoing.class);
                details.put(dataSnapshot.getKey(), Ongoing);
                rcAdapter.notifyDataSetChanged();
                hideProgressDialog();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Ongoing ongoing = dataSnapshot.getValue(Ongoing.class);
                details.put(dataSnapshot.getKey(), ongoing);
                rcAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Ongoing ongoing = dataSnapshot.getValue(Ongoing.class);
                details.remove(dataSnapshot.getKey());
                rcAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();

    }

    private void makegrid() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,1);
        //GridLayoutManager lLayout = new GridLayoutManager(getActivity(), 2);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(staggeredGridLayoutManager);
        //rc.setAdapter(rcAdapter);

    }
}