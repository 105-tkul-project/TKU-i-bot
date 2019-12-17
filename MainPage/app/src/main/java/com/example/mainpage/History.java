package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class History extends AppCompatActivity {

    TextView txtv1, txtv2;
    ImageView img1,img2,img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtv1=(TextView)findViewById(R.id.textView);
        txtv2=(TextView)findViewById(R.id.textView2);
        img1=(ImageView)findViewById(R.id.imageView);
        img2=(ImageView)findViewById(R.id.imageView2);
        img3=(ImageView)findViewById(R.id.imageView3);

        img1.setImageResource(R.drawable.tamkung);
        img2.setImageResource(R.drawable.hightable);
        img3.setImageResource(R.drawable.lanyang);
    }
}
