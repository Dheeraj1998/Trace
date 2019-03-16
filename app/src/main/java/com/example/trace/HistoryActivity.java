package com.example.trace;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTheme(R.style.AppTheme);

        final TextView recorded_data = findViewById(R.id.history_data);
        CalendarView date_select = findViewById(R.id.date_select);

        date_select.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String data_string_start, data_string_end;
                recorded_data.setText("No recorded data today.");

                month += 1;

                if(month <= 9) {
                    data_string_start = year + "/0" + month + "/";
                    data_string_end = year + "/0" + month + "/";
                }

                else{
                    data_string_start = year + "/" + month + "/";
                    data_string_end = year + "/" + month + "/";
                }

                if(dayOfMonth <= 9) {
                    data_string_start += "0" + dayOfMonth + " 00:00:00";
                    data_string_end += "0" + dayOfMonth + " 23:59:59";
                }

                else {
                    data_string_start += dayOfMonth + " 00:00:00";
                    data_string_end += dayOfMonth + " 23:59:59";
                }

                long start_millis = 0, end_millis = 0;

                Date date = null;
                try {
                    date = sdf.parse(data_string_start);
                    start_millis = date.getTime()/1000;

                    date = sdf.parse(data_string_end);
                    end_millis = date.getTime()/1000;
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //region Patient 1
                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();
                Query reqQuery = dbref.child("Anay");

                final long finalStart_millis = start_millis;
                final long finalEnd_millis = end_millis;
                reqQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot data_pack : dataSnapshot.getChildren()) {
                                if (finalStart_millis <= Long.parseLong(data_pack.getKey()) && finalEnd_millis >= Long.parseLong(data_pack.getKey())) {
                                    if(recorded_data.length() != 0){
                                        recorded_data.setText("Patient | Gas | Humidity | Stress | Temperature\n" + "\n" + "Anay" +
                                                " | " + data_pack.child("Gas").getValue().toString() +
                                                " | " + data_pack.child("Humidity").getValue().toString() +
                                                " | " + data_pack.child("Stress").getValue().toString() +
                                                " | " + data_pack.child("Temperature").getValue().toString());
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                //endregion

                //region Patient 2
                reqQuery = dbref.child("Akarsh");

                reqQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot data_pack : dataSnapshot.getChildren()) {
                                if (finalStart_millis <= Long.parseLong(data_pack.getKey()) && finalEnd_millis >= Long.parseLong(data_pack.getKey())) {
                                    if(recorded_data.length() != 0){
                                        recorded_data.setText("Patient | Gas | Humidity | Stress | Temperature\n" + "\n" + "Akarsh" +
                                                " | " + data_pack.child("Gas").getValue().toString() +
                                                " | " + data_pack.child("Humidity").getValue().toString() +
                                                " | " + data_pack.child("Stress").getValue().toString() +
                                                " | " + data_pack.child("Temperature").getValue().toString());
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                //endregion

                //region Patient 3
                reqQuery = dbref.child("Advik");

                reqQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot data_pack : dataSnapshot.getChildren()) {
                                if (finalStart_millis <= Long.parseLong(data_pack.getKey()) && finalEnd_millis >= Long.parseLong(data_pack.getKey())) {
                                    if(recorded_data.length() != 0){
                                        recorded_data.setText("Patient | Gas | Humidity | Stress | Temperature\n" + "\n" + "Advik" +
                                                " | " + data_pack.child("Gas").getValue().toString() +
                                                " | " + data_pack.child("Humidity").getValue().toString() +
                                                " | " + data_pack.child("Stress").getValue().toString() +
                                                " | " + data_pack.child("Temperature").getValue().toString());
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                //endregion

                //region Patient 4
                reqQuery = dbref.child("Abram");

                reqQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot data_pack : dataSnapshot.getChildren()) {
                                if (finalStart_millis <= Long.parseLong(data_pack.getKey()) && finalEnd_millis >= Long.parseLong(data_pack.getKey())) {
                                    if(recorded_data.length() != 0){
                                        recorded_data.setText("Patient | Gas | Humidity | Stress | Temperature\n" + "\n" + "Abram" +
                                                " | " + data_pack.child("Gas").getValue().toString() +
                                                " | " + data_pack.child("Humidity").getValue().toString() +
                                                " | " + data_pack.child("Stress").getValue().toString() +
                                                " | " + data_pack.child("Temperature").getValue().toString());
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                //endregion

            }
        });
    }
}
