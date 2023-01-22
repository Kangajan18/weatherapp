package com.kangajan.weatherapp.viewmodel;

import android.util.Log;

import com.kangajan.weatherapp.Model.Weather;
import com.kangajan.weatherapp.repository.WeatherRepository;

public class WeatherViewModel {
    private final String TAG = "WeatherViewModel";

    private final WeatherRepository weatherRepository;

    public WeatherViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather getWeather(String city){
        Weather weather = this.weatherRepository.getWeather(city);
        Log.d(TAG, weather.toString());
        return weather;
    }
}
