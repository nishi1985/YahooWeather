package com.example.akira.weatherapp.data;

import org.json.JSONObject;

public class Units implements JSON {
    private String temperature;
    @Override
    public void content(JSONObject data){
        temperature=data.optString("temperature");
    }

    public String getTemperature() {
        return temperature;
    }
}
