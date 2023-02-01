package com.kangajan.weatherapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.kangajan.weatherapp.R;

public class WelcomePage extends AppCompatActivity {

    private Button startButton;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);

        startButton = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);

        loadBackGround();



        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStartButton();
            }
        });
    }



    private void loadBackGround(){
        progressBar.setVisibility(View.INVISIBLE);
    }


    public void onClickStartButton(){
        startButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this,MainActivity.class);

        startActivity(intent);

    }
}