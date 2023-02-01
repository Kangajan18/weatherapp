package com.kangajan.weatherapp.Model;

import lombok.Data;

@Data
public class Weather {
    public Location location;
    public Current current;
}
