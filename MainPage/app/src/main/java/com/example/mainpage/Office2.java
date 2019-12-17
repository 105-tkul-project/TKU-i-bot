package com.example.mainpage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Office2 extends Fragment {
    ImageView im4, im5, im6;
    TextView tx4, tx5, tx6, tx7, tx8, tx9,tx10,tx11,tx12,tx13,tx14,tx15;
    public Office2(){
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_office2,container,false);

        im4 = (ImageView) view.findViewById(R.id.imageView4);
        im5 = (ImageView) view.findViewById(R.id.imageView5);
        im6 = (ImageView) view.findViewById(R.id.imageView6);

        tx4=(TextView)view.findViewById(R.id.textView4);
        tx5=(TextView)view.findViewById(R.id.textView5);
        tx6=(TextView)view.findViewById(R.id.textView6);
        tx7=(TextView)view.findViewById(R.id.textView7);
        tx8=(TextView)view.findViewById(R.id.textView8);
        tx9=(TextView)view.findViewById(R.id.textView9);
        tx10=(TextView)view.findViewById(R.id.textView10);
        tx11=(TextView)view.findViewById(R.id.textView11);
        tx12=(TextView)view.findViewById(R.id.textView12);
        tx13=(TextView)view.findViewById(R.id.textView13);
        tx14=(TextView)view.findViewById(R.id.textView14);
        tx15=(TextView)view.findViewById(R.id.textView15);

        im4.setImageResource(R.drawable.language01);
        im5.setImageResource(R.drawable.language02);
        im6.setImageResource(R.drawable.language03);
        return view;
    }
}
