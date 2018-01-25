package com.example.android.sunshine.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.data.WeatherDbHelper;
import com.example.android.sunshine.data.WeatherProvider;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import java.io.IOException;
import java.net.URL;

//  TODO (1) Create a class called SunshineSyncTask
public class SunshineSyncTask {
//  TODO (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
    synchronized public static void syncWeather(Context context) {
//      TODO (3) Within syncWeather, fetch new weather data
        URL weatherRequestUrl = NetworkUtils.getUrl(context);

        String weatherJsonString = null;
        try {
            weatherJsonString = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl);

            ContentValues [] weatherInfo = OpenWeatherJsonUtils.getWeatherContentValuesFromJson(context, weatherJsonString);

//      TODO (4) If we have valid results, delete the old data and insert the new
            if (weatherInfo != null && weatherInfo.length > 0) {
                ContentResolver contentResolver = context.getContentResolver();

                contentResolver.delete(WeatherContract.WeatherEntry.CONTENT_URI,
                        null,
                        null);

                contentResolver.bulkInsert(WeatherContract.WeatherEntry.CONTENT_URI,
                        weatherInfo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}