package com.kangajan.weatherapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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

        if (getIntent().getStringExtra("warning") != null) {
            if (getIntent().getStringExtra("warning").equals("true")) {
                showErrorDialog();
            }
        }

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("button: ", "working");

                searchFunction();
            }
        });
    }

    public void showErrorDialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater1 = this.getLayoutInflater();
        final View view = inflater1.inflate(R.layout.activity_error_message_dialog, null);
        dialogBuilder.setView(view);

        final AlertDialog dialogBox = dialogBuilder.create();

        Button doneButton = view.findViewById(R.id.doneButton);

        doneButton.setOnClickListener(v -> {
            dialogBox.dismiss();
            appStart();
        });

        dialogBox.show();

    }

    private void appStart() {
        citySearch.setText("");
    }

    public void searchFunction() {
        cityName = citySearch.getText().toString();
        Log.e("searchFunction: ", "working");
        WeatherAsyncTask weatherAsyncTask = new WeatherAsyncTask(this, cityName);
        weatherAsyncTask.execute("");
    }
}

