package com.kangajan.weatherapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kangajan.weatherapp.R;
import com.kangajan.weatherapp.asyn.task.WeatherAsyncTask;

public class MainActivity extends AppCompatActivity {

    private EditText citySearch;
    private Button searchButton;

    private String cityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        citySearch = findViewById(R.id.editCitySearch);
        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e( "button: ","working" );

                searchFunction();
            }
        });
    }

    public void searchFunction(){
        cityName = citySearch.getText().toString();
        Log.e( "searchFunction: ","working" );
        WeatherAsyncTask weatherAsyncTask = new WeatherAsyncTask(this,cityName);
        weatherAsyncTask.execute("");
    }
}

