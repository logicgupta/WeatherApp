package com.logic.weatherapp.Repositery;

import com.logic.weatherapp.Model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("weather")
    Call<WeatherData> getWeatherData(
            @Query("q")String city,
            @Query("appid")String appid);

}
