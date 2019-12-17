package com.example.mainpage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Office1 extends Fragment {
    ImageView iv1,iv2,iv3;
    TextView tx, tx2, tx3;
    public Office1(){
    }

    @Nullable
    @Override
    //container 是fragment 要放的容器, viewpager 是 fragment 的容器, R.layout.(這是放fragment1 專屬的layout =fragment01)
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_office1,container,false);
        iv1 = (ImageView) view.findViewById(R.id.imageView);
        iv2 = (ImageView) view.findViewById(R.id.imageView2);
        iv3 = (ImageView) view.findViewById(R.id.imageView3);

        tx=(TextView)view.findViewById(R.id.textView);
        tx2=(TextView)view.findViewById(R.id.textView2);
        tx3=(TextView)view.findViewById(R.id.textView3);

        iv1.setImageResource(R.drawable.a);
        iv2.setImageResource(R.drawable.b2);
        iv3.setImageResource(R.drawable.c);
        return view;

    }
}
