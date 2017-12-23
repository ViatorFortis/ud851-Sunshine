package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTextView = (TextView) findViewById(R.id.tv_detailWeatherForDay);

        // TODO (2) Display the weather forecast that was passed from MainActivity
        Intent intentToStartDetailActivity = this.getIntent();
        String weatherForDay = intentToStartDetailActivity.getCharSequenceExtra(Intent.EXTRA_TEXT).toString();
        mTextView.setText(weatherForDay);
    }
}