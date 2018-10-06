package com.example.dibya.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TestAdapter rcAdapter;
    RecyclerView rc;
    HashMap<String, TradeData> details;
    private DatabaseReference mDatabase;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar tb = findViewById(R.id.tb);
        setSupportActionBar(tb);
        tb.setTitleTextColor(Color.WHITE);
        rc = findViewById(R.id.rc);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        details = new HashMap<>();
        setData();
        rcAdapter = new TestAdapter(this, details);
        makegrid();
    }

    @Override
    protected void onResume() {
        super.onResume();
        rcAdapter.notifyDataSetChanged();
    }

    private void setData() {
        showProgressDialog();

        Query myTopPostsQuery = mDatabase.child("Trade_info")
                .orderByChild("timeStamp");

        myTopPostsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TradeData tradeData = dataSnapshot.getValue(TradeData.class);
                details.put(dataSnapshot.getKey(), tradeData);
                rcAdapter.notifyDataSetChanged();
                hideProgressDialog();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TradeData tradeData = dataSnapshot.getValue(TradeData.class);
                details.put(dataSnapshot.getKey(), tradeData);
                rcAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                TradeData tradeData = dataSnapshot.getValue(TradeData.class);
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
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();

    }

    private void makegrid() {
        GridLayoutManager lLayout = new GridLayoutManager(this, 2);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(lLayout);
        rc.setAdapter(rcAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent i = new Intent(this, Add_data.class);
            startActivity(i);
            // Toast.makeText(this,"hthtt",Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.logout) {
            showAlert();
        }
        return true;
    }

    private void showAlert() {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("Are You Sure to Log out?");
        ab.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                signOut();
            }
        });
        ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        ab.show();
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        GoogleSignIn.getClient(this, MyFirebase.gso).signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }

                });
    }

}

