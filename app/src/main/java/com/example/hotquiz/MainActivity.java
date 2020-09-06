package com.example.hotquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "data" ;

    public Object page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        System.out.println("account : + " + account);
        if(account == null){
            page =  SigninActivity.class;
        }else{
            page = HomeActivity.class;
        }

    }

    public void getStarted(View view) {
        // Do something in response to button

        Intent intent = new Intent(this, (Class<?>) page);
     //   EditText editText = (EditText) findViewById(R.id.editText);
        String message = "final data";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
