package com.example.akira.weatherapp.service;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.akira.weatherapp.data.Channel;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class YahooWeather {
    private YahooReturn aReturn;
    private String location;
    private Exception error;

    public YahooWeather(YahooReturn aReturn){
        this.aReturn = aReturn;
    }

    public String getLocation() {
        return location;
    }

    @SuppressLint("StaticFieldLeak")//verificar (preenchimento automatico)
    public void aRefresh(final String location) {               //
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
               String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", location);
               String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try {
                    URL url = new URL(endpoint);

                    URLConnection connection = url.openConnection();

                    InputStream inputStream = connection.getInputStream();

                    BufferedReader reader= new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return result.toString();

                } catch (java.io.IOException e) {
                    error = e;
                    //e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s == null && error != null){
                    aReturn.Failure(error);
                    return;
                }

                try {
                    JSONObject data = new JSONObject(s);
                    JSONObject queryResults = data.optJSONObject("query");
                    int count = queryResults.optInt("count");

                    if (count == 0){
                        aReturn.Failure(new LocalException("Não localizado!"));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.content(queryResults.optJSONObject("results").optJSONObject("channel"));

                    aReturn.Success(channel);


                } catch (JSONException e) {
                    aReturn.Failure(e);
                }

            }
        }.execute(location);
    }
    public class LocalException extends Exception{

        public LocalException(String message) {
            super(message);
        }
    }
}
