package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Weather extends AppCompatActivity {

    TextView cityName;
    TextView cloud;
    TextView temp;
    TextView degree;
    TextView humidity;


    class Weather1 extends AsyncTask<String, Void, String> { // First String means URL is in String, Void mean nothing, Third String means Return type will be String

        @Override
        protected String doInBackground(String... address) {
            //String... means multiple address can be send. Ti acts as array
            try {
                URL url = new URL(address[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //Establish connection with address

                connection.connect();

                //retrieve data form url
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                //Retrieve data and return is as String

                int data = isr.read();
                String content = "";
                char ch;
                while (data != -1) {
                    ch = (char) data;
                    content = content + ch;
                    data = isr.read();
                }
                return content;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


        cityName = findViewById(R.id.cityName);
        cloud = findViewById(R.id.cloud);
        temp = findViewById(R.id.temperature);
        degree = findViewById(R.id.degree);
        humidity = findViewById(R.id.hum);
        final String cName = "jiaoxi";

        String content;
        Weather1 weather = new Weather1();
        try {
            content = weather.execute("https://openweathermap.org/data/2.5/weather?q=" + cName +
                    "&appid=b6907d289e10d714a6e88b30761fae22").get();

            //First we will check data is retrieve successfully or not


            //JSON
            JSONObject jsonObject = new JSONObject(content);
            String weatherData = jsonObject.getString("weather");
            String mainTemperature = jsonObject.getString("main"); //this main is not part of weather array, it's separate variable
            double visibility;
            // Log.i("weatherData",weatherData);
            //weather data is in Array


            JSONArray array = new JSONArray(weatherData);

            String main = "";
            String description = "";
            String temperature = "";
            String temp_min = "";
            String temp_max = "";
            String hum = "";


            for (int i = 0; i < array.length(); i++) {
                JSONObject weatherPart = array.getJSONObject(i);
                main = weatherPart.getString("main");
                description = weatherPart.getString("description");

            }

            JSONObject mainPart = new JSONObject(mainTemperature);
            temperature = mainPart.getString("temp");
            temp_min = mainPart.getString("temp_min");
            temp_max = mainPart.getString("temp_max");
            hum = mainPart.getString("humidity");

            visibility = Double.parseDouble(jsonObject.getString("visibility"));
            //By default visibility is in meter
            int visibilityInKilometer = (int) visibility / 1000;


//            String resultText = "Main :      "+main+"" +
//                    "\nDescription :    "+description + "" +
//                    "\nTemperature :    "+temperature + "*C"+
//                    "\nvisibility :     "+ visibilityInKilometer + "KM";
            String temperaturetxt = temperature + "*C";


            temp.setText(temperaturetxt);
            cloud.setText(description);
            degree.setText("最低溫 ~ 最高溫\n" + temp_min + "~" + temp_max);
            humidity.setText("濕度\n" + hum + "%");



            //Now we will show this result on screen
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
