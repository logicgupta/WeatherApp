package com.logic.weatherapp.Model;

import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("speed")
    public float speed;

    @SerializedName("deg")
    public int deg;
}
