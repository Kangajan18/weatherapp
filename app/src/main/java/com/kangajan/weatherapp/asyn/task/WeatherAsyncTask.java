package com.kangajan.weatherapp.asyn.task;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.kangajan.weatherapp.Model.Weather;
import com.kangajan.weatherapp.activity.MainActivity;
import com.kangajan.weatherapp.network.WeatherNetworkService;
import com.kangajan.weatherapp.rest.RestClient;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherAsyncTask extends AsyncTask<String, Activity,Weather> {

    private String cityName;
    private Activity taskActivity;



    public WeatherAsyncTask(Activity taskActivity, String cityName){
        this.taskActivity = taskActivity;
        this.cityName = cityName;
    }
    @Override
    protected Weather doInBackground(String... strings) {
        Weather weather = new Weather();
        try {
            weather = new RestClient(taskActivity).getWeatherObject(cityName);
        } catch (Exception e) {
            Log.e("Failed to get Response",e.getMessage());
        }
        return weather;
    }

    @Override
    protected void onPostExecute(Weather weather) {
        super.onPostExecute(weather);
        Log.e( "dataresult",weather.toString() );
        Intent intent = new Intent(taskActivity,MainActivity.class);
        taskActivity.startActivity(intent);
    }
}
