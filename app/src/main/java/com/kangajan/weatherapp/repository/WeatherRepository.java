package com.kangajan.weatherapp.repository;

import com.kangajan.weatherapp.Model.Weather;
import com.kangajan.weatherapp.network.WeatherNetworkService;

public class WeatherRepository {

    private final WeatherNetworkService weatherNetworkService;

    public WeatherRepository(WeatherNetworkService weatherNetworkService) {
        this.weatherNetworkService = weatherNetworkService;
    }

    public Weather getWeather(String city) {
        return weatherNetworkService.getWeather(city);
    }
}
