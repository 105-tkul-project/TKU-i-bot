package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
// this project has dependency with python LSTM predict.py
public class MainPage extends AppCompatActivity implements View.OnClickListener {
    ImageButton btn_ibot,btn_weather,btn_bus,btn_office,btn_history,btn_map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bond();
    }

    private void Bond(){
        btn_ibot = findViewById(R.id.btn_ibot);
        btn_weather= findViewById(R.id.btn_weater);
        btn_bus= findViewById(R.id.btn_bus);
        btn_office= findViewById(R.id.btn_office);
        btn_history= findViewById(R.id.btn_history);
        btn_map= findViewById(R.id.btn_map);

        btn_ibot.setOnClickListener(this);
        btn_weather.setOnClickListener(this);
        btn_bus.setOnClickListener(this);
        btn_office.setOnClickListener(this);
        btn_history.setOnClickListener(this);
        btn_map.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btn_ibot){
            Intent intent = new Intent(this,MessageActivity.class);
            startActivity(intent);
        }else if(v==btn_bus){
            Intent intent = new Intent(this,Bus.class);
            startActivity(intent);
        }
        else if(v==btn_map){
            Intent intent = new Intent(this,Map.class);
            startActivity(intent);
        }
        else if(v==btn_history){
            Intent intent = new Intent(this,History.class);
            startActivity(intent);
        }
        else if(v==btn_office){
            Intent intent = new Intent(this,Office.class);
            startActivity(intent);
        }
        else if(v==btn_weather){
            Intent intent = new Intent(this,Weather.class);
            startActivity(intent);
        }
    }
}
