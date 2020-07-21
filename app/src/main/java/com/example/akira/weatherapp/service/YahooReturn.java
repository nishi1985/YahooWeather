package com.example.akira.weatherapp.service;

import com.example.akira.weatherapp.data.Channel;

public interface YahooReturn {
    void Success(Channel channel);
    void Failure (Exception exception);


}
