package com.example.trace;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FamilyActivity extends AppCompatActivity {

    private TextView fm_1_1, fm_1_2, fm_1_3, fm_1_4, fm_2_1, fm_2_2, fm_2_3, fm_2_4, fm_3_1, fm_3_2, fm_3_3, fm_3_4, fm_4_1, fm_4_2, fm_4_3, fm_4_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        fm_1_1 = findViewById(R.id.fm_1_1);
        fm_1_2 = findViewById(R.id.fm_1_2);
        fm_1_3 = findViewById(R.id.fm_1_3);
        fm_1_4 = findViewById(R.id.fm_1_4);

        fm_2_1 = findViewById(R.id.fm_2_1);
        fm_2_2 = findViewById(R.id.fm_2_2);
        fm_2_3 = findViewById(R.id.fm_2_3);
        fm_2_4 = findViewById(R.id.fm_2_4);

        fm_3_1 = findViewById(R.id.fm_3_1);
        fm_3_2 = findViewById(R.id.fm_3_2);
        fm_3_3 = findViewById(R.id.fm_3_3);
        fm_3_4 = findViewById(R.id.fm_3_4);

        fm_4_1 = findViewById(R.id.fm_4_1);
        fm_4_2 = findViewById(R.id.fm_4_2);
        fm_4_3 = findViewById(R.id.fm_4_3);
        fm_4_4 = findViewById(R.id.fm_4_4);

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();
        Query reqQuery = dbref.child("Abram");

        reqQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data_pack : dataSnapshot.getChildren()) {
                        fm_1_1.setText(data_pack.child("Gas").getValue().toString() + " ppm");
                        fm_1_2.setText(data_pack.child("Humidity").getValue().toString() + " g/m3");
                        fm_1_3.setText(data_pack.child("Stress").getValue().toString() + " str");
                        fm_1_4.setText(data_pack.child("Temperature").getValue().toString() + " °C");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reqQuery = dbref.child("Anay");

        reqQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data_pack : dataSnapshot.getChildren()) {
                        fm_4_1.setText(data_pack.child("Gas").getValue().toString() + " ppm");
                        fm_4_2.setText(data_pack.child("Humidity").getValue().toString() + " g/m3");
                        fm_4_3.setText(data_pack.child("Stress").getValue().toString() + " str");
                        fm_4_4.setText(data_pack.child("Temperature").getValue().toString() + " °C");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reqQuery = dbref.child("Advik");

        reqQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data_pack : dataSnapshot.getChildren()) {
                        fm_2_1.setText(data_pack.child("Gas").getValue().toString() + " ppm");
                        fm_2_2.setText(data_pack.child("Humidity").getValue().toString() + " g/m3");
                        fm_2_3.setText(data_pack.child("Stress").getValue().toString() + " str");
                        fm_2_4.setText(data_pack.child("Temperature").getValue().toString() + " °C");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reqQuery = dbref.child("Akarsh");

        reqQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data_pack : dataSnapshot.getChildren()) {
                        fm_3_1.setText(data_pack.child("Gas").getValue().toString() + " ppm");
                        fm_3_2.setText(data_pack.child("Humidity").getValue().toString() + " g/m3");
                        fm_3_3.setText(data_pack.child("Stress").getValue().toString() + " str");
                        fm_3_4.setText(data_pack.child("Temperature").getValue().toString() + " °C");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
