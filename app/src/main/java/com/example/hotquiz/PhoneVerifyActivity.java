package com.example.hotquiz;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

public class PhoneVerifyActivity extends AppCompatActivity {



    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_CODE_SENT = 2;
    private static final int STATE_VERIFY_FAILED = 3;
    private static final int STATE_VERIFY_SUCCESS = 4;
    private static final int STATE_SIGNIN_FAILED = 5;
    private static final int STATE_SIGNIN_SUCCESS = 6;

    TextView textView;
    CountryCodePicker ccp;
    EditText editTextCarrierNumber;
    ImageView phone_successs;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth auth;
    public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private boolean mVerificationInProgress;
    private Button verify_button;
    private String phone_number = null;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private AlertDialog alertDialog;
    private EditText otp_code;
    private TextView dialog_notification;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verify);

        //initialize firebase app
        FirebaseApp.initializeApp(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        populate_profile_name_google();



        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        editTextCarrierNumber = (EditText) findViewById(R.id.editText_carrierNumber);
        phone_successs = (ImageView) findViewById(R.id.phone_successs);
        verify_button = (Button) findViewById(R.id.phone_verify_button);

        phone_successs.setVisibility(View.INVISIBLE);
        verify_button.setEnabled(false);
        verify_button.setVisibility(View.INVISIBLE);
        
        ccp.registerCarrierNumberEditText(editTextCarrierNumber);


        ccp.setPhoneNumberValidityChangeListener(new CountryCodePicker.PhoneNumberValidityChangeListener() {
            @Override
            public void onValidityChanged(boolean isValidNumber) {
                // your code
                System.out.println("number is valid " + isValidNumber);
                
                if(isValidNumber){
                    phone_successs.setVisibility(View.VISIBLE);

                    verify_button.setEnabled(true);
                    verify_button.setVisibility(View.VISIBLE);

                    phone_number = ccp.getFullNumberWithPlus();

                }else{
                    phone_successs.setVisibility(View.INVISIBLE);
                    verify_button.setEnabled(false);
                    verify_button.setVisibility(View.INVISIBLE);

                }
            }
        });


      //  auth = FirebaseAuth.getInstance();

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {



            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d("verification complete", "onVerificationCompleted:" + credential);
                // [START_EXCLUDE silent]
                mVerificationInProgress = false;
                // [END_EXCLUDE
                // [START_EXCLUDE silent]
                // Update the UI and attempt sign in with the phone credential
                String code = credential.getSmsCode();
                startHomePage();
              /*  if (code != null) {
                   // editTextCode.setText(code);
                    //verifying the code
                    verifyVerificationCode(code);
                }else{
                    update_verify_dialog();
                    Log.d("verification not valid", "onVerificationCompleted:" + credential.getSmsCode());
                } */


               // update_verify_dialog();

                // [END_EXCLUDE]
              //  signInWithPhoneAuthCredential(credential);
            }



            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w("verification failed", "onVerificationFailed", e);
                // [START_EXCLUDE silent]
                mVerificationInProgress = false;
                // [END_EXCLUDE]

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // [START_EXCLUDE]
                  //  mBinding.fieldPhoneNumber.setError("Invalid phone number.");
                    System.out.println("invalid phone number");
                    // [END_EXCLUDE]
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // [START_EXCLUDE]
                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_SHORT).show();
                    // [END_EXCLUDE]
                }

                // Show a message and update the UI
                // [START_EXCLUDE]
            //    updateUI(STATE_VERIFY_FAILED);

                // [END_EXCLUDE]
            }



            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d("codesent", "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                 mVerificationId = verificationId;
                 mResendToken = token;

                // [START_EXCLUDE]
                // Update UI
                update_verify_dialog();
                // [END_EXCLUDE]
            }
        };
        // [END phone_auth_callbacks]





    }

    private void verifyVerificationCode(String otp) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp);

        //signing the user
        signInWithPhoneAuthCredential(credential);


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(PhoneVerifyActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activity

                            startHomePage();

                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                                dialog_notification.setText("OTP code is invalid please wait for 2 minutes and try again");
                            }

                        /*   Snackbar snackbar = Snackbar.make(findViewById(R.id.parent), message, Snackbar.LENGTH_LONG);
                            snackbar.setAction("Dismiss", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            });
                            snackbar.show();
                           */
                        }
                    }
                });
    }

    private void startHomePage() {

        Intent intent = new Intent(PhoneVerifyActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void update_verify_dialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog, viewGroup, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
/*
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
              //  Toast.makeText(getBaseContext(), input.getText().toString(), Toast.LENGTH_SHORT).show();
                System.out.println("button is working");
            }
        }); */

            Button button_ok = (Button) dialogView.findViewById(R.id.buttonOk);
            otp_code = (EditText) dialogView.findViewById(R.id.otp_code);
              dialog_notification = (TextView) dialogView.findViewById(R.id.error_notification_dialog);

            button_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("button is working 2222222");

                    System.out.println("button is working 2222222");
                    if(otp_code.getText().length() == 6) {
                        verifyVerificationCode(otp_code.getText().toString().trim());
                    }else{

                        dialog_notification.setText("OTP code is invalid please wait for 2 minutes and try again");
                    }
                }
        });

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);




        //    builder.setNegativeButton(android.R.string.cancel, null);

        //finally creating the alert dialog and displaying it
         alertDialog = builder.create();
        alertDialog.show();



/*
        findViewById(R.id.b

        uttonOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("dialog submit","asdadasd");

                alertDialog.dismiss();
            }
        });
*/


    }

    private void populate_profile_name_google() {

        TextView userName=(TextView)findViewById(R.id.profile_username);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


            try{
                userName.setText(personName);

            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(),"image not found",Toast.LENGTH_LONG).show();
            }
        }
    }


    public void verify_phone_number(View view){
            //verify the phone number from CCP form else return error response

        // auth.setLanguageCode(Locale.getDefault().language);
        Log.d("reviewed_phone",   phone_number);
       FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();
        auth.setLanguageCode("En");

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone_number,//"+2348137896637",        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
        // [END start_phone_auth]

        mVerificationInProgress = true;
    }


}

