package com.example.dibya.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TestAdapter rcAdapter;
    RecyclerView rc;
    ArrayList<TradeData> details;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar tb = findViewById(R.id.tb);
        setSupportActionBar(tb);
        tb.setTitleTextColor(Color.WHITE);
        final SQLiteDatabase mydatabase = openOrCreateDatabase("trade_data", MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Trade(Id INTEGER PRIMARY KEY AUTOINCREMENT,type VARCHAR(10), switch2 VARCHAR(10) ," +
                "Profit INTEGER,protype VARCHAR(10),entry VARCHAR(100),target VARCHAR(100), stop VARCHAR(100), warning VARCHAR(100), " +
                "comment VARCHAR(100), date VARCHAR(100),extrainfo VARCHAR(100) );");
        cursor = mydatabase.rawQuery("Select * from Trade ORDER BY Id DESC", null);

        rc = findViewById(R.id.rc);
        setData();
        if (details.isEmpty()) {
            Snackbar.make(findViewById(R.id.linear), "No data to Display", Snackbar.LENGTH_LONG).setAction("CLOSE", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }).show();
        }
        rcAdapter = new TestAdapter(this, details);

        makegrid();
    }

    @Override
    protected void onResume() {
        super.onResume();
        rcAdapter.notifyDataSetChanged();

    }

    private void setData() {
        details = new ArrayList<>();
        while (cursor.moveToNext()) {
            TradeData t = new TradeData();
            t.setId(cursor.getInt(0));
            t.setType(cursor.getString(1));
            t.setSwitch2(cursor.getString(2));
            t.setProfit(cursor.getInt(3));
            t.setProtype(cursor.getString(4));
            t.setEntry(cursor.getString(5));
            t.setTarget(cursor.getString(6));
            t.setStop(cursor.getString(7));
            t.setWarnings(cursor.getString(8));
            t.setComments(cursor.getString(9));
            t.setDate(cursor.getString(10));
            t.setCheckboxinfo(cursor.getString(11));
            details.add(t);
            Log.i("dibya", t.getProtype() + t.getProfit());
        }

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
        }
        return true;
    }
}
