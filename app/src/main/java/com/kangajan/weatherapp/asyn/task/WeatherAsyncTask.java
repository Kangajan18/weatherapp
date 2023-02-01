package com.kangajan.weatherapp.asyn.task;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kangajan.weatherapp.Model.Weather;
import com.kangajan.weatherapp.R;
import com.kangajan.weatherapp.activity.MainActivity;
import com.kangajan.weatherapp.rest.RestClient;

public class WeatherAsyncTask extends AsyncTask<String, Activity, Weather> {

    private String cityName;
    private Activity taskActivity;


    private TextView dateTextView;
    private TextView cityTextView;
    private TextView temp_cTextView;

    private EditText citySearchEditText;

    private ProgressBar progressBarData;
    private LinearLayout dataLinearLayout;

    private ImageView imageViewWeather;

    public static final int LENGTH_SHORT = 5;


    public WeatherAsyncTask(Activity taskActivity, String cityName) {
        this.taskActivity = taskActivity;
        this.cityName = cityName;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (taskActivity instanceof MainActivity) {
            dateTextView = taskActivity.findViewById(R.id.textviewDate);
            cityTextView = taskActivity.findViewById(R.id.textviewCity);
            temp_cTextView = taskActivity.findViewById(R.id.textviewData);
            progressBarData = taskActivity.findViewById(R.id.progressBarData);
            imageViewWeather = taskActivity.findViewById(R.id.imageViewWeather);
            dataLinearLayout = taskActivity.findViewById(R.id.dataLinearLayout);
            citySearchEditText = taskActivity.findViewById(R.id.editCitySearch);
        }
        preExecutionTask();
    }

    public void preExecutionTask() {
        progressBarData.setVisibility(View.VISIBLE);
        imageViewWeather.setVisibility(View.INVISIBLE);
    }

    @Override
    protected Weather doInBackground(String... strings) {
        Weather weather = new Weather();

        try {
            weather = new RestClient(taskActivity).getWeatherObject(cityName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return weather;
    }

    @Override
    protected void onPostExecute(Weather weather) {
        super.onPostExecute(weather);
        if (weather != null) {
            doBackGroundTask(weather);
        } else {
            Intent intent = new Intent(taskActivity,MainActivity.class);
            intent.putExtra("warning","true");
            taskActivity.startActivity(intent);
        }
    }

    public void doBackGroundTask(Weather weather) {

        dataLinearLayout.setVisibility(View.VISIBLE);
        progressBarData.setVisibility(View.INVISIBLE);
        String imageUrl = "https:" + weather.getCurrent().condition.icon;
        Log.e("fullUrl", imageUrl);
        imageViewWeather.setVisibility(View.VISIBLE);

        Glide.with(taskActivity)
                .load(imageUrl)
                .into(imageViewWeather);

        dateTextView.setText(weather.getLocation().localtime.subSequence(0, 10));
        cityTextView.setText(weather.getLocation().name);
        temp_cTextView.setText(String.valueOf(weather.getCurrent().temp_c));
        temp_cTextView.append("°C");
    }
}
