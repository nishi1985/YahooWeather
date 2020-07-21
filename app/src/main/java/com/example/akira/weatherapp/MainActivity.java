package com.example.akira.weatherapp;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akira.weatherapp.data.Channel;
import com.example.akira.weatherapp.data.Item;
import com.example.akira.weatherapp.service.YahooReturn;
import com.example.akira.weatherapp.service.YahooWeather;

public class MainActivity extends AppCompatActivity implements YahooReturn {

    private TextView txt_temp;
    private TextView txt_cond;
    private TextView txt_local;
    private ImageView imageView_icon;
    private YahooWeather service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_temp = (TextView)findViewById(R.id.txt_temp);
        txt_cond = (TextView)findViewById(R.id.txt_cond);
        txt_local = (TextView)findViewById((R.id.txt_local));
        imageView_icon = (ImageView)findViewById(R.id.imageView_Icon);
        service = new YahooWeather(this);
        service.aRefresh("Sao Paulo, BR");
    }

    @Override
    public void Success(Channel channel) {
        //mensagem de confirmação

        Item item = channel.getItem();
        int resource = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());

//        Drawable imageView_icon = getResources().getDrawable(resource);

//        imageView_icon.setImageDrawable(imageView_icon);

        txt_temp.setText(item.getCondition().getTemperature() + "\u00B8" + channel.getUnits().getTemperature());
        txt_local.setText(service.getLocation());
        txt_cond.setText(item.getCondition().getDescrition());


    }

    @Override
    public void Failure(Exception exception) {
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
