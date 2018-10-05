package com.example.dibya.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TestAdapter rcAdapter;
    RecyclerView rc;
    ArrayList<TradeData> details;
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
        details = new ArrayList<>();
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
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1: dataSnapshot.child("Trade_info").getChildren())
                {
                    Log.i("dibya",dataSnapshot.toString());
                    TradeData tradeData = dataSnapshot1.getValue(TradeData.class);
                    details.add(tradeData);
                    rcAdapter.notifyDataSetChanged();
                }
                hideProgressDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                hideProgressDialog();
                Snackbar.make(findViewById(R.id.linear), "Sorry!! Could not fetch data", Snackbar.LENGTH_LONG).setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
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
            signOut();
        }
        return true;
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

