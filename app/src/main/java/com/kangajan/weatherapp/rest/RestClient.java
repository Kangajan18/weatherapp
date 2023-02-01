package com.kangajan.weatherapp.rest;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.kangajan.weatherapp.Model.Weather;
import com.kangajan.weatherapp.config.Config;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

public class RestClient {

    private static int CONNECTION_TIMEOUT = 75000;

    private static int READ_TIMEOUT = 60000;

    private static String POST = "POST";

    private static String GET = "GET";

    private static String PUT = "PUT";

    private static String DELETE = "DELETE";

    private Activity taskActivity;


    public RestClient(Activity activity) {
        this.taskActivity = activity;
    }


    public Weather getWeatherObject(String city) throws Exception {
        Log.e("cityName", String.valueOf(city));

        Weather weather = null;
        StringBuilder responseString = new StringBuilder();
        String fullUrl = (Config.WEATHER_DATA_URL).replace("%s",city);
        Log.e("fullUrl", fullUrl);
        URL requestUrl = new URL(fullUrl);
        HttpURLConnection conn = getConnection(GET, requestUrl);
        conn.connect();

        InputStream in = getConnectionInputStream(conn);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = reader.readLine()) != null) {
            responseString.append(line);
        }

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 299) {
            Log.e("GET 1 weather Response", responseString.toString());

            weather = new Gson().fromJson(String.valueOf(responseString),Weather.class);
            Log.e("GET weather Response >>", weather.getLocation().country.toString());

        } else {
            return null;
        }
        conn.disconnect();
        return  weather;

    }

    private InputStream getConnectionInputStream(HttpURLConnection httpURLConnection) throws Exception {
        InputStream in;
        if (httpURLConnection.getResponseCode() >= 200 && httpURLConnection.getResponseCode() <= 299) {
            in = new BufferedInputStream(httpURLConnection.getInputStream());
        } else {
            in = new BufferedInputStream(httpURLConnection.getErrorStream());
        }
        if ("gzip".equals(httpURLConnection.getContentEncoding())) {
            in = new GZIPInputStream(in);
        }
        return in;
    }

    private HttpURLConnection getConnection(String type, URL requestUrl) throws Exception {
        if (requestUrl.getProtocol().equals("https")) {
            HttpsURLConnection conn = (HttpsURLConnection) requestUrl.openConnection();
            return conn;
        } else {
            HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
            return conn;
        }
    }
}