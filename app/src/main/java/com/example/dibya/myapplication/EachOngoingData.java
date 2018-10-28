package com.example.dibya.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EachOngoingData extends AppCompatActivity {

    Button tradetaken;
    String type = "";
    String data2;
    String proloss;
    CheckBox c1, c2, c3, c4, c5, c6;
    EditText e1, e2, e3, e4, e5, e6, e7, time1, time2, time3, time4, time5, time6;
    Button save;
    Switch s, s2;
    RadioButton rb1, rb2;
    Button submit;
    private DatabaseReference mDatabase;
    FirebaseUser firebaseUser;
    String key;
    Ongoing ongoing;
    String current_user;
    boolean trade_taken = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_ongoing_data);
        ongoing = (Ongoing) getIntent().getSerializableExtra("data");
        key = getIntent().getStringExtra("key");

        //Toast.makeText(this,ongoing.getUserId(),Toast.LENGTH_LONG).show();

        Toolbar tb = findViewById(R.id.tb);
        setSupportActionBar(tb);
        tb.setTitleTextColor(Color.WHITE);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        s = findViewById(R.id.type_1);
        s2 = findViewById(R.id.switch2_1);
        c1 = findViewById(R.id.ch1_1);
        c2 = findViewById(R.id.ch2_1);
        c3 = findViewById(R.id.ch3_1);
        c4 = findViewById(R.id.ch4_1);
        c5 = findViewById(R.id.ch5_1);
        c6 = findViewById(R.id.ch6_1);
        Boolean b1 = s.isChecked();
        Boolean b2 = s2.isChecked();

        //Handle checkbox thing



        e1 = findViewById(R.id.entry_1);
        e2 = findViewById(R.id.time_1);
        e3 = findViewById(R.id.target_1);
        e4 = findViewById(R.id.stop_1);
        e5 = findViewById(R.id.warning_1);
        e6 = findViewById(R.id.proloss_1);
        e7 = findViewById(R.id.comments_1);
        rb1 = findViewById(R.id.rb1_1);
        rb2 = findViewById(R.id.rb2_1);
        tradetaken = findViewById(R.id.trade);
        submit = findViewById(R.id.finalsubmit);
        time1 = findViewById(R.id.time1_1);
        time2 = findViewById(R.id.time2_1);
        time3 = findViewById(R.id.time3_1);
        time4 = findViewById(R.id.time4_1);
        time5 = findViewById(R.id.time5_1);
        time6 = findViewById(R.id.time6_1);

        if(ongoing.getType().equalsIgnoreCase("Long"))
            s.setChecked(true);
        else
            s.setChecked(false);

        if (ongoing.getSwitch2().equalsIgnoreCase("neiit"))
            s2.setChecked(true);
        else
            s2.setChecked(false);

        e1.setText(ongoing.getEntry());
        e2.setText(ongoing.getTime());
        e3.setText(ongoing.getTarget());
        e4.setText(ongoing.getStop());
        e5.setText(ongoing.getWarnings());
        time1.setText(ongoing.getTime1());
        time2.setText(ongoing.getTime2());
        time3.setText(ongoing.getTime3());
        time4.setText(ongoing.getTime4());
        time5.setText(ongoing.getTime5());
        time6.setText(ongoing.getTime6());
        firebaseUser = MyFirebase.mAuth.getCurrentUser();
        current_user = firebaseUser.getEmail();

        new SetTime(time1, this);
        new SetTime(time2, this);
        new SetTime(time3, this);
        new SetTime(time4, this);
        new SetTime(time5, this);
        new SetTime(time6, this);
        new SetTime(e2,this);

        if (current_user.equals(ongoing.getUserId()) && ongoing.getTradetaken()==false)
        {
            tradetaken.setEnabled(true);
        }
        else if(current_user.equals(ongoing.getUserId()) && ongoing.getTradetaken()==true)
        {
            s.setEnabled(false);
            s2.setEnabled(false);
            e1.setEnabled(false);
            e2.setEnabled(false);
            e3.setEnabled(false);
            e4.setEnabled(false);
            e5.setEnabled(false);
            time1.setEnabled(false);
            time2.setEnabled(false);
            time3.setEnabled(false);
            time4.setEnabled(false);
            time5.setEnabled(false);
            time6.setEnabled(false);
        }
        else {
            submit.setEnabled(false);
            tradetaken.setEnabled(false);
            s.setEnabled(false);
            s2.setEnabled(false);
            e1.setEnabled(false);
            e2.setEnabled(false);
            e3.setEnabled(false);
            e4.setEnabled(false);
            e5.setEnabled(false);
            time1.setEnabled(false);
            time2.setEnabled(false);
            time3.setEnabled(false);
            time4.setEnabled(false);
            time5.setEnabled(false);
            time6.setEnabled(false);

        }
        if (ongoing.getTradetaken())
        {
            trade_taken = true;
            s.setEnabled(false);
            s2.setEnabled(false);
            e1.setEnabled(false);
            e2.setEnabled(false);
            e3.setEnabled(false);
            e4.setEnabled(false);
            e5.setEnabled(false);
            time1.setEnabled(false);
            time2.setEnabled(false);
            time3.setEnabled(false);
            time4.setEnabled(false);
            time5.setEnabled(false);
            time6.setEnabled(false);
            rb1.setEnabled(true);
            rb2.setEnabled(true);
            e7.setEnabled(true);
            e6.setEnabled(true);
            submit.setText("Submit");
        }
        if(tradetaken.isEnabled())
        {
            tradetaken.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    trade_taken = true;
                    s.setEnabled(false);
                    s2.setEnabled(false);
                    e1.setEnabled(false);
                    e2.setEnabled(false);
                    e3.setEnabled(false);
                    e4.setEnabled(false);
                    e5.setEnabled(false);
                    time1.setEnabled(false);
                    time2.setEnabled(false);
                    time3.setEnabled(false);
                    time4.setEnabled(false);
                    time5.setEnabled(false);
                    time6.setEnabled(false);
                    rb1.setEnabled(true);
                    rb2.setEnabled(true);
                    e7.setEnabled(true);
                    e6.setEnabled(true);
                    submit.setText("Submit");
                    update_trade_values();

                }
            });
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(submit.getText().equals("Save"))
                {
                    update_values();

                }
                else if(submit.getText().equals("Submit"))
                {
                    boolean status = validateData();
                    if(status)
                    {
                        Completed completed = new Completed();
                        completed.setUserId(ongoing.getUserId());
                        if (s.isChecked()) {
                            type = (String) s.getTextOn();
                        } else {
                            type = (String) s.getTextOff();
                        }
                        completed.setType(type);
                        if (s2.isChecked()) {
                            data2 = (String) s2.getTextOn();
                        } else {
                            data2 = (String) s2.getTextOff();
                        }
                        completed.setTime(e2.getText().toString());
                        completed.setSwitch2(data2);
                        completed.setEntry(e1.getText().toString());
                        completed.setTarget(e3.getText().toString());
                        completed.setStop(e4.getText().toString());
                        completed.setWarnings(e5.getText().toString());
                        completed.setTime1(time1.getText().toString());
                        completed.setTime2(time2.getText().toString());
                        completed.setTime3(time3.getText().toString());
                        completed.setTime4(time4.getText().toString());
                        completed.setTime5(time5.getText().toString());
                        completed.setTime6(time6.getText().toString());
                        completed.setDate("Dummy for now");
                        completed.setCheckboxinfo("Dummy for now");
                        completed.setComments(e7.getText().toString());
                        if (rb1.isChecked()) {
                            proloss = (String) rb1.getText();

                        } else {
                            proloss = (String) rb2.getText();
                        }
                        completed.setProtype(proloss);
                        completed.setProfit(e6.getText().toString());
                        completed.setTimeStamp(System.currentTimeMillis());
                        mDatabase.child("Completed").push().setValue(completed)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Intent i = new Intent(EachOngoingData.this, MainActivity.class);
                                        // set the new task and clear flags
                                        mDatabase.child("Ongoing").child(key).removeValue();

                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast.makeText(EachOngoingData.this,"Could Not Send data to the server",Toast.LENGTH_LONG).show();
                                    }
                                });

                    }
                    else
                    {
                        showAlert();
                    }

                }

            }
        });

    }

    private void update_trade_values() {
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
        ongoing.setTime1(time1.getText().toString());
        ongoing.setTime2(time2.getText().toString());
        ongoing.setTime3(time3.getText().toString());
        ongoing.setTime4(time4.getText().toString());
        ongoing.setTime5(time5.getText().toString());
        ongoing.setTime6(time6.getText().toString());
        ongoing.setSwitch2(data2);
        ongoing.setEntry(e1.getText().toString());
        ongoing.setTime(e2.getText().toString());
        ongoing.setTarget(e3.getText().toString());
        ongoing.setStop(e4.getText().toString());
        ongoing.setWarnings(e5.getText().toString());
        ongoing.setDate("Dummy for now");
        ongoing.setCheckboxinfo("Dummy for now");
        ongoing.setTimeStamp(System.currentTimeMillis());
        ongoing.setTradetaken(trade_taken);
        mDatabase.child("Ongoing").child(key).setValue(ongoing)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(EachOngoingData.this,"Could Not Send data to the server",Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void update_values() {
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
        ongoing.setTime1(time1.getText().toString());
        ongoing.setTime2(time2.getText().toString());
        ongoing.setTime3(time3.getText().toString());
        ongoing.setTime4(time4.getText().toString());
        ongoing.setTime5(time5.getText().toString());
        ongoing.setTime6(time6.getText().toString());
        ongoing.setSwitch2(data2);
        ongoing.setEntry(e1.getText().toString());
        ongoing.setTime(e2.getText().toString());
        ongoing.setTarget(e3.getText().toString());
        ongoing.setStop(e4.getText().toString());
        ongoing.setWarnings(e5.getText().toString());
        ongoing.setDate("Dummy for now");
        ongoing.setCheckboxinfo("Dummy for now");
        ongoing.setTimeStamp(System.currentTimeMillis());
        ongoing.setTradetaken(trade_taken);
        mDatabase.child("Ongoing").child(key).setValue(ongoing)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent i = new Intent(EachOngoingData.this, MainActivity.class);
                        // set the new task and clear flags
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(EachOngoingData.this,"Could Not Send data to the server",Toast.LENGTH_LONG).show();
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
        if (e1.getText().toString().trim().length()!=0 && e3.getText().toString().trim().length()!=0 && e7.getText().toString().trim().length()!=0)
        {
            if(e4.getText().toString().trim().length()!=0  && e5.getText().toString().trim().length()!=0 && e6.getText().toString().trim().length()!=0)
            {
                status = true;
            }
        }
        return status;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(current_user.equals(ongoing.getUserId())){
            getMenuInflater().inflate(R.menu.menu_ongoing,menu);
            return true;
        }

        return false;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(current_user.equals(ongoing.getUserId())) {
            if(trade_taken == true)
            {
                show_Alert();
            }
            else {
                showConfirm();
            }


            return true;
        }
        Toast.makeText(this,"Program should never touch this part",Toast.LENGTH_LONG).show();
        return false;
    }

    private void show_Alert() {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("You can't delete this. You have already taken the trade");
        ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        ab.show();
    }

    private void showConfirm() {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("Delete Confirmation");
        ab.setMessage("Are you sure to delete the data?");
        ab.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mDatabase.child("Ongoing").child(key).removeValue();
                Intent j = new Intent(EachOngoingData.this, MainActivity.class);
                // set the new task and clear flags
                j.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(j);
            }
        });
        ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        ab.show();
    }
}
