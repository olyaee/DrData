package com.example.ehsan.drdata;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ehsan-HP on 20/07/2017.
 */

public class FetchDataTask extends AsyncTask<String, Void, String[]> {
    private final Context mContext;
    private ArrayAdapter<String> mForecastAdapter;

    public FetchDataTask(Context context, ArrayAdapter<String> forecastAdapter) {
        mContext = context;
        mForecastAdapter = forecastAdapter;
    }

    @Override
    protected String[] doInBackground(String... params) {

        String[] ehsan = new String[1];
        ehsan[0] = null ;
        return ehsan;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        // Create some dummy data for the ListView.  Here's a sample weekly forecast
        String[] data1;
        if (strings[0] != null) {
            data1 = strings;
        } else {
            data1 = new String[]{
                    "Nothing to show",
                    "Not any Data"};
        }
        List<String> weekForecast = new ArrayList<String>(Arrays.asList(data1));

        if (data1 != null) {
            mForecastAdapter.clear();
            for (String dayForecastStr : data1) {
                mForecastAdapter.add(dayForecastStr);
            }
            // New data is back from the server.  Hooray!
        }
    }

}
