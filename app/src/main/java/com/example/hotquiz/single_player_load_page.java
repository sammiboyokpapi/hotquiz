package com.example.hotquiz;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class single_player_load_page extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player_load_page);

        // funds_button = (Button) findViewById(R.id.phone_verify_button);

        Intent intent=getIntent();

        String id = intent.getStringExtra("picture_id");
        String text = intent.getStringExtra("picture_text");
        String content = intent.getStringExtra("picture_content");

        load_game_parameters(id,text,content);

        start_game();
    }

    private void load_game_parameters(String id, String text, String content) {

        Button single_player_image = (Button) findViewById(R.id.image_container);
        TextView single_player_game_type = (TextView) findViewById(R.id.game_type);
        TextView single_player_game_description = (TextView) findViewById(R.id.game_description);

        single_player_image.setPadding(10,0,0,0);

        single_player_image.setBackgroundResource(getResources().getIdentifier(id, "drawable", getPackageName()));

        single_player_game_type.setText(text);

        single_player_game_description.setText(content);

    }

    private void start_game(){

        Button start_game_button = (Button) findViewById(R.id.start_game_btn);



        start_game_button.setOnClickListener(new View.OnClickListener() {

            public Intent intent;

            @Override
            public void onClick(View v) {

                home_clicked();
              /*   intent = new Intent(CurrentActivity.this, FundsActivity.class);

                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

               */
            }
        });

    }

    public void home_clicked() {
        // Do something in response to button
        Intent intent = new Intent(this, FundsActivity.class);

        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.bounce_interpolator, android.R.anim.cycle_interpolator);
     //   startActivity(intent);

       // getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }






   /* @Override
   // public void onBackPressed() {
        moveTaskToBack(false);
    }
*/


}
