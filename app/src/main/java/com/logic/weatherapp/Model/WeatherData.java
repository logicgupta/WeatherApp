package com.logic.weatherapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;












public class WeatherData {

    @SerializedName("coord")
    public Coord coord;

    @SerializedName("weather")
    public List<Weather> weather=new ArrayList<>();

    @SerializedName("base")
    public String base;

    @SerializedName("main")
    public Main main;

    @SerializedName("wind")
    public Wind wind;

    @SerializedName("visibility")
    public int visibility;

    @SerializedName("clouds")
    public Clouds clouds;

    @SerializedName("dt")
    public int dt;

    @SerializedName("sys")
    public Sys sys;

    @SerializedName("id")
    public  int id;

    @SerializedName("name")
    public String name;

    @SerializedName("cod")
    int cod;


}
