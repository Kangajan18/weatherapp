package com.kangajan.weatherapp.asyn.task;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.kangajan.weatherapp.activity.MainActivity;
import com.kangajan.weatherapp.viewmodel.WeatherViewModel;

public class WeatherAsyncTask extends AsyncTask<String, Activity,Object> {

    private String cityName;
    private Activity taskActivity;

    private WeatherViewModel weatherViewModel;


    public WeatherAsyncTask(Activity taskActivity, String cityName){
        this.taskActivity = taskActivity;
        this.cityName = cityName;
    }
    @Override
    protected Object doInBackground(String... strings) {

        return weatherViewModel.getWeather(cityName);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.e( "result",o.toString() );
        Intent intent = new Intent(taskActivity,MainActivity.class);
        taskActivity.startActivity(intent);
    }
}
