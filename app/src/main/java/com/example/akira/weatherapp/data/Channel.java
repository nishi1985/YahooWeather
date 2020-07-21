package com.example.akira.weatherapp.data;

import org.json.JSONObject;

public class Channel implements JSON{
    public Item getItem() {
        return item;
    }

    public Units getUnits() {
        return units;
    }

    private Item item;
    private Units units;

    @Override
    public void content (JSONObject data){
       item = new Item();
       item.content(data.optJSONObject("item"));
       units = new Units();
       units.content(data.optJSONObject("units"));

    }
}
