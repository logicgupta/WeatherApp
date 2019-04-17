package com.logic.weatherapp.Service;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.logic.weatherapp.Model.Main;
import com.logic.weatherapp.Model.Weather;
import com.logic.weatherapp.Model.WeatherData;
import com.logic.weatherapp.Model.Wind;
import com.logic.weatherapp.Repositery.FetchWeatherData;

import java.util.List;

public class MyService extends JobService{

    MyBackgroundTask backgroundTask;
    public static MutableLiveData<WeatherData> weatherDataMutableLiveData=new MutableLiveData<>();


    @Override
    public boolean onStartJob(final JobParameters params) {

        backgroundTask=new MyBackgroundTask()
        {
            @Override
            protected void onPostExecute(MutableLiveData<WeatherData> weatherDataMutableLiveData) {

                Toast.makeText(MyService.this, "Executed !", Toast.LENGTH_SHORT).show();
                Log.e("bvkbkvb","Job Srvice Called");
                if (weatherDataMutableLiveData!=null){
                    WeatherData weatherData=new WeatherData();
                    weatherData=weatherDataMutableLiveData.getValue();
                    Wind wind1=weatherData.wind;
                    Main main=weatherData.main;
                    List<Weather> weatherList=weatherData.weather;
                    Log.e("MyService"," "+weatherData.name);

                    SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(MyService.this);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("name",weatherData.name);
                    editor.putInt("pressure",main.pressure);
                    editor.putFloat("temp",main.temp);
                    editor.putInt("hum",main.humidity);
                    editor.putString("desc",weatherList.get(0).description);
                    editor.putFloat("speed",wind1.speed);
                    editor.commit();
                }

                jobFinished(params,false);

                super.onPostExecute(weatherDataMutableLiveData);
            }
        };
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(MyService.this);

        backgroundTask.execute(sharedPreferences.getString("name",null));
        return true;
    }

    public static class MyBackgroundTask extends AsyncTask<String,Void,MutableLiveData<WeatherData>>{


        @Override
        protected MutableLiveData<WeatherData> doInBackground(String... params) {
            int i;
            FetchWeatherData fetchWeatherData=FetchWeatherData.getInstance();
            Log.e("scccs"," xdd"+params[0]);
            weatherDataMutableLiveData=fetchWeatherData.getData(params[0]);
            WeatherData weatherData=weatherDataMutableLiveData.getValue();

            for(i= 0; i < 5; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (i==5){
                Log.e("Sssvv","doBackground Called");
                // Wind wind1=weatherData.wind;
                //  Main main=weatherData.main;
                //  List<Weather> weatherList=weatherData.weather;
                return weatherDataMutableLiveData;
            }
            else {
                return null;
            }

        }

    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
