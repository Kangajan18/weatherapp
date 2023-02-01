package com.kangajan.weatherapp.asyn.task;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kangajan.weatherapp.Model.Weather;
import com.kangajan.weatherapp.R;
import com.kangajan.weatherapp.activity.MainActivity;
import com.kangajan.weatherapp.activity.WelcomePage;
import com.kangajan.weatherapp.network.WeatherNetworkService;
import com.kangajan.weatherapp.rest.RestClient;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

        preExcutionTask();
    }

    public void preExcutionTask() {
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

        //https://cdn.weatherapi.com/weather/64x64/day/176.png

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
