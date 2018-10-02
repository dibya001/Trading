package com.example.dibya.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

public class Add_data extends AppCompatActivity {

    String type = "";
    String data2;
    String proloss;
    CheckBox c1, c2, c3, c4, c5, c6;
    EditText e1, e2, e3, e4, e5, e6, e7;
    Button submit;
    Switch s, s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        Toolbar tb = findViewById(R.id.tb);
        setSupportActionBar(tb);
        tb.setTitleTextColor(Color.WHITE);
        final SQLiteDatabase mydatabase = openOrCreateDatabase("trade_data", MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Trade(Id INTEGER PRIMARY KEY AUTOINCREMENT,type VARCHAR(10), switch2 VARCHAR(10) ," +
                "Profit INTEGER,protype VARCHAR(10),entry VARCHAR(100),target VARCHAR(100), stop VARCHAR(100), warning VARCHAR(100), " +
                "comment VARCHAR(100), date VARCHAR(100),extrainfo VARCHAR(100) );");

        s = findViewById(R.id.type);
        c1 = findViewById(R.id.ch1);
        c2 = findViewById(R.id.ch2);
        c3 = findViewById(R.id.ch3);
        c4 = findViewById(R.id.ch4);
        c5 = findViewById(R.id.ch5);
        c6 = findViewById(R.id.ch6);

        if (s.isChecked()) {
            type = (String) s.getTextOn();
        } else {
            type = (String) s.getTextOff();
        }
        s2 = findViewById(R.id.switch2);
        if (s2.isChecked()) {
            data2 = (String) s2.getTextOn();
        } else {
            data2 = (String) s2.getTextOff();
        }
        Boolean b1 = s.isChecked();
        Boolean b2 = s2.isChecked();
        if (b1 && b2) {
            c1.setEnabled(true);
            c2.setEnabled(true);
        } else if (!b1 && b2) {

            c2.setEnabled(true);
            c5.setEnabled(true);
        } else if (!b1 && !b2) {
            c5.setEnabled(true);
            c6.setEnabled(true);
        } else if (b1 && !b2) {
            c4.setEnabled(true);
            c2.setEnabled(true);
        }

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                c1.setEnabled(false);
                c2.setEnabled(false);
                c3.setEnabled(false);
                c4.setEnabled(false);
                c5.setEnabled(false);
                c6.setEnabled(false);
                Boolean b1 = s.isChecked();
                Boolean b2 = s2.isChecked();
                if (b1 && b2) {
                    c1.setEnabled(true);
                    c2.setEnabled(true);
                } else if (!b1 && b2) {

                    c2.setEnabled(true);
                    c5.setEnabled(true);
                } else if (!b1 && !b2) {
                    c5.setEnabled(true);
                    c6.setEnabled(true);
                } else if (b1 && !b2) {
                    c4.setEnabled(true);
                    c2.setEnabled(true);
                }


            }
        });
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                c1.setEnabled(false);
                c2.setEnabled(false);
                c3.setEnabled(false);
                c4.setEnabled(false);
                c5.setEnabled(false);
                c6.setEnabled(false);
                Boolean b1 = s.isChecked();
                Boolean b2 = s2.isChecked();
                if (b1 && b2) {
                    c1.setEnabled(true);
                    c2.setEnabled(true);
                } else if (!b1 && b2) {

                    c2.setEnabled(true);
                    c5.setEnabled(true);
                } else if (!b1 && !b2) {
                    c5.setEnabled(true);
                    c6.setEnabled(true);
                } else if (b1 && !b2) {
                    c4.setEnabled(true);
                    c2.setEnabled(true);
                }


            }
        });

        e1 = findViewById(R.id.entry);
        e2 = findViewById(R.id.time);
        e3 = findViewById(R.id.target);
        e4 = findViewById(R.id.stop);
        e5 = findViewById(R.id.warning);
        e6 = findViewById(R.id.proloss);
        e7 = findViewById(R.id.comments);
        RadioButton rb1 = findViewById(R.id.rb1);
        RadioButton rb2 = findViewById(R.id.rb2);
        if (rb1.isChecked()) {
            proloss = (String) rb1.getText();
        } else {
            proloss = (String) rb2.getText();
        }
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String query = "INSERT INTO Trade(type,switch2,Profit,protype,entry,target,stop,warning,comment,date,extrainfo) VALUES(" + "\'" + type + "\'" + "," + "\'" + data2 + "\'" + "," + Integer.parseInt(e6.getText().toString())
                        + "," + "\'" + proloss + "\'" + "," + "\'" + e1.getText().toString() + "\'" + "," + "\'" + e3.getText().toString() + "\'" + "," + "\'" + e4.getText().toString() + "\'"
                        + "," + "\'" + e5.getText().toString() + "\'" + "," + "\'" + e7.getText().toString() + "\'" + "," + "\'" + "hello" + "\'" + "," + "\'" + "hello2" + "\'" + ")";
                mydatabase.execSQL(query);
                Intent i = new Intent(Add_data.this, MainActivity.class);
                // set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                //Changes made to Temp1 branch
                //System.out.print(5);

                //Log.i("data",proloss+e1.getText().toString()+e3.getText().toString()+e5.getText().toString());
                //Log.i("data",e2.getText().toString()+data2+type) ;
            }
        });
    }
}
