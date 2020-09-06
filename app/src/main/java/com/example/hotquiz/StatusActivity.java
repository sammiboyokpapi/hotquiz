package com.example.hotquiz;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.time.Instant;

public class StatusActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "data";
    private GoogleSignInClient mGoogleSignInClient;
    private Button funds_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        funds_button = (Button) findViewById(R.id.phone_verify_button);


    }



    public void home_clicked(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, HomeActivity.class);
        //   EditText editText = (EditText) findViewById(R.id.editText);
        // String message = "final data";
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void funds_clicked(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, FundsActivity.class);
        //   EditText editText = (EditText) findViewById(R.id.editText);
        // String message = "final data";
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void settings_clicked(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, SettingsActivity.class);
        //   EditText editText = (EditText) findViewById(R.id.editText);
        // String message = "final data";
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}
