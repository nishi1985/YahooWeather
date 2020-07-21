package com.example.akira.weatherapp.data;

import org.json.JSONObject;

public class Item implements JSON{
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void content(JSONObject data){
        condition = new Condition();
        condition.content(data.optJSONObject("condition"));

    }
}
