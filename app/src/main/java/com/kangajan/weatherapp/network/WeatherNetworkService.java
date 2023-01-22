package com.kangajan.weatherapp.network;

import com.kangajan.weatherapp.Model.Weather;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherNetworkService {

    @GET("https://api.weatherapi.com/v1/current.json?key=3102e42a24c04b16aee20719232201&q=/{city}/&aqi=no")
    Weather getWeather(@Path("city") String city);
}
