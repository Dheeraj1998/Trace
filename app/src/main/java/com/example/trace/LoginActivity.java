package com.example.trace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_login);

        Button login_button = findViewById(R.id.loginbtn);
        final EditText userid = findViewById(R.id.userid);
        final EditText password = findViewById(R.id.password);

        login_button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                userid.setText("trace_001");
                password.setText("12345678");

                return true;
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home_intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(home_intent);
                finish();
            }
        });
    }
}
