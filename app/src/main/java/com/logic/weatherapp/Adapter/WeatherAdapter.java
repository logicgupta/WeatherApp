package com.logic.weatherapp.Adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.logic.weatherapp.R;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    Context context;
    RecyclerView recyclerView;
    String name;
    int pressure;
    float temp;
    String desc;
    float hum;
    float speed;
    int size;

    public WeatherAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.custom_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder viewHolder, int i) {
        viewHolder.textViewName.setText("  "+name);
        viewHolder.textViewPressure.setText("  "+pressure);
        viewHolder.textViewwindSpeed.setText("  "+speed);
        viewHolder.textViewTemp.setText("  "+temp);
        viewHolder.textViewHumidity.setText("  "+hum);
        viewHolder.textViewDesc.setText("  "+desc);


    }

    @Override
    public int getItemCount() {
        return size;
    }

    public void setData(String name1,
                        int pressure1, float temp1, float hum1, String desc1, float speed1,int size1){
        name=name1;
        pressure=pressure1;
        temp=temp1;
        desc=desc1;
        hum=hum1;
        speed=speed1;
        size=size1;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewName,textViewwindSpeed,textViewTemp,textViewHumidity,textViewDesc,textViewPressure;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName=itemView.findViewById(R.id.textView1);
            textViewDesc=itemView.findViewById(R.id.textView2);
            textViewTemp=itemView.findViewById(R.id.textView3);
            textViewwindSpeed=itemView.findViewById(R.id.textView4);
            textViewHumidity=itemView.findViewById(R.id.textView5);
            textViewPressure=itemView.findViewById(R.id.textView6);

        }
    }

}
