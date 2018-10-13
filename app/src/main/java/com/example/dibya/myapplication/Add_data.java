package com.example.dibya.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_data extends AppCompatActivity {

    String type = "";
    String data2;
    String proloss;
    CheckBox c1, c2, c3, c4, c5, c6;
    EditText e1, e2, e3, e4, e5;
    Button save;
    Switch s, s2;
    private DatabaseReference mDatabase;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        Toolbar tb = findViewById(R.id.tb);
        setSupportActionBar(tb);
        tb.setTitleTextColor(Color.WHITE);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseUser = MyFirebase.mAuth.getCurrentUser();
        s = findViewById(R.id.type);
        s2 = findViewById(R.id.switch2);
        c1 = findViewById(R.id.ch1);
        c2 = findViewById(R.id.ch2);
        c3 = findViewById(R.id.ch3);
        c4 = findViewById(R.id.ch4);
        c5 = findViewById(R.id.ch5);
        c6 = findViewById(R.id.ch6);
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
                c1.setChecked(false);
                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
                c5.setChecked(false);
                c6.setChecked(false);
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
                c1.setChecked(false);
                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
                c5.setChecked(false);
                c6.setChecked(false);
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

        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean status=validateData();
                if(status)
                {
                    Ongoing ongoing = new Ongoing();
                    String userid =  firebaseUser.getEmail();
                    ongoing.setUserId(userid);
                    if (s.isChecked()) {
                        type = (String) s.getTextOn();
                    } else {
                        type = (String) s.getTextOff();
                    }
                    ongoing.setType(type);
                    if (s2.isChecked()) {
                        data2 = (String) s2.getTextOn();
                    } else {
                        data2 = (String) s2.getTextOff();
                    }
                    ongoing.setSwitch2(data2);
                    ongoing.setEntry(e1.getText().toString());
                    ongoing.setTime(e2.getText().toString());
                    ongoing.setTarget(e3.getText().toString());
                    ongoing.setStop(e4.getText().toString());
                    ongoing.setWarnings(e5.getText().toString());
                    ongoing.setDate("Dummy for now");
                    ongoing.setCheckboxinfo("Dummy for now");
                    ongoing.setTimeStamp(System.currentTimeMillis());
                    ongoing.setTradetaken(false);
                    mDatabase.child("Ongoing").push().setValue(ongoing)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent i = new Intent(Add_data.this, MainActivity.class);
                                    // set the new task and clear flags
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(Add_data.this,"Could Not Send data to the server",Toast.LENGTH_LONG).show();
                                }
                            });

                }
                else
                {
                    showAlert();
                }

            }
        });
    }

    private void showAlert() {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("Can't Push Data to Server");
        ab.setMessage("One or More Empty Fields");
        ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        ab.show();
    }

    private Boolean validateData() {
        Boolean status = false;
        if (e1.getText().toString().trim().length()!=0 && e3.getText().toString().trim().length()!=0)
        {
            if(e4.getText().toString().trim().length()!=0  && e5.getText().toString().trim().length()!=0)
            {
                status = true;
            }
        }
        return status;
    }
}
