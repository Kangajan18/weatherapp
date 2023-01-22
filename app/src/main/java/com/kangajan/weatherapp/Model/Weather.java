package com.kangajan.weatherapp.Model;

import lombok.Data;

@Data
public class Weather {
    private String name;
    private String temp_c;
    private String localTime;
}
