package com.logic.weatherapp.Repositery;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.logic.weatherapp.Model.WeatherData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchWeatherData {

    private static FetchWeatherData instance;
    MutableLiveData<Boolean> status=new MutableLiveData<>();
    MutableLiveData<WeatherData> weatherDataMutableLiveData;

    String apiKey="266791252176b20d99b84865beb58dfb";


    public static FetchWeatherData getInstance(){
        if (instance==null){
            instance=new FetchWeatherData();
        }
        return instance;
    }

    public void fetchData(String city){

        RetrofitClient
                .getInstance()
                .getApi()
                .getWeatherData(city,apiKey)
                .enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                        if (response.body()!=null){

                            Log.e("FetchWeatherData :-"," "+response.body());
                            weatherDataMutableLiveData.postValue(response.body());
                            status.setValue(true);

                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherData> call, Throwable t) {

                        Log.e("FetchweatherData","Error : "+t);
                        status.setValue(false);
                    }
                });
    }

    public MutableLiveData<WeatherData> getData(String city){
        weatherDataMutableLiveData=new MutableLiveData<>();
        status=new MutableLiveData<>();

        fetchData(city);
        return weatherDataMutableLiveData;
    }


    public MutableLiveData<Boolean> getStatus()
    {
        return status;
    }
}
