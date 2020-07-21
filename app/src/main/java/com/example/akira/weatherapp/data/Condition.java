package com.example.akira.weatherapp.data;

import org.json.JSONObject;

public class Condition implements JSON{
    private int code;
    private int temperature;
    private String descrition;

    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDescrition() {
        return descrition;
    }

    @Override
    public void content (JSONObject data){
        code = data.optInt("code");
        temperature = data.optInt("temp");
        descrition = data.optString("text");

    }
}
