package com.example.hotquiz;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.tabs.TabLayout;

import lib.game.models.adapter.HomeAdapter;

public class HomeActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "data";
    private GoogleSignInClient mGoogleSignInClient;
    private Button funds_button;
    public Button single_player_button;
    public Button multi_player_button;
    public ViewPager viewPager;
    public Button tournament_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        funds_button = (Button) findViewById(R.id.phone_verify_button);

        load_tab_layout();

        load_button_layout();
    }

    private void load_button_layout(){
        single_player_button = (Button) findViewById(R.id.single_button_id);
        multi_player_button = (Button) findViewById(R.id.multi_player_button_id);
        tournament_button = (Button) findViewById(R.id.tournament_button_id);

        single_player_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something when the corky is clicked
                viewPager.setCurrentItem(0);
            }
        });
        multi_player_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something when the corky is clicked
                viewPager.setCurrentItem(1);
            }
        });
        tournament_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something when the corky is clicked
                viewPager.setCurrentItem(2);
            }
        });




    }

    private void load_tab_layout() {
        TabLayout tabLayout = (TabLayout)findViewById(R.id.home_tab_layout);

          viewPager = (ViewPager) findViewById(R.id.home_view_pager);

       // tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
       // tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
      //  tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));

     //   tabLayout.getTabAt(0).setIcon(R.drawable.single_player_icon).;

       // ImageView imgView2= new ImageView(this);
        //imgView2.setImageResource(R.drawable.single_player_icon);
       // imgView.setPadding(10,10,10,10);

        /*

        TextView textView = new TextView(this);
        textView.setText("Single player");
        textView.setTextSize(10);
        textView.setPadding(10,10,10,10);
        textView.setBackgroundResource(R.drawable.single_player_icon);
        textView.setTextColor(Color.WHITE);
        //textView.setGravity(center);


        View customView = LayoutInflater.from(this).inflate(R.layout.single_player, null);


        ViewGroup.LayoutParams link = new ViewGroup.LayoutParams(customView.getWidth(),customView.getHeight());
        // link
        textView.setLayoutParams(link);
        tabLayout.getTabAt(0).setCustomView(textView);


*/



        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final HomeAdapter adapter = new HomeAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
      //  View rootView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);




        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



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

    public void status_clicked(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, StatusActivity.class);
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
