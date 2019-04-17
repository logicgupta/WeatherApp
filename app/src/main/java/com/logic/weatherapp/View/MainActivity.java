package com.logic.weatherapp.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.logic.weatherapp.R;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    String city;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        initViews();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city=editText.getText().toString().trim();
                if (city.equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this, "Please Enter The City !", Toast.LENGTH_SHORT).show();
                }
                else{

                    Intent intent=new Intent(MainActivity.this,FinalActivity.class);
                    intent.putExtra("city",city);
                    startActivity(intent);
                }
            }
        });
    }

    void initViews(){
        editText=findViewById(R.id.cityEdiText);
        button=findViewById(R.id.submit);


    }
}
