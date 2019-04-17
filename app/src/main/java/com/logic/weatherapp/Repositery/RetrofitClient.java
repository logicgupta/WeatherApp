package com.logic.weatherapp.Repositery;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static  String BASE_URL="https://samples.openweathermap.org/data/2.5/";
    private static RetrofitClient instance;
    private static Retrofit retrofit;


    private RetrofitClient(){

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public  static  synchronized RetrofitClient getInstance(){

        if (instance==null){
            instance=new RetrofitClient();
        }
        return instance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }

}
