package com.example.android.sunshine;

//import android.content.Context;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Антон on 19.12.2017.
 */

public class ForecastAdapter extends RecyclerView.Adapter <ForecastAdapter.ForecastAdapterViewHolder> {
    private String [] mWeatherData;

    public ForecastAdapter() {

    }

    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder {
        public final TextView mWeatherTextView;

        public ForecastAdapterViewHolder(View view) {
            super(view);

            mWeatherTextView = (TextView) view.findViewById(R.id.tv_weather_data);
        }
    }

    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int listItemLayout = R.layout.forecast_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(listItemLayout, viewGroup, shouldAttachToParentImmediately);

        return new ForecastAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder viewHolder, int itemIndex) {
        String itemWeather = mWeatherData[itemIndex];
        viewHolder.mWeatherTextView.setText(itemWeather);
    }

    @Override
    public int getItemCount () {
        if (mWeatherData == null) {
            return 0;
        } else {
            return mWeatherData.length;
        }
    }

    public void setWeatherData(String [] weatherData) {
        mWeatherData = weatherData;
        notifyDataSetChanged();
    }
}
