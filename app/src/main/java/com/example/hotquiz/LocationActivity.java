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

public class LocationActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "data";
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

         mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();



            Button logoutBtn = (Button) findViewById(R.id.logoutBtn);
            TextView userName=(TextView)findViewById(R.id.name);
            TextView userEmail=(TextView)findViewById(R.id.email);
            TextView userId=(TextView)findViewById(R.id.userId);
            ImageView profileImage=(ImageView)findViewById(R.id.profileImage);


            try{
                userName.setText(personName);
                userEmail.setText(personEmail);
                userId.setText(personId);
                Glide.with(this).load(personPhoto).into(profileImage);
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(),"image not found",Toast.LENGTH_LONG).show();
            }
        }



    }

    public void googleSignOut(View view){


        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        gotoMainActivity();
                    }
                });
    }

    private void gotoMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}
