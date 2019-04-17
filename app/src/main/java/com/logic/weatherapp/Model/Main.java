package com.logic.weatherapp.Model;

import com.google.gson.annotations.SerializedName;

public class Main{

    @SerializedName("temp")
    public float temp;
    @SerializedName("pressure")
    public int pressure;
    @SerializedName("humidity")
    public int humidity;
    @SerializedName("temp_min")
    public float temp_min;
    @SerializedName("temp_max")
    public float temp_max;

}
