package lib.game.models.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hotquiz.R;
import com.example.hotquiz.single_player_load_page;

import lib.game.models.SinglePlayer.single_player_model;


public class Select_Single_Player_Fragment extends Fragment {
    private single_player_model single_player_param;

    //Integer selected_layout;


    public Select_Single_Player_Fragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        single_player_param = new single_player_model();

        return inflater.inflate(R.layout.select_single_player_game, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      Button nigeria_sport = (Button) view.findViewById(R.id.nigeria_sport_icon);
        Button nigeria_movies = (Button) view.findViewById(R.id.nigeria_movies_icon);
        Button nigeria_politics = (Button) view.findViewById(R.id.nigeria_politics_icon);
        Button nigeria_entertainment = (Button) view.findViewById(R.id.nigeria_entertainment_icon);
        Button nigeria_history = (Button) view.findViewById(R.id.nigeria_history_icon);

        nigeria_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something when the corky is clicked
                Intent intent = new Intent(getActivity(), single_player_load_page.class);
                //   EditText editText = (EditText) findViewById(R.id.editText);
                // String message = "final data";
                intent.putExtra("picture_id", "africa_icon_white");
                intent.putExtra("picture_text", "Nigeria Sport");
                intent.putExtra("picture_content", "Are you a Sport enthusiast ?  Take this challenge to find out ");

                startActivity(intent);
            }
        });

        nigeria_sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something when the corky is clicked
                Intent intent = new Intent(getActivity(), single_player_load_page.class);
                //   EditText editText = (EditText) findViewById(R.id.editText);
                // String message = "final data";
                 intent.putExtra("picture_id", "nigeria_sport_icon_white");
                intent.putExtra("picture_text", "Nigeria Sport");
                intent.putExtra("picture_content", "Are you a Sport enthusiast ?  Take this challenge to find out ");

                startActivity(intent);
            }
        });

        nigeria_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something when the corky is clicked
                Intent intent = new Intent(getActivity(), single_player_load_page.class);
                //   EditText editText = (EditText) findViewById(R.id.editText);
                // String message = "final data";
                intent.putExtra("picture_id", "nigeria_movies_icon_white");
                intent.putExtra("picture_text", "Nigeria Movies");
                intent.putExtra("picture_content", "How well do you know Nigerian Movies ?  Play this game to Find out ");

                startActivity(intent);
            }
        });


        nigeria_entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something when the corky is clicked
                Intent intent = new Intent(getActivity(), single_player_load_page.class);
                //   EditText editText = (EditText) findViewById(R.id.editText);
                // String message = "final data";
                intent.putExtra("picture_id", "nigeria_entertainment_icon_white");
                intent.putExtra("picture_text", "Nigeria Entertainment Industry");
                intent.putExtra("picture_content", "Are you up to date with social activities going on in Nigeria ? play this game to find out.");

                startActivity(intent);
            }
        });


        nigeria_politics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), single_player_load_page.class);
                //   EditText editText = (EditText) findViewById(R.id.editText);
                // String message = "final data";
                intent.putExtra("picture_id", "nigeria_politics_icon_white");
                intent.putExtra("picture_text", "Nigeria Politics");
                intent.putExtra("picture_content", "Are you up to Date on Nigeria political and social affairs ? Play this game and find out ");

                startActivity(intent);
            }
        });


        single_player_param.load_layout_buttons();


    }





}