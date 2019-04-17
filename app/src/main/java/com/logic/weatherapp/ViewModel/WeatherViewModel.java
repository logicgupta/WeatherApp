package com.logic.weatherapp.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.logic.weatherapp.Model.WeatherData;
import com.logic.weatherapp.Repositery.FetchWeatherData;

public class WeatherViewModel extends ViewModel {

    MutableLiveData<WeatherData> mutableLiveData=new MutableLiveData<>();
    MutableLiveData<Boolean> status=new MutableLiveData<>();

    FetchWeatherData fetchWeatherData;

    public void fetchRepo(String city){

        fetchWeatherData=FetchWeatherData.getInstance();
        mutableLiveData=fetchWeatherData.getData(city);
        status=fetchWeatherData.getStatus();
    }

    public MutableLiveData<WeatherData> getData(){
        return mutableLiveData;

    }

    public MutableLiveData<Boolean> getStatus(){                     // Getting status from Repositery Class

        return status;
    }

}
