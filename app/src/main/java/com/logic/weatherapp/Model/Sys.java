package com.logic.weatherapp.Model;

import com.google.gson.annotations.SerializedName;

public class Sys{
    @SerializedName("type")
    public int type;
    @SerializedName("id")
    public int id;
    @SerializedName("message")
    public float message;
    @SerializedName("country")
    public String country;
    @SerializedName("sunrise")
    public int sunrise;
    @SerializedName("sunset")
    public int sunset;
}