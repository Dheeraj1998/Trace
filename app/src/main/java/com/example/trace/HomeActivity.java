package com.example.trace;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_home);

        Toast.makeText(HomeActivity.this, "Login successful.", Toast.LENGTH_SHORT).show();

        FloatingActionButton add_button = findViewById(R.id.add_btn);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("custom","Hello!");
            }
        });
    }

}
