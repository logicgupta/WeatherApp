package com.logic.weatherapp.View;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.logic.weatherapp.Adapter.WeatherAdapter;
import com.logic.weatherapp.Model.Main;
import com.logic.weatherapp.Model.Weather;
import com.logic.weatherapp.Model.WeatherData;
import com.logic.weatherapp.Model.Wind;
import com.logic.weatherapp.Model.abc;
import com.logic.weatherapp.R;
import com.logic.weatherapp.Service.MyService;
import com.logic.weatherapp.ViewModel.WeatherViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FinalActivity extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    WeatherAdapter adapter;
    List<WeatherData> list=new ArrayList<>();
    WeatherViewModel viewModel;
    Bundle bundle;
    String city;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        bundle=getIntent().getExtras();
        city=bundle.getString("city");


        initViews();

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(FinalActivity.this);
            if (sharedPreferences.getString("name",null)==null){

                viewModel= ViewModelProviders.of(FinalActivity.this).get(WeatherViewModel.class);
                viewModel.fetchRepo(city);

                viewModel.getData().observe(FinalActivity.this, new Observer<WeatherData>() {
                    @Override
                    public void onChanged(@Nullable WeatherData weatherData) {


                        Wind wind1=weatherData.wind;
                        Main main=weatherData.main;
                        List<Weather> weatherList=weatherData.weather;
                        Log.e("FinalActivity"," "+main.pressure);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("name",weatherData.name);
                        editor.putInt("pressure",main.pressure);
                        editor.putFloat("temp",main.temp);
                        editor.putInt("hum",main.humidity);
                        editor.putString("desc",weatherList.get(0).description);
                        editor.putFloat("speed",wind1.speed);
                        editor.commit();
                        adapter.setData(weatherData.name,main.pressure,main.temp,main.humidity,weatherList.get(0).description,wind1.speed,1);
                        adapter.notifyDataSetChanged();
                    }
                });
                viewModel.getStatus().observe(FinalActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {

                        if (aBoolean){
                            progressBar.setVisibility(View.GONE);
                            startJob();
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(FinalActivity.this, "No Data Found !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }else if (sharedPreferences.getString("name",null).equalsIgnoreCase(city)){
                progressBar.setVisibility(View.GONE);
                String name=sharedPreferences.getString("name",null);
                int pressure=sharedPreferences.getInt("pressure",0);
                float temp=sharedPreferences.getFloat("temp",0);
                int humidity=sharedPreferences.getInt("hum",0);
                String description=sharedPreferences.getString("desc",null);
                float speed=sharedPreferences.getFloat("speed",0);
                adapter.setData(name,pressure,temp,humidity,description,speed,1);
                adapter.notifyDataSetChanged();

            }
            else{

                viewModel= ViewModelProviders.of(FinalActivity.this).get(WeatherViewModel.class);
                viewModel.fetchRepo(city);

                viewModel.getData().observe(FinalActivity.this, new Observer<WeatherData>() {
                    @Override
                    public void onChanged(@Nullable WeatherData weatherData) {


                        Wind wind1=weatherData.wind;
                        Main main=weatherData.main;
                        List<Weather> weatherList=weatherData.weather;
                        Log.e("FinalActivity"," "+main.pressure);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("name",weatherData.name);
                        editor.putInt("pressure",main.pressure);
                        editor.putFloat("temp",main.temp);
                        editor.putInt("hum",main.humidity);
                        editor.putString("desc",weatherList.get(0).description);
                        editor.putFloat("speed",wind1.speed);
                        editor.commit();
                        adapter.setData(weatherData.name,main.pressure,main.temp,main.humidity,weatherList.get(0).description,wind1.speed,1);
                        adapter.notifyDataSetChanged();
                    }
                });
                viewModel.getStatus().observe(FinalActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {

                        if (aBoolean){
                            progressBar.setVisibility(View.GONE);
                            startJob();
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(FinalActivity.this, "No Data Found !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }



    }

    public void initViews(){

        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(FinalActivity.this));
        adapter=new WeatherAdapter(FinalActivity.this,recyclerView);
        recyclerView.setAdapter(adapter);
    }

    public void startJob(){
        Log.e("vjkk","NKcnj");
        ComponentName componentName = new ComponentName(this, MyService.class);
        JobInfo info = new JobInfo.Builder(1234, componentName)
                //.setRequiresCharging(true)
                //.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(24*60*60* 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d("nkdsj", "Job scheduled");
        } else {
            Log.d("nvdn", "Job scheduling failed");
        }
    }
}
