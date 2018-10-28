package com.example.dibya.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

public class ViewInfo extends AppCompatActivity {

    MyTextView tx1, tx2, tx3, tx4, tx5, tx6, tx7, tx8, tx9, tx10, tx11, tx12, tx13, tx14;
    Completed completed;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info);
        completed = (Completed) getIntent().getSerializableExtra("data");
        key = getIntent().getStringExtra("key");
        Toolbar tb = findViewById(R.id.tb);
        setSupportActionBar(tb);
        tb.setTitleTextColor(Color.WHITE);

        tx1 = findViewById(R.id.entry_2);
        tx2 = findViewById(R.id.time_2);
        tx3 = findViewById(R.id.target_2);
        tx4 = findViewById(R.id.stop_2);
        tx5 = findViewById(R.id.warning_2);
        tx6 = findViewById(R.id.protype_2);
        tx7 = findViewById(R.id.proloss_2);
        tx8 =findViewById(R.id.comments_2);
        tx9 = findViewById(R.id.time1_2);
        tx10 = findViewById(R.id.time2_2);
        tx11 = findViewById(R.id.time3_2);
        tx12 = findViewById(R.id.time4_2);
        tx13 = findViewById(R.id.time5_2);
        tx14 =findViewById(R.id.time6_2);

        tx1.setText(completed.getEntry());
        tx2.setText(completed.getTime());
        tx3.setText(completed.getTarget());
        tx4.setText(completed.getStop());
        tx5.setText(completed.getWarnings());
        tx6.setText(completed.getProtype());
        tx7.setText(completed.getProfit());
        tx9.setText(completed.getTime1());
        tx10.setText(completed.getTime2());
        tx11.setText(completed.getTime3());
        tx12.setText(completed.getTime4());
        tx13.setText(completed.getTime5());
        tx14.setText(completed.getTime6());
        tx8.setText(completed.getComments());

    }
}
