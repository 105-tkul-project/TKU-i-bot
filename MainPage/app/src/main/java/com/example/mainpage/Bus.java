package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Bus extends AppCompatActivity {

    TextView t1, t2, t3;
    ImageView im1, im2, im3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        t1=(TextView)findViewById(R.id.textView);
        t2=(TextView)findViewById(R.id.textView2);
        t3=(TextView)findViewById(R.id.textView3);

        im1=(ImageView)findViewById(R.id.imageView);
        im2=(ImageView)findViewById(R.id.imageView2);
        im3=(ImageView)findViewById(R.id.imageView3);

        im1.setImageResource(R.drawable.time);
        im2.setImageResource(R.drawable.line);
        im3.setImageResource(R.drawable.bus);
    }
}
